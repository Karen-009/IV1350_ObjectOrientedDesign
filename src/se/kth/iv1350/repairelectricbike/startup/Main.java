package se.kth.iv1350.repairelectricbike.startup;

import se.kth.iv1350.repairelectricbike.controller.Controller;
import se.kth.iv1350.repairelectricbike.view.View;
import se.kth.iv1350.repairelectricbike.integration.Printer;
import se.kth.iv1350.repairelectricbike.integration.RegistryCreator;

/**
 * Starts the entire application and contains the main method used to initiate
 * controller, view and integration.
 */

public class Main {
    /**
     * The main operation used to start the whole application.
     * 
     * @param args The application does not take any command line arguments.
     */

    public static void main(String[] args) {
        RegistryCreator creator = new RegistryCreator();
        Printer printer = new Printer();

        Controller contr = new Controller(creator, printer);

        View view = new View(contr);

        view.runFakeExecution();
    }
}
