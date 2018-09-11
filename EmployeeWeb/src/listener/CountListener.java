package listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class CountListener implements HttpSessionListener {// 监听新session
	// session的新建
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		// TODO Auto-generated method stub event session创建的事件

		ServletContext application = event.getSession().getServletContext();
		int num = 0;
		if (application.getAttribute("num") != null) {
			num = (Integer) application.getAttribute("num");
		}
		num++;
		application.setAttribute("num", num);

	}

	// session的销毁
	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		// TODO Auto-generated method stub

	}

}
