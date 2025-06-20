package com.neosoft.service;

import com.neosoft.dto.PositionDTOs;
import com.neosoft.entity.Position;
import com.neosoft.exception.UserNotFoundException;
import com.neosoft.mapper.PositionMapper;
import com.neosoft.repository.PositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PositionServiceImpl implements PositionService{

    private final PositionRepository positionRepository;
    private final PositionMapper positionMapper;

    @Override
    public String save(PositionDTOs dto) {
        if (positionRepository.existsByPositionName(dto.getPositionName())){
            return "Position already exists!";
        }
        Position position = positionMapper.toEntity(dto);
        positionRepository.save(position);
        return "Position created successfully!";
    }

    @Override
    public List<PositionDTOs> getAll() {
        return positionRepository.findAll()
                .stream()
                .map(positionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PositionDTOs getBYId(Long id) {
        Position position = positionRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Position not found with ID: "+id));
        return positionMapper.toDTO(position);
    }

    @Override
    public String update(Long id, PositionDTOs dto) {
        Position position = positionRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException("Position not found with ID: "+id));

        position.setPositionName(dto.getPositionName());
        positionRepository.save(position);

        return "Position updated successfully!";
    }

    @Override
    public void delete(Long id) {
        if (!positionRepository.existsById(id)){
            throw new UserNotFoundException("Position not found with ID: "+id);
        }
        positionRepository.deleteById(id);

    }
}
