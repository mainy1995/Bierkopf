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

  HashMap<ZAHL, String> zahlenMap;
  HashMap<FARBE, String> farbenMap;
  HashMap<ZAHL, Integer> werteMap;

  public Karte(ZAHL _zahl, FARBE _farbe) {
    zahlenMap = new HashMap<ZAHL, String>();
    farbenMap = new HashMap<FARBE, String>();
    werteMap = new HashMap<ZAHL, Integer>();
    initMap();

    farbe = _farbe;
    zahl = _zahl;
    wertigkeit = werteMap.get(_zahl);

    if (farbenMap.get(farbe) == "H" || zahlenMap.get(zahl) == "U" || zahlenMap.get(zahl) == "O") {
      trumpf = true;
    } else {
      trumpf = false;
    }
  }

  private void initMap() {
    zahlenMap.put(ZAHL.NEUN, "9");
    zahlenMap.put(ZAHL.UNTER, "U");
    zahlenMap.put(ZAHL.OBER, "O");
    zahlenMap.put(ZAHL.KOENIG, "K");
    zahlenMap.put(ZAHL.ZEHN, "10");
    zahlenMap.put(ZAHL.ASS, "A");

    farbenMap.put(FARBE.EICHEL, "E");
    farbenMap.put(FARBE.BLATT, "B");
    farbenMap.put(FARBE.HERZ, "H");
    farbenMap.put(FARBE.SCHELLEN, "S");

    werteMap.put(ZAHL.NEUN, 0);
    werteMap.put(ZAHL.UNTER, 2);
    werteMap.put(ZAHL.OBER, 3);
    werteMap.put(ZAHL.KOENIG, 4);
    werteMap.put(ZAHL.ZEHN, 10);
    werteMap.put(ZAHL.ASS, 11);
  }

  public String get_karte() {
    return zahlenMap.get(zahl) + farbenMap.get(farbe);
  }

  public String get_trumpf() {
    return Boolean.toString(trumpf);
  }

}
