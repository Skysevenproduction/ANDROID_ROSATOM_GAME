package ru.skyseven.rosatom_game_android;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import ru.skyseven.rosatom_game_android.data_source.DataManager;


public class AnswerActivity extends Activity {
    public LinearLayout layoutTop;
    public LinearLayout layoutMiddle;
    public LinearLayout layoutBottom;
    public ImageView iconAnswer;
    public TextView passQuestionText;
    public ImageView imgTitleAnswer;
    public TextView passQuestionNumber;

    public ImageView progress_img_color1;
    public ImageView progress_img_color2;
    public ImageView progress_img_color3;
    public ImageView progress_img_color4;
    public ImageView progress_img_color5;
    public ImageView progress_img_color6;
    public ImageView progress_img_color7;
    public ImageView progress_img_color8;

    public Intent intent;

    Animation animationFadeIn, animationFadeOut;

    public   DataManager DATA_MGR;

    public int passQuestion;

    public boolean answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);


        String font = getResources().getString(R.string.main_text_font);

        Typeface statusTypeface = Typeface.createFromAsset(getAssets(), font);

        TextView questionText = (TextView) findViewById(R.id.commentText);

        questionText.setTypeface(statusTypeface);

        layoutTop=(LinearLayout) findViewById(R.id.bgLayoutTop);
        layoutMiddle=(LinearLayout) findViewById(R.id.bgLayoutMiddle);
        layoutBottom=(LinearLayout) findViewById(R.id.bgLayoutBottom);
        iconAnswer=(ImageView) findViewById(R.id.iconAnswer);
        passQuestionText=(TextView) findViewById(R.id.commentText);
        imgTitleAnswer=(ImageView) findViewById(R.id.imgTitleAnswer);
        passQuestionNumber=(TextView) findViewById(R.id.passQuestionNumber);


        progress_img_color1=(ImageView) findViewById(R.id.progress_img_color1);
        progress_img_color2=(ImageView) findViewById(R.id.progress_img_color2);
        progress_img_color3=(ImageView) findViewById(R.id.progress_img_color3);
        progress_img_color4=(ImageView) findViewById(R.id.progress_img_color4);
        progress_img_color5=(ImageView) findViewById(R.id.progress_img_color5);
        progress_img_color6=(ImageView) findViewById(R.id.progress_img_color6);
        progress_img_color7=(ImageView) findViewById(R.id.progress_img_color7);
        progress_img_color8=(ImageView) findViewById(R.id.progress_img_color8);

        animationFadeIn = AnimationUtils.loadAnimation(this, R.anim.fadein);

        DATA_MGR=new DataManager(this);

        intent = getIntent();
        answer=intent.getBooleanExtra("answer",true);
        passQuestion=intent.getIntExtra("passQuestion",-10);

        preAnimProgress();

        passQuestionNumber.setText(Integer.toString(passQuestion));
        if (answer){
            layoutTop.setBackgroundResource(R.color.bg_color_middle_right_answer);
            layoutMiddle.setBackgroundResource(R.color.bg_color_middle_right_answer);
            layoutBottom.setBackgroundResource(R.color.bg_color_middle_right_answer);
            iconAnswer.setImageResource(R.drawable.img_right);
            imgTitleAnswer.setImageResource(R.drawable.title_right_answer);
            animProgress();
        }else{
            if (DATA_MGR.getLife_count()>0)
                DATA_MGR.setLife_count(DATA_MGR.getLife_count()-1);

            layoutTop.setBackgroundResource(R.color.bg_color_middle_wrong_answer);
            layoutMiddle.setBackgroundResource(R.color.bg_color_middle_wrong_answer);
            layoutBottom.setBackgroundResource(R.color.bg_color_middle_wrong_answer);
            iconAnswer.setImageResource(R.drawable.img_wrong);
            imgTitleAnswer.setImageResource(R.drawable.title_wrong_answer);
            postAnimProgress();
        }

        String comment=intent.getStringExtra("comment");
        if (comment.length()<4){
            imgTitleAnswer.setVisibility(View.VISIBLE);
            passQuestionText.setVisibility(View.INVISIBLE);
        }else {
            imgTitleAnswer.setVisibility(View.INVISIBLE);
            passQuestionText.setVisibility(View.VISIBLE);
            passQuestionText.setText(comment);
        }

    }



    @Override
    public void onStart() {
        super.onStart();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_answer, menu);
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
            this.finish();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            if (answer){

                if (DATA_MGR.getPass_question_count()==8)
                    startActivityForResult(new Intent(this,CompletedLevelActivity.class),2);
                else
                    this.finish();
            }else{
                if (DATA_MGR.getLife_count()==0) {
                    startActivityForResult(new Intent(this, LifeBeOverActivity.class),2);
                }else{
                    this.finish();
                }
            }
        }


        return false;
    }

    public void animProgress(){
        switch (passQuestion){
            case 1:{
                progress_img_color1.startAnimation(animationFadeIn);
                break;
            }
            case 2:{
                progress_img_color2.startAnimation(animationFadeIn);
                break;
            }
            case 3:{
                progress_img_color3.startAnimation(animationFadeIn);
                break;
            }
            case 4:{
                progress_img_color4.startAnimation(animationFadeIn);
                break;
            }
            case 5:{
                progress_img_color5.startAnimation(animationFadeIn);
                break;
            }
            case 6:{
                progress_img_color6.startAnimation(animationFadeIn);
                break;
            }
            case 7:{
                progress_img_color7.startAnimation(animationFadeIn);
                break;
            }
            case 8:{
                progress_img_color8.startAnimation(animationFadeIn);
                break;
            }
        }
        postAnimProgress();

    }

    public void postAnimProgress(){
        switch (passQuestion){
            case 1:{
                progress_img_color1.setVisibility(View.VISIBLE);
                break;
            }
            case 2:{
                progress_img_color2.setVisibility(View.VISIBLE);
                break;
            }
            case 3:{
                progress_img_color3.setVisibility(View.VISIBLE);
                break;
            }
            case 4:{
                progress_img_color4.setVisibility(View.VISIBLE);
                break;
            }
            case 5:{
                progress_img_color5.setVisibility(View.VISIBLE);
                break;
            }
            case 6:{
                progress_img_color6.setVisibility(View.VISIBLE);
                break;
            }
            case 7:{
                progress_img_color7.setVisibility(View.VISIBLE);
                break;
            }
            case 8:{
                progress_img_color8.setVisibility(View.VISIBLE);
                break;
            }
        }

    }

    public void preAnimProgress(){

        progress_img_color1.setVisibility(View.INVISIBLE);
        progress_img_color2.setVisibility(View.INVISIBLE);
        progress_img_color3.setVisibility(View.INVISIBLE);
        progress_img_color4.setVisibility(View.INVISIBLE);
        progress_img_color5.setVisibility(View.INVISIBLE);
        progress_img_color6.setVisibility(View.INVISIBLE);
        progress_img_color7.setVisibility(View.INVISIBLE);
        progress_img_color8.setVisibility(View.INVISIBLE);



        switch (passQuestion){
            case 2:{
                progress_img_color1.setVisibility(View.VISIBLE);
                break;
            }
            case 3:{
                progress_img_color1.setVisibility(View.VISIBLE);
                progress_img_color2.setVisibility(View.VISIBLE);
                break;
            }
            case 4:{
                progress_img_color1.setVisibility(View.VISIBLE);
                progress_img_color2.setVisibility(View.VISIBLE);
                progress_img_color3.setVisibility(View.VISIBLE);
                break;
            }
            case 5:{
                progress_img_color1.setVisibility(View.VISIBLE);
                progress_img_color2.setVisibility(View.VISIBLE);
                progress_img_color3.setVisibility(View.VISIBLE);
                progress_img_color4.setVisibility(View.VISIBLE);
                break;
            }
            case 6:{
                progress_img_color1.setVisibility(View.VISIBLE);
                progress_img_color2.setVisibility(View.VISIBLE);
                progress_img_color3.setVisibility(View.VISIBLE);
                progress_img_color4.setVisibility(View.VISIBLE);
                progress_img_color5.setVisibility(View.VISIBLE);
                break;
            }
            case 7:{
                progress_img_color1.setVisibility(View.VISIBLE);
                progress_img_color2.setVisibility(View.VISIBLE);
                progress_img_color3.setVisibility(View.VISIBLE);
                progress_img_color4.setVisibility(View.VISIBLE);
                progress_img_color5.setVisibility(View.VISIBLE);
                progress_img_color6.setVisibility(View.VISIBLE);
                break;
            }
            case 8:{
                progress_img_color1.setVisibility(View.VISIBLE);
                progress_img_color2.setVisibility(View.VISIBLE);
                progress_img_color3.setVisibility(View.VISIBLE);
                progress_img_color4.setVisibility(View.VISIBLE);
                progress_img_color5.setVisibility(View.VISIBLE);
                progress_img_color6.setVisibility(View.VISIBLE);
                progress_img_color7.setVisibility(View.VISIBLE);

                break;
            }


        }

    }

}
