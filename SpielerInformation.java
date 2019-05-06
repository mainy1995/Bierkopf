package Bierkopf;

import java.util.HashMap;

public class SpielerInformation {

  public boolean[] trumpfFrei, eichelFrei, blattFrei, schellenFrei;
  private HashMap<String, boolean[]> userFarbMap;

  // Zustände werden von jedem Spieler selbst auf TRUE gesetzt, wenn er nicht
  // Farbe/Trumpf zugeben kann
  public SpielerInformation() {
    // Default -> false
    trumpfFrei = new boolean[4];
    eichelFrei = new boolean[4];
    blattFrei = new boolean[4];
    schellenFrei = new boolean[4];

    userFarbMap = new HashMap<String, boolean[]>();
    userFarbMap.put("E", eichelFrei);
    userFarbMap.put("B", blattFrei);
    userFarbMap.put("S", schellenFrei);
  }

  // Zustände des Users auf TRUE setzen
  public void setUserFarbe(String farbe) {
    userFarbMap.get(farbe)[0] = true;
  }

}
