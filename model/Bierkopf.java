package Bierkopf.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Bierkopf muss Controller für Synchronisation kennen
import Bierkopf.controller.Controller;

public class Bierkopf implements EventUserInputListener {

    public List<Karte> alleKarten;
    public List<Stich> alleStiche;
    public List<Spieler> alleSpieler;
    public SpielerInformation spielerInfo;

    public Controller controller;

//  public static void main(String[] args) {
//    Bierkopf bierkopf = new Bierkopf();
//    bierkopf.spielen();
//  }
    public Bierkopf() {
        P.pln("Konstruktor: Bierkopf()");
        alleStiche = new ArrayList<>();
        alleKarten = new ArrayList<>();
        alleSpieler = new ArrayList<>();
        spielerInfo = new SpielerInformation();

        // initialisiere alle Karten
        for (FARBE f : FARBE.values()) {
            for (ZAHL z : ZAHL.values()) {
                alleKarten.add(new Karte(z, f));
            }
        }

        // Karten mischen
        Collections.shuffle(alleKarten);

        printAlleKarten();

        // 4 Spieler erstellen mit jeweils 6 Karten
        User user = new User(this, alleKarten.subList(0, 6), "Ich", 0);
        alleSpieler.add(user);
        alleSpieler.add(new Spieler(this, alleKarten.subList(6, 12), "Marie", 1));
        alleSpieler.add(new Spieler(this, alleKarten.subList(12, 18), "Sepp", 2));
        alleSpieler.add(new Spieler(this, alleKarten.subList(18, 24), "Konrad", 3));

        // Add Listener zum synchronisieren
        user.setEventUserInputListener(this);

        P.nLines(2);
        P.pln("---------------------Spielbeginn---------------------");
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    // Ausgabe aller Karten
    private void printAlleKarten() {
        for (Karte karte : alleKarten) {
            P.pln(karte.getKarte() + " " + karte.getTrumpf());
        }
    }

    public void spielen() {
        int gewinner = 0;
        Karte karte;
        int position;
        for (int i = 0; i < 6; i++) {
            Stich liveStich = new Stich();
            for (int anfaenger = gewinner; anfaenger - gewinner < 4; anfaenger++) {
                karte = alleSpieler.get(anfaenger % 4).legeKarte(liveStich);
                // Überprüfung ob Karte legitim war
                position = alleSpieler.get(anfaenger % 4).position;
                liveStich.zumStich(karte, position);
                // EVENT, Karte eines NPCs wurde gespielt!
                controller.updateNPCKarte(karte.getKarte(), position);
                bedenkzeit(500);
            }
            gewinner = liveStich.getGewinnerPosition();
            alleSpieler.get(gewinner).punkte += liveStich.getPunkte();
            alleStiche.add(liveStich);

            // Wegräumen der Stiche sowie Refresh der Spieler-Handkarten
            controller.raumeTischauf();
            controller.updateHandkarten(alleSpieler.get(0).handkarten);
            bedenkzeit(1500);
        }
        controller.setUserText(ermittleGewinner());

    }

    private String ermittleGewinner() {
        int punktanzahl = alleSpieler.get(0).punkte + alleSpieler.get(2).punkte;
        StringBuilder builder = new StringBuilder();

        if (punktanzahl >= 60) {
            builder.append("Eigenes Team hat gewonnen mit " + punktanzahl + " Punkten");
        } else {
            builder.append("Anderes Team hat gewonnen mit " + (120 - punktanzahl) + " Punkten");
        }

        P.pln();
        P.pln(builder.toString());
        return builder.toString();
    }

    // Funktion um die Bedenkzeit der Spielzüge zu simulieren; sollte für Debuging auskommentiert werden
    void bedenkzeit(long millisekunden) {
        try {
            Thread.sleep(millisekunden);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    // Funktion wird aufgerufen, wenn EventUserInput vom User kommt
    public String userLegtKarte() {
        String kartenname = null;
        P.pln("Event detektiert. User muss Karte auswählen");
        controller.enableInput();
        // So lange warten, bis User einen Input liefert

        while (kartenname == null) {
            kartenname = controller.getZuSpielendeKarte();
            System.out.print("");
        }
        return kartenname;
    }

}
