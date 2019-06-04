package Bierkopf.model;

public class Stich {
  private Karte[] karten;
  private int punkte, gewinnerPosition;
  private Karte hoechsteKarte, ersteKarte;

  public Stich() {
    P.pln();
    P.pln("---------------------Neuer Stich---------------------");
    karten = new Karte[4];
    punkte = 0;
  }

  public void zumStich(Karte gelegteKarte, int spielerPosition) {
    karten[spielerPosition] = gelegteKarte;
    punkte += gelegteKarte.getPunkte();

    // Betrachtung der ersten Karte
    if (ersteKarte == null) {
      ersteKarte = hoechsteKarte = gelegteKarte;
      gewinnerPosition = spielerPosition;
    }

    // Im Zweifelsfall triumphiert die ersteKarte
    if (isthoechsteKarte(gelegteKarte)) {
      hoechsteKarte = gelegteKarte;
      gewinnerPosition = spielerPosition;
    }
  }

  public boolean isthoechsteKarte(Karte gelegteKarte) {
    if (hoechsteKarte.getTrumpf()) {
      if (gelegteKarte.getTrumpf()) {
        // Trümpfe miteinander vergleichen
        int i = gelegteKarte.zahl.compareTo(hoechsteKarte.zahl); // positiv, falls größer
        if (i > 0) {
          return true;
        }
        // Farbvergleich nötig für Ober und Unter
        else if (i == 0) {
          int j = gelegteKarte.farbe.compareTo(hoechsteKarte.farbe);
          if (j > 0)
            return true;
        }
      }
    }
    // hoechste Karte ist nicht Trumpf
    else {
      // mit Trumpf stechen
      if (gelegteKarte.getTrumpf()) {
        hoechsteKarte = gelegteKarte;
        return true;
      }
      // gelegte Karte ist auch kein Trumpf
      else {
        // Farbvergleich
        int i = gelegteKarte.farbe.compareTo(hoechsteKarte.farbe);
        if (i == 0) {
          int j = gelegteKarte.zahl.compareTo(hoechsteKarte.zahl);
          if (j > 0)
            return true;
        }
      }
    }
    // Ansonsten
    return false;
  }

  public Karte ersteKarte() {
    return ersteKarte;
  }

  public int getPunkte() {
    return punkte;
  }

  public int getGewinnerPosition() {
    return gewinnerPosition;
  }

}
