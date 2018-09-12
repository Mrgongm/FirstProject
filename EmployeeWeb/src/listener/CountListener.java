package listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import until.MyWebSocket;

public class CountListener implements HttpSessionListener {// 监听新session
	// session的新建
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		// TODO Auto-generated method stub event session创建的事件
		HttpSession session = event.getSession();// 该session不同于浏览器请求时的会话
		ServletContext application = session.getServletContext();
		int num = 0;
		int allnum = 0;
		if (application.getAttribute("num") != null) {
			num = (Integer) application.getAttribute("num");
		}
		if (application.getAttribute("allnum") != null) {
			allnum = (Integer) application.getAttribute("allnum");
		}
		num++;
		allnum++;
		application.setAttribute("num", num);
		application.setAttribute("allnum", allnum);
		for (MyWebSocket item : MyWebSocket.set) {
			item.sendMessage(String.valueOf(num));
			item.sendMessage(String.valueOf(allnum));
		}

	}

	// session的销毁
	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		// TODO Auto-generated method stub
		HttpSession session = event.getSession();// 该session不同于浏览器请求时的会话
		ServletContext application = session.getServletContext();
		int num = 0;
		if (application.getAttribute("num") != null) {
			num = (Integer) application.getAttribute("num");
		}
		num--;
		application.setAttribute("num", num);
		for (MyWebSocket item : MyWebSocket.set) {
			item.sendMessage(String.valueOf(num));
		}
	}

}
