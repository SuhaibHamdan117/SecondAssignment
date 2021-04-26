package edu.cs.birzeit.secondassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    Spinner spinl;
    private EditText txtf;
    private EditText txtl;
    private EditText txte;
    private EditText txtp;
    private EditText txtpa;
    CheckBox boxRe2;
    public static final String FIRST="FIRST";
    public static final String LAST="LAST";
    public static final String EMAIL="EMAIL";
    public static final String GENDER="GENDER";
    public static final String PHONE="PHONE";
    public static final String ADDRESS="ADDRESS";
    public static final String STATUS="STATUS";
    public static final String FLAG="FLAG";
    private boolean flag=false;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    RadioButton ragf;
    RadioButton ragm;
    RadioGroup rgroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();
        setupShared();
        checkPrefs();
        populateSpinner();


    }

    private void checkPrefs() {
        flag =prefs.getBoolean(FLAG, false);
        if(flag){
            String first =prefs.getString(FIRST,"");
            String last=prefs.getString(LAST,"");
            String email=prefs.getString(EMAIL,"");
            String phone =prefs.getString(PHONE,"");
            String address=prefs.getString(ADDRESS,"");
            String gender=prefs.getString(GENDER,"");
            int status = prefs.getInt("STATUS",0);
            txtf.setText(first);
            txtl.setText(last);
            txte.setText(email);
            txtp.setText(phone);
            txtpa.setText(address);
            ragf.setChecked(prefs.getBoolean("FEMALE", false));
            ragm.setChecked(prefs.getBoolean("Male", false));
            spinl.setSelection(status);
            boxRe2.setChecked(true);
        }
    }

    private void setupShared() {
        prefs= PreferenceManager.getDefaultSharedPreferences(this);
        editor=prefs.edit();
    }


    private void setup() {

        txtf =findViewById(R.id.txtf);
        txtl =findViewById(R.id.txtl);
        txte =findViewById(R.id.txte);
        txtp =findViewById(R.id.txtp);
        txtpa =findViewById(R.id.txtpa);
        spinl =(Spinner) findViewById(R.id.spinl);
        boxRe2=findViewById(R.id.boxRe2);
        rgroup =findViewById(R.id.rgroup);
        ragf =findViewById(R.id.ragf);
        ragm =findViewById(R.id.ragm);
    }

    private void populateSpinner() {
        ArrayList<String> data=new ArrayList<>();
        data.add("Married");
        data.add("Single");
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,data);
        spinl.setAdapter(adapter);
    }

    public void btn_onClick(View view) {

        InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(view.getWindowToken(), 0);

        String first=txtf. getText(). toString();
        String last=txtl. getText(). toString();
        String email=txte. getText(). toString();
        String phone=txtp. getText(). toString();
        String address=txtpa. getText(). toString();
        int status = spinl.getSelectedItemPosition();
        if(boxRe2.isChecked()){
            if(!flag){
                editor.putString(FIRST,first);
                editor.putString(LAST,last);
                editor.putString(EMAIL,email);
                editor.putString(PHONE,phone);
                editor.putString(ADDRESS,address);
                spinl.setSelection(status);
                editor.putBoolean("FEMALE", ragf.isChecked());
                editor.putBoolean("Male", ragm.isChecked());
                editor.putInt(STATUS,status);
                editor.putBoolean(FLAG,true);
                editor.commit();
            }
          }
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

    public void onSave(View view) {
        String first=txtf. getText(). toString();
        String last=txtl. getText(). toString();
        String email=txte. getText(). toString();
        String phone=txtp. getText(). toString();
        String address=txtpa. getText(). toString();
        int temp=spinl.getSelectedItemPosition();
        String stat=spinl.getSelectedItem().toString();
        int genid=rgroup.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) rgroup.findViewById(genid);
        String gender=radioButton.getText().toString();
        personal[] pers=new personal[3];
        pers[0]=new personal("suhaib", "hamdan", "suhaib@hotmail.com","Male", "05959"," betunia", "Single");
        pers[1]=new personal("omar", "hamdan", "omar@hotmail.com","Male", "05959123"," betunia", "Married");
        pers[2]=new personal(first, last, email,gender, phone,address, stat);

        SharedPreferences prefs2 = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor2 = prefs2.edit();
        Gson gson=new Gson();

        String personalinfo=gson.toJson(pers);
        editor2.putString("123",personalinfo);
        editor2.commit();
        Toast.makeText(this, "Data Saved:\n" + personalinfo,
                Toast.LENGTH_SHORT).show();
    }

    public void onLoad(View view) {
        SharedPreferences prefs3 = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson=new Gson();
        String str = prefs.getString("123", "");
        personal[] pers = gson.fromJson(str, personal[].class);
        Toast.makeText(this, "number of CVs  " + pers.length
                , Toast.LENGTH_SHORT).show();

    }
}