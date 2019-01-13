package com.example.rohitdhende.linearlayout;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.TextView;

import com.suke.widget.SwitchButton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Second_Page extends AppCompatActivity {

    TextView t1,t2,t3,t4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second__page);



        if (Build.VERSION.SDK_INT>=21){
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.black));
        }

        t1 = (TextView)findViewById(R.id.textView1);
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(),  "fonts/Amatic-Bold.ttf");
        t1.setTypeface(custom_font1);

        t2 = (TextView)findViewById(R.id.textView2);
        Typeface custom_font2 = Typeface.createFromAsset(getAssets(),  "fonts/Amatic-Bold.ttf");
        t2.setTypeface(custom_font2);

        t3 = (TextView)findViewById(R.id.textView3);
        Typeface custom_font3 = Typeface.createFromAsset(getAssets(),  "fonts/Amatic-Bold.ttf");
        t3.setTypeface(custom_font3);

        t4 = (TextView)findViewById(R.id.textView4);
        Typeface custom_font4 = Typeface.createFromAsset(getAssets(),  "fonts/Amatic-Bold.ttf");
        t4.setTypeface(custom_font4);

        com.suke.widget.SwitchButton s1 = findViewById(R.id.switch1);
        com.suke.widget.SwitchButton s2 = findViewById(R.id.switch2);
        com.suke.widget.SwitchButton s3 = findViewById(R.id.switch3);
        com.suke.widget.SwitchButton s4 = findViewById(R.id.switch4);

        s1.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (isChecked) {
                    /* Switch is led 1 */
                    new Second_Page.Background_get().execute("pin=16&on=1");
                    t1.setTextColor(Color.parseColor("#00ffff"));
                } else {
                    new Second_Page.Background_get().execute("pin=16&on=0");
                    t1.setTextColor(Color.parseColor("#ffffff"));
                }


            }
        });

        s2.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (isChecked) {
                    /* Switch is led 1 */
                    new Second_Page.Background_get().execute("pin=5&on=1");
                    t2.setTextColor(Color.parseColor("#00ffff"));

                } else {
                    new Second_Page.Background_get().execute("pin=5&on=0");
                    t2.setTextColor(Color.parseColor("#ffffff"));
                }
            }
        });

        s3.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (isChecked) {
                    /* Switch is led 1 */
                    new Second_Page.Background_get().execute("pin=4&on=1");
                    t3.setTextColor(Color.parseColor("#00ff7a"));

                } else {
                    new Second_Page.Background_get().execute("pin=4&on=0");
                    t3.setTextColor(Color.parseColor("#ffffff"));
                }
            }
        });

        s4.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (isChecked) {
                    /* Switch is led 1 */
                    new Second_Page.Background_get().execute("pin=2&on=1");
                    t4.setTextColor(Color.parseColor("#00ff7a"));
                } else {
                    new Second_Page.Background_get().execute("pin=2&on=0");
                    t4.setTextColor(Color.parseColor("#ffffff"));
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
