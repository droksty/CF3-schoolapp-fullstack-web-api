package gr.aueb.cf.schoolapp.service;

import gr.aueb.cf.schoolapp.dao.IUserDAO;
import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.dto.UserDTO;
import gr.aueb.cf.schoolapp.model.User;
import gr.aueb.cf.schoolapp.service.exceptions.UserNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements IUserService {
    private IUserDAO userDAO;

    public UserServiceImpl(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }


    // Public API
    @Override
    public User insertUser(UserDTO userToInsert) throws UserDAOException {
        if (userToInsert == null) return null;

        try {
            User user = mapUser(userToInsert);
            return userDAO.insert(user);
        } catch (UserDAOException exception) {
            //exception.printStackTrace();
            throw exception;
        }
    }

    @Override
    public User updateUser(UserDTO userToUpdate) throws UserDAOException, UserNotFoundException {
        if (userToUpdate == null) return null;

        try {
            if (userDAO.getById(userToUpdate.getId()) == null) {
                throw new UserNotFoundException("User with id " + userToUpdate.getId() + " was not found.");
            }
            User user = mapUser(userToUpdate);
            return userDAO.update(user);
        } catch (UserDAOException | UserNotFoundException exception) {
            //exception.printStackTrace();
            throw exception;
        }
    }

    @Override
    public void deleteUser(int id) throws UserDAOException, UserNotFoundException {
        try {
            if (userDAO.getById(id) == null) {
                throw new UserNotFoundException("User with id " + id + " was not found.");
            }
            userDAO.delete(id);
        } catch (UserDAOException | UserNotFoundException exception) {
            //exception.printStackTrace();
            throw exception;
        }
    }

    @Override
    public List<User> getAllUsers() throws UserDAOException {
        List<User> userList = new ArrayList<>();

        try {
            userList = userDAO.getAll();
            return userList;
        } catch (UserDAOException exception) {
            //exception.printStackTrace();
            throw exception;
        }
    }


    //
    private User mapUser(UserDTO userDTO) {
        return new User(userDTO.getId(), userDTO.getUsername(), userDTO.getPassword());
    }
}
