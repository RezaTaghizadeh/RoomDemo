package ir.infocastle.test;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private AppDatabase db;
    private UserAnswerDao userDao;
    UserAnswer userAnswer=new UserAnswer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = Room.databaseBuilder(this, AppDatabase.class, "userAnswerTable")
                .allowMainThreadQueries()
                .build();
        userDao = db.getUserDao();

       // Toast.makeText(this, Thread.currentThread().getName(), Toast.LENGTH_SHORT).show();

        final EditText appointment=(EditText)findViewById(R.id.txt_appointment);
        final EditText TableNumber=(EditText)findViewById(R.id.txt_TableNumber);
        final EditText QuestionNumber=(EditText)findViewById(R.id.txt_QuestionNumber);
        final EditText Answer=(EditText)findViewById(R.id.txt_Answer);
        final TextView ShowAnswer=(TextView)findViewById(R.id.txt_show);

        findViewById(R.id.btn_Show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int aNumber= Integer.parseInt(appointment.getText().toString());
                int TNumber=Integer.parseInt(TableNumber.getText().toString());
                int QNumber=Integer.parseInt(QuestionNumber.getText().toString());

                ShowAnswer.setText("");

                userAnswer=null;
                userAnswer= userDao.getQuestion(aNumber,TNumber,QNumber);

                if(userAnswer!=null) {
                   ShowAnswer.setText(userAnswer.getAnswer());
                }
                else
                    Toast.makeText(getApplication(), "موجود نیست", Toast.LENGTH_SHORT).show();


            }
        });


        findViewById(R.id.btn_Register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                int aNumber= Integer.parseInt(appointment.getText().toString());
                int TNumber=Integer.parseInt(TableNumber.getText().toString());
                int QNumber=Integer.parseInt(QuestionNumber.getText().toString());

                if(userDao.getQuestion(aNumber,TNumber,QNumber)==null){

                    UserAnswer userAnswer0=new UserAnswer();
                    userAnswer0.setAppointment(aNumber);
                    userAnswer0.setTableNumber(TNumber);
                    userAnswer0.setQuestionNumber(QNumber);
                    userAnswer0.setAnswer(Answer.getText().toString());

                    userDao.insert(userAnswer0);

                    Answer.setText("");

                }
                else {
                   Toast.makeText(getApplication(), "موجود است", Toast.LENGTH_SHORT).show();

                }

               // List<UserAnswer> users = userDao.getAll();
                        /*for (UserAnswer user : users) {
                            Toast.makeText(getApplication(), user.getFirstName(), Toast.LENGTH_SHORT).show();

                    }*/

//                Log.d(TAG, "onClick: "+userShow.getFirstName());
//                Log.d(TAG, "onClick: "+userShow.getLastName());
            }
        });


    }

}




