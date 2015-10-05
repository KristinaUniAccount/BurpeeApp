package com.example.kristinah.burpeeapp;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageButton;


public class ActivityYellowButton extends ActionBarActivity {

    ImageButton next1;
    ImageButton backtomenu1;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yellow_button);

        next1 = (ImageButton) findViewById(R.id.next1);
        backtomenu1 = (ImageButton) findViewById(R.id.backtomenu1);

        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.hide();
    }

    //Button zum nächsten Schritt
    public void next1(View v){
        v.startAnimation(buttonClick);
        startActivity(new Intent(getApplicationContext(), ActivityYellowButton2.class));
    }

    //Button zurück zum Menü
    public void backtomenu1(View v){
        v.startAnimation(buttonClick);
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_yellow_button, menu);
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
