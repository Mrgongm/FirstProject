package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.ProMenege;

public class ProMenegeDao extends BaseDao {

	public List<ProMenege> readpro() {// ȡ����
		List<ProMenege> list = new ArrayList<ProMenege>();
		try {

			getConnection();// 4������stat sql���ִ����
			stat = conn.createStatement();

			// 5��ִ��sql��䲢�õ����
			rs = stat.executeQuery("select* from project ");
			// 6���Խ�������д���

			while (rs.next()) {
				ProMenege proMen = new ProMenege();
				proMen.setId(rs.getInt("id"));
				proMen.setpName(rs.getString("name"));
				list.add(proMen);
			}
			// 7�� �ر�
			closeAll(conn, stat, rs);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}

	public List<ProMenege> readDepPro(int id) {// ȡ�����Ŷ�Ӧ����Ŀ
		List<ProMenege> list = new ArrayList<ProMenege>();
		try {

			getConnection();// 4������stat sql���ִ����
			stat = conn.createStatement();

			// 5��ִ��sql��䲢�õ����
			String sql = "select p.name ,d.id as d_id,p.id  as p_id  from (department as d join r_dep_pro as r on d.id = r.d_id"
					+ " join project as p on r.p_id = p.id) where d.id=" + id + "";

			rs = stat.executeQuery(sql);
			// 6���Խ�������д���

			while (rs.next()) {
				ProMenege proMen = new ProMenege();
				proMen.setId(rs.getInt("d_id"));
				proMen.setpId(rs.getInt("p_id"));
				proMen.setpName(rs.getString("p.name"));

				list.add(proMen);
			}
			// 7�� �ر�
			closeAll(conn, stat, rs);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}

	public List<ProMenege> readDepPro(int id, int start, int eachPage) {// ȡ�����Ŷ�Ӧ����Ŀ
		List<ProMenege> list = new ArrayList<ProMenege>();
		try {

			getConnection();// 4������stat sql���ִ����
			stat = conn.createStatement();

			// 5��ִ��sql��䲢�õ����
			String sql = "select p.name ,d.id as d_id,p.id  as p_id  from (department as d join r_dep_pro as r on d.id = r.d_id"
					+ " join project as p on r.p_id = p.id) where d.id=" + id + " limit " + start + "," + eachPage + "";

			rs = stat.executeQuery(sql);
			// 6���Խ�������д���

			while (rs.next()) {
				ProMenege proMen = new ProMenege();
				proMen.setId(rs.getInt("d_id"));
				proMen.setpId(rs.getInt("p_id"));
				proMen.setpName(rs.getString("p.name"));

				list.add(proMen);
			}
			// 7�� �ر�
			closeAll(conn, stat, rs);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}

	public List<ProMenege> readEmpNoDepPro(String depName, int empId) {// ȡ�����Ŷ�Ӧ����Ŀ,��Ա��û��
		List<ProMenege> list = new ArrayList<ProMenege>();
		try {

			getConnection();// 4������stat sql���ִ����
			stat = conn.createStatement();

			// 5��ִ��sql��䲢�õ����
			String sql = "select p.name ,d.id as d_id,p.id  as p_id  from (department as d join r_dep_pro as r on d.id = r.d_id"
					+ " join project as p on r.p_id = p.id) where d.name='" + depName
					+ "' and p.id not in (select p_id from score WHERE e_id = " + empId + ") ";

			rs = stat.executeQuery(sql);
			// 6���Խ�������д���

			while (rs.next()) {
				ProMenege proMen = new ProMenege();
				proMen.setId(rs.getInt("d_id"));
				proMen.setpId(rs.getInt("p_id"));
				proMen.setpName(rs.getString("p.name"));

				list.add(proMen);
			}
			// 7�� �ر�
			closeAll(conn, stat, rs);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}

	public List<ProMenege> readNoPro(int id) {// ȡ�����Ŷ�Ӧ����Ŀ
		List<ProMenege> list = new ArrayList<ProMenege>();
		try {

			getConnection();// 4������stat sql���ִ����
			stat = conn.createStatement();

			// 5��ִ��sql��䲢�õ����
			String sql = "SELECT p.name , p.id from project as p where p.id not in "
					+ "(select p.id from (department as d " + "join r_dep_pro as r on d.id = r.d_id"
					+ " join project as p on r.p_id = p.id)" + " where d.id='" + id + "' ) ";

			rs = stat.executeQuery(sql);
			// 6���Խ�������д���

			while (rs.next()) {
				ProMenege proMen = new ProMenege();

				proMen.setpName(rs.getString("p.name"));
				proMen.setpId(rs.getInt("p.id"));
				list.add(proMen);
			}
			// 7�� �ر�
			closeAll(conn, stat, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}

	public int searchProCount(int id) {
		int count = 0;
		try {

			getConnection();// 4������stat sql���ִ����
			stat = conn.createStatement();

			// 5��ִ��sql��䲢�õ����
			String sql = "SELECT count(*) as count from project as p where p.id in "
					+ "(select p.id from (department as d " + "join r_dep_pro as r on d.id = r.d_id"
					+ " join project as p on r.p_id = p.id)" + " where d.id='" + id + "' ) ";

			rs = stat.executeQuery(sql);
			// 6���Խ�������д���

			while (rs.next()) {
				count = Integer.valueOf(rs.getString("count"));
			}
			// 7�� �ر�
			closeAll(conn, stat, rs);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;

	}

	public boolean add(int proid, ProMenege pro) {// ������
		boolean falg = false;
		try {
			getConnection();
			stat = conn.createStatement();

			String sql = " insert into r_dep_pro(d_id,p_id) values(" + proid + ",(select id from project where name ='"
					+ pro.getpName() + "'))";

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

	public boolean addBatch(int depId, String[] proIds) {// ������
		boolean falg = true;

		try {
			getConnection();
			conn.setAutoCommit(false);
			for (int i = 0; i < proIds.length; i++) {
				adddp(conn, depId, Integer.parseInt(proIds[i]));
			}
			conn.commit();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			falg = false;
		} finally {
			closeAll(conn, null, null);
		}
		return falg;

	}

	public boolean adddp(Connection conn, int depId, int proId) {// ������
		boolean falg = false;
		try {

			stat = conn.createStatement();

			String sql = " insert into r_dep_pro(d_id,p_id) values(" + depId + "," + proId + ")";

			int resultSet = stat.executeUpdate(sql);

			if (resultSet > 0) {
				falg = true;
			}

			closeAll(null, stat, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return falg;

	}

	public boolean adddp(int depId, int proId) {// ������
		boolean falg = false;
		try {
			getConnection();
			stat = conn.createStatement();

			String sql = " insert into r_dep_pro(d_id,p_id) values(" + depId + "," + proId + ")";

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

	public void deleteBatch(int deid, int ints) {
		try {
			getConnection();
			stat = conn.createStatement();

			String sql = " delete from r_dep_pro where d_id=" + deid + " and p_id = " + ints + "";

			stat.executeUpdate(sql);

			closeAll(conn, stat, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean delete(int deid, String ints) {
		boolean flag = false;
		try {

			getConnection();
			stat = conn.createStatement();

			String sql = " delete from r_dep_pro where d_id=" + deid + " and p_id in (" + ints + ")";

			int result = stat.executeUpdate(sql);

			if (result > 0) {
				flag = true;
			}
			closeAll(conn, stat, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

}
