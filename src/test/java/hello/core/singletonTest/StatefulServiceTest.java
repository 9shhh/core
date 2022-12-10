package hello.core.singletonTest;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA: 사용자A가 10000원 주문
//        statefulService1.order("userA", 10000);
        int userAPrice = statefulService1.order("userA", 10000);
        //ThreadB: 사용자B가 20000원 주문
//        statefulService2.order("userA", 20000);
        int userBPrice = statefulService2.order("userB", 20000);

        //ThreadA: 사용자A 주문 금액 조회
//        int price = statefulService1.getPrice();
//        System.out.println("price = " + price);
        System.out.println("price = " + userAPrice);

//        assertThat(statefulService1.getPrice()).isEqualTo(20000);
        assertThat(userAPrice).isEqualTo(10000);
        assertThat(userBPrice).isEqualTo(20000);
    }

    static class TestConfig {
       @Bean
       public StatefulService statefulService() {
           return new StatefulService();
       }
    }
}