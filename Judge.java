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
} 
