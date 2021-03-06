package com.example.kristinah.burpeeapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.VideoView;


public class ActivityHowto2 extends ActionBarActivity {

    ImageButton next2;
    ImageButton back2;
    ImageButton backtomenu2;
    VideoView video;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_howto2);
        MediaController vidControl = new MediaController(this);
        vidControl.setAnchorView(video);

        ImageButton next2 = (ImageButton) findViewById(R.id.next2);
        ImageButton back2 = (ImageButton) findViewById(R.id.back2);
        ImageButton backtomenu2 = (ImageButton) findViewById(R.id.backtomenu2);

        //Video und Mediacontroler zuweisen
        VideoView video = (VideoView) findViewById(R.id.video);
        video.setMediaController(vidControl);
        String urlpath = "android.resource://" + getPackageName() + "/" + R.raw.film;
        video.setVideoURI(Uri.parse(urlpath));
        video.start();

        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.hide();
    }


    //Button zum nächsten View
    public void next2(View v){
        v.startAnimation(buttonClick);
        startActivity(new Intent(getApplicationContext(), ActivityHowto3.class));
    }

    //Butten zum vorherigen View
    public void back2(View v){
        v.startAnimation(buttonClick);
        startActivity(new Intent(getApplicationContext(), ActivityHowto.class));
    }

    //Button zum Hauptmenü
    public void backtomenu2(View v){
        v.startAnimation(buttonClick);
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_howto2, menu);
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
