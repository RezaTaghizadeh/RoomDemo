package ir.infocastle.test;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by admin on 5/31/2018.
 */
//tableName = "userAnswerTable"
@Entity(primaryKeys = {"appointment", "TableNumber","QuestionNumber"},tableName = "userAnswerTable")
public class UserAnswer {
    //@PrimaryKey(autoGenerate = true)

    private int appointment;
    private int TableNumber;
    private int QuestionNumber;

    private String Answer;

    public int getAppointment() {
        return appointment;
    }

    public void setAppointment(int appointment) {
        this.appointment = appointment;
    }

    public int getTableNumber() {
        return TableNumber;
    }

    public void setTableNumber(int tableNumber) {
        TableNumber = tableNumber;
    }

    public int getQuestionNumber() {
        return QuestionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        QuestionNumber = questionNumber;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }
}
