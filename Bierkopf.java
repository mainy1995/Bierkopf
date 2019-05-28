package Bierkopf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bierkopf {

  public List<Karte> alleKarten;
  public List<Stich> alleStiche;
  public List<Spieler> alleSpieler;
  public SpielerInformation spielerInfo;

  public static void main(String[] args) {
    Bierkopf bierkopf = new Bierkopf();
    bierkopf.spielen();
  }

  public Bierkopf() {
    P.pln("Konstruktor: Bierkopf()");
    alleStiche = new ArrayList<Stich>();
    alleKarten = new ArrayList<Karte>();
    alleSpieler = new ArrayList<Spieler>();
    spielerInfo = new SpielerInformation();

    // initialisiere alle Karten
    for (FARBE f : FARBE.values())
      for (ZAHL z : ZAHL.values())
        alleKarten.add(new Karte(z, f));

    // Karten mischen
    Collections.shuffle(alleKarten);

    printAlleKarten();

    // 4 Spieler erstellen mit jeweils 6 Karten
    alleSpieler.add(new User(this, alleKarten.subList(0, 6), "Ich", 0));
    alleSpieler.add(new Spieler(this, alleKarten.subList(6, 12), "Marie", 1));
    alleSpieler.add(new Spieler(this, alleKarten.subList(12, 18), "Sepp", 2));
    alleSpieler.add(new Spieler(this, alleKarten.subList(18, 24), "Konrad", 3));

    P.nLines(2);
    P.pln("---------------------Spielbeginn---------------------");
  }

  // Ausgabe aller Karten
  private void printAlleKarten() {
    for (Karte karte : alleKarten)
      P.pln(karte.getKarte() + " " + karte.getTrumpf());
  }

  public void spielen() {
    int gewinner = 0;
    for (int i = 0; i < 6; i++) {
      Stich liveStich = new Stich();
      for (int werDranIst = gewinner; werDranIst - gewinner < 4; werDranIst++) {
        Karte gelegteKarte = alleSpieler.get(werDranIst % 4).legeKarte(liveStich, false);
        liveStich.zumStich(gelegteKarte, alleSpieler.get(werDranIst % 4).position);
        alleKarten.remove(gelegteKarte);
      }
      gewinner = liveStich.getGewinnerPosition();
      alleSpieler.get(gewinner).punkte += liveStich.getPunkte();
      alleStiche.add(liveStich);

    }
  }

  // Ausgabe eines Kartenbereiches mit String
  public void printKarten(Karte[] karten, String title) {
    P.pln(title);
    for (Karte karte : karten)
      P.pln(karte.getKarte() + " " + karte.getTrumpf());
  }
}
