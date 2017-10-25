# spring-legacy-web-jsp 샘플 프로젝트
- 프로젝트를 위한 Spring 샘플 소스. 
- 적용된 기능 : Spring MVC, Spring Security, Spring Batch, JSP(JSTL), MyBatis  

## XML Config
- Spring Common Config
- Spring Batch (include TaskExecutor, Scheduler)

## Java Config
- DataSource
- Spring Security
- MyBatis

## Annotation Description
- @Mapper : MyBatis에서 매핑할 Dao Class를 스캔해 주도록 지정.
- @Slf4j : 해당 클래스에 Slf4j log 변수를 사용할 수 있도록 설정해 줌. (by lombok lib)