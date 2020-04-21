package com.example.quickmath;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.core.Query;

public class ChildProfile extends AppCompatActivity {

    ImageView currentAvatar, a1, a2, a3, a4, a5, a6;
    Button btnHome;
    TextView txtCurrentUser, txtGamesCompleted;
    SharedPreferences sp;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference gamesDB = db.collection("Games");
    int documentCounter = 0;


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

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_child_profile);

        currentAvatar = findViewById(R.id.ivCurrentSticker);
        a1 = findViewById(R.id.iv1);
        a2 = findViewById(R.id.iv2);
        a3 = findViewById(R.id.iv3);
        a4 = findViewById(R.id.iv4);
        a5 = findViewById(R.id.iv5);
        a6 = findViewById(R.id.iv6);
        btnHome = findViewById(R.id.btnProfileHome);
        txtCurrentUser = findViewById(R.id.txtProfileUsername);
        txtGamesCompleted = findViewById(R.id.gamesCompleted);

        sp = getSharedPreferences("currentUser", 0);





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



        String string = sp.getString("User", "User");
        gamesDB.whereEqualTo("child", string).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        documentCounter++;
                        txtGamesCompleted.setText(Integer.toString(documentCounter));
                        checkCoins(documentCounter);

                }
            }
        });




        btnHome.setOnClickListener(this::backToChoices);

        txtCurrentUser.setText(sp.getString("User", "User"));
        a1.setOnClickListener(this::setAvatar);
        a2.setOnClickListener(this::setAvatar);
        a3.setOnClickListener(this::setAvatar);
        a4.setOnClickListener(this::setAvatar);
        a5.setOnClickListener(this::setAvatar);
        a6.setOnClickListener(this::setAvatar);




    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void checkCoins(int n) {

        ImageView av1, av2, av3, av4, av5, av6;

        av1 = findViewById(R.id.iv1);
        av2 = findViewById(R.id.iv2);
        av3 = findViewById(R.id.iv3);
        av4 = findViewById(R.id.iv4);
        av5 = findViewById(R.id.iv5);
        av6 = findViewById(R.id.iv6);



        if (n >= 30){
            av1.setForeground(null);
            av2.setForeground(null);
            av3.setForeground(null);
            av4.setForeground(null);
            av5.setForeground(null);
            av6.setForeground(null);
        }
        else if(n < 30 && n >=25){
            av1.setForeground(null);
            av2.setForeground(null);
            av3.setForeground(null);
            av4.setForeground(null);
            av5.setForeground(null);
        }
        else if (n < 25 && n >=20){
            av1.setForeground(null);
            av2.setForeground(null);
            av3.setForeground(null);
            av4.setForeground(null);
        }
        else if (n < 20 && n >=15){
            av1.setForeground(null);
            av2.setForeground(null);
            av3.setForeground(null);
        }
        else if (n < 15 && n >=10){
            av1.setForeground(null);
            av2.setForeground(null);
        }
        else {
            av1.setForeground(null);
        }
    }

     private void setAvatar(View view) {

        String s = (String) txtGamesCompleted.getText();
        int n = Integer.parseInt(s);


        switch (view.getId()) {
            case R.id.iv1:
                if (n >= 5) {
                    currentAvatar.setImageResource(R.drawable.b);

                }
                else {
                    Toast.makeText(this, "You need 5 coins to unlock.",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.iv2:
                if (n >= 10) {
                    currentAvatar.setImageResource(R.drawable.c);
                }
                else {

                Toast.makeText(this, "You need 10 coins to unlock.",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.iv3:
                if (n >= 15) {
                    currentAvatar.setImageResource(R.drawable.d);
                }
                else {
                    Toast.makeText(this, "You need 15 coins to unlock.",Toast.LENGTH_LONG).show();
                }                break;
            case R.id.iv4:
                if (n >= 20) {
                    currentAvatar.setImageResource(R.drawable.e);
                }
                else {
                    Toast.makeText(this, "You need 20 coins to unlock.",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.iv5:
                if (n >= 25) {
                    currentAvatar.setImageResource(R.drawable.f);
                }
                else {
                    Toast.makeText(this, "You need 25 coins to unlock.",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.iv6:
                if (n >= 30) {
                    currentAvatar.setImageResource(R.drawable.g);
                }
                else {
                    Toast.makeText(this, "You need 30 coins to unlock.",Toast.LENGTH_LONG).show();
                }
                break;
            default:
                Toast.makeText(ChildProfile.this, "Please Complete More Games \n To earn more coins.", Toast.LENGTH_LONG).show();
        }

    }

    private void backToChoices(View view) {

        Intent i = new Intent(this, choices.class);
        startActivity(i);

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
