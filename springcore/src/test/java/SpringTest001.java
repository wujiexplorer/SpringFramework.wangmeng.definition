
import com.wangmeng02.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest001 {

    public static void main(String[] args){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("db.xml");
//        UserEntity userEntity001 = (UserEntity)applicationContext.getBean("userEntity001");
//        UserEntity userEntity002 = (UserEntity)applicationContext.getBean("userEntity001");
//        userEntity001.setAge(20);
//        userEntity001.setUseName("yushengjun");
//        System.out.println(userEntity001.toString());
  //      System.out.println(userEntity001 == userEntity002);
//        UserEntity userEntity = (UserEntity) applicationContext.getBean("userEntity002");
//        System.out.println(userEntity.toString());
//        UserService userService = (UserService) applicationContext.getBean("userService");
//        userService.add();
        UserService userService = (UserService) applicationContext.getBean("userService02");
        userService.add("yushengjun",20);//bean id 不能为一样

    }
}
