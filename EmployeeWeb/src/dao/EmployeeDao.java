package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Department;
import entity.Employee;

public class EmployeeDao extends BaseDao {

	public List<Employee> searchByConnection(Employee connection, int start, int eachPage) {
		List<Employee> list = new ArrayList<Employee>();
		String name = connection.getName();
		String sex = connection.getSex();
		String depSelect = connection.getDep().getName();
		int depSelecyIndex = connection.getDep().getId();
		int age = -1;
		age = connection.getAge();
		try {
			getConnection();
			stat = conn.createStatement();

			String sql = "select e.*,d.name ,emp_count from employee as e left JOIN department as d on e.d_id = d.id";
			String condition = "";
			if (name != null && !name.equals("")) {
				condition = condition + " e.name='" + name + "' And ";
			}
			if (sex != null && !sex.equals("")) {
				condition = condition + " sex='" + sex + "' And ";
			}
			if (age != -1) {
				condition = condition + " age=" + age + " And ";
			}

			if (depSelect != null && !depSelect.equals("")) {
				condition = condition + " d.name='" + depSelect + "' And ";
			}
			if (depSelecyIndex == -1) {// -1则无部门
				condition = condition + " d_id=0 and";
			}
			if (!condition.equals("")) {
				condition = condition.substring(0, condition.length() - 4);

				sql = sql + " where " + condition;
			}
			sql = sql + " limit " + start + "," + eachPage;
			rs = stat.executeQuery(sql);
			// 6、对结果集进行处理

			while (rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setSex(rs.getString("sex"));
				emp.setAge(rs.getInt("age"));
				emp.setImage(rs.getString("image"));
				Department dep = new Department();
				dep.setName(rs.getString("d.name"));
				emp.setDep(dep);
				list.add(emp);
			}
			// 7、 关闭
			closeAll(conn, stat, rs);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}

	public int searchCount(Employee connection) {
		String name = connection.getName();
		String sex = connection.getSex();
		String depSelect = connection.getDep().getName();
		int depSelecyIndex = connection.getDep().getId();
		int age = -1;
		age = connection.getAge();
		int count = 0;
		try {
			getConnection();
			stat = conn.createStatement();

			String sql = "select count(*) as count  from employee as e left JOIN department as d on e.d_id = d.id";
			String condition = "";
			if (name != null && !name.equals("")) {
				condition = condition + " e.name='" + name + "' And ";
			}
			if (sex != null && !sex.equals("")) {
				condition = condition + " sex='" + sex + "' And ";
			}
			if (age != -1) {
				condition = condition + " age=" + age + " And ";
			}

			if (depSelect != null && !depSelect.equals("")) {
				condition = condition + " d.name='" + depSelect + "' And ";
			}
			if (depSelecyIndex == -1) {// -1则无部门
				condition = condition + " d_id=0 and";
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

	public List<Employee> read() {// 取数据
		List<Employee> list = new ArrayList<Employee>();

		try {

			getConnection();// 4、建立stat sql语句执行器
			stat = conn.createStatement();

			// 5、执行sql语句并得到结果
			rs = stat.executeQuery(
					"select e.*,d.name as dName, emp_count from employee as e left JOIN department as d on e.d_id = d.id");
			// 6、对结果集进行处理

			while (rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setSex(rs.getString("sex"));
				emp.setAge(rs.getInt("age"));
				Department dep = new Department();
				dep.setId(rs.getInt("d_id"));
				dep.setName(rs.getString("dName"));
				dep.setEmpCount(rs.getInt("emp_count"));
				emp.setDep(dep);
				list.add(emp);
			}
			// 7、 关闭
			closeAll(conn, stat, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}

	public List<Employee> readPage(int start, int eachPage) {// 取数据
		List<Employee> list = new ArrayList<Employee>();

		try {

			getConnection();// 4、建立stat sql语句执行器
			stat = conn.createStatement();

			// 5、执行sql语句并得到结果
			rs = stat.executeQuery(
					"select e.*,d.name as dName, emp_count from employee as e left JOIN department as d on e.d_id = d.id limit "
							+ start + "," + eachPage + "");
			// 6、对结果集进行处理

			while (rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setSex(rs.getString("sex"));
				emp.setAge(rs.getInt("age"));
				Department dep = new Department();
				dep.setId(rs.getInt("d_id"));
				dep.setName(rs.getString("dName"));
				dep.setEmpCount(rs.getInt("emp_count"));
				emp.setDep(dep);
				list.add(emp);
			}
			// 7、 关闭
			closeAll(conn, stat, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}

	public int getCount() {// 取数据
		int count = 0;
		try {

			getConnection();// 4、建立stat sql语句执行器
			stat = conn.createStatement();

			// 5、执行sql语句并得到结果
			rs = stat.executeQuery("select count(*) as count from employee");
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

	public int getDepId(String depName) {// 取数据
		int depId = 0;
		try {

			getConnection();// 4、建立stat sql语句执行器
			stat = conn.createStatement();

			// 5、执行sql语句并得到结果
			rs = stat.executeQuery("select id  from department where name = '" + depName + "' ");
			// 6、对结果集进行处理

			while (rs.next()) {
				depId = rs.getInt("id");
			}
			// 7、 关闭
			closeAll(conn, stat, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return depId;

	}

	public List<Employee> search(String id) {// 取数据
		List<Employee> list = new ArrayList<Employee>();

		try {

			getConnection();// 4、建立stat sql语句执行器
			stat = conn.createStatement();

			// 5、执行sql语句并得到结果
			rs = stat.executeQuery(
					"select e.*,d.name as dName, emp_count from employee as e left JOIN department as d on e.d_id = d.id where e.id in ("
							+ id + ")  ");
			// 6、对结果集进行处理

			while (rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setSex(rs.getString("sex"));
				emp.setAge(rs.getInt("age"));
				Department dep = new Department();
				dep.setId(rs.getInt("d_id"));
				dep.setName(rs.getString("dName"));
				dep.setEmpCount(rs.getInt("emp_count"));
				emp.setDep(dep);
				list.add(emp);
			}
			// 7、 关闭
			closeAll(conn, stat, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}

	public void update(Employee emp) {
		try {

			getConnection();

			stat = conn.createStatement();

			String sql = "update employee set name='" + emp.getName() + "',sex='" + emp.getSex() + "',age="
					+ emp.getAge() + ",d_id=" + emp.getDep().getId() + " where id=" + emp.getId() + "";
			// ,d_id=" + emp.getDep().getId() + "
			stat.executeUpdate(sql);
			closeAll(conn, stat, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void update2(List<Employee> list) {
		try {

			getConnection();

			stat = conn.createStatement();
			for (int i = 0; i < list.size(); i++) {
				Employee emp = new Employee();
				emp = list.get(i);

				String sql = "update employee set name='" + emp.getName() + "',sex='" + emp.getSex() + "',age="
						+ emp.getAge() + ",d_id=" + emp.getDep().getId() + " where id=" + emp.getId() + "";
				// ,d_id=" + emp.getDep().getId() + "
				stat.executeUpdate(sql);
			}
			closeAll(conn, stat, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean add(Employee emp) {// 存数据
		boolean falg = false;
		try {
			getConnection();
			stat = conn.createStatement();

			String sql = " insert into employee(name,sex,age,d_id,image) values('" + emp.getName() + "','"
					+ emp.getSex() + "'," + emp.getAge() + "," + emp.getDep().getId() + ",'" + emp.getImage() + "')";

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

	public void deleteBatch(String ints) {
		try {
			getConnection();
			stat = conn.createStatement();
			conn.setAutoCommit(false);
			String sql = " delete from employee where id in (" + ints + ")";
			String sql2 = "delete from score where e_id in (" + ints + ")";
			stat.executeUpdate(sql);
			stat.executeUpdate(sql2);

			conn.commit();
			closeAll(conn, stat, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//
	//
	// 批量删除代替下面的单条删除
	//
	// public void delete(Employee emp) {
	// try {
	//
	// getConnection();
	// Statement stat = conn.createStatement();
	//
	// String sql = " delete from employee where id=" + emp.getId() + "";
	//
	// stat.executeUpdate(sql);
	//
	// stat.close();
	// conn.close();
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

	public List<Employee> readNowTable() {
		// TODO Auto-generated method stub
		List<Employee> list = new ArrayList<Employee>();
		getConnection();
		try {

			getConnection();// 4、建立stat sql语句执行器
			stat = conn.createStatement();

			// 5、执行sql语句并得到结果
			rs = stat.executeQuery(
					"select e.*,d.name as dName, emp_count from employee as e left JOIN department as d on e.d_id = d.id");
			// 6、对结果集进行处理

			while (rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setSex(rs.getString("sex"));
				emp.setAge(rs.getInt("age"));
				Department dep = new Department();
				dep.setId(rs.getInt("d_id"));
				dep.setName(rs.getString("dName"));
				dep.setEmpCount(rs.getInt("emp_count"));
				emp.setDep(dep);
				list.add(emp);
			}
			// 7、 关闭
			closeAll(conn, stat, rs);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	public int getEmpDep(String empName) {
		int dId = 0;
		getConnection();// 4、建立stat sql语句执行器
		try {

			getConnection();// 4、建立stat sql语句执行器
			stat = conn.createStatement();

			// 5、执行sql语句并得到结果
			String sql = "select d_id from employee where name = '" + empName + "'";
			rs = stat.executeQuery(sql);
			// 6、对结果集进行处理

			while (rs.next()) {
				dId = rs.getInt("d_id");

			}
			// 7、 关闭
			closeAll(conn, stat, rs);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dId;
	}

	public String getDepName(int id) {
		String dName = "";
		getConnection();// 4、建立stat sql语句执行器
		try {

			getConnection();// 4、建立stat sql语句执行器
			stat = conn.createStatement();

			// 5、执行sql语句并得到结果
			String sql = "select name from department where id = '" + id + "'";
			rs = stat.executeQuery(sql);
			// 6、对结果集进行处理

			while (rs.next()) {
				dName = rs.getString("name");

			}
			// 7、 关闭
			closeAll(conn, stat, rs);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dName;
	}

	public void setEmpPro(int empId, int proId) {
		// TODO Auto-generated method stub

		getConnection();// 4、建立stat sql语句执行器
		try {

			getConnection();// 4、建立stat sql语句执行器
			stat = conn.createStatement();

			// 5、执行sql语句并得到结果
			String sql = "insert into score( e_id, p_id ) values ( " + empId + "," + proId + ") ";
			stat.executeUpdate(sql);
			// 6、对结果集进行处理

			// 7、 关闭
			closeAll(conn, stat, null);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public int getEmpId(String empName) {
		// TODO Auto-generated method stub
		int empId = 0;
		try {

			getConnection();// 4、建立statement sql语句执行器
			stat = conn.createStatement();

			// 5、执行sql语句并得到结果
			rs = stat.executeQuery("select id from employee where name='" + empName + "'");
			// 6、对结果集进行处理

			while (rs.next()) {
				empId = rs.getInt("id");
			}
			// 7、 关闭
			closeAll(conn, stat, rs);

		} catch (SQLException e) {
			//
			e.printStackTrace();
		}
		return empId;
	}

}
