package com.example.endproject.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.endproject.MainActivity;
import com.example.endproject.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Login#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Login extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Login() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Login.
     */
    // TODO: Rename and change types and number of parameters
    public static Login newInstance(String param1, String param2) {
        Login fragment = new Login();
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
        View view = inflater.inflate(R.layout.fragment_login,container,false);
        TextView registerLink = view.findViewById(R.id.register_link);
        TextView forgotPasswordLink = view.findViewById(R.id.forgot_password);
        TextView title = view.findViewById(R.id.login_title);
        Button login_btn = view.findViewById(R.id.login_btn);
        //get the bundle argument that passed and set the title(at the login page)
        Bundle bundle = this.getArguments();
        if(bundle!=null)
            title.setText(bundle.getString("Champiz"));
        //add click event that link to the register

        //iterate on the views and addclick event to each one
        for( int i = 0;i<3; i++){
            final View control;
            final int index = i;
            if(index==0)
                control=registerLink;
            else if(index==1)
                control=forgotPasswordLink;
            else
                control = login_btn;
            control.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MainActivity mainActivity = (MainActivity) getActivity();
                   if(index<2){
                       mainActivity.changeFragment(null,
                               index==0?new Register():new NewPassword());
                   }
                   else{
                      mainActivity.loginFunc(null);
                   }
                }

            });
        }
        return view;
    }

}