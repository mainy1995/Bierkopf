package Bierkopf;

public class Spieler {
  String name;
  Karte[] handkarten;
  Bierkopf bierkopf;
  boolean trumpfFrei, eichelFrei, blattFrei, schellenfrei;

  public Spieler(Bierkopf _bierkopf, Karte[] _handkarten, String _name) {
    P.pln();
    P.pln("Konstruktor: Spieler(" + _name + ")");
    trumpfFrei = eichelFrei = blattFrei = schellenfrei = true;

    bierkopf = _bierkopf;
    handkarten = _handkarten;
    name = _name;

    // Ausgabe aller Karten
    P.pln("(" + name + ") Meine Karten sind:");
    for (int i = 0; i < handkarten.length; i++) {
      P.pln(handkarten[i].getKarte() + " " + handkarten[i].getTrumpf());
    }
  }

  //nicht ansatzweise
  public Karte legeKarte() {
    // nicht rauskommen - also die erste Karte im Stich existiert
    if (bierkopf.stich.karten[0] != null) {
      // Trumpf ist gespielt und ich habe einen Trumpf
      if (bierkopf.stich.karten[0].trumpf && !trumpfFrei) {
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
