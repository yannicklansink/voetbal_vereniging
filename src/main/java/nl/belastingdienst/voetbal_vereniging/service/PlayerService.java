package nl.belastingdienst.voetbal_vereniging.service;

import nl.belastingdienst.voetbal_vereniging.controller.InjuryController;
import nl.belastingdienst.voetbal_vereniging.dto.DtoEntity;
import nl.belastingdienst.voetbal_vereniging.dto.InjuryDto;
import nl.belastingdienst.voetbal_vereniging.dto.PlayerDataDto;
import nl.belastingdienst.voetbal_vereniging.dto.PlayerDto;
import nl.belastingdienst.voetbal_vereniging.exception.RecordNotFoundException;
import nl.belastingdienst.voetbal_vereniging.model.Injury;
import nl.belastingdienst.voetbal_vereniging.model.Player;
import nl.belastingdienst.voetbal_vereniging.model.PlayerData;
import nl.belastingdienst.voetbal_vereniging.repository.InjuryRepository;
import nl.belastingdienst.voetbal_vereniging.repository.PlayerDataRepository;
import nl.belastingdienst.voetbal_vereniging.repository.PlayerRepository;
import nl.belastingdienst.voetbal_vereniging.util.DtoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    private PlayerRepository repository;

    private InjuryRepository injuryRepository;

    private PlayerDataRepository playerDataRepository;

    private InjuryService injuryService;

    private PlayerDataService playerDataService;

    @Autowired
    public PlayerService(PlayerRepository repository, InjuryRepository injuryRepository, PlayerDataRepository playerDataRepository, InjuryService injuryService, PlayerDataService playerDataService) {
        this.playerDataService = playerDataService;
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

    public Optional<PlayerDto> getSpelerById(int id) {
        Optional<PlayerDto> newSpeler = Optional.empty();
        if (checkIfIdExists(id)) {
            Optional<Player> speler = repository.findById(id);
            newSpeler = convertSpelerToDto(speler);
        }
        return newSpeler;
    }

    public Player addNewSpeler(PlayerDto playerDto) {
        Player player = repository.save(convertDtoToPlayer(playerDto));
//        for (Injury injury : player.getInjury()) {
//            injury.setPlayer(player);
//            injuryService.updateInjury(injury);
//        }
        checkIfPlayerDtoHasInjury(playerDto, player);
        checkIfPlayerDtoHasPlayerData(playerDto, player);
        return player;
    }

    public boolean updatePlayerById(PlayerDto playerDto, int id) {
        if (checkIfIdExists(id)) {
            Player updatedPlayer = repository.save(convertDtoToExistingPlayer(playerDto, repository.findById(id).get()));
            // updatedPlayer has no injuries

            for (InjuryDto injuryDto : playerDto.getInjury()) {
                Injury newInjury = InjuryService.convertDtoToInjury(injuryDto);
                injuryRepository.delete(newInjury.getId());
                updatedPlayer.addInjury(newInjury);
                checkIfPlayerDtoHasInjury(playerDto, updatedPlayer);
            }

//            checkIfPlayerDtoHasPlayerData(playerDto, updatedPlayer);
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

    public void checkIfPlayerDtoHasPlayerData(PlayerDto playerDto, Player player) {
        if (playerDto.getPlayerData() != null) {
            playerDataRepository.delete(player.getPlayerData().getId()); // delete old playerdata

            PlayerData playerData = PlayerDataService.convertDtoToPlayerData(playerDto.getPlayerData());
            playerData.setPlayer(player);
            playerDataRepository.save(playerData);
        }
    }

    public void checkIfPlayerDtoHasInjury(PlayerDto playerDto, Player player) {
        System.out.println("before if statement");
        if (playerDto.getInjury() != null) {
            System.out.println("after if statement");
//            for (InjuryDto injuryDto : playerDto.getInjury()) {
//                Injury injury = InjuryService.convertDtoToInjury(injuryDto);
//                injury.setPlayer(player);
//                injuryRepository.save(injury);
//
//                injury.setPlayer(player);
//                injuryService.updateInjury(injury);
//            }
            if (player.getInjury() == null) {
                System.out.println("Player injury is null :/");
            }
            for (Injury injury : player.getInjury()) {
                System.out.println("updating injury for player: ");
                System.out.println("player id: " + player.getPlayerId());
                injury.setPlayer(player);
                injuryService.updateInjury(injury);
            }
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

    // Is dit wel mogelijk? Want er zou informatie kunnen ontbreken in een DTO
    private Player convertDtoToPlayer(PlayerDto playerDto){
        return (Player) new DtoUtils().convertToEntity(new Player(), playerDto);
    }

    private Player convertDtoToExistingPlayer(PlayerDto playerDto, Player player) {
        Player newPlayer = convertDtoToPlayer(playerDto);
        newPlayer.setPlayerId(player.getPlayerId());
        return newPlayer;
    }





}
