package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Project;

public class ProjectDao extends BaseDao {

	public List<Project> searchByConnection(Project connection, int start, int eachPage) {
		List<Project> list = new ArrayList<Project>();
		String name = connection.getName();
		try {
			getConnection();
			// 4、建立statement sql语句执行器
			stat = conn.createStatement();

			String sql = "select * from project";
			String condition = "";
			if (name != null && !name.equals("")) {
				condition = condition + " name='" + name + "' And ";
			}
			if (!condition.equals("")) {
				condition = condition.substring(0, condition.length() - 4);

				sql = sql + " where " + condition;
			}
			sql = sql + " limit " + start + "," + eachPage;
			rs = stat.executeQuery(sql);
			// 6、对结果集进行处理

			while (rs.next()) {
				Project pro = new Project();
				pro.setId(rs.getInt("id"));
				pro.setName(rs.getString("name"));

				list.add(pro);
			}
			// 7、 关闭
			closeAll(conn, stat, rs);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}

	public int searchCount(Project connection) {
		String name = connection.getName();
		int count = 0;
		try {
			getConnection();
			stat = conn.createStatement();

			String sql = "select count(*) as count from project";
			String condition = "";
			if (name != null && !name.equals("")) {
				condition = condition + " name='" + name + "' And ";
			}

			if (!condition.equals("")) {
				condition = condition.substring(0, condition.length() - 4);

				sql = sql + " where " + condition;
			}
			rs = stat.executeQuery(sql);
			// 6、对结果集进行处理

			while (rs.next()) {
				count = rs.getInt("count");
			}
			// 7、 关闭
			closeAll(conn, stat, rs);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return count;

	}

	public List<Project> search(String id) {// 取数据
		List<Project> list = new ArrayList<Project>();

		try {

			getConnection();// 4、建立stat sql语句执行器
			stat = conn.createStatement();

			// 5、执行sql语句并得到结果
			rs = stat.executeQuery("select * from project where id in (" + id + ")");
			// 6、对结果集进行处理

			while (rs.next()) {
				Project pro = new Project();
				pro.setId(rs.getInt("id"));
				pro.setName(rs.getString("name"));

				list.add(pro);
			}
			// 7、 关闭
			closeAll(conn, stat, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}

	public List<Project> read() {// 取数据
		List<Project> list = new ArrayList<Project>();
		try {

			getConnection();// 4、建立statement sql语句执行器
			stat = conn.createStatement();

			// 5、执行sql语句并得到结果
			rs = stat.executeQuery("select* from project ");
			// 6、对结果集进行处理

			while (rs.next()) {
				Project pro = new Project();
				pro.setId(rs.getInt("id"));
				pro.setName(rs.getString("name"));

				list.add(pro);
			}
			// 7、 关闭
			closeAll(conn, stat, rs);

		} catch (SQLException e) {
			//
			e.printStackTrace();
		}

		return list;

	}

	public boolean add(Project pro) {// 存数据
		boolean falg = false;
		try {
			getConnection();
			stat = conn.createStatement();

			String sql = " insert into project(name) values('" + pro.getName() + "')";

			int resultSet = stat.executeUpdate(sql);

			if (resultSet > 0) {
				falg = true;
			}
			closeAll(conn, stat, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return falg;

	}

	public void update(Project pro) {
		try {

			getConnection();

			stat = conn.createStatement();

			String sql = "update project set name='" + pro.getName() + "' where id=" + pro.getId() + "";

			stat.executeUpdate(sql);
			closeAll(conn, stat, null);
		} catch (SQLException e) {
			//
			e.printStackTrace();
		}
	}

	public void update2(List<Project> list) {
		try {

			getConnection();

			stat = conn.createStatement();
			for (int i = 0; i < list.size(); i++) {
				Project pro = new Project();
				pro = list.get(i);

				String sql = "update project set name='" + pro.getName() + "' where id=" + pro.getId() + "";
				// ,d_id=" + pro.getPro().getId() + "
				stat.executeUpdate(sql);
			}
			closeAll(conn, stat, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteBatch(String ints) {
		try {
			getConnection();
			stat = conn.createStatement();

			String sql = " delete from project where id in (" + ints + ")";

			stat.executeUpdate(sql);

			closeAll(conn, stat, null);
		} catch (SQLException e) {
			//
			e.printStackTrace();
		}
	}

	public int getProId(String proName) {
		// TODO Auto-generated method stub
		int proId = 0;
		try {

			getConnection();// 4、建立statement sql语句执行器
			stat = conn.createStatement();

			// 5、执行sql语句并得到结果
			rs = stat.executeQuery("select id from project where name='" + proName + "'");
			// 6、对结果集进行处理

			while (rs.next()) {
				proId = rs.getInt("id");
			}
			// 7、 关闭
			closeAll(conn, stat, rs);

		} catch (SQLException e) {
			//
			e.printStackTrace();
		}
		return proId;
	}
}
