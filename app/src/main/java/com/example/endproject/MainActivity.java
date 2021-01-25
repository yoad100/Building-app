package com.example.endproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import com.example.endproject.Fragments.FirstPage;
import com.example.endproject.Fragments.HomeManager_Page;
import com.example.endproject.Fragments.Tenent_Page;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    String uid;
    DatabaseReference myRef;
    FirebaseUser user;
    //the reason is that current tenent is an array with one cell
    // is for initialize that tenent inside inner class
    final private Tenent[] currentTenent = new Tenent[1];
    //all of the persons from the database container
    final private ArrayList<Person> persons=new ArrayList<Person>();
    // every bill object from the database container
    final private ArrayList<Bill> bills = new ArrayList<>();


    public Tenent getCurrentTenent() {
        return currentTenent[0];
    }
    public ArrayList<Person> getPersons() {
        return persons;
    }
    public ArrayList<Bill> getBills() {
        return bills;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.main_frame, new FirstPage()).commit();
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Write a message to the database
        database = FirebaseDatabase.getInstance();
        //fill the ArrayLists with data
        getPersonData(persons);
        getBillsData(bills);
        user = mAuth.getCurrentUser();
    }

    //fetch all the users from the database
    public void getPersonData(final ArrayList<Person> arr){
        for(int i = 0;i<2;i++){
            final int index = i;
            myRef = index==0?database.getReference("Tenent"):database.getReference("HomeManager");
            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
                        Person person = index==0?singleSnapshot.getValue(Tenent.class):singleSnapshot.getValue(HomeManager.class);
                        arr.add(person);
                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.d("yoad","error");
                }
            });
        }

    }
    //fetch the current tenent that logged in
    public void fetchCurrentTenent(){
        final String uid = user.getUid();
        myRef = database.getReference("Tenent").child(uid);
        Log.d("yoad","fetch Curr");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                currentTenent[0] =dataSnapshot.getValue(Tenent.class);
                changeFragment(null,new Tenent_Page());
            }

    @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("yoad","wrong");
            }
        });

    }

    //fetch all the bills data from database
    public void getBillsData(final ArrayList<Bill>arr){
        myRef = database.getReference("Bills");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
                    Bill bill = singleSnapshot.getValue(Bill.class);
                    arr.add(bill);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("yoad","error");
            }
        });
    }


//change fragment method (called from the fragments themself)
    public void changeFragment(View view, Fragment f) {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, f).addToBackStack(null).commit();
    }
//manage fragments in the HomeManager page fragment
    public void HomeManager_Page_handler(View view, Fragment f) {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.home_manager_page_frame, f).addToBackStack(null).commit();
    }



    //Register function
    public void signUp(View view) {
        EditText idText = findViewById(R.id.id_register);
        EditText passwordText = findViewById(R.id.password_register);
        EditText emailText = findViewById(R.id.email_register);
        EditText firstNameText = findViewById(R.id.first_name_register);
        EditText lastNameText = findViewById(R.id.last_name_register);
        EditText departmentText = findViewById(R.id.department_num_register);
        EditText dynamicText = findViewById(R.id.dynamic_register);

        //getUID

        final String id = idText.getText().toString();
        final String password = passwordText.getText().toString();
        final String email = emailText.getText().toString();
        final String firstName = firstNameText.getText().toString();
        final String lastName = lastNameText.getText().toString();
        final String department = departmentText.getText().toString();
        final Switch switcher = findViewById(R.id.switch_register);
        final String dynamic = dynamicText.getText().toString();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(MainActivity.this, "register Succeded!.",
                                    Toast.LENGTH_SHORT).show();
                            //check if this is an HomeManager or Tenent base on the the switch text
                            String typeOfPerson = switcher.getText().toString();
                            uid = user.getUid();
                            if (typeOfPerson.equals("וועד בית")) {
                                myRef = database.getReference("HomeManager").child(uid);
                                HomeManager person = new HomeManager(id, firstName, password, email, lastName, Integer.parseInt(dynamic));
                                myRef.setValue(person);
                                persons.add(person);
                            } else {
                                myRef = database.getReference("Tenent").child(uid);
                                Tenent person = new Tenent(id, firstName, lastName, password, email, Integer.parseInt(department), Integer.parseInt(dynamic));
                                myRef.setValue(person);
                                persons.add(person);
                            }

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainActivity.this, "register failed.",
                                    Toast.LENGTH_SHORT).show();
                        }


                    }
                });
    }

    //sign in function
    public void loginFunc(View view){

            EditText mailText = findViewById(R.id.email_login);
            EditText passwordText = findViewById(R.id.password_login);


        final String email = mailText.getText().toString();
        final String password = passwordText.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(MainActivity.this, "Authentication succeded!.",
                                    Toast.LENGTH_SHORT).show();
                            int counter =0;

                            for(Person p : persons){
                                if(p instanceof HomeManager && p.getEmail().equals(email))
                                    changeFragment(null,new HomeManager_Page());
                                else if(p instanceof Tenent && p.getEmail().equals(email))
                                    //execute the method after tenent login success
                                    fetchCurrentTenent();
                            }
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });

    }

    //update password by recive an email and update on the server
    public void updatePassword(View view) {
        EditText email = findViewById(R.id.email_new_password);

        mAuth.sendPasswordResetEmail(email.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Upadte password sent to the mail!",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        }
                    }

                });
    }
    //sign new bills to the database
    public void payBills(View view,int depertmentNum,int month,int money){
        uid = user.getUid();
        myRef = database.getReference("Bills");
        Bill bill = new Bill(month,money,depertmentNum);
        myRef.push().setValue(bill);
        Toast.makeText(MainActivity.this,"Payment Succedded!",Toast.LENGTH_LONG).show();
    }

}
