package spo.ifsp.edu.br.projeto_lp2.models;

import jakarta.persistence.*;
import spo.ifsp.edu.br.projeto_lp2.utils.FormatPhoneUtil;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UserType type;
    private char gender;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nameId", referencedColumnName = "id")
    private Name name;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "locationId", referencedColumnName = "id")
    private Location location;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "timezoneId", referencedColumnName = "id")
    private Timezone timezone;
    private String email;
    private Date birthday;
    private Date registered;

    @ElementCollection
    @CollectionTable(name = "telephoneNumber")
    private List<String> telephoneNumbers;

    @ElementCollection
    @CollectionTable(name = "mobilePhoneNumber")
    private List<String> mobilePhoneNumbers;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pictureId", referencedColumnName = "id")
    private Picture picture;
    private String nationality;

    public User() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = Character.toLowerCase(gender);
    }

    public Name getName() {
        return name;
    }

    public void setName(String title, String first, String last) {
        this.name = new Name(title, first, last);
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(String street, String city, String state, String postcode, double latitude, double longitude) {
        Coordinate coordinate = new Coordinate(latitude, longitude);
        this.location = new Location(street, city, state, postcode, coordinate);
    }

    public Timezone getTimezone() {
        return timezone;
    }

    public void setTimezone(String offset, String description) {
        this.timezone = new Timezone(offset, description);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getRegistered() {
        return this.registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public List<String> getTelephoneNumbers() {
        return telephoneNumbers;
    }

    public void setTelephoneNumbers(List<String> telephoneNumbers) {
        for (int i = 0; i < telephoneNumbers.size(); i++) {
            telephoneNumbers.set(i, FormatPhoneUtil.format(telephoneNumbers.get(i)));
        }

        this.telephoneNumbers = telephoneNumbers;
    }

    public List<String> getMobilePhoneNumbers() {
        return mobilePhoneNumbers;
    }

    public void setMobilePhoneNumbers(List<String> mobilePhoneNumbers) {
        for (int i = 0; i < mobilePhoneNumbers.size(); i++) {
            mobilePhoneNumbers.set(i, FormatPhoneUtil.format(mobilePhoneNumbers.get(i)));
        }

        this.mobilePhoneNumbers = mobilePhoneNumbers;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(String large, String medium, String thumbnail) {
        this.picture = new Picture(large, medium, thumbnail);
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}
