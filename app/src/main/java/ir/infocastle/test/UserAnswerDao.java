package ir.infocastle.test;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by admin on 5/31/2018.
 */

@Dao
public interface UserAnswerDao {

    @Query("SELECT * FROM userAnswerTable WHERE " +
            "(appointment IN (:userAppointment)) AND (TableNumber IN (:userTableNumber))")
    List<UserAnswer> getAll(int userAppointment,int userTableNumber );

    @Query("SELECT * FROM userAnswerTable WHERE " +
            "(appointment IN (:userAppointment)) AND (TableNumber IN (:userTableNumber)) AND (QuestionNumber IN (:userQuestionNumber))")
    UserAnswer getQuestion(int userAppointment,int userTableNumber,int userQuestionNumber);

    @Insert
    void insert(UserAnswer... answer);
}
