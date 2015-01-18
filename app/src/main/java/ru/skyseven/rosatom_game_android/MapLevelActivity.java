package ru.skyseven.rosatom_game_android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.ImageView;

import ru.skyseven.rosatom_game_android.data_source.DataManager;


public class MapLevelActivity extends Activity {

    DataManager DATA_MGR;
    ImageView imgLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_level);

        imgLevel=(ImageView) findViewById(R.id.mapImgLevel);

        DATA_MGR=new DataManager(this);

        switch (DATA_MGR.getCurrent_level()){
            case 1:{
                imgLevel.setImageResource(R.drawable.img_001);
                break;
            }
            case 2:{
                imgLevel.setImageResource(R.drawable.img_002);
                break;
            }
            case 3:{
                imgLevel.setImageResource(R.drawable.img_003);
                break;
            }
            case 4:{
                imgLevel.setImageResource(R.drawable.img_004);
                break;
            }
            case 5:{
                imgLevel.setImageResource(R.drawable.img_005);
                break;
            }
            case 6:{
                imgLevel.setImageResource(R.drawable.img_006);
                break;
            }
            case 7:{
                imgLevel.setImageResource(R.drawable.img_007);
                break;
            }
            case 8:{
                imgLevel.setImageResource(R.drawable.img_008);
                break;
            }
            case 9:{
                imgLevel.setImageResource(R.drawable.img_009);
                break;
            }
            case 10:{
                imgLevel.setImageResource(R.drawable.img_010);
                break;
            }
            case 11:{
                imgLevel.setImageResource(R.drawable.img_011);
                break;
            }
            case 12:{
                imgLevel.setImageResource(R.drawable.img_012);
                break;
            }
            case 13:{
                imgLevel.setImageResource(R.drawable.img_013);
                break;
            }
            case 14:{
                imgLevel.setImageResource(R.drawable.img_014);
                break;
            }
            case 15:{
                imgLevel.setImageResource(R.drawable.img_015);
                break;
            }
            case 16:{
                imgLevel.setImageResource(R.drawable.img_016);
                break;
            }
            case 17:{
                imgLevel.setImageResource(R.drawable.img_017);
                break;
            }
            case 18:{
                imgLevel.setImageResource(R.drawable.img_018);
                break;
            }
            case 19:{
                imgLevel.setImageResource(R.drawable.img_019);
                break;
            }
            case 20:{
                imgLevel.setImageResource(R.drawable.img_020);
                break;
            }
            case 21:{
                imgLevel.setImageResource(R.drawable.img_021);
                break;
            }
            case 22:{
                imgLevel.setImageResource(R.drawable.img_022);
                break;
            }
            case 23:{
                imgLevel.setImageResource(R.drawable.img_023);
                break;
            }
            case 24:{
                imgLevel.setImageResource(R.drawable.img_024);
                break;
            }
            case 25:{
                imgLevel.setImageResource(R.drawable.img_025);
                break;
            }
            case 26:{
                imgLevel.setImageResource(R.drawable.img_026);
                break;
            }
            case 27:{
                imgLevel.setImageResource(R.drawable.img_027);
                break;
            }
            case 28:{
                imgLevel.setImageResource(R.drawable.img_028);
                break;
            }
            case 29:{
                imgLevel.setImageResource(R.drawable.img_029);
                break;
            }
            case 30:{
                imgLevel.setImageResource(R.drawable.img_030);
                break;
            }
            case 31:{
                imgLevel.setImageResource(R.drawable.img_031);
                break;
            }
            case 32:{
                imgLevel.setImageResource(R.drawable.img_032);
                break;
            }
            case 33:{
                imgLevel.setImageResource(R.drawable.img_033);
                break;
            }
            case 34:{
                imgLevel.setImageResource(R.drawable.img_034);
                break;
            }
            case 35:{
                imgLevel.setImageResource(R.drawable.img_035);
                break;
            }
            case 36:{
                imgLevel.setImageResource(R.drawable.img_036);
                break;
            }
            case 37:{
                imgLevel.setImageResource(R.drawable.img_037);
                break;
            }
            case 38:{
                imgLevel.setImageResource(R.drawable.img_038);
                break;
            }
            case 39:{
                imgLevel.setImageResource(R.drawable.img_039);
                break;
            }
            case 40:{
                imgLevel.setImageResource(R.drawable.img_040);
                break;
            }
            case 41:{
                imgLevel.setImageResource(R.drawable.img_041);
                break;
            }
            case 42:{
                imgLevel.setImageResource(R.drawable.img_042);
                break;
            }
            case 43:{
                imgLevel.setImageResource(R.drawable.img_043);
                break;
            }
            case 44:{
                imgLevel.setImageResource(R.drawable.img_044);
                break;
            }
            case 45:{
                imgLevel.setImageResource(R.drawable.img_045);
                break;
            }

            default: {
                imgLevel.setImageResource(R.drawable.img_045);
                break;
            }
        }

    }
    @Override
    public void onBackPressed() {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            setResult(2);
            this.finish();
            return false;
        }


        return false;
    }
}
