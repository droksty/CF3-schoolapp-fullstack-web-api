package gr.aueb.cf.schoolapp.service;

import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.dto.UserDTO;
import gr.aueb.cf.schoolapp.model.User;
import gr.aueb.cf.schoolapp.service.exceptions.UserNotFoundException;

import java.util.List;

public interface IUserService {

    /**
     * Inserts a {@link User} into the datasource.
     *
     * @param userToInsert
     *          DTO containing the new User data.
     * @return
     *          The inserted User instance.
     * @throws UserDAOException
     *          In case any DAO exception happens.
     */
    User insertUser(UserDTO userToInsert) throws UserDAOException;

    /**
     * Updates an existing {@link User}.
     *
     * @param userToUpdate
     *          DTO containing the updated User data.
     * @return
     *          The update instance of the {@link User}.
     * @throws UserDAOException
     *          In case any DAO exception happens.
     * @throws UserNotFoundException
     *          If User was not found in the datasource.
     */
    User updateUser(UserDTO userToUpdate) throws UserDAOException, UserNotFoundException;

    /**
     * Deletes a {@link User} from the datasource.
     *
     * @param id
     *          The id of the User to be deleted.
     * @throws UserDAOException
     *          In case any DAO exception happens.
     * @throws UserNotFoundException
     *          If User was not found in the datasource.
     */
    void deleteUser(int id) throws UserDAOException, UserNotFoundException;

    /**
     *
     * @return
     * @throws UserDAOException
     */
    List<User> getAllUsers() throws UserDAOException;
}
