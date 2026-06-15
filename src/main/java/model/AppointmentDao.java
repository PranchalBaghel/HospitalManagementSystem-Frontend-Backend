package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Appointment;

public class AppointmentDao {

   //add
    public int addAppointment(Appointment a) {

        int i = 0;

        try {
            Connection con = DBUtil.makeConnection();

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO appointment(appointment_date, appointment_time, patient_id, doctor_id, status) VALUES (?,?,?,?,?)"
            );

            ps.setDate(1, a.getAppointmentDate());
            ps.setTime(2, a.getAppointmentTime());
            ps.setInt(3, a.getPatientId());
            ps.setInt(4, a.getDoctorId());
            ps.setString(5, a.getStatus());

            i = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return i;
    }

   //read
    public List<Appointment> getAllAppointments() {

        List<Appointment> list = new ArrayList<>();

        try {
            Connection con = DBUtil.makeConnection();

            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM appointment"
            );

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Appointment a = new Appointment();

                a.setId(rs.getInt("id"));
                a.setAppointmentDate(rs.getDate("appointment_date"));
                a.setAppointmentTime(rs.getTime("appointment_time"));
                a.setPatientId(rs.getInt("patient_id"));
                a.setDoctorId(rs.getInt("doctor_id"));
                a.setStatus(rs.getString("status"));

                list.add(a);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

   //getbyId
    public Appointment getById(int id) {

        Appointment a = null;

        try {
            Connection con = DBUtil.makeConnection();

            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM appointment WHERE id=?"
            );

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                a = new Appointment();

                a.setId(rs.getInt("id"));
                a.setAppointmentDate(rs.getDate("appointment_date"));
                a.setAppointmentTime(rs.getTime("appointment_time"));
                a.setPatientId(rs.getInt("patient_id"));
                a.setDoctorId(rs.getInt("doctor_id"));
                a.setStatus(rs.getString("status"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return a;
    }

   //update status
    public int updateStatus(int id, String status) {

        int i = 0;

        try {
            Connection con = DBUtil.makeConnection();

            PreparedStatement ps = con.prepareStatement(
                "UPDATE appointment SET status=? WHERE id=?"
            );

            ps.setString(1, status);
            ps.setInt(2, id);

            i = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return i;
    }

    //delete
    public int deleteAppointment(int id) {

        int i = 0;

        try {
            Connection con = DBUtil.makeConnection();

            PreparedStatement ps = con.prepareStatement(
                "DELETE FROM appointment WHERE id=?"
            );

            ps.setInt(1, id);

            i = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return i;
    }
    //paging
    public List<Appointment> getAppointmentsByPage(int start, int total) {

        List<Appointment> list = new ArrayList<>();

        try {

            Connection con = DBUtil.makeConnection();

            String sql =
                "SELECT a.id, " +
                "a.patient_id, " +
                "a.doctor_id, " +
                "a.appointment_date, " +
                "a.appointment_time, " +
                "a.status, " +
                "p.name AS patient_name, " +
                "d.name AS doctor_name " +
                "FROM appointment a " +
                "JOIN patient p ON a.patient_id = p.id " +
                "JOIN doctor d ON a.doctor_id = d.id " +
                "ORDER BY a.id " +
                "LIMIT ? OFFSET ?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, total);
            ps.setInt(2, start);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Appointment a = new Appointment();

                a.setId(rs.getInt("id"));
                a.setPatientId(rs.getInt("patient_id"));
                a.setDoctorId(rs.getInt("doctor_id"));
                a.setAppointmentDate(rs.getDate("appointment_date"));
                a.setAppointmentTime(rs.getTime("appointment_time"));
                a.setStatus(rs.getString("status"));

                // JOIN se aayi values
                a.setPatientName(rs.getString("patient_name"));
                a.setDoctorName(rs.getString("doctor_name"));

                list.add(a);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    
    //getTotalAppointments
    public int getTotalAppointments() {

        int total = 0;

        try {
            Connection con = DBUtil.makeConnection();

            PreparedStatement ps = con.prepareStatement(
                "SELECT COUNT(*) FROM appointment"
            );

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                total = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return total;
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
                "ORDER BY a.id ";

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
    
    //search
    
    public List<Appointment> searchAppointments(String keyword) {

        List<Appointment> list = new ArrayList<>();

        try {

            Connection con = DBUtil.makeConnection();

            String sql =
                "SELECT a.id, a.patient_id, a.doctor_id, " +
                "a.appointment_date, a.appointment_time, a.status, " +
                "p.name AS patient_name, " +
                "d.name AS doctor_name " +
                "FROM appointment a " +
                "JOIN patient p ON a.patient_id = p.id " +
                "JOIN doctor d ON a.doctor_id = d.id " +
                "WHERE a.id = ? " +
                "OR p.name LIKE ? " +
                "OR d.name LIKE ? " +
                "OR a.status LIKE ?";

            PreparedStatement ps = con.prepareStatement(sql);

            int id = 0;

            try {
                id = Integer.parseInt(keyword);
            } catch (NumberFormatException e) {
                // keyword number nahi hai
            }

            String value = "%" + keyword + "%";

            ps.setInt(1, id);
            ps.setString(2, value);
            ps.setString(3, value);
            ps.setString(4, value);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Appointment a = new Appointment();

                a.setId(rs.getInt("id"));
                a.setPatientId(rs.getInt("patient_id"));
                a.setDoctorId(rs.getInt("doctor_id"));
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
}