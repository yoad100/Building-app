package com.example.endproject.Fragments.HomeManager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.endproject.Bill;
import com.example.endproject.MainActivity;
import com.example.endproject.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TenentDetails#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TenentDetails extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TenentDetails() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TenentDetails.
     */
    // TODO: Rename and change types and number of parameters
    public static TenentDetails newInstance(String param1, String param2) {
        TenentDetails fragment = new TenentDetails();
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
        final View MainView = inflater.inflate(R.layout.fragment_tenent_details, container, false);
        Button btn = MainView.findViewById(R.id.month_payed_btn);
        final TextView result = MainView.findViewById(R.id.month_payed_result);
        btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                EditText inText = MainView.findViewById(R.id.month_payed_input);
                final String departmentNum = inText.getText().toString();
                MainActivity main = (MainActivity)getActivity();
                ArrayList<Bill>bills = main.getBills();
                result.setText("התשלומים בוצעו בחודשים:\n");
                for(Bill bill : bills){
                    if((bill.getDepartmentNum()+"").equals(departmentNum))
                        result.append(" "+bill.getMonth()+" ");
                }
            }
        });
        return MainView;
    }
}