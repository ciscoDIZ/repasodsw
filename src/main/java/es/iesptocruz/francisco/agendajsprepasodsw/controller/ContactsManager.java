package es.iesptocruz.francisco.agendajsprepasodsw.controller;

import es.iesptocruz.francisco.agendamavendsw.exceptions.NullContactException;
import es.iesptocruz.francisco.agendamavendsw.exceptions.NullContactIdException;
import es.iesptocruz.francisco.agendamavendsw.model.Address;
import es.iesptocruz.francisco.agendamavendsw.model.Contact;
import es.iesptocruz.francisco.agendamavendsw.model.Phone;
import es.iesptocruz.francisco.agendamavendsw.model.dao.AddressDAO;
import es.iesptocruz.francisco.agendamavendsw.model.dao.ContactDAO;
import es.iesptocruz.francisco.agendamavendsw.model.dao.PhoneDAO;

import java.util.List;

public class ContactsManager {

    private ContactDAO contact;
    private AddressDAO address;
    private PhoneDAO phone;
    public void createContact(ContactDAO contact) throws NullContactException{
        if(contact != null) {
            this.contact = contact.add();
        }else {
            throw new NullContactException("se debe crear/buscar el contacto antes de hacer operaciones sobre el");
        }
    }

    public void addAddress(AddressDAO address) throws NullContactException {
        if(contact != null) {
            address.setContact(contact);
            this.address = address.add();
        }else{
            throw new NullContactException("se debe crear/buscar el contacto antes de hacer operaciones sobre el");
        }
    }

    public void addPhone(PhoneDAO phone) throws NullContactException{
        if(contact != null) {
            phone.setContact(contact);
            this.phone = phone.add();
        }else{
            throw new NullContactException("se debe crear/buscar el contacto antes de hacer operaciones sobre el");
        }
    }
    public List<PhoneDAO> showContactPhones() throws NullContactException{
        if(contact != null) {
            return  phone.findByContactId(contact);
        }else {
            throw new NullContactException("se debe crear/buscar el contacto antes de hacer operaciones sobre el");
        }
    }
    public void findById(ContactDAO contact) throws NullContactIdException {
        if(contact.getId() != null){
            this.contact = contact.findById();
        }else{
            throw new NullContactIdException();
        }
    }
    public Contact getContact() {
        return contact;
    }

    public void setContact(ContactDAO contact) {
        this.contact = contact;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(AddressDAO address) {
        this.address = address;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(PhoneDAO phone) {
        this.phone = phone;
    }
}
