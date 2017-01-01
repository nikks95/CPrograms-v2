package com.prognoobie.nikhil.programc;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.util.Linkify;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.ttoolbar);

        final ImageView logoImage = (ImageView) findViewById(R.id.main_logo);
        ImageView codingImage = (ImageView) findViewById(R.id.main_coding);
        ImageView quesImage = (ImageView) findViewById(R.id.main_quesans);
        ImageView aboutusImage = (ImageView) findViewById(R.id.main_aboutus);


        //Listener
        logoImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                final Animation an  = AnimationUtils.loadAnimation(getBaseContext(),R.anim.fastrotate);
                logoImage.startAnimation(an);
            }
        });

        //coding Listener
        codingImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),CodingActivity.class);
                startActivity(intent);
            }
        });
        quesImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(),QuesActivity.class);
                startActivity(intent);
            }
        });
        aboutusImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AboutActivity.class);
                startActivity(intent);
            }
        });



        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final SpannableString s = new SpannableString("https://github.com/nikks95/CPrograms-v2");
                Linkify.addLinks(s, Linkify.WEB_URLS);

                String header = "Watch Source code at @github ";
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle(header)
                        .setMessage(s)

//
                        .setNegativeButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setIcon(R.drawable.but)
                        .show();


            }
        });
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
        if (id == R.id.share) {
           String total_data  = "https://github.com/nikks95/CPrograms-v2";
            Intent sharewhatsappIntent = new Intent(Intent.ACTION_SEND);
            sharewhatsappIntent.setType("text/plain");
            sharewhatsappIntent.setPackage("com.whatsapp");
            sharewhatsappIntent.putExtra(Intent.EXTRA_TEXT, total_data);

            try {
                startActivity(sharewhatsappIntent);
            } catch (android.content.ActivityNotFoundException ex) {

            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
