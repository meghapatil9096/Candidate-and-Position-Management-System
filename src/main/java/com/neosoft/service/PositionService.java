package com.neosoft.service;

import com.neosoft.dto.PositionDTOs;

import java.util.List;

public interface PositionService {

    String save(PositionDTOs dto);

    List<PositionDTOs> getAll();

    PositionDTOs getBYId(Long id);

    String update(Long id, PositionDTOs dto);

    void delete(Long id);
}
