package Bierkopf;

import java.util.HashMap;

public class SpielerInformation {

  public boolean[] trumpfFrei, eichelFrei, blattFrei, schellenFrei;
  private HashMap<String, boolean[]> spielerFarbMap;

  // Zustände werden von jedem Spieler selbst auf TRUE gesetzt, wenn er nicht
  // Farbe/Trumpf zugeben kann
  public SpielerInformation() {
    // Default -> false
    trumpfFrei = new boolean[4];
    eichelFrei = new boolean[4];
    blattFrei = new boolean[4];
    schellenFrei = new boolean[4];

    spielerFarbMap = new HashMap<String, boolean[]>();
    spielerFarbMap.put("E", eichelFrei);
    spielerFarbMap.put("B", blattFrei);
    spielerFarbMap.put("S", schellenFrei);
  }

  // Zustände des Users auf TRUE setzen
  public void setSpielerFarbe(String farbe, int position) {
    spielerFarbMap.get(farbe)[position] = true;
  }

}
