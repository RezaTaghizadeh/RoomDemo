package ir.infocastle.test;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

/**
 * Created by admin on 6/2/2018.
 */

public class AppRepository {

    private UserAnswerDao mUserAnswerDao;

    private LiveData<List<UserAnswer>> mGetAll;
    private LiveData<UserAnswer> mGetQuestion;

    AppRepository(Application application ){
        AppDatabase db= AppDatabase.getDatabase(application);
        mUserAnswerDao= db.userDao();
        //mGetAll=mUserAnswerDao.getAll( );
    }

    //LiveData<List<UserAnswer>> getAll() {return mGetAll;}

    LiveData<UserAnswer> getQuestion(int userAppointment,int userTableNumber,int userQuestionNumber){
        return mUserAnswerDao.getQuestion(userAppointment,userTableNumber,userQuestionNumber);
    }

    public void insert (UserAnswer userAnswer) {
        new insertAsyncTask(mUserAnswerDao).execute(userAnswer);
    }

    public void update(UserAnswer userAnswer){
        new updateAsyncTask(mUserAnswerDao).execute(userAnswer);
    }

    private static class insertAsyncTask extends AsyncTask<UserAnswer, Void, Void> {

        private UserAnswerDao mAsyncTaskDao;
        insertAsyncTask(UserAnswerDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final UserAnswer... params){
            mAsyncTaskDao.insert(params[0]);
            return null;
        }

    }

    private static class updateAsyncTask  extends AsyncTask<UserAnswer, Void, Void>{

        private UserAnswerDao mAsyncTaskDao;
        updateAsyncTask(UserAnswerDao dao){mAsyncTaskDao=dao;}

        @Override
        protected Void doInBackground(final UserAnswer... params){
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }

}
