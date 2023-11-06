package com.tokioschool.flightapp.store.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tokioschool.flightapp.store.StoreService;
import com.tokioschool.flightapp.store.config.StoreConfigurationProperties;
import com.tokioschool.flightapp.store.domain.ResourceDescription;
import com.tokioschool.flightapp.store.dto.ResourceContentDTO;
import com.tokioschool.flightapp.store.dto.ResourceIdDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class StoreServiceImpl implements StoreService {

    private final StoreConfigurationProperties storeConfigurationProperties;
    private final ObjectMapper objectMapper;
    @Override
    public Optional<ResourceIdDTO> saveResource(MultipartFile multipartFile, String description) {

        ResourceDescription resourceDescription = ResourceDescription.builder()
                .contentType(multipartFile.getContentType())
                .filename(multipartFile.getOriginalFilename())
                .size((int) multipartFile.getSize())
                .description(description)
                .build();

        ResourceIdDTO resourceIdDTO= ResourceIdDTO.builder().resourceId(UUID.randomUUID()).build();
        Path pathToContent= storeConfigurationProperties
                .getPath(resourceIdDTO.getResourceId().toString());
        Path pathToDescription= storeConfigurationProperties
                .getPath(resourceIdDTO.getResourceId()+ ".json");
        try{
            Files.write(pathToContent, multipartFile.getBytes());
        } catch (IOException e) {
            log.error("Exception in saveResorce", e);
            return Optional.empty();
        }
        try{
            objectMapper.writeValue(pathToDescription.toFile(), resourceDescription);

        }catch (IOException e){
            log.error("Exception in saveResorce", e);
            return  Optional.empty();
        }

        return Optional.of(resourceIdDTO);
    }




    @Override
    public Optional<ResourceContentDTO> findResource(UUID resourceId) {

        Path pathFromContent= storeConfigurationProperties.getPath(resourceId.toString());
        Path pathFromDescription = storeConfigurationProperties.getPath(resourceId + ".json");

        if (!Files.exists(pathFromContent)){
            return Optional.empty();
        }
        byte[] bytes;
        try{
            bytes = Files.readAllBytes(pathFromContent);
        }catch (IOException e){
            throw  new RuntimeException(e);
        }
        ResourceDescription resourceDescription;
        try{
            resourceDescription =
                    objectMapper.readValue(pathFromDescription.toFile(), ResourceDescription.class);
        }catch (IOException e){
            log.error("Exception in saveResorce", e);
            return Optional.empty();
        }

        return Optional.of(
                ResourceContentDTO.builder()
                        .resouceId(resourceId)
                        .content(bytes)
                        .description(resourceDescription.getDescription())
                        .contentType(resourceDescription.getContentType())
                        .size(resourceDescription.getSize())
                        .filename(resourceDescription.getFilename())
                        .build()
        );
    }



    @Override
    public void deleteResource(UUID resourceId) {

        Path pathFromContent= storeConfigurationProperties.getPath(resourceId.toString());
        Path pathFromDescription = storeConfigurationProperties.getPath(resourceId + ".json");
        try{
            Files.deleteIfExists(pathFromContent);
            Files.deleteIfExists(pathFromDescription);
        }catch (IOException e){
            log.error("Exception in saveResorce", e);
        }
    }
}
