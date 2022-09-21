package nl.belastingdienst.voetbal_vereniging.service;

import nl.belastingdienst.voetbal_vereniging.dto.PlayerDto;
import nl.belastingdienst.voetbal_vereniging.exception.RecordNotFoundException;
import nl.belastingdienst.voetbal_vereniging.model.Player;
import nl.belastingdienst.voetbal_vereniging.repository.PlayerRepository;
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


    public List<PlayerDto> getAllSpelers() {
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
        return repository.save(convertDtoToSpeler(playerDto));
    }

    public boolean updateSpelerById(PlayerDto playerDto, int id) {
        if (checkIfIdExists(id)) {
            Optional<Player> speler = repository.findById(id);
            repository.save(speler.get());
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

    private PlayerDto convertSpelerToDto(Player player) {
        PlayerDto playerDto = new PlayerDto();
        playerDto.setPlayerName(player.getPlayerName());
        playerDto.setAge(player.getAge());
        playerDto.setGender(player.getGender());
        playerDto.setStreet(player.getStreet());
        playerDto.setHouseNumber(player.getHouseNumber());
        playerDto.setBirthDate(player.getBirthDate());
        playerDto.setPostalCode(player.getPostalCode());
        return playerDto;
    }

    private Optional<PlayerDto> convertSpelerToDto(Optional<Player> speler) {
        PlayerDto playerDto = new PlayerDto();
        playerDto.setPlayerName(speler.get().getPlayerName());
        playerDto.setGender(speler.get().getGender());
        playerDto.setAge(speler.get().getAge());
        playerDto.setStreet(speler.get().getStreet());
        playerDto.setBirthDate(speler.get().getBirthDate());
        playerDto.setHouseNumber(speler.get().getHouseNumber());
        playerDto.setPostalCode(speler.get().getPostalCode());
        Optional<PlayerDto> oDto = Optional.of(playerDto);
        return oDto;
    }

    // Is dit wel mogelijk? Want er zou informatie kunnen ontbreken in een DTO
    private Player convertDtoToSpeler(PlayerDto playerDto){
        Player player = new Player();
        player.setPlayerName(playerDto.getPlayerName());

        return player;
    }





}
