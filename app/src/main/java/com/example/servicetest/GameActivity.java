package com.example.servicetest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameActivity extends AppCompatActivity {
    private ActivityChangeService.CommandBinder commandBinder;
    ActivityChangeService myService;

    private Button red, blue, green;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            commandBinder = (ActivityChangeService.CommandBinder)service;
            myService = commandBinder.getService();

            myService.setColorRemoveCallBack(new ActivityChangeService.ColorRemoveCallBack() {
                @Override
                public void removeColor(String color) {
                    switch (color){
                        case "red":
                            red.setVisibility(View.GONE);
                            break;
                        case "green":
                            green.setVisibility(View.GONE);
                            break;
                        case "blue":
                            blue.setVisibility(View.GONE);
                            break;
                        default:
                            break;
                    }
                }
            });

            myService.setEndCallBack(new ActivityChangeService.EndCallBack() {
                @Override
                public void endGame() {
                    Intent intent = new Intent(GameActivity.this, EndActivity.class);
                    startActivity(intent);
                }
            });

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        red = (Button)findViewById(R.id.red_button);
        blue = (Button)findViewById(R.id.blue_button);
        green = (Button)findViewById(R.id.green_button);

        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myService.getCommandTask().send("red clicked");

            }
        });

        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myService.getCommandTask().send("green clicked");
            }
        });

        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myService.getCommandTask().send("blue clicked");
            }
        });

        Intent startIntent = new Intent(this, ActivityChangeService.class);
        bindService(startIntent, serviceConnection, BIND_AUTO_CREATE);


    }
}
