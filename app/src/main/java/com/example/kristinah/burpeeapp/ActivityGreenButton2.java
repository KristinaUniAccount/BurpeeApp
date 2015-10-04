package com.example.kristinah.burpeeapp;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.hardware.Sensor.TYPE_LINEAR_ACCELERATION;
import static android.hardware.Sensor.TYPE_ORIENTATION;


public class ActivityGreenButton2 extends ActionBarActivity implements SensorEventListener{

    TextView gruenEinfuehrung;
    int counter = 0;
    Sensor orientation;
    Sensor acceleration;
    SensorManager am;
    SensorManager om;
    MediaPlayer player;
    MediaPlayer player2;
    int wert;
    int wert2;
    int wertmax = 0;
    int c = 0;
    ImageButton buttonreset;
    ImageButton backtomenu1;
    EditText email;
    String emailAdr = "";
    String Text1 = "";
    String Text2 = "";
    String Text3 = "";
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_button2);

        gruenEinfuehrung = (TextView) findViewById(R.id.gruenEinfuehrung);
        buttonreset = (ImageButton) findViewById(R.id.buttonreset);
        backtomenu1 = (ImageButton) findViewById(R.id.backtomenu1);
        email = (EditText) findViewById(R.id.email);

        om=(SensorManager)getSystemService(SENSOR_SERVICE);
        orientation=om.getDefaultSensor(TYPE_ORIENTATION);
        om.registerListener(this, orientation, SensorManager.SENSOR_ORIENTATION);

        am = (SensorManager)getSystemService(SENSOR_SERVICE);
        acceleration = am.getDefaultSensor(TYPE_LINEAR_ACCELERATION);
        am.registerListener(this, acceleration, SensorManager.SENSOR_DELAY_NORMAL);

        Intent i = getIntent();
        emailAdr= i.getStringExtra("email");

        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.hide();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_green_button2, menu);
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

    public void senddata(View v){
            v.startAnimation(buttonClick);
            Log.i("Send email", "");

            String[] TO = {emailAdr};
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.setType("text/plain");



            emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Burpee Data");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Anbei findest du deine Daten aus den gemachten Burpees. \n+" +
                    "Du hast ingesamt " + counter + " Burpee(s) gemacht! Sehr gut :-)! \n\n" +
                    "Liebe Grüße, Kristina und Anni (die Macher) \n\n\n\n\n" +
                    "Orientierung im Raum: \n" + Text1 + "\n\n\n\n" +
                    "Beschleunigung auf der X-Achse: \n" + Text2 + "\n\n\n\n" +
                    "Beschleunigung auf der Y-Achse: \n" + Text3 + "\n\n\n\n" +
                    "Für weitere Informationen siehe: http://developer.android.com/guide/topics/sensors/sensors_overview.html");

            try {
                startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                finish();
                Log.i("Finished sending ", "");
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(ActivityGreenButton2.this,
                        "There is no email client installed.", Toast.LENGTH_SHORT).show();
            }
            Text1 = "";
            Text2 = "";
            Text3 = "";
            counter = 0;

    }



    public void resetdata(View v){
        v.startAnimation(buttonClick);
        counter = 0;
        wertmax = 0;
        c = 0;
        gruenEinfuehrung.setText("Du hast die Daten zurück gesetzt. \nBeginne jetzt damit, Burpees zu machen!");
        Text1 = "";
        Text2 = "";
        Text3 = "";

    }


    public void backtomenu1(View v){
        v.startAnimation(buttonClick);
        startActivity(new Intent(getApplicationContext(), MainActivity.class));

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_ORIENTATION){

            wert= (int) (Math.sqrt((event.values[1])*(event.values[1])));
            Text1 += wert;
            Text1 += ",";

        }

        if (event.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION){

            Text2 += event.values[1];
            Text2 += ",";

            Text3 += event.values[2];
            Text3 += ",";

            wert2= (int) (Math.sqrt((event.values[1])*(event.values[1])) + Math.sqrt((event.values[2])*(event.values[2])));

            if (wert2 > wertmax){
                wertmax = wert2;
            }
        }


        if ((wert >= 120) && (c == 0)) {
            c = 1;}

        if ((wert <= 40) && (c ==1) ){
            c = 2;
            wertmax = 0;}

        if((wert >= 110) && (c == 2)) {
            c =3;
            }

        if ((wert<110) && (wert>70) && (c ==3) && (wertmax > 13)){
            c =0;
            wertmax = 0;
            counter += 1;
            gruenEinfuehrung.setText("Du hast \n " + counter + " \n Burpees gemacht!");

            player= MediaPlayer.create(ActivityGreenButton2.this, R.raw.iphone);
            player.start();
        }


        if ((wert <= 20) && (c == 0)) {
            c = 4;}

        if ((wert >=160) && (c ==4) ){
            c = 5;
            wertmax = 0;}

        if((wert <=20) && (c == 5)) {
            c =6;            }

        if ((wert<110) && (wert>70) && (c ==6) && (wertmax > 13)){
            c =0;
            wertmax = 0;
            counter += 1;
            gruenEinfuehrung.setText("Du hast \n " + counter + " \n Burpees gemacht!");

            player= MediaPlayer.create(ActivityGreenButton2.this, R.raw.iphone);
            player.start();
        }



    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
