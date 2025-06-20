package com.neosoft.mapper;

import com.neosoft.dto.PositionDTOs;
import com.neosoft.entity.Position;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface PositionMapper {

    PositionDTOs toDTO(Position position);

    Position toEntity(PositionDTOs dto);
}
