package com.neosoft.controller;

import com.neosoft.dto.CandidateDTO;
import com.neosoft.service.CandidateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/candidates")
@RequiredArgsConstructor
public class CandidateController {

    private final CandidateService candidateService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@Valid @RequestBody CandidateDTO dto){
        return ResponseEntity.ok(candidateService.save(dto));
    }
    @GetMapping("/get/all")
    public ResponseEntity<List<CandidateDTO>> getAll(){
        return ResponseEntity.ok(candidateService.getAll());
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<CandidateDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(candidateService.getById(id));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id,
                                         @Valid @RequestBody CandidateDTO dto){
        return ResponseEntity.ok(candidateService.update(id,dto));
    }
    @DeleteMapping("/delete/{id}")
    void delete(@PathVariable Long id){
        candidateService.delete(id);
    }
}
