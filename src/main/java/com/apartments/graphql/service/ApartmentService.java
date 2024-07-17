package com.apartments.graphql.service;

import com.apartments.graphql.domain.*;
import com.apartments.graphql.repository.ApartmentRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ApartmentService {
    private final ApartmentRepository apartmentRepository;

    public ApartmentService(ApartmentRepository apartmentRepository) {
        this.apartmentRepository = apartmentRepository;
    }

    public List<ApartmentDTO> findAll() {
        List<ApartmentEntity> all = apartmentRepository.findAll();
        List<ApartmentDTO> dtos = new ArrayList<>();
        for (ApartmentEntity entity : all) {
            dtos.add(entity.toDTO());
        }
        return dtos;
    }

    public Optional<ApartmentDTO> findOne(String id) {
        Optional<ApartmentEntity> optionalApartment = apartmentRepository.findById(id);
        return optionalApartment.map(ApartmentEntity::toDTO);
    }

    public ApartmentDTO create(Integer bedrooms, Integer bathrooms, Double area, Boolean hasParking, Float price, String description) {
        UUID uuid = UUID.randomUUID();
        ApartmentDTO apartment = new ApartmentDTO(uuid.toString(), bedrooms, bathrooms, area, hasParking,
                price, description);
        ApartmentEntity entity = new ApartmentEntity()
                .setId(uuid.toString())
                .setNumberOfBedrooms(bedrooms)
                .setNumberOfBathrooms(bathrooms)
                .setArea(area)
                .setHasParking(hasParking)
                .setPrice(price)
                .setDescription(description);
        apartmentRepository.save(entity);
        return apartment;
    }

    public ApartmentDTO update(String id, Integer bedrooms, Integer bathrooms, Double area, Boolean hasParking, Float price, String description) {
        ApartmentEntity entity = apartmentRepository.getReferenceById(id);
        if (Objects.nonNull(bedrooms)) {
            entity.setNumberOfBedrooms(bedrooms);
        }
        if (Objects.nonNull(bathrooms)) {
            entity.setNumberOfBathrooms(bathrooms);
        }
        if (Objects.nonNull(area)) {
            entity.setArea(area);
        }
        if (Objects.nonNull(hasParking)) {
            entity.setHasParking(hasParking);
        }
        if (Objects.nonNull(price)) {
            entity.setPrice(price);
        }
        if (Objects.nonNull(description)) {
            entity.setDescription(description);
        }
        return entity.toDTO();
    }

    public ApartmentDTO delete(String id) {
        Optional<ApartmentEntity> apartment = apartmentRepository.findById(id);
        if (apartment.isEmpty()) {
            throw new IllegalArgumentException(String.format("Apartment not Found. Id: %s", id));
        }
        apartmentRepository.delete(apartment.get());
        return apartment.get().toDTO();
    }

    @PostConstruct
    private void init() {
        apartmentRepository.deleteAll();
        ApartmentEntity entity1 = new ApartmentEntity()
                .setId(String.valueOf(UUID.randomUUID().toString()))
                .setNumberOfBedrooms(1)
                .setNumberOfBathrooms(1)
                .setArea(20.0)
                .setHasParking(false)
                .setPrice(1500F)
                .setDescription("1 bed, 1 bath");
        ApartmentEntity saved = apartmentRepository.save(entity1);
        ApartmentEntity entity2 = new ApartmentEntity()
                .setId(String.valueOf(UUID.randomUUID().toString()))
                .setNumberOfBedrooms(2)
                .setNumberOfBathrooms(1)
                .setArea(30.0)
                .setHasParking(false)
                .setPrice(2000F)
                .setDescription("2 bed, 1 bath");
        ApartmentEntity saved2 = apartmentRepository.save(entity2);
        ApartmentEntity entity3 = new ApartmentEntity()
                .setId(String.valueOf(UUID.randomUUID().toString()))
                .setNumberOfBedrooms(2)
                .setNumberOfBathrooms(2)
                .setArea(40.0)
                .setHasParking(true)
                .setPrice(2500F)
                .setDescription("2 bed, 2 bath");
        ApartmentEntity saved3 = apartmentRepository.save(entity3);
    }
}
