package Bierkopf;

import Bierkopf.controller.Controller;
import Bierkopf.model.Bierkopf;
import Bierkopf.view.View;

/**
 * Builder Class
 *
 * @author alex, yannik and lorenzo
 */
public class Start {

    public Start() {
        Bierkopf bierkopf = new Bierkopf();
        View view = new View();
        Controller controller = new Controller(view, bierkopf);
        view.setVisible(true);

        bierkopf.spielen();

        //controller.registerEvents();
    }

    /**
     * simply starts the builder class
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Start();
    }
}
