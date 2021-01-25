package com.example.endproject.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.endproject.Fragments.HomeManager.AddPayment;
import com.example.endproject.Fragments.HomeManager.BuildingDetails;
import com.example.endproject.Fragments.HomeManager.BuildingIncome;
import com.example.endproject.Fragments.HomeManager.TenentDetails;
import com.example.endproject.MainActivity;
import com.example.endproject.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeManager_Page#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeManager_Page extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeManager_Page() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeManager.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeManager_Page newInstance(String param1, String param2) {
        HomeManager_Page fragment = new HomeManager_Page();
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
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_home_manager, container, false);;
        Button addPayment = view.findViewById(R.id.add_payment_btn);
        Button TenentDetails = view.findViewById(R.id.depertment_payments_btn);
        Button  BuildingDetails= view.findViewById(R.id.sum_of_payments_btn);
        Button BuildingIncome = view.findViewById(R.id.month_income_btn);
        //set an onclick to each button at the HomeManager_Page
        for(int i =0;i<4;i++){
            Button btn;
            final int index = i;
            switch (index){
                case 0:btn=addPayment;
                break;
                case 1:btn = TenentDetails;
                break;
                case 2:btn = BuildingIncome;
                break;
                case 3:btn = BuildingDetails;
                break;
                default:btn = new Button(getActivity());
            }
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MainActivity main = (MainActivity) getActivity();
                    if(index == 0)
                        main.HomeManager_Page_handler(view,new AddPayment());
                    else if(index == 1)
                        main.HomeManager_Page_handler(view,new TenentDetails());
                    else if(index == 2)
                        main.HomeManager_Page_handler(view,new BuildingIncome());
                    else
                        main.HomeManager_Page_handler(view,new BuildingDetails());
                }
            });
        }

        return view;
    }
}