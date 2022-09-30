package nl.belastingdienst.voetbal_vereniging.load;

import nl.belastingdienst.voetbal_vereniging.model.Player;
import nl.belastingdienst.voetbal_vereniging.model.Team;
import nl.belastingdienst.voetbal_vereniging.model.Trainer;
import nl.belastingdienst.voetbal_vereniging.model.Training;
import nl.belastingdienst.voetbal_vereniging.repository.*;
import nl.belastingdienst.voetbal_vereniging.util.Gender;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

//@Component
public class DataLoader {

    private GameRepository gameRepository;
    private InjuryRepository injuryRepository;
    private PlayerRepository playerRepository;
    private RefereeRepository refereeRepository;
    private TeamRepository teamRepository;
    private TrainerRepository trainerRepository;
    private TrainingRepository trainingRepository;

    private List<String> nameListPlayers = Arrays.asList( "Yannick", "Stan", "Erik", "Peter", "Sjoerd", "Noah", "Sem", "Liam", "Lucas", "Daan", "Finn", "Levi", "Luuk", "Mees", "James","Milan","Sam","Noud","Benjamin","Luca","Bram","Mason","Max","Thomas","Adam","Hugo","Jesse","Boaz","Olivier","Teun","Julian","Lars","Thijs","Gijs","Siem","Guus","Mats","Zayn", "Otis","Jens", "Jack","Floris","Ties","Vince", "Joep", "David", "Jan", "Stijn", "Sven", "Dex", "Jurre", "Morris", "Ruben" );
    private List<String> nameListTrainers = Arrays.asList("Jorgen", "Michael", "Rutger", "Wouter", "Rene", "Harry", "Peter", "Vincent");
    private Random rand;

    public DataLoader(GameRepository gameRepository, InjuryRepository injuryRepository, PlayerRepository playerRepository, RefereeRepository refereeRepository, TeamRepository teamRepository, TrainerRepository trainerRepository, TrainingRepository trainingRepository) {
        this.gameRepository = gameRepository;
        this.injuryRepository = injuryRepository;
        this.playerRepository = playerRepository;
        this.refereeRepository = refereeRepository;
        this.teamRepository = teamRepository;
        this.trainerRepository = trainerRepository;
        this.trainingRepository = trainingRepository;
        rand = new Random();
        loadTeams();
        loadPlayers();
        loadTrainers();
        loadTraining();
        loadPlayerHasTraining();
        loadReferees();
        loadInjuries();
        loadGames();
    }

    private void loadTeams() {
        Team team1 = new Team("De Toppers");
        Team team2 = new Team("Fc Twente");
        Team team3 = new Team("Barcelona");
        Team team4 = new Team("Manchester United");
        List<Team> teamList = Arrays.asList(team1, team2, team3, team4);
        teamRepository.saveAll(teamList);
    }

    private void loadPlayers() {
        for (int j = 1; j < 5; j++) {
            for (int i = 1; i < 12; i++) {
                int randomNum = rand.nextInt((nameListPlayers.size() - 0)) + 0;
                playerRepository.save(new Player(nameListPlayers.get(randomNum), Gender.MALE, teamRepository.getById(j)));
            }
        }
    }

    private void loadTrainers() {
        for (int j = 1; j < 5; j++) {
            int randomNum = rand.nextInt((nameListTrainers.size() - 0)) + 0;
            trainerRepository.save(new Trainer(nameListTrainers.get(randomNum), teamRepository.getById(j)));
        }
    }

    private void loadTraining() {
        trainingRepository.save(new Training(LocalDate.of(2022, 12, 10), trainerRepository.getById(1)));
        trainingRepository.save(new Training(LocalDate.of(2022, 12, 12), trainerRepository.getById(2)));
        trainingRepository.save(new Training(LocalDate.of(2022, 12, 14), trainerRepository.getById(3)));
        trainingRepository.save(new Training(LocalDate.of(2022, 12, 16), trainerRepository.getById(4)));
        trainingRepository.save(new Training(LocalDate.of(2022, 12, 18), trainerRepository.getById(1)));
    }

    private void loadPlayerHasTraining() {

    }

    private void loadReferees() {

    }

    private void loadInjuries() {

    }

    private void loadGames() {

    }



}
