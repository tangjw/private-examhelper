package com.zonsim.examhelper;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.zonsim.examhelper.entity.ExamQuestion;

public class QuestionFragment extends Fragment {
    
    private static final String ARG_PARAM1 = "question";
    private ExamQuestion mParam1;
    
    
    public static QuestionFragment newInstance(ExamQuestion examQuestion) {
        QuestionFragment fragment = new QuestionFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, examQuestion);
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = (ExamQuestion) getArguments().getSerializable(ARG_PARAM1);
        }
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_question, container, false);
        
        //Find the +1 button
        // mPlusOneButton = (Button) view.findViewById(R.id.plus_one_button);
        TextView textView = (TextView) view.findViewById(R.id.tv_key);
        textView.setText(mParam1.getQKey());
        TextView textView1 = (TextView) view.findViewById(R.id.tv_value);
        textView1.setText(mParam1.getQValue());
        return view;
    }
    
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
       /* if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }
    
}
