package ir.infocastle.test;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by admin on 5/31/2018.
 */

@Dao
public interface UserAnswerDao {

    @Query("SELECT * FROM userAnswerTable")
    LiveData<List<UserAnswer>> getAll();

    @Query("SELECT * FROM userAnswerTable WHERE " +
            "(appointment IN (:userAppointment)) AND (TableNumber IN (:userTableNumber)) AND (QuestionNumber IN (:userQuestionNumber))")
    LiveData<UserAnswer> getQuestion(int userAppointment, int userTableNumber, int userQuestionNumber);

    @Insert
    void insert(UserAnswer... answer);

    @Update
    void update(UserAnswer... answer);

    @Delete
    void delete(UserAnswer answer);

    @Query("DELETE FROM userAnswerTable")
    void deleteAll();

}
