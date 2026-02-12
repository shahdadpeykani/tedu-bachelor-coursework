import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.example.DatabaseConnector;
import org.example.NotificationService;
import org.example.UserService;
import org.junit.Before;
import org.junit.Test;

public class UserServiceTest {

    private UserService userService;
    private DatabaseConnector mockDatabaseConnector;
    private NotificationService mockNotificationService;

    @Before
    public void setUp() {
        // Initializes the mocks and the UserService before each test.
        mockDatabaseConnector = mock(DatabaseConnector.class);
        mockNotificationService = mock(NotificationService.class);
        userService = new UserService(mockDatabaseConnector, mockNotificationService);
    }

    @Test
    public void testRegisterUserSuccess() {
        //The user does not exist, and the registration is successful.
        when(mockDatabaseConnector.userExists("john_doe")).thenReturn(false);
        when(mockDatabaseConnector.saveUser("john_doe", "password123", "john@example.com")).thenReturn(true);

        boolean result = userService.registerUser("john_doe", "password123", "john@example.com");

        assertTrue(result);
        verify(mockDatabaseConnector).userExists("john_doe");
        verify(mockDatabaseConnector).saveUser("john_doe", "password123", "john@example.com");
        verify(mockNotificationService).sendEmail("john@example.com", "Welcome to the system!");
    }

    @Test
    public void testRegisterUserDuplicate() {
        //The username already exists, so registration fails.
        when(mockDatabaseConnector.userExists("john_doe")).thenReturn(true);

        boolean result = userService.registerUser("john_doe", "password123", "john@example.com");

        assertFalse(result);
        verify(mockDatabaseConnector).userExists("john_doe");
        verifyNoMoreInteractions(mockNotificationService);
    }

    @Test
    public void testAuthenticateUserSuccess() {
        //The provided credentials match the stored password.
        when(mockDatabaseConnector.getPassword("john_doe")).thenReturn("password123");

        boolean result = userService.authenticateUser("john_doe", "password123");

        assertTrue(result);
        verify(mockDatabaseConnector).getPassword("john_doe");
    }

    @Test
    public void testAuthenticateUserFailure() {
        //The username does not exist or the password is incorrect.
        when(mockDatabaseConnector.getPassword("john_doe")).thenReturn(null);

        boolean result = userService.authenticateUser("john_doe", "password123");

        assertFalse(result);
        verify(mockDatabaseConnector).getPassword("john_doe");
    }

    @Test
    public void testUpdateUserEmailSuccess() {
        //The email update operation is successful.
        when(mockDatabaseConnector.updateEmail("john_doe", "new_email@example.com")).thenReturn(true);

        boolean result = userService.updateUserEmail("john_doe", "new_email@example.com");

        assertTrue(result);
        verify(mockDatabaseConnector).updateEmail("john_doe", "new_email@example.com");
        verify(mockNotificationService).sendEmail("new_email@example.com", "Your email has been updated.");
    }
}