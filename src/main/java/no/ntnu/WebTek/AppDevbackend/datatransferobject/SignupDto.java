package no.ntnu.WebTek.AppDevbackend.datatransferobject;

/**
 *
 */
public class SignupDto {

    private final String username;
    private final String email;
    private final String password;

    public SignupDto(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
