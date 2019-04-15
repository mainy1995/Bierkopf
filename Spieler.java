package Bierkopf;

public class Spieler {

  Karte[] handkarten;
  Bierkopf bierkopf;

  public Spieler(Bierkopf _bierkopf) {
    System.out.println("Konstruktor: Spieler()");
    bierkopf = _bierkopf;

    handkarten = new Karte[6];
  }

  // Auslagerung in Bierkopf-> Teile Karten aus
  public void teile_Handkarten_aus()
  {
    int i = 0;
    for (i = 0; i < 6; i++) {
      handkarten[i] = bierkopf.all_cards[i];
      System.out.print(handkarten[i].get_karte());
    }

  }

}
