package es.iesptocruz.francisco.agendajsprepasodsw.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Objects;

@XmlRootElement
public abstract class Address implements Serializable {
    private Long id;
    private String name;
    private String description;
    private String street;
    private Integer number;
    private String city;
    private String state;
    private String country;
    private Contact contact;

    public Address(String name, String description, String street, Integer number, String city, String state,
                   String country) {
        this.name = name;
        this.description = description;
        this.street = street;
        this.number = number;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public Address(Long id, String name, String description, String street, Integer number, String city, String state,
                   String country) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.street = street;
        this.number = number;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public Address() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return getId().equals(address.getId()) && Objects.equals(getName(), address.getName()) &&
                Objects.equals(getDescription(), address.getDescription()) && Objects.equals(getStreet(),
                address.getStreet()) && Objects.equals(getNumber(), address.getNumber()) && Objects.equals(getCity(),
                address.getCity()) && Objects.equals(getState(), address.getState()) && Objects.equals(getCountry(),
                address.getCountry());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getStreet(), getNumber(), getCity(), getState(),
                getCountry());
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", street='" + street + '\'' +
                ", number=" + number +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", contact=" + contact +
                '}';
    }
}
