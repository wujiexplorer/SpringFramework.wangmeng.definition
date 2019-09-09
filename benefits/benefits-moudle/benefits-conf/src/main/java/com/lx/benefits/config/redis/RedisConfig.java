package com.lx.benefits.config.redis;

import com.apollo.common.lock.support.DistributedLock;
import com.apollo.common.lock.support.RedisDistributedLock;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Set;

@Configuration
@EnableCaching
@ConditionalOnClass(RedisOperations.class)
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfig extends CachingConfigurerSupport {

    private final static String DEV_PROFILE="dev";

    private final static String REDIS_KEY_PREFIX = "benefits" ;

    @Autowired
    private Environment environment;

    @Autowired
    private RedisProperties redisProperties;
    /**
     * 是否本地开发环境
     * @return
     */
    private boolean isLocalEnvironment() {
        String[] activeProfiles = environment.getActiveProfiles();
        for(String profile : activeProfiles){
            if( profile.equals( DEV_PROFILE ) ){
                return true;
            }
        }
        return false;
    }

    /**
     * 自定义缓存key的生成策略。默认的生成策略是看不懂的(乱码内容) 通过Spring 的依赖注入特性进行自定义的配置注入并且此类是一个配置类可以更多程度的自定义配置
     *
     * @return
     */
    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(method.getName());
            for (Object obj : params) {
                sb.append(obj.toString());
            }
            return sb.toString();
        };
    }

    @Bean
    public JedisPoolConfig getJedisPoolConfig(){

        RedisProperties.Pool pool = redisProperties.getJedis().getPool();
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //最大空闲连接数, 默认8个
        jedisPoolConfig.setMaxIdle(pool.getMaxIdle());
        //最大连接数, 默认8个
        jedisPoolConfig.setMaxTotal(pool.getMaxActive());
        //最小空闲连接数, 默认0
        jedisPoolConfig.setMinIdle(pool.getMinIdle());
        //获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1
        jedisPoolConfig.setMaxWaitMillis(15000);
        //逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
        jedisPoolConfig.setMinEvictableIdleTimeMillis(300000);
        //每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
        jedisPoolConfig.setNumTestsPerEvictionRun(3);
        //一个连接在池中最小生存的时间
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(60000);
        //连接超时时是否阻塞，false时报异常,ture阻塞直到超时, 默认true
        jedisPoolConfig.setBlockWhenExhausted(true);
        return jedisPoolConfig;
    }

    @Bean(name = "sellerJedisConnectionFactory")
    @Primary
    public JedisConnectionFactory getJedisConnectionFactory(){
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setPoolConfig(getJedisPoolConfig());
        jedisConnectionFactory.setHostName(redisProperties.getHost());
        jedisConnectionFactory.setPort(redisProperties.getPort());
        jedisConnectionFactory.setPassword(redisProperties.getPassword());
        jedisConnectionFactory.setTimeout( (int)redisProperties.getTimeout().toMillis());
        return jedisConnectionFactory;
    }

    /**
     * 缓存管理器
     */
    @Bean
    @Override
    public CacheManager cacheManager() {

        RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager.RedisCacheManagerBuilder
                .fromConnectionFactory(getJedisConnectionFactory());
        @SuppressWarnings("serial")
        Set<String> cacheNames = Sets.newHashSet(REDIS_KEY_PREFIX);
        builder.initialCacheNames(cacheNames);
        return builder.build();
    }


    /**
     * RedisTemplate配置
     */
    @Bean
    @Primary
    public RedisTemplate<String, Object> redisTemplate() {

        RedisSerializer<?> stringSerializer = new StringRedisSerializer();
        /**以下代码为将RedisTemplate的Value序列化方式由JdkSerializationRedisSerializer更换为Jackson2JsonRedisSerializer
         此种序列化方式结果清晰、容易阅读、存储字节少、速度快，所以推荐更换*/
//        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(
//                Object.class);
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, Visibility.ANY);
//        om.enableDefaultTyping(DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(om);

        JdkSerializationRedisSerializer jdkSerializationRedisSerializer = new JdkSerializationRedisSerializer();
        //创建Redis缓存操作助手RedisTemplate对象
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(getJedisConnectionFactory());
        //RedisTemplate对象需要指明Key序列化方式，如果声明StringRedisTemplate对象则不需要
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.setKeySerializer(stringSerializer);
        // Hash key序列化
        redisTemplate.setHashKeySerializer(stringSerializer);
        // Hash value序列化
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        //template.setEnableTransactionSupport(true);//是否启用事务
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }


    @Bean("redisDistributedLock")
    public DistributedLock redisDistributedLock(RedisTemplate<String, Object> redisTemplate){
        return new RedisDistributedLock(redisTemplate,REDIS_KEY_PREFIX);
    }

    @Bean
    public JedisPool redisPoolFactory() {
        String host = redisProperties.getHost();
        int port = redisProperties.getPort();
        String password = redisProperties.getPassword();
        int timeout = (int)redisProperties.getTimeout().toMillis();
        JedisPool jedisPool = new JedisPool(getJedisPoolConfig(), host, port, timeout, password);
        return jedisPool;
    }
}
