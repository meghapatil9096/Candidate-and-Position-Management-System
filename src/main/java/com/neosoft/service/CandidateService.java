package com.neosoft.service;

import com.neosoft.dto.CandidateDTO;

import java.util.List;

public interface CandidateService {

    String save(CandidateDTO dto);

    List<CandidateDTO> getAll();

    CandidateDTO getById(Long id);

    String update(Long id, CandidateDTO dto);

    void delete(Long id);
}
