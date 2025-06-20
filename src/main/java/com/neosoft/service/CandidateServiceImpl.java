package com.neosoft.service;

import com.neosoft.dto.CandidateDTO;
import com.neosoft.entity.Candidate;
import com.neosoft.entity.Position;
import com.neosoft.exception.UserNotFoundException;
import com.neosoft.mapper.CandidateMapper;
import com.neosoft.repository.CandidateRepository;
import com.neosoft.repository.PositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService{

    private final CandidateRepository candidateRepository;
    private final PositionRepository positionRepository;
    private final CandidateMapper candidateMapper;

    @Override
    public String save(CandidateDTO dto) {
        if (candidateRepository.existsByEmail(dto.getEmail())){
            return "Email already exists!";
        }
        if (Period.between(dto.getDob(), LocalDate.now()).getYears()<18){
            return "Candidate must be at least 18 years old.";
        }

        Candidate candidate = candidateMapper.toEntity(dto);
        List<Position> positions = positionRepository.findAllById(dto.getPositionIds());
        if (positions.size() != dto.getPositionIds().size()){
            throw new UserNotFoundException("One or more position IDs are invalid.");
        }
        candidate.setPositions(positions);
        candidateRepository.save(candidate);
        return  "Candidate saved successfully!";
    }

    @Override
    public List<CandidateDTO> getAll() {
        return candidateRepository.findAll()
                .stream()
                .map(candidateMapper::toDTO)
                .toList();
    }

    @Override
    public CandidateDTO getById(Long id) {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException("Candidate not found with ID: " + id));
        return candidateMapper.toDTO(candidate);
    }

    @Override
    public String update(Long id, CandidateDTO dto) {
        Candidate existing = candidateRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException("Candidate not found with ID: "+id));
        if (!existing.getEmail().equals(dto.getEmail()) && candidateRepository.existsByEmail(dto.getEmail())){
            return "Email already exists!";
        }
        existing.setName(dto.getName());
        existing.setEmail(dto.getEmail());
        existing.setDob(dto.getDob());

        List<Position> positions = positionRepository.findAllById(dto.getPositionIds());
        existing.setPositions(positions);

        candidateRepository.save(existing);
        return "Candidate updated successfully!";
    }

    @Override
    public void delete(Long id) {
        if (!candidateRepository.existsById(id)){
            throw new UserNotFoundException("Candidate not found with ID: " + id);
        }
        candidateRepository.deleteById(id);
    }
}
