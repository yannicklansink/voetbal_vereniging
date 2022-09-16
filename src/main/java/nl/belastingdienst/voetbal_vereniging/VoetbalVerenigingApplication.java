package nl.belastingdienst.voetbal_vereniging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class VoetbalVerenigingApplication {

    public static void main(String[] args) {
        SpringApplication.run(VoetbalVerenigingApplication.class, args);
    }

}
