package Bierkopf.model;

import java.util.ArrayList;
import java.util.List;

public class Spieler {

    protected String name;
    public List<Karte> handkarten;
    protected Bierkopf bierkopf;
    protected boolean trumpfFrei, eichelFrei, blattFrei, schellenFrei;
    protected int position, punkte;
    private Stich liveStich;

    public Spieler(Bierkopf _bierkopf, List<Karte> _handkarten, String _name, int _position) {
        P.pln();
        P.pln("Konstruktor: Spieler(" + _name + ")");

        name = _name;
        handkarten = new ArrayList<>();
        handkarten.addAll(_handkarten);
        bierkopf = _bierkopf;
        checkFreiheit();
        position = _position;
        punkte = 0;

        printAlleHandkarten();

    }

    // eigene Handkarten auf "Freiheit" überprüfen
    protected final void checkFreiheit() {
        trumpfFrei = eichelFrei = blattFrei = schellenFrei = true;

        for (Karte karte : handkarten) {
            if (karte.getTrumpf()) {
                if (trumpfFrei) {
                    trumpfFrei = false;
                }
            } else {
                if (karte.getFarbe() == "E" && eichelFrei) {
                    eichelFrei = false;
                }
                if (karte.getFarbe() == "B" && blattFrei) {
                    blattFrei = false;
                }
                if (karte.getFarbe() == "S" && schellenFrei) {
                    schellenFrei = false;
                }
            }
        }
    }

    // Ausgabe aller Handkarten
    public final void printAlleHandkarten() {
        P.pln("(" + name + ") Meine Karten sind:");
        for (Karte karte : handkarten) {
            P.pln(karte.getKarte());
        }
        P.pln();

        // Zustände mehr oder weniger schön ausgeben
        P.p("(" + name + ") Ich bin");

        if (trumpfFrei) {
            P.p(" trumpffrei...");
        }
        if (eichelFrei) {
            P.p(" eichelfrei,");
        }
        if (blattFrei) {
            P.p(" blattfrei,");
        }
        if (schellenFrei) {
            P.p(" schellenfrei");
        }
        P.pln(".");
    }

    public Karte legeKarte(Stich _liveStich) {
        liveStich = _liveStich;
        P.pln();
        P.pln("(" + name + ") Ich bin am Zug.");
        Karte ersteKarte = _liveStich.ersteKarte();
        checkFreiheit();

        List<Karte> moeglicheKarten = moeglicheKarten();
        Karte dieKarte = moeglicheKarten.get(0);

        P.p("moegliche Karten: ");
        for (Karte m : moeglicheKarten) {
            P.p(m.getKarte() + ",");
        }
        P.pln();

        // waehle immer die hoechstmoegliche Karte
        for (Karte m : moeglicheKarten) {
            if (isthoechsteKarte(m, dieKarte)) {
                dieKarte = m;
            }
        }

        removeKarte(dieKarte);
        P.pln("(" + name + ") Ich lege die " + dieKarte.getKarte());

        return dieKarte;
    }

    public boolean isthoechsteKarte(Karte kandidat, Karte hoechsteKarte) {
        if (hoechsteKarte.getTrumpf()) {
            if (kandidat.getTrumpf()) {
                // Trümpfe miteinander vergleichen
                int i = kandidat.zahl.compareTo(hoechsteKarte.zahl); // positiv, falls größer
                if (i > 0) {
                    return true;
                } // Farbvergleich nötig für Ober und Unter
                else if (i == 0) {
                    int j = kandidat.farbe.compareTo(hoechsteKarte.farbe);
                    if (j > 0) {
                        return true;
                    }
                }
            }
        } // hoechste Karte ist nicht Trumpf
        else // mit Trumpf stechen
         if (kandidat.getTrumpf()) {
                return true;
            } // gelegte Karte ist auch kein Trumpf
            else {
                // Farbvergleich
                int i = kandidat.farbe.compareTo(hoechsteKarte.farbe);
                if (i == 0) {
                    int j = kandidat.zahl.compareTo(hoechsteKarte.zahl);
                    if (j > 0) {
                        return true;
                    }
                }
            }
        // Ansonsten
        return false;
    }

    protected void removeKarte(Karte karte) {
        if (handkarten.contains(karte)) {
            handkarten.remove(karte);
        } else {
            P.pln("Karte war nicht Teil der Hand!");
        }
    }

    private List<Karte> moeglicheKarten() {
        Karte ersteKarte = liveStich.ersteKarte();
        List<Karte> moeglicheKarten = new ArrayList<>();

        if (ersteKarte == null) {
            return handkarten;
        }

        // trumpf
        if (ersteKarte.getTrumpf()) {
            for (Karte karte : handkarten) {
                if (karte.getTrumpf()) {
                    moeglicheKarten.add(karte);
                }
            }

            if (moeglicheKarten.isEmpty()) {
                bierkopf.spielerInfo.trumpfFrei[position] = true;
            }
        } // Farbe
        else {
            for (Karte karte : handkarten) {
                if (!karte.getTrumpf() && karte.getFarbe().contentEquals(ersteKarte.getFarbe())) {
                    moeglicheKarten.add(karte);
                }
            }
        }

        if (moeglicheKarten.isEmpty()) {
            return handkarten;
        } else {
            return moeglicheKarten;
        }
    }

    public int getPosition() {
        return position;
    }

    public int getPunkte() {
        return punkte;
    }

}
