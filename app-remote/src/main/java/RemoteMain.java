import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zhangqiang on 2016/7/21.
 */
public class RemoteMain {
    public static void main(String args[]){
        ApplicationContext ac = new ClassPathXmlApplicationContext("root-context.xml");
        //com.alibaba.dubbo.container.Main.main(null);
    }
}
