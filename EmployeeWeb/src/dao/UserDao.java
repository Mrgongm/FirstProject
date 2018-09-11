package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import entity.User;

public class UserDao extends BaseDao {

	public boolean search(User user) {
		boolean flag = false;
		PreparedStatement pstat = null;
		getConnection();
		try {
			String sql = "select * from user where username=? and password=? ";
			pstat = conn.prepareStatement(sql);

			pstat.setString(1, user.getUsername());
			pstat.setString(2, user.getPassword());

			rs = pstat.executeQuery();

			if (rs.next()) {
				flag = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (pstat != null) {
					pstat.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;

	}

	public String getDate(String username) {
		String date = null;
		PreparedStatement pstat = null;
		getConnection();
		try {
			String sql = "select date from user where username=?  ";
			pstat = conn.prepareStatement(sql);

			pstat.setString(1, username);

			rs = pstat.executeQuery();

			while (rs.next()) {
				date = rs.getString("date");
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (pstat != null) {
					pstat.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return date;

	}

	public boolean add(User user) {
		PreparedStatement pstat = null;
		int rs = 0;
		getConnection();
		try {
			String sql = "insert into user(username,password,date) values (?,?,?)";
			pstat = conn.prepareStatement(sql);

			pstat.setString(1, user.getUsername());
			pstat.setString(2, user.getPassword());
			pstat.setString(3, user.getDate());
			rs = pstat.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (pstat != null) {
					pstat.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rs > 0;

	}

	public boolean isNewUser(String username) {
		// TODO Auto-generated method stub
		boolean flag = false;
		PreparedStatement pstat = null;
		getConnection();
		try {
			String sql = "select * from user where username=?";
			pstat = conn.prepareStatement(sql);

			pstat.setString(1, username);

			rs = pstat.executeQuery();

			if (rs.next()) {
				flag = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (pstat != null) {
					pstat.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}

}
