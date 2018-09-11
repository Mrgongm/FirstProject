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

import dao.ProjectDao;
import entity.Project;
import until.Contant;
import vo.PageVo;

public class ProjectServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");

			String type = request.getParameter("type");
			if (type == null || "searchPro".equals(type)) {
				// showProject(request, response);//首页

				searchProject(request, response);// 分页首页
			} else if ("showAdd".equals(type)) {
				showAdd(request, response);// 添加页面
			} else if ("addPro".equals(type)) {
				addProject(request, response);// 完成添加
			} else if ("showModity".equals(type)) {
				showModity(request, response);// 修改页面
			} else if ("modityPro".equals(type)) {
				modityProject(request, response);// 提交修改
			} else if ("delPro".equals(type)) {
				delProject(request, response);// 删除
			} else if ("updateTable".equals(type)) {
				updateTable(request, response);// 列表直接修改
			}

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void searchProject(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			@SuppressWarnings("rawtypes")
			PageVo pageVo = new PageVo(Contant.PRO_NUM_IN_PAGE, Contant.PRO_NUM_OF_PAGE);
			String pageNoStr = request.getParameter("pageNo");
			String name = request.getParameter("name");

			Project project = new Project();
			ProjectDao proDao = new ProjectDao();
			project.setName(name);

			int count = proDao.searchCount(project);

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
			List<Project> list = proDao.searchByConnection(project, pageVo.getStartIndex(), pageVo.getPageSize());

			pageVo.setRecords(list);

			pageVo.setUrl("Project?type=searchPro&pageNo=");
			request.setAttribute("condition", project);
			request.setAttribute("pageVo", pageVo);
			request.getRequestDispatcher("WEB-INF/Project/pros.jsp").forward(request, response);
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

		List<Project> list = new ArrayList<>();
		for (int i = 0; i < strs.length; i++) {
			String[] temp = strs[i].split(",");
			Project pro = new Project();
			pro.setId(Integer.parseInt(temp[0]));
			pro.setName(temp[1]);
			list.add(pro);
		}

		ProjectDao projectDao = new ProjectDao();
		projectDao.update2(list);

		try {
			response.sendRedirect("Project?pageNo=" + page);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void delProject(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			String ID = request.getParameter("delId");
			ProjectDao proDao = new ProjectDao();
			proDao.deleteBatch(ID);
			response.sendRedirect("Project");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void modityProject(HttpServletRequest request, HttpServletResponse response) {

		ProjectDao proDao = new ProjectDao();

		String[] proIds = request.getParameterValues("proId");
		String[] proNames = request.getParameterValues("proName");

		for (int i = 0; i < proIds.length; i++) {

			Project project = new Project();
			project.setId(Integer.valueOf(proIds[i]));
			project.setName(proNames[i]);
			proDao.update(project);
		}
		// 只有经历越多才能v变得更强
		try {

			response.sendRedirect("Project");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void showModity(HttpServletRequest request, HttpServletResponse response) {

		try {
			String id = request.getParameter("delId");
			ProjectDao proDao = new ProjectDao();
			List<Project> list = proDao.search(id);
			request.setAttribute("list", list);
			request.getRequestDispatcher("WEB-INF/Project/modityPro.jsp").forward(request, response);
		} catch (ServletException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public void addProject(HttpServletRequest request, HttpServletResponse response) {
		Project project = new Project();
		ProjectDao proDao = new ProjectDao();
		String proName = request.getParameter("proName");
		project.setName(proName);
		boolean flag = proDao.add(project);
		try {
			if (flag) {
				response.sendRedirect("Project");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void showAdd(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			request.getRequestDispatcher("WEB-INF/Project/addPro.jsp").forward(request, response);
		} catch (ServletException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void showProject(HttpServletRequest request, HttpServletResponse response) {
		// List<Project> pros = new ArrayList<>();
		// ProjectDao proDao = new ProjectDao();
		// pros = proDao.read();
		// request.setAttribute("pros", pros);
		//
		// try {
		// request.getRequestDispatcher("WEB-INF/pros.jsp").forward(request, response);
		// } catch (ServletException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
}
