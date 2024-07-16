package com.apartments.graphql.service;

import com.apartments.graphql.domain.*;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ApartmentService {

    private List<ApartmentDTO> apartments = new ArrayList<>();
    AtomicInteger id = new AtomicInteger(0);

    public List<ApartmentDTO> findAll() {
        return apartments;
    }

    public Optional<ApartmentDTO> findOne(String id) {
        return apartments.stream().filter(apartment -> apartment.id().equals(id)).findFirst();
    }

    public ApartmentDTO create(Integer bedrooms, Integer bathrooms, Double area, Boolean hasParking, Float price, String description) {
        UUID uuid = UUID.randomUUID();
        ApartmentDTO apartment = new ApartmentDTO(uuid.toString(), bedrooms, bathrooms, area, hasParking,
                price, description);
        apartments.add(apartment);
        return apartment;
    }

    public ApartmentDTO update(String id, Integer bedrooms, Integer bathrooms, Double area, Boolean hasParking, Float price, String description) {
        ApartmentDTO updatedApartment = new ApartmentDTO(id, bedrooms, bathrooms, area, hasParking, price, description);
        Optional<ApartmentDTO> optional = apartments.stream().filter(c -> c.id().equals(id)).findFirst();
        if (optional.isPresent()) {
            ApartmentDTO apartment = optional.get();
            int index = apartments.indexOf(apartment);
            apartments.set(index, updatedApartment);
        } else {
            throw new IllegalArgumentException("Invalid coffee");
        }
        return updatedApartment;
    }

    public ApartmentDTO delete(String id) {
        ApartmentDTO apartment = apartments.stream().filter(c -> c.id().equals(id))
                .findFirst().orElseThrow(IllegalArgumentException::new);
        apartments.remove(apartment);
        return apartment;
    }

    @PostConstruct
    private void init() {
        apartments.add(new ApartmentDTO(String.valueOf(id.incrementAndGet()), 1, 1, 20.0, false, 1500F, "1 bed, 1 bath"));
        apartments.add(new ApartmentDTO(String.valueOf(id.incrementAndGet()), 2, 1, 30.0, false, 2000F, "2 bed, 1 bath"));
        apartments.add(new ApartmentDTO(String.valueOf(id.incrementAndGet()), 2, 2, 40.0, true, 2500F, "2 bed, 2 bath"));
    }
}
