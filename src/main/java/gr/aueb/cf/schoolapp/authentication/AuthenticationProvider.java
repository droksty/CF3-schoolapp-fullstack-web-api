package gr.aueb.cf.schoolapp.authentication;

import gr.aueb.cf.schoolapp.authentication.exceptions.AuthenticationProviderException;
import gr.aueb.cf.schoolapp.dao.IUserDAO;
import gr.aueb.cf.schoolapp.dao.UserDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.dto.UserDTO;
import gr.aueb.cf.schoolapp.model.User;

public class AuthenticationProvider {

    private static final IUserDAO userDAO = new UserDAOImpl();

    private AuthenticationProvider() {}

    public static User authenticate(UserDTO userDTO) throws AuthenticationProviderException {
        try {
            return !userDAO.isUserValid(userDTO.getUsername(), userDTO.getPassword())
                    ? null
                    : new User(userDTO.getId(), userDTO.getUsername(), userDTO.getPassword());
        } catch (UserDAOException e) {
            //e.printStackTrace();
            throw new AuthenticationProviderException("Error in Authentication for user: " + userDTO.getUsername());
        }
    }
}
