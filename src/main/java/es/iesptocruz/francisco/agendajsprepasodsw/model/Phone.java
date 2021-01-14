package es.iesptocruz.francisco.agendajsprepasodsw.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Objects;

@XmlRootElement
public abstract class Phone implements Serializable {
    private Long id;
    private String type;
    private String description;
    private Long number;
    private Contact contact;

    public Phone() {
    }

    public Phone(String type, String description, Long number) {
        this.type = type;
        this.description = description;
        this.number = number;
    }

    public Phone(Long id, String type, String description, Long number) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Phone)) return false;
        Phone phone = (Phone) o;
        return getId().equals(phone.getId()) && Objects.equals(getType(), phone.getType()) && Objects.equals(getDescription(), phone.getDescription()) && Objects.equals(getNumber(), phone.getNumber()) && Objects.equals(getContact(), phone.getContact());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getType(), getDescription(), getNumber(), getContact());
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", number=" + number +
                ", contact=" + contact +
                '}';
    }
}
