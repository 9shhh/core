package hello.core.scan.filter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ComponentFilterAppConfigTest {

    @Test
    void filterScan() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanA beanA = ac.getBean("beanA", BeanA.class);

        assertThat(beanA).isNotNull();
        assertThrows(
                NoSuchBeanDefinitionException.class,
                () -> ac.getBean("beanB", BeanB.class));
    }

    @Configuration
    @ComponentScan(
            includeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class), // 어노테이션과 관련된 필터를 만드는 것을 의미함.
            excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
    )
    static class ComponentFilterAppConfig {

    }
}

/**
 * FilterType 옵션 (5가지)
 * 1. ANNOTATION: 기본값, 어노테이션을 인식해서 동작함.
 * ex) 'org.example.SomeAnnotation'
 *
 * 2. ASSIGNABLE_TYPE: 지정한 타입과 자식 타입을 인식해서 동작함.
 * ex) 'org.example.SomeClass'
 *
 * 3. ASPECTJ: AspectJ 패턴 사용
 * ex) 'org.example..*Service+'
 *
 * 4. REGEX: 정규 표현식
 * ex) 'org\.example\.Default.*'
 *
 * 5. CUSTOM: 'TypeFilter' 라는 인터페이스를 구현해서 처리.
 * ex) 'org.example.MyTypeFilter'
 *
 * 간혹 excludeFilters 를 사용하는 경우가 있긴 히지만 거의 없음.
 * 옵션을 통해 변경하면서 사용하기 보다 스프링의 기본 설정에 최대한 맞춰 사용하는 것을 권장함.
 */