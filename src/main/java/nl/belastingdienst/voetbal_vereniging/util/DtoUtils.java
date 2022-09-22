package nl.belastingdienst.voetbal_vereniging.util;

import nl.belastingdienst.voetbal_vereniging.dto.DtoEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;

public class DtoUtils {

    private ModelMapper modelMapper;
    public DtoUtils() {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
    }

    public DtoEntity convertToDto(Object obj, DtoEntity mapper) {

        return modelMapper.map(obj, mapper.getClass());
    }

    public Object convertToEntity(Object obj, DtoEntity mapper) {
        return modelMapper.map(mapper, obj.getClass());
    }

}
