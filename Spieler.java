package Bierkopf;

public class Spieler {
  protected String name;
  protected Karte[] handkarten;
  protected Bierkopf bierkopf;
  protected boolean trumpfFrei, eichelFrei, blattFrei, schellenfrei;

  public Spieler(Bierkopf _bierkopf, Karte[] _handkarten, String _name) {
    P.pln();
    P.pln("Konstruktor: Spieler(" + _name + ")");

    bierkopf = _bierkopf;
    handkarten = _handkarten;
    name = _name;

    trumpfFrei = eichelFrei = blattFrei = schellenfrei = true;

    for (int i = 0; i < handkarten.length; i++) {
      Karte k = handkarten[i];

      if (k.getTrumpf()) {
        if (trumpfFrei)
          trumpfFrei = false;
      } else {
        if (k.getFarbe() == "E" && eichelFrei)
          eichelFrei = false;
        if (k.getFarbe() == "B" && blattFrei)
          blattFrei = false;
        if (k.getFarbe() == "S" && schellenfrei)
          schellenfrei = false;
      }
    }
    // Ausgabe aller Handkarten
    P.pln("(" + name + ") Meine Karten sind:");
    for (int i = 0; i < handkarten.length; i++) {
      P.pln(handkarten[i].getKarte() + " " + handkarten[i].getTrumpf());
    }

    // Zustände mehr oder weniger schön ausgeben
    P.p("(" + name + ") Ich bin");
    if (trumpfFrei)
      P.p(" trumpffrei,");
    if (eichelFrei)
      P.p(" eichelfrei,");
    if (blattFrei)
      P.p(" blattfrei,");
    if (schellenfrei)
      P.p(" schellenfrei");
    P.pln(".");
  }

  // nicht ansatzweise
  public Karte legeKarte() {
    // nicht rauskommen - also die erste Karte im Stich existiert
    if (bierkopf.stich.ersteKarte() != null) {
      // Trumpf ist gespielt und ich habe einen Trumpf
      if (bierkopf.stich.ersteKarte().getTrumpf() && !trumpfFrei) {
        // Trumpf legen
      } else {
        // etc
      }
    }
    // Karte aus Array entfernen und dennoch mit Return zurückgeben?
    // dummy
    return handkarten[0];
  }

}
