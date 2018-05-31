package ir.infocastle.test;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private AppDatabase db;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = Room.databaseBuilder(this, AppDatabase.class, "info")
                .build();

        Toast.makeText(this, Thread.currentThread().getName(), Toast.LENGTH_SHORT).show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, Thread.currentThread().getName(), Toast.LENGTH_SHORT).show();
                addData();
            }
        }).start();

        userDao = db.getUserDao();


        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        List<User> users = userDao.getAll();
                        for (User user : users) {
                            Toast.makeText(getApplication(), user.getFirstName(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }).start();

//                Log.d(TAG, "onClick: "+userShow.getFirstName());
//                Log.d(TAG, "onClick: "+userShow.getLastName());
            }
        });


    }


    public void addData() {

        User user = new User();
        user.setUid(7);
        user.setFirstName("ramin");
        user.setLastName("gholami");

        userDao.insert(user);

    }

}




