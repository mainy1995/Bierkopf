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
    P.pln();
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

  /*
   * Es ist häßlich.
   * Szenario: KI gewinnt den ersten Stich und kommt raus. Optimierungen freilich
   * da.
   */
  public void gibAus() {
    P.pln("(" + name + ") Alle Kartenkombinationen, die folgen können sind:");
    int i = 0;
    for (Karte k1 : bierkopf.alleKarten)
      if (handkarten.contains(k1))// erste Karte ist innerhalb der Handkarten
        for (Karte k2 : bierkopf.alleKarten)
          if (!handkarten.contains(k2))// nachfolgenden nicht
            for (Karte k3 : bierkopf.alleKarten)
              if (!handkarten.contains(k3))
                for (Karte k4 : bierkopf.alleKarten)
                  if (!handkarten.contains(k4))
                    if (!(k1.equals(k2) || k1.equals(k3) || k1.equals(k4) || k2.equals(k3) || k2.equals(k4)
                        || k3.equals(k4))) {
                      P.pln(k1.getKarte() + " " + k2.getKarte() + " " + k3.getKarte() + " " + k4.getKarte());
                      i++;
                    }
    P.pln("Es sind noch " + i + " Kartenkombinationen im Spiel.");
  }

}
