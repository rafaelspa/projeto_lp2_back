package spo.ifsp.edu.br.projeto_lp2.models;

import jakarta.persistence.*;


@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Region region;
    private String street;
    private String city;
    private String state;
    private String postcode;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coordinatesId", referencedColumnName = "id")
    private Coordinate coordinate;
    public Location() {
    }

    public Location(String street, String city, String state, String postcode, Coordinate coordinate) {
        this.region = getRegionFromCoordinates(coordinate);
        this.street = street;
        this.city = city;
        this.state = state;
        this.postcode = postcode;
        this.coordinate = coordinate;
    }

    private static Region getRegionFromCoordinates(Coordinate coordinate) {
        return null;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public Coordinate getCoordinates() {
        return coordinate;
    }

    public void setCoordinates(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
}
