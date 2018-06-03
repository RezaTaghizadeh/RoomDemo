package ir.infocastle.test;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

/**
 * Created by admin on 5/31/2018.
 */

@Database(entities = {UserAnswer.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public  abstract UserAnswerDao userDao();

    private static AppDatabase INSTANCE;

    static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "App_database")
                            .addCallback(sAppDatabaseCallback)
                            .build();
                }
            }

        }

        return INSTANCE;
    }

    private static AppDatabase.Callback sAppDatabaseCallback = new AppDatabase.Callback(){
        @Override
        public void onOpen (@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final UserAnswerDao mDao;

        PopulateDbAsync(AppDatabase db) {
            mDao = db.userDao();
        }

            @Override
            protected Void doInBackground(final Void... params) {
                mDao.deleteAll();

               /* UserAnswer userAnswer=new UserAnswer();
                userAnswer.setAppointment(1);
                userAnswer.setTableNumber(1);
                userAnswer.setQuestionNumber(1);
                userAnswer.setAnswer("reza is good");

                mDao.insert(userAnswer);*/

                return null;
            }

    }
}
