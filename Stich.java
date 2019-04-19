package Bierkopf;

public class Stich {
  private Karte[] karten;
  private static int i;

  public Stich() {
    neuerStich();
  }

  public void neuerStich() {
    karten = new Karte[4];
    i = 0;
  }

  public Karte ersteKarte() {
    return karten[0];
  }

  public int getStichSumme() {
    int sum = 0;
    for (int j = 1; j <= 4; j++)
      sum += karten[j].getPunkte();
    return sum;
  }

  public void zumStich(Karte karte) {
    if (i < 4) {
      karten[i++] = karte;
    }
  }
}
