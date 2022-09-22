package nl.belastingdienst.voetbal_vereniging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FootbalClubApplication {

    public static void main(String[] args) {
        SpringApplication.run(FootbalClubApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner run(PlayerRepository spelerRepo) throws Exception {
//        return (String[] args) -> {
//            Player player1 = new Player("yannick", "spoorstraat", 24, "7572CZ", 23, new Date(), Gender.MALE);
//            Player player2 = new Player("Bart", "spoorstraat", 24, "7572CZ", 23, new Date(), Gender.MALE);
//            spelerRepo.save(player1);
//            spelerRepo.save(player2);
//            spelerRepo.findAll().forEach(System.out::println);
//        };
//    }
}
