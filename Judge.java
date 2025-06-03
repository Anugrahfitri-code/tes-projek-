package model;

public class Judge extends EventUser {

    public Judge(String username, String password) {
        super(username, password);
    }

    public void submitIdea(String title, String description) {
        submittedIdeas.add(new Idea(title, description, this));
    }

    public void voteIdea(Idea idea) {
        idea.addVote();
    }

    @Override
    public String getRole() {
        return "Judge";
    }
    System.out.println("Loading content for user: " + user.getUsername() + " (Role: " + user.getRole() + ")");
if (user instanceof Participant) {
    System.out.println("User is a Participant. Building participant UI.");
} else if (user instanceof Judge) {
    System.out.println("User is a Judge. Building judge UI.");
} else {
    System.out.println("User role is unrecognized.");
}
} 
