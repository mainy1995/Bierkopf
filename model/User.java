package Bierkopf.model;

import java.util.HashMap;
import java.util.List;

public class User extends Spieler {

  private HashMap<String, Boolean> farbenFreiMap;
  private EventUserInputListener listener;

  public User(Bierkopf _bierkopf, List<Karte> _handkarten, String _name, int _position) {
    super(_bierkopf, _handkarten, _name, _position);
    farbenFreiMap = new HashMap<String, Boolean>();
  }



  // Karten mit Eingabe auswählen
  @Override
  public Karte legeKarte(Stich liveStich) {
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

      
      
      // Aufruf des Events -> Benachrichtigen der Bierkopf-Klasse
     //if(listener!=null){
        String eingabe = listener.userLegtKarte();
       // }
      // Hier muss noch die Synchronisation zwischen Anklicken der Karte und Speichern in "eingabe" rein
      // ich habe bereits probiert die Eingabe über stdin mit einem '\r' oder/und ’\n' in Eingabe.readLine umzulenken
      // das hat aber leider nicht so ganz funktioniert. Vllt findet ihr den Trick
      
      //String eingabe = Eingabe.readLine("(Ich) Ich lege die Karte: ");

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
              // ausgewählte Karte ist nicht Trumpf und ich könnte Trumpf spielen
              if (!k.getTrumpf() && !trumpfFrei)
                P.pln("Trumpf muss zugegeben werden!");
              else {
                if (trumpfFrei)
                  bierkopf.spielerInfo.trumpfFrei[0] = true;
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
              // ausgewählte Karte ist unterschiedliche Farbe und ich könnte zugeben
              if (!k.getFarbe().contentEquals(farbe) && !farbenFreiMap.get(farbe))
                P.pln("Die Farbe " + farbe + " muss zugegeben werden");
              else {
                if (farbenFreiMap.get(farbe))
                  bierkopf.spielerInfo.setUserFarbe(farbe);
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

  public void setEventUserInputListener(EventUserInputListener listener){
    this.listener=listener;
  }
}
