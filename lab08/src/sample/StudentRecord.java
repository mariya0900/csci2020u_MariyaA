package sample;

public class StudentRecord {
    private String SID;
    private float assignments;
    private float midterm;
    private float finalExam;
    private float finalMark;
    private String letterGrade;

    public StudentRecord(String SID, float assignments, float midterm, float finalExam /*, float finalMark, String letterGrade*/){
        this.SID=SID;
        this.assignments=assignments;
        this.midterm=midterm;
        this.finalExam=finalExam;

    }

    public String getSID(){
        return SID;
    }
    public float getAssignments(){
        return assignments;
    }
    public float getMidterm(){
        return midterm;
    }
    public float getFinalExam(){
        return finalExam;
    }
    public void setFinalMark(){
        float finalMark=0.0f;
        finalMark+=assignments*0.2f;
        finalMark+=midterm*0.3f;
        finalMark+=finalExam*0.5f;
        this.finalMark=finalMark;
    }

    public float getFinalMark(){
        setFinalMark();
        return this.finalMark;
    }

    public void setLetterGrade() {
        if (finalMark>=80&&finalMark<=100){
            this.letterGrade="A";
        }
        else if (finalMark>=70&&finalMark<=79){
            this.letterGrade="B";
        }
        else if (finalMark>=60&&finalMark<=69){
             this.letterGrade="C";
         }
         else if (finalMark>=50&&finalMark<=59){
            this.letterGrade="D";
         }
         else if (finalMark>=0&&finalMark<=49){
            this.letterGrade="F";
        }
        else{
            this.letterGrade="Error: Invalid grade";
        }
    }

    public String getLetterGrade(){
        setLetterGrade();
        return this.letterGrade;
    }

}
