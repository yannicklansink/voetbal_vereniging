package nl.belastingdienst.voetbal_vereniging.service;

import nl.belastingdienst.voetbal_vereniging.dto.InjuryDto;
import nl.belastingdienst.voetbal_vereniging.exception.RecordNotFoundException;
import nl.belastingdienst.voetbal_vereniging.model.Injury;
import nl.belastingdienst.voetbal_vereniging.repository.InjuryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InjuryServiceTest {

    @InjectMocks
    private InjuryService injuryService;

    @Mock
    private InjuryRepository injuryRepository;

    Injury injury = new Injury();
    Injury injury2 = new Injury();

    InjuryDto injuryDto = new InjuryDto();

    InjuryDto injuryDto2 = new InjuryDto();

    List<Injury> injuryList = new ArrayList<>();

    List<InjuryDto> injuryDtoList = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        injury.setStartDate(LocalDate.of(2020, 5, 5));
        injury.setEndDate(LocalDate.of(2022, 2, 6));
        injury.setExplanation("last van de knie");

        injury2.setStartDate(LocalDate.of(2008, 5, 5));
        injury2.setEndDate(LocalDate.of(2010, 2, 6));
        injury2.setExplanation("last van de andere knie");

        injuryDto.setStartDate(LocalDate.of(2020, 5, 5));
        injuryDto.setEndDate(LocalDate.of(2022, 2, 6));
        injuryDto.setExplanation("last van keel");

        injuryDto2.setStartDate(LocalDate.of(2020, 5, 5));
        injuryDto2.setEndDate(LocalDate.of(2022, 2, 6));
        injuryDto2.setExplanation("last van arm");

        injuryDtoList.add(injuryDto);
        injuryDtoList.add(injuryDto2);
    }

    @Test
    public void getOneInjuryByIdException() {
        assertThrows(RecordNotFoundException.class, () -> injuryService.getInjuryById(100));
    }

    @Test
    public void getAllInjuries() {
        // arrange
        when(injuryRepository.findAll()).thenReturn(injuryList);
        when(injuryRepository.count()).thenReturn(1L);

        // act
        List<InjuryDto> foundInjuries = injuryService.getAllInjuries();

        // assert
        verify(injuryRepository, times(1)).findAll();
        assertThat(foundInjuries).isEqualTo(injuryList);
    }

    @Test
    public void getAllInjuriesThrowException() {
        when(injuryRepository.count()).thenReturn(0L);
        assertThrows(RecordNotFoundException.class, () -> injuryService.getAllInjuries());
    }

    @Test
    public void getInjuryById() {
        Optional<Injury> injuryOptional = Optional.of(injury);
        when(injuryRepository.findById(1)).thenReturn(injuryOptional);

        Optional<InjuryDto> optionalInjuryDto = injuryService.getInjuryById(1);

        verify(injuryRepository, atLeastOnce()).findById(1);
    }

    @Test
    public void addNewInjury() {
       when(injuryRepository.save(injury)).thenReturn(injury);
       InjuryDto injuryDtoNew = injuryService.convertInjuryToDto(injury);

       Injury newInjury = injuryService.addNewInjury(injuryDtoNew);
       InjuryDto newInjuryDto = injuryService.convertInjuryToDto(newInjury);

       assertThat(newInjuryDto).isEqualTo(injuryDtoNew);
    }

    @Test
    public void updateInjuryById() {
        when(injuryRepository.save(injury)).thenReturn(injury);
        when(injuryRepository.findById(1)).thenReturn(Optional.of(injury));
        InjuryDto injuryDtoNew = injuryService.convertInjuryToDto(injury);

        Boolean value = injuryService.updateInjuryById(injuryDtoNew, 1);

        assertEquals(value, true);
    }

    @Test
    public void updateInjury() {
        when(injuryRepository.save(injury)).thenReturn(injury);

        injuryService.updateInjury(injury);

        verify(injuryRepository, atLeastOnce()).save(injury);
    }

    @Test
    public void deleteInjuryById() {
        Optional<Injury> injuryOptional = Optional.of(injury);
        when(injuryRepository.findById(1)).thenReturn(injuryOptional);

        Boolean value = injuryService.deleteInjuryById(1);

        assertEquals(value, true);
    }


}
