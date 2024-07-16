package com.apartments.graphql.domain;

public record ApartmentDTO(
        String id,
        Integer numberOfBedrooms,
        Integer numberOfBathrooms,
        Double area,
        Boolean hasParking,
        Float price,
        String description
) {

    public String toJson() {
        return String.format("""
                {"id": "%s", "numberOfBedrooms": %d, "numberOfBathrooms": %d, "area": %.2f, "hasParking": %b, "price": %.2f, "description": "%s" }
                """, id, numberOfBedrooms, numberOfBathrooms, area, hasParking, price, description);
    }

    public ApartmentEntity toEntity() {
        return new ApartmentEntity()
                .setId(id)
                .setNumberOfBedrooms(numberOfBedrooms)
                .setNumberOfBathrooms(numberOfBathrooms)
                .setArea(area)
                .setHasParking(hasParking)
                .setPrice(price)
                .setDescription(description);
    }
}
