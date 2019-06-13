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
        disableInput();
    }

    public void disableInput(){
        view.buttonMap.forEach((key,value) -> {
            view.buttonMap.get(key).setEnabled(false);
        }); 
    }

    public void enableInput(){
        view.buttonMap.forEach((key,value) -> {
            view.buttonMap.get(key).setEnabled(true);
        }); 
    }

    public void getHandKarten() {
        view.updateHandkarten(bierkopf.alleSpieler.get(0).handkarten);
    }

    public void registerEvents() {
        view.gethandkartePos1().addActionListener(this);
        view.gethandkartePos2().addActionListener(this);
        view.gethandkartePos3().addActionListener(this);
        view.gethandkartePos4().addActionListener(this);
        view.gethandkartePos5().addActionListener(this);
        view.gethandkartePos6().addActionListener(this);
    }


    // Synchronisation zwischen Spielablauf problematisch: Spieler handkarten sollten in einem eigenen Thread laufen
    @Override
    public void actionPerformed(ActionEvent evt) {
        Object src = evt.getSource();
        String zuSpielendeKarte = "";

        if (src == view.gethandkartePos1()) {
            zuSpielendeKarte = bierkopf.alleSpieler.get(0).handkarten.get(0).getKarte();
        } else if (src == view.gethandkartePos2()) {
            zuSpielendeKarte = bierkopf.alleSpieler.get(0).handkarten.get(1).getKarte();
        } else if (src == view.gethandkartePos3()) {
            zuSpielendeKarte = bierkopf.alleSpieler.get(0).handkarten.get(2).getKarte();
        } else if (src == view.gethandkartePos4()) {
            zuSpielendeKarte = bierkopf.alleSpieler.get(0).handkarten.get(3).getKarte();
        } else if (src == view.gethandkartePos5()) {
            zuSpielendeKarte = bierkopf.alleSpieler.get(0).handkarten.get(4).getKarte();
        } else if (src == view.gethandkartePos6()) {
            zuSpielendeKarte = bierkopf.alleSpieler.get(0).handkarten.get(5).getKarte();
        }

        view.getkarteUnten().setIcon(view.updateCard(zuSpielendeKarte,5));
        
    }


    public void updateNPCKarte(String kartenname,int position){
        if (position == 1){
            view.getkarteLinks().setIcon(view.rotateIcon(view.updateCard(kartenname,5)));
        } else if (position == 2) {
            view.getkarteOben().setIcon(view.updateCard(kartenname,5));
        } else if (position == 3){
            view.getkarteRechts().setIcon(view.rotateIcon(view.updateCard(kartenname,5)));
        }
    }
}
