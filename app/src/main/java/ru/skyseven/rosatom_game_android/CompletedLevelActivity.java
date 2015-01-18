package ru.skyseven.rosatom_game_android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;

import ru.skyseven.rosatom_game_android.data_source.DataManager;


public class CompletedLevelActivity extends Activity {

    public DataManager DATA_MGR;
    static final String LOG_TAG="RA_GAME";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_level);

        DATA_MGR=new DataManager(this);
        DATA_MGR.setShowMapLevel(true);

        long value=DATA_MGR.getCurrent_level();
        Log.i(LOG_TAG,"level="+String.valueOf( value));
        DATA_MGR.ga_send_event(this,"level","level_complete","number", value);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_completed_level, menu);
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

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==2){
            setResult(2);
            this.finish();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (DATA_MGR.getPass_question_count()==8){
                DATA_MGR.setPass_question_count(0);
                DATA_MGR.setLife_count(3);
                if (DATA_MGR.getCurrent_level()<45)
                    DATA_MGR.setCurrent_level(DATA_MGR.getCurrent_level()+1);
                startActivityForResult(new Intent(this,MapLevelActivity.class),2);

            }else {
                DATA_MGR.setPass_question_count(0);
                DATA_MGR.setLife_count(3);
                setResult(2);
                this.finish();
            }
        }


        return false;
        //return super.onTouchEvent(event);
    }
}
