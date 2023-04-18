package gr.aueb.cf.schoolapp.dao;

import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.model.User;
import gr.aueb.cf.schoolapp.service.exceptions.UserNotFoundException;
import gr.aueb.cf.schoolapp.service.util.DBUtil;
import gr.aueb.cf.schoolapp.service.util.PasswordEncrypter;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements IUserDAO {

    @Override
    public User insert(User user) throws UserDAOException {
        String query = "INSERT INTO USERS (USERNAME, PASSWORD) VALUES (?, ?)";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preStmt = connection.prepareStatement(query)) {

            String username = user.getUsername();
            String password = user.getPassword();

            preStmt.setString(1, username);
            preStmt.setString(2, PasswordEncrypter.hashPassword(password));
            preStmt.executeUpdate();
            return user;
        } catch (SQLException | ClassNotFoundException e) {
            //e.printStackTrace();
            throw new UserDAOException("SQL Error in User " + user + " insertion");
        }
    }

    @Override
    public User update(User user) throws UserDAOException {
        String query = "UPDATE USERS SET USERNAME = ?, PASSWORD = ? WHERE ID = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preStmt = connection.prepareStatement(query)) {

            int id = user.getId();
            String username = user.getUsername();
            String password = user.getPassword();

            preStmt.setString(1, username);
            preStmt.setString(2, PasswordEncrypter.hashPassword(password));
            preStmt.setInt(3, id);
            preStmt.executeUpdate();
            return user;
        } catch (SQLException | ClassNotFoundException e) {
            //e.printStackTrace();
            throw new UserDAOException("SQL Error in User " + user.getUsername() + " update");
        }
    }

    @Override
    public void delete(int id) throws UserDAOException {
        String query = "DELETE FROM USERS WHERE ID = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preStmt = connection.prepareStatement(query)) {
            preStmt.setInt(1, id);
            preStmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            //e.printStackTrace();
            throw new UserDAOException("SQL Error in User with id = " + id  + " delete");
        }
    }

    @Override
    public List<User> getAll() throws UserDAOException {
        String query = "SELECT * FROM USERS";
        ResultSet rs;
        List<User> userList = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
            PreparedStatement preStmt = connection.prepareStatement(query)) {

            rs = preStmt.executeQuery();
            while (rs.next()) {
                User user = new User(
                        rs.getInt("ID"),
                        rs.getString("USERNAME"),
                        rs.getString("PASSWORD")
                );
                userList.add(user);
            }
            return userList;
        } catch (SQLException | ClassNotFoundException e) {
            //e.printStackTrace();
            throw new UserDAOException("SQL Error in User select");
        }
    }

    @Override
    public User getByUsername(String username) throws UserDAOException {
        User user = null;
        ResultSet rs;
        String query = "SELECT ID, USERNAME, PASSWORD FROM USERS WHERE USERNAME = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preStmt = connection.prepareStatement(query)) {

            preStmt.setString(1, username);
            rs = preStmt.executeQuery();

            if (rs.next()) {
                user = new User(
                    rs.getInt("ID"),
                    rs.getString("USERNAME"),
                    rs.getString("PASSWORD")
                );
            }
            return user;
        } catch (SQLException | ClassNotFoundException e) {
            //e.printStackTrace();
            throw new UserDAOException("SQL Error in User with username " + username + " select");
        }
    }

    @Override
    public User getById(int id) throws UserDAOException {
        User user = null;
        ResultSet rs;
        String query = "SELECT ID, USERNAME, PASSWORD FROM USERS WHERE ID = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preStmt = connection.prepareStatement(query)) {

            preStmt.setInt(1, id);
            rs = preStmt.executeQuery();

            if (rs.next()) {
                user = new User(
                        rs.getInt("ID"),
                        rs.getString("USERNAME"),
                        rs.getString("PASSWORD")
                );
            }
            return user;
        } catch (SQLException | ClassNotFoundException e) {
            //e.printStackTrace();
            throw new UserDAOException("SQL Error in User with id " + id + " select");
        }
    }

    @Override
    public boolean isUserValid(String username, String password) throws UserDAOException {
        String query = "SELECT PASSWORD FROM USERS WHERE USERNAME = ?";
        String hashedPassword = null;
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preStmt = connection.prepareStatement(query)) {

            preStmt.setString(1, username);
            ResultSet rs = preStmt.executeQuery();

            return rs.next() && BCrypt.checkpw(password, rs.getString("PASSWORD"));
        } catch (SQLException | ClassNotFoundException e) {
            //e.printStackTrace();
            throw new UserDAOException("SQL Error in User with username " + username + " authentication");
        }
    }



}
