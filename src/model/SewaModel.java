package model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class SewaModel {
    String IDSewa;
    String namaMobil;
    Timestamp tglSewa;
    Timestamp tglPengembalian;
    String IDMobil;

    public SewaModel(String IDSewa, String namaMobil, Timestamp tglSewa, Timestamp tglPengembalian, String IDMobil) {
        this.IDSewa = IDSewa;
        this.namaMobil = namaMobil;
        this.tglSewa = tglSewa;
        this.tglPengembalian = tglPengembalian;
        this.IDMobil = IDMobil;
    }
     
    public String getIDSewa() {
        return IDSewa;
    }

    public String getNamaMobil() {
        return namaMobil;
    }

    public Timestamp getTglSewa() {
        return tglSewa;
    }

    public Timestamp getTglPengembalian() {
        return tglPengembalian;
    }

    public String getIDMobil() {
        return IDMobil;
    }

    @Override
    public String toString() {
        return "SewaModel{" + "IDSewa=" + IDSewa + ", namaMobil=" + namaMobil + ", tglSewa=" + tglSewa + ", tglPengembalian=" + tglPengembalian + ", IDMobil=" + IDMobil + '}';
    }
    
}
