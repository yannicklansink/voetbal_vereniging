package nl.belastingdienst.voetbal_vereniging.service;

import nl.belastingdienst.voetbal_vereniging.dto.*;
import nl.belastingdienst.voetbal_vereniging.exception.BadRequestException;
import nl.belastingdienst.voetbal_vereniging.exception.BadTeamNameException;
import nl.belastingdienst.voetbal_vereniging.exception.RecordNotFoundException;
import nl.belastingdienst.voetbal_vereniging.model.Injury;
import nl.belastingdienst.voetbal_vereniging.model.Player;
import nl.belastingdienst.voetbal_vereniging.model.PlayerData;
import nl.belastingdienst.voetbal_vereniging.model.Team;
import nl.belastingdienst.voetbal_vereniging.repository.InjuryRepository;
import nl.belastingdienst.voetbal_vereniging.repository.PlayerDataRepository;
import nl.belastingdienst.voetbal_vereniging.repository.PlayerRepository;
import nl.belastingdienst.voetbal_vereniging.util.DtoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    private PlayerRepository repository;

    private InjuryRepository injuryRepository;

    private PlayerDataRepository playerDataRepository;

    private InjuryService injuryService;

    private TeamService teamService;

    @Autowired
    public PlayerService(PlayerRepository repository, InjuryRepository injuryRepository, PlayerDataRepository playerDataRepository, InjuryService injuryService, TeamService teamService) {
        this.teamService = teamService;
        this.injuryService = injuryService;
        this.playerDataRepository = playerDataRepository;
        this.repository = repository;
        this.injuryRepository = injuryRepository;
    }

    public List<DtoEntity> getAllSpelers() {
        if (repository.count() != 0) {
            return repository.findAll()
                    .stream()
                    .map(this::convertSpelerToDto)
                    .collect(Collectors.toList());
        } else {
            throw new RecordNotFoundException("There are no players in the database");
        }
    }

    public Optional<PlayerDto> getPlayerDtoById(int id) {
        Optional<PlayerDto> newSpeler = Optional.empty();
        if (checkIfIdExists(id)) {
            Optional<Player> speler = repository.findById(id);
            newSpeler = convertSpelerToDto(speler);
        }
        return newSpeler;
    }

    public Optional<Player> getPlayerById(int id) {
        Optional<Player> newPlayer = Optional.empty();

        if (checkIfIdExists(id)) {
            newPlayer = repository.findById(id);

        }
        return newPlayer;
    }


    public Player addNewSpeler(PlayerDto playerDto) {
        String teamName = playerDto.getTeamName();
        playerDto.setTeamName(null);
        Player player = repository.save(convertDtoToPlayer(playerDto));
        checkIfPlayerDtoHasInjury(player);
        checkIfPlayerDtoHasPlayerData(playerDto, player);
        checkIfPlayerDtoHasTeam(player, teamName);
        return player;
    }

    public boolean updatePlayerById(PlayerDto playerDto, int id) {
        if (checkIfIdExists(id)) {
            String teamName = playerDto.getTeamName();
            playerDto.setTeamName(null);
            Player updatedPlayer = repository.save(convertDtoToExistingPlayer(playerDto, repository.findById(id).get()));

            if (playerDto.getInjury() != null) {
                for (InjuryDto injuryDto : playerDto.getInjury()) {
                    Injury newInjury = InjuryService.convertDtoToInjury(injuryDto);
//                injuryRepository.delete(newInjury.getId()); // heeft nog geen id
                    updatedPlayer.addInjury(newInjury);
                    checkIfPlayerDtoHasInjury(updatedPlayer);
                }
            }
            checkIfPlayerDtoHasPlayerData(playerDto, updatedPlayer);
            checkIfPlayerDtoHasTeam(updatedPlayer, teamName);
            return true;
        }
        return false;
    }

    public boolean deleteSpelerById(int id) {
        if (checkIfIdExists(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public void deletePlayersBasedOnSpeed(PlayerDataDto playerDataDto) {
        double topSpeed = 100.0;
        if (playerDataDto.getTopSpeed() > 20) {
            topSpeed = playerDataDto.getTopSpeed();
        } else {
            throw new BadRequestException("Top speed must be higher then 20");
        }
        repository.deletePlayerBySpeed(topSpeed);
    }

    public void checkIfPlayerDtoHasPlayerData(PlayerDto playerDto, Player player) {
        if (playerDto.getPlayerData() != null) {
            playerDataRepository.delete(player.getPlayerData().getId()); // delete the double inserted playerdata
            playerDataRepository.deleteByPlayer(player); // delete old playerdata


            PlayerData playerData = PlayerDataService.convertDtoToPlayerData(playerDto.getPlayerData());
            playerData.setPlayer(player);
            playerDataRepository.save(playerData);
        }
    }

    public void checkIfPlayerDtoHasInjury(Player player) {

        if (player.getInjury() != null) {
            for (Injury injury : player.getInjury()) {
                System.out.println("updating injury for player: ");
                System.out.println("player id: " + player.getPlayerId());
                injury.setPlayer(player);
                injuryService.updateInjury(injury);
            }
        }
    }

    private void checkIfPlayerDtoHasTeam(Player player, String teamName) {
        List<Team> teamList = teamService.doesTeamNameExists(teamName);
        if (teamList.size() > 0) {
            player.setTeam(teamList.get(0));
            repository.save(player);
        } else {
            throw new BadTeamNameException("Team name does not exists");
        }
    }

    /*
    Method will return a RecordNotFoundException when the Optional object is empty
     */
    public boolean checkIfIdExists(int id) {
        Optional<Player> newSpeler = repository.findById(id);
        if (newSpeler.isPresent()) {
            return true;
        } else {
            throw new RecordNotFoundException("Id is not in use");
        }
    }

    private DtoEntity convertSpelerToDto(Player player) {
        return new DtoUtils().convertToDto(player, new PlayerDto());
    }

    private Optional<PlayerDto> convertSpelerToDto(Optional<Player> player) {
        PlayerDto playerDto = (PlayerDto) new DtoUtils().convertToDto(player.get(), new PlayerDto());
        return Optional.of(playerDto);
    }

    private Player convertDtoToPlayer(PlayerDto playerDto) {
        return (Player) new DtoUtils().convertToEntity(new Player(), playerDto);
    }

    private Player convertDtoToExistingPlayer(PlayerDto playerDto, Player player) {
        Player newPlayer = convertDtoToPlayer(playerDto);
        newPlayer.emptyInjuries();
//        newPlayer.setPlayerData(null);
        newPlayer.setPlayerId(player.getPlayerId());
        return newPlayer;
    }

}
