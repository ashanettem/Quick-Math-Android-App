package com.example.quickmath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class createGame extends AppCompatActivity {

    RadioButton q5, q10, q15, qAdd, qSub, qMutl, qDiv;
    Button submit, back;
    EditText childEmail;
    

    FirebaseFirestore db;
    CollectionReference Games;

    private boolean mIsBound = false;
    private MusicService mServ;
    private ServiceConnection Scon =new ServiceConnection(){

        public void onServiceConnected(ComponentName name, IBinder
                binder) {
            mServ = ((MusicService.ServiceBinder)binder).getService();
        }

        public void onServiceDisconnected(ComponentName name) {
            mServ = null;
        }
    };

    void doBindService(){
        bindService(new Intent(this,MusicService.class),
                Scon, Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }

    void doUnbindService()
    {
        if(mIsBound)
        {
            unbindService(Scon);
            mIsBound = false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_create_game);

        doBindService();
        Intent music = new Intent();
        music.setClass(this, MusicService.class);
        startService(music);

        HomeWatcher mHomeWatcher;

        mHomeWatcher = new HomeWatcher(this);
        mHomeWatcher.setOnHomePressedListener(new HomeWatcher.OnHomePressedListener() {
            @Override
            public void onHomePressed() {
                if (mServ != null) {
                    mServ.pauseMusic();
                }
            }
            @Override
            public void onHomeLongPressed() {
                if (mServ != null) {
                    mServ.pauseMusic();
                }
            }
        });
        mHomeWatcher.startWatch();

        q5 = findViewById(R.id.q5);
        q10 = findViewById(R.id.q10);
        q15 = findViewById(R.id.q15);
        qAdd = findViewById(R.id.qAdd);
        qSub = findViewById(R.id.qSub);
        qMutl = findViewById(R.id.qMult);
        qDiv = findViewById(R.id.qDiv);
        submit = findViewById(R.id.subBtn);
        childEmail = findViewById(R.id.childEmail);
        back = findViewById(R.id.btnReturn);


        db = FirebaseFirestore.getInstance();
        Games = db.collection("Games");
        
        submit.setOnClickListener(this::CreateGame);
        back.setOnClickListener(this::returnToDash);
    }

    private void returnToDash(View view) {
        Intent i = new Intent(this, parentDash.class);
        startActivity(i);
    }

    private void CreateGame(View view) {

        Game newGame = new Game();
        newGame.setNumOfQuestions(5);
        if (q5.isChecked()) {
            if (qAdd.isChecked()) {
                newGame.setType("addition");
            } else if (qSub.isChecked()) {
                newGame.setType("subtract");
            } else if (qMutl.isChecked()) {
                newGame.setType("multiply");
            } else if (qDiv.isChecked()) {
                newGame.setType("divide");
            } else {
                Toast.makeText(this, "Please Select How Many \nQuestions You Wish To Do.", Toast.LENGTH_SHORT).show();
            }
            newGame.setState("incomplete");
            newGame.setChild(childEmail.getText().toString());
            newGame.setScore(0);
            Games.add(newGame);
            Toast.makeText(this, "Game Successfully Created.", Toast.LENGTH_SHORT).show();
        } else if (q10.isChecked()) {
            newGame.setNumOfQuestions(10);
            if (qAdd.isChecked()) {
                newGame.setType("addition");
            } else if (qSub.isChecked()) {
                newGame.setType("subtract");
            } else if (qMutl.isChecked()) {
                newGame.setType("multiply");
            } else if (qDiv.isChecked()) {
                newGame.setType("divide");
            } else {
                Toast.makeText(this, "Please Select How Many \nQuestions You Wish To Do.", Toast.LENGTH_SHORT).show();
            }
            newGame.setState("incomplete");
            newGame.setChild(childEmail.getText().toString());
            newGame.setScore(0);
            Games.add(newGame);
            Toast.makeText(this, "Game Successfully Created.", Toast.LENGTH_SHORT).show();
        } else if (q15.isChecked()) {
            newGame.setNumOfQuestions(15);
            if (qAdd.isChecked()) {
                newGame.setType("addition");
            } else if (qSub.isChecked()) {
                newGame.setType("subtract");
            } else if (qMutl.isChecked()) {
                newGame.setType("multiply");
            } else if (qDiv.isChecked()) {
                newGame.setType("divide");
            } else {
                Toast.makeText(this, "Please Select How Many \nQuestions You Wish To Do.", Toast.LENGTH_SHORT).show();
            }
            newGame.setState("incomplete");
            newGame.setChild(childEmail.getText().toString());
            newGame.setScore(0);
            Games.add(newGame);
            Toast.makeText(this, "Game Successfully Created.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please Select How Many \nQuestions You Wish To Do.", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mServ != null) {
            mServ.resumeMusic();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();

        PowerManager pm = (PowerManager)
                getSystemService(Context.POWER_SERVICE);
        boolean isScreenOn = false;
        if (pm != null) {
            isScreenOn = pm.isScreenOn();
        }

        if (!isScreenOn) {
            if (mServ != null) {
                mServ.pauseMusic();
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        doUnbindService();
        Intent music = new Intent();
        music.setClass(this,MusicService.class);
        stopService(music);

    }
}
