package org.example;

public class UserService {
    private DatabaseConnector databaseConnector;
    private NotificationService notificationService;

    public UserService(DatabaseConnector databaseConnector, NotificationService notificationService) {
        this.databaseConnector = databaseConnector;
        this.notificationService = notificationService;
    }

    public boolean registerUser(String username, String password, String email) {
        if (databaseConnector.userExists(username)) {
            return false; // Duplicate user
        }
        boolean isSaved = databaseConnector.saveUser(username, password, email);
        if (isSaved) {
            notificationService.sendEmail(email, "Welcome to the system!");
        }
        return isSaved;
    }

    //Authenticates a user by verifying their credentials.
    public boolean authenticateUser(String username, String password) {
        String storedPassword = databaseConnector.getPassword(username);
        return storedPassword != null && storedPassword.equals(password);
    }

    public boolean updateUserEmail(String username, String newEmail) {
        boolean isUpdated = databaseConnector.updateEmail(username, newEmail);
        if (isUpdated) {
            notificationService.sendEmail(newEmail, "Your email has been updated.");
        }
        return isUpdated;
    }
}
