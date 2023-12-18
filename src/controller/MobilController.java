package controller;

import service.DBService;
import util.MobilQueue;
import util.ArraySewa;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class MobilController {

    private static MobilQueue mobilQueue;
    private static ArraySewa arraySewa;
    private static DBService service = new DBService();

    public static void getQueueMobil() {
        mobilQueue = service.getMobilQueueService();
    }

    public static boolean addSewa(int lamaSewa) {
        if (mobilQueueIsEmpty() == false) {
            String IDSewa = generateIDSewa();
            LocalDateTime tglSewa = LocalDateTime.now();
            LocalDateTime tglPengembalian = tglSewa.plus(lamaSewa, ChronoUnit.DAYS);
            LocalDateTime tglDikembalikan = null;
            boolean statusPengembalian = false;
            String IDMobil = peekIDMobil();
            String IDUser = service.auth.getId();
            service.addSewaService(IDSewa, tglSewa, tglPengembalian, statusPengembalian, IDMobil, IDUser);
            mobilQueue.remove();
            return true;
        } else {
            System.out.println("Tidak ada mobil tersedia");
            return false;
        }
    }

    public static boolean setPengembalian(String idSewa) {
        if (service.setPengembalianService(idSewa)
                && service.updateStatusTersediaService(arraySewa.findIdMobil(idSewa), true)) {
            return true;
        } else {
            return false;
        }
    }

    public static String generateIDSewa() {
        String newIDSewa = DBService.generateIDSewaService();
        return newIDSewa;
    }

    public static String peekIDMobil() {
        return mobilQueue.peekFront().getIdMobil();
    }

    public static String peekNamaMobil() {
        return mobilQueue.peekFront().getNamaMobil();
    }

    public static boolean mobilQueueIsEmpty() {
        return mobilQueue == null;
    }

    public static ArraySewa getArraySewa() {
        String IDUser = service.auth.getId();
        arraySewa = service.getSewaService(IDUser);
        if (arraySewa != null) {
            return arraySewa;
        } else {
            return null;
        }
    }
    
    public static boolean arraySewaIsEmpty(){
        if (arraySewa.isEmpty()) {
            return true;
        }
        return false;
    }
}
