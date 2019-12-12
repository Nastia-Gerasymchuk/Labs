package main.dao;

import main.connection.ConnectionWeb;
import main.exceptions.DaoException;
import main.exceptions.DatabaseConnectionException;
import main.model.Category;
import main.model.Person;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class PersonDao implements Dao<Person> {
    private static final Connection connection = ConnectionWeb.getInstance().getConnection();
    protected final String FIND_BY_ID="SELECT * " +
                                    "FROM persons " +
                                    "WHERE id_pers=?;";
    protected final String SELECT_ALL="SELECT * " +
                                    "FROM persons; ";
    private final String INSERT_PERSONS="INSERT INTO persons(pers_fname, pers_lname, pers_tname, address,birthday, login, pasw) VALUES(?,?,?,?,?,?,?)";
    private final String UPDATE_PERSONS="UPDATE persons " +
                                        "SET address=? "+
                                        "WHERE id_pers=?;";
    private final String DELETE_FROM_PERSONS="DELETE FROM persons " +
                                              "WHERE id_pers=?;";

    private Person fromResultSet(ResultSet resultSet) throws SQLException {
        //Date date=resultSet.getDate("birthday");
        Person person = new Person.PersonBuilder()
                .setName(resultSet.getString("pers_fname"))
                .setSurname(resultSet.getString("pers_lname"))
                .setFathername(resultSet.getString("pers_tname"))
                .setAddress(resultSet.getString("address"))
//                .setLogin(resultSet.getString("login"))
//                .setPasw(resultSet.getString("pasw"))
                .setIdPerson(resultSet.getInt("id_pers"))
                .setYearBorn(resultSet.getObject("birthday",LocalDate.class))
                .setLogin(resultSet.getString("login"))
                .setPasw(resultSet.getString("pasw"))
                .build();
        return person;

    }
    @Override
    public Optional findById(int id) throws DaoException, DatabaseConnectionException, SQLException {
        try(PreparedStatement preparedStatement=connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setInt(1,id);
            ResultSet resultSet=preparedStatement.executeQuery();
            resultSet.next();
            return Optional.ofNullable(fromResultSet(resultSet));
        }
    }

    @Override
    public List<Person> findAll() throws DaoException, DatabaseConnectionException, SQLException {
        List<Person> personList=new LinkedList<Person>();
        try(PreparedStatement preparedStatement=connection.prepareStatement(SELECT_ALL)) {
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                personList.add(fromResultSet(resultSet));
            }
            return personList;
        }
    }

    @Override
    public void insert(Person person) throws DaoException, DatabaseConnectionException, SQLException {
        try(PreparedStatement preparedStatement=connection.prepareStatement(INSERT_PERSONS)) {
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//            String dateInString = person.getYearBorn().toString();
            //preparedStatement.setObject(1, localDate);
          //  st.executeUpdate(); st.close();
           // preparedStatement.setInt(1,person.getIdPerson());
            preparedStatement.setString(1,person.getSurname());
            preparedStatement.setString(2,person.getName());
            preparedStatement.setString(3,person.getFathername());
            preparedStatement.setString(4,person.getAddress());
            preparedStatement.setObject(5, person.getYearBorn());
            //preparedStatement.setDate(6, (Date) formatter.parse(dateInString));
            preparedStatement.setString(6,person.getLogin());
            preparedStatement.setString(7,person.getPasw());
            preparedStatement.executeUpdate();

//        } catch (ParseException e) {
//            e.printStackTrace();
        }
    }

    @Override
    public void update(Person person) throws DaoException, DatabaseConnectionException, SQLException {
        try(PreparedStatement preparedStatement=connection.prepareStatement(UPDATE_PERSONS)) {
            preparedStatement.setString(1,person.getAddress());
            preparedStatement.setInt(2,person.getIdPerson());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws DaoException, DatabaseConnectionException, SQLException {
        try(PreparedStatement preparedStatement=connection.prepareStatement(DELETE_FROM_PERSONS)) {
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        }
    }
}
