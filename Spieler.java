package Bierkopf;

import java.util.ArrayList;
import java.util.List;

public class Spieler {
  protected String name;
  protected List<Karte> handkarten;
  protected Bierkopf bierkopf;
  protected boolean trumpfFrei, eichelFrei, blattFrei, schellenfrei;

  public Spieler(Bierkopf _bierkopf, List<Karte> _handkarten, String _name) {
    P.pln();
    P.pln("Konstruktor: Spieler(" + _name + ")");

    bierkopf = _bierkopf;
    handkarten = new ArrayList<Karte>();
    handkarten.addAll(_handkarten);
    name = _name;

    trumpfFrei = eichelFrei = blattFrei = schellenfrei = true;

    // eigene Handkarten auf "Freiheit" überprüfen
    for (Karte karte : handkarten) {
      if (karte.getTrumpf()) {
        if (trumpfFrei)
          trumpfFrei = false;
      } else {
        if (karte.getFarbe() == "E" && eichelFrei)
          eichelFrei = false;
        if (karte.getFarbe() == "B" && blattFrei)
          blattFrei = false;
        if (karte.getFarbe() == "S" && schellenfrei)
          schellenfrei = false;
      }
    }

    printAlleHandkarten();

    // Zustände mehr oder weniger schön ausgeben
    P.p("(" + name + ") Ich bin");

    if (trumpfFrei)
      P.p(" trumpffrei...");
    if (eichelFrei)
      P.p(" eichelfrei,");
    if (blattFrei)
      P.p(" blattfrei,");
    if (schellenfrei)
      P.p(" schellenfrei");
    P.pln(".");

  }

  // Ausgabe aller Handkarten
  public void printAlleHandkarten() {
    P.pln("(" + name + ") Meine Karten sind:");
    for (Karte karte : handkarten)
      P.pln(karte.getKarte() + " " + karte.getTrumpf());
  }

  public Karte legeKarte() {
    Karte ersteKarte = bierkopf.stich.ersteKarte();
    Karte dieKarte = null;

    // nicht rauskommen - also die erste Karte im Stich existiert
    if (ersteKarte != null) {
      // Trumpf ist gespielt
      if (ersteKarte.getTrumpf()) {
        if (trumpfFrei) {
          // ich habe keinen Trumpf
          dieKarte = kamikazeFunktion();
        } else {
          // ich gebe Trumpf zu
          dieKarte = ausFarbeWaehlen();
        }
      }
      // eine Farbe ist gepielt
      else {
        if (ersteKarte.getFarbe() == "E") {
          if (eichelFrei) {
            // ich habe keine Eichel
            dieKarte = kamikazeFunktion();
          } else {
            // ich gebe Eichel zu
            dieKarte = ausFarbeWaehlen();
          }
        } else if (ersteKarte.getFarbe() == "B") {
          if (blattFrei) {
            // ich habe keinen Blatt
            dieKarte = kamikazeFunktion();
          } else {
            // ich gebe Blatt zu
            dieKarte = ausFarbeWaehlen();
          }
        } else if (ersteKarte.getFarbe() == "S") {
          if (schellenfrei) {
            // ich habe keinen Schellen
            dieKarte = kamikazeFunktion();
          } else {
            // ich gebe Schellen zu
            dieKarte = ausFarbeWaehlen();
          }
        }
      }
    }
    // ich komme raus
    else {
      dieKarte = kamikazeFunktion();
    }

    removeKarte(dieKarte);
    P.pln();
    P.pln("(" + name + ") Ich lege die " + dieKarte.getKarte());
    return dieKarte;
  }

  protected void removeKarte(Karte k) {
    for (Karte karte : handkarten) {
      if (karte.getKarte().contentEquals(k.getKarte())) {
        handkarten.remove(karte);
        break;
      }
    }
  }

  // irgendwie Baum aufbauen und beste Karte für den Stich wählen
  private Karte kamikazeFunktion() {
    Karte k = null;
    // TODO Drachen bezwingen

    // k = handkarten.get(0);
    return k;
  }

  // aus mehreren oder einer Karte einer Farbe die beste für den Stich wählen
  private Karte ausFarbeWaehlen() {
    Karte k = null;
    // TODO weise Wählen
    return k;
  }
}
