package Bierkopf;

import java.util.HashMap;

enum FARBE {
  SCHELLEN, HERZ, BLATT, EICHEL
}

enum ZAHL {
  NEUN, KOENIG, ZEHN, ASS, UNTER, OBER
}

public class Karte {
  public FARBE farbe;
  public ZAHL zahl;
  private boolean trumpf;

  private HashMap<ZAHL, String> zahlenMap;
  private HashMap<FARBE, String> farbenMap;
  private HashMap<ZAHL, Integer> punkteMap;

  public Karte(ZAHL _zahl, FARBE _farbe) {
    zahlenMap = new HashMap<ZAHL, String>();
    farbenMap = new HashMap<FARBE, String>();
    punkteMap = new HashMap<ZAHL, Integer>();
    initMap();

    farbe = _farbe;
    zahl = _zahl;

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

    punkteMap.put(ZAHL.NEUN, 0);
    punkteMap.put(ZAHL.UNTER, 2);
    punkteMap.put(ZAHL.OBER, 3);
    punkteMap.put(ZAHL.KOENIG, 4);
    punkteMap.put(ZAHL.ZEHN, 10);
    punkteMap.put(ZAHL.ASS, 11);
  }

  public String getKarte() {
    return getZahl() + getFarbe();
  }

  public String getFarbe() {
    return farbenMap.get(farbe);
  }

  public String getZahl() {
    return zahlenMap.get(zahl);
  }

  public int getPunkte() {
    return punkteMap.get(zahl);
  }

  public boolean getTrumpf() {
    return trumpf;
  }

}
