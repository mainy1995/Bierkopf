package Bierkopf;

import Bierkopf.controller.Controller;
import Bierkopf.model.Bierkopf;
import Bierkopf.view.View;

/**
 * Keine Möglichkeit zur Synchronisation zwischen Model und Controller
 */

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
        controller.registerEvents();
        bierkopf.setController(controller);
        view.setVisible(true);

        bierkopf.spielen();

        
    }

    /**
     * simply starts the builder class
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        for (int i = 1; i <= 1; i++) {
            new Start();

            try {  
                Thread.sleep(5000); 
            }
            catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }

    }
}
