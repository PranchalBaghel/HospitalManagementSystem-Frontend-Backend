package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Department;

public class DepartmentDao {

    //add
    public int addDepartment(Department d) {

        int i = 0;

        try {
            Connection con = DBUtil.makeConnection();

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO department(name, description) VALUES(?,?)"
            );

            ps.setString(1, d.getName());
            ps.setString(2, d.getDescription());

            i = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return i;
    }

    //read
    public List<Department> getAllDepartments() {

        List<Department> list = new ArrayList<>();

        try {
            Connection con = DBUtil.makeConnection();

            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM department"
            );

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Department d = new Department(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("description")
                );

                list.add(d);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    //getbyId
    public Department getById(int id) {

        Department d = null;

        try {
            Connection con = DBUtil.makeConnection();

            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM department WHERE id=?"
            );

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                d = new Department(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("description")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return d;
    }

    //update
    public int updateDepartment(Department d) {

        int i = 0;

        try {
            Connection con = DBUtil.makeConnection();

            PreparedStatement ps = con.prepareStatement(
                "UPDATE department SET name=?, description=? WHERE id=?"
            );

            ps.setString(1, d.getName());
            ps.setString(2, d.getDescription());
            ps.setInt(3, d.getId());

            i = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return i;
    }

    //delete
    public int deleteDepartment(int id) {

        int i = 0;

        try {
            Connection con = DBUtil.makeConnection();

            PreparedStatement ps = con.prepareStatement(
                "DELETE FROM department WHERE id=?"
            );

            ps.setInt(1, id);

            i = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return i;
    }

    //search
    public List<Department> searchDepartments(String keyword) {

        List<Department> list = new ArrayList<>();

        try {
            Connection con = DBUtil.makeConnection();

            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM department WHERE name LIKE ?"
            );

            ps.setString(1, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                list.add(new Department(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("description")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}