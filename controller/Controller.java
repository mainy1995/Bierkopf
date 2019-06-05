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

    @Override
    public void actionPerformed(ActionEvent evt) {
        Object src = evt.getSource();

        if (src == view.gethandkartePos1()) {
            System.out.println("Handkarte 1");
        } else if (src == view.gethandkartePos2()) {
            System.out.println("Handkarte 2");
        } else if (src == view.gethandkartePos3()) {
            System.out.println("Handkarte 3");
        } else if (src == view.gethandkartePos4()) {
            System.out.println("Handkarte 4");
        } else if (src == view.gethandkartePos5()) {
            System.out.println("Handkarte 5");
        } else if (src == view.gethandkartePos6()) {
            System.out.println("Handkarte 6");
        }
    }
}
