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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import de.vp.dennis.vp2018.core.DoesUrlExist;
import de.vp.dennis.vp2018.core.InternetCheck;
import de.vp.dennis.vp2018.core.Vertretung;
import de.vp.dennis.vp2018.core.VertretungsHandle;
import de.vp.dennis.vp2018.utils.OnSwipeTouchListener;

public class main_vp extends AppCompatActivity {

    private static String monday = "", tuesday = "", wednesday = "", thursday = "", friday = "";
    private static String dayoweek = "";
    private int perm_internet;
    public static SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_vp);


        sp = PreferenceManager.getDefaultSharedPreferences(this);

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

        //   getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        //         WindowManager.LayoutParams.FLAG_FULLSCREEN);

        final ImageView iv = findViewById(R.id.imageView);

        if (sp.getString("Klasse", null) == null || sp.getString("Klasse", null) == "") {
            intentToMenu();
        } else {

            startup.start();
            try {
                startup.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            final Button settings = findViewById(R.id.settings);
            settings.setVisibility(View.VISIBLE);
            settings.setBackgroundColor(Color.TRANSPARENT);
            settings.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startIntent();
                }
            });

            final Button monday = findViewById(R.id.monday);
            monday.setVisibility(View.VISIBLE);
            monday.setBackgroundColor(Color.TRANSPARENT);
            monday.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    dayoweek = "montag";
                    iv.setImageResource(R.drawable.montag);
                    Thread m = new Thread(updateVP);
                    m.start();
                    try {
                        m.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

            final Button tuesday = findViewById(R.id.tuesday);
            tuesday.setVisibility(View.VISIBLE);
            tuesday.setBackgroundColor(Color.TRANSPARENT);
            tuesday.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    dayoweek = "dienstag";
                    iv.setImageResource(R.drawable.dienstag);
                    Thread di = new Thread(updateVP);
                    di.start();
                    try {
                        di.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

            final Button wednesday = findViewById(R.id.wednesday);
            wednesday.setVisibility(View.VISIBLE);
            wednesday.setBackgroundColor(Color.TRANSPARENT);
            wednesday.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    dayoweek = "mittwoch";
                    iv.setImageResource(R.drawable.mittwoch);
                    Thread mi = new Thread(updateVP);
                    mi.start();
                    try {
                        mi.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

            final Button thursday = findViewById(R.id.thursday);
            thursday.setVisibility(View.VISIBLE);
            thursday.setBackgroundColor(Color.TRANSPARENT);
            thursday.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    dayoweek = "donnerstag";
                    iv.setImageResource(R.drawable.donnerstag);
                    Thread don = new Thread(updateVP);
                    don.start();
                    try {
                        don.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

            final Button friday = findViewById(R.id.friday);
            friday.setVisibility(View.VISIBLE);
            friday.setBackgroundColor(Color.TRANSPARENT);
            friday.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    dayoweek = "freitag";
                    iv.setImageResource(R.drawable.freitag);
                    Thread fr = new Thread(updateVP);
                    fr.start();
                    try {
                        fr.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

            View v = findViewById(R.id.bgvp);
            v.setOnTouchListener(new OnSwipeTouchListener(v.getContext()) {
                @Override
                public void onSwipeLeft() {
                    if (dayoweek == "montag") {
                        tuesday.performClick();
                    } else if (dayoweek == "dienstag") {
                        wednesday.performClick();
                    } else if (dayoweek == "mittwoch") {
                        thursday.performClick();
                    } else if (dayoweek == "donnerstag") {
                        friday.performClick();
                    }
                }

                @Override
                public void onSwipeRight() {
                    if (dayoweek == "dienstag") {
                        monday.performClick();
                    } else if (dayoweek == "mittwoch") {
                        tuesday.performClick();
                    } else if (dayoweek == "donnerstag") {
                        wednesday.performClick();
                    } else if (dayoweek == "freitag") {
                        thursday.performClick();
                    }
                }
            });
        }
    }

    Thread startup = new Thread(new Runnable() {
        @Override
        public void run() {
            try{

                final ImageView iv = findViewById(R.id.imageView);

                Calendar cal = Calendar.getInstance();
                cal.setFirstDayOfWeek(Calendar.MONDAY);
                cal.setTime(new Date());

                int h = cal.get(Calendar.HOUR_OF_DAY);

                int dow = cal.get(Calendar.DAY_OF_WEEK);

                if(h > 15 && dow != 1 && dow != 7){
                    dow++;
                }

                if(dow == 2){
                    iv.setImageResource(R.drawable.montag);
                    dayoweek = "montag";
                }else if(dow == 3){
                    iv.setImageResource(R.drawable.dienstag);
                    dayoweek = "dienstag";
                }else if(dow == 4){
                    iv.setImageResource(R.drawable.mittwoch);
                    dayoweek = "mittwoch";
                }else if(dow == 5){
                    iv.setImageResource(R.drawable.donnerstag);
                    dayoweek = "donnerstag";
                }else if(dow == 6){
                    iv.setImageResource(R.drawable.freitag);
                    dayoweek = "freitag";
                }else if(dow == 1 || dow == 7){
                    iv.setImageResource(R.drawable.montag);
                    dayoweek = "montag";
                }


                int weekNr = cal.get(Calendar.WEEK_OF_YEAR);

                System.out.println("Tag Nummer: "+dow);
                // SONNTAG = TAG 1
                if (dow > 6 || dow == 1) {
                    weekNr++;
                }

                String weekNumber;
                if (weekNr < 10) {
                    weekNumber = "0" + weekNr;
                } else {
                    weekNumber = String.valueOf(weekNr);
                }


                String klasse = sp.getString("Klasse", null);
                if(klasse != null && klasse != "") {

                    if (InternetCheck.isOnline()) {
                        VertretungsHandle.sortDays(VertretungsHandle.getSource(klasse, weekNumber));
                        System.out.println("Suche nach w/" + weekNumber + "/" + klasse + "!");

                        Map<String, Vertretung> map = new TreeMap<>(VertretungsHandle.vp);
                        if (!map.isEmpty()) {

                            monday = tuesday = wednesday = thursday = friday = "";
                            for (Map.Entry<String, Vertretung> e : map.entrySet()) {
                                String key = e.getKey();
                                Vertretung v = e.getValue();

                                if (key.startsWith("monday")) {
                                    monday += replaceChars("» Stunde " + v.stunde + " - " + v.fach + " statt " + v.stattFach + " in Raum " + v.raum + " - " + v.text + " \n \n \n");
                                } else if (key.startsWith("tuesday")) {
                                    tuesday += replaceChars("» Stunde " + v.stunde + " - " + v.fach + " statt " + v.stattFach + " in Raum " + v.raum + " - " + v.text + " \n \n \n");
                                } else if (key.startsWith("wednesday")) {
                                    wednesday += replaceChars("» Stunde " + v.stunde + " - " + v.fach + " statt " + v.stattFach + " in Raum " + v.raum + " - " + v.text + " \n \n \n");
                                } else if (key.startsWith("thursday")) {
                                    thursday += replaceChars("» Stunde " + v.stunde + " - " + v.fach + " statt " + v.stattFach + " in Raum " + v.raum + " - " + v.text + " \n \n \n");
                                } else if (key.startsWith("friday")) {
                                    friday += replaceChars("» Stunde " + v.stunde + " - " + v.fach + " statt " + v.stattFach + " in Raum " + v.raum + " - " + v.text + " \n \n \n");
                                }
                            }

                            insertDefaults();

                        } else {
                            System.out.println("TreeMap ist leer.");

                            String url = "http://mpg-vertretungsplan.de/w/" + weekNumber + "/" + klasse + ".htm";
                            if (!DoesUrlExist.exists(url)) {
                                monday = tuesday = wednesday = thursday = friday = "Unter der dynamisch generierten URL wurde kein Vertretungsplan gefunden (Externer Fehler?). (Fehlercode: 404)";
                            } else {
                                monday = tuesday = wednesday = thursday = friday = "Für diese Woche wurden entweder keine Vertretungen eingetragen oder es handelt sich um einen Fehler. (Fehlercode: 001)";
                            }
                        }
                    } else {
                        monday = tuesday = wednesday = thursday = friday = "Derzeit besteht keine Internetverbindung!";
                    }

                }

                Thread u1 = new Thread(updateVP);
                u1.start();
                u1.join();
                findViewById(R.id.loadingPanel).setVisibility(View.GONE);

            }catch(Exception e){
                e.printStackTrace();
            }

        }
    });

    private Runnable updateVP = new Runnable() {

        @Override
        public void run() {
            try  {

                final TextView tv = findViewById(R.id.textView);

                        if(dayoweek == "montag"){
                            setText(tv, monday);
                        }else if(dayoweek == "dienstag"){
                            setText(tv, tuesday);
                        }else if(dayoweek == "mittwoch"){
                            setText(tv, wednesday);
                        }else if(dayoweek == "donnerstag"){
                            setText(tv, thursday);
                        }else if(dayoweek == "freitag"){
                            setText(tv, friday);
                        }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };



    private static String replaceChars(String s){
        if(s.contains("---")){
            String s1 = s.replace("---", "~");
                return s1;
        }else{
            return s;
        }
    }

    private static void insertDefaults(){
       if(monday == ""){
           monday = "Es wurde keine Vertretung eingetragen!";
       }
       if(tuesday == ""){
           tuesday = "Es wurde keine Vertretung eingetragen!";
       }
       if(wednesday == ""){
           wednesday = "Es wurde keine Vertretung eingetragen!";
       }
       if(thursday == ""){
           thursday = "Es wurde keine Vertretung eingetragen!";
       }
       if(friday == ""){
           friday = "Es wurde keine Vertretung eingetragen!";
       }
    }

    private void startIntent(){
        Intent intent = new Intent(this, settings.class);
        startActivity(intent);
    }

    private void intentToMenu(){
        Intent intent = new Intent(this, first_start.class);
        startActivity(intent);
    }

    private void setText(final TextView text,final String value){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                text.setText(value);
            }
        });
    }

}
