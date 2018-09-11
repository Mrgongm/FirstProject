package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import entity.User;
import until.MD5;
import until.RandomNumber;
import until.ValidateCode;

public class UserServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) {

		String type = request.getParameter("type");
		if (type == null) {
			showRegister(request, response);
		} else if ("showLogin".equals(type)) {
			showLogin(request, response);
		} else if ("doLogin".equals(type)) {
			doLogin(request, response);
		} else if ("randomImage".equals(type)) {
			randomImage(request, response);
		} else if ("showRegister".equals(type)) {
			showRegister(request, response);
		} else if ("doRegister".equals(type)) {
			doRegister(request, response);
		} else if ("isNew".equals(type)) {
			isNew(request, response);
		}

	}

	private void isNew(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {

			PrintWriter out = response.getWriter();
			String username = request.getParameter("username");
			User user = new User();
			user.setUsername(username);
			UserDao userDao = new UserDao();
			boolean flag = userDao.isNewUser(username);

			out.print(flag);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private void doRegister(HttpServletRequest request, HttpServletResponse response) {
		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");

			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String str = format.format(date);

			UserDao userDao = new UserDao();

			boolean isnewuser = userDao.isNewUser(username);
			if (!isnewuser) {

				User user = new User();
				user.setPassword(MD5.MD5(password + str));
				user.setUsername(username);
				user.setDate(str);

				boolean flag = userDao.add(user);

				if (flag) {
					Cookie cookie = new Cookie("usernameCookie", username);
					cookie.setMaxAge(30);// ������������ʱ�䣬��λ���룬��60*60*24Ϊһ��
					response.addCookie(cookie);
					response.sendRedirect("User?type=showLogin");
				} else {
					response.sendRedirect("User?type=showRegister");
				}
			} else {
				request.setAttribute("mes", "�û����Ѵ���");
				request.getRequestDispatcher("WEB-INF/register/register.jsp").forward(request, response);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	private void randomImage(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		RandomNumber rn = new RandomNumber();
		try {
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);// ������
			ValidateCode vc = rn.generateImage();
			ServletOutputStream outStream = response.getOutputStream();
			// ���ͼ��ҳ��
			ImageIO.write(vc.getImage(), "JPEG", outStream);
			outStream.close();
			request.getSession().setAttribute("rand", vc.getRand());

		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	private void showRegister(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {

			request.getRequestDispatcher("WEB-INF/register/register.jsp").forward(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void showLogin(HttpServletRequest request, HttpServletResponse response) {
		try {
			// boolean flag = (boolean) request.getAttribute("flag");
			String name = "";
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {

				for (int i = 0; i < cookies.length; i++) {
					if ("usernameCookie".equals(cookies[i].getName())) {
						name = cookies[i].getValue();
					}
				}
			}

			// request.setAttribute("flag", flag);
			request.setAttribute("name", name);
			request.getRequestDispatcher("WEB-INF/login/login.jsp").forward(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void doLogin(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			User user = new User();
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String random = request.getParameter("random");
			if (random.equals(session.getAttribute("rand"))) {
				UserDao userDao = new UserDao();
				String str = userDao.getDate(username);
				user.setPassword(MD5.MD5(password + str));
				user.setUsername(username);

				boolean flag = userDao.search(user);
				if (flag) {

					session.setAttribute("username", username); // ���Ự�����һ�����ԣ���ŵ��û���

					Cookie cookie = new Cookie("usernameCookie", username);
					cookie.setMaxAge(60);// ������������ʱ�䣬��λ���룬��60*60*24Ϊһ��
					response.addCookie(cookie);

					response.sendRedirect("Index");
				} else {
					// request.setAttribute("flag", flag);
					request.setAttribute("mes", "�˺Ż��������");
					request.getRequestDispatcher("WEB-INF/login/login.jsp").forward(request, response);
				}
			} else {
				request.setAttribute("mes", "��֤�����");
				request.getRequestDispatcher("WEB-INF/login/login.jsp").forward(request, response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);

	}
}
