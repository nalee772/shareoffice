package control;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CommandProcess;


@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	// web.xml에서 propertyConfig에 해당하는 init-param의 값을 읽어옴
	private Map<String, Object> commandMap = new HashMap<String, Object>();
			//list.do=service.ListAction
	
	
	// 메모리 사용위한 메소드 config라는 이름으로 사용(<web.xml에있는 <param-name>으로 사용)
	public void init(ServletConfig config) throws ServletException {
		// web.xml에서 propertyConfig에 해당하는 init-param 의 값을 읽어옴
		String props = config.getInitParameter("config");

		// 명령어와 처리클래스의 맵핑정보를 저장할 properties 객체 생성
		Properties pr = new Properties();  //= command.Properties
		FileInputStream f = null;

		try {
			String configFilePath = config.getServletContext().getRealPath(props);
			f = new FileInputStream(configFilePath); // 파일생성

			// command.properties파일의 정보를 Properties객체에 저장
			pr.load(f);

		} catch (IOException e) {
			throw new ServletException(e);

		} finally {

			if (f != null)
				try {
					f.close();
				} catch (IOException ex) {

				}
		}

		// Iterator객체는 Enumeration 객체를 확장시킨 개념의 객체
		Iterator keyIter = pr.keySet().iterator(); //list.do, content.do 등을 keyIter에 저장
		/// list.do = service.ListAction
			//키 = value 
		// 객체를 하나씩 꺼내서 그 객체명으로 properties객체에 저장된 객체에 접근

		while (keyIter.hasNext()) { //keyIter의 값이 있으면
			String command = (String) keyIter.next(); // command 에 list.do 저장
			String className = pr.getProperty(command); // service.ListAction을 전달

			try {
				Class commandClass = Class.forName(className);  //해당 문자열로 클래스 객체를 생성.(선언)
				Object commandInstance = commandClass.newInstance();//해당클래스의 인스턴스 생성(메모리 할당)
				//위 두줄은 ListAction la = new ListAction(); 
				// ContentAction ca = new ContentAction(); 와 같은 의미
				//String className = pr.getProperty(command); 줄의 
				//String 타입으로 쓰려고 Class.forName 을 사용한것
				
				commandMap.put(command, commandInstance); // Map객체인 commandMap에 객체 저장
							// <String, Object>

			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		requestPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		requestPro(request, response);
	}

	
	
	
	// http://localhost:8284/och16/list.jsp
	// 사용자의 요청을 분석해서 해당 작업을 처리
	//액션 클래스에서 처리한 작업을 가져와 view페이지로 forward
	private void requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String view = null;
		CommandProcess com = null;

		try {
			String command = request.getRequestURI();
			System.out.println("Controller getRequestURI=>"+request.getRequestURI());
			System.out.println("Controller getContextPath=>"+request.getContextPath());
			System.out.println("1.requestPro start...."+command);
			// System.out.println(request.getContextPath()); // /ch16
			// System.out.println(command.indexOf(request.getContextPath())); // 0
			// if (command.indexOf(request.getContextPath()) == 0) {

			command = command.substring(request.getContextPath().length());
			System.out.println("2.requestPro"+command);

			// }
			com = (CommandProcess) commandMap.get(command);
			//오버라이딩 된 해당 action 객체
			System.out.println("3.requestPro command-->" + command);
			System.out.println("4.requestPro com-->" + com);

			view = com.requestPro(request, response);
			//액션에서 돌아오는 msg를 view로 받음
			System.out.println("view-->" + view);

		} catch (Throwable e) {
			throw new ServletException(e);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		//이동객체 -> view가 목적지
		dispatcher.forward(request, response); 
	}
}
