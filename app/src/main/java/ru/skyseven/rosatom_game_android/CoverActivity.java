package ru.skyseven.rosatom_game_android;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class CoverActivity extends Activity implements View.OnClickListener {
    Button startBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cover);

        String font = getResources().getString(R.string.button_text_font);

        Typeface buttonTypeface = Typeface.createFromAsset(this.getAssets(), font);


        startBtn = (Button) findViewById(R.id.main_start_btn);
        startBtn.setTypeface(buttonTypeface);
        startBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {



        startActivity(new Intent(this, MainActivity.class));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cover, menu);
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
