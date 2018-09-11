package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;

import dao.DepartmentDao;
import dao.EmployeeDao;
import entity.Department;
import entity.Employee;
import until.Contant;
import vo.PageVo;

public class EmployeeServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");

			String type = request.getParameter("type");
			if (type == null || "searchEmp".equals(type)) {
				// showEmployee(request, response);//首页

				searchEmployee(request, response);// 分页首页
			} else if ("showAdd".equals(type)) {
				showAdd(request, response);// 添加页面
			} else if ("showAdd2".equals(type)) {
				showAdd2(request, response);// 添加页面
			} else if ("addEmp".equals(type)) {
				addEmployee(request, response);// 完成添加
			} else if ("addEmp2".equals(type)) {
				addEmployee2(request, response);// 完成添加
			} else if ("showModity".equals(type)) {
				showModity(request, response);// 修改页面
			} else if ("modityEmp".equals(type)) {
				modityEmployee(request, response);// 提交修改
			} else if ("delEmp".equals(type)) {
				delEmployee(request, response);// 删除
			} else if ("updateTable".equals(type)) {
				updateTable(request, response);// 列表直接修改
			} else if ("empPro".equals(type)) {
				empPro(request, response);// 列表直接修改
			} else if ("upload".equals(type)) {
				upload(request, response);// 列表直接修改
			} else if ("deleteImg".equals(type)) {
				deleteImg(request, response);// 列表直接修改
			}

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void deleteImg(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String fileName = request.getParameter("fileNamed");
		File file = new File("G:/EmpImage");
		File[] fileArray = file.listFiles();
		if (fileArray != null) {
			for (File eachFile : fileArray) {
				if (eachFile.getName().equals(fileName)) {
					eachFile.delete();
				}
			}
		}

	}

	private void searchEmployee(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			@SuppressWarnings("rawtypes")
			PageVo pageVo = new PageVo(Contant.EMP_NUM_IN_PAGE, Contant.EMP_NUM_OF_PAGE);
			String pageNoStr = request.getParameter("pageNo");
			String name = request.getParameter("name");
			String sex = request.getParameter("sex");
			if ("请选择性别".equals(sex)) {
				sex = "";
			}
			String empage = request.getParameter("age");
			int age = -1;
			if (empage != null && !"".equals(empage)) {

				age = Integer.valueOf(empage);
			}
			String dep = request.getParameter("dep");
			if ("请选择部门".equals(dep)) {
				dep = "";
			}
			Employee employee = new Employee();
			EmployeeDao empDao = new EmployeeDao();
			DepartmentDao depDao = new DepartmentDao();

			employee.setName(name);
			employee.setAge(age);
			employee.setSex(sex);
			Department department = new Department();
			department.setName(dep);
			int depid = empDao.getDepId(dep);
			department.setId(depid);
			employee.setDep(department);
			int count = empDao.searchCount(employee);

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
			List<Employee> list = empDao.searchByConnection(employee, pageVo.getStartIndex(), pageVo.getPageSize());

			pageVo.setRecords(list);

			pageVo.setUrl("Employee?type=searchEmp&pageNo=");

			List<Department> depList = new ArrayList<>();
			depList = depDao.read();
			request.setAttribute("deplist", depList);
			request.setAttribute("condition", employee);
			request.setAttribute("pageVo", pageVo);
			request.getRequestDispatcher("WEB-INF/Employee/emps.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void showPage(HttpServletRequest request, HttpServletResponse response) {
		try {
			@SuppressWarnings("rawtypes")
			PageVo pageVo = new PageVo(Contant.EMP_NUM_IN_PAGE, Contant.EMP_NUM_OF_PAGE);
			String pageNoStr = request.getParameter("pageNo");
			int pageNo = 1;
			EmployeeDao empDao = new EmployeeDao();
			int count = empDao.getCount();
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
			List<Employee> list = empDao.readPage(pageVo.getStartIndex(), pageVo.getPageSize());

			pageVo.setRecords(list);

			pageVo.setUrl("Employee?pageNo=");
			request.setAttribute("pageVo", pageVo);
			request.getRequestDispatcher("WEB-INF/Employee/emps.jsp").forward(request, response);
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
		EmployeeDao employeeDao = new EmployeeDao();
		List<Employee> list = new ArrayList<>();
		for (int i = 0; i < strs.length; i++) {
			String[] temp = strs[i].split(",");
			Employee emp = new Employee();
			Department dep = new Department();
			emp.setId(Integer.parseInt(temp[0]));
			emp.setName(temp[1]);
			emp.setSex(temp[2]);
			emp.setAge(Integer.parseInt(temp[3]));
			int depId = employeeDao.getDepId(temp[4]);
			dep.setName(temp[4]);
			dep.setId(depId);
			emp.setDep(dep);
			list.add(emp);
		}

		employeeDao.update2(list);

		try {
			response.sendRedirect("Employee?pageNo=" + page);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void delEmployee(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			String ID = request.getParameter("delId");
			EmployeeDao empDao = new EmployeeDao();
			empDao.deleteBatch(ID);

			response.sendRedirect("Employee");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void modityEmployee(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			EmployeeDao empDao = new EmployeeDao();

			String[] empIds = request.getParameterValues("empId");
			String[] empNames = request.getParameterValues("empName");
			// String[] empSexs = request.getParameterValues("empSex");
			String[] empAges = request.getParameterValues("empAge");
			String[] empDeps = request.getParameterValues("empDep");
			// int empId = Integer.parseInt(request.getParameter("empId"));
			// String empName = request.getParameter("empName");
			// String empSex = request.getParameter("empSex");
			// String empAge = request.getParameter("empAge");
			// String empDep = request.getParameter("empDep");
			for (int i = 0; i < empIds.length; i++) {
				String empSex = request.getParameter("empSex" + i);
				Employee employee = new Employee();
				Department department = new Department();
				employee.setId(Integer.valueOf(empIds[i]));
				employee.setName(empNames[i]);
				employee.setAge(Integer.valueOf(empAges[i]));
				employee.setSex(empSex);
				int depId = empDao.getDepId(empDeps[i]);
				department.setName(empDeps[i]);
				department.setId(depId);
				employee.setDep(department);
				empDao.update(employee);
			}
			// 只有经历越多才能v变得更强

			response.sendRedirect("Employee");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void showModity(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			String id = request.getParameter("delId");
			EmployeeDao empDao = new EmployeeDao();
			List<Employee> list = empDao.search(id);
			request.setAttribute("list", list);
			DepartmentDao depDao = new DepartmentDao();
			List<Department> depList = new ArrayList<>();
			depList = depDao.read();
			request.setAttribute("deplist", depList);
			request.getRequestDispatcher("WEB-INF/Employee/modityEmp.jsp").forward(request, response);
		} catch (ServletException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public void upload(HttpServletRequest request, HttpServletResponse response) {
		try {

			String path = "g:/EmpImage/";
			String photoName = "";
			FileItemFactory factory = new DiskFileItemFactory();// 为该请求创建一个DiskFileItemFactory对象，通过它来解析请求。执行解析后，所有的表单项目都保存在一个List中。
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> items;
			items = upload.parseRequest(request);
			for (int i = 0; i < items.size(); i++) {

				FileItem item = items.get(i);
				if (item.getFieldName().equals("photo")) {
					UUID uuid = UUID.randomUUID();
					String houzhui = item.getName().substring(item.getName().lastIndexOf("."));
					photoName = uuid.toString() + houzhui;
					File savedFile = new File(path, photoName);
					item.write(savedFile);

				}
			}
			PrintWriter out = response.getWriter();
			out.print(photoName);

		} catch (FileUploadException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addEmployee(HttpServletRequest request, HttpServletResponse response) {
		try {
			String empName = "";
			String empSex = "";
			String empAge = null;
			String empDepName = "";
			String empImage = "";

			String path = "g:/EmpImage/";
			FileItemFactory factory = new DiskFileItemFactory();// 为该请求创建一个DiskFileItemFactory对象，通过它来解析请求。执行解析后，所有的表单项目都保存在一个List中。
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> items;
			items = upload.parseRequest(request);
			for (int i = 0; i < items.size(); i++) {

				FileItem item = items.get(i);
				if (item.getFieldName().equals("myFile")) {
					UUID uuid = UUID.randomUUID();
					String houzhui = item.getName().substring(item.getName().lastIndexOf("."));
					File savedFile = new File(path, uuid.toString() + houzhui);
					item.write(savedFile);
					empImage = uuid.toString() + houzhui;
				} else if (item.getFieldName().equals("empName")) {
					empName = new String(item.getString().getBytes("ISO-8859-1"), "utf-8");
				} else if (item.getFieldName().equals("empSex")) {
					empSex = new String(item.getString().getBytes("ISO-8859-1"), "utf-8");
				} else if (item.getFieldName().equals("empAge")) {
					empAge = new String(item.getString().getBytes("ISO-8859-1"), "utf-8");
				} else if (item.getFieldName().equals("empDep")) {
					empDepName = new String(item.getString().getBytes("ISO-8859-1"), "utf-8");
				}
			}
			Employee employee = new Employee();
			EmployeeDao empDao = new EmployeeDao();
			Department department = new Department();

			employee.setName(empName);
			employee.setAge(Integer.parseInt(empAge));
			employee.setSex(empSex);
			employee.setImage(empImage);
			Integer depId = empDao.getDepId(empDepName);
			department.setId(depId);
			department.setName(empDepName);
			employee.setDep(department);
			boolean flag = empDao.add(employee);

			if (flag) {
				response.sendRedirect("Employee");
			}

		} catch (FileUploadException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addEmployee2(HttpServletRequest request, HttpServletResponse response) {
		Employee employee = new Employee();
		EmployeeDao empDao = new EmployeeDao();
		Department department = new Department();
		String empName = request.getParameter("empName");
		String empSex = request.getParameter("empSex");
		String empAge = request.getParameter("empAge");
		String empDepName = request.getParameter("empDep");
		String empImage = request.getParameter("picture");
		employee.setName(empName);
		employee.setAge(Integer.valueOf(empAge));
		employee.setSex(empSex);
		employee.setImage(empImage);
		Integer depId = empDao.getDepId(empDepName);
		department.setId(depId);
		department.setName(empDepName);
		employee.setDep(department);
		boolean flag = empDao.add(employee);
		try {
			if (flag) {
				response.sendRedirect("Employee");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void showAdd(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {

			DepartmentDao depDao = new DepartmentDao();
			List<Department> depList = new ArrayList<>();
			depList = depDao.read();
			request.setAttribute("deplist", depList);
			request.getRequestDispatcher("WEB-INF/Employee/addEmp.jsp").forward(request, response);
		} catch (ServletException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void showAdd2(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {

			DepartmentDao depDao = new DepartmentDao();
			List<Department> depList = new ArrayList<>();
			depList = depDao.read();
			request.setAttribute("deplist", depList);
			request.getRequestDispatcher("WEB-INF/Employee/addEmp2.jsp").forward(request, response);
		} catch (ServletException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void showEmployee(HttpServletRequest request, HttpServletResponse response) {
		List<Employee> emps = new ArrayList<>();
		EmployeeDao empDao = new EmployeeDao();
		emps = empDao.read();
		request.setAttribute("emps", emps);

		try {
			request.getRequestDispatcher("WEB-INF/Employee/emps.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void empPro(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			@SuppressWarnings("rawtypes")
			PageVo pageVo = new PageVo(Contant.EMP_NUM_IN_PAGE, Contant.EMP_NUM_OF_PAGE);
			String pageNoStr = request.getParameter("pageNo");
			String name = request.getParameter("name");
			String sex = request.getParameter("sex");
			if ("请选择性别".equals(sex)) {
				sex = "";
			}
			String empage = request.getParameter("age");
			int age = -1;
			if (empage != null && !"".equals(empage)) {

				age = Integer.valueOf(empage);
			}
			String dep = request.getParameter("dep");
			if ("请选择部门".equals(dep)) {
				dep = "";
			}
			Employee employee = new Employee();
			EmployeeDao empDao = new EmployeeDao();
			DepartmentDao depDao = new DepartmentDao();

			employee.setName(name);
			employee.setAge(age);
			employee.setSex(sex);
			Department department = new Department();
			department.setName(dep);
			int depid = empDao.getDepId(dep);
			department.setId(depid);
			employee.setDep(department);
			int count = empDao.searchCount(employee);

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
			List<Employee> list = empDao.searchByConnection(employee, pageVo.getStartIndex(), pageVo.getPageSize());

			pageVo.setRecords(list);

			pageVo.setUrl("Employee?type=empPro&pageNo=");

			List<Department> depList = new ArrayList<>();
			depList = depDao.read();
			request.setAttribute("deplist", depList);
			request.setAttribute("condition", employee);
			request.setAttribute("pageVo", pageVo);
			request.getRequestDispatcher("WEB-INF/Employee/empPros.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
}
