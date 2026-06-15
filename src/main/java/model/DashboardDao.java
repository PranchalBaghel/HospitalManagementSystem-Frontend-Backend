package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Appointment;
import entity.Doctor;

public class DashboardDao {
	
	//total patient
	 public int getTotalPatients() {
	        int count = 0;

	        try {
	            Connection con = DBUtil.makeConnection();
	            PreparedStatement ps =
	                    con.prepareStatement("select count(*) as total from patient");

	            ResultSet rs = ps.executeQuery();

	            if(rs.next()) {
	                count = rs.getInt("total");
	            }

	        } catch(Exception e) {
	            e.printStackTrace();
	        }

	        return count;
	    }

	 //total doctor
	    public int getTotalDoctors() {
	        int count = 0;

	        try {
	            Connection con = DBUtil.makeConnection();

	            PreparedStatement ps =
	                    con.prepareStatement("select count(*) as total from doctor");

	            ResultSet rs = ps.executeQuery();

	            if(rs.next()) {
	                count = rs.getInt("total");
	            }

	        } catch(Exception e) {
	            e.printStackTrace();
	        }

	        return count;
	    }

	    //total appointment
	    public int getTotalAppointments() {
	        int count = 0;

	        try {
	            Connection con = DBUtil.makeConnection();

	            PreparedStatement ps =
	                    con.prepareStatement("select count(*) as total from appointment");

	            ResultSet rs = ps.executeQuery();

	            if(rs.next()) {
	                count = rs.getInt(1);
	            }

	        } catch(Exception e) {
	            e.printStackTrace();
	        }

	        return count;
	    }
	    
	    //total hospital earning
	    public double getTotalRevenue() {

	        double revenue = 0;

	        try {

	            Connection con = DBUtil.makeConnection();

	            PreparedStatement ps = con.prepareStatement(
	                    "select sum(amount) as total_revenue from bill where is_paid = true");

	            ResultSet rs = ps.executeQuery();

	            if (rs.next()) {
	                revenue = rs.getDouble("total_revenue");
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return revenue;
	    }
	    
	    // getRecentAppointments

	    public List<Appointment> getRecentAppointments() {

	        List<Appointment> list = new ArrayList<>();

	        try {
	            Connection con = DBUtil.makeConnection();

	            String sql =
	                "SELECT a.id, a.appointment_date, a.appointment_time, " +
	                "a.status, " +
	                "p.name AS patient_name, " +
	                "d.name AS doctor_name " +
	                "FROM appointment a " +
	                "JOIN patient p ON a.patient_id = p.id " +
	                "JOIN doctor d ON a.doctor_id = d.id " +
	                "ORDER BY a.id DESC LIMIT 5";

	            PreparedStatement ps = con.prepareStatement(sql);
	            ResultSet rs = ps.executeQuery();

	            while (rs.next()) {

	                Appointment a = new Appointment();

	                a.setId(rs.getInt("id"));
	                a.setAppointmentDate(rs.getDate("appointment_date"));
	                a.setAppointmentTime(rs.getTime("appointment_time"));
	                a.setStatus(rs.getString("status")); 

	                a.setPatientName(rs.getString("patient_name"));
	                a.setDoctorName(rs.getString("doctor_name"));

	                list.add(a);
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return list;
	    }
	    
	    //getDoctorWorkload
	    public List<Doctor> getDoctorWorkload() {

	        List<Doctor> list = new ArrayList<>();

	        try {

	            Connection con = DBUtil.makeConnection();

	            String sql =
	                "SELECT d.id, d.name, d.specialization, " +
	                "COUNT(a.id) AS total_appointments " +
	                "FROM doctor d " +
	                "LEFT JOIN appointment a ON d.id = a.doctor_id " +
	                "GROUP BY d.id, d.name, d.specialization " +
	                "ORDER BY total_appointments DESC";

	            PreparedStatement ps = con.prepareStatement(sql);
	            ResultSet rs = ps.executeQuery();

	            while (rs.next()) {

	                Doctor d = new Doctor();

	                d.setId(rs.getInt("id"));
	                d.setName(rs.getString("name"));
	                d.setSpecialization(rs.getString("specialization"));
	                d.setTotalAppointments(rs.getInt("total_appointments"));

	                list.add(d);
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return list;
	    }
	    
	    
	 // Patient Visit History
	    public List<Appointment> getPatientVisitHistory() {

	        List<Appointment> list = new ArrayList<>();

	        try {

	            Connection con = DBUtil.makeConnection();

	            String sql =
	                "SELECT p.name AS patient_name, " +
	                "d.name AS doctor_name, " +
	                "a.appointment_date, " +
	                "a.appointment_time, " +
	                "a.status " +
	                "FROM appointment a " +
	                "INNER JOIN patient p ON a.patient_id = p.id " +
	                "INNER JOIN doctor d ON a.doctor_id = d.id " +
	                "ORDER BY a.appointment_date DESC, a.appointment_time DESC LIMIT 10";

	            PreparedStatement ps = con.prepareStatement(sql);
	            ResultSet rs = ps.executeQuery();

	            while (rs.next()) {

	                Appointment a = new Appointment();

	                a.setPatientName(rs.getString("patient_name"));
	                a.setDoctorName(rs.getString("doctor_name"));
	                a.setAppointmentDate(rs.getDate("appointment_date"));
	                a.setAppointmentTime(rs.getTime("appointment_time"));
	                a.setStatus(rs.getString("status"));

	                list.add(a);
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return list;
	    }
}
