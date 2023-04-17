package gr.aueb.cf.schoolapp.dao;

public class UserDAOImpl implements IUserDAO {


    @Override
    public boolean isUserValid(String username, String password) {
        return true;
    }
}
