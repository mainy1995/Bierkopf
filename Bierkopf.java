package Bierkopf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bierkopf {

    List<Karte> all_cards;

    public static void main(String[] args) {
	Bierkopf bierkopf = new Bierkopf();
    }

    public Bierkopf() {
	System.out.println("Konstruktor: Bierkopf()");

	all_cards = new ArrayList<Karte>();

	// initialisiere alle Karten
	for (FARBE f : FARBE.values())
	    for (ZAHL z : ZAHL.values())
		all_cards.add(new Karte(z, f));

	// Karten ausgeben + Trumpf
	all_cards.forEach((karte) -> {
		P.p(karte.get_karte() + " ");
		P.pln(karte.get_trumpf());
	    });
	mischeKarten();
	all_cards.forEach((karte) -> {
		P.p(karte.get_karte() + " ");
		P.pln(karte.get_trumpf());
	    });

    }

    public void mischeKarten() {
	Collections.shuffle(all_cards);
	P.pln();
	P.pln("Karten mischen");
	P.pln();
    }
}       
