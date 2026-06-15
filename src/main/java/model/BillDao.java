package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Bill;

public class BillDao {

    // Add Bill
    public boolean addBill(Bill bill) {

        boolean flag = false;

        try (Connection con = DBUtil.makeConnection()) {

            String sql = "INSERT INTO bill(patient_id, amount, is_paid) VALUES(?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, bill.getPatientId());
            ps.setDouble(2, bill.getAmount());
            ps.setBoolean(3, bill.isPaid());

            flag = ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }

    // Update Bill
    public boolean updateBill(Bill bill) {

        boolean flag = false;

        try (Connection con = DBUtil.makeConnection()) {

            String sql = "UPDATE bill SET patient_id=?, amount=?, is_paid=? WHERE id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, bill.getPatientId());
            ps.setDouble(2, bill.getAmount());
            ps.setBoolean(3, bill.isPaid());
            ps.setInt(4, bill.getId());

            flag = ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }

    // Delete Bill
    public boolean deleteBill(int id) {

        boolean flag = false;

        try (Connection con = DBUtil.makeConnection()) {

            String sql = "DELETE FROM bill WHERE id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            flag = ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }

    // Get Bill By Id
    public Bill getBillById(int id) {

        Bill bill = null;

        try (Connection con = DBUtil.makeConnection()) {

            String sql = "SELECT * FROM bill WHERE id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                bill = new Bill();

                bill.setId(rs.getInt("id"));
                bill.setPatientId(rs.getInt("patient_id"));
                bill.setAmount(rs.getDouble("amount"));
                bill.setPaid(rs.getBoolean("is_paid"));
                bill.setCreatedAt(rs.getTimestamp("created_at"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bill;
    }

    // Get All Bills
    public List<Bill> getAllBills() {

        List<Bill> list = new ArrayList<>();

        try (Connection con = DBUtil.makeConnection()) {

            String sql =
                "SELECT b.id, b.patient_id, p.name, b.amount, b.is_paid " +
                "FROM bill b " +
                "JOIN patient p ON b.patient_id = p.id " +
                "ORDER BY b.id DESC";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Bill bill = new Bill();

                bill.setId(rs.getInt("id"));
                bill.setPatientId(rs.getInt("patient_id"));
                bill.setPatientName(rs.getString("name"));
                bill.setAmount(rs.getDouble("amount"));
                bill.setPaid(rs.getBoolean("is_paid"));

                list.add(bill);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // Search Bills by Patient Name
    public List<Bill> searchBills(String keyword) {

        List<Bill> list = new ArrayList<>();

        try (Connection con = DBUtil.makeConnection()) {

            String sql =
                "SELECT b.id, b.patient_id, p.name, b.amount, b.is_paid " +
                "FROM bill b " +
                "JOIN patient p ON b.patient_id = p.id " +
                "WHERE p.name LIKE ? " ;
                

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Bill bill = new Bill();

                bill.setId(rs.getInt("id"));
                bill.setPatientId(rs.getInt("patient_id"));
                bill.setPatientName(rs.getString("name"));
                bill.setAmount(rs.getDouble("amount"));
                bill.setPaid(rs.getBoolean("is_paid"));

                list.add(bill);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // Pagination
    public List<Bill> getBillsByPage(int start, int limit) {

        List<Bill> list = new ArrayList<>();

        try (Connection con = DBUtil.makeConnection()) {

            String sql =
                "SELECT b.id, b.patient_id, p.name, b.amount, b.is_paid " +
                "FROM bill b " +
                "JOIN patient p ON b.patient_id = p.id " +
                "ORDER BY b.id DESC LIMIT ?,?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, start);
            ps.setInt(2, limit);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Bill bill = new Bill();

                bill.setId(rs.getInt("id"));
                bill.setPatientId(rs.getInt("patient_id"));
                bill.setPatientName(rs.getString("name"));
                bill.setAmount(rs.getDouble("amount"));
                bill.setPaid(rs.getBoolean("is_paid"));

                list.add(bill);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    // Total Bills
    public int getTotalBills() {

        int total = 0;

        try (Connection con = DBUtil.makeConnection()) {

            String sql = "SELECT COUNT(*) FROM bill";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                total = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return total;
    }

    

}