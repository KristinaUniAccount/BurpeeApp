package com.example.kristinah.burpeeapp;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.EditText;
import android.widget.ImageButton;


public class ActivityCounter extends ActionBarActivity {

    EditText email;
    ImageButton goToCounter;
    String s;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);

        email = (EditText) findViewById(R.id.email);
        goToCounter = (ImageButton) findViewById(R.id.goToCounter);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.hide();

    }

    public void backtomenu1(View v){
        v.startAnimation(buttonClick);
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    public void goToCounter(View v){
        v.startAnimation(buttonClick);
        Intent nextScreen = new Intent(getApplicationContext(), ActivityCounter2.class);
        nextScreen.putExtra("email", email.getText().toString());
        startActivity(nextScreen);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_counter, menu);
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


}
