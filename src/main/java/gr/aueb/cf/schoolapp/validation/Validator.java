package gr.aueb.cf.schoolapp.validation;

import gr.aueb.cf.schoolapp.dto.TeacherDTO;
import gr.aueb.cf.schoolapp.dto.UserDTO;

public class Validator {
    private Validator() {}

    public static String validate(TeacherDTO dto) {
        if (dto.getFirstname().equals(""))  {
            return "Firstname: Empty";
        }

        if ((dto.getLastname().equals(""))) {
            return "Lastname: Empty";
        }

        return "";
    }

    //
    public static String validate(UserDTO userDTO) {
        if (userDTO.getUsername().equals("")) return "username: empty";
        if (userDTO.getPassword().length() < 8) return "password must be at least 8 characters long";

        // πρόσθεσε έλεγχο που καλεί το service και με τη σειρά του καλει το dao να ελέγξει αν υπάρχει ήδη το username

        return "";
    }
}
