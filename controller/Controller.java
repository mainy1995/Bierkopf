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
    private String zuSpielendeKarte = null;


    public Controller(View view, Bierkopf bierkopf) {

        this.view = view;
        this.bierkopf = bierkopf;
        view.initializeHandkarten(bierkopf.alleSpieler.get(0).handkarten);
        disableInput();
    }

    private void setZuSpielendeKarte(String s)
    {
        zuSpielendeKarte = s;
    } 

    public String getZuSpielendeKarte()
    {
        return zuSpielendeKarte;
    } 

    public void disableInput(){
        view.buttonMap.forEach((key,value) -> {
            view.buttonMap.get(key).setEnabled(false);
        }); 
        zuSpielendeKarte = null;
    }

    public void enableInput(){
        view.buttonMap.forEach((key,value) -> {
            view.buttonMap.get(key).setEnabled(true);
        }); 
    }

    public void updateHandkarten(List<Karte> handkarten) {
        for(Karte karte : handkarten){
            System.out.println(karte.getKarte());
        }
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

        if (src == view.gethandkartePos1()) {
            setZuSpielendeKarte(bierkopf.alleSpieler.get(0).handkarten.get(0).getKarte());
            // jbutton kann aus der Hand entfernt werden. Problem hierbei: Array Indexe wandern immer eins runter
            // heißt wenn erste Karte entfernt wird, dann muss die hinterste Karte entfernt werden:
            // Information über "hinterste Karte" -> Rundenzähler?
            view.gethandkartenPanel().remove(view.gethandkartePos1());
           // view.getContentPane().remove(view.gethandkartePos1());
            view.getContentPane().validate();
            view.getContentPane().repaint();
            //view.getContentPane()
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
        view.getkarteUnten().setVisible(true);
        disableInput();
    }


    public void updateNPCKarte(String kartenname,int position){
        if (position == 1){
            view.getkarteLinks().setIcon(view.rotateIcon(view.updateCard(kartenname,5)));
            view.getkarteLinks().setVisible(true);
        } else if (position == 2) {
            view.getkarteOben().setIcon(view.updateCard(kartenname,5));
            view.getkarteOben().setVisible(true);
        } else if (position == 3){
            view.getkarteRechts().setIcon(view.rotateIcon(view.updateCard(kartenname,5)));
            view.getkarteRechts().setVisible(true);
        }
    }

    public void raumeTischauf(){
        view.getkarteLinks().setVisible(false);
        view.getkarteUnten().setVisible(false);
        view.getkarteOben().setVisible(false);
        view.getkarteRechts().setVisible(false);
    }
}
