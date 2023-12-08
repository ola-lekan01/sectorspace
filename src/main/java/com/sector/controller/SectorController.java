package com.sector.controller;

import com.sector.request.SectorRequest;
import com.sector.response.SectorResponse;
import com.sector.response.SystemSectorResponse;
import com.sector.service.SectorInitializer;
import com.sector.service.SectorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/sector")
@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class SectorController {

    private final SectorService sectorService;

    private final SectorInitializer sectorInitializer;

    @PostMapping("/create")
    public ResponseEntity<List<SectorResponse>> createSector(@Valid @RequestBody SectorRequest request){
        return ResponseEntity.ok(sectorService.createSector(request));
    }

    @GetMapping("/getSector")
    public ResponseEntity<List<SectorResponse>> getSectorByUserId(@RequestParam("userId") String userId){
        return ResponseEntity.ok(sectorService.getSectorByUser(userId));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getSectorByUserId(){
        return ResponseEntity.ok(sectorInitializer.getSystemSector());
    }
}
