package com.example.kristinah.burpeeapp;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageButton;


public class MainActivity extends ActionBarActivity{

    ImageButton buttongelb;
    ImageButton buttongruen;
    ImageButton buttonblau;
    ImageButton buttonlila;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttongelb = (ImageButton) findViewById(R.id.buttongelb);
        buttongruen = (ImageButton) findViewById(R.id.buttongruen);
        buttonblau = (ImageButton) findViewById(R.id.buttonblau);
        buttonlila = (ImageButton) findViewById(R.id.buttonlila);

        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.hide();


    }

    //Button HowTo
    public void clickgelb(View v){
        v.startAnimation(buttonClick);
        startActivity(new Intent(getApplicationContext(), ActivityHowto.class));

    }

    //Button start
    public void clickgruen(View v){
        v.startAnimation(buttonClick);
        startActivity(new Intent(getApplicationContext(), ActivityCounter.class));
    }

    //Button about
    public void clickblau(View v)
    {
        v.startAnimation(buttonClick);
        startActivity(new Intent(getApplicationContext(), ActivityAboutus.class));
    }

    //Button info
    public void clicklila(View v){
        v.startAnimation(buttonClick);
        startActivity(new Intent(getApplicationContext(), ActivityInfo.class));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        // Inflate the menu; this adds items to the action bar if it is present.
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




}
