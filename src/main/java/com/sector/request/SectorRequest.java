package com.sector.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class SectorRequest {

    private List<String> sectorName;

    private Boolean agreeToTerms;

    @NotBlank(message = "UserId is required")
    private String userId;

    @NotBlank(message = "User Name is required")
    private String userName;
}
