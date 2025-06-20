package com.neosoft.mapper;

import com.neosoft.dto.CandidateDTO;
import com.neosoft.entity.Candidate;
import com.neosoft.entity.Position;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CandidateMapper {

    @Mapping(target = "positions", ignore = true)
    Candidate toEntity(CandidateDTO dto);

    @Mapping(target =  "positionIds", expression = "java(getPositionIds(candidate.getPositions()))")
    CandidateDTO toDTO(Candidate candidate);

    default List<Long> getPositionIds(List<Position> positions){
        return positions.stream().map(Position::getId).toList();
    }
}
