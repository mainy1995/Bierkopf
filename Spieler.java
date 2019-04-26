package Bierkopf;

import java.util.List;
import java.util.ArrayList;

public class Spieler {

  List<Karte> handCards;
  String name;
  int position;
  int punkte;
  public Spieler(String name, int position) {
    this.name = name;
    this.position=position;
    punkte=0;
    handCards = new ArrayList<Karte>();
    System.out.println("Spieler " + name + " erstellt");
  }

  public void addKarte(Karte newKarte) {
    handCards.add(newKarte);
  }

  public Karte playKarte() {
    Karte ret = handCards.get(0);
    handCards.remove(0);
    return ret;
  }
}

