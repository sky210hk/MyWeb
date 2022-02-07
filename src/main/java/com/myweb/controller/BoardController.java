package com.myweb.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myweb.board.service.BoardService;
import com.myweb.board.service.DeleteServiceImpl;
import com.myweb.board.service.GetContentServiceImpl;
import com.myweb.board.service.GetListServiceImpl;
import com.myweb.board.service.RegistServiceImpl;
import com.myweb.board.service.UpHitServiceImpl;
import com.myweb.user.model.UsersVO;

//1.
@WebServlet("*.board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BoardController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}
	
	//2.
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//한글처리
		request.setCharacterEncoding("UTF-8");

		//3. 요청핸들링
		String uri = request.getRequestURI();
		String path = request.getContextPath(); //MyWeb
		
		//절삭
		String command = uri.substring(path.length());
		
		System.out.println(command);
		
		//MVC2방식에서 페이지 이동은 기본적으로 포워드 형식을 사용합니다.
		//redirect는 다시 컨트롤러를 태울 때 사용합니다.
		//부모인터페이스 선언
		BoardService service;
		
		//경로설정
		if(command.equals("/board/list.board")) { //들어오는 경로
			
			service = new GetListServiceImpl();
			service.execute(request, response);
			
			//request를 계속 가지고 가야 하므로 forward이동을 해야 한다.
			request.getRequestDispatcher("board_list.jsp").forward(request, response);

		} else if (command.equals("/board/write.board")){ //1. 글쓰기 화면 이동
			
			request.getRequestDispatcher("board_write.jsp").forward(request, response);

		} else if (command.equals("/board/regist.board")) { //2. 글 등록
			
			//등록영역
			service = new RegistServiceImpl();
			service.execute(request, response);
			
			response.sendRedirect("list.board"); //다시 컨트롤러를 태워 나간다.
			
		} else if (command.equals("/board/content.board")) { //4. 상세내용(화면에서 bno를 넘겨준다.)
			
			//조회수 증가
			service = new UpHitServiceImpl();
			service.execute(request, response);
			
			//상세보기
			service = new GetContentServiceImpl();
			service.execute(request, response);
			
			//포워드이동
			request.getRequestDispatcher("board_content.jsp").forward(request, response);
			
		} else if (command.equals("/board/modify.board")) { //5. 수정화면(화면에서 bno를 넘겨준다.)
			
			//상세보기
			service = new GetContentServiceImpl();
			service.execute(request, response);
			
			//포워드이동
			request.getRequestDispatcher("board_modify.jsp").forward(request, response);
			
		} else if (command.equals("/board/update.board")){ //6. 글 수정
			
			//상세보기
			service = new GetContentServiceImpl();
			service.execute(request, response);
			
			//포워드이동을 하지 않는다.
			response.sendRedirect("content.board?bno=" + request.getParameter("bno"));
			
		} else if (command.equals("/board/delete.board")) { //7. 글 삭제
			
			service = new DeleteServiceImpl();
			service.execute(request, response);
			
			//포워드 이동 x
			response.sendRedirect("list.board");
			
		}
		
	}

}










