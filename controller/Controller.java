package Bierkopf.controller;

import Bierkopf.model.Bierkopf;
import Bierkopf.model.Karte;
import Bierkopf.view.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * calls the bierkopf to do things for the view
 *
 * @author alex, yannik and lorenzo
 */
public class Controller implements ActionListener {

    private View view;
    private Bierkopf bierkopf;

    public Controller(View view, Bierkopf bierkopf) {

        this.view = view;
        this.bierkopf = bierkopf;
        getHandKarten();

        // Hier müssen dazu noch die anderen Events registriert werden -> Verzahnung mit Modell: Spielablauf
        view.addSpieleKarte1Listener(new spieleKarteListener());
    }

    public void getHandKarten() {
        view.updateHandkarten(bierkopf.alleSpieler.get(0).handkarten);
    }

    ////// inner Class spieleKarteListener ////
    // Frage: Detektion, welche Karte wirklich gespielt wurde, ist die gespielte Karte erlaubt? 
    // ->>>Verzahnung mit Modell
    class spieleKarteListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            //view.getkarteUnten().setIcon(updateCard("Schell_Blatt",1));
            System.out.println("Event detektiert");

        }
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
//    if (bandit.isRunning())
//    {
//      view.getBtnStartStop().setText("Start");
//    }
//    else
//    {
//      view.getBtnStartStop().setText("Stop");
//    }
//    bandit.rollDice();
    }
}
