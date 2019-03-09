package com.example.servicetest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GameActivity extends AppCompatActivity {
    private ActivityChangeService.CommandBinder commandBinder;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            commandBinder = (ActivityChangeService.CommandBinder)service;
            ActivityChangeService myService = commandBinder.getService();

            myService.setColorRemoveCallBack(new ActivityChangeService.ColorRemoveCallBack() {
                @Override
                public void removeColor(String color) {
                    switch (color){
                        case "red":
                            findViewById(R.id.red_button).setVisibility(View.GONE);
                            break;
                        case "green":
                            findViewById(R.id.green_button).setVisibility(View.GONE);
                            break;
                        case "blue":
                            findViewById(R.id.blue_button).setVisibility(View.GONE);
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
                    //GameActivity.this.finish();
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
        Intent startIntent = new Intent(this, ActivityChangeService.class);
        bindService(startIntent, serviceConnection, BIND_AUTO_CREATE);
    }
}
