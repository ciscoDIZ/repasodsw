package es.iesptocruz.francisco.agendajsprepasodsw.model.dao;


import es.iesptocruz.francisco.agendamavendsw.model.Contact;
import es.iesptocruz.francisco.agendamavendsw.model.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ContactDAO extends Contact implements DAO<ContactDAO>{

    public ContactDAO(Long id, String name, String surname, Date birth, String email) {
        super(id, name, surname, birth, email);
    }

    public ContactDAO(String name, String surname, Date birth, String email) {
        super(name, surname, birth, email);
    }

    public ContactDAO() {}
    private static Connection connect() throws Exception {
        MySQLConnection mySQLConnection = MySQLConnection.getMySQLConnection();
        return mySQLConnection.getConnection();
    }
    @Override
    public ContactDAO add() {
        String query = "insert into "+MySQLConnection.TCONTACT+"("+MySQLConnection.TCONTACT_NAME+","+
                MySQLConnection.TCONTACT_SURNAME+","+MySQLConnection.TCONTACT_BIRTH+", "+
                MySQLConnection.TCONTAC_EMAIL+") values (?,?,?,?)";
        Connection connection;
        ContactDAO contact=null;
        try{
            connection = connect();
            try(PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
                statement.setString(1,getName());
                statement.setString(2, getSurname());
                statement.setObject(3, getBirth());
                statement.setString(4, getEmail());
                statement.executeUpdate();
                ResultSet generatedIds = statement.getGeneratedKeys();
                if (generatedIds.next()){
                    setId(generatedIds.getLong(1));
                }
                contact = this;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return contact;
    }

    @Override
    public ContactDAO edit() {
        String query = "update "+MySQLConnection.TCONTACT+" set "+
                MySQLConnection.TCONTACT_NAME+" = ?, "+
                MySQLConnection.TCONTACT_SURNAME+" = ?, "+
                MySQLConnection.TCONTACT_BIRTH+" = ?, "+
                MySQLConnection.TCONTAC_EMAIL+" = ? where "+
                MySQLConnection.TCONTACT_ID+" = "+getId();
        Connection connection;
        try{
            connection = connect();
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, getName());
                statement.setString(2, getSurname());
                statement.setObject(3, getBirth());
                statement.setString(4,getEmail());
                statement.executeUpdate();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return this;
    }

    @Override
    public ContactDAO findById() {
        String query = "select * from "+MySQLConnection.TCONTACT+" where "+MySQLConnection.TCONTACT_ID+" = ?";
        try{
            Connection connection = connect();
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setObject(1,getId());
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()){
                    setName(resultSet.getString(2));
                    setSurname(resultSet.getString(3));
                    setBirth(resultSet.getDate(4));
                    setEmail(resultSet.getString(5));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return this;
    }

    @Override
    public List<ContactDAO> findAll() {
        List<ContactDAO> contacts = new ArrayList<>();
        String query = "select * from "+MySQLConnection.TCONTACT;
        try{
            Connection connection = connect();
            try(Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()){
                    ContactDAO contact = new ContactDAO();
                    contact.setId(resultSet.getLong(1));
                    contact.setName(resultSet.getString(2));
                    contact.setSurname(resultSet.getString(3));
                    contact.setBirth(resultSet.getObject(4, java.sql.Date.class));
                    contact.setEmail(resultSet.getString(5));
                    contacts.add(contact);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return contacts;
    }

    @Override
    public void delete() {
        String query = "delete from "+MySQLConnection.TCONTACT+" where "+MySQLConnection.TCONTACT_ID+" = ?";
        try{
            Connection connection = connect();
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setLong(1, getId());
                statement.execute(query);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
