package com.apartments.graphql.controller;

import com.apartments.graphql.domain.ApartmentDTO;
import com.apartments.graphql.service.ApartmentService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class ApartmentController {

    private final ApartmentService apartmentService;

    public ApartmentController(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    @QueryMapping
    public List<ApartmentDTO> findAll() {
        return apartmentService.findAll();
    }

    @QueryMapping
    public Optional<ApartmentDTO> findOne(@Argument String id) {
        return apartmentService.findOne(id);
    }

    @MutationMapping
    public ApartmentDTO create(
            @Argument Integer bedrooms,
            @Argument Integer bathrooms,
            @Argument Double area,
            @Argument Boolean hasParking,
            @Argument Float price,
            @Argument String description) {
        return apartmentService.create(bedrooms, bathrooms, area, hasParking, price, description);
    }

    @MutationMapping
    public ApartmentDTO update(
            @Argument String id,
            @Argument Integer bedroom,
            @Argument Integer bathroom,
            @Argument Double area,
            @Argument Boolean hasParking,
            @Argument Float price,
            @Argument String description) {
        return apartmentService.update(id, bedroom, bathroom, area, hasParking, price, description);
    }

    @MutationMapping
    public ApartmentDTO delete(@Argument String id) {
        return apartmentService.delete(id);
    }
}