package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import entity.Admin;

public class AdminDao {

    // Register Admin
    public int registerAdmin(Admin admin) {

        int result = 0;

        try {
            Connection con = DBUtil.makeConnection();

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO admin(name,email,password) VALUES(?,?,?)");

            ps.setString(1, admin.getName());
            ps.setString(2, admin.getEmail());
            ps.setString(3, admin.getPassword());

            result = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    // Get Admin By Email
    public Admin getAdminByEmail(String email) {

        Admin admin = null;

        try {
            Connection con = DBUtil.makeConnection();

            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM admin WHERE email=?");

            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                admin = new Admin(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return admin;
    }

    // Check Email Exists
    public boolean emailExists(String email) {

        boolean exists = false;

        try {
            Connection con = DBUtil.makeConnection();

            PreparedStatement ps = con.prepareStatement(
                    "SELECT id FROM admin WHERE email=?");

            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                exists = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return exists;
    }
    //update pwd
    public void updatePassword(String email, String password){

        try {
            Connection con = DBUtil.makeConnection();

            String sql = "update admin set password=? where email=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, password);
            ps.setString(2, email);

            ps.executeUpdate();

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}