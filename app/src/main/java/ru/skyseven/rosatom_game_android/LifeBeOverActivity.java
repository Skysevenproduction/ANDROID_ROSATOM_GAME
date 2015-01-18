package ru.skyseven.rosatom_game_android;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import ru.skyseven.rosatom_game_android.data_source.DataManager;


public class LifeBeOverActivity extends Activity implements View.OnClickListener{
    public DataManager DATA_MGR;

    Button repeat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_be_over);

        String font = getResources().getString(R.string.button_text_font);

        Typeface buttonTypeface = Typeface.createFromAsset(this.getAssets(), font);
        repeat=(Button)findViewById(R.id.buttonRepeat);
        repeat.setTypeface(buttonTypeface);
        repeat.setOnClickListener(this);

        DATA_MGR=new DataManager(this);


    }

    @Override
    public void onClick(View v) {
        DATA_MGR.setPass_question_count(0);
        DATA_MGR.setLife_count(3);
        setResult(2);
        this.finish();

    }


        @Override
    public void onBackPressed() {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            DATA_MGR.setPass_question_count(0);
            DATA_MGR.setLife_count(3);
            setResult(2);
            this.finish();
        }
        return super.onTouchEvent(event);
    }
}
