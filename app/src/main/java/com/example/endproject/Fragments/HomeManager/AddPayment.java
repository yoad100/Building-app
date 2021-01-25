package com.example.endproject.Fragments.HomeManager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.endproject.MainActivity;
import com.example.endproject.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddPayment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddPayment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddPayment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Add_payment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddPayment newInstance(String param1, String param2) {
        AddPayment fragment = new AddPayment();
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
        final View mainView = inflater.inflate(R.layout.fragment_add_payment, container, false);
        Button pay = mainView.findViewById(R.id.payment_btn);

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText departmentNumText = mainView.findViewById(R.id.department_num_input);
                EditText monthText = mainView.findViewById(R.id.month_input);
                EditText paymentText = mainView.findViewById(R.id.payment_input);
                final int departmentNum = Integer.parseInt(departmentNumText.getText().toString());
                final int month = Integer.parseInt(monthText.getText().toString());
                final int money = Integer.parseInt(paymentText.getText().toString());
                MainActivity main = (MainActivity)getActivity();
                main.payBills(null,departmentNum,month,money);
            }
        });
        return mainView;
    }
}