package nl.belastingdienst.voetbal_vereniging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.sql.init.SqlInitializationAutoConfiguration;

@SpringBootApplication
public class FootbalClubApplication {

    public static void main(String[] args) {
        SpringApplication.run(FootbalClubApplication.class, args);
    }

}
