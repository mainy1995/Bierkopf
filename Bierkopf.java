package Bierkopf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bierkopf {

  public List<Karte> alleKarten;
  public Stich stich;
  public Spieler[] spieler;

  public static void main(String[] args) {
    Bierkopf bierkopf = new Bierkopf();
//    bierkopf.spielen();
    bierkopf.spieler[3].gibAus();

//    P.pln();
//    P.pln("------------------------------------------");
//    for (int i = 0; i < bierkopf.spieler.length; i++)
//      bierkopf.spieler[i].printAlleHandkarten();
//    P.pln();
  }

  public Bierkopf() {
    P.pln("Konstruktor: Bierkopf()");
    stich = new Stich();
    alleKarten = new ArrayList<Karte>();

    // initialisiere alle Karten
    for (FARBE f : FARBE.values())
      for (ZAHL z : ZAHL.values())
        alleKarten.add(new Karte(z, f));

    // Karten mischen
    Collections.shuffle(alleKarten);

    printAlleKarten();

    // 4 Spieler erstellen mit jeweils 6 Karten
    spieler = new Spieler[] { // nl
        new User(this, alleKarten.subList(0, 6), "Ich"), // nl
        new Spieler(this, alleKarten.subList(6, 12), "Marie"), // nl
        new Spieler(this, alleKarten.subList(12, 18), "Sepp"), // nl
        new Spieler(this, alleKarten.subList(18, 24), "Konrad")// nl
    };
  }

  // Ausgabe aller Karten
  private void printAlleKarten() {
    for (Karte karte : alleKarten)
      P.pln(karte.getKarte() + " " + karte.getTrumpf());
  }

  private void spielen() {
//    for (int runde = 1; runde <= 6; runde++) {
//      stich.neuerStich();
//      for (int i = 1; i <= 4; i++)
//        // Fundamental falsch, da Reihenfolge sich nicht ändert - aber fürn Anfang

    Karte k1 = spieler[0].legeKarte();
    stich.zumStich(k1);
    alleKarten.remove(k1);
  }
}
