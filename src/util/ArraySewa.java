package util;

import java.sql.Timestamp;
import model.SewaModel;

public class ArraySewa {
    private SewaModel[] arrSewa;
    private int nElemen;

    public ArraySewa(int max) {
        arrSewa = new SewaModel[max];
        nElemen = 0;
    }
    
    public void insert(String IDSewa, String namaMobil, Timestamp tglSewa, Timestamp tglPengembalian, String IDMobil) {
        arrSewa[nElemen] = new SewaModel(IDSewa, namaMobil, tglSewa, tglPengembalian, IDMobil);
        nElemen++;
    }
    
    public String findIdMobil(String targetIDSewa) {
        for (int i = 0; i < nElemen; i++) {
            if (arrSewa[i].getIDSewa().equals(targetIDSewa)) {
                return arrSewa[i].getIDMobil();
            }
        }
        return null; 
    }
    
    public boolean isEmpty() {
        return nElemen == 0;
    }

    public int getnElemen() {
        return nElemen;
    }

    public SewaModel[] getArrSewa() {
        return arrSewa;
    }
    
    public String displayArraySewa(int index){
        return arrSewa[index].toString();
    }
}
