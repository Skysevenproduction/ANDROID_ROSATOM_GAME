package ru.skyseven.rosatom_game_android;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.Fields;
import com.google.analytics.tracking.android.MapBuilder;
import com.google.analytics.tracking.android.Tracker;

import org.w3c.dom.Text;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import ru.skyseven.rosatom_game_android.data_source.DataManager;
import ru.skyseven.rosatom_game_android.data_source.QuestionItem;


public class PlayGameActivity extends Activity  {
    static final String LOG_TAG="RA_GAME";

    ImageView   imgBtnBG;
    Button      buttonYES;
    Button      buttonNO;
    TextView    questionText;
    TextView    timerText;

    public DataManager DATA_MGR;
    public int[] questionListEdit;
    public int timeCount;
    public String[] userTimeAnswerEdit;

    public boolean isClick;

    private Timer myTimer;
    private exTimerTask myTimerTask;

    List<QuestionItem> allQuestionList;



    private enum keyName{ANSWER,COMMENT,ID,QUESTION};
    int keyNameStatus=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);



        String font = getResources().getString(R.string.main_text_font);

        Typeface textTypeface = Typeface.createFromAsset(this.getAssets(), font);


        buttonYES = (Button) findViewById(R.id.startBtn);
        //buttonYES.setTypeface(buttonTypeface);
//        buttonYES.setOnClickListener(this);

        DATA_MGR=new DataManager(this);
        DATA_MGR.setPass_question_count(0);
        DATA_MGR.setLife_count(3);

        imgBtnBG=(ImageView) findViewById(R.id.imageBtnBG);
        buttonYES=(Button) findViewById(R.id.buttonYES);
        buttonNO=(Button) findViewById(R.id.buttonNO);
        questionText=(TextView) findViewById(R.id.questionText);
        timerText=(TextView) findViewById(R.id.timerText);

        questionText.setTypeface(textTypeface);

        View.OnClickListener handler = new View.OnClickListener(){

            public void onClick(View v) {

                isClick=true;

                switch (v.getId()) {

                    case R.id.buttonYES:
                        // doStuff
                        imgBtnBG.setImageResource(R.drawable.btn_true_select);

                        checkQuestionAnswer(true);
                        break;
                    case R.id.buttonNO:
                        imgBtnBG.setImageResource(R.drawable.btn_false_select);

                        // doStuff
                        checkQuestionAnswer(false);
                        break;
                }
            }
        };

        findViewById(R.id.buttonYES).setOnClickListener(handler);
        findViewById(R.id.buttonNO).setOnClickListener(handler);

        //загрузка всех вопросов
        loadListQuestionFromXML();


        }

    @Override
    public void onBackPressed() {
        stopTimer();
        super.onBackPressed();

    }

    @Override
    protected void onStart() {
        super.onStart();
        isClick=false;


        //google analytics
        Tracker easyTracker = EasyTracker.getInstance(this);
        easyTracker.set(Fields.SCREEN_NAME, "ATOM");

        easyTracker.send(MapBuilder
                        .createAppView()
                        .build()
        );
        //google analytics
        
        myTimer = new Timer();
        myTimerTask = new exTimerTask();


        imgBtnBG.setImageResource(R.drawable.bg_btn_true_false);

        //составляем список индексов если он пуст
        if (DATA_MGR.getList_question_length()==0){
            addALLQuestion();
        }else{
            questionListEdit=new int[DATA_MGR.getList_question_length()];
            questionListEdit=DATA_MGR.getList_number_question();
        }


        //выбираем случайный вопрос

        Random r = new Random();
        long seed=System.currentTimeMillis();
        r.setSeed(seed);
        int qnum=0;
        if (questionListEdit.length!=1){
            qnum=r.nextInt(questionListEdit.length-1);
        }
        DATA_MGR.setCurrent_question_number(questionListEdit[qnum]);
        Log.i(LOG_TAG,"ответ № "+allQuestionList.get(DATA_MGR.getCurrent_question_number()).getAnswer());

        questionText.setText(allQuestionList.get(DATA_MGR.getCurrent_question_number()).getQuestion());


        timerText.setText("0");
        myTimer.schedule(myTimerTask, 0, 1000);


    }


    @Override
    protected void onPause() {

        if (!isClick){
            stopTimer();
            this.finish();
        }
        super.onPause();
    }

    public void addALLQuestion(){
    questionListEdit=new int[allQuestionList.size()];
    for(int i=0; i<allQuestionList.size();i++){
        questionListEdit[i]=i;
    }
    DATA_MGR.setList_number_question(questionListEdit);

    }


public void checkQuestionAnswer(boolean answerUser){
    boolean answerRight;


    if (allQuestionList.get(DATA_MGR.getCurrent_question_number()).getAnswer().equals("ВЫМЫСЕЛ")){
        answerRight=false;
    }else{
        answerRight=true;
    }



    if (questionListEdit.length==1){
        addALLQuestion();
    }

    if (answerRight==answerUser){
        rightAnswer();
    }else{
        wrongAnswer();
    }
}
    public void rightAnswer(){
        stopTimer();

        //write spent time for question
        String answer_time=String.valueOf(DATA_MGR.getCurrent_question_number())+"="+timerText.getText();
        int ind=DATA_MGR.getUser_time_answer_length();
        userTimeAnswerEdit=new String[ind+1];

        String[] user_time_answer_copy=DATA_MGR.getUser_time_answer();

        for (int j=0;j<ind;j++ )
            userTimeAnswerEdit[j]=user_time_answer_copy[j];


        userTimeAnswerEdit[ind]=answer_time;
        DATA_MGR.setUser_time_answer(userTimeAnswerEdit);
        //-----------------


        Intent intent=new Intent(this,AnswerActivity.class);
        intent.putExtra("comment",allQuestionList.get(DATA_MGR.getCurrent_question_number()).getComment());

        int[] questionListEditCopy=new int[questionListEdit.length-1];

        int j=0;
        for (int i:questionListEdit){
            if (i!=DATA_MGR.getCurrent_question_number()){
                questionListEditCopy[j]=i;
                j++;
            }
        }
        DATA_MGR.setList_number_question(questionListEditCopy);

        DATA_MGR.setPass_question_count(DATA_MGR.getPass_question_count()+1);


            intent.putExtra("passQuestion",DATA_MGR.getPass_question_count());
            intent.putExtra("answer",true);
            startActivity(intent);


    }
    public void wrongAnswer(){
      stopTimer();

        Intent intent=new Intent(this,AnswerActivity.class);
        intent.putExtra("comment",allQuestionList.get(DATA_MGR.getCurrent_question_number()).getComment());

            intent.putExtra("passQuestion",DATA_MGR.getPass_question_count());
            intent.putExtra("answer",false);
            startActivity(intent);

    }


public void stopTimer(){
    timeCount=0;

    if (myTimer != null) {
        myTimer.cancel();
        myTimer.purge();

        myTimer = null;
        myTimerTask.cancel();
    }
}
    public void loadListQuestionFromXML(){

        boolean keyFlag=false;
        boolean valueFlag=false;
        int index=0;

        if (allQuestionList==null)
            allQuestionList=new ArrayList<QuestionItem>();
        else
            return;

        int element=-1;
        String id="",question="",comment="",answer="";

        try {
            XmlPullParser xpp = prepareXpp();
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                switch (xpp.getEventType()) {
                    // начало документа
                    case XmlPullParser.START_DOCUMENT:
                        Log.d(LOG_TAG, "START_DOCUMENT");
                        break;
                    // начало тэга
                    case XmlPullParser.START_TAG:

                        if(xpp.getName().equals("dict"))
                            element++;
                        if (xpp.getName().equals("key") && element>=0)
                            keyFlag=true;
                        if (xpp.getName().equals("string") && element>=0)
                            valueFlag=true;
                        break;
                    // конец тэга
                    case XmlPullParser.END_TAG:

                        if (keyFlag && valueFlag && keyNameStatus>0){
                            keyFlag=false;
                            valueFlag=false;
                            keyNameStatus=0;
                        }
                        if(xpp.getName().equals("dict")){
                            allQuestionList.add(new QuestionItem(id, question, comment, answer));
//                            Log.i(LOG_TAG,"-----------------------------------------------");
//                            Log.i(LOG_TAG,"id="+allQuestionList.get(index).getId());
//                            Log.i(LOG_TAG,"question="+allQuestionList.get(index).getQuestion());
//                            Log.i(LOG_TAG,"answer="+allQuestionList.get(index).getAnswer());
//                            Log.i(LOG_TAG,"comment="+allQuestionList.get(index).getComment());
//                            Log.i(LOG_TAG,"-----------------------------------------------");

                            index++;

                            question="";
                            comment="";
                            answer="";

                            keyFlag=false;
                            valueFlag=false;
                            keyNameStatus=0;
                            //Log.d(LOG_TAG, "END_TAG: count = " + allQuestionList.size());

                        }

                        break;
                    // содержимое тэга
                    case XmlPullParser.TEXT:

                        if (valueFlag && keyNameStatus>0){
                            switch (keyNameStatus){
                                case 1:
                                    answer=xpp.getText();
                                    break;
                                case 2:
                                    comment=xpp.getText();
                                    break;
                                case 3:
                                    id=xpp.getText();
                                    break;
                                case 4:
                                    question=xpp.getText();
                                    break;

                            }
                        }

                        if (keyFlag && keyNameStatus==0){
                            switch (keyName.valueOf(xpp.getText())){
                                case ANSWER:
                                    keyNameStatus=1;
                                    break;
                                case COMMENT:
                                    keyNameStatus=2;
                                    break;
                                case ID:
                                    keyNameStatus=3;
                                    break;
                                case QUESTION:
                                    keyNameStatus=4;
                                    break;
                                default:
                                    break;
                            }


                        }
                        break;

                    default:
                        break;
                }
                // следующий элемент
                xpp.next();
            }
            Log.d(LOG_TAG, "END_DOCUMENT");

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    XmlPullParser prepareXpp() {
        return getResources().getXml(R.xml.list_questions);
    }




    class exTimerTask extends TimerTask {

        @Override
        public void run() {

            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    timeCount=Integer.valueOf(String.valueOf(timerText.getText()));
                    if (timeCount>=20){
                        wrongAnswer();
                    }else{
                        timeCount++;
                        timerText.setText(String.valueOf(timeCount));
                    }

                }
            });
        }
    }
}


