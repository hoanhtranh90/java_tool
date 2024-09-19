package org.me.dependencyinjectionspring;

import org.springframework.beans.factory.annotation.BeanFactoryAnnotationUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;


@Component
public class PrimaryBean {
    @Component
    public class EMailService implements PrimaryBean.NotificationService {
        public void send(String message) {
            System.out.println("I send " + message);
        }
    }
    @Component
    @Primary
    public class EMailService1 implements PrimaryBean.NotificationService {
        public void send(String message) {
            System.out.println("He send " + message);
        }
    }


    public interface NotificationService {
        void send(String message);
    }

    public static void main(String[] args)  throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PrimaryBean.class);
        var service = context.getBean(PrimaryBean.NotificationService.class);
        service.send("Hello world!");
    }
}
