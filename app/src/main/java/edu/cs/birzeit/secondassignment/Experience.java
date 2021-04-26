package edu.cs.birzeit.secondassignment;

public class Experience {

    private String work;
    private String edu;

    public Experience() {
    }

    public Experience(String work, String edu) {
        this.work = work;
        this.edu = edu;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getEdu() {
        return edu;
    }

    public void setEdu(String edu) {
        this.edu = edu;
    }
}
