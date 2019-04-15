package Bierkopf;

import java.util.List;
import java.util.*;
import java.util.ArrayList;
import java.util.Collections;


public class Bierkopf {

  List<Karte> all_cards;

  public static void main(String[] args) {
    Bierkopf bierkopf = new Bierkopf();
    Spieler player_one = new Spieler(bierkopf);
    player_one.teile_Handkarten_aus();
  }

  public Bierkopf() {
    System.out.println("Konstruktor: Bierkopf()");

    all_cards = new ArrayList<Karte>();

    // initialize all cards
    int i = 0;
    for (FARBE f : FARBE.values())
      for (ZAHL z : ZAHL.values())
        all_cards.add(new Karte(z, f));

    // print all cards + trump
    all_cards.forEach((karte) -> {
      System.out.print(karte.get_karte() + " ");
      System.out.println(karte.get_trumpf());
    });
    mischeKarten();
    all_cards.forEach((karte) -> {
      System.out.print(karte.get_karte() + " ");
      System.out.println(karte.get_trumpf());
    });


  }

  // Ausgabefunktion

  public void mischeKarten()
  {
    Collections.shuffle(all_cards);
  }
}
