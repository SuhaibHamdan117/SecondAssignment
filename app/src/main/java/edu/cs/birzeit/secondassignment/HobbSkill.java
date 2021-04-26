package edu.cs.birzeit.secondassignment;

public class HobbSkill {

    private String hobbies;
    private String skills;

    public HobbSkill() {
    }

    public HobbSkill(String hobbies, String skills) {
        this.hobbies = hobbies;
        this.skills = skills;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
}
