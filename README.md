<img src="images/spring-framework.png" width="80" height="80"/> spring-legacy-sample-project
==========================

# Table of Contents

# [1. 소개](#1)
## &nbsp; [1.1. 목적](#1_1)

# [2. 개발 표준](#2)
## &nbsp; [2.1. Application 개발환경](#2_1)
## &nbsp; [2.2. 디렉토리 구조](#2_2)
## &nbsp; [2.3. Naming Convention](#2_3)
### &nbsp;&nbsp; [2.3.1. Naming 개요](#2_3_1)
### &nbsp;&nbsp; [2.3.2. Package Naming Rules](#2_3_2)
### &nbsp;&nbsp; [2.3.3. Class Naming Rules](#2_3_3)
### &nbsp;&nbsp; [2.3.4. Method Naming Rules](#2_3_4)
### &nbsp;&nbsp; [2.3.5. Constant Naming Rules](#2_3_5)
### &nbsp;&nbsp; [2.3.6. VO(or DTO) Naming Rules](#2_3_6)
## &nbsp; [2.4. Spring Framework](#2_4)
### &nbsp;&nbsp; [2.4.1. Configuration](#2_4_1)

## &nbsp; [2.x. Spring Batch](#)
## &nbsp; [2.x. Spring Security](#)

# [3. 개발환경 설정](#3)
## &nbsp; [3.1. IDE 설치](#3_1)
### &nbsp;&nbsp; [3.1.1. Plugin 설치](#3_1_1)
## &nbsp; [3.2. Maven 설치 및 설정](#3_2)
## &nbsp; [3.3. Tomcat 설정](#3_3)
## &nbsp; [3.4. 소스버전관리](#3_4)

# [4. 개발 가이드](#4)
## &nbsp; [4.1. 공통](#4_1)
### &nbsp;&nbsp; [4.1.1. Logging 처리](#4_1_1)
### &nbsp;&nbsp; [4.1.2. Properties](#4_1_2)
### &nbsp;&nbsp; [4.1.3. Message 처리](#4_1_3)
### &nbsp;&nbsp; [4.1.4. Validation](#4_1_4)
## &nbsp; [4.2. 신규 모듈 개발](#4_2)
### &nbsp;&nbsp; [4.2.1. View](#4_2_1)
### &nbsp;&nbsp; [4.2.2. Controller](#4_2_2)
### &nbsp;&nbsp; [4.2.3. Service](#4_2_3)
### &nbsp;&nbsp; [4.2.4. Dao](#4_2_4)
### &nbsp;&nbsp; [4.2.5. SQL Mapper XML](#4_2_5)
## &nbsp; [4.3. SQL가이드](#4_3)
### &nbsp; [4.3.1. Database Naming Rules](#4_3_1)
### &nbsp; [4.3.2. INDEX](#4_3_2)
## &nbsp; [4.4. 테스트](#4_4)
### &nbsp;&nbsp; [4.4.1. Assert](#)

<a name="1"/>

# 1.소개

<a name="1_1"/>

## 1.1. 목적
Spring 기반으로 진행되는 Web Application 프로젝트를 위한 샘플 소스입니다.

<a name="2"/>

# 2.개발 표준

<a name="2_1"/>

## 2.1. Application 개발환경

| 구분 |  제품명 | Version |  비고 |
| ----- | ----- | ----- | ----- |
| 개발언어 | Java | 1.8.0_102 |  |
| DBMS | H2 Database | 1.4.1 | 임시 |
| WAS | Tomcat | 8.0 | Servlet 3.1 |
| Framework | Spring Framework | 4.3.12 | Spring Batch, Spring Security 포함 |
| ORM | MyBatis | 3.4.5 |  |
| UI | Bootstrap, jQuery | 3.3.7, 3.2.1 |  |
| IDE | Eclipse | 4.7.2 (Oxygen) |  |

<a name="2_2"/>

## 2.2. 디렉토리 구조
Maven 프로젝트에서 정의된 Web Application을 위한 Standard Directory Layout 구조를 따릅니다. 

```
├── src/main/java
│   ├── {업무레벨1}.{업무레벨2}
│   │    ├── batch               * Spring Batch 관련 패키지
│   │    │   ├── tasklet            - tasklet 패키지
│   │    │   ├── scheduler          - scheduler 패키지 
│   │    │   ├── listener           - listener 패키지       
│   │    │   └── item               - ItemReader, Writer, Processor 패키지
│   │    ├── service             * Service 클래스 패키지
│   │    ├── dao                 * Dao 인터페이스 패키지
│   │    └── model               * Model 클래스 패키지 
│   │    
│   └── {업무레벨1}.common       * 공통 소스 패키지
│        ├── configuration          - Java Config 관련 패키지
│        ├── interceptor            - interceptor 패키지
│        ├── utils                  - 공통 유틸 패키지 
│        └── view                   - 공통 view 패키지
│
├── src/main/resources
│   ├── config
│   │    ├── batch
│   │    │    └── job            * Spring Batch Job xml 설정파일 폴더
│   │    ├── log                 * Logback 설정파일 폴더
│   │    └── spring              * Spring 설정파일 폴더
│   ├── message                  * 메시지 프로퍼티 파일폴더
│   └── sql                      * Mybatis 설정 파일 및 mapper.xml 폴더
│
├── src/main/webapp
│   ├── resources                * web static resource 폴더 (js,css,images 등)
│   │    ├── css                    - css 파일 폴더
│   │    ├── images                 - 이미지 파일 폴더
│   │    └── js                     - js 파일 폴더
│   └── WEB-INF
│        └── jsp                 * jsp view 파일 폴더
└── src/test/java
    └── {업무레벨1}.{업무레벨2}  * 테스트 클래스 패키지
```

<a name="2_3"/>

## 2.3. Naming Convention

<a name="2_3_1"/>

### 2.3.1. Naming 개요
- 모든 명칭은 의도가 명확하고 이해가 가능한 Full English 조합 방식을 사용합니다. 
- 잘 알려진 단어는 이니셜로 표기하지 말고 Full English 명사로 표시합니다. 
- 단어를 2개 이상 조합하는 경우 Camel 표기법을 따릅니다. `ex) userService`
- 단순히 값을 반환하는 것이 아닌 복잡한 처리가 일어나는 method라면 get 대신 의미있는 이름을 사용합니다. `ex) loadUserByUsername`

<a name="2_3_2"/>

### 2.3.2. Package Naming Rules
- package 명은 모두 소문자를 사용합니다.
- 모든 package는 {domain이름}.{업무레벨1}.{업무레벨2} 으로 시작합니다. 
`ex) joyoungc.github.io.web.user`

<a name="2_3_3"/>

### 2.3.3. Class Naming Rules
- Class 이름은 명사를 사용합니다. 
- 첫 글자는 대문자로 시작하며, 단어를 2개 이상 조합하는 경우 Camel 표기법을 따릅니다. `ex) UserService`
- 단어를 구분하기 위해서 밑줄(`_`)을 사용하지 않습니다. 

_ex) Layer별 Class 명명 규칙_

| Layer 계층 |  접미사 | 예제 |
| ----- | ----- | ----- |
| Controller | [명사] + Controller.java | UserController.java |
| Service | [명사] + Service.java | UserService.java |
| Value Object | [명사].java | User.java |
| Data Transfer Object | [명사] + DTO.java | UserDTO.java |
| SQL Mapper XML | [명사] + -mapper.xml | user-mapper.xml |
| Utility Class | [명사] + Utils.java | CommonUtils.java |


<a name="2_3_4"/>

### 2.3.4. Method Naming Rules
- method 이름은 동사를 사용합니다. 
- 동사만으로 의미 전달이 힘들경우 동사 + 명사로 사용합니다. 
- Annotation은 method 정의 바로 위에 위치합니다.
```java
   @GetMapping("/admins/{adminId}")
   @ResponseBody
   public Admin getAdmin(@PathVariable String adminId) {
      return adminService.getAdmin(adminId);
   }
```

_ex) Role별 Method 명명 규칙_

| Role |  prefix | subfix | example |
| ----- | ----- | ----- | ----- |
| 생성 | create |   | createUser() |
| 수정 | update |   | updateUser() |
| 삭제 | delete |   | deleteUser() |
| 단건조회 | get |   | getUser() |
| 목록조회 | select |   | selectUser() |
| 실행(배치,복합로직) | execute |   | executeBatch() |

<a name="2_3_5"/>

### 2.3.5. Constant Naming Rules
- 영어 대문자를 사용합니다. 
- 단어와 단어 사이에는 밑줄(`_`)로 연결합니다. 
```java
   public static final int MAX_REQUEST_COUNT = 5;
```

<a name="2_3_6"/>

### 2.3.6. VO(or DTO) Naming Rules
- DB Table과 1:1로 매핑되는 VO는 테이블명과 동일한 의미의 이름을 사용합니다.
- DTO 구현시 static nested class로 Create(생성요청), Update(수정요청), Read(조회요청), Response(응답)를 구현합니다.

| - | Table이름 |  VO명 | 생성요청 | 수정요청 | 조회요청 | 응답 |
| ----- | ----- | ----- | ----- | ----- | ----- | ----- |
| Naming | TB_ADM_USR | User.java  | UserDTO.Create  | UserDTO.Update | UserDTO.Read | UserDTO.Response |

```java
public class UserDTO {
   
   @Data
   public static class Create {
      private String userId;
      private String userName;
      private String password;
   }
   
   @Data 
   public static class Update {
      private String userName;
      private String password;
      private Integer enabled;
   }
   
   @Data
   public static class Read {
      private String userId;
      private String userName;
   }
   
   @Data
   public static class Response {
      private String userId;
      private String userName;
      private String password;
      private Integer enabled;
      private String createDatetime;
      private String updateDatetime;
   }

}

```
<a name="2_4"/>

## 2.4. Spring Framework

<a name="2_4_1"/>

### 2.4.1. Configuration
#### 1) XML Config
XML 파일로 구성된 Configuration 목록입니다. 

`파일위치 : {PROJECT_ROOT}/src/main/resources/config/spring`

- **application-context.xml**
   - Spring Common Config

- **application-servlet.xml**
   - MessageSource
   - Validator
   - ViewResolver
   - Interceptors

- **context-batch.xml**
   - Spring Batch
   - TaskExecutor, Scheduler
   
- **context-datasource.xml**
   - Datasource

- **context-transaction.xml**   
   - TransactionManager
   

#### 2) Java Config
Java Config(Annotation기반)으로 구성된 Configuration 목록입니다.
 
`파일위치 : {PROJECT_ROOT}/src/main/java/io/github/joyoungc/web/common/configuration`

- **SpringCommonConfig.java**
   - ObjectMapper
   - ModelMapper
   - RestTemplate
   - MultipartResolver (Apache Commons)   
   - CacheManager

- **SpringSecurityConfig.java**   
   - Spring Security
   
- **MybatisConfig.java**   
   - MyBatis


#### 3) Annotation Description
- @Slf4j : 해당 클래스에 Slf4j log 변수를 사용할 수 있도록 설정해 줍니다. (by lombok lib)
- @Data : Object에 선언된 변수에 대해 getter, setter, toString 등을 자동 생성합니다. (by lombok lib)
- @Transactional : Service Layer에서 사용되며 method 단위로 적용. 단순 조회(Read)에서는 사용하지 않습니다. Create, Update, Delete가 실행되는 method에 적용합니다.
- @Scheduled : cron 형식으로 설정된 시간에 해당 method가 실행됩니다. 
- @Cacheable : 해당 method를 Cache 처리 합니다. 최초 호출 이후 다음 실행건에 대해서는 캐싱된 데이터로 처리됩니다.
- @Async : 해당 method를 비동기로 처리합니다. 

<a name="3"/>

# 3.개발환경 설정

<a name="3_1"/>

## 3.1. IDE 설치 (Eclipse)

<a name="3_1_1"/>

### 3.1.1. Plugin 설치
#### 1) lombok plugin 설치
   1. C:\dev-project\maven\local\repository\org\projectlombok\lombok\1.16.16 의 lombok-1.16.16.jar 실행(더블클릭)
![maven](images/002.png)
   2. `Specify location` 버튼 클릭 후 설치된 이클립스 location 설정
   3. `Install / Update` 버튼 클릭
   4. 설치 완료후 이클립스 재시작

#### 2) SVN Connector 설치
   1. <http://www.eclipse.org/subversive/latest-releases.php> 에 접속하여 `Subversive-4.0.5.I20170425-1700.zip` 파일 다운로드  
   2. <http://community.polarion.com/projects/subversive/download/eclipse/6.0/builds/?C=M;O=A> 에 접속하여 `Subversive-connectors-allplatforms-6.0.4.I20161211-1700.zip` 파일 다운로드  
   3. 이클립스 > Help > Install New Software
   ![maven](images/004.png)
   4. `Add` 버튼 클릭 > Add Repository 창에서 `Archive` 클릭 > `Subversive-4.0.5.I20170425-1700.zip` 파일 첨부 
   ![maven](images/005.png)
   5. `Select All` 클릭 후 `Contact all update sites during install to find required software` 체크해제
   6. Next > Next > `I accept the terms of the license agreement` 선택 후 Finish
   7. 이클립스 재시작
   8. `Subversive-connectors-allplatforms-6.0.4.I20161211-1700.zip` 파일로 3 ~ 7번 과정을 반복 

<a name="3_2"/>

## 3.2. Maven 설치 및 설정
   1. <https://maven.apache.org/download.cgi> 에 접속하여 파일 다운로드
   2. C:\dev-project\maven에 압축 해제
   3. C:\dev-project\maven\conf\settings.xml파일을 편집하여 아래 설정 추가
   ```xml
   ...
   <localRepository>C:\dev-project\maven\local\repository</localRepository> <!-- artifacts가 저장되는 위치 지정 -->
	...
   <offline>true</offline> <!-- 외부 네트워크(인터넷) 접속이 불가능한 환경에서 설정 -->
   ```
   4. 이클립스 > Preference > Maven > User Settings
   ![maven](images/001.png)
   5. User Setting을 C:\dev-project\maven\conf\settings.xml로 설정

<a name="3_3"/>

## 3.3. Tomcat 설정
   1. <https://tomcat.apache.org/download-80.cgi> 에 접속하여 설치 파일 다운로드
   2. C:\dev-project\tomcat에 압축 해제
   3. 이클립스 > Preference > Server > Runtime Environment 
   ![tomcat](images/006.png)
   4. `Add` 클릭 > Apache Tomcat v8.0 선택 후 `Next`
   5. `Browse` 클릭 후 C:\dev-project\tomcat 지정 > `Finish` > `Apply and Close`
   6. Servers > Tomcat 서버 더블 클릭 > Overview > Open launch configuration 클릭
   ![tomcat](images/003.png)
   7. Arguments 탭 클릭 > VM arguments 에 `-Dspring.profiles.active=local` 추가

<a name="3_4"/>

## 3.4. 소스 버전 관리
※ 프로젝트 환경에 따라 설정 정보 입력

<a name="4"/>

# 4.개발 가이드

<a name="4_1"/>

## 4.1. 공통

<a name="4_1_1"/>

### 4.1.1. Logging 처리
- LogBack 라이브러리를 이용하여 debug 및 중요한 정보에 대해 Tracing 처리를 합니다.  
- 로그레벨을 조정하여 로그를 남길 수 있도록 지원합니다. 
> 주의) System.out.println() 사용을 최대한 피하도록 합니다. 

#### 1) 로그 Trace Level 설명
| Level |  역할 및 기능 | 개발자 사용여부 |
| ----- | ----- | ----- |
| error | 비즈니스 오류나 업무에서 발생되어서는 안되는 경우를 체크하기 위함 | X |
| debug | 개발 시 디버그를 위해 사용. <br>운영 시 디버그 로그가 남지 않는다. | O |
| warn  | 비즈니스 측면에서 충분히 발생할 수 있는 에러 상황에 대한 로그 기록시 사용.<br> 당장 조치할 성격이 아닌 경우 경고성으로 남긴다. | X |
| info  | 운영 시 정보 성격의 로그를 남길 때 사용한다. <br> ex) 사용자 최초 접속여부 등| O |

#### 2) 로그 설정
`파일위치 : {PROJECT_ROOT}/src/main/resources/config/log`
- 총 4개의 파일이 존재하며 logback.xml의 설정을 상속받아 각 서버 별로 logback-{서버profile}.xml 파일로 관리됩니다. (prod-운영, dev-개발, local-로컬)
- Java 옵션 `-Dspring.profiles.active=local` 설정으로 서버를 구분합니다. (Tomcat 설정시 Start Option으로 설정)  

#### 3) 로그 Trace 예제
```java
@Slf4j /* lombok에서 제공하는 annotation을 이용하여 logger를 생성한다. */
@Service
public class UserService {
   
    private final UserMapper userMapper;
   
   	public UserService(final UserMapper userMapper) {
		this.userMapper = userMapper;
	}
   
    public List<User> selectUser(User user) {

		// 잘못된 사용예 1 : 문자열 조합의 비용 발생
		log.debug("## params : " + user.getUserId());

		// 올바른 예 : 파라미터 formatting을 적용하여 사용
		log.debug("## params : {}", user.getUserId());

      return userMapper.selectUser(user);
    }

}

```

<a name="4_1_2"/>

### 4.1.2. Properties
`파일위치 : {PROJECT_ROOT}/src/main/resources/config`
- 총 3개의 파일이 존재하며 각 서버 별로 config-{서버profile}.properties 파일로 관리됩니다. (prod-운영, dev-개발, local-로컬)
- Java 옵션 `-Dspring.profiles.active=local` 설정으로 서버를 구분합니다. (Tomcat 설정시 Start Option으로 설정)

<a name="4_1_3"/>

### 4.1.3. Message 처리
- 해당 프로젝트는 다국어 지원을 위해 MessageSourceAccessor 를 사용합니다. 
- Locale은 xml config에서 설정합니다. 

#### 1) XML Config 설정 

`파일위치 : {PROJECT_ROOT}/src/main/resources/config/spring/application-servlet.xml`

```xml
   <!-- Message 관련 설정 -->
   <bean id="messageSourceAccessor" class="org.springframework.context.support.MessageSourceAccessor">
      <constructor-arg ref="messageSource" />
      <constructor-arg value="ko_KR" />   <!-- Locale 설정 -->
   </bean>
   
   <!-- 해당 프로퍼티 파일이 변경되었을 경우 애플리케이션을 다시 시작할 필요가 없이 
   		변경이 가능한 ReloadableResourceBundleMessageSource 적용 -->
   <bean id="messageSource" class="org.springframework.context.support.ReloadableResourceBundleMessageSource">
      <property name="basenames">
         <list>
            <value>classpath:/message/msg</value>
         </list>
      </property>
      <property name="defaultEncoding" value="UTF-8" />
      <property name="cacheSeconds" value="60" />
   </bean>
```

#### 2) 메세지 처리

`파일위치 : {PROJECT_ROOT}/src/main/resources/message`

- Server단 메세지 처리

```
msg_ko_KR.properties
required={0}) {1}은 필수항목입니다. 

msg_en_US.properties 
required={0}) {1} is a required field.
```

```java
   @Autowired
   private MessageSourceAccessor message;

   @Test
   public void messageSourceAccessorTest() {
      /* Case 1 */
      String success = message.getMessage("success"); // args : 메시지코드
      assertThat(success, is("성공입니다."));

      /* Case 2 */
      Object[] args = new String[] { "1", "userId" };
      String required = message.getMessage("required", args); // args : 메시지코드, 메시지 Arguments 바인딩
      assertThat(required, is( "1) userId은 필수항목입니다." ));
   }
```

- Client단 메세지 처리

```
msg_ko_KR.properties
login=로그인 

msg_en_US.properties 
login=Login
```
```html
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags"%>
<sp:message code="login" /> <!-- output : 로그인 -->
```

<a name="4_1_4"/>

### 4.1.4. Validation
- JSR 380 spec을 사용하여 annotation을 이용한 자동 검증 방식을 사용합니다.
- javax.validation.Valid annotation을 Controller에 적용하여 내부적으로 검증이 수행됩니다.   

#### 1) Validation DTO
다음은 JSR 380 에서 정의된 annotation이 적용된 DTO 입니다.
 
```java
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

public class UserDTO {
	
	@Data
	public static class Create {
		
		@NotBlank
		@Size(min = 4, max = 20)
		private String userName;
		
		@NotBlank
		private String password;
		
		@NotNull		
		@Range(min=0, max=1)
		private Integer enabled;
	}
...
```  
#### 2) Validation을 적용할 Controller Method
- 검증 방법은 Controller method의 파라미터에 @Valid 어노테이션을 추가합니다. 
- 바인드 과정에서 자동으로 검증이 되고, 바인딩 오류가 있을 시 
@ControllerAdvice에 설정된 ExceptionHandler에 Error내역이 Response 됩니다.

```java
@Slf4j
@ControllerAdvice
public class ControllerExceptionHanderAdvice {
	
	@ExceptionHandler(BindException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Map<String, String> handlerBindException(BindException e) {
		log.error("## BindException error : {}", e);
		String resultMsg = e.hasFieldErrors()
				? MessageFormat.format(e.getFieldErrors().get(0).getDefaultMessage(),
						e.getFieldErrors().get(0).getField())
				: e.getMessage();
		return Collections.singletonMap("resultMessage", resultMsg);		
	}

}
```

#### 3) Validation 메세지 설정
각 Annotation에 설정되어 있는 default 메세지들은 아래와 같이 message properties 파일에 overriding 해서 설정합니다.

```
msg_ko_KR.properties
javax.validation.constraints.NotBlank.message={0}은(는) 필수 항목입니다.  

msg_en_US.properties 
javax.validation.constraints.NotBlank.message={0} must not be blank
```


#### 4) JSR-380 (Bean Validation constraints) Annotations

| Annotation | Supported data types | Description |
| ---------- | ---------- | ---------- |
| @AssertTrue | Boolean, boolean | validates that the annotated property value is true |
| @Email | CharSequence | validates that the annotated property is a valid email address |
| @Future and @FutureOrPresent | java.util.Date, java.time.LocalDateTime 외 다수 | validates that a date value is in the future, or in the future including the present |
| @Max(value=) | BigDecimal, BigInteger, byte, short, int, long | validates that the annotated property has a value no larger than the value attribute |
| @Min(value=) | BigDecimal, BigInteger, byte, short, int, long | validates that the annotated property has a value no smaller than the value attribute |
| @NotBlank | CharSequence | can be applied only to text values and validated that the property is not null or whitespace |
| @NotEmpty | CharSequence, Collection, Map and arrays | validates that the property is not null or empty; can be applied to String, Collection, Map or Array values |
| @NotNull | Any type | validates that the annotated property value is not null |
| @Negative and @NegativeOrZero | BigDecimal, BigInteger, byte, short, int, long | apply to numeric values and validate that they are strictly negative, or negative including 0 |
| @Past and @PastOrPresent | java.util.Date, java.time.LocalDateTime 외 다수 | validate that a date value is in the past or the past including the present; can be applied to date types including those added in Java 8 |
| @Pattern(regex=, flags=) | CharSequence | Checks if the annotated string matches the regular expression regex considering the given flag match |
| @Positive and @PositiveOrZero | BigDecimal, BigInteger, byte, short, int, long | apply to numeric values and validate that they are strictly positive, or positive including 0 |
| @Range(min=, max=) | BigDecimal, BigInteger, byte, short, int, long | Checks whether the annotated value lies between (inclusive) the specified minimum and maximum |
| @Size | CharSequence, Collection, Map and arrays | validates that the annotated property value has a size between the attributes min and max; can be applied to String, Collection, Map, and array properties |

> 더 자세한 내용은 [validator-defineconstraints-spec](https://docs.jboss.org/hibernate/stable/validator/reference/en-US/html_single/#validator-defineconstraints-spec) 페이지를 참고하시기 바랍니다.

<a name="4_2"/>

## 4.2. 신규 모듈 개발

<a name="4_2_1"/>

### 4.2.1. View
- src/main/webapp/WEB-INF/jsp 폴더 및에 업무레벨별로 폴더 생성 후 jsp 파일을 생성합니다. 
- 페이지별 공통 css, js import를 위해 `<head>` 태그 사이에 ```java <jsp:include page="/WEB-INF/jsp/common/head.jsp" /> ``` 를 삽입합니다.
```html
<html lang="ko">
<head>
<jsp:include page="/WEB-INF/jsp/common/head.jsp" />
<title>사용자</title>
</head>
```
 
- Tag library 종류
```java
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 		/* JSTL Core */
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>	/* JSTL Formmater */
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags"%>		/* Spring */
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>	/* Spring Security */
```

<a name="4_2_2"/>

### 4.2.2. Controller

```java
@Controller  // 1)
@RequestMapping("/user")	// 2)
public class UserController {

	private final UserService userService;

	/* 생성자를 통한 Service DI */
	public UserController(UserService userService) {
		this.userService = userService;
	}

	/***
	 * 사용자 Page
	 * 
	 * @return
	 */
	@RequestMapping("/list")
	public String list() {
		return "/user/list";
	}

```
#### 1) Controller 스테레오 타입 설정
- @Controller 어노테이션 설정을 하여 Spring Bean에 등록되도록 합니다.

 
#### 2) RequestMapping 설정 
- 클래스 및 메소드 단위로 URL 매핑정보를 반영합니다. 

#### 3) Method 작성
```java
	/***
	 * 사용자 등록
	 * 
	 * @param dto
	 */
	@PostMapping("/rest/users")
	public void createUser(@RequestBody @Valid UserDTO.Create dto) {
		userService.createUser(dto);
	}
```
- GetMapping, PutMapping, PostMapping, DeleteMapping 을 사용하여 URL과 Http Method를 설정합니다.
- 파라미터에 @Valid 를 적용하여 Server Validation 을 활성화 합니다.


<a name="4_2_3"/>

### 4.2.3. Service

```java
@Slf4j
@Service   // 1)
public class UserService {
	
	private final UserMapper userMapper;

	private final ModelMapper modelMapper;

	/* 생성자를 통한  DI */
	public UserService(final UserMapper userMapper, final ModelMapper modelMapper) {
		this.userMapper = userMapper;
		this.modelMapper = modelMapper;
	}
	
	public List<User> selectUser(User user) {
		log.debug("## params : {}", user.getUserId());
		return userMapper.selectUser(user);
	}

	public User getUser(String userId) {
		return userMapper.getUser(userId);
	}

	@Transactional  // 2)
	public void updateUser(UserDTO.Update dto, String userId) {
		User user = modelMapper.map(dto, User.class);
		user.setUserId(userId);
		log.debug("## user : {}", user);
		userMapper.updateUser(user);
	}	
```

#### 1) Service 스테레오 타입 설정
- @Service 어노테이션 설정을 하여 Spring Bean에 등록되도록 합니다.

#### 2) Transaction 설정
- Transaction은 Service Layer에서 설정하며 @Transactional 어노테이션을 이용해 활성화 합니다. 
- 조회를 제외한 등록, 수정, 삭제 메소드에 설정합니다.

<a name="4_2_4"/>

### 4.2.4. Mapper
```java
@Mapper
public interface UserMapper {
	
	List<User> selectUser(User user);

	User getUser(String userId);
	
	void updateUser(User user);
```
#### 1) Mapper 스테레오 타입 설정
- Mapper는 interface로 생성합니다.
- @Mapper 어노테이션 설정을 하여 Spring Bean에 등록되도록 합니다.


#### 2) Method 작성
- 각각의 메소드명은 MyBatis SQL Mapper에 있는 id와 일치하도록 합니다.
```java
	List<User> selectUser(User user);
```
```xml
	<select id="selectUser" resultType="User">
```

<a name="4_2_5"/>

### 4.2.5. SQL Mapper XML

#### 1) SQL mapper 파일 작성 위치
- [PROJECT_ROOT/src/main/resources/sql/{업무레벨2}/{명사}-mapper.xml

#### 2) SQL mapper 표준 작성 방법
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" 
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="io.github.joyoungc.web.user.mapper.UserMapper">  <!-- (1) 네임스페이스 -->

	<!-- 사용자 조회 -->
	<select id="selectUser" parameterType="User" resultType="User"> <!-- (2) parameterType, resultType -->
		SELECT /*[user/user-mapper.xml] selectUser */
			USER_ID,
			USER_NAME,
			PASSWORD,
			ENABLED
		  FROM TB_ADM_USR
		<if test="@org.apache.commons.lang3.StringUtils@isNotBlank(userId)">
		 WHERE USER_ID = #{userId}
		</if>
	</select>

</mapper>
```
- SQL Mapper 작성시 네임스페이스는 Dao interface 로 설정합니다. 
- Mapper에서 parameterType 과 resultType은 java.util.Map 사용을 지양하고, Value Object를 기반으로 설정하도록 합니다.
- Mapper XML은 Well-formed 문서로 작성되어야 합니다. "<", ">" 와 같은 특수문자는 `&lt`, `&rt`로 치환해서 사용하거나,
쿼리 내용을 그대로 표현하기 위해 <![CDATA[ ]]> 를 사용합니다. 

#### 3) 주의 사항
- Java 소스 내에 SQL문 작성을 하지 않습니다. 
- Procedure 사용을 지양합니다. 
- 되도록 Ansi SQL 문법으로 작성합니다.
- MyBatis 파라미터 바인딩 시 문자열 대체는 사용을 금지합니다. ex) ${param}

<a name="4_3"/>

## 4.3. SQL가이드

<a name="4_3_1"/>

### 4.3.1 Database Naming Rules

#### 1) Table 이름
- 테이블임을 표시하기 위해 테이블 명 앞에 'TB_' 구분을 사용합니다.
- 테이블명은 영문 대문자로 사용합니다.
- 단어와 단어 사이는 '_' 로 구분합니다.
- 각 단어는 최대 4자리까지 사용합니다.
- 접미사는 Table의 특성을 나타냅니다.

_ex) 자주 사용되는 접미사 목록_

| Alias | Definition | Description |
|-----|-----|-----|
| MST | Master | 기준정보 테이블 |
| DTL | Detail | 상세정보 테이블 |
| HST | History | 이력 테이블 |
| STC | Statistics | 통계 관련 테이블 |

- 표기 방식
TB + '\_' + <업무분류> + '\_' + <의미있는 테이블명> + '\_' + 접미사(Option) 
`ex) 스피링배치 JOB 실행 테이블 : TB_BATCH_JOB_EXECUTION , 공통 파일 이력 테이블 : TB_COMN_FILE_HST`

#### 2) Column 이름
- 컬럼명은 영문 대문자로 사용합니다.
- Column에 대한 자리수는 총 12자리로 하며, 제한은 없습니다. 단, 사용하는 Database의 특성에 따라 제한될 수 있습니다.
   - 참고로 Oracle 12.1 과 그 아래 버전은 object name 길이가 최대 30 bytes입니다.
   - MySQL 5.5 이상 버전은 최대 64 Bytes 입니다.
- 단어와 단어 사이는 '_' 로 구분합니다.
- 각 단어는 최대 8자리까지 사용합니다.
- 모든 Column은 Dictionary List에 등록된 약어사전 및 자료사전을 기초로 작성합니다.
- Dictionary List에 등록되지 않은 약어는 DBA의 동의 하에 등록합니다. 
- Column Name은 약어의 조합으로 구성합니다.
- 컬럼명에 컬럼을 대표하는 접미사를 사용하여 컬럼명의 성격을 나타냄.

_ex) 자주 사용되는 접미사 목록_

| Alias | Definition | Description | Example |
|-----|-----|-----|-----|
| CD | Code | 공통 코드 테이블의 코드, 각종 코드에 사용 | ex) 카테고리 코드 CATE_CD, 사용자 그룹 코드 USR_GRP_CD 등 |
| NM | Name | 코드에 대한 명칭에 주로 사용 | ex) 코드명 CD_NM, 사용자이름 USR_NM, 메뉴명 MENU_NM |
| NO | Number | 숫자로만 이루어진 경우에 사용 | ex) 사원 번호 EMP_NO |
| SEQ | Sequence | 오라클의 Sequence, MySQL의 auto_increment 처럼 숫자 일련번호로 PK를 설정하는 경우 사용 | ex) 이력번호 HST_SEQ |
| ID | Identifier | 주로 사용자 아이디의 경우에 사용 | ex) 사용자 아이디 USR_ID, 등록자 아이디 REG_ID |
| DT | DateTime | 날짜의 경우 사용. DT는 날짜 타입이 DATETIME 이나 TIMESTAMP 형인 경우에만 사용한다. 보통 날짜의 경우 CHAR(8)형으로 20050718식으로 저장을 많이 한다. 이런 경우에는 YMD를 사용한다. | ex) 수정일자 UPT_DT |
| YMD | YYYYMMDD | 날짜의 경우 사용한다. 날짜 타입이 CHAR 인경우 사용한다. 년월일인 경우 _YMD를 사용하고, 년월형식으로 CHAR(6)로 저장될 경우 _YM을 사용한다. 년도, 월, 일자 인경우에는 YEAR, MONTH, DAY등의 컬럼명을 사용한다. |
| TYP | Type | 구분값을 나타낼때 사용한다.  | ex) 통계구분 STAT_TYP |
| STS | Status | 상태값이다. 주로 CHAR(1) 형식을 사용한다. | 사용자 상태 USER_STS |
| YN | Yes or No | CHAR(1) 형식을 사용하여 0/1 이나 Y/N 로 표시한다. | ex) 사용여부 USE_YN |
| ORD | Order | 순서를 나타낼 때 사용한다. | ex) 컬럼순서 COL_ORD |
| CNT | Count | 카운트 | ex) 조회수 VIEW_CNT |
| AMT | Amount | 양  | ex) 재고량 STOCK_AMT |
| SUM | Sum | 합계 | ex) 년도합계 YEAR_SUM |

- 표기방식
<의미있는 컬럼명> + '_' + 접미사
`ex) 사용자 아이디 : USR_ID`


<a name="4_3_2"/>

### 4.3.2 INDEX

#### 1) 원리 및 목적
- 해당 테이블의 컬럼을 색인화(따로 파일로 저장)하여 검색시 해당 테이블의 레코드를 FULL SCAN 하는게 아니라 색인화 되어있는 INDEX 파일을 검색하여 검색속도를 빠르게 합니다.
- 테이블의 기본 키는 자동으로 인덱스 됩니다.
- 키 값을 기초로 하여 테이블에서 검색과 정렬 속도를 향상시킵니다.
- 질의나 보고서에서 그룹화 작업의 속도를 향상시킵니다.

#### 2) 인덱스 생성 대상과 유의사항
- 인덱스는 컬럼 단위로 생성합니다.
- WHERE절에서 사용되는 컬럼을 인덱스 대상으로 합니다.
- JOIN에 자주 사용되는 컬럼에도 인덱스를 생성하는 것을 권장합니다.
- 외래키가 사용되는 컬럼에는 인덱스를 되도록 생성하는 것을 권장합니다.
- 데이터의 중복도가 높은 컬럼은 인덱스를 생성해도 효용이 없습니다. (예 : 성별 등 타입이 별로 없는 경우, 표본 데이터가 적은 경우)
- INSERT / UPDATE / DELETE가 얼마나 자주 일어나는지를 고려해야 합니다.
- 사용하지 않는 인덱스는 제거하도록 합니다.
- 필드 중에는 데이터 형식 때문에 인덱스 될 수 없는 필드도 있습니다.

#### 3) INDEX가 타지 않는 경우

- **인덱스 컬럼의 변형**

_ex) 인덱스 컬럼 변형 SQL과 회피방법 (SAL, HIREDATE 에 INDEX 설정)_
```sql
SELECT ENAME FROM EMP
 WHERE SAL * 3.1 > 950; /* 인덱스 사용 불가 */
 
SELECT ENAME FROM EMP
 WHERE SAL > 950 / 3.1; /* 인덱스 사용 가능 */
 
SELECT ENAME FROM EMP
 WHERE TO_CHAR(HIREDATE, 'YYYYMMDD') = '20170101'; /* 인덱스 사용 불가 */
 
SELECT ENAME FROM EMP
 WHERE HIREDATE = TO_DATE('20170101', 'YYYYMMDD');  /* 인덱스 사용 가능 */
```
_ex) 함수 기반 인덱스 생성과 인덱스 적용 Sql_
```sql 
CREATE INDEX ENAME_FIDX ON EMP SUBSTR(ENAME,1,1); /* 함수 기반 인덱스 생성 */

SELECT ENAME FROM EMP
 WHERE SUBSTR(ENAME,1,1) = 'J'; /* 인덱스 사용 가능 */  
```

- **데이터 변환**

_ex) 데이터 변환 SQL_
```sql
SELECT ENAME FROM EMP
 WHERE HIREDATE = '2017-01-01';  /* DB 종류 및 버전, 상황에 따라 인덱스 사용 (불)가능 */

SELECT ENAME FROM EMP
 WHERE HIREDATE = TO_DATE('2017-01-01', 'YYYY-MM-DD');  /* 인덱스 사용 가능 */
 
SELECT ENAME FROM EMP
 WHERE EMPNO = 7913;  /* EMPNO가 CHAR형인 경우 인덱스 사용 불가*/
 
SELECT ENAME FROM EMP
 WHERE EMPNO = TO_CHAR(7913);  /* 인덱스 사용 가능 */ 
```

- **NULL 조건 사용**

_ex) NULL을 사용하는 SQL_
```sql
SELECT ENAME FROM EMP
 WHERE DEPT IS NULL; /* 인덱스에 NULL은 포함되지 않음. 인덱스 사용 불가 */

SELECT ENAME FROM EMP
 WHERE DEPT IS NOT NULL;   /* DB 종류 및 버전, 상황에 따라 인덱스 사용 (불)가능 */
 
SELECT ENAME FROM EMP
 WHERE DEPT > ''; /* 인덱스 사용 가능 */ 
 
SELECT ENAME FROM EMP
 WHERE DEPTNO >= 0; /* 인덱스 사용 가능 */ 
```

- **부정형 조건**

_ex) 부정형을 사용하는 SQL과 회피 방법_
```sql
SELECT ENAME FROM EMP
 WHERE DEPTNO != 130; /* 인덱스 사용 불가 */

SELECT ENAME FROM EMP
 WHERE DEPTNO < 130
    OR DEPTNO > 130; /* 인덱스 사용가능 */

SELECT ENAME FROM EMP
 WHERE NOT EXISTS
 	(SELECT '' FROM EMP WHERE DEPTNO = 130); /* 인덱스 사용 가능 */ 
 	
SELECT ENAME FROM EMP
 MINUS SELECT '' FROM EMP WHERE DEPTNO = 130; /* 인덱스 사용 가능(정렬 발생) */

```

- **LIKE 연산자**

_ex) LIKE 연산자 SQL_
```sql
SELECT ENAME FROM EMP
 WHERE ENAME LIKE 'S%'; /* 인덱스 사용 가능 */

SELECT ENAME FROM EMP
 WHERE ENAME LIKE '%S%'; /* 인덱스 사용 불가 */
```


<a name="4_4"/>

## 4.4. 테스트

### 4.4.1. Assert

_ex) 향상된 assertThat을 이용해 기존 assert method 대체_ ([The Benefits of Using assertThat over other Assert Methods in Unit Tests](https://objectpartners.com/2013/09/18/the-benefits-of-using-assertthat-over-other-assert-methods-in-unit-tests/))

| Old Assert Method | Equivalent with AssertThat | Static imports | Notes |
|-----|-----|-----|-----|
|assertEquals("expected", "actual");|assertThat("actual", is("expected"));|org.hamcrest.core.Is.is|"is" is short hand for is(equalTo())|
|assertArrayEquals(new String[] {"test1", "test2"}, new String[] {"test3", "test4"});|assertThat(new String[] {"test3", "test4"}, is(new String[] {"test1", "test2"}));|org.hamcrest.core.Is.is|The error message looks like this: java.lang.AssertionError:<br>Expected: is ["test1", "test2"]<br>got: ["test3", "test4"]|
|assertTrue(value); or <br>assertFalse(value);|assertThat(actual, is(true));<br>assertThat(actual, is(false));|org.hamcrest.core.Is.is|The error message looks like this (depending on the values): java.lang.AssertionError:<br>Expected: is true<br>got: false|
|assertNull(value); or <br>assertNotNull(value);|assertThat(actual, nullValue());<br>assertThat(actual, notNullValue());|org.hamcrest.core.IsNull.notNullValue<br>org.hamcrest.core.IsNull.nullValue;|The error message looks like this (depending on the values):<br>java.lang.AssertionError:<br>Expected: not null<br>got: null<br>Also both matchers can be typed as such:<br>assertThat(actual, nullValue(String.class)); <br>which means the actual argument must be a string.|
|assertSame(expected, actual); or <br>assertNotSame(expected, actual);|assertThat(actual, sameInstance(expected));<br>assertThat(actual, not(sameInstance(expected)));|org.hamcrest.core.IsNot.not<br>org.hamcrest.core.IsSame.sameInstance|The error message looks like this (depending on the values): <br>java.lang.AssertionError:<br>Expected: sameInstance("test")<br>got: "test"<br>using: String actual = new String("test");<br>String expected = new String("test");|
|assertTrue(1 > 3);|assertThat(1, greaterThan(3));|org.hamcrest.number.OrderingComparison.greaterThan|The error message is similar to the pattern above rather than "java.lang.AssertionError" OrderingComparison also contains: "comparesEqualTo", "greaterThanOrEqualTo", "lessThan" and "lessThanOrEqualTo"|
|assertTrue("abc".contains("d"));|assertThat("abc", containsString("d"));|oorg.hamcrest.core.StringContains.containsString|The error message is similar to the pattern above.|
|assertTrue("abc".contains("d"));|assertThat("abc", containsString("d"));|oorg.hamcrest.core.StringContains.containsString|The error message is similar to the pattern above. See also in the same package: StringStartsWith, StringEndsWith|
