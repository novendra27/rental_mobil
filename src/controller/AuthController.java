package controller;

import service.DBService;
import model.AuthModel;

public class AuthController {

    private static AuthModel authModel;
    private static DBService service = new DBService();

    public static boolean login(String username, String password) {
        if (service.authLoginService(username, password)) {
            return true;
        }
        return false;
    }
    
    public static boolean register(String username, String password, String noKTP, String alamat, String noHP) {
        if (service.authRegisterService(generateIDUser(), username, password, noKTP, alamat, noHP)) {
            return true;
        }
        return false;
    }
    
    public static String generateIDUser() {
        String newIDUser = DBService.generateIDUserService();
        return newIDUser;
    }
    
    public static String getUsername(){
        return service.auth.getUsername();
    }
    
    public static int getRole(){
        return service.auth.getRole();
    }
}
