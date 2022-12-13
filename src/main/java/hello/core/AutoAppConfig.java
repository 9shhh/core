package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        // @ComponentScan 은 @Component 가 붙은 클래스를 스캔해서 스프링 빈으로 등록함.
        // 기존 학습 및 테스트에서 활용한 @Configuration 붙은 클래스들을 모두 필터로 스캔 대상에서 제외.
        // 추가로 @Configuration 은 컴포넌트 스캔 대상이 되는데 그 이유는, 해당 어노테이션도 @Component 어노테이션이 붙어 있음.
        // 기존 예제코드를 남기기 위해 해당 필터를 적용함.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
}
