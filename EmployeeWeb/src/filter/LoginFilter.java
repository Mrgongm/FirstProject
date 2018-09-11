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

	// 执行过滤
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub FilterChain chain链式结构
		HttpSession session = ((HttpServletRequest) request).getSession();
		if (session.getAttribute("username") == null) {
			((HttpServletResponse) response).sendRedirect("User?type=showLogin");
		} else {
			chain.doFilter(request, response);// 继续往下执行，如果还有过滤器进过滤器，如果没有就继续执行程序
		}
	}

	// 初始化时执行
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

	// 销毁时执行
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
