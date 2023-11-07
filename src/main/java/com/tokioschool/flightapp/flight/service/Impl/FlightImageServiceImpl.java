package com.tokioschool.flightapp.flight.service.Impl;

import com.tokioschool.flightapp.flight.domain.FlightImage;
import com.tokioschool.flightapp.flight.mvc.dto.FlightImageResourceDTO;
import com.tokioschool.flightapp.flight.service.FlightImageService;
import com.tokioschool.flightapp.store.StoreService;
import com.tokioschool.flightapp.store.dto.ResourceContentDTO;
import com.tokioschool.flightapp.store.dto.ResourceIdDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FlightImageServiceImpl implements FlightImageService {

  private StoreService storeService;

    @Override
    public FlightImage saveImage(MultipartFile multipartFile) {

        ResourceIdDTO resourceIdDTO = storeService.saveResource(multipartFile, "flight-app")
                .orElseThrow(() -> new IllegalStateException("Resource not saved in store"));

        return FlightImage.builder()
                .contentType(multipartFile.getContentType())
                .size((int) multipartFile.getSize())
                .filename(multipartFile.getOriginalFilename())
                .resourceId(resourceIdDTO.getResourceId())
                .build();

    }

    @Override
    public FlightImageResourceDTO getImage(UUID resourceId) {

        ResourceContentDTO resourceContentDTO = storeService.findResource(resourceId)
                .orElseThrow(() -> new IllegalArgumentException("Resource with id:%s not founf or not available"
                        .formatted(resourceId)));

        return FlightImageResourceDTO.builder()
                .content(resourceContentDTO.getContent())
                .size(resourceContentDTO.getSize())
                .contentType(resourceContentDTO.getContentType())
                .filename(resourceContentDTO.getFilename())
                .build();
    }

    @Override
    public void delete(UUID resourceId) {

        storeService.deleteResource(resourceId);
    }
}
