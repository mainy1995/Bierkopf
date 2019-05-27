package Bierkopf;

import java.util.List;

public class VirtualBierkopf {
	private List<Karte> alleKarten;
	private Karte zuBetrachtendeKarte;
	private Spieler spieler;
	private Stich liveStich;
	private Bierkopf bierkopf;

	public VirtualBierkopf(List<Karte> _alleKarten, Karte _zuBetrachtendeKarte, Spieler _spieler, Stich _liveStich,
			Bierkopf _bierkopf) {
		alleKarten = _alleKarten;
		zuBetrachtendeKarte = _zuBetrachtendeKarte;
		spieler = _spieler;
		liveStich = _liveStich;
		bierkopf = _bierkopf;
		P.pln("Neuer Virtueller Bierkopf für Karte " + zuBetrachtendeKarte.getKarte());
	}

	/*
	 * beende den aktuellen Stich - spiele soweit fort bis der spieler wieder am
	 * Zug ist - rufe spieler.legeKarte()
	 */
	public int virtuellSpielen() {
		int gewinner = 0;
		liveStich.zumStich(zuBetrachtendeKarte, spieler.position);

		legeKarten(4 - liveStich.anzahlImStich, spieler.position);
		P.pln("Test");
		gewinner = liveStich.getGewinnerPosition();
		bierkopf.alleSpieler.get(gewinner).punkte += liveStich.getPunkte();

		// Bewertung zurueckgeben anstatt 0
		return spieler.punkte;
	}

	private void legeKarten(int tiefe, int _naechstePosition) {
		P.pln("Position: " + _naechstePosition);
		int naechstePosition = (_naechstePosition + 1) % 4;
		P.pln("Position: " + naechstePosition);
		// nach alle moeglichen Kombinationen den Stich zu beenden
		if (tiefe <= 0) {
			// gewinner ermitteln
			int gewinner = liveStich.getGewinnerPosition();
			// im selben Team
			if (gewinner % 2 == spieler.position % 2)
				spieler.punkte += liveStich.getPunkte();

			// neuen Stich erstellen
			liveStich = new Stich();
			//
			int neueTiefe = Math.abs(gewinner - spieler.position) % 4;
			P.pln("Berechnete Tiefe: " + neueTiefe);
			P.pln("gewinner: " + gewinner);
			P.pln("spieler.position: " + spieler.position);

			fuelleNeuenStich(neueTiefe, gewinner);
			return;
		}
		for (Karte k2 : alleKarten) {
			P.pln("Verbliebene Karten: " + alleKarten.size());
			// P.p("(" + bierkopf.alleSpieler.get(naechstePosition).name + ")
			// ");
			P.pln("Tiefe: " + tiefe + ", Karte: " + k2.getKarte());
			P.pln("naechstePosition: " + naechstePosition);
			liveStich.zumStich(k2, naechstePosition);
			alleKarten.remove(k2);
			legeKarten(tiefe - 1, naechstePosition);
			alleKarten.add(k2);
		}
	}

	
	//nächste Position ist falsch
	private void fuelleNeuenStich(int tiefe, int _naechstePosition) {
		P.pln("Tiefe: " + tiefe);
		P.pln("Position: " + _naechstePosition);	
		// nÃ¤chsten Stich weiterspielen bis Spieler dran ist
		int naechstePosition = (_naechstePosition + 1) % 4;
		P.pln("Position: " + naechstePosition);
		// nach alle moeglichen Kombinationen den Stich zu beenden
		if (tiefe == 0) {

			spieler.legeKarte(liveStich, true);
			return;
		}
		for (Karte k2 : alleKarten) {
			liveStich.zumStich(k2, naechstePosition);
			alleKarten.remove(k2);
			// letzte änderung hier: legeKarte in fuelleNeuenStich 
			fuelleNeuenStich(tiefe - 1, naechstePosition);
			alleKarten.add(k2);
		}
	}

	public void printKombinations1() {
		for (Karte k2 : alleKarten)
			if (!spieler.handkarten.contains(k2))
				for (Karte k3 : alleKarten)
					if (!(spieler.handkarten.contains(k3) || k2.equals(k3)))
						for (Karte k4 : alleKarten)
							if (!(spieler.handkarten.contains(k4) || k2.equals(k4) || k3.equals(k4)))
								;

	}

}
