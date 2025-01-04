package codsoft.backend.services;

public interface UserService {

    void updateEmail(String oldMail, String newMail);
    void updatePassword(String Email, String newPassword);

}
