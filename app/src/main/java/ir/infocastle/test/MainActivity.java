package ir.infocastle.test;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.constraint.solver.Cache;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
   // private AppDatabase db;
    //private UserAnswerDao userDao;
    //UserAnswer userAnswer=new UserAnswer();

    AppViewModel mAppViewModel;
    boolean flag=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int aNumber=1;
        int TNumber=1;
        int QNumber=1;

        final EditText Answer=(EditText)findViewById(R.id.txt_Answer1);

        mAppViewModel= ViewModelProviders.of(this).get(AppViewModel.class);

            mAppViewModel.getQuestion(aNumber,TNumber,QNumber).observe(this, new Observer<UserAnswer>() {

                @Override
                public void onChanged(@Nullable UserAnswer userAnswer) {
                    // Update the cached copy of the userAnswer in the Activity.

                    if (userAnswer!=null)
                    {
                        flag=true;
                        Answer.setText(userAnswer.getAnswer());
                    }

                }
            });

       // Toast.makeText(this, Thread.currentThread().getName(), Toast.LENGTH_SHORT).show();



        findViewById(R.id.btn_Next1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserAnswer userAnswer=new UserAnswer();
                userAnswer.setAppointment(1);
                userAnswer.setTableNumber(1);
                userAnswer.setQuestionNumber(1);
                userAnswer.setAnswer(Answer.getText().toString());

                if(flag==false)
                    mAppViewModel.insert(userAnswer);
                else
                    mAppViewModel.update(userAnswer);

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
                finish();

        }
        });

        findViewById(R.id.btn_Skip1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
                finish();
            }
        });

        findViewById(R.id.btn_Delete1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

}




