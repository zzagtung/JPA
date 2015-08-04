package jelly.jpatest.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.cfg.AvailableSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "jelly.jpatest.repository") // jpa repository 패키지
@ComponentScan(basePackages = "jelly.jpatest")
@EnableTransactionManagement
public class JpaConfiguration {
    
    // KSUG 2015 Modern Data Access for Enterprise Java - JPA
    // https://github.com/holyeye/ksug2015-morden-jpa
    
    @Bean
    public DataSource dataSource() {
        // h2 database
        // http://www.h2database.com/html/download.html
        // 실행 bin/ java -jar h2-*.jar
        // Ctrl + c 하면 db 내려감
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:tcp://localhost/~/test");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        // JpaTransaction 관리
        // MyBatis의 Transaction도 관리해줌.
        return new JpaTransactionManager();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setPackagesToScan("jelly.jpatest"); // entity scan package
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties jpaProperties = new Properties();
        jpaProperties.put(AvailableSettings.SHOW_SQL, true);
        jpaProperties.put(AvailableSettings.SHOW_SQL, true);
        jpaProperties.put(AvailableSettings.FORMAT_SQL, true);
        jpaProperties.put(AvailableSettings.USE_SQL_COMMENTS, true);
        jpaProperties.put(AvailableSettings.HBM2DDL_AUTO, "create"); // 기동 시 table drop and create
        jpaProperties.put(AvailableSettings.DIALECT, "org.hibernate.dialect.H2Dialect"); // h2에 맞는 sql이 실행됨
        jpaProperties.put(AvailableSettings.USE_NEW_ID_GENERATOR_MAPPINGS, "true");

        factoryBean.setJpaProperties(jpaProperties);
        return factoryBean;
    }
    
}
