package ir.infocastle.test;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by admin on 5/31/2018.
 */

@Database(entities = {UserAnswer.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public  abstract UserAnswerDao getUserDao();
}
