package nl.belastingdienst.voetbal_vereniging.service;

import nl.belastingdienst.voetbal_vereniging.dto.PlayerDataDto;
import nl.belastingdienst.voetbal_vereniging.dto.PlayerDto;
import nl.belastingdienst.voetbal_vereniging.exception.RecordNotFoundException;
import nl.belastingdienst.voetbal_vereniging.model.Player;
import nl.belastingdienst.voetbal_vereniging.model.PlayerData;
import nl.belastingdienst.voetbal_vereniging.repository.PlayerDataRepository;
import nl.belastingdienst.voetbal_vereniging.util.DtoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerDataService {

    private PlayerDataRepository repository;

    private PlayerService playerService;

    @Autowired
    public PlayerDataService(PlayerDataRepository repository, PlayerService playerService) {
        this.playerService = playerService;
        this.repository = repository;
    }

    public List<PlayerDataDto> getAllPlayersData() {
        if (repository.count() != 0) {
            return repository.findAll()
                    .stream()
                    .map(this::convertPlayerDataToDto)
                    .collect(Collectors.toList());

        } else {
            throw new RecordNotFoundException("There are no players data in the database");
        }
    }

    public Optional<PlayerDataDto> getPlayerDataById(int id) {
        Optional<PlayerDataDto> newPlayerDataDto = Optional.empty();
        if (checkIfIdExists(id)) {
            Optional<PlayerData> playerData = repository.findById(id);
            newPlayerDataDto = convertPlayerDataToDto(playerData);
        }
        return newPlayerDataDto;
    }

    public PlayerData addNewPlayerData(PlayerDataDto playerDataDto) {
        return repository.save(convertDtoToPlayerData(playerDataDto));
    }

    public boolean updatePlayerDataById(PlayerDataDto playerDataDto, int id) {
        if (checkIfIdExists(id)) {
            PlayerData updatedPlayerData = convertDtoToExistingPlayerData(playerDataDto, repository.findById(id).get());
            Optional<Player> player = playerService.getPlayerById(id);
            if (player.isPresent()) {
                updatedPlayerData.setPlayer(player.get());
            }
            repository.save(updatedPlayerData);
            return true;
        }
        return false;
    }

    public boolean deletePlayerDataById(int id) {
        if (checkIfIdExists(id)) {
            System.out.println("deleting playerdata by id: " + id);

//            Optional<PlayerData> playerData = repository.findById(id);
//            if (playerData.isPresent()) {
//                Player player = playerData.get().getPlayer();
//                player.setPlayerData(null);
//            }


//            repository.deleteById(id); // delete werkt niet.
            repository.delete(id);
            return true;
        }
        return false;
    }

    /*
    Method will return a RecordNotFoundException when the Optional object is empty
     */
    public boolean checkIfIdExists(int id) {
        Optional<PlayerData> newPlayerData = repository.findById(id);
        if (newPlayerData.isPresent()) {
            return true;
        } else {
            throw new RecordNotFoundException("Id is not in use");
        }
    }

    // Convert DTOs and Entities methodes
    private PlayerDataDto convertPlayerDataToDto(PlayerData playerData) {
        return (PlayerDataDto) new DtoUtils().convertToDto(playerData, new PlayerDataDto());
    }

    private Optional<PlayerDataDto> convertPlayerDataToDto(Optional<PlayerData> playerData) {
        PlayerDataDto playerDataDto = (PlayerDataDto) new DtoUtils().convertToDto(playerData.get(), new PlayerDataDto());
        return Optional.of(playerDataDto);
    }

    public static PlayerData convertDtoToPlayerData(PlayerDataDto playerDataDto){
        return (PlayerData) new DtoUtils().convertToEntity(new PlayerData(), playerDataDto);
    }

    private PlayerData convertDtoToExistingPlayerData(PlayerDataDto playerDataDto, PlayerData playerData) {
        PlayerData newPlayerData = convertDtoToPlayerData(playerDataDto);
        newPlayerData.setId(playerData.getId());
        return newPlayerData;
    }

}
