package ru.skyseven.rosatom_game_android;

import android.graphics.Typeface;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ru.skyseven.rosatom_game_android.data_source.DataManager;


public class StatusFragment extends Fragment {

    TextView statusTextLevel;
    TextView statusTextLife;
    TextView statusTextQuestion;

    public DataManager DATA_MGR;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        String font = getResources().getString(R.string.status_text_font);

        View statFrag=inflater.inflate(R.layout.fragment_status_fragment, container, false);


        Typeface statusTypeface = Typeface.createFromAsset(getActivity().getAssets(), font);

        statusTextLevel = (TextView)statFrag.findViewById(R.id.statusLevelText);
        statusTextLife = (TextView)statFrag.findViewById(R.id.statusLifeText);
         statusTextQuestion = (TextView)statFrag.findViewById(R.id.statusQuestionText);

        statusTextLevel.setTypeface(statusTypeface);
        statusTextLife.setTypeface(statusTypeface);
        statusTextQuestion.setTypeface(statusTypeface);

        return statFrag;
    }



    @Override
    public void onStart() {
        super.onStart();

        DATA_MGR=new DataManager(getActivity());

        statusTextLevel.setText(String.valueOf( DATA_MGR.getCurrent_level()));

        //DATA_MGR.setCurrent_question_number(3);
        String life=String.valueOf(DATA_MGR.getLife_count())+"/3";
        statusTextLife.setText(life);

        String passQuestion=String.valueOf(DATA_MGR.getPass_question_count())+"/8";
        statusTextQuestion.setText(passQuestion);
    }

    //    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//
//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        try {
//            mListener = (OnFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        public void onFragmentInteraction(Uri uri);
//    }

}
