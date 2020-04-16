package com.example.quickmath;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

public class regActivity extends AppCompatActivity {

    EditText regPass;
    EditText regFName;
    EditText regLName;
    EditText regUName;
    Button regBtn;

    SharedPreferences sp;
    SharedPreferences.Editor edit;

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
        setContentView(R.layout.activity_reg);

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


        regUName = findViewById(R.id.uNameRegTxt);
        regPass = findViewById(R.id.pRegTxt);
        regFName = findViewById(R.id.nameTxt);
        regLName = findViewById(R.id.lNameTxt);
        regBtn = findViewById(R.id.btnReg);


        regBtn.setOnClickListener(this::register);

    }

    private void register(View view) {

        String firstName = regFName.getText().toString().trim();
        String lastName = regLName.getText().toString().trim();
        String password = regPass.getText().toString().trim();
        String username = regUName.getText().toString().trim();



        if (firstName.isEmpty() == true && lastName.isEmpty() == true && password.isEmpty() == true && username.isEmpty() == true) {
            Toast.makeText(this, "Please Fill in All Fields", Toast.LENGTH_SHORT).show();
        }
        else {


                Child newChild = new Child(firstName, lastName, username, password, "Child");

                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("Users").add(newChild);

                Intent intent;
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);

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
