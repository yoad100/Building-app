package com.example.endproject.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.endproject.MainActivity;
import com.example.endproject.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FirstPage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstPage extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FirstPage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment firstPage.
     */
    // TODO: Rename and change types and number of parameters
    public static FirstPage newInstance(String param1, String param2) {
        FirstPage fragment = new FirstPage();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_first_page, container, false);
        final Button button = (Button)view.findViewById(R.id.tenant_btn);
        final Button button2 = (Button)view.findViewById(R.id.houseManager_btn);
        Button[] arr = new Button[]{button,button2};
        for(final Button btn : arr){
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MainActivity mainActivity = (MainActivity) getActivity();
                    Login fragment = new Login();
                    //send the text of the title at the login page
                    Bundle bundle = new Bundle();
                    if(btn.getText().toString().equals("דיירים"))
                        bundle.putString("Champiz","שלום דייר נכבד");
                    else
                        bundle.putString("Champiz","שלום וועד הבית");

                    fragment.setArguments(bundle);
                    //change fragment
                    mainActivity.changeFragment(null,fragment);
                }
            });
        }

        // Inflate the layout for this fragment
        return view;
    }



}