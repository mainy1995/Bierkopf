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

    public void setUserText(String s){
        view.getUserText().setText(s);
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
            setZuSpielendeKarte(view.handkartenMap.get(view.gethandkartePos1()));
        } else if (src == view.gethandkartePos2()) {
            zuSpielendeKarte = view.handkartenMap.get(view.gethandkartePos2());
        } else if (src == view.gethandkartePos3()) {
            zuSpielendeKarte = view.handkartenMap.get(view.gethandkartePos3());
        } else if (src == view.gethandkartePos4()) {
            zuSpielendeKarte = view.handkartenMap.get(view.gethandkartePos4());
        } else if (src == view.gethandkartePos5()) {
            zuSpielendeKarte = view.handkartenMap.get(view.gethandkartePos5());
        } else if (src == view.gethandkartePos6()) {
            zuSpielendeKarte = view.handkartenMap.get(view.gethandkartePos6());
        }

        disableInput();
    }


    public void updateNPCKarte(String kartenname,int position){
        switch (position) {
            case 1:
                view.getkarteLinks().setIcon(view.rotateIcon(view.updateCard(kartenname,5)));
                view.getkarteLinks().setVisible(true);
                break;
            case 2:
                view.getkarteOben().setIcon(view.updateCard(kartenname,5));
                view.getkarteOben().setVisible(true);
                break;
            case 3:
                view.getkarteRechts().setIcon(view.rotateIcon(view.updateCard(kartenname,5)));
                view.getkarteRechts().setVisible(true);
                break;
            default:
                break;
        }
    }

    public void removeKarte(String s){
        view.getkarteUnten().setIcon(view.updateCard(zuSpielendeKarte,5));
        view.getkarteUnten().setVisible(true);
        view.gethandkartenPanel().remove(view.buttonMap.get(s));
        view.getContentPane().validate();
        view.getContentPane().repaint();
        view.getkarteUnten().setVisible(true);
    }


    public void raumeTischauf(){
        view.getkarteLinks().setVisible(false);
        view.getkarteUnten().setVisible(false);
        view.getkarteOben().setVisible(false);
        view.getkarteRechts().setVisible(false);
    }
}
