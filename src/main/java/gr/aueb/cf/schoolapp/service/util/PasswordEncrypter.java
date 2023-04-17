package gr.aueb.cf.schoolapp.service.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncrypter {

    private PasswordEncrypter() {}

    public static String hashPassword(String password) {
        int workload = 12;
        String salt = BCrypt.gensalt(workload);
        return BCrypt.hashpw(password, salt);
    }
}
