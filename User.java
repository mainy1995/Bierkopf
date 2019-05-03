package Bierkopf;

import java.util.List;

public class User extends Spieler {

  public User(Bierkopf _bierkopf, List<Karte> _handkarten, String _name, int _position) {
    super(_bierkopf, _handkarten, _name, _position);
  }

  // Karten mit Eingabe auswählen
  @Override
  public Karte legeKarte(Stich liveStich) {
    int index = 0;
    boolean karteVorhanden = false;

    do {
      P.pln();
      P.pln("--------------------------------------");
      printAlleHandkarten();
      P.pln();
      String eingabe = Eingabe.readLine("(Ich) Ich lege die Karte: ");

      for (Karte k : handkarten) {
        if (eingabe.contentEquals(k.getKarte())) {
          karteVorhanden = true;
          index = handkarten.indexOf(k);
          P.pln("(Ich) Gelegte Karte: " + k.getKarte());
          break;
        }
      }

    } while (!karteVorhanden);

    Karte k = handkarten.get(index);
    removeKarte(k);
    return k;
  }
}
