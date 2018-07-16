package de.vp.dennis.vp2018.core;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by dennis on 24.01.2018.
 */

public class VertretungsHandle {
    public static HashMap<String, Vertretung> vp = new HashMap<>();

    public static ArrayList<String> getSource(String klasse, String woche) {

        String url = "http://mpg-vertretungsplan.de/w/"+woche+"/"+klasse+".htm";

        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<String> returnment = new ArrayList<>();
        for (String s : doc.toString().replace("\"", "").split("\n"))
        {
            returnment.add(s);
        }
        return returnment;
    }

    public static void sortDays(ArrayList<String> days) {

        // Monday

        int mondayint = 0;
        boolean mondaybool = false;
        ArrayList<String> monday = new ArrayList<>();

        for (String s : days) {

            if (s.contains("<a name=2>")) {
                mondaybool = false;
            }

            if (mondaybool == true) {
                monday.add(days.get(mondayint));
            }

            if (s.contains("<a name=1>")) {
                monday.add(days.get(mondayint));
                mondaybool = true;
            }
            mondayint++;
        }

        addVP(monday, "monday");


        // Tuesday

        int tuesdayint = 0;
        boolean tuesdaybool = false;
        ArrayList<String> tuesday = new ArrayList<>();

        for (String s : days) {

            if (s.contains("<a name=3>")) {
                tuesdaybool = false;
            }

            if (tuesdaybool == true) {
                tuesday.add(days.get(tuesdayint));
            }

            if (s.contains("<a name=2>")) {
                tuesday.add(days.get(tuesdayint));
                tuesdaybool = true;
            }

            tuesdayint++;
        }

        addVP(tuesday, "tuesday");


        // Wednesday

        int wednesdayint = 0;
        boolean wednesdaybool = false;
        ArrayList<String> wednesday = new ArrayList<>();

        for (String s : days) {

            if (s.contains("<a name=4>")) {
                wednesdaybool = false;
            }

            if (wednesdaybool == true) {
                wednesday.add(days.get(wednesdayint));
            }

            if (s.contains("<a name=3>")) {
                wednesday.add(days.get(wednesdayint));
                wednesdaybool = true;
            }

            wednesdayint++;
        }

        addVP(wednesday, "wednesday");


        // Thursday

        int thursdayint = 0;
        boolean thursdaybool = false;
        ArrayList<String> thursday = new ArrayList<>();

        for (String s : days) {

            if (s.contains("<a name=5>")) {
                thursdaybool = false;
            }

            if (thursdaybool == true) {
                thursday.add(days.get(thursdayint));
            }

            if (s.contains("<a name=4>")) {
                thursday.add(days.get(thursdayint));
                thursdaybool = true;
            }

            thursdayint++;
        }

        addVP(thursday, "thursday");


        // Friday

        int fridayint = 0;
        boolean fridaybool = false;
        ArrayList<String> friday = new ArrayList<>();

        for (String s : days) {

            if (s.contains("<a name=6>")) {
                fridaybool = false;
            }

            if (fridaybool == true) {
                friday.add(days.get(fridayint));
            }

            if (s.contains("<a name=5>")) {
                friday.add(days.get(fridayint));
                fridaybool = true;
            }

            fridayint++;
        }

        addVP(friday, "friday");

    }





    public static void addVP(ArrayList<String> strings, String tag ) {

        int i = 0;
        for (String s : strings) {

            if (s.contains("<td class=list align=center>11</td>")
                    || s.contains("<td class=list align=center>10 - 11</td>")
                    || s.contains("<td class=list align=center>10</td>")
                    || s.contains("<td class=list align=center>9 - 10</td>")
                    || s.contains("<td class=list align=center>9</td>")
                    || s.contains("<td class=list align=center>8 - 9</td>")
                    || s.contains("<td class=list align=center>8</td>")
                    || s.contains("<td class=list align=center>6</td>")
                    || s.contains("<td class=list align=center>5 - 6</td>")
                    || s.contains("<td class=list align=center>5</td>")
                    || s.contains("<td class=list align=center>4 - 5</td>")
                    || s.contains("<td class=list align=center>4</td>")
                    || s.contains("<td class=list align=center>3 - 4</td>")
                    || s.contains("<td class=list align=center>3</td>")
                    || s.contains("<td class=list align=center>2 - 3</td>")
                    || s.contains("<td class=list align=center>2</td>")
                    || s.contains("<td class=list align=center>1 - 2</td>")
                    || s.contains("<td class=list align=center>1</td>")) {


                String i0 = "";
                if(strings.get(i).contains("-")){
                    i0 = strings.get(i).replace("-", " und ").trim();
                }else{
                    i0 = strings.get(i);
                }

                Vertretung v = new Vertretung(
                        strings.get(i - 2).replace("<td class=list align=center>", "").replace("</td>", "").trim(),
                        strings.get(i - 1).replace("<td class=list align=center>", "").replace("</td>", "").trim(),
                        i0.replace("<td class=list align=center>", "").replace("</td>", "").trim(),
                        strings.get(i + 1).replace("<td class=list align=center>", "").replace("</td>", "").trim(),
                        strings.get(i + 2).replace("<td class=list align=center>", "").replace("</td>", "").trim(),
                        strings.get(i + 3).replace("<td class=list align=center>", "").replace("</td>", "").trim(),
                        strings.get(i + 4).replace("<td class=list align=center>", "").replace("</td>", "").trim());

                vp.put(tag+strings.get(i + 0).replace("<td class=list align=center>", "").replace("</td>", "").trim(), v);
            }
            i++;
        }
        }
 }

