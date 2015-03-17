package eu.acengineering.sample.querybyspec;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.dbunit.DataSourceDatabaseTester;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;

@Configuration
@ImportResource("classpath*:/META-INF/spring/root-context.xml")
@Profile("test")
public class ServiceTestConfig {
    
    @Resource(name = "dataSource")
    private DataSource dataSource;
    
    @Bean(name = "databaseTester")
    public DataSourceDatabaseTester dataSourceDatabaseTester() {
        DataSourceDatabaseTester databaseTester = new DataSourceDatabaseTester(dataSource);
        return databaseTester;
    }
    
}