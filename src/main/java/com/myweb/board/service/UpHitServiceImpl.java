package com.myweb.board.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myweb.board.model.BoardDAO;

public class UpHitServiceImpl implements BoardService {

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		//bno를 받는다.
		String bno = request.getParameter("bno");
		
		//쿠키검사
		Cookie[] cookies = request.getCookies();
		
		boolean flag = true; //중복검사 변수
		if(cookies != null) {
			for (Cookie c : cookies) {
				if(c.getName().equals(bno)) { //쿠키이름이 bno랑 같으면
					flag = false; //중복됨
					break; //빠져나옴(증가 x)
				}
			}
		}
		//flag가 true인 경우만 조회수를 증가하도록
		if(flag) {
			BoardDAO dao = BoardDAO.getInstance();
			dao.upHit(bno);
		}
		//쿠키는 서버측에서 생성되서 클라이언트에 저장되는 정보
		//쿠키 : 글번호에 저장
		//쿠키가 있으면 - 조회수 증가 쿠키가 없으면 - 조회수 증가 o
		//쿠키생성
		Cookie cookie = new Cookie(bno, bno); //(이름, 값)
		//쿠키시간설정
		cookie.setMaxAge(20);
		//쿠키전송
		response.addCookie(cookie);
	}
}

