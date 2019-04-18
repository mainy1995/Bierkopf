package Bierkopf;

public class User extends Spieler {

  public User(Bierkopf _bierkopf, Karte[] _handkarten, String _name) {
    super(_bierkopf, _handkarten, _name);    
  }

  @Override
  public Karte legeKarte() {
    // Karten mit Eingabe auswählen

    // dummy
    return handkarten[0];
  }
}
