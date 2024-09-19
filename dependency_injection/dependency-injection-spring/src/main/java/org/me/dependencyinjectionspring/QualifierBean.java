package org.me.dependencyinjectionspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.BeanFactoryAnnotationUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
public class QualifierBean {
    @Component("eMailService")
    public class EMailService implements QualifierBean.NotificationService {
        public void send(String message) {
            System.out.println("I send " + message);
        }
    }
    @Component("eMailService1")
    public class EMailService1 implements QualifierBean.NotificationService {
        public void send(String message) {
            System.out.println("He send " + message);
        }
    }

    @Component("eMailService2")
    public class EMailService2 implements QualifierBean.NotificationService {
        public void send(String message) {
            System.out.println("She send " + message);
        }
    }

    public interface NotificationService {
        void send(String message);
    }

    public static void main(String[] args)  throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(QualifierBean.class);

        /*
         * use BeanFactoryAnnotationUtils
         */
        var service = BeanFactoryAnnotationUtils.qualifiedBeanOfType(context.getBeanFactory(), QualifierBean.NotificationService.class, "eMailService1");
        service.send("Hello world!");

        /*
         * use specific bean name
         */
        service = context.getBean("eMailService2", QualifierBean.NotificationService.class);
        service.send("Hello world!");
    }
}
