package Bierkopf;

public class Stich {
  private Karte[] karten;
  private int punkte, gewinnerPosition;
  private Karte hoechsteKarte, ersteKarte;

  public Stich() {
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

  public int getGewinner() {
    return gewinnerPosition;
  }

  public boolean isthoechsteKarte(Karte gelegteKarte) {
    if (hoechsteKarte.getTrumpf()) {
      if (gelegteKarte.getTrumpf()) {
        // Trümpfe miteinander vergleichen
        int i = gelegteKarte.zahl.compareTo(hoechsteKarte.zahl); // positiv, falls größer
        if (i > 0) {
          return true;
        }
        /*************************************************************
         * Ist das Nachfolgende nicht unnötig, da nur Herz Trumpf ist?
         ************************************************************/
        else if (i == 0) {
          int j = gelegteKarte.farbe.compareTo(hoechsteKarte.farbe);
          // j wird doch stets 0 sein?
          if (j > 0)
            return true;
        }
        /************************************************************/
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
          if (j > 0) {
            return true;
          }
          /*************************************************************
           * Das nachfolgende Return zuviel?
           ************************************************************/
          return true;
          /************************************************************/
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
