import com.wangmeng.proxy.UserServiceProxy;
import com.wangmeng.service.Impl.UserServiceImpl;
import com.wangmeng.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test001 {

    public static void main(String[] args){
//        UserService userService = new UserServiceImpl();
//        //userService.add();
//        UserServiceProxy userServiceProxy = new UserServiceProxy(userService);
//        userServiceProxy.add();
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        UserService userService = (UserService)applicationContext.getBean("userServiceImpl");
        userService.add();
    }
}
