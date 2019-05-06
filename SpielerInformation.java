package Bierkopf;

public class SpielerInformation {

  public boolean[] trumpfFrei, eichelFrei, blattFrei, schellenFrei;

  // Zustände werden von jedem Spieler selbst auf TRUE gesetzt, wenn er nicht
  // Farbe/Trumpf zugeben kann
  public SpielerInformation() {
    // Default -> false
    trumpfFrei = new boolean[4];
    eichelFrei = new boolean[4];
    blattFrei = new boolean[4];
    schellenFrei = new boolean[4];
  }

  // Zustände des Users auf TRUE setzen
  public void setUserFarbe(String farbe) {
    switch (farbe) {
    case "E":
      eichelFrei[0] = true;
      break;
    case "B":
      blattFrei[0] = true;
      break;
    case "S":
      schellenFrei[0] = true;
      break;

    default:
      break;
    }

  }

}
