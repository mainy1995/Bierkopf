package Bierkopf;

public class Spieler {

  Karte[] all_cards;

  public Spieler() {
    // Problem hierbei: Jeder Spieler würde nun "all_cards" generieren. 
    // Vllt sollte das lieber nur die "Bierkopf-Klasse" tun. 
    all_cards = new Karte[24];

    // initialize all cards
    int i = 0;
    for (FARBE f : FARBE.values())
      for (ZAHL z : ZAHL.values())
        all_cards[i++] = new Karte(z, f);

    // print all cards + trump
    for (i = 0; i < 24; i++) {
      System.out.print(all_cards[i].get_karte() + " ");
      System.out.println(all_cards[i].get_trumpf());
    }
  }

}
