package gr.aueb.cf.schoolapp.dao;

import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.model.User;
import gr.aueb.cf.schoolapp.service.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements IUserDAO {

    @Override
    public User insert(User user) throws UserDAOException {
        String insertQuery = "INSERT INTO USERS (USERNAME, PASSWORD) VALUES (?, ?)";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(insertQuery)) {

            String username = user.getUsername();
            String password = user.getPassword();

            statement.setString(1, username);
            statement.setString(2, password);
            statement.executeUpdate();
            return user;
        } catch (SQLException | ClassNotFoundException exception) {
            //exception.printStackTrace();
            throw new UserDAOException("SQL Error in User " + user + " insertion");
        }
    }

    @Override
    public User update(User user) throws UserDAOException {
        String updateQuery = "UPDATE USERS SET USERNAME = ?, PASSWORD = ? WHERE ID = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(updateQuery)) {

            int id = user.getId();
            String username = user.getUsername();
            String password = user.getPassword();

            statement.setString(1, username);
            statement.setString(2, password);
            statement.setInt(3, id);
            statement.executeUpdate();
            return user;
        } catch (SQLException | ClassNotFoundException exception) {
            //exception.printStackTrace();
            throw new UserDAOException("SQL Error in User " + user.getUsername() + " update");
        }
    }

    @Override
    public void delete(int id) throws UserDAOException {
        String deleteQuery = "DELETE FROM USERS WHERE ID = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException exception) {
            //exception.printStackTrace();
            throw new UserDAOException("SQL Error in User with id = " + id  + " delete");
        }
    }

    @Override
    public List<User> getAll() throws UserDAOException {
        String selectQuery = "SELECT * FROM USERS";
        ResultSet resultSet;
        List<User> userList = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(selectQuery)) {

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getInt("ID"),
                        resultSet.getString("USERNAME"),
                        resultSet.getString("PASSWORD")
                );
                userList.add(user);
            }
            return userList;
        } catch (SQLException | ClassNotFoundException exception) {
            //exception.printStackTrace();
            throw new UserDAOException("SQL Error in User select");
        }
    }

    @Override
    public User getByUsername(String username) throws UserDAOException {
        User user = null;
        ResultSet resultSet;
        String selectQuery = "SELECT ID, USERNAME, PASSWORD FROM USERS WHERE USERNAME = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectQuery)) {

            statement.setString(1, username);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = new User(
                    resultSet.getInt("ID"),
                    resultSet.getString("USERNAME"),
                    resultSet.getString("PASSWORD")
                );
            }
            return user;
        } catch (SQLException | ClassNotFoundException exception) {
            //exception.printStackTrace();
            throw new UserDAOException("SQL Error in User with username " + username + " select");
        }
    }

    @Override
    public User getById(int id) throws UserDAOException {
        User user = null;
        ResultSet resultSet;
        String selectQuery = "SELECT ID, USERNAME, PASSWORD FROM USERS WHERE ID = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectQuery)) {

            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = new User(
                        resultSet.getInt("ID"),
                        resultSet.getString("USERNAME"),
                        resultSet.getString("PASSWORD")
                );
            }
            return user;
        } catch (SQLException | ClassNotFoundException exception) {
            //exception.printStackTrace();
            throw new UserDAOException("SQL Error in User with id " + id + " select");
        }
    }

    @Override
    public boolean isUserValid(String username, String password) {

        // Replace with jbcrypt code
        return true;
    }
}
