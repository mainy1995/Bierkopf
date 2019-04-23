package Bierkopf;

import java.util.List;

public class User extends Spieler {

  public User(Bierkopf _bierkopf, List<Karte> _handkarten, String _name) {
    super(_bierkopf, _handkarten, _name);
  }

//Karten mit Eingabe auswählen
  @Override
  public Karte legeKarte() {

    // dummy
    Karte k = handkarten.get(0);
    P.pln("(" + name + ") Ich lege die Karte: " + k.getKarte());
    removeKarte(k);
    return k;
  }
}
