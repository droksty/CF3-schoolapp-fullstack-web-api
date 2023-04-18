package gr.aueb.cf.schoolapp.authentication.exceptions;

public class AuthenticationProviderException extends Exception {
    private final static long serialVersionUID = 1L;

    public AuthenticationProviderException(String s) {
        super(s);
    }
}
