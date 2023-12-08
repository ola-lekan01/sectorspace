package com.sector.service;


import com.sector.model.DataSectors;
import com.sector.model.User;
import com.sector.model.UserSector;
import com.sector.repository.SectorRepository;
import com.sector.request.SectorRequest;
import com.sector.response.SectorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SectorService {

    private final SectorRepository sectorRepository;

    private final UserService userService;

    private final SectorInitializer sectorInitializer;


    public List<SectorResponse> createSector(SectorRequest sectorRequest) {
        validateSectorRequest(sectorRequest);
        User foundUser = userService.getUserById(sectorRequest.getUserId());
        List<DataSectors> foundDataSectors = sectorInitializer.getAllSectorByName(sectorRequest.getSectorName());

        if (foundDataSectors == null) {
            throw new RuntimeException("Sector not found, Please enter a Valid Sector Value");
        }

        List<SectorResponse> responses = new ArrayList<>();

        for (DataSectors dataSectors : foundDataSectors) {
            UserSector finalFoundUserSector = new UserSector();
            finalFoundUserSector.setSectorName(dataSectors.getSectorName());
            finalFoundUserSector.setSectorValue(dataSectors.getSectorValue());
            finalFoundUserSector.setUser(foundUser);
            finalFoundUserSector.setAgreeToTerms(sectorRequest.getAgreeToTerms());
            saveSector(finalFoundUserSector);

            responses.add(SectorResponse.builder()
                    .id(finalFoundUserSector.getId())
                    .sectorName(finalFoundUserSector.getSectorName())
                    .sectorValue(finalFoundUserSector.getSectorValue())
                    .name(foundUser.getName())
                    .build());
        }

        foundUser.setUserName(sectorRequest.getUserName());
        userService.saveUser(foundUser);
        return responses;
    }


    private void validateSectorRequest(SectorRequest sectorRequest) {
        if (sectorRequest == null) {
            throw new RuntimeException("UserSector request cannot be null");
        }

        if (sectorRequest.getUserId() == null) {
            throw new RuntimeException("User id cannot be null");
        }

        if (sectorRequest.getSectorName() == null) {
            throw new RuntimeException("UserSector Value cannot be null");
        }

        if (!sectorRequest.getAgreeToTerms()) {
            throw new RuntimeException("You must agree to the terms and conditions");
        }
    }


    public List<SectorResponse> getSectorByUser(String id) {
        User foundUser = userService.getUserById(id);
        List<UserSector> foundUserSector = getSectorByUserId(foundUser.getId());
        List<SectorResponse> responses = new ArrayList<>();

        for (UserSector userSector : foundUserSector) {
            responses.add(SectorResponse.builder()
                    .id(userSector.getId())
                    .sectorName(userSector.getSectorName())
                    .sectorValue(userSector.getSectorValue())
                    .name(foundUser.getName())
                    .build());
        }
        return responses;
    }


    public List<UserSector> getSectorByUserId(String id) {
        return sectorRepository.findAllByUser_Id(id);
    }

    public UserSector getSector(String id) {
        return sectorRepository.findByUser_Id(id).orElse(null);
    }

    public void saveSector(UserSector userSector) {
        sectorRepository.save(userSector);
    }
}
