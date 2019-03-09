package com.example.servicetest;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;

public class CommandTask extends AsyncTask<Void, String, Integer> {
    private CommandListener listener;

    public CommandTask(CommandListener listener){
        this.listener = listener;
    }

    @Override
    protected Integer doInBackground(Void... voids) {
        try {
            //host请自行更改
            Socket client = new Socket("175.159.82.169", 6666);

            InputStream inputStream = client.getInputStream();
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(buf)) != -1) {
                String s = new String(buf, 0, len);
                publishProgress(s);
                System.out.println(s);
                //buf = new byte[1024];
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        String command = values[0];
        if (command.equals("-start")){
            listener.onGameStart();
        }else if (command.startsWith("-color")){
            String color = command.substring(7);
            listener.onColorChange(color);
        }else if (command.equals("-end")){
            listener.onGameEnd();
        }else if (command.equals("-newGame")){
            listener.onNewGame();
        }
    }

    @Override
    protected void onPostExecute(Integer status) {
        // TODO: 02/03/2019
    }

}
