package anup8bit.com.ecommerce.logger;

import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ActiveClassProfileLogger {
    ApplicationContext context;
    public ActiveClassProfileLogger(Environment env) {
        System.out.println(context);
        System.out.println("Active profiles : " + Arrays.toString(env.getActiveProfiles()));
    }
}
