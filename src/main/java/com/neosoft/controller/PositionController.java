package com.neosoft.controller;

import com.neosoft.dto.PositionDTOs;
import com.neosoft.service.PositionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/positions")
@RequiredArgsConstructor
public class PositionController {

    private final PositionService positionService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@Valid @RequestBody PositionDTOs dto){
        return ResponseEntity.ok(positionService.save(dto));
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<PositionDTOs>> getAll(){
        return ResponseEntity.ok(positionService.getAll());
    }

    @GetMapping("get/{id}")
    public ResponseEntity<PositionDTOs> getBYId(@PathVariable Long id) {
        return ResponseEntity.ok(positionService.getBYId(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id,
                                         @Valid @RequestBody PositionDTOs dto){
        return ResponseEntity.ok(positionService.update(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        positionService.delete(id);
    }
}
