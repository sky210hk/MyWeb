package com.myweb.util.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myweb.user.model.UsersVO;

//글 수정, 삭제에 대한 필터
@WebFilter({"/board/modify.board", "/board/update.board", "/board/delete.board"})
public class BoardFilter02 implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		request.setCharacterEncoding("UTF-8");
		
		/*
		 * 1. 등록화면에서 작성자를 id값으로 고정
		 * 2. 각각 요청엥서 writer값이 반드시 담겨 넘어오도록 처리.
		 * 3. 세션의 id와 writer의 비교
		 */
		
		//세션
		HttpSession session = req.getSession();
		UsersVO vo = (UsersVO)session.getAttribute("userVO");
		
		if(vo == null) {
			res.sendRedirect("/MyWeb/user/login.jsp");
			return; //컨트롤러를 실행하지 않는다.
		}
		
		
		//화면에서 넘어오는 writer
		String writer = req.getParameter("writer"); //작성자
		System.out.println(writer);
		
		//세션에서 id를 확인
		String id = vo.getId();
		
		if(writer == null || !id.equals(writer) ) { //작성자가 아닌 경우(권한이 없음)
			res.setContentType("text/html; charset=UTF-8;"); //응답내용에 관한 설정
			
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('권한이 없습니다.'); ");
			out.println("location.href='/MyWeb/board/list.board'; ");
			out.println("</script>");
			
			return; //컨트롤러를 실행하지 않음
		}
		
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

}
