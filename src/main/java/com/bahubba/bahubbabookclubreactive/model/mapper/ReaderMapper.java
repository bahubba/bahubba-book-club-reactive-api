package com.bahubba.bahubbabookclubreactive.model.mapper;

import com.bahubba.bahubbabookclubreactive.model.dto.ReaderDTO;
import com.bahubba.bahubbabookclubreactive.model.entity.Reader;
import com.bahubba.bahubbabookclubreactive.model.mapper.custom.PasswordEncoderMapper;
import lombok.Generated;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapping logic for readers (users)
 */
@Mapper(componentModel = "spring", uses = PasswordEncoderMapper.class)
public interface ReaderMapper {

    @Generated
    ReaderDTO entityToDTO(Reader reader);

    @Generated
    List<ReaderDTO> entityListToDTO(List<Reader> readers);
}
