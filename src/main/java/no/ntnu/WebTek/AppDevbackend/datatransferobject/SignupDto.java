package no.ntnu.WebTek.AppDevbackend.datatransferobject;

/**
 *
 */
public class SignupDto {

    private final String username;
    private final String password;

    public SignupDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
