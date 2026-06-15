package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Patient;

public class PatientDao {
	
//read
	public List<Patient> getAllPatients() {

	    List<Patient> list = new ArrayList<>();

	    try {
	        Connection con = DBUtil.makeConnection();
	        PreparedStatement ps = con.prepareStatement("SELECT * FROM patient ");

	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {

	            list.add(new Patient(
	                    rs.getInt("id"),
	                    rs.getString("name"),
	                    rs.getString("email"),
	                    rs.getString("phone"),
	                    rs.getString("gender"),
	                    rs.getInt("age"),
	                    rs.getString("address"),
	                    rs.getString("disease"),
	                    rs.getString("blood")
	            ));
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return list;
	}
	
	//search
	
	public List<Patient> searchPatients(String keyword) {

	    List<Patient> list = new ArrayList<>();

	    try {
	        Connection con = DBUtil.makeConnection();
	        
	        PreparedStatement ps = con.prepareStatement("SELECT * FROM patient WHERE name LIKE ? OR phone LIKE ? ");

	        ps.setString(1, "%" + keyword + "%");
	        ps.setString(2, "%" + keyword + "%");

	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {

	            list.add(new Patient(
	                    rs.getInt("id"),
	                    rs.getString("name"),
	                    rs.getString("email"),
	                    rs.getString("phone"),
	                    rs.getString("gender"),
	                    rs.getInt("age"),
	                    rs.getString("address"),
	                    rs.getString("disease"),
	                    rs.getString("blood")
	            ));
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return list;
	}
	
	//add 
	public int addPatient(Patient p) {

        int result = 0;

        try {
            Connection con = DBUtil.makeConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO patient(name,email,phone,gender,age,address,disease,blood) VALUES (?,?,?,?,?,?,?,?)");

            ps.setString(1, p.getName());
            ps.setString(2, p.getEmail());
            ps.setString(3, p.getPhone());
            ps.setString(4, p.getGender());
            ps.setInt(5, p.getAge());
            ps.setString(6, p.getAddress());
            ps.setString(7, p.getDisease());
            ps.setString(8, p.getBlood());

            result = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
	
	//delete
	
	public int deletePatient(int id) {

        int result = 0;

        try {
            Connection con = DBUtil.makeConnection();
            PreparedStatement ps = con.prepareStatement("DELETE FROM patient WHERE id = ?");

            ps.setInt(1, id);

            result = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
	
    //get by id (edit ke liye)
  
	public Patient getById(int id) {

	    Patient p = null;

	    try {
	        Connection con = DBUtil.makeConnection();

	        PreparedStatement pst =
	                con.prepareStatement(
	                "SELECT * FROM patient WHERE id=?");

	        pst.setInt(1, id);

	        ResultSet rs = pst.executeQuery();

	        if (rs.next()) {

	            p = new Patient(
	                    rs.getInt("id"),
	                    rs.getString("name"),
	                    rs.getString("email"),
	                    rs.getString("phone"),
	                    rs.getString("gender"),
	                    rs.getInt("age"),
	                    rs.getString("address"),
	                    rs.getString("disease"),
	                    rs.getString("blood")
	            );
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return p;
	}
	
	//update
	
	public int updatePatient(Patient p) {

	    int result = 0;

	    try {
	        Connection con = DBUtil.makeConnection();

	        PreparedStatement ps = con.prepareStatement("UPDATE patient SET name=?, email=?, phone=?, gender=?, age=?, address=?, disease=?, blood=? WHERE id=?");

	        ps.setString(1, p.getName());
	        ps.setString(2, p.getEmail());
	        ps.setString(3, p.getPhone());
	        ps.setString(4, p.getGender());
	        ps.setInt(5, p.getAge());
	        ps.setString(6, p.getAddress());
	        ps.setString(7, p.getDisease());
	        ps.setString(8, p.getBlood());
	        ps.setInt(9, p.getId());

	        result = ps.executeUpdate();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return result;
	}
	
	
	//getPatientsByPage
	public List<Patient> getPatientsByPage(int start, int total) {

	    List<Patient> list = new ArrayList<>();

	    try {
	        Connection con = DBUtil.makeConnection();

	        PreparedStatement pst = con.prepareStatement(
	            "SELECT * FROM patient LIMIT ?, ?"
	        );

	        pst.setInt(1, start);
	        pst.setInt(2, total);

	        ResultSet rs = pst.executeQuery();

	        while (rs.next()) {

	            Patient p = new Patient(
	                rs.getInt("id"),
	                rs.getString("name"),
	                rs.getString("email"),
	                rs.getString("phone"),
	                rs.getString("gender"),
	                rs.getInt("age"),
	                rs.getString("address"),
	                rs.getString("disease"),
	                rs.getString("blood")
	            );

	            list.add(p);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return list;
	}

	//getTotalPatients
	public int getTotalPatients() {

	    int total = 0;

	    try {
	        Connection con = DBUtil.makeConnection();

	        PreparedStatement pst = con.prepareStatement(
	            "SELECT COUNT(*) FROM patient"
	        );

	        ResultSet rs = pst.executeQuery();

	        if (rs.next()) {
	            total = rs.getInt(1);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return total;
	}
}
