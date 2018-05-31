package ir.infocastle.test;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by admin on 5/31/2018.
 */

@Dao
public interface UserDao {

    @Query("SELECT * FROM info")
    List<User> getAll();

    @Query("SELECT * FROM info WHERE uid IN (:userIds)")
     User loadAllByIds(int userIds);

    @Insert
    void insert(User... user);
}
