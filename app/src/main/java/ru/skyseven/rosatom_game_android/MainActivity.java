package ru.skyseven.rosatom_game_android;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ru.skyseven.rosatom_game_android.data_source.DataManager;

import ru.skyseven.rosatom_game_android.data_source.DataManager;
import ru.skyseven.rosatom_game_android.data_source.QuestionItem;
import ru.skyseven.rosatom_game_android.data_source.XMLParser;


public class MainActivity extends Activity implements View.OnClickListener {

    static final String LOG_TAG="RA_GAME";





    public DataManager DATA_MGR;

    Button startBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String font = getResources().getString(R.string.button_text_font);

        Typeface buttonTypeface = Typeface.createFromAsset(this.getAssets(), font);


        startBtn = (Button) findViewById(R.id.startBtn);
        startBtn.setTypeface(buttonTypeface);
        startBtn.setOnClickListener(this);

        DATA_MGR = new DataManager(this);

        DATA_MGR.setShowMapLevel(false);

    }


    @Override
    public void onClick(View v) {

        startActivityForResult(new Intent(this,MapLevelActivity.class),2);



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==2){
            startActivity(new Intent(this, PlayGameActivity.class));
        }
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
