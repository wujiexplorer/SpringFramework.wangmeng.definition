import com.wangmeng.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test004 {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring02.xml");
        UserService userService = (UserService)applicationContext.getBean("userService");
        System.out.println(userService);

    }
}
