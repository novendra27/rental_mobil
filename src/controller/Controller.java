package controller;

import view.Login;

public class Controller {

    private static MobilController mobilController;
    private static Login frameLogin = new Login();
            
    public void mainApp() {
        mobilController.getQueueMobil();
        frameLogin.setVisible(true);
    }
}
