package service;

import java.time.LocalDateTime;
import util.MobilQueue;
import repository.DBManager;
import util.ArraySewa;

public class DBService extends DBManager {

    public static boolean authLoginService(String username, String password) {
        if (authLogin(username, password)) {
            return true;
        }
        return false;
    }

    public static boolean authRegisterService(String idUser, String username, String password, String noKTP, String alamat, String noHP) {
        if (authRegister(idUser, username, password, noKTP, alamat, noHP)) {
            return true;
        }
        return false;
    }
    
    public static MobilQueue getMobilQueueService() {
        MobilQueue mobilQueue = getMobilQueue();
        if (mobilQueue.isEmpty()) {
            return null;
        }
        return mobilQueue;
    }

    public static String generateIDUserService() {
        String newIDUser = generateIDUser();
        if (newIDUser != null) {
            return newIDUser;
        }
        return "US001";
    }
    
    public static String generateIDSewaService() {
        String newIDSewa = generateIDSewa();
        if (newIDSewa != null) {
            return newIDSewa;
        }
        return "SE001";
    }

    public String addSewaService(String idSewa, LocalDateTime tglSewa, LocalDateTime tglPengembalian,
            boolean statusPengembalian, String idMobil, String idUser) {
        if (addSewa(idSewa, tglSewa, tglPengembalian, statusPengembalian, idMobil, idUser)) {
            if (updateStatusTersedia(idMobil, false)) {
                return "Berhasil menambahkan sewa";
            }
        }
        return "Gagal menambahkan sewa";
    }

    public static ArraySewa getSewaService(String idUser) {
        ArraySewa arraySewa = getSewa(getSizeSewa(), idUser);
        if (arraySewa.isEmpty()) {
            return null;
        }
        return arraySewa;
    }
    
    public static boolean setPengembalianService(String idSewa) {
        if (setPengembalian(idSewa)) {
            return true;
        }
        return false;
    }
    
    public static boolean updateStatusTersediaService(String idMobil, Boolean status) {
        if (updateStatusTersedia(idMobil, status)) {
            return true;
        }
        return false;
    }
}
