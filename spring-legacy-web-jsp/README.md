# spring-legacy-web-jsp 샘플 프로젝트
- 프로젝트를 위한 Spring 샘플 소스.
- 개발 환경 : Java 1.8, Spring Framework 4.3.12, Servlet 3.1, H2 Database 
- 적용된 기능 : Spring MVC, Spring Security, Spring Batch, JSP(JSTL), MyBatis, Bootstrap  

## XML Config
- Spring Common Config
- Spring Batch (include TaskExecutor, Scheduler)
- Datasource
- TaskExecutor, Scheduler
- MessageSource
- Validator
- viewResolver
- interceptors

## Java Config
- Spring Security
- MyBatis
- MultipartResolver (Apache Commons )
- ModelMapper
- CacheManager

## Annotation Description
- @Slf4j : 해당 클래스에 Slf4j log 변수를 사용할 수 있도록 설정해 줌 (by lombok lib)
- @Data : Object에 선언된 변수에 대해 getter, setter, toString 등을 자동 생성 (by lombok lib)