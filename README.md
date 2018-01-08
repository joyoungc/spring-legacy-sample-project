<img src="images/spring-framework.png" width="80" height="80"> spring-legacy-sample-project
==========================

# 1.소개

- 프로젝트를 위한 Spring 샘플 소스.
- 개발 환경 : Java 1.8, Spring Framework 4.3.12, Servlet 3.1, H2 Database 
- 적용된 기능 : Spring MVC, Spring Security, Spring Batch, JSP(JSTL), MyBatis, Bootstrap  

## 1.1 XML Config
- Spring Common Config
- Spring Batch (include TaskExecutor, Scheduler)
- Datasource
- TaskExecutor, Scheduler
- MessageSource
- Validator
- ViewResolver
- Interceptors

## 1.2 Java Config
- Spring Security
- MyBatis
- MultipartResolver (Apache Commons )
- ModelMapper
- CacheManager

## 1.3 Annotation Description
- @Slf4j : 해당 클래스에 Slf4j log 변수를 사용할 수 있도록 설정해 줌 (by lombok lib)
- @Data : Object에 선언된 변수에 대해 getter, setter, toString 등을 자동 생성 (by lombok lib)

# 2.개요


# 3.개발환경 설정

## 2.1 IDE 설치 (Eclipse or ETC)
- lombok plugin 설치 
   1. C:\dev-project\maven\local\repository\org\projectlombok\lombok\1.16.16 의 lombok-1.16.16.jar 실행(더블클릭)
![maven](images/002.png)
   2. ``Specify location`` 버튼 클릭 후 설치된 이클립스 location 설정
   3. ``Install / Update`` 버튼 클릭
   4. 설치 완료후 이클립스 재시작

## 2.2 Maven 설치 및 설정
   - <https://maven.apache.org/download.cgi> 에 접속하여 파일 다운로드
   - C:\dev-project\maven에 압축 해제
   - C:\dev-project\maven\conf\settings.xml에서 아래 설정 추가
   ```xml 
   ...
   <localRepository>C:\dev-project\maven\local\repository</localRepository> <!-- artifacts가 저장되는 위치 지정 -->
	...
   <offline>true</offline> <!-- 외부 네트워크(인터넷) 접속이 불가능한 환경에서 설정 -->
   ```
   - 이클립스 > Preferenct > Maven > User Settings
   ![maven](images/001.png)
   User Setting을 C:\dev-project\maven\conf\settings.xml로 설정
   
   - ojdbc6.jar
   mvn install:install-file -Dfile=jar위치 -DgroupId=com.oracle -DartifactId=ojdbc6 -Dversion=11.2.0 -Dpackaging=jar

## 2.3 SVN 설정



# 4.개발 표준




# 5.개발 가이드
