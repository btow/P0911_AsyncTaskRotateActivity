package com.example.samsung.p0911_asynctaskrotateactivity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private final String LOG_TAG = "myLogs";
    private String message = "";

    private class MyTask extends AsyncTask<String, Integer, Void> {

        @Override
        protected Void doInBackground(String... params) {

            try {
                for (int i = 0; i < 10; i++) {
                    TimeUnit.SECONDS.sleep(1);
                    publishProgress(i);
                    message = "i = " + i
                            + ", MyTask: " + this.hashCode()
                            + ", MainActivity: " + MainActivity.this.hashCode();
                    Log.d(LOG_TAG, message);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            message = "i = " + values[0];
            tvInfo.setText(message);
        }
    }

    private MyTask myTask;
    private TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        message = "create MainActivity: " + this.hashCode();
        Log.d(LOG_TAG, message);

        tvInfo = (TextView) findViewById(R.id.tvInfo);

        myTask = new MyTask();
        message = "create MyTask " + myTask.hashCode();
        Log.d(LOG_TAG, message);
        myTask.execute();

    }
}
