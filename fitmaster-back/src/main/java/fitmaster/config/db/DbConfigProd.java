package fitmaster.config.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;

@Configuration
@Profile("prod")
public class DbConfigProd {
    
    private final Logger log = LoggerFactory.getLogger(DbConfigProd.class);
    
    @Bean
    public DataSource getProdDataSource() throws URISyntaxException {
        log.debug("Generating DataSource using DbConfigProd");
        var dbUri = new URI(System.getenv("DATABASE_URL"));

        var username = dbUri.getUserInfo().split(":")[0];
        var password = dbUri.getUserInfo().split(":")[1];
        var dbUrl =  "jdbc:postgresql://" + dbUri.getHost() 
                + ":" + dbUri.getPort() + dbUri.getPath()
                + "?" + dbUri.getQuery() 
                + "&user=" + username 
                + "&password=" + password;
        
        return DataSourceBuilder.create()
                .url(dbUrl)
                .username(username)
                .password(password)
                .build();
    }
}
