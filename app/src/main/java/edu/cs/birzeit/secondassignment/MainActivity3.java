package edu.cs.birzeit.secondassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

public class MainActivity3 extends AppCompatActivity {

    private EditText txtw;
    private EditText txte;
    public static final String HOBBIES="HOBBIES";
    public static final String SKILLS="SKILLS";
    public static final String FLAG="FLAG";
    private boolean flag=false;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        setup();
        setupShared();
        checkPrefs();
    }

    private void checkPrefs() {

        flag =prefs.getBoolean(FLAG, false);
        if(flag) {
            String hobbies = prefs.getString(HOBBIES, "");
            String skill = prefs.getString(SKILLS, "");
            txtw.setText(hobbies);
            txte.setText(skill);

        }
    }

    private void setupShared() {
        prefs= PreferenceManager.getDefaultSharedPreferences(this);
        editor=prefs.edit();
    }

    private void setup() {

        txtw =findViewById(R.id.txtw);
        txte =findViewById(R.id.txte);
    }

    public void btn_onClick(View view) {

        InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(view.getWindowToken(), 0);

        String hobbies=txtw. getText(). toString();
        String skill=txte. getText(). toString();

            if (flag) {
                editor.putString(HOBBIES, hobbies);
                editor.putString(SKILLS, skill);
                editor.putBoolean(FLAG,true);
                editor.commit();
            }


        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onSave(View view) {
        String hobb=txtw. getText(). toString();
        String skill=txte. getText(). toString();

        HobbSkill[] hobskil=new HobbSkill[1];
        hobskil[0]=new HobbSkill(hobb, skill);

        SharedPreferences prefs2 = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor2 = prefs2.edit();
        Gson gson=new Gson();

        String hobandskil=gson.toJson(hobskil);
        editor2.putString("123",hobandskil);
        editor2.commit();
        Toast.makeText(this, "Data Saved:\n" + hobandskil,
                Toast.LENGTH_SHORT).show();
    }

    public void onLoad(View view) {
        SharedPreferences prefs3 = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson=new Gson();
        String str = prefs.getString("123", "");
        personal[] hobskil = gson.fromJson(str, personal[].class);
        Toast.makeText(this, "number of Hobbies and Skills  " + hobskil.length
                , Toast.LENGTH_SHORT).show();

    }
}