package nl.belastingdienst.voetbal_vereniging.service;

import nl.belastingdienst.voetbal_vereniging.dto.PlayerDataDto;
import nl.belastingdienst.voetbal_vereniging.exception.BadRequestException;
import nl.belastingdienst.voetbal_vereniging.model.Player;
import nl.belastingdienst.voetbal_vereniging.model.PlayerData;
import nl.belastingdienst.voetbal_vereniging.model.enumeration.Foot;
import nl.belastingdienst.voetbal_vereniging.model.enumeration.Position;
import nl.belastingdienst.voetbal_vereniging.model.enumeration.Star;
import nl.belastingdienst.voetbal_vereniging.repository.PlayerDataRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import nl.belastingdienst.voetbal_vereniging.exception.RecordNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PlayerDataServiceTest {

    @InjectMocks
    private PlayerDataService playerDataService;

    @Mock
    private PlayerDataRepository playerDataRepository;

    @Mock
    private PlayerService playerService;

    PlayerData playerData = new PlayerData();

    PlayerData playerData2 = new PlayerData();

    List<PlayerData> playerDataList = new ArrayList<>();


    @BeforeEach
    public void setUp() {
        playerData.setHeight(200);
        playerData.setWeight(80);
        playerData.setTopSpeed(30);
        playerData.setPreferedFoot(Foot.LEFT);
        playerData.setWorkRate(70);
        playerData.setWeakFoot(Star.FIVE);
        playerData.setSkillMoves(Star.TWO);
        playerData.setDribbling(79);
        playerData.setShooting(88);
        playerData.setPassing(77);
        playerData.setPosition(Position.AM);

        playerData2.setHeight(180);
        playerData2.setWeight(70);
        playerData2.setTopSpeed(33);
        playerData2.setPreferedFoot(Foot.RIGHT);
        playerData2.setWorkRate(75);
        playerData2.setWeakFoot(Star.FOUR);
        playerData2.setSkillMoves(Star.THREE);
        playerData2.setDribbling(88);
        playerData2.setShooting(70);
        playerData2.setPassing(69);
        playerData2.setPosition(Position.CM);

    }


    @Test
    public void getPlayerDataByIdException() {
        assertThrows(RecordNotFoundException.class, () -> playerDataService.getPlayerDataById(100));
    }

    @Test
    public void getALlPlayersData() {
        // arrange
        when(playerDataRepository.findAll()).thenReturn(playerDataList);
        when(playerDataRepository.count()).thenReturn(1L);

        // act
        List<PlayerDataDto> foundPlayerDataList = playerDataService.getAllPlayersData();

        // assert
        verify(playerDataRepository, times(1)).findAll();
        assertThat(foundPlayerDataList).isEqualTo(playerDataList);
    }

    @Test
    public void getAllPlayersDataThrowException() {
        when(playerDataRepository.count()).thenReturn(0L);
        assertThrows(RecordNotFoundException.class, () -> playerDataService.getAllPlayersData());
    }

    @Test
    public void getPlayerDataById() {
        Optional<PlayerData> playerDataOptional = Optional.of(playerData);
        when(playerDataRepository.findById(1)).thenReturn(playerDataOptional);

        Optional<PlayerDataDto> optionalPlayerDataDto = playerDataService.getPlayerDataById(1);

        verify(playerDataRepository, atLeastOnce()).findById(1);
    }

    @Test
    public void getPlayersScoutingList() {
        String playerDataPosition = playerData.getPosition().toString();
        playerDataList.add(playerData);
        when(playerDataRepository.findPlayersScoutingList(playerDataPosition)).thenReturn(playerDataList);
        PlayerDataDto playerDataDtoNew = playerDataService.convertPlayerDataToDto(playerData);

        List<PlayerDataDto> playerDataListNew = playerDataService.getPlayersScoutingList(playerDataDtoNew);

        verify(playerDataRepository, atLeastOnce()).findPlayersScoutingList(playerDataPosition);
    }

    @Test
    public void getPlayerScoutingListByPositionException() {
        PlayerDataDto playerDataDtoNew = playerDataService.convertPlayerDataToDto(playerData);
        playerDataDtoNew.setPosition(null);
        assertThrows(BadRequestException.class, () -> playerDataService.getPlayersScoutingList(playerDataDtoNew));
    }

    @Test
    public void addNewPlayerData() {
        when(playerDataRepository.save(playerData)).thenReturn(playerData);
        PlayerDataDto playerDataDtoNew = playerDataService.convertPlayerDataToDto(playerData);

        PlayerData newPlayerData = playerDataService.addNewPlayerData(playerDataDtoNew);
        PlayerDataDto newPlayerDataDto = playerDataService.convertPlayerDataToDto(newPlayerData);

        assertThat(newPlayerDataDto).isEqualTo(playerDataDtoNew);
    }

    @Test
    public void updatePlayerDataById() {
        Optional<Player> newPlayer = playerService.getPlayerById(1);
        when(playerService.getPlayerById(1)).thenReturn(newPlayer);
        when(playerDataRepository.save(playerData)).thenReturn(playerData);
        when(playerDataRepository.findById(1)).thenReturn(Optional.of(playerData));
        PlayerDataDto playerDataDtoNew = playerDataService.convertPlayerDataToDto(playerData);

        Boolean value = playerDataService.updatePlayerDataById(playerDataDtoNew, 1);

        assertEquals(value, true);
    }

    @Test
    public void deletePlayerDataById() {
        Optional<PlayerData> playerDataOptional = Optional.of(playerData);
        when(playerDataRepository.findById(1)).thenReturn(playerDataOptional);

        Boolean value = playerDataService.deletePlayerDataById(1);

        assertEquals(value, true);
    }

}
