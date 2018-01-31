package io.github.joyoungc.web.common.configuration;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

@Configuration
// * annotationClass : Dao Class에 선언될 annotation 설정 ( @Mapper or @Repository ) 
@MapperScan(basePackages = "io.github.joyoungc", annotationClass = Repository.class) 
public class MybatisConfig {
	
	 /**
     * MyBatis의 {@link org.apache.ibatis.session.SqlSessionFactory}을 생성하는 팩토리빈을 등록한다.
     */
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory (
            DataSource dataSource, ApplicationContext applicationContext) throws IOException {

        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();

        // MyBatis가 사용할 DataSource를 등록
        factoryBean.setDataSource(dataSource);

        // io.github.joyoungc.web.admin.model 패키지 이하의 model 클래스 이름을 짧은 별칭으로 등록
        // ; 으로 구분지어 다른 패키지들도 등록가능
        factoryBean.setTypeAliasesPackage("io.github.joyoungc.web.user.model;io.github.joyoungc.web.admin.model");

        // sql/**/*-mapper.xml로 지정된 모든 XML을 Mapper로 등록
        factoryBean.setMapperLocations(applicationContext.getResources("classpath:sql/**/*-mapper.xml"));
        
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        
        // null 처리시 에러 방지 ex) Oracle - 부적합한 열 유형 : 1111
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        
        // SELECT 문에 있는 column을 CamelCase로 자동 매핑 ex) USER_NAME -> userName
        configuration.setMapUnderscoreToCamelCase(true);
        
        // 데이터가 null 값이더라도 setter를 호출한다. resultType을 Map으로 설정시 null 값이 제외되는 현상 방지)
        configuration.setCallSettersOnNulls(true);
        
        factoryBean.setConfiguration(configuration);

        return factoryBean;
    }

    /**
     * MyBatis {@link org.apache.ibatis.session.SqlSession} 빈을 등록한다.
     *
     * SqlSessionTemplate은 SqlSession을 구현하고 코드에서 SqlSession를 대체하는 역할을 한다.
     * Thread Safe하기 때문에 여러 Dao나 Mapper에서 공유 가능.
     */
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
