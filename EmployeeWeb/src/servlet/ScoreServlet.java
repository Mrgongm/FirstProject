package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import dao.DepartmentDao;
import dao.ProjectDao;
import dao.ScoreDao;
import entity.Department;
import entity.Employee;
import entity.Project;
import entity.Score;
import until.Contant;
import vo.PageVo;

public class ScoreServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");

			String type = request.getParameter("type");
			if (type == null || "searchSco".equals(type)) {
				// showScore(request, response);//首页

				searchScore(request, response);// 分页首页
			} else if ("updateTable".equals(type)) {
				updateTable(request, response);// 列表直接修改
			} else if ("search".equals(type)) {
				search(request, response);// 列表直接修改
			} else if ("input".equals(type)) {
				input(request, response);// 列表直接修改
			}

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void search(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			@SuppressWarnings("rawtypes")
			PageVo pageVo = new PageVo(Contant.SCO_NUM_IN_PAGE, Contant.SCO_NUM_OF_PAGE);
			String pageNoStr = request.getParameter("pageNo");
			String name = request.getParameter("name");
			String dName = request.getParameter("dName");
			String pName = request.getParameter("pName");
			String grade = request.getParameter("grade");

			ScoreDao scoDao = new ScoreDao();
			DepartmentDao depDao = new DepartmentDao();
			ProjectDao proDao = new ProjectDao();
			Employee employee = new Employee();
			Department department = new Department();
			Score score = new Score();
			Project project = new Project();

			employee.setName(name);
			score.setEmp(employee);

			if ("选择查找部门".equals(dName)) {
				dName = "";
			}
			department.setName(dName);
			score.setDep(department);

			if ("选择查找项目".equals(pName)) {
				pName = "";
			}
			project.setName(pName);
			score.setPro(project);

			if ("选择查找等级".equals(grade)) {
				grade = "";
			}
			score.setGrade(grade);

			int count = scoDao.searchCount(score);

			int pageNo = 1;
			if (StringUtils.isNotEmpty(pageNoStr)) {
				pageNo = Integer.parseInt(pageNoStr);
			}
			if (pageNo < 1) {
				pageNo = 1;
			}
			pageVo.setTotalRecords(count);
			if (pageNo >= pageVo.getTotalPageSize()) {
				pageNo = pageVo.getTotalPageSize();
			}

			pageVo.setPageNo(pageNo);
			List<Score> list = scoDao.searchByConnection(score, pageVo.getStartIndex(), pageVo.getPageSize());

			pageVo.setRecords(list);

			List<Department> depList = new ArrayList<>();
			depList = depDao.read();
			List<Project> proList = new ArrayList<>();
			proList = proDao.read();
			request.setAttribute("deplist", depList);
			request.setAttribute("prolist", proList);
			pageVo.setUrl("Score?type=searchSco&pageNo=");
			request.setAttribute("condition", score);
			request.setAttribute("pageVo", pageVo);
			request.getRequestDispatcher("WEB-INF/Score/scos2.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void input(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

	private void searchScore(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			@SuppressWarnings("rawtypes")
			PageVo pageVo = new PageVo(Contant.SCO_NUM_IN_PAGE, Contant.SCO_NUM_OF_PAGE);
			String pageNoStr = request.getParameter("pageNo");
			String name = request.getParameter("name");
			String dName = request.getParameter("dName");
			String pName = request.getParameter("pName");
			String grade = request.getParameter("grade");

			ScoreDao scoDao = new ScoreDao();
			DepartmentDao depDao = new DepartmentDao();
			ProjectDao proDao = new ProjectDao();
			Employee employee = new Employee();
			Department department = new Department();
			Score score = new Score();
			Project project = new Project();

			employee.setName(name);
			score.setEmp(employee);

			if ("选择查找部门".equals(dName)) {
				dName = "";
			}
			department.setName(dName);
			score.setDep(department);

			if ("选择查找项目".equals(pName)) {
				pName = "";
			}
			project.setName(pName);
			score.setPro(project);

			if ("选择查找等级".equals(grade)) {
				grade = "";
			}
			score.setGrade(grade);

			int count = scoDao.searchCount(score);

			int pageNo = 1;
			if (StringUtils.isNotEmpty(pageNoStr)) {
				pageNo = Integer.parseInt(pageNoStr);
			}
			if (pageNo < 1) {
				pageNo = 1;
			}
			pageVo.setTotalRecords(count);
			if (pageNo >= pageVo.getTotalPageSize()) {
				pageNo = pageVo.getTotalPageSize();
			}

			pageVo.setPageNo(pageNo);
			List<Score> list = scoDao.searchByConnection(score, pageVo.getStartIndex(), pageVo.getPageSize());

			pageVo.setRecords(list);

			List<Department> depList = new ArrayList<>();
			depList = depDao.read();
			List<Project> proList = new ArrayList<>();
			proList = proDao.read();
			request.setAttribute("deplist", depList);
			request.setAttribute("prolist", proList);
			pageVo.setUrl("Score?type=searchSco&pageNo=");
			request.setAttribute("condition", score);
			request.setAttribute("pageVo", pageVo);
			request.getRequestDispatcher("WEB-INF/Score/scos.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void updateTable(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String str = request.getParameter("str");
		String page = request.getParameter("page");
		String[] strs = str.split(";");

		List<Score> list = new ArrayList<>();
		for (int i = 0; i < strs.length; i++) {
			String[] temp = strs[i].split(",");
			Score sco = new Score();
			sco.setId(Integer.parseInt(temp[0]));
			sco.setValue(Integer.parseInt(temp[1]));
			list.add(sco);
		}

		ScoreDao scoreDao = new ScoreDao();
		scoreDao.update2(list);

		try {
			response.sendRedirect("Score?pageNo=" + page);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
}
