package nl.belastingdienst.voetbal_vereniging.service;

import nl.belastingdienst.voetbal_vereniging.dto.DtoEntity;
import nl.belastingdienst.voetbal_vereniging.dto.PlayerDto;
import nl.belastingdienst.voetbal_vereniging.exception.RecordNotFoundException;
import nl.belastingdienst.voetbal_vereniging.model.Player;
import nl.belastingdienst.voetbal_vereniging.repository.PlayerRepository;
import nl.belastingdienst.voetbal_vereniging.util.DtoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    private PlayerRepository repository;

    @Autowired
    public PlayerService(PlayerRepository repository) {
        this.repository = repository;
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
        return repository.save(convertDtoToPlayer(playerDto));
    }

    public boolean updatePlayerById(PlayerDto playerDto, int id) {
        if (checkIfIdExists(id)) {
            Player updatedPlayer = convertDtoToExistingPlayer(playerDto, repository.findById(id).get());
            repository.save(updatedPlayer);
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

    public boolean checkIfIdExists(int id) {
        Optional<Player> newSpeler = repository.findById(id);
        if (newSpeler.isPresent()) {
            return true;
        } else {
            throw new RecordNotFoundException("Id is not in use");
        }
    }

    private DtoEntity convertSpelerToDto(Player player) {
//        PlayerDto playerDto = new PlayerDto();
        System.out.println("----- converting player to dto ------");
        return new DtoUtils().convertToDto(player, new PlayerDto());
//        playerDto.setPlayerName(player.getPlayerName());
//        playerDto.setAge(player.getAge());
//        playerDto.setGender(player.getGender());
//        playerDto.setStreet(player.getStreet());
//        playerDto.setHouseNumber(player.getHouseNumber());
//        playerDto.setBirthDate(player.getBirthDate());
//        playerDto.setPostalCode(player.getPostalCode());
//        return playerDto;
    }

    private Optional<PlayerDto> convertSpelerToDto(Optional<Player> player) {
        System.out.println("----- converting player optional to playerDto optional ------");
        PlayerDto playerDto = (PlayerDto) new DtoUtils().convertToDto(player.get(), new PlayerDto());
        return Optional.of(playerDto);

//        playerDto.setPlayerName(speler.get().getPlayerName());
//        playerDto.setGender(speler.get().getGender());
//        playerDto.setAge(speler.get().getAge());
//        playerDto.setStreet(speler.get().getStreet());
//        playerDto.setBirthDate(speler.get().getBirthDate());
//        playerDto.setHouseNumber(speler.get().getHouseNumber());
//        playerDto.setPostalCode(speler.get().getPostalCode());
//        Optional<PlayerDto> oDto = Optional.of(playerDto);
//        return oDto;
    }

    // Is dit wel mogelijk? Want er zou informatie kunnen ontbreken in een DTO
    private Player convertDtoToPlayer(PlayerDto playerDto){
        return (Player) new DtoUtils().convertToEntity(new Player(), playerDto);
    }

    private Player convertDtoToExistingPlayer(PlayerDto playerDto, Player player) {
        Player newPlayer = convertDtoToPlayer(playerDto);
        newPlayer.setPlayerId(player.getPlayerId());
        return newPlayer;
//        player.setPlayerName(dto.getPlayerName());
//        player.setAge(dto.getAge());
//        player.setGender(dto.getGender());
//        player.setStreet(dto.getStreet());
//        player.setHouseNumber(dto.getHouseNumber());
//        player.setBirthDate(dto.getBirthDate());
//        player.setPostalCode(dto.getPostalCode());
//        return player;
    }





}
