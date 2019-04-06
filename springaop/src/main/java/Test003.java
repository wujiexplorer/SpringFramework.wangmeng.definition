import com.wangmeng.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test003 {

    public static void main(String[] args){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring02.xml");
        System.out.println("########################################");
        UserService userService = (UserService) applicationContext.getBean("userService");
        System.out.println(userService);
    }
}
