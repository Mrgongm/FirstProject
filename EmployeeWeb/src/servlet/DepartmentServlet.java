package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import dao.DepartmentDao;
import dao.ProMenegeDao;
import entity.Department;
import entity.ProMenege;
import until.Contant;
import vo.PageVo;

public class DepartmentServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");

			String type = request.getParameter("type");
			if (type == null || "searchDep".equals(type)) {
				// showDepartment(request, response);//首页

				searchDepartment(request, response);// 分页首页
			} else if ("showAdd".equals(type)) {
				showAdd(request, response);// 添加页面
			} else if ("addDep".equals(type)) {
				addDepartment(request, response);// 完成添加
			} else if ("showModity".equals(type)) {
				showModity(request, response);// 修改页面
			} else if ("modityDep".equals(type)) {
				modityDepartment(request, response);// 提交修改
			} else if ("delDep".equals(type)) {
				delDepartment(request, response);// 删除
			} else if ("updateTable".equals(type)) {
				updateTable(request, response);// 列表直接修改
			} else if ("showManagePro".equals(type)) {
				showManagePro(request, response);// 列表直接修改
			} else if ("addPro".equals(type)) {
				addPro(request, response);// 列表直接修改
			} else if ("delPro".equals(type)) {
				delProject(request, response);// 删除
			} else if ("showManagePro2".equals(type)) {
				showManagePro2(request, response);// 列表直接修改
			} else if ("addPro2".equals(type)) {
				addPro2(request, response);// 列表直接修改
			} else if ("delPro2".equals(type)) {
				delProject2(request, response);// 删除
			} else if ("showManagePro3".equals(type)) {
				showManagePro3(request, response);// 列表直接修改
			} else if ("addPro3".equals(type)) {
				addPro3(request, response);// 列表直接修改
			} else if ("delPro3".equals(type)) {
				delProject3(request, response);// 删除
			} else if ("showManagePro4".equals(type)) {
				showManagePro4(request, response);// 列表直接修改
			}

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void showManagePro4(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {

			int id = Integer.parseInt(request.getParameter("delId"));

			DepartmentDao depDao = new DepartmentDao();
			ProMenegeDao pDao = new ProMenegeDao();
			List<ProMenege> nolist = pDao.readNoPro(id);
			String depName = depDao.getDepName(id);
			List<ProMenege> list = pDao.readDepPro(id);
			request.setAttribute("id", id);
			request.setAttribute("depName", depName);
			request.setAttribute("list", list);
			request.setAttribute("nolist", nolist);
			request.getRequestDispatcher("WEB-INF/Department/managePro4.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void delProject3(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			PrintWriter out = response.getWriter();
			int depId = Integer.parseInt(request.getParameter("delId"));
			String selectIds = request.getParameter("pId");
			ProMenegeDao proMenegeDao = new ProMenegeDao();
			boolean flag = proMenegeDao.delete(depId, selectIds);
			out.print(flag);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void addPro3(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			PrintWriter out = response.getWriter();
			int depId = Integer.parseInt(request.getParameter("delId"));
			String[] proIds = request.getParameter("pId").split(",");

			ProMenegeDao pDao = new ProMenegeDao();

			boolean flag = pDao.addBatch(depId, proIds);

			out.print(flag);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void showManagePro3(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {

			int id = Integer.parseInt(request.getParameter("delId"));

			DepartmentDao depDao = new DepartmentDao();
			ProMenegeDao pDao = new ProMenegeDao();
			List<ProMenege> nolist = pDao.readNoPro(id);
			String depName = depDao.getDepName(id);
			List<ProMenege> list = pDao.readDepPro(id);
			request.setAttribute("id", id);
			request.setAttribute("depName", depName);
			request.setAttribute("list", list);
			request.setAttribute("nolist", nolist);
			request.getRequestDispatcher("WEB-INF/Department/managePro3.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void delProject2(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

	private void addPro2(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			PrintWriter out = response.getWriter();
			int depId = Integer.parseInt(request.getParameter("delId"));
			int proId = Integer.parseInt(request.getParameter("pId"));

			ProMenegeDao pDao = new ProMenegeDao();

			boolean flag = pDao.adddp(depId, proId);
			out.print(flag);
			response.sendRedirect("Department?type=showManagePro2&delId=" + depId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void showManagePro2(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			PageVo pageVo = new PageVo(Contant.PRO_NUM_IN_PAGE, Contant.PRO_NUM_OF_PAGE);
			int id = Integer.parseInt(request.getParameter("delId"));
			String pageNoStr = request.getParameter("pageNo");
			DepartmentDao depDao = new DepartmentDao();
			ProMenegeDao pDao = new ProMenegeDao();

			List<ProMenege> nolist = pDao.readNoPro(id);
			String depName = depDao.getDepName(id);

			int count = pDao.searchProCount(id);

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
			List<ProMenege> list = pDao.readDepPro(id, pageVo.getStartIndex(), pageVo.getPageSize());
			pageVo.setRecords(list);
			pageVo.setUrl("Department?type=showManagePro2&pageNo=");
			request.setAttribute("pageVo", pageVo);
			request.setAttribute("id", id);
			request.setAttribute("depName", depName);
			request.setAttribute("condition", list);
			request.setAttribute("noPro", nolist);
			request.getRequestDispatcher("WEB-INF/Department/managePro2.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void delProject(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			int depId = Integer.parseInt(request.getParameter("delId"));
			int selectId = Integer.parseInt(request.getParameter("pId"));
			ProMenegeDao proMenegeDao = new ProMenegeDao();
			proMenegeDao.deleteBatch(depId, selectId);
			response.sendRedirect("Department?type=showManagePro&delId=" + depId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void addPro(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			int depId = Integer.parseInt(request.getParameter("delId"));
			int proId = Integer.parseInt(request.getParameter("pId"));

			ProMenegeDao pDao = new ProMenegeDao();

			pDao.adddp(depId, proId);

			response.sendRedirect("Department?type=showManagePro&delId=" + depId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void showManagePro(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			PageVo pageVo = new PageVo(Contant.PRO_NUM_IN_PAGE, Contant.PRO_NUM_OF_PAGE);
			int id = Integer.parseInt(request.getParameter("delId"));
			String pageNoStr = request.getParameter("pageNo");
			DepartmentDao depDao = new DepartmentDao();
			ProMenegeDao pDao = new ProMenegeDao();

			List<ProMenege> nolist = pDao.readNoPro(id);
			String depName = depDao.getDepName(id);

			int count = pDao.searchProCount(id);

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
			List<ProMenege> list = pDao.readDepPro(id, pageVo.getStartIndex(), pageVo.getPageSize());
			pageVo.setRecords(list);
			pageVo.setUrl("Department?type=showManagePro&pageNo=");
			request.setAttribute("pageVo", pageVo);
			request.setAttribute("id", id);
			request.setAttribute("depName", depName);
			request.setAttribute("condition", list);
			request.setAttribute("noPro", nolist);
			request.getRequestDispatcher("WEB-INF/Department/managePro.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void searchDepartment(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			@SuppressWarnings("rawtypes")
			PageVo pageVo = new PageVo(Contant.DEP_NUM_IN_PAGE, Contant.DEP_NUM_OF_PAGE);
			String pageNoStr = request.getParameter("pageNo");
			String name = request.getParameter("name");
			String depcount = request.getParameter("empCount");
			int empCount = -1;
			Department department = new Department();
			DepartmentDao depDao = new DepartmentDao();
			department.setName(name);

			if (depcount != null && !"".equals(depcount)) {

				empCount = Integer.valueOf(depcount);
			}
			department.setEmpCount(empCount);
			int count = depDao.searchCount(department);

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
			List<Department> list = depDao.searchByConnection(department, pageVo.getStartIndex(), pageVo.getPageSize());

			pageVo.setRecords(list);

			pageVo.setUrl("Department?type=searchDep&pageNo=");
			request.setAttribute("condition", department);
			request.setAttribute("pageVo", pageVo);
			request.getRequestDispatcher("WEB-INF/Department/deps.jsp").forward(request, response);
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

		List<Department> list = new ArrayList<>();
		for (int i = 0; i < strs.length; i++) {
			String[] temp = strs[i].split(",");
			Department dep = new Department();
			dep.setId(Integer.parseInt(temp[0]));
			dep.setName(temp[1]);
			list.add(dep);
		}

		DepartmentDao departmentDao = new DepartmentDao();
		departmentDao.update2(list);

		try {
			response.sendRedirect("Department?pageNo=" + page);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void delDepartment(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			String ID = request.getParameter("delId");
			DepartmentDao depDao = new DepartmentDao();
			depDao.deleteBatch(ID);
			response.sendRedirect("Department");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void modityDepartment(HttpServletRequest request, HttpServletResponse response) {

		DepartmentDao depDao = new DepartmentDao();

		String[] depIds = request.getParameterValues("depId");
		String[] depNames = request.getParameterValues("depName");

		for (int i = 0; i < depIds.length; i++) {

			Department department = new Department();

			department.setId(Integer.valueOf(depIds[i]));
			department.setName(depNames[i]);

			depDao.update(department);
		}
		// 只有经历越多才能v变得更强
		try {

			response.sendRedirect("Department");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void showModity(HttpServletRequest request, HttpServletResponse response) {

		try {
			String id = request.getParameter("delId");
			DepartmentDao depDao = new DepartmentDao();
			List<Department> list = depDao.search(id);
			request.setAttribute("list", list);
			request.getRequestDispatcher("WEB-INF/Department/modityDep.jsp").forward(request, response);
		} catch (ServletException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public void addDepartment(HttpServletRequest request, HttpServletResponse response) {
		Department department = new Department();
		DepartmentDao depDao = new DepartmentDao();
		String depName = request.getParameter("depName");
		department.setName(depName);
		boolean flag = depDao.add(department);
		try {
			if (flag) {
				response.sendRedirect("Department");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void showAdd(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			request.getRequestDispatcher("WEB-INF/Department/addDep.jsp").forward(request, response);
		} catch (ServletException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void showDepartment(HttpServletRequest request, HttpServletResponse response) {
		// List<Department> deps = new ArrayList<>();
		// DepartmentDao depDao = new DepartmentDao();
		// deps = depDao.read();
		// request.setAttribute("deps", deps);
		//
		// try {
		// request.getRequestDispatcher("WEB-INF/deps.jsp").forward(request, response);
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
