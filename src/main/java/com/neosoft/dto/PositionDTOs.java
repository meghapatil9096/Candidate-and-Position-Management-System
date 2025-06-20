package com.neosoft.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PositionDTOs {
    private Long id;

    @NotBlank(message = "Position name is required")
    @Size(max = 50, message = "Position name must be at most 50 characters long")
    private String positionName;
}
