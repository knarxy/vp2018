package de.vp.dennis.vp2018;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {


    private int perm_internet;
    public static SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor editor = sp.edit();

        //    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
          //      WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted

                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.INTERNET},
                        perm_internet);

                // perm_intenet is an
                // app-defined int constant. The callback method gets the
                // result of the request.

        } else {
            // Permission has already been granted
        }

        String klasse = sp.getString("Klasse", null);
        if(klasse != null && klasse != ""){
            startIntent();
        }

        final Spinner spinner = findViewById(R.id.spinnerKlassen);
        spinner.setVisibility(View.VISIBLE);
        spinner.setBackgroundColor(Color.TRANSPARENT);

        final Button button = findViewById(R.id.but2);
        button.setVisibility(View.VISIBLE);
        button.setBackgroundColor(Color.TRANSPARENT);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(spinner.getSelectedItem().equals("5a")){
                    editor.putString("Klasse", "w00001");
                }else if(spinner.getSelectedItem().equals("5b")){
                    editor.putString("Klasse", "w00002");
                }else if(spinner.getSelectedItem().equals("5c")){
                    editor.putString("Klasse", "w00003");
                }else if(spinner.getSelectedItem().equals("5d")){
                    editor.putString("Klasse", "w00004");
                }else if(spinner.getSelectedItem().equals("6a")){
                    editor.putString("Klasse", "w00005");
                }else if(spinner.getSelectedItem().equals("6b")){
                    editor.putString("Klasse", "w00006");
                }else if(spinner.getSelectedItem().equals("6c")){
                    editor.putString("Klasse", "w00007");
                }else if(spinner.getSelectedItem().equals("6d")){
                    editor.putString("Klasse", "w00008");
                }else if(spinner.getSelectedItem().equals("7a")){
                    editor.putString("Klasse", "w00009");
                }else if(spinner.getSelectedItem().equals("7b")){
                    editor.putString("Klasse", "w00010");
                }else if(spinner.getSelectedItem().equals("7c")){
                    editor.putString("Klasse", "w00011");
                }else if(spinner.getSelectedItem().equals("7d")){
                    editor.putString("Klasse", "w00012");
                }else if(spinner.getSelectedItem().equals("8a")){
                    editor.putString("Klasse", "w00013");
                }else if(spinner.getSelectedItem().equals("8b")){
                    editor.putString("Klasse", "w00014");
                }else if(spinner.getSelectedItem().equals("8c")){
                    editor.putString("Klasse", "w00015");
                }else if(spinner.getSelectedItem().equals("8d")){
                    editor.putString("Klasse", "w00016");
                }else if(spinner.getSelectedItem().equals("9a")){
                    editor.putString("Klasse", "w00017");
                }else if(spinner.getSelectedItem().equals("9b")){
                    editor.putString("Klasse", "w00018");
                }else if(spinner.getSelectedItem().equals("9c")){
                    editor.putString("Klasse", "w00019");
                }else if(spinner.getSelectedItem().equals("9d")){
                    editor.putString("Klasse", "w00020");
                }else if(spinner.getSelectedItem().equals("9e")){
                    editor.putString("Klasse", "w00021");
                }else if(spinner.getSelectedItem().equals("10a")){
                    editor.putString("Klasse", "w00022");
                }else if(spinner.getSelectedItem().equals("10b")){
                    editor.putString("Klasse", "w00023");
                }else if(spinner.getSelectedItem().equals("10c")){
                    editor.putString("Klasse", "w00024");
                }else if(spinner.getSelectedItem().equals("10d")){
                    editor.putString("Klasse", "w00025");
                }else if(spinner.getSelectedItem().equals("10e")){
                    editor.putString("Klasse", "w00026");
                }else if(spinner.getSelectedItem().equals("JG1")){
                    editor.putString("Klasse", "w00027");
                }else if(spinner.getSelectedItem().equals("JG2")){
                    editor.putString("Klasse", "w00028");
                }else if(spinner.getSelectedItem().equals("xy")){
                    editor.putString("Klasse", "w00029");
                }

                editor.apply();

                startIntent();
            }
        });

    }

    private void startIntent(){
        Intent intent = new Intent(this, main_vp.class);
        startActivity(intent);
    }

}
