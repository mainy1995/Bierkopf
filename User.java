package Bierkopf;

import java.util.List;

public class User extends Spieler {

  public User(Bierkopf _bierkopf, List<Karte> _handkarten, String _name, int _position) {
    super(_bierkopf, _handkarten, _name, _position);
  }

  // Karten mit Eingabe auswählen
  @Override
  public Karte legeKarte(Stich liveStich, Boolean virtuell) {
    Karte ersteKarte = liveStich.ersteKarte();
    int index = 0;
    boolean karteVorhanden = false;

    checkFreiheit();
    farbenFreiMap.clear();
    farbenFreiMap.put("E", eichelFrei);
    farbenFreiMap.put("B", blattFrei);
    farbenFreiMap.put("S", schellenFrei);

    do {
      P.pln();
      P.pln("-----------------------------------------------------");

      printAlleHandkarten();
      P.pln();
      String eingabe = Eingabe.readLine("(Ich) Ich lege die Karte: ");

      if (ersteKarte == null) {
        // spiele beliebige Karte
        for (Karte k : handkarten) {
          if (eingabe.contentEquals(k.getKarte())) {
            karteVorhanden = true;
            index = handkarten.indexOf(k);
            P.pln("(Ich) Gelegte Karte: " + k.getKarte());
            break;
          }
        }
      }
      // Farbe/Trumpf bekennen
      else {
        // Trumpf is' gespielt
        if (ersteKarte.getTrumpf()) {
          for (Karte k : handkarten) {
            if (eingabe.contentEquals(k.getKarte())) {
              // ausgewählte Karte ist nicht Trumpf und ich
              // könnte Trumpf spielen
              if (!k.getTrumpf() && !trumpfFrei)
                P.pln("Trumpf muss zugegeben werden!");
              else {
                if (trumpfFrei)
                  bierkopf.spielerInfo.trumpfFrei[position] = true;
                // spiele ausgewählten Trumpf
                karteVorhanden = true;
                index = handkarten.indexOf(k);
                P.pln("(Ich) Gelegte Karte: " + k.getKarte());
              }
              break;
            }
          }
        }
        // kein Trumpf
        else {
          String farbe = ersteKarte.getFarbe();
          for (Karte k : handkarten) {
            if (eingabe.contentEquals(k.getKarte())) {
              // ausgewählte Karte ist unterschiedliche Farbe und
              // ich könnte zugeben
              if (!k.getFarbe().contentEquals(farbe) && !farbenFreiMap.get(farbe))
                P.pln("Die Farbe " + farbe + " muss zugegeben werden");
              else {
                if (farbenFreiMap.get(farbe))
                  bierkopf.spielerInfo.setSpielerFarbe(farbe, position);
                // spiele ausgewählte Karte
                karteVorhanden = true;
                index = handkarten.indexOf(k);
                P.pln("(Ich) Gelegte Karte: " + k.getKarte());
              }
              break;
            }
          }
        }
      }
    } while (!karteVorhanden);

    Karte k = handkarten.get(index);
    removeKarte(k);
    return k;
  }
}
