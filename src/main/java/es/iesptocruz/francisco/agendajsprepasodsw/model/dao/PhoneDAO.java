package es.iesptocruz.francisco.agendajsprepasodsw.model.dao;





import es.iesptocruz.francisco.agendamavendsw.model.Contact;
import es.iesptocruz.francisco.agendamavendsw.model.MySQLConnection;
import es.iesptocruz.francisco.agendamavendsw.model.Phone;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhoneDAO extends Phone implements DAO<PhoneDAO>{

    public PhoneDAO() {
    }

    public PhoneDAO(String type, String description, long number) {
        super(type, description, number);
    }

    public PhoneDAO(long id, String type, String description, long number) {
        super(id, type, description, number);
    }

    private static Connection connect() throws Exception {
        MySQLConnection mySQLConnection = MySQLConnection.getMySQLConnection();
        return mySQLConnection.getConnection();
    }

    @Override
    public PhoneDAO add() {
        String query = "insert into "+ MySQLConnection.TPHONE+" ("+
                MySQLConnection.TPHONES_TYPE+", "+
                MySQLConnection.TPHONES_DESCRIPTION+", "+
                MySQLConnection.TPHONES_NUMBER+", "+
                MySQLConnection.TPHONES_CONTACT+") values (?,?,?,?)";
        try {
            Connection connection = connect();
            try (PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)){
                statement.setString(1, getType());
                statement.setString(2, getDescription());
                statement.setLong(3, getNumber());
                statement.setObject(4, getContact().getId());
                statement.executeUpdate();
                ResultSet generatedIds = statement.getGeneratedKeys();
                if (generatedIds.next()){
                    setId(generatedIds.getLong(1));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    return this;
    }

    @Override
    public PhoneDAO edit() {
        String query = "update "+MySQLConnection.TPHONE+" set "+
                MySQLConnection.TPHONES_TYPE+" = ?, "+
                MySQLConnection.TPHONES_DESCRIPTION+" = ?, "+
                MySQLConnection.TPHONES_NUMBER+" = ?, "+
                MySQLConnection.TPHONES_CONTACT+" = ? where "+
                MySQLConnection.TPHONES_ID+" = ?";
        try{
            Connection connection = connect();
            try (PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, getType());
                statement.setString(2, getDescription());
                statement.setLong(3,getNumber());
                statement.setObject(4, getContact());
                statement.setLong(5, getId());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return this;
    }

    @Override
    public PhoneDAO findById() {
        String query = "select * from "+MySQLConnection.TPHONE+" where "+MySQLConnection.TPHONES_ID+" = ?";
        try{
            Connection connection = connect();
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setLong(1, getId());
                ResultSet resultSet = statement.executeQuery();
                if(resultSet.next()){
                    setType(resultSet.getString(2));
                    setDescription(resultSet.getString(3));
                    setNumber(resultSet.getLong(4));
                    ContactDAO contactId = new ContactDAO();
                    contactId.setId(resultSet.getLong(5));
                    setContact(contactId.findById());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return this;
    }

    @Override
    public List<PhoneDAO> findAll() {
        List<PhoneDAO> phones = new ArrayList<>();
        String query = "select * from "+MySQLConnection.TPHONE;
        try{
            Connection connection = connect();
            try(Statement statement = connection.createStatement()){
                ResultSet resultSet = statement.executeQuery(query);
                generatePhones(phones, resultSet);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return phones;
    }

    private void generatePhones(List<PhoneDAO> phones, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            PhoneDAO phone = new PhoneDAO();
            phone.setId(resultSet.getLong(1));
            phone.setType(resultSet.getString(2));
            phone.setDescription(resultSet.getString(3));
            phone.setNumber(resultSet.getLong(4));
            ContactDAO contact = new ContactDAO();
            contact.setId(resultSet.getLong(5));
            phone.setContact(contact.findById());
            phones.add(phone);
        }
    }

    @Override
    public void delete() {
        String query = "delete from "+MySQLConnection.TPHONE+" where "+MySQLConnection.TPHONES_ID+" = ?";
        try{
            Connection connection = connect();
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setLong(1, getId());
                statement.execute();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public List<PhoneDAO> findByContactId(Contact contact){
        String query = "select * from "+MySQLConnection.TPHONE+" where "+MySQLConnection.TPHONES_CONTACT+" = ?";
        List<PhoneDAO> phones = new ArrayList<>();
        try{
            Connection connection = connect();
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setLong(1, contact.getId());
                ResultSet resultSet = statement.executeQuery();
                generatePhones(phones, resultSet);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return phones;
    }
}
