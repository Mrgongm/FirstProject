package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Department;
import entity.Employee;
import entity.Project;
import entity.Score;

public class ScoreDao extends BaseDao {

	public List<Score> searchByConnection(Score connection, int start, int eachPage) {
		List<Score> list = new ArrayList<Score>();
		String name = connection.getEmp().getName();
		String proSelect = connection.getPro().getName();
		String depSelect = connection.getDep().getName();
		String greSelect = connection.getGrade();
		try {
			getConnection();
			stat = conn.createStatement();

			String sql = "SELECT s.id, p.id as p_id, e.id as e_id, d.`name` as d_name,e.`name`AS e_name,sex,age, p.`name`AS p_name,`value`,grade  "
					+ "FROM department AS d JOIN employee AS e  ON e.d_id=d.id "
					+ " JOIN r_dep_pro AS r  ON d.id = r.d_id  JOIN project AS p ON r.p_id=p.id "
					+ "  JOIN score AS s  ON e.id= s.e_id and p.id=s.p_id";

			String condition = "";
			if (name != null && !name.equals("")) {
				condition = condition + " e.name='" + name + "' And ";
			}
			if (proSelect != null && !proSelect.equals("")) {
				condition = condition + " p.name='" + proSelect + "' And ";
			}

			if (depSelect != null && !depSelect.equals("")) {
				condition = condition + " d.name='" + depSelect + "' And ";
			}
			if (greSelect != null && !greSelect.equals("")) {
				condition = condition + " grade= '" + greSelect + "' And ";
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
				Department dep = new Department();
				Project pro = new Project();
				Score sco = new Score();
				sco.setId(rs.getInt("id"));
				sco.seteId(rs.getInt("e_id"));
				sco.setpId(rs.getInt("p_id"));
				emp.setName(rs.getString("e_name"));
				dep.setName(rs.getString("d_name"));
				pro.setName(rs.getString("p_name"));
				sco.setDep(dep);
				sco.setEmp(emp);
				sco.setPro(pro);
				sco.setValue((Integer) rs.getObject("value"));
				sco.setGrade(rs.getString("grade"));
				list.add(sco);
			}
			// 7、 关闭
			closeAll(conn, stat, rs);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}

	public int searchCount(Score connection) {
		int count = 0;
		List<Score> list = new ArrayList<Score>();
		String name = connection.getEmp().getName();
		String proSelect = connection.getPro().getName();
		String depSelect = connection.getDep().getName();
		String greSelect = connection.getGrade();
		try {
			getConnection();
			stat = conn.createStatement();

			String sql = "SELECT count(*) as count FROM department AS d JOIN employee AS e  ON e.d_id=d.id "
					+ " JOIN r_dep_pro AS r  ON d.id = r.d_id  JOIN project AS p ON r.p_id=p.id "
					+ "  JOIN score AS s  ON e.id= s.e_id and p.id=s.p_id";

			String condition = "";
			if (name != null && !name.equals("")) {
				condition = condition + " e.name='" + name + "' And ";
			}
			if (proSelect != null && !proSelect.equals("")) {
				condition = condition + " p.name='" + proSelect + "' And ";
			}

			if (depSelect != null && !depSelect.equals("")) {
				condition = condition + " d.name='" + depSelect + "' And ";
			}
			if (greSelect != null && !greSelect.equals("")) {
				condition = condition + " grade= '" + greSelect + "' And ";
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

	public List<Score> read() {// 取数据
		List<Score> list = new ArrayList<Score>();

		try {

			getConnection();// 4、建立stat sql语句执行器
			stat = conn.createStatement();

			String sql = "SELECT  p.id as p_id, e.id, d.`name` as d_name,e.`name`AS e_name,sex,age, p.`name`AS p_name,`value`,grade  "
					+ "FROM department AS d JOIN employee AS e  ON e.d_id=d.id "
					+ " JOIN r_dep_pro AS r  ON d.id = r.d_id JOIN project AS p ON r.p_id=p.id "
					+ "  JOIN score AS s  ON e.id= s.e_id and p.id=s.p_id  ";

			// String sql = "SELECT * from v_allTables ";
			// 5、执行sql语句并得到结果
			rs = stat.executeQuery(sql);
			// 6、对结果集进行处理

			while (rs.next()) {
				Score sco = new Score();
				sco.setId(rs.getInt("id"));
				sco.seteId(rs.getInt("id"));
				sco.setpId(rs.getInt("p_id"));
				Employee emp = new Employee();
				Department dep = new Department();
				Project pro = new Project();
				dep.setName(rs.getString("d_name"));
				sco.setDep(dep);
				emp.setName(rs.getString("e_name"));
				sco.setEmp(emp);
				pro.setName(rs.getString("p_name"));
				sco.setPro(pro);
				sco.setValue((Integer) rs.getObject("value"));
				sco.setGrade(rs.getString("grade"));
				list.add(sco);
			}
			// 7、 关闭
			closeAll(conn, stat, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}

	public List<Score> readNowTable() {
		// TODO Auto-generated method stub
		List<Score> list = new ArrayList<Score>();
		getConnection();
		try {

			getConnection();// 4、建立stat sql语句执行器
			stat = conn.createStatement();

			// 5、执行sql语句并得到结果
			rs = stat.executeQuery("select * from score as s left JOIN department as d on s.d_id = d.id");
			// 6、对结果集进行处理

			while (rs.next()) {
				Score sco = new Score();
				sco.setId(rs.getInt("id"));

				Department dep = new Department();
				dep.setId(rs.getInt("d_id"));
				dep.setName(rs.getString("dName"));

				sco.setDep(dep);
				list.add(sco);
			}
			// 7、 关闭
			closeAll(conn, stat, rs);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	public void save(List<Score> score) {

		try {

			getConnection();

			stat = conn.createStatement();
			for (Score sc : score) {

				String sql = "update score set value=" + sc.getValue() + " where e_id=" + sc.geteId() + " and  p_id="
						+ sc.getpId() + "";
				stat.executeUpdate(sql);
			}
			closeAll(conn, stat, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void update2(List<Score> list) {
		// TODO Auto-generated method stub
		try {

			getConnection();

			stat = conn.createStatement();
			for (Score sc : list) {

				String sql = "update score set value=" + sc.getValue() + " where id=" + sc.getId() + "";
				stat.executeUpdate(sql);
			}
			closeAll(conn, stat, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
