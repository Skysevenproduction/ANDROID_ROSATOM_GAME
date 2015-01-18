package ru.skyseven.rosatom_game_android.data_source;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.MapBuilder;

import java.util.List;

/**
 * Created by aleksey.m on 07.12.14.
 */
public class DataManager {

    SharedPreferences sPref;

    static final public String    U_Q_CURRENT="current_question";
    final String    U_Q_PASS="pass_question";
    final String    U_LIFE_COUNT="life_count";
    final String    U_CURRENT_LEVEL="current_level";
    final String    U_Q_LIST="list_question";
    final String    U_Q_LIST_LENGTH="list_question_length";
    final String    U_MAP_LEVEL="show_map_level";
    final String    U_TIME_ANSWER="user_time_answer";
    final String    U_TIME_ANSWER_LENGTH="user_time_answer_length";

    public int current_question_number;
    public int life_count;
    public int current_level;
    public int pass_question_count;

    public boolean showMapLevel;

    public int[] list_number_question;
    public int list_question_length;


    public String[] user_time_answer;
    public int user_time_answer_length;



    static final String LOG_TAG="RA_GAME";


    public List<QuestionItem> questionList;

    public List<QuestionItem> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<QuestionItem> questionList) {
        this.questionList = questionList;
    }

    private static String prefsFileName = "rosatom_prefs";
    private SharedPreferences prefs;

    public DataManager(Context context) {
        prefs = context.getSharedPreferences(prefsFileName, Context.MODE_PRIVATE);
    }

    public SharedPreferences getPreferences() {
        return prefs;
    }

    public boolean isShowMapLevel() {
        sPref = getPreferences();
        int showMapLevelPref = sPref.getInt(U_MAP_LEVEL,0);
        if (showMapLevelPref==0){
            showMapLevel=false;
        }else{
            showMapLevel=true;
        }

        return showMapLevel;
    }

    public void setShowMapLevel(boolean showMapLevel) {
        sPref =getPreferences();
        SharedPreferences.Editor editPref = sPref.edit();
        if (showMapLevel){
            editPref.putInt(U_MAP_LEVEL, 1);
        }else{
            editPref.putInt(U_MAP_LEVEL, 0);
        }
        editPref.commit();
        this.showMapLevel = showMapLevel;
    }

    public int getCurrent_question_number() {
        sPref = getPreferences();
        int current_question_number = sPref.getInt(U_Q_CURRENT,-1);

        return current_question_number;
    }

    public void setCurrent_question_number(int current_question_number) {
        sPref =getPreferences();
        SharedPreferences.Editor editPref = sPref.edit();
        editPref.putInt(U_Q_CURRENT, current_question_number);
        editPref.commit();

        this.current_question_number = current_question_number;
    }

    public int getLife_count() {
        sPref = getPreferences();
        int life_count = sPref.getInt(U_LIFE_COUNT,-1);
        return life_count;
    }

    public void setLife_count(int life_count) {
        sPref =getPreferences();
        SharedPreferences.Editor editPref = sPref.edit();
        editPref.putInt(U_LIFE_COUNT, life_count);
        editPref.commit();

        this.life_count = life_count;
    }

    public int getCurrent_level() {
        sPref = getPreferences();
        int current_level = sPref.getInt(U_CURRENT_LEVEL,1);
        return current_level;
    }

    public void setCurrent_level(int current_level) {
        sPref =getPreferences();
        SharedPreferences.Editor editPref = sPref.edit();
        editPref.putInt(U_CURRENT_LEVEL, current_level);
        editPref.commit();

        this.current_level = current_level;
    }

    public int getPass_question_count() {
        sPref = getPreferences();
        int pass_question_count = sPref.getInt(U_Q_PASS,-1);
        return pass_question_count;
    }

    public void setPass_question_count(int pass_question_count) {
        sPref =getPreferences();
        SharedPreferences.Editor editPref = sPref.edit();
        editPref.putInt(U_Q_PASS, pass_question_count);
        editPref.commit();

        this.pass_question_count = pass_question_count;
    }

    public int[] getList_number_question() {
        sPref = getPreferences();
        String listQuestion = sPref.getString(U_Q_LIST,"");

        String[] tokens = listQuestion.split("/");

        int[] list_number_question=new int[tokens.length];

        for (int i = 0; i <tokens.length ; i++) {
            list_number_question[i]=Integer.parseInt(tokens[i]);
        }

        return list_number_question;
    }

    public void setList_number_question(int[] list_number_question) {
        String listQuestion="";
        for (int i:list_number_question ){
            listQuestion=listQuestion+"/"+String.valueOf(i);
        }
        listQuestion=listQuestion.substring(1);
        sPref =getPreferences();
        SharedPreferences.Editor editPref = sPref.edit();
        editPref.putString(U_Q_LIST, listQuestion);
        editPref.commit();
        this.setList_question_length(list_number_question.length);
        
        this.list_number_question = list_number_question;
    }
    public String[] getUser_time_answer() {

        sPref = getPreferences();
        String listQuestion = sPref.getString(U_TIME_ANSWER,"");

        String[] tokens = listQuestion.split("/");

        String[] user_time_answer=new String[tokens.length];

        for (int i = 0; i <tokens.length ; i++) {
            user_time_answer[i]=tokens[i];
        }

        this.setUser_time_answer_length(user_time_answer.length);

        return user_time_answer;
    }

    public void setUser_time_answer(String[] user_time_answer) {
        String listTimeAnswer="";
        for (String s:user_time_answer ){
            listTimeAnswer=listTimeAnswer+"/"+s;
        }
        listTimeAnswer=listTimeAnswer.substring(1);

        sPref =getPreferences();
        SharedPreferences.Editor editPref = sPref.edit();
        editPref.putString(U_TIME_ANSWER, listTimeAnswer);
        editPref.commit();

        this.setUser_time_answer_length(user_time_answer.length);


        this.user_time_answer = user_time_answer;
    }

    public int getUser_time_answer_length() {
        sPref=getPreferences();
        int user_time_answer_length=sPref.getInt(U_TIME_ANSWER_LENGTH,0);
        return user_time_answer_length;
    }

    public void setUser_time_answer_length(int user_time_answer_length) {

        sPref =getPreferences();
        SharedPreferences.Editor editPref = sPref.edit();
        editPref.putInt(U_TIME_ANSWER_LENGTH, user_time_answer_length);
        editPref.commit();

        this.user_time_answer_length = user_time_answer_length;
    }

    public int getList_question_length() {
        sPref = getPreferences();
        int list_question_length = sPref.getInt(U_Q_LIST_LENGTH,0);

        return list_question_length;
    }

    public void setList_question_length(int list_question_length) {
        sPref =getPreferences();
        SharedPreferences.Editor editPref = sPref.edit();
        editPref.putInt(U_Q_LIST_LENGTH, list_question_length);
        editPref.commit();

        this.list_question_length = list_question_length;
    }

    public void ga_send_event(Context context,String category,String action,String label,long value){
        // May return null if a EasyTracker has not yet been initialized with a
        // property ID.
        EasyTracker easyTracker = EasyTracker.getInstance(context);

        // MapBuilder.createEvent().build() returns a Map of event fields and values
        // that are set and sent with the hit.
        easyTracker.send(MapBuilder
                        .createEvent(category,     // Event category (required)
                                action,  // Event action (required)
                                label,   // Event label
                                value)            // Event value
                        .build()
        );
    }
}
