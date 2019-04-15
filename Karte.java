package Bierkopf;

import java.util.HashMap;

enum FARBE {
  EICHEL, BLATT, HERZ, SCHELLEN
}

enum ZAHL {
  NEUN, UNTER, OBER, KOENIG, ZEHN, ASS
}

public class Karte {
  FARBE farbe;
  ZAHL zahl;
  int wertigkeit;
  boolean trumpf;

  private String zahl(ZAHL _zahl) {
    HashMap<ZAHL, String> zahl = new HashMap<ZAHL, String>();

    zahl.put(ZAHL.NEUN, "9");
    zahl.put(ZAHL.UNTER, "U");
    zahl.put(ZAHL.OBER, "O");
    zahl.put(ZAHL.KOENIG, "K");
    zahl.put(ZAHL.ZEHN, "10");
    zahl.put(ZAHL.ASS, "A");

    return zahl.get(_zahl);
  }

  private String farbe(FARBE _farbe) {
    HashMap<FARBE, String> farbton = new HashMap<FARBE, String>();

    farbton.put(FARBE.EICHEL, "E");
    farbton.put(FARBE.BLATT, "B");
    farbton.put(FARBE.HERZ, "H");
    farbton.put(FARBE.SCHELLEN, "S");

    return farbton.get(_farbe);
  }

  private int wertigkeit(ZAHL _zahl) {
    HashMap<ZAHL, Integer> wertigkeit = new HashMap<ZAHL, Integer>();

    wertigkeit.put(ZAHL.NEUN, 0);
    wertigkeit.put(ZAHL.UNTER, 2);
    wertigkeit.put(ZAHL.OBER, 3);
    wertigkeit.put(ZAHL.KOENIG, 4);
    wertigkeit.put(ZAHL.ZEHN, 10);
    wertigkeit.put(ZAHL.ASS, 11);

    return wertigkeit.get(_zahl);
  }

  public Karte(ZAHL _zahl, FARBE _farbe) {
    farbe = _farbe;
    zahl = _zahl;
    wertigkeit = wertigkeit(_zahl);

    if(farbe(farbe) == "H" || zahl(zahl) == "U" || zahl(zahl) == "O" ) {
      trumpf = true;
    }
    else { trumpf = false; }
  }

  public String get_karte() {
    return zahl(zahl) + farbe(farbe);
  }

  public String get_trumpf() {
    return Boolean.toString(trumpf);
  }

}
