package com.example.kristinah.burpeeapp;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.net.Uri;
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

import static android.hardware.Sensor.TYPE_LINEAR_ACCELERATION;
import static android.hardware.Sensor.TYPE_ORIENTATION;


public class ActivityGreenButton2 extends ActionBarActivity implements SensorEventListener{


    Sensor orientation;
    Sensor acceleration;
    SensorManager am;
    SensorManager om;
    MediaPlayer player;
    int grad;
    int beschl;
    int maxBeschl = 0;
    int phase = 0;
    int counter = 0;
    TextView gruenEinfuehrung;
    ImageButton buttonreset;
    ImageButton backtomenu1;
    EditText email;
    String emailAdr = "";
    String Text1 = "";
    String Text2 = "";
    String Text3 = "";
    //Animantion für ButtonClick
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_button2);

        //Elemente des Layouts bestimmen
        gruenEinfuehrung = (TextView) findViewById(R.id.gruenEinfuehrung);
        buttonreset = (ImageButton) findViewById(R.id.buttonreset);
        backtomenu1 = (ImageButton) findViewById(R.id.backtomenu1);
        email = (EditText) findViewById(R.id.email);

        //Sensoren bestimmen
        om=(SensorManager)getSystemService(SENSOR_SERVICE);
        orientation=om.getDefaultSensor(TYPE_ORIENTATION);
        om.registerListener(this, orientation, SensorManager.SENSOR_ORIENTATION);
        am = (SensorManager)getSystemService(SENSOR_SERVICE);
        acceleration = am.getDefaultSensor(TYPE_LINEAR_ACCELERATION);
        am.registerListener(this, acceleration, SensorManager.SENSOR_DELAY_NORMAL);

        //Intent bestimmen
        Intent i = getIntent();
        emailAdr= i.getStringExtra("email");

        //Actionbar soll nicht sichbar sein
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

    //Methode um Daten zu versenden
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

            // Anschließend Werte zurück auf Ausgangswert setzen
            Text1 = "";
            Text2 = "";
            Text3 = "";
            counter = 0;

    }



    // Methode, um Daten auf Ausgangswerte zurückzusetzen
    public void resetdata(View v){
        v.startAnimation(buttonClick);
        counter = 0;
        maxBeschl = 0;
        phase = 0;
        gruenEinfuehrung.setText("Du hast die Daten zurück gesetzt. \nBeginne jetzt damit, Burpees zu machen!");
        Text1 = "";
        Text2 = "";
        Text3 = "";

    }


    //Methode, um in das Startmenü zurückzukehren
    public void backtomenu1(View v){
        v.startAnimation(buttonClick);
        startActivity(new Intent(getApplicationContext(), MainActivity.class));

    }

    //Sensorabfrage
    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_ORIENTATION){

            grad= (int) (Math.sqrt((event.values[1])*(event.values[1])));

            //Daten der Orientierung in "Text1" speichern
            Text1 += grad;
            Text1 += ",";

        }

        if (event.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION){

            //Daten der Beschleunigung in "Text2" und "Text3" speichern
            Text2 += event.values[1];
            Text2 += ",";

            Text3 += event.values[2];
            Text3 += ",";

            beschl= (int) (Math.sqrt((event.values[1])*(event.values[1])) + Math.sqrt((event.values[2])*(event.values[2])));

            //Maximale Beschleunigung der Summe der Y- und Z-Achse, die erreicht wurde, in maxBeschl speichern
            if (beschl > maxBeschl){
                maxBeschl = beschl;
            }
        }


        // Folgende Rechnung dient der Ermittlung eines Burpees, wenn das Handy mit
        // korrekter Ausrichtung in der Hosentasche ist.

        //Hocke:
        if ((grad >= 120) && (phase == 0)) {
            phase = 1;}

        //Stütz:
        if ((grad <= 40) && (phase ==1) ){
            phase = 2;
            maxBeschl = 0;}

         //Hocke:
        if((grad >= 110) && (phase == 2)) {
            phase = 3;
            }

        //Hocke:
        if ((grad<110) && (grad>70) && (phase ==3) && (maxBeschl > 13)){

            //Alle Werte zurücksetzen, Anzahl der Burpees anzeigen lassen,
            //Bestätigungston abspielen
            phase = 0;
            maxBeschl = 0;
            counter += 1;

            gruenEinfuehrung.setText("Du hast \n " + counter + " \n Burpees gemacht!");

            player= MediaPlayer.create(ActivityGreenButton2.this, R.raw.ton1);
            player.start();
        }

        // Folgende Rechnung dient der Ermittlung eines Burpees, wenn das Handy mit
        // verkehrter Ausrichtung in der Hosentasche ist.


        //Hocke:
        if ((grad <= 20) && (phase == 0)) {
            phase = 4;}

        //Stütz:
        if ((grad >=160) && (phase ==4) ){
            phase = 5;
            maxBeschl = 0;}

        //Hocke:
        if((grad <=20) && (phase == 5)) {
            phase = 6;            }

        //Strecksprung:
        if ((grad<110) && (grad>70) && (phase ==6) && (maxBeschl > 13)){

            //Alle Werte zurücksetzen, Anzahl der Burpees anzeigen lassen,
            //Bestätigungston abspielen
            phase = 0;
            maxBeschl = 0;
            counter += 1;
            gruenEinfuehrung.setText("Du hast \n " + counter + " \n Burpees gemacht!");
            player= MediaPlayer.create(ActivityGreenButton2.this, R.raw.ton1);
            player.start();
        }



    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
