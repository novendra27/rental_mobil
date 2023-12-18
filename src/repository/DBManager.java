package repository;

import model.AuthModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import model.MobilModel;
import model.SewaModel;
import util.ArraySewa;
import util.MobilQueue;

public class DBManager {

    public static AuthModel auth;
    public static MobilModel mobil;
    public static SewaModel sewa;
    public static MobilQueue mobilQueue;
    public static ArraySewa arraySewa;

    protected static boolean authLogin(String username, String password) {
        try {
            String sql = "SELECT * FROM `tb_auth` WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                auth = new AuthModel(result.getString("id_user"), result.getString("username"), 
                        result.getString("password"), result.getInt("role"));
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    protected static boolean authRegister(String idUser, String username, String password, String noKTP, String alamat, String noHP) {
        try {
            String sql = "INSERT INTO tb_auth (`id_user`, `username`, `password`, `no_ktp`, `alamat`, `no_hp`, `role`) "
                    + "VALUES (?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, idUser);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, noKTP);
            preparedStatement.setString(5, alamat);
            preparedStatement.setString(6, noHP);
            preparedStatement.setInt(7, 1);
            int result = preparedStatement.executeUpdate();
            if (result == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    protected static MobilQueue getMobilQueue() {
        MobilQueue mobilQueue = new MobilQueue();
        try {
            String sql = "SELECT * FROM `tb_mobil` WHERE status_tersedia = '1'";
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(sql);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                mobil = new MobilModel(result.getString("id_mobil"), result.getString("nama_mobil"), 
                        result.getBoolean("status_tersedia"));
                mobilQueue.insert(mobil);
            }
            return mobilQueue;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected static boolean addSewa(String idSewa, LocalDateTime tglSewa, LocalDateTime tglPengembalian,
            boolean statusPengembalian, String idMobil, String idUser) {
        try {
            String sql = "INSERT INTO `tb_sewa`(`id_sewa`, `tgl_sewa`, `tgl_pengembalian`, `tgl_dikembalikan`, "
                    + "`status_pengembalian`, `id_mobil`, `id_user`) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(sql);

            Timestamp timestampTglSewa = java.sql.Timestamp.valueOf(tglSewa);
            Timestamp timestampTglPengembalian = java.sql.Timestamp.valueOf(tglPengembalian);
            Timestamp tglDikembalikan = null;

            preparedStatement.setString(1, idSewa);
            preparedStatement.setTimestamp(2, timestampTglSewa);
            preparedStatement.setTimestamp(3, timestampTglPengembalian);
            preparedStatement.setTimestamp(4, tglDikembalikan);
            preparedStatement.setBoolean(5, statusPengembalian);
            preparedStatement.setString(6, idMobil);
            preparedStatement.setString(7, idUser);
            int result = preparedStatement.executeUpdate();
            if (result == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    protected static boolean updateStatusTersedia(String idMobil, Boolean status) {
        try {
            String sql = "UPDATE `tb_mobil` SET `status_tersedia`= ? WHERE id_mobil = ?";
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(sql);
            preparedStatement.setBoolean(1, status);
            preparedStatement.setString(2, idMobil);
            int result = preparedStatement.executeUpdate();
            if (result == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    protected static String generateIDUser() {
        try {
            String sql = "SELECT MAX(id_user) FROM `tb_auth`";
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(sql);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                String lastID = result.getString("MAX(id_user)");
                if (lastID != null) {
                    int lastNumber = Integer.parseInt(lastID.substring(2));
                    int newNumber = lastNumber + 1;
                    if (lastNumber < 100) {
                        return "US00" + newNumber;
                    } else {
                        return "US0" + newNumber;
                    }
                }
            }
            return "US001";
        } catch (SQLException e) {
            e.printStackTrace();
            return "US001";
        }
    }
    
    protected static String generateIDSewa() {
        try {
            String sql = "SELECT MAX(id_sewa) FROM `tb_sewa`";
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(sql);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                String lastID = result.getString("MAX(id_sewa)");
                if (lastID != null) {
                    int lastNumber = Integer.parseInt(lastID.substring(2));
                    int newNumber = lastNumber + 1;
                    if (lastNumber < 100) {
                        return "SE0" + newNumber;
                    } else {
                        return "SE" + newNumber;
                    }
                }
            }
            return "SE001";
        } catch (SQLException e) {
            e.printStackTrace();
            return "SE001";
        }
    }

    protected static ArraySewa getSewa(int size, String idUser) {
        ArraySewa arraySewa = new ArraySewa(size);
        try {
            String sql = "SELECT s.id_sewa, m.nama_mobil, s.tgl_sewa, s.tgl_pengembalian, m.id_mobil "
                    + "FROM `tb_sewa` s "
                    + "LEFT OUTER JOIN `tb_mobil` m ON s.id_mobil = m.id_mobil "
                    + "WHERE s.status_pengembalian = 0 && s.id_user = ?;";
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, idUser);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                arraySewa.insert(result.getString("s.id_sewa"), result.getString("m.nama_mobil"), result.getTimestamp("s.tgl_sewa"), 
                        result.getTimestamp("s.tgl_pengembalian"), result.getString("m.id_mobil"));
            }
            return arraySewa;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected static int getSizeSewa() {
        try {
            String sql = "SELECT COUNT(id_sewa) FROM tb_sewa;";
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(sql);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                int size = result.getInt("COUNT(id_sewa)");
                return size;
            }
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    protected static boolean setPengembalian(String idSewa) {
        try {
            String sql = "UPDATE tb_sewa SET status_pengembalian = ? WHERE id_sewa = ?";
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(sql);
            preparedStatement.setBoolean(1, true);
            preparedStatement.setString(2, idSewa);
            int result = preparedStatement.executeUpdate();
            if (result == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
   
    public static void main(String[] args) {
//        getMobilTersedia();
    }

}
