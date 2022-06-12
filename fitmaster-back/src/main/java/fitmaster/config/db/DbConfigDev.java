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

// TODO: Jeśli konfiguracja zależna od profilu zacznie się rozrastać lepiej
//  przejść na @ConditionalOnProperty

@Configuration
@Profile("dev")
public class DbConfigDev {

    private final Logger log = LoggerFactory.getLogger(DbConfigDev.class);
    
    @Bean
    public DataSource getDevDataSource() throws URISyntaxException {
        log.debug("Generating DataSource using DbConfigDev");
        
        var dbUri = new URI(System.getenv("DATABASE_URL"));
        var username = dbUri.getUserInfo().split(":")[0];
        var password = dbUri.getUserInfo().split(":")[1];
        var dbUrl =  "://" + dbUri.getHost() 
                + ":" + dbUri.getPort() + dbUri.getPath();
        
        if (dbUri.getScheme().equals("postgres"))
            dbUrl = "jdbc:postgresql" + dbUrl;
        else
            dbUrl = "jdbc:" + dbUri.getScheme() + dbUrl;
        
        if (dbUri.getQuery() != null)
            dbUrl += "?" + dbUri.getQuery();
        
        log.info("Connecting to db with URL: " + dbUrl);
        
        return DataSourceBuilder.create()
                .url(dbUrl)
                .username(username)
                .password(password)
                .build();
    }
}
