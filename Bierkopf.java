package Bierkopf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bierkopf {

  private List<Karte> alleKarten;
  public Stich stich;
  public Spieler[] spieler;

  public static void main(String[] args) {
    Bierkopf bierkopf = new Bierkopf();
    bierkopf.spielen();
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

    // Ausgabe aller Karten
    alleKarten.forEach((karte) -> {
      P.pln(karte.getKarte() + " " + karte.getTrumpf());
    });

    // 4 Spieler erstellen mit jeweils 6 Karten
    spieler = new Spieler[] { // nl
        new User(this, alleKarten.subList(0, 6).toArray(new Karte[6]), "Ich"),
        new Spieler(this, alleKarten.subList(6, 12).toArray(new Karte[6]), "Marie"),
        new Spieler(this, alleKarten.subList(12, 18).toArray(new Karte[6]), "Sepp"),
        new Spieler(this, alleKarten.subList(18, 24).toArray(new Karte[6]), "Konrad")// nl
    };
  }

  private void spielen() {
//    for (int runde = 1; runde <= 6; runde++) {
//      stich.neuerStich();
//      for (int i = 1; i <= 4; i++)
//        // Fundamental falsch, da Reihenfolge sich nicht ändert - aber fürn Anfang
//        stich.zumStich(spieler[i].legeKarte());
//    }
  }

}
