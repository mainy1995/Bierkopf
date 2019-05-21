package Bierkopf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Spieler {
	protected String name;
	protected List<Karte> handkarten;
	protected Bierkopf bierkopf;
	protected boolean trumpfFrei, eichelFrei, blattFrei, schellenFrei;
	protected int position, punkte;
	protected HashMap<String, Boolean> farbenFreiMap;
	private VirtualBierkopf virtualBierkopf;
	private Stich liveStich;

	public Spieler(Bierkopf _bierkopf, List<Karte> _handkarten, String _name, int _position) {
		P.pln();
		P.pln("Konstruktor: Spieler(" + _name + ")");

		name = _name;
		handkarten = new ArrayList<Karte>();
		handkarten.addAll(_handkarten);
		bierkopf = _bierkopf;
		position = _position;
		punkte = 0;
		farbenFreiMap = new HashMap<String, Boolean>();

		printAlleHandkarten();
	}

	// Ausgabe aller Handkarten
	public void printAlleHandkarten() {
		P.pln("(" + name + ") Meine Karten sind:");
		for (Karte karte : handkarten)
			P.pln(karte.getKarte() + " " + karte.getTrumpf());
		P.pln();

		// Zustände mehr oder weniger schön ausgeben
		P.p("(" + name + ") Ich bin");

		if (trumpfFrei)
			P.p(" trumpffrei...");
		if (eichelFrei)
			P.p(" eichelfrei,");
		if (blattFrei)
			P.p(" blattfrei,");
		if (schellenFrei)
			P.p(" schellenfrei");
		P.pln(".");
	}

	// eigene Handkarten auf "Freiheit" überprüfen
	protected void checkFreiheit() {
		trumpfFrei = eichelFrei = blattFrei = schellenFrei = true;

		for (Karte karte : handkarten) {
			if (karte.getTrumpf()) {
				if (trumpfFrei)
					trumpfFrei = false;
			} else {
				if (karte.getFarbe() == "E" && eichelFrei)
					eichelFrei = false;
				if (karte.getFarbe() == "B" && blattFrei)
					blattFrei = false;
				if (karte.getFarbe() == "S" && schellenFrei)
					schellenFrei = false;
			}
		}
	}

	protected void removeKarte(Karte karte) {
		if (handkarten.contains(karte))
			handkarten.remove(karte);
		else
			P.pln("Karte war nicht Teil der Hand!");
	}

	public Karte legeKarte(Stich _liveStich, Boolean virtuell) {
		liveStich = _liveStich;

		Karte dieKarte = null;
		if (!handkarten.isEmpty()) {
			List<Karte> moeglicheKarten = moeglicheKarten();
			if (!virtuell && moeglicheKarten.size() == 1)
				dieKarte = moeglicheKarten.get(0);
			else {
				int hoechsteBewertung = 0;

				for (Karte kk : moeglicheKarten) {
					handkarten.remove(kk);
					virtualBierkopf = new VirtualBierkopf(bierkopf.alleKarten, kk, this, liveStich);
					handkarten.add(kk);
					int bewertung = virtualBierkopf.virtuellSpielen();
					if (bewertung > hoechsteBewertung) {
						dieKarte = kk;
						hoechsteBewertung = bewertung;
					}
				}
			}
		}
		removeKarte(dieKarte);
		P.pln("(" + name + ") Ich lege die " + dieKarte.getKarte());
		return dieKarte;
	}

	// Farbe oder Trumpf bekennen
	// und bei mehreren passenden Handkarten weise auswählen
	private List<Karte> moeglicheKarten() {
		Karte ersteKarte = liveStich.ersteKarte();
		List<Karte> moeglicheKarten = new ArrayList<Karte>();

		if (ersteKarte == null)
			return handkarten;

		// trumpf
		if (ersteKarte.getTrumpf()) {
			for (Karte karte : handkarten)
				if (karte.getTrumpf())
					moeglicheKarten.add(karte);

			if (moeglicheKarten.size() == 0)
				bierkopf.spielerInfo.trumpfFrei[position] = true;
		}
		// Farbe
		else {
			for (Karte karte : handkarten)
				if (!karte.getTrumpf() && karte.getFarbe().contentEquals(ersteKarte.getFarbe()))
					moeglicheKarten.add(karte);

			if (moeglicheKarten.size() == 0)
				bierkopf.spielerInfo.setSpielerFarbe(ersteKarte.getFarbe(), position);
		}

		if (moeglicheKarten.size() == 0)
			return handkarten;
		else
			return moeglicheKarten;
	}

	public int getPosition() {
		return position;
	}

	public int getPunkte() {
		return punkte;
	}
}
