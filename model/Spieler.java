package Bierkopf.model;

import java.util.ArrayList;
import java.util.List;

public class Spieler {

    protected String name;
    public List<Karte> handkarten;
    protected Bierkopf bierkopf;
    protected boolean trumpfFrei, eichelFrei, blattFrei, schellenFrei;
    protected int position, punkte;



    public Spieler(Bierkopf _bierkopf, List<Karte> _handkarten, String _name, int _position) {
        P.pln();
        P.pln("Konstruktor: Spieler(" + _name + ")");

        name = _name;
        handkarten = new ArrayList<Karte>();
        handkarten.addAll(_handkarten);
        bierkopf = _bierkopf;
        checkFreiheit();
        position = _position;
        punkte = 0;        

        printAlleHandkarten();

    }

    // eigene Handkarten auf "Freiheit" überprüfen
    protected void checkFreiheit() {
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
    public void printAlleHandkarten() {
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

    public Karte legeKarte(Stich liveStich) {
        P.pln();
        P.pln("(" + name + ") Ich bin am Zug.");
        Karte ersteKarte = liveStich.ersteKarte();
        Karte dieKarte = null;
        checkFreiheit();

        // nicht rauskommen - also die erste Karte im Stich existiert
        if (ersteKarte != null) {
            // Trumpf ist gespielt
            if (ersteKarte.getTrumpf()) {
                if (trumpfFrei) {
                    // ich habe keinen Trumpf
                    bierkopf.spielerInfo.trumpfFrei[position] = true;
                    dieKarte = kamikazeFunktion();
                } else {
                    // ich gebe Trumpf zu
                    dieKarte = farbeZugeben(ersteKarte, true);
                }
            } // eine Farbe ist gepielt
            else if (ersteKarte.getFarbe() == "E") {
                if (eichelFrei) {
                    // ich habe keine Eichel
                    bierkopf.spielerInfo.eichelFrei[position] = true;
                    dieKarte = kamikazeFunktion();
                } else {
                    // ich gebe Eichel zu
                    dieKarte = farbeZugeben(ersteKarte, false);
                }
            } else if (ersteKarte.getFarbe() == "B") {
                if (blattFrei) {
                    // ich habe keinen Blatt
                    bierkopf.spielerInfo.blattFrei[position] = true;
                    dieKarte = kamikazeFunktion();
                } else {
                    // ich gebe Blatt zu
                    dieKarte = farbeZugeben(ersteKarte, false);
                }
            } else if (ersteKarte.getFarbe() == "S") {
                if (schellenFrei) {
                    // ich habe keinen Schellen
                    bierkopf.spielerInfo.schellenFrei[position] = true;
                    dieKarte = kamikazeFunktion();
                } else {
                    // ich gebe Schellen zu
                    dieKarte = farbeZugeben(ersteKarte, false);
                }
            }
        } // ich komme raus
        else {
            dieKarte = kamikazeFunktion();
        }

        removeKarte(dieKarte);
        P.pln("(" + name + ") Ich lege die " + dieKarte.getKarte());
        // Visuelles Anzeigen der Karte -> senden eines Events?
        return dieKarte;
    }

    protected void removeKarte(Karte karte) {
        if (handkarten.contains(karte)) {
            handkarten.remove(karte);
        } else {
            P.pln("Karte war nicht Teil der Hand!");
        }
    }

    // irgendwie Baum aufbauen und beste Karte für den Stich wählen
    private Karte kamikazeFunktion() {
        Karte k = null;
        // TODO Drachen bezwingen
        // die nachfolgende Zeile ist FALSCH
        k = handkarten.get(0);
        return k;
    }

    // Farbe oder Trumpf bekennen
    // und bei mehreren passenden Handkarten weise auswählen
    private Karte farbeZugeben(Karte ersteKarte, boolean trumpf) {
        Karte dieKarte = null;
        List<Integer> indizes = new ArrayList<Integer>();

        int anzahlKarten = 0;
        if (!trumpf) {
            for (Karte karte : handkarten) {
                if (!karte.getTrumpf() && karte.getFarbe().contentEquals(ersteKarte.getFarbe())) {
                    anzahlKarten++;
                    indizes.add(handkarten.indexOf(karte));
                }
            }
            if (anzahlKarten == 1) {
                dieKarte = handkarten.get(indizes.get(0));
            } else {
                // TODO weise Wählen
                // die nachfolgende Zeile ist FALSCH
                dieKarte = handkarten.get(indizes.get(0));
            }
        } // trumpf
        else {
            for (Karte karte : handkarten) {
                if (karte.getTrumpf()) {
                    anzahlKarten++;
                    indizes.add(handkarten.indexOf(karte));
                }
            }
            if (anzahlKarten == 1) {
                dieKarte = handkarten.get(indizes.get(0));
            } else {
                // TODO weise Wählen
                // die nachfolgende Zeile ist FALSCH
                dieKarte = handkarten.get(indizes.get(0));
            }
        }
        return dieKarte;
    }

    // alle möglichen Kombinationen eines Stichs auszugeben (verbessert)
    // 4 Karten werden in den Stich gelegt (KI beginnt)
    public void printKombinations4() {
        P.pln("(" + name + ") Alle Kartenkombinationen, die folgen können sind:");

        int i = 0;
        for (Karte k1 : handkarten)// erste Karte ist eine Handkarte
        {
            for (Karte k2 : bierkopf.alleKarten) {
                if (!handkarten.contains(k2))// nachfolgenden Karten sind nicht auf der Hand
                {
                    for (Karte k3 : bierkopf.alleKarten) {
                        if (!(handkarten.contains(k3) || k2.equals(k3))) {
                            for (Karte k4 : bierkopf.alleKarten) {
                                if (!(handkarten.contains(k4) || k2.equals(k4) || k3.equals(k4))) {
                                    P.pln(k1.getKarte() + " " + k2.getKarte() + " " + k3.getKarte() + " " + k4.getKarte());
                                    i++;
                                }
                            }
                        }
                    }
                }
            }
        }

        P.pln("Es sind noch " + i + " Kartenkombinationen im Spiel.");
    }

    public int getPosition() {
        return position;
    }

    public int getPunkte() {
        return punkte;
    }

}
