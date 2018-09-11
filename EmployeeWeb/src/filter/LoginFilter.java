package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	// ִ�й���
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub FilterChain chain��ʽ�ṹ
		HttpSession session = ((HttpServletRequest) request).getSession();
		if (session.getAttribute("username") == null) {
			((HttpServletResponse) response).sendRedirect("User?type=showLogin");
		} else {
			chain.doFilter(request, response);// ��������ִ�У�������й������������������û�оͼ���ִ�г���
		}
	}

	// ��ʼ��ʱִ��
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

	// ����ʱִ��
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
