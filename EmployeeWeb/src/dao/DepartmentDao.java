package dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Department;

public class DepartmentDao extends BaseDao {

	public List<Department> searchByConnection(Department connection, int start, int eachPage) {
		List<Department> list = new ArrayList<Department>();
		String name = connection.getName();
		int count = connection.getEmpCount();
		try {
			getConnection();
			// 4、建立statement sql语句执行器
			stat = conn.createStatement();

			String sql = "select * from department";
			String condition = "";
			if (name != null && !name.equals("")) {
				condition = condition + " name='" + name + "' And ";
			}
			if (count != -1) {
				condition = condition + " emp_count=" + count + " And ";
			}
			if (!condition.equals("")) {
				condition = condition.substring(0, condition.length() - 4);

				sql = sql + " where " + condition;
			}
			sql = sql + " limit " + start + "," + eachPage;
			rs = stat.executeQuery(sql);
			// 6、对结果集进行处理

			while (rs.next()) {
				Department dep = new Department();
				dep.setId(rs.getInt("id"));
				dep.setName(rs.getString("name"));
				dep.setEmpCount(rs.getInt("emp_count"));

				list.add(dep);
			}
			// 7、 关闭
			closeAll(conn, stat, rs);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}

	public int searchCount(Department connection) {
		String name = connection.getName();
		int count = connection.getEmpCount();

		try {
			getConnection();
			stat = conn.createStatement();

			String sql = "select count(*) as count from department";
			String condition = "";
			if (name != null && !name.equals("")) {
				condition = condition + " name='" + name + "' And ";
			}
			if (count != -1) {
				condition = condition + " emp_count=" + count + " And ";
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

	public List<Department> search(String id) {// 取数据
		List<Department> list = new ArrayList<Department>();

		try {

			getConnection();// 4、建立stat sql语句执行器
			stat = conn.createStatement();

			// 5、执行sql语句并得到结果
			rs = stat.executeQuery("select * from department where id in (" + id + ")");
			// 6、对结果集进行处理

			while (rs.next()) {
				Department dep = new Department();
				dep.setId(rs.getInt("id"));
				dep.setName(rs.getString("name"));
				dep.setEmpCount(rs.getInt("emp_count"));
				list.add(dep);
			}
			// 7、 关闭
			closeAll(conn, stat, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}

	public List<Department> read() {// 取数据
		List<Department> list = new ArrayList<Department>();
		// io流获取数据
		// try {
		// FileInputStream fileInputStream = new
		// FileInputStream("G:/iocunchu/deps.txt");
		// ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		//
		// list = (List<Department>) objectInputStream.readObject();
		//
		// objectInputStream.close();
		// } catch (FileNotFoundException e) {
		// //
		// e.printStackTrace();
		// } catch (IOException e) {
		//
		// e.printStackTrace();
		// } catch (ClassNotFoundException e) {
		//
		// e.printStackTrace();
		// }

		try {
			getConnection();
			// 4、建立statement sql语句执行器
			Statement stat = conn.createStatement();

			// 5、执行sql语句并得到结果
			rs = stat.executeQuery("select * from department");
			// 6、对结果集进行处理

			while (rs.next()) {
				Department dep = new Department();
				dep.setId(rs.getInt("id"));
				dep.setName(rs.getString("name"));
				dep.setEmpCount(rs.getInt("emp_count"));
				list.add(dep);
			}
			// 7、 关闭

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}

		return list;

	}

	public String getDepName(int id) {// 取数据
		String depName = null;
		try {
			getConnection();
			// 4、建立statement sql语句执行器
			Statement stat = conn.createStatement();

			// 5、执行sql语句并得到结果
			rs = stat.executeQuery("select name from department where id=" + id + "");
			// 6、对结果集进行处理

			while (rs.next()) {
				depName = rs.getString("name");
			}
			// 7、 关闭

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}

		return depName;

	}

	public void update(Department dep) {// 更新数据
		try {
			getConnection();
			Statement stat = conn.createStatement();

			String sql = "update department set name='" + dep.getName() + "' where id=" + dep.getId() + "";

			stat.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void update2(List<Department> list) {
		try {

			getConnection();

			stat = conn.createStatement();
			for (int i = 0; i < list.size(); i++) {
				Department dep = new Department();
				dep = list.get(i);

				String sql = "update department set name='" + dep.getName() + "' where id=" + dep.getId() + "";
				// ,d_id=" + dep.getDep().getId() + "
				stat.executeUpdate(sql);
			}
			closeAll(conn, stat, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean add(Department dep) {// 存数据
		boolean falg = false;
		try {
			getConnection();
			stat = conn.createStatement();

			String sql = " insert into department(name) values('" + dep.getName() + "')";

			int rs = stat.executeUpdate(sql);

			if (rs > 0) {
				falg = true;
			}

			closeAll(conn, stat, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return falg;

	}

	public void deleteBatch(String ints) {// 删除
		try {
			getConnection();
			stat = conn.createStatement();
			conn.setAutoCommit(false);
			String sql2 = "update employee set d_id=null where d_id in (" + ints + ")";
			String sql = " delete from department where id in (" + ints + ")";
			String sql3 = " delete from r_dep_pro where d_id in (" + ints + ")";
			stat.executeUpdate(sql);
			stat.executeUpdate(sql2);
			stat.executeUpdate(sql3);
			conn.commit();
			closeAll(conn, stat, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
