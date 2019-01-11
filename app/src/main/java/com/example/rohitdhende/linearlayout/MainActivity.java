package com.example.rohitdhende.linearlayout;

import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    TextView t1,t2,t3,t4;
    Switch s1,s2,s3,s4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t1 = (TextView)findViewById(R.id.textView1);
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(),  "fonts/Amatic-Bold.ttf");
        t1.setTypeface(custom_font1);

        t2 = (TextView)findViewById(R.id.textView2);
        Typeface custom_font2 = Typeface.createFromAsset(getAssets(),  "fonts/Amatic-Bold.ttf");
        t1.setTypeface(custom_font2);

        t3 = (TextView)findViewById(R.id.textView3);
        Typeface custom_font3 = Typeface.createFromAsset(getAssets(),  "fonts/Amatic-Bold.ttf");
        t1.setTypeface(custom_font3);

        t4 = (TextView)findViewById(R.id.textView4);
        Typeface custom_font4 = Typeface.createFromAsset(getAssets(),  "fonts/Amatic-Bold.ttf");
        t1.setTypeface(custom_font4);


        s1 = findViewById(R.id.switch1);
        s2 = findViewById(R.id.switch2);
        s3 = findViewById(R.id.switch3);
        s4 = findViewById(R.id.switch4);

        s1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    /* Switch is led 1 */
                    new Background_get().execute("pin=16&on=1");
                } else {
                    new Background_get().execute("pin=16&on=0");
                }
            }
        });

        s2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    /* Switch is led 1 */
                    new Background_get().execute("pin=5&on=1");
                } else {
                    new Background_get().execute("pin=5&on=0");
                }
            }
        });

        s3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    /* Switch is led 1 */
                    new Background_get().execute("pin=9&on=1");
                } else {
                    new Background_get().execute("pin=9&on=0");
                }
            }
        });

        s4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    /* Switch is led 1 */
                    new Background_get().execute("pin=7&on=1");
                } else {
                    new Background_get().execute("pin=7&on=0");
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu_main; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private class Background_get extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try {
                /*********************************************************/
                /* Change the IP to the IP you set in the arduino sketch */
                /*********************************************************/
                URL url = new URL("http://192.168.4.1/?" + params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                Log.d("AsyncTask", url.toString());

                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder result = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null)
                    result.append(inputLine).append("\n");

                in.close();
                connection.disconnect();
                return result.toString();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
