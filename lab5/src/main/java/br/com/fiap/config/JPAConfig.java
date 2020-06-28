package br.com.fiap.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("br.com.fiap.repository")
@PropertySource("classpath:database.properties")
public class JPAConfig {

    private final Environment env;

    public JPAConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        return jpaVendorAdapter;
    }

    @Bean
    public DataSource dataSource(){
        return DataSourceBuilder.create()
                .driverClassName(env.getProperty("database.driverClassName"))
                .url(env.getProperty("database.url"))
                .username(env.getProperty("database.username"))
                .password(env.getProperty("database.password"))
                .build();
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactoryBean(JpaVendorAdapter jpaVendorAdapter, DataSource dataSource, Properties jpaProperties) {
        LocalContainerEntityManagerFactoryBean containerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        containerEntityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        containerEntityManagerFactoryBean.setDataSource(dataSource);
        containerEntityManagerFactoryBean.setPersistenceUnitName("appPersistenceUnit");
        containerEntityManagerFactoryBean.setPackagesToScan("br.com.fiap.entity");
        containerEntityManagerFactoryBean.setJpaProperties(jpaProperties);

        return containerEntityManagerFactoryBean;
    }

    @Bean(name = "jpaProperties")
    public Properties jpaProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.id.new_generator_mappings", env.getProperty("hibernate.id.new_generator_mappings"));

        return properties;
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager(entityManagerFactory.getObject());

        return jpaTransactionManager;
    }
}
