package codsoft.backend.TestUnitaires;

import codsoft.backend.models.User;
import codsoft.backend.repositories.UserRepository;
import codsoft.backend.services.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UserTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User existingUser;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        existingUser = new User();
        existingUser.setId(1L);
        existingUser.setName("John Doe");
        existingUser.setEmail("old@example.com");
        existingUser.setPassword("oldPassword");
    }

    @Test
    public void testUpdateEmail() {
        String oldEmail = "old@example.com";
        String newEmail = "new@example.com";

        when(userRepository.findByEmail(oldEmail)).thenReturn(existingUser);

        userService.updateEmail(oldEmail, newEmail);

        assertEquals(newEmail, existingUser.getEmail());
        verify(userRepository).save(existingUser);
    }

    @Test
    public void testUpdatePassword() {
        String email = "old@example.com";
        String newPassword = "newPassword";

        when(userRepository.findByEmail(email)).thenReturn(existingUser);

        userService.updatePassword(email, newPassword);

        assertEquals(newPassword, existingUser.getPassword());
        verify(userRepository).save(existingUser);
    }

    @Test
    public void testUpdateEmail_UserNotFound() {
        String oldEmail = "nonexistent@example.com";
        String newEmail = "new@example.com";

        when(userRepository.findByEmail(oldEmail)).thenReturn(null);

        assertThrows(NullPointerException.class, () -> userService.updateEmail(oldEmail, newEmail));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    public void testUpdatePassword_UserNotFound() {
        String email = "nonexistent@example.com";
        String newPassword = "newPassword";

        when(userRepository.findByEmail(email)).thenReturn(null);

        assertThrows(NullPointerException.class, () -> userService.updatePassword(email, newPassword));
        verify(userRepository, never()).save(any(User.class));
    }
}

