package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Doctor;

public class DoctorDao {
	
     //add
	 public int addDoctor(Doctor d) {
	        int i = 0;

	        try {
	            Connection con = DBUtil.makeConnection();

	            PreparedStatement ps = con.prepareStatement(
	                "INSERT INTO doctor(name, email, phone, specialization, experience, department_id) VALUES(?,?,?,?,?,?)"
	            );

	            ps.setString(1, d.getName());
	            ps.setString(2, d.getEmail());
	            ps.setString(3, d.getPhone());
	            ps.setString(4, d.getSpecialization());
	            ps.setInt(5, d.getExperience());
	            ps.setInt(6, d.getDepartmentId());

	            i = ps.executeUpdate();

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return i;
	    }
	 
	 //read
	 public List<Doctor> getAllDoctors() {

	        List<Doctor> list = new ArrayList<>();

	        try {
	            Connection con = DBUtil.makeConnection();

	            PreparedStatement ps = con.prepareStatement("SELECT * FROM doctor");
	            ResultSet rs = ps.executeQuery();

	            while (rs.next()) {

	            	Doctor d = new Doctor(
		                    rs.getInt("id"),
		                    rs.getString("name"),
		                    rs.getString("email"),
		                    rs.getString("phone"),
		                    rs.getString("specialization"),
		                    rs.getInt("experience"),
		                    rs.getInt("department_id")
		                );

	                list.add(d);
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return list;
	    }
	 
	 //getbyId
	 
	 public Doctor getById(int id) {

	        Doctor d = null;

	        try {
	            Connection con = DBUtil.makeConnection();

	            PreparedStatement ps = con.prepareStatement(
	                "SELECT * FROM doctor WHERE id=?"
	            );

	            ps.setInt(1, id);

	            ResultSet rs = ps.executeQuery();

	            if (rs.next()) {

	                d = new Doctor(
	                    rs.getInt("id"),
	                    rs.getString("name"),
	                    rs.getString("email"),
	                    rs.getString("phone"),
	                    rs.getString("specialization"),
	                    rs.getInt("experience"),
	                    rs.getInt("department_id")
	                );
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return d;
	    }

	 //update 
	 public int updateDoctor(Doctor d) {

	        int i = 0;

	        try {
	            Connection con = DBUtil.makeConnection();

	            PreparedStatement ps = con.prepareStatement(
	                "UPDATE doctor SET name=?, email=?, phone=?, specialization=?, experience=?, department_id=? WHERE id=?"
	            );

	            ps.setString(1, d.getName());
	            ps.setString(2, d.getEmail());
	            ps.setString(3, d.getPhone());
	            ps.setString(4, d.getSpecialization());
	            ps.setInt(5, d.getExperience());
	            ps.setInt(6, d.getDepartmentId());
	            ps.setInt(7, d.getId());

	            i = ps.executeUpdate();

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return i;
	    }

	 //delete
	 public int deleteDoctor(int id) {

	        int i = 0;

	        try {
	            Connection con = DBUtil.makeConnection();

	            PreparedStatement ps = con.prepareStatement(
	                "DELETE FROM doctor WHERE id=?"
	            );

	            ps.setInt(1, id);

	            i = ps.executeUpdate();

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return i;
	    }
	 
	 //search
	 public List<Doctor> searchDoctors(String keyword) {

	        List<Doctor> list = new ArrayList<>();

	        try {
	            Connection con = DBUtil.makeConnection();

	            PreparedStatement ps = con.prepareStatement(
	                "SELECT * FROM doctor WHERE name LIKE ?"
	            );

	            ps.setString(1, "%" + keyword + "%");

	            ResultSet rs = ps.executeQuery();

	            while (rs.next()) {

	                list.add(new Doctor(
	                    rs.getInt("id"),
	                    rs.getString("name"),
	                    rs.getString("email"),
	                    rs.getString("phone"),
	                    rs.getString("specialization"),
	                    rs.getInt("experience"),
	                    rs.getInt("department_id")
	                ));
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return list;
	    }
	 
	 
	 //paging 
	 public List<Doctor> getDoctorsByPage(int start, int limit) {

	        List<Doctor> list = new ArrayList<>();

	        try {
	            Connection con = DBUtil.makeConnection();

	            PreparedStatement ps = con.prepareStatement(
	                "SELECT * FROM doctor LIMIT ? OFFSET ?"
	            );

	            ps.setInt(1, limit);
	            ps.setInt(2, start);

	            ResultSet rs = ps.executeQuery();

	            while (rs.next()) {

	                list.add(new Doctor(
	                    rs.getInt("id"),
	                    rs.getString("name"),
	                    rs.getString("email"),
	                    rs.getString("phone"),
	                    rs.getString("specialization"),
	                    rs.getInt("experience"),
	                    rs.getInt("department_id")
	                ));
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return list;
	    }
	 
	 //total count
	 
	 public int getTotalDoctors() {

	        int count = 0;

	        try {
	            Connection con = DBUtil.makeConnection();

	            PreparedStatement ps = con.prepareStatement(
	                "SELECT COUNT(*) FROM doctor"
	            );

	            ResultSet rs = ps.executeQuery();

	            if (rs.next()) {
	                count = rs.getInt(1);
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return count;
	    }
	
}
