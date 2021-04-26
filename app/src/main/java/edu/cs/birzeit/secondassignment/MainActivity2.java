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

public class MainActivity2 extends AppCompatActivity {

    private EditText txtw;
    private EditText txte;
    public static final String WORK="WORK";
    public static final String EDU="EDU";
    public static final String FLAG="FLAG";
    private boolean flag2=false;
    private SharedPreferences prefs2;
    private SharedPreferences.Editor editor2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setup();
        setupShared();
        checkprefs();
    }

    private void checkprefs() {

        flag2 =prefs2.getBoolean(FLAG, false);
        if(flag2) {
            String work = prefs2.getString(WORK, "");
            String edu = prefs2.getString(EDU, "");
            txtw.setText(work);
            txte.setText(edu);

        }
    }

    private void setupShared() {
        prefs2= PreferenceManager.getDefaultSharedPreferences(this);
        editor2=prefs2.edit();
    }

    private void setup() {

        txtw =findViewById(R.id.txtw);
        txte =findViewById(R.id.txte);
    }

    public void btn_onClick(View view) {

        InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(view.getWindowToken(), 0);

        String work=txtw. getText(). toString();
        String edu=txte. getText(). toString();
            if (flag2) {
                editor2.putString(WORK, work);
                editor2.putString(EDU, edu);
                editor2.putBoolean(FLAG, true);
                editor2.commit();
            }
        Intent intent = new Intent(this, MainActivity3.class);
        startActivity(intent);

    }

    public void onSave(View view) {
        String work=txtw. getText(). toString();
        String experience=txte. getText(). toString();

        Experience[] Wrkex=new Experience[2];
        Wrkex[0]=new Experience("Worked at Beirzeit University", "Studied at betunia school");
        Wrkex[1]=new Experience(work, experience);

        SharedPreferences prefs2 = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor2 = prefs2.edit();
        Gson gson=new Gson();

        String workandexper=gson.toJson(Wrkex);
        editor2.putString("123",workandexper);
        editor2.commit();
        Toast.makeText(this, "Data Saved:\n" + workandexper,
                Toast.LENGTH_SHORT).show();
    }

    public void onLoad(View view) {
        SharedPreferences prefs3 = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson=new Gson();
        String str = prefs2.getString("123", "");
        personal[] Wrkex = gson.fromJson(str, personal[].class);
        Toast.makeText(this, "number of Experiences and Educations  " + Wrkex.length
                , Toast.LENGTH_SHORT).show();

    }
}