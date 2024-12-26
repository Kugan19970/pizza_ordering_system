package command;

import user.User;
import user.UserManager;

public class SubmitFeedbackCommand implements Command {
    private final String feedback;
    private final int rating;

    public SubmitFeedbackCommand(String feedback, int rating) {
        this.feedback = feedback;
        this.rating = rating;
    }

    @Override
    public void execute() {
        UserManager userManager = UserManager.getInstance();
        User loggedInUser = userManager.getLoggedInUser();

        if (loggedInUser != null) {
            // Simulate saving feedback
            System.out.println("Feedback from " + loggedInUser.getUsername() + ": " + feedback);
            System.out.println("Rating: " + rating);
            // In a real implementation, save this to a database or file
        } else {
            System.out.println("No user is logged in to submit feedback.");
        }
    }
}
