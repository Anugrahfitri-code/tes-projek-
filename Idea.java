package model;

public class Idea {
    private String title;
    private String description;
    private int votes;
    private Participant creator;

    public Idea(String title, String description, Participant creator) {
        this.title = title;
        this.description = description;
        this.creator = creator;
        this.votes = 0;
    }
    public setDescription(){
        this.description = 0;
    }    

    public void addVote() {
        votes++;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getVotes() {
        return votes;
    }

    public Participant getCreator() {
        return creator;
    }
}
