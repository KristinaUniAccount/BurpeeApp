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


public class ActivityHowto3 extends ActionBarActivity {
    ImageButton back3;
    ImageButton backtomenu3;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_howto3);

        ImageButton back3 = (ImageButton) findViewById(R.id.back3);
        ImageButton backtomenu3 = (ImageButton) findViewById(R.id.backtomenu3);

        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.hide();
    }

    public void back3(View v){
        v.startAnimation(buttonClick);
        startActivity(new Intent(getApplicationContext(), ActivityHowto2.class));
    }

    public void backtomenu3(View v){
        v.startAnimation(buttonClick);
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_howto3, menu);
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
