package de.vp.dennis.vp2018.core;

/**
 * Created by dennis on 23.01.2018.
 */

public class Vertretung {


       public String datum, klasse, stunde, fach, raum, stattFach, text;


    public Vertretung(String datum, String klasse, String stunde, String fach, String raum, String stattFach, String text) {
        this.datum = datum;
        this.klasse = klasse;
        this.stunde = stunde;
        this.fach = fach;
        this.raum = raum;
        this.stattFach = stattFach;
        this.text = text;
    }

}
