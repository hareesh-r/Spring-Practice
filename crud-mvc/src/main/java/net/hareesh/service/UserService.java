package net.hareesh.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.hareesh.model.User;

public class UserService {
	public final String selectquery = "select id,name,email,mobile,dob,city,gender from user";
	public final String selectId = "select id,name,email,mobile,dob,city,gender from user where id=?";
	public final String insertquery = "insert into user (name,email,mobile,dob,city,gender) values (?,?,?,?,?,?)";
	public final String updatequery = "update user set name=?,email=?,mobile=?,dob=?,city=?,gender=? where id=?";
	public final String deletequery = "delete from user where id=?";

	public List<User> getUser() {
		List<User> users = new ArrayList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}

		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeemgmt", "root",
				"passwordtoor"); PreparedStatement ps = con.prepareStatement(selectquery);) {
			ResultSet rs = ps.executeQuery(selectquery);

			while (rs.next()) {
				User tempuser = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7));
				users.add(tempuser);

			}

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return (List<User>) users;
	}

	public List<User> getUser(int id) {
		List<User> users = new ArrayList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(id);
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeemgmt", "root",
				"passwordtoor"); PreparedStatement ps = con.prepareStatement(selectId);) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				User tempuser = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7));
				users.add(tempuser);
				System.out.println(tempuser.toString());

			}

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return (List<User>) users;
	}

	// Tested
	public void addUser(User user) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}

		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeemgmt", "root",
				"passwordtoor"); PreparedStatement ps = con.prepareStatement(insertquery)) {
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getMobile());
			ps.setString(4, user.getDob());
			ps.setString(5, user.getCity());
			ps.setString(6, user.getGender());
			ps.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateUser(User user) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}

		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeemgmt", "root",
				"passwordtoor"); PreparedStatement ps = con.prepareStatement(updatequery)) {
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getMobile());
			ps.setString(4, user.getDob());
			ps.setString(5, user.getCity());
			ps.setString(6, user.getGender());
			ps.setInt(7, (int) user.getId());
			ps.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void removeUser(int id) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}

		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeemgmt", "root",
				"passwordtoor"); PreparedStatement ps = con.prepareStatement(deletequery)) {
			ps.setInt(1, id);
			ps.execute();

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
