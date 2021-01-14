package es.iesptocruz.francisco.agendajsprepasodsw.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@XmlRootElement
public abstract class Contact implements Serializable {
    private Long id;
    private String name;
    private String surname;
    private Date birth;
    private String email;
    private List<Phone> phones;
    private List<Address> addresses;
    public Contact() {
        phones = new ArrayList<>();
        addresses = new ArrayList<>();
    }

    public Contact(String name, String surname, Date birth, String email) {
        this.name = name;
        this.surname = surname;
        this.birth = birth;
        this.email = email;
        phones = new ArrayList<>();
    }
    public Contact(Long id, String name, String surname, Date birth, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birth = birth;
        this.email = email;
        phones = new ArrayList<>();
    }
    public Phone addPhone(Phone phone){
        phones.add(phone);
        phone.setContact(this);
        return phone;
    }
    public void removePhone(Phone phone){
        phones.remove(phone);
        phone.setContact(null);
    }
    public Address addAddress(Address address){
        addresses.add(address);
        address.setContact(this);
        return address;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;
        Contact contact = (Contact) o;
        return getId().equals(contact.getId()) && Objects.equals(getName(), contact.getName()) && Objects.equals(getSurname(), contact.getSurname()) && Objects.equals(getBirth(), contact.getBirth()) && Objects.equals(getEmail(), contact.getEmail()) && Objects.equals(getPhones(), contact.getPhones()) && Objects.equals(getAddresses(), contact.getAddresses());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getSurname(), getBirth(), getEmail(), getPhones(), getAddresses());
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birth=" + birth +
                ", email='" + email + '\'' +
                ", phones=" + phones +
                ", addresses=" + addresses +
                '}';
    }
}
