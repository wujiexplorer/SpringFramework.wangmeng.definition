package com.wangmeng.orm.mybatis.aop;

import com.wangmeng.orm.annotation.ExtInsert;
import com.wangmeng.orm.annotation.ExtSelect;
import com.wangmeng.orm.annotation.Extparam;
import com.wangmeng.orm.utils.JDBCUtils;
import com.wangmeng.utils.SQLUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class MyInvocationHandlerMybatis implements InvocationHandler {

    private Object object;

    public MyInvocationHandlerMybatis(Object object){
        this.object = object;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("methodName:"+method.getName());
        System.out.println("Object:"+object);
        //System.out.println("proxy:"+proxy);
        System.out.println("使用动态代理技术拦截接口方法开始############");

        ExtInsert extInsert = method.getDeclaredAnnotation(ExtInsert.class);
        if(extInsert != null){
            return extInsert(extInsert,proxy,method,args);
        }
        ExtSelect extSelect = method.getDeclaredAnnotation(ExtSelect.class);
        if(extSelect != null){
            String selectSQL = extSelect.value();
            ConcurrentHashMap<Object, Object> paramsMap = paramsMap(proxy, method, args);
            List<String> sqlSelectParameter = SQLUtils.sqlSelectParameter(selectSQL);
            List<String> sqlSelectResultParameter = SQLUtils.sqlSelectResultParameter(selectSQL);
            List<Object> sqlParams = new ArrayList<>();
            for(String  parameterName : sqlSelectParameter){
                Object parameterValue = paramsMap.get(parameterName);
                sqlParams.add(parameterValue);
            }
            String newSQL = SQLUtils.parameQuestion(selectSQL, sqlSelectParameter);
            System.out.println("newSql:"+newSQL+",sqlParams:"+sqlParams.toString());
            ResultSet res = JDBCUtils.query(newSQL, sqlParams);
            if(!res.next()){
                return null;
            }
            res.previous();
            Class<?> returnType = method.getReturnType();
            //List.class.isAssignableFrom(rtClass)
            //this.rtType = method.getGenericReturnType();泛型 Type rtType;
            //rtType instanceof ParameterizedType
            //if (rtType instanceof ParameterizedType) {
            //Type[] actualTypeArguments = ((ParameterizedType) rtType).getActualTypeArguments();
            //ry {
            //	Class<?> clazz = Class.forName(actualTypeArguments[0].getTypeName());
            //	2.1.1泛型为Java自身的类
            //	if (isJavaClass(clazz)) {
            //	return jdbcTemplate.queryForList(command.getName(), clazz, args);
            //					}
            //	// 2.1.2泛型为用户自定义类
            //	RowMapper<?> rm = BeanPropertyRowMapper.newInstance(clazz);
            //	return jdbcTemplate.query(command.getName(), rm, args);
            //ResultSet rs = this.executeQuery(sql, objs);
            //List<xxx> list = new Array<xxx>();
            //if(rs.next()){ 循环一个list的下标
            //xxx x = new xxx();
            //x.setxxx(rs.getString("xxx"));一个实体类的属性
            //x.setsss(rs.getString("sss"));
            //list.add(x);
            Object object = returnType.newInstance();
            while (res.next()){
                //System.out.println(res.getObject(1));
                for(String parameterName : sqlSelectResultParameter){
                    Object resultValue = res.getObject(parameterName);
                    if("user_name".equals(parameterName)){
                        parameterName = "userName";
                    }
                    Field field = returnType.getDeclaredField(parameterName);
                    // java.lang.NoSuchFieldException: userName
                    //没加declaredField就报找不到该属性
                    field.setAccessible(true);
                    field.set(object,resultValue);
                }
            }
            return object;
        }
        return null;
    }

    private Object extInsert(ExtInsert extInsert,Object proxy, Method method, Object[] args){
        ConcurrentHashMap<Object, Object> paramsMap = paramsMap(proxy, method, args);
        String insertSql = extInsert.value();
        System.out.println("insertSql:"+insertSql);
        String[] sqlInsertParameter = SQLUtils.sqlInsertParameter(insertSql);
        List<Object> sqlParams = sqlParams(sqlInsertParameter, paramsMap);
        String newSQL = SQLUtils.parameQuestion(insertSql, sqlInsertParameter);
        System.out.println("newSQL:"+newSQL);
        return JDBCUtils.insert(newSQL,false,sqlParams);
    }

    private ConcurrentHashMap<Object,Object> paramsMap(Object proxy, Method method, Object[] args){
        ConcurrentHashMap<Object, Object> paramsMap = new ConcurrentHashMap<>();
        Parameter[] parameters = method.getParameters();
        for(int i=0;i<parameters.length;i++){
            Parameter parameter = parameters[i];
            Extparam extparam = parameter.getDeclaredAnnotation(Extparam.class);
            if(extparam != null){
                String paramName = extparam.value();
                Object paramValue = args[i];
                System.out.println(paramName+","+paramValue);
                paramsMap.put(paramName,paramValue);
            }
        }
        return paramsMap;
    }

    private List<Object> sqlInsertParameter(String insertSql,ConcurrentHashMap<Object,Object> paramsMap){
        List<Object> sqlParams = new ArrayList<>();
        String[] sqlInsertParameter = SQLUtils.sqlInsertParameter(insertSql);
        for(String paramName : sqlInsertParameter){
            Object paramValue = paramsMap.get(paramName);
            sqlParams.add(paramValue);
        }
        return sqlParams;
    }

    private List<Object> sqlParams(String[] sqlInsertParameter, ConcurrentHashMap<Object, Object> paramsMap){
        List<Object> sqlParams = new ArrayList<>();
        for(String paramName : sqlInsertParameter){
            Object paramValue = paramsMap.get(paramName);
            sqlParams.add(paramValue);
        }
        return sqlParams;
    }

    public Object extInsertSQL(){
        return object;
    }
}
