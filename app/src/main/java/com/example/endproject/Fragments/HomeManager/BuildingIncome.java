package com.example.endproject.Fragments.HomeManager;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.endproject.Bill;
import com.example.endproject.MainActivity;
import com.example.endproject.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BuildingIncome#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BuildingIncome extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BuildingIncome() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BuildingIncome.
     */
    // TODO: Rename and change types and number of parameters
    public static BuildingIncome newInstance(String param1, String param2) {
        BuildingIncome fragment = new BuildingIncome();
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
        View view = inflater.inflate(R.layout.fragment_building_details, container, false);
        MainActivity main = (MainActivity) getActivity();
        ArrayList<Bill> bills = main.getBills();
        TableLayout table = view.findViewById(R.id.building_details_table);

        //set the titles of the table
        TableRow trTitles = new TableRow(getActivity());
        for (int i = 0; i < 2; i++) {

            TextView tv1 = new TextView(getActivity());
            if (i == 0)
                tv1.setText("month:");
            else
                tv1.setText("amount:");
            tv1.setTextColor(Color.BLACK);
            tv1.setTypeface(tv1.getTypeface(), Typeface.BOLD);
            tv1.setTextSize(25);
            tv1.setPadding(5, 5, 5, 5);
            tv1.setBackgroundResource(R.drawable.input_borders);
            tv1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.33f);
            tv1.setLayoutParams(lp);
            trTitles.addView(tv1);
        }

        table.addView(trTitles);
        //set the rows to disply the income of each month
        for (int i = 1;i<13;i++) {
            int sum =0;
            TableRow trData = new TableRow(getActivity());
            for (Bill bill : bills) {
                if(bill.getMonth()==i)
                    sum += bill.getAmount();
            }
            for(int j =0 ; j<2;j++){
                TextView tv1 = new TextView(getActivity());
                if(j==0)
                    tv1.setText(i+"");
                else
                    tv1.setText(sum+"");
                tv1.setTextColor(Color.BLACK);
                tv1.setTextSize(20);
                tv1.setPadding(5, 5, 5, 5);
                tv1.setBackgroundResource(R.drawable.input_borders);
                tv1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                TableRow.LayoutParams lp = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.33f);
                tv1.setLayoutParams(lp);
                trData.addView(tv1);
            }
            table.addView(trData);
        }
        return view;
    }
}
