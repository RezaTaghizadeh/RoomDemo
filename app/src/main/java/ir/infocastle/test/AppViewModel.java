package ir.infocastle.test;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

/**
 * Created by admin on 6/2/2018.
 */

public class AppViewModel extends AndroidViewModel {

    private AppRepository mRepository;
    //private LiveData<List<UserAnswer>> mGetAll;

    private LiveData<UserAnswer> mGetQuestion;

    public AppViewModel(Application application){
        super(application);

        mRepository = new AppRepository(application);
        //mGetAll=mRepository.getAll();
    }

    //LiveData<List<UserAnswer>> getAll() { return mGetAll; }

    LiveData<UserAnswer> getQuestion(int userAppointment,int userTableNumber,int userQuestionNumber){
        mGetQuestion= mRepository.getQuestion(userAppointment,userTableNumber,userQuestionNumber);
        return mGetQuestion;
    }

    public void insert(UserAnswer userAnswer) { mRepository.insert(userAnswer); }

    public void update(UserAnswer userAnswer) {mRepository.update(userAnswer);}

}
