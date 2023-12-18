package model;

public class MobilModel {

    private String idMobil;
    private String namaMobil;
    private boolean statusTersedia;

    public MobilModel(String idMobil, String namaMobil, boolean statusTersedia) {
        this.idMobil = idMobil;
        this.namaMobil = namaMobil;
        this.statusTersedia = statusTersedia;
    }

    public String getIdMobil() {
        return idMobil;
    }

    public String getNamaMobil() {
        return namaMobil;
    }

    public boolean isStatusTersedia() {
        return statusTersedia;
    }


    @Override
    public String toString() {
        return idMobil + " " + namaMobil + " " + statusTersedia;
    }
}
