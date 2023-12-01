package EffortLoggerV2;

public class TeamMember {
    private String memberID;
    private String name;
    private String role;

    public TeamMember(String memberID, String name, String role) {
        this.memberID = memberID;
        this.name = name;
        this.role = role;
    }

    public void vote(Task task, PlanningPokerCard selectedCard) {
        //team voting
        task.addVote(this, selectedCard);
    }

    public void viewTask(Task task) {
        //viewing task for team member
        //display task details
    }
}