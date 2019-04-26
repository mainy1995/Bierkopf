
package Bierkopf;

import java.util.List;
import java.util.*;
import java.util.ArrayList;
import java.util.Collections;

public class Bierkopf {

  List<Karte> allCards;
  List<Spieler> allPlayer;
  List<Stich> allStich;

  public static void main(String[] args) {
    Bierkopf bierkopf = new Bierkopf();
    bierkopf.init();
    bierkopf.mischeKarten();
    bierkopf.allCards.forEach((karte) -> {
      System.out.print(karte.getKarte() + " ");
      System.out.println(karte.getTrumpf());
    });
    bierkopf.gebeKarten();
    for (Spieler spieler : bierkopf.allPlayer) {
      //bierkopf.printCards(spieler.handCards.toArray(), "Handkarten Spieler "+ spieler.name);
    }
    bierkopf.start();
    int i=0;
    for (Stich stich : bierkopf.allStich) {
      
      bierkopf.printCards(stich.Karten, "Stich "+i );
      i++;
    }





  }

  public Bierkopf() {
    System.out.println("Konstruktor: Bierkopf()");
    allCards = new ArrayList<Karte>();
    allPlayer = new ArrayList<Spieler>();
    allStich=new ArrayList<Stich>();
  }

  // Ausgabefunktion
  public void init() {
    // initialize all cards
    for (FARBE f : FARBE.values())
      for (ZAHL z : ZAHL.values())
        allCards.add(new Karte(z, f));    
    mischeKarten();    
    // initialize all players
    for (int i = 0;  i < 4; i++) {
      allPlayer.add(new Spieler("Spieler " +(i+1),i));
    }
  }
  public void printCards(Karte[] karten, String title){
    System.out.println(title);
   for (Karte karte : karten) {
    System.out.print(karte.getKarte() + " ");
    System.out.println(karte.getTrumpf());
   }
     
    
  }
  public void gebeKarten(){
    for (int i = 0; i < allCards.size(); i++) {
      allPlayer.get(i%4).addKarte(allCards.get(i));
    }
  }
  public void mischeKarten() {
    Collections.shuffle(allCards);
  }
  public void start(){
    for (int i = 0; i < 6; i++) {
      Stich liveStich = new Stich();
        for (Spieler spieler : allPlayer) {
          liveStich.karteGelegt(spieler.playKarte(),spieler.position);
        }
        allPlayer.get(liveStich.getGewinner()).punkte+=liveStich.punkte;    
        allStich.add(liveStich);      
    }
  }
}