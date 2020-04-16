package com.example.quickmath;

import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.RadioButton;
import android.widget.Toast;

public class choices extends AppCompatActivity {

    String email;
    RadioButton q5, q10, q15, easy, medium, hard;
    SharedPreferences sp;
    SharedPreferences.Editor spEdit;
    Button add,subtract,multiply,divide,logOut;


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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_choices);

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

        email = getIntent().getStringExtra("User");
        q5 = findViewById(R.id.q5);
        q10 = findViewById(R.id.q10);
        q15 = findViewById(R.id.q15);
        easy = findViewById(R.id.btnEasy);
        medium = findViewById(R.id.btnMedium);
        hard = findViewById(R.id.btnHard);

        sp = getSharedPreferences("SP", MODE_PRIVATE);
        spEdit = sp.edit();

        add = findViewById(R.id.add_button);
        subtract = findViewById(R.id.subtract_button);
        multiply = findViewById(R.id.multiply_button);
        divide = findViewById(R.id.divide_button);
        logOut = findViewById(R.id.logOut_button);

        add.setOnClickListener(this::Button_add);
        subtract.setOnClickListener(this::Button_subtract);
        divide.setOnClickListener(this::divide_button);
        multiply.setOnClickListener(this::Button_multiply);

        logOut.setOnClickListener(this::logOut);

    }

    private void logOut(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void Button_add(View view) {

        if (easy.isChecked()) {
            if (q5.isChecked()) {
                Intent intent = new Intent(this, allcalculation.class);
                intent.putExtra("value", "addition");
                intent.putExtra("User", email);
                intent.putExtra("difficulty", "easy");
                spEdit.putInt("numOfQuestions", 5);
                spEdit.commit();
                startActivity(intent);
            } else if (q10.isChecked()) {
                Intent intent = new Intent(this, allcalculation.class);
                intent.putExtra("value", "addition");
                intent.putExtra("User", email);
                intent.putExtra("difficulty", "easy");
                spEdit.putInt("numOfQuestions", 10);
                spEdit.commit();
                startActivity(intent);
            } else if (q15.isChecked()) {
                Intent intent = new Intent(this, allcalculation.class);
                intent.putExtra("value", "addition");
                intent.putExtra("User", email);
                intent.putExtra("difficulty", "easy");
                spEdit.putInt("numOfQuestions", 15);
                spEdit.commit();
                startActivity(intent);
            } else {
                Toast.makeText(this, "Please Select How Many \nQuestions You Wish To Do.", Toast.LENGTH_SHORT).show();
            }
        }
        else if (medium.isChecked()){
            if (q5.isChecked()) {
                Intent intent=new Intent(this,allcalculation.class);
                intent.putExtra("value","addition");
                intent.putExtra("User", email);
                intent.putExtra("difficulty", "medium");
                spEdit.putInt("numOfQuestions", 5);
                spEdit.commit();
                startActivity(intent);
            }
            else if (q10.isChecked()) {
                Intent intent=new Intent(this,allcalculation.class);
                intent.putExtra("value","addition");
                intent.putExtra("User", email);
                intent.putExtra("difficulty", "medium");
                spEdit.putInt("numOfQuestions", 10);
                spEdit.commit();
                startActivity(intent);
            }
            else if (q15.isChecked()) {
                Intent intent=new Intent(this,allcalculation.class);
                intent.putExtra("value","addition");
                intent.putExtra("User", email);
                intent.putExtra("difficulty", "medium");
                spEdit.putInt("numOfQuestions", 15);
                spEdit.commit();
                startActivity(intent);
            }
            else{
                Toast.makeText(this, "Please Select How Many \nQuestions You Wish To Do.", Toast.LENGTH_SHORT).show();
            }
        }
        else if(hard.isChecked()){
            if (q5.isChecked()) {
                Intent intent=new Intent(this,allcalculation.class);
                intent.putExtra("value","addition");
                intent.putExtra("User", email);
                intent.putExtra("difficulty", "hard");
                spEdit.putInt("numOfQuestions", 5);
                spEdit.commit();
                startActivity(intent);
            }
            else if (q10.isChecked()) {
                Intent intent=new Intent(this,allcalculation.class);
                intent.putExtra("value","addition");
                intent.putExtra("User", email);
                intent.putExtra("difficulty", "hard");
                spEdit.putInt("numOfQuestions", 10);
                spEdit.commit();
                startActivity(intent);
            }
            else if (q15.isChecked()) {
                Intent intent=new Intent(this,allcalculation.class);
                intent.putExtra("value","addition");
                intent.putExtra("User", email);
                intent.putExtra("difficulty", "hard");
                spEdit.putInt("numOfQuestions", 15);
                spEdit.commit();
                startActivity(intent);
            }
            else{
                Toast.makeText(this, "Please Select How Many \nQuestions You Wish To Do.", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this,"Please Select a Difficulty",Toast.LENGTH_SHORT).show();
        }

    }

    public void Button_multiply(View view) {

        if (easy.isChecked()) {
            if (q5.isChecked()) {
                Intent intent = new Intent(this, allcalculation.class);
                intent.putExtra("value", "multiply");
                intent.putExtra("User", email);
                intent.putExtra("difficulty", "easy");
                spEdit.putInt("numOfQuestions", 5);
                spEdit.commit();
                startActivity(intent);
            } else if (q10.isChecked()) {
                Intent intent = new Intent(this, allcalculation.class);
                intent.putExtra("value", "multiply");
                intent.putExtra("User", email);
                intent.putExtra("difficulty", "easy");
                spEdit.putInt("numOfQuestions", 10);
                spEdit.commit();
                startActivity(intent);
            } else if (q15.isChecked()) {
                Intent intent = new Intent(this, allcalculation.class);
                intent.putExtra("value", "multiply");
                intent.putExtra("User", email);
                intent.putExtra("difficulty", "easy");
                spEdit.putInt("numOfQuestions", 15);
                spEdit.commit();
                startActivity(intent);
            } else {
                Toast.makeText(this, "Please Select How Many \nQuestions You Wish To Do.", Toast.LENGTH_SHORT).show();
            }
        }
        else if (medium.isChecked()){
            if (q5.isChecked()) {
                Intent intent=new Intent(this,allcalculation.class);
                intent.putExtra("value","multiply");
                intent.putExtra("User", email);
                intent.putExtra("difficulty", "medium");
                spEdit.putInt("numOfQuestions", 5);
                spEdit.commit();
                startActivity(intent);
            }
            else if (q10.isChecked()) {
                Intent intent=new Intent(this,allcalculation.class);
                intent.putExtra("value","multiply");
                intent.putExtra("User", email);
                intent.putExtra("difficulty", "medium");
                spEdit.putInt("numOfQuestions", 10);
                spEdit.commit();
                startActivity(intent);
            }
            else if (q15.isChecked()) {
                Intent intent=new Intent(this,allcalculation.class);
                intent.putExtra("value","multiply");
                intent.putExtra("User", email);
                intent.putExtra("difficulty", "medium");
                spEdit.putInt("numOfQuestions", 15);
                spEdit.commit();
                startActivity(intent);
            }
            else{
                Toast.makeText(this, "Please Select How Many \nQuestions You Wish To Do.", Toast.LENGTH_SHORT).show();
            }
        }
        else if(hard.isChecked()){
            if (q5.isChecked()) {
                Intent intent=new Intent(this,allcalculation.class);
                intent.putExtra("value","multiply");
                intent.putExtra("User", email);
                intent.putExtra("difficulty", "hard");
                spEdit.putInt("numOfQuestions", 5);
                spEdit.commit();
                startActivity(intent);
            }
            else if (q10.isChecked()) {
                Intent intent=new Intent(this,allcalculation.class);
                intent.putExtra("value","multiply");
                intent.putExtra("User", email);
                intent.putExtra("difficulty", "hard");
                spEdit.putInt("numOfQuestions", 10);
                spEdit.commit();
                startActivity(intent);
            }
            else if (q15.isChecked()) {
                Intent intent=new Intent(this,allcalculation.class);
                intent.putExtra("value","multiply");
                intent.putExtra("User", email);
                intent.putExtra("difficulty", "hard");
                spEdit.putInt("numOfQuestions", 15);
                spEdit.commit();
                startActivity(intent);
            }
            else{
                Toast.makeText(this, "Please Select How Many \nQuestions You Wish To Do.", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this,"Please Select a Difficulty",Toast.LENGTH_SHORT).show();
        }

    }

    public void Button_subtract(View view) {

        if (easy.isChecked()) {
            if (q5.isChecked()) {
                Intent intent = new Intent(this, allcalculation.class);
                intent.putExtra("value", "subtract");
                intent.putExtra("User", email);
                intent.putExtra("difficulty", "easy");
                spEdit.putInt("numOfQuestions", 5);
                spEdit.commit();
                startActivity(intent);
            } else if (q10.isChecked()) {
                Intent intent = new Intent(this, allcalculation.class);
                intent.putExtra("value", "subtract");
                intent.putExtra("User", email);
                intent.putExtra("difficulty", "easy");
                spEdit.putInt("numOfQuestions", 10);
                spEdit.commit();
                startActivity(intent);
            } else if (q15.isChecked()) {
                Intent intent = new Intent(this, allcalculation.class);
                intent.putExtra("value", "subtract");
                intent.putExtra("User", email);
                intent.putExtra("difficulty", "easy");
                spEdit.putInt("numOfQuestions", 15);
                spEdit.commit();
                startActivity(intent);
            } else {
                Toast.makeText(this, "Please Select How Many \nQuestions You Wish To Do.", Toast.LENGTH_SHORT).show();
            }
        } else if (medium.isChecked()) {
            if (q5.isChecked()) {
                Intent intent = new Intent(this, allcalculation.class);
                intent.putExtra("value", "subtract");
                intent.putExtra("User", email);
                intent.putExtra("difficulty", "medium");
                spEdit.putInt("numOfQuestions", 5);
                spEdit.commit();
                startActivity(intent);
            } else if (q10.isChecked()) {
                Intent intent = new Intent(this, allcalculation.class);
                intent.putExtra("value", "subtract");
                intent.putExtra("User", email);
                intent.putExtra("difficulty", "medium");
                spEdit.putInt("numOfQuestions", 10);
                spEdit.commit();
                startActivity(intent);
            } else if (q15.isChecked()) {
                Intent intent = new Intent(this, allcalculation.class);
                intent.putExtra("value", "subtract");
                intent.putExtra("User", email);
                intent.putExtra("difficulty", "medium");
                spEdit.putInt("numOfQuestions", 15);
                spEdit.commit();
                startActivity(intent);
            } else {
                Toast.makeText(this, "Please Select How Many \nQuestions You Wish To Do.", Toast.LENGTH_SHORT).show();
            }
        } else if (hard.isChecked()) {
            if (q5.isChecked()) {
                Intent intent = new Intent(this, allcalculation.class);
                intent.putExtra("value", "subtract");
                intent.putExtra("User", email);
                intent.putExtra("difficulty", "hard");
                spEdit.putInt("numOfQuestions", 5);
                spEdit.commit();
                startActivity(intent);
            } else if (q10.isChecked()) {
                Intent intent = new Intent(this, allcalculation.class);
                intent.putExtra("value", "subtract");
                intent.putExtra("User", email);
                intent.putExtra("difficulty", "hard");
                spEdit.putInt("numOfQuestions", 10);
                spEdit.commit();
                startActivity(intent);
            } else if (q15.isChecked()) {
                Intent intent = new Intent(this, allcalculation.class);
                intent.putExtra("value", "subtract");
                intent.putExtra("User", email);
                intent.putExtra("difficulty", "hard");
                spEdit.putInt("numOfQuestions", 15);
                spEdit.commit();
                startActivity(intent);
            } else {
                Toast.makeText(this, "Please Select How Many \nQuestions You Wish To Do.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Please Select a Difficulty", Toast.LENGTH_SHORT).show();

        }
    }

    public void divide_button(View view) {

            if (easy.isChecked()) {
                if (q5.isChecked()) {
                    Intent intent = new Intent(this, allcalculation.class);
                    intent.putExtra("value", "divide");
                    intent.putExtra("User", email);
                    intent.putExtra("difficulty", "easy");
                    spEdit.putInt("numOfQuestions", 5);
                    spEdit.commit();
                    startActivity(intent);
                } else if (q10.isChecked()) {
                    Intent intent = new Intent(this, allcalculation.class);
                    intent.putExtra("value", "divide");
                    intent.putExtra("User", email);
                    intent.putExtra("difficulty", "easy");
                    spEdit.putInt("numOfQuestions", 10);
                    spEdit.commit();
                    startActivity(intent);
                } else if (q15.isChecked()) {
                    Intent intent = new Intent(this, allcalculation.class);
                    intent.putExtra("value", "divide");
                    intent.putExtra("User", email);
                    intent.putExtra("difficulty", "easy");
                    spEdit.putInt("numOfQuestions", 15);
                    spEdit.commit();
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Please Select How Many \nQuestions You Wish To Do.", Toast.LENGTH_SHORT).show();
                }
            }
            else if (medium.isChecked()){
                if (q5.isChecked()) {
                    Intent intent=new Intent(this,allcalculation.class);
                    intent.putExtra("value","divide");
                    intent.putExtra("User", email);
                    intent.putExtra("difficulty", "medium");
                    spEdit.putInt("numOfQuestions", 5);
                    spEdit.commit();
                    startActivity(intent);
                }
                else if (q10.isChecked()) {
                    Intent intent=new Intent(this,allcalculation.class);
                    intent.putExtra("value","divide");
                    intent.putExtra("User", email);
                    intent.putExtra("difficulty", "medium");
                    spEdit.putInt("numOfQuestions", 10);
                    spEdit.commit();
                    startActivity(intent);
                }
                else if (q15.isChecked()) {
                    Intent intent=new Intent(this,allcalculation.class);
                    intent.putExtra("value","divide");
                    intent.putExtra("User", email);
                    intent.putExtra("difficulty", "medium");
                    spEdit.putInt("numOfQuestions", 15);
                    spEdit.commit();
                    startActivity(intent);
                }
                else{
                    Toast.makeText(this, "Please Select How Many \nQuestions You Wish To Do.", Toast.LENGTH_SHORT).show();
                }
            }
            else if(hard.isChecked()){
                if (q5.isChecked()) {
                    Intent intent=new Intent(this,allcalculation.class);
                    intent.putExtra("value","divide");
                    intent.putExtra("User", email);
                    intent.putExtra("difficulty", "hard");
                    spEdit.putInt("numOfQuestions", 5);
                    spEdit.commit();
                    startActivity(intent);
                }
                else if (q10.isChecked()) {
                    Intent intent=new Intent(this,allcalculation.class);
                    intent.putExtra("value","divide");
                    intent.putExtra("User", email);
                    intent.putExtra("difficulty", "hard");
                    spEdit.putInt("numOfQuestions", 10);
                    spEdit.commit();
                    startActivity(intent);
                }
                else if (q15.isChecked()) {
                    Intent intent=new Intent(this,allcalculation.class);
                    intent.putExtra("value","divide");
                    intent.putExtra("User", email);
                    intent.putExtra("difficulty", "hard");
                    spEdit.putInt("numOfQuestions", 15);
                    spEdit.commit();
                    startActivity(intent);
                }
                else{
                    Toast.makeText(this, "Please Select How Many \nQuestions You Wish To Do.", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(this,"Please Select a Difficulty",Toast.LENGTH_SHORT).show();
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
