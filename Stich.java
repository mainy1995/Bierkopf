package Bierkopf;

public class Stich {
  Karte[] karten;
  private static int i;

  public Stich() {
    neuerStich();
  }

  public void neuerStich() {
    karten = new Karte[4];
    i = 0;
  }

  public void zumStich(Karte karte) {
    if (i < 4) {
      i++;
      karten[i] = karte;
    }
  }
}
