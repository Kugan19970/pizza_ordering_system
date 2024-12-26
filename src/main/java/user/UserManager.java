package user;

public class UserManager {
    private static UserManager instance;
    private User loggedInUser;

    private UserManager() {
        loggedInUser = null;
    }

    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void login(User user) {
        loggedInUser = user;
    }

    public void logout() {
        loggedInUser = null;
    }
}
