package es.iesptocruz.francisco.agendajsprepasodsw.model.dao;


import es.iesptocruz.francisco.agendamavendsw.model.Address;
import es.iesptocruz.francisco.agendamavendsw.model.MySQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressDAO extends Address implements DAO<AddressDAO> {
    public AddressDAO(String name, String description, String street, Integer number, String city, String state, String country) {
        super(name, description, street, number, city, state, country);
    }

    public AddressDAO(Long id, String name, String description, String street, Integer number, String city, String state, String country) {
        super(id, name, description, street, number, city, state, country);
    }

    public AddressDAO() {
        super();
    }

    private static Connection connect() throws Exception {
        MySQLConnection mySQLConnection = MySQLConnection.getMySQLConnection();
        return mySQLConnection.getConnection();
    }
    @Override
    public AddressDAO add() {
        String query = "insert into " + MySQLConnection.TADDRESS + "(" +
                MySQLConnection.TADDRESS_NAME + "," + MySQLConnection.TADDRESS_DESCRIPTION + "," +
                MySQLConnection.TADDRESS_STREET + "," + MySQLConnection.TADDRESS_NUMBER + "," +
                MySQLConnection.TADDRESS_CITY + "," + MySQLConnection.TADDRESS_STATE + "," +
                MySQLConnection.TADDRESS_COUNTRY +","+MySQLConnection.TADDRESS_CONTACT+") values (?,?,?,?,?,?,?,?);";
        try {
            Connection connection = connect();
            try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                setStatement(statement);
                statement.executeUpdate();
                ResultSet generatedIds = statement.getGeneratedKeys();
                if (generatedIds.next()){
                    setId(generatedIds.getLong(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }


    @Override
    public AddressDAO edit() {
        String query = "update "+MySQLConnection.DATABASE+"."+MySQLConnection.TADDRESS+" set " +
                MySQLConnection.TADDRESS_NAME + " = ?, " +
                MySQLConnection.TADDRESS_DESCRIPTION + " = ?, " +
                MySQLConnection.TADDRESS_STREET + " = ?, " +
                MySQLConnection.TADDRESS_NUMBER + " = ?, " +
                MySQLConnection.TADDRESS_CITY + " = ?, " +
                MySQLConnection.TADDRESS_STATE + " = ?, " +
                MySQLConnection.TADDRESS_COUNTRY + " = ?,"+
                MySQLConnection.TADDRESS_CONTACT + " = ?"+
                " where " + MySQLConnection.TADDRESS_ID + " = ?";
        try {
            Connection connection = connect();
            try(PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(2, getName());
                statement.setString(3, getDescription());
                statement.setString(4, getStreet());
                statement.setInt(5, getNumber());
                statement.setString(6, getCity());
                statement.setString(7, getState());
                statement.setString(8, getCountry());
                statement.setObject(9, getContact());
                statement.executeUpdate();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return this;
    }

    private void setStatement(PreparedStatement statement) throws SQLException {
        statement.setString(1, getName());
        statement.setString(2, getDescription());
        statement.setString(3, getStreet());
        statement.setInt(4, getNumber());
        statement.setString(5, getCity());
        statement.setString(6, getState());
        statement.setString(7, getCountry());
        statement.setLong(8, getContact().getId());
    }

    @Override
    public AddressDAO findById() {
        String query = "select a.* from "+MySQLConnection.TADDRESS+" a where a."+MySQLConnection.TADDRESS_ID+" = ?";
        try{
            Connection connection = connect();
            try(PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setObject(1,getId());
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()){
                    setId(resultSet.getLong(1));
                    setName(resultSet.getString(2));
                    setDescription(resultSet.getString(3));
                    setStreet(resultSet.getString(4));
                    setNumber(resultSet.getInt(5));
                    setCity(resultSet.getString(6));
                    setState(resultSet.getString(7));
                    setCountry(resultSet.getString(8));
                    ContactDAO contactId = new ContactDAO();
                    contactId.setId(resultSet.getLong(9));
                    setContact(contactId.findById());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return this;
    }

    @Override
    public List<AddressDAO> findAll() {
        String query = "select * from "+MySQLConnection.TADDRESS;
        List<AddressDAO> addressesList = new ArrayList<>();
        try{
          Connection connection = connect();
          try(Statement statement = connection.createStatement()){
              ResultSet resultSet = statement.executeQuery(query);
              while (resultSet.next()){
                  AddressDAO address = new AddressDAO();
                  address.setId(resultSet.getLong(1));
                  address.setName(resultSet.getString(2));
                  address.setDescription(resultSet.getString(3));
                  address.setStreet(resultSet.getString(4));
                  address.setNumber(resultSet.getInt(5));
                  address.setCity(resultSet.getString(6));
                  address.setState(resultSet.getString(7));
                  address.setCountry(resultSet.getString(8));
                  ContactDAO contactId = new ContactDAO();
                  contactId.setId(resultSet.getLong(9));
                  address.setContact(contactId.findById());
                  addressesList.add(address);
              }
          }
        }catch (Exception e){
            e.printStackTrace();
        }
        return addressesList;
    }

    @Override
    public void delete() {
        String query = "delete from "+MySQLConnection.TADDRESS+" where "+MySQLConnection.TADDRESS_ID+" = ?";
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
