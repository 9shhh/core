package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;


/**
 * @ComponentScan 은 @Component 가 붙은 클래스를 스캔해서 스프링 빈으로 등록함.
 * 추가로 @Configuration 은 컴포넌트 스캔 대상이 되는데 그 이유는, 해당 어노테이션도 @Component 어노테이션이 붙어 있기 때문.
 * (중요!)만약 basePackages, basePackageClasses 로 스캔 범의를 지정하지 않은 디폴트 상태면, @ComponentScan 를 붙인 클래스
 * => 즉, AutoAppConfig 의 패키지 부터 시작해서 하위 패키지 까지 다 스캔 대상이 됨.
 *
 * 권장하는 방법은 설정 정보 클래스의 위치를 프로젝트 최상단에 두는 것. 스프링 부트도 이 방법을 기본적으로 제공함.
 * 현재를 기준으로 하면 hello.core 가 프로젝트 시작 위치 이므로 여기에 설정 정보를 두고 @ComponentScan 어노테이션을 붙이고, basePackage 는 생략. -> 디폴트 설정 이용.
 * 추가로, 스프링 부트는 @SpringBootApplication 가 정의된 대표 시작 정보를 프로젝트 시작 루트에 위치하는 것이 관례고 해당 어노테이션 안에 @ComponentScan 이미 정의 되어 있음.
 * 따라서, 따로 @ComponentScan 을 정의하지 않아도 기본적으로 동작하게 됨.
 *
 * 어노테이션을 보면 다른 어노테이션을 포함하고 있는 경우가 있는데, 이 경우는 상속관계 개념이 아님.
 * 그리고 어노테이션이 특정 어노테이션을 들고 있는 것을 인식할 수 있는 것은 "자바 언어가 지원하는 기능이 아니고, 스프링이 지원하는 기능"임.
 */
@Configuration
@ComponentScan(
        // 기존 학습 및 테스트에서 활용한 @Configuration 붙은 클래스들을 모두 필터로 스캔 대상에서 제외. (필터 기능)
        // 기존 예제코드를 남기기 위해 해당 필터를 적용함.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class),
        basePackages = "hello.core.member", // 해당 패키지를 시작 위치로 지정하고, 포함하여 하위 패키지를 모두 탐색함.
        basePackageClasses = AutoAppConfig.class // 지정된 클래스의 패키지 위치를 기준으로 찾음. 현재는 AutoAppConfig.class 의 패키지 경로인 package hello.core 부터임.
)
public class AutoAppConfig {
}


