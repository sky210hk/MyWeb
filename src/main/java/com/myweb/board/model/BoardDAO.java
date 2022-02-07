package com.myweb.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.myweb.util.JdbcUtil;

public class BoardDAO {

	//1. 스스로 객체를 생성하고 1개로 제한
	static BoardDAO dao = new BoardDAO();

	//2. 생성자에 private를 붙인다. (다른 데서 쓸 수 없도록 하기 위해)
	private BoardDAO() {

		try {
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			//커넥션 풀 정보
			InitialContext ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//3. 외부에서 객체생성을 요구할 때 멤버변수 dao를 반환합니다.
	public static BoardDAO getInstance() {
		return dao;
	}

	//데이터베이스 연결주소
	String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
	String uid = "jsp";
	String upw = "jsp";

	DataSource ds;

	//글 등록 메서드
	public void regist(String writer, String title, String content) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "INSERT INTO BOARD (BNO, WRITER, TITLE, CONTENT) VALUES (BOARD_SEQ.NEXTVAL, ?, ?, ?)";

		try {
			conn = ds.getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writer);
			pstmt.setString(2, title);
			pstmt.setString(3, content);

			pstmt.executeUpdate(); //실행

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, null);
		}
	}
	//목록 조회 메서드(여러개의 값을 받아야 하기 때문에 List형식을 사용한다.)
	public ArrayList<BoardVO> getList(int pageNum, int amount){
		ArrayList<BoardVO> list = new ArrayList<>(); //셀렉트한 결과를 담을 list

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//BNO역순으로 해야 최신글이 맨 위로 온다.
		String sql = "SELECT *\r\n"
				+ "FROM (SELECT ROWNUM AS RN,\r\n"
				+ "       A.*\r\n"
				+ "      FROM (SELECT * \r\n"
				+ "            FROM BOARD \r\n"
				+ "            ORDER BY BNO DESC) A)\r\n"
				+ "WHERE RN > ? AND RN <= ?"; 

		try {
			conn = ds.getConnection();

			pstmt = conn.prepareStatement(sql);
			
			//(조회하는 페이지 - 1) * 데이터 개수
			pstmt.setInt( 1, (pageNum - 1) * amount );
			pstmt.setInt( 2, pageNum * amount);
			
			rs = pstmt.executeQuery();

			while(rs.next()) {
				//1. 컬럼값을 가지고와서 (rs.getString / rs.getInt / rs.getTimestamp)
				int bno = rs.getInt("bno");
				String writer = rs.getString("writer");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Timestamp regdate = rs.getTimestamp("regdate");
				int hit = rs.getInt("hit");
				//2. VO를 생성 후 값 저장
				BoardVO vo = new BoardVO(bno, writer, title, content, regdate, hit);
				//3. list에 담는다.
				list.add(vo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		return list;
	}
	//전체 게시글 수 계산하기
	public int getTotal() {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT COUNT(*) AS TOTAL FROM BOARD";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt("total");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		return result;
	}
	
	//게시물 내용 보기
	public BoardVO getContent(String bno) {
		BoardVO vo = new BoardVO();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM BOARD WHERE BNO = ?";
		
		try {
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			//bno의 반환은 정수형이지만 DB는 자동형변환을 지원하기 때문에 setString으로 받아도 됀다.
			pstmt.setString(1, bno);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo.setBno(rs.getInt("bno"));
				vo.setWriter(rs.getString("writer"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setRegdate(rs.getTimestamp("regdate"));
				vo.setHit(rs.getInt("hit"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		return vo;
	}
	//글 수정 하기
	public void update(String bno, String title, String content) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE BOARD SET TITLE = ?, CONTENT = ? WHERE BNO = ?";
		
		try {
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, bno);
			
			pstmt.executeUpdate(); //sql실행
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, null);
		}
	}
	//글 삭제하기
	public void delete(String bno) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "DELETE FROM BOARD WHERE BNO = ?";
		
		try {
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bno);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, null);
		}
	}
	//조회수 증가
	public void upHit(String bno) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE BOARD SET HIT = HIT + 1 WHERE BNO = ?";
		
		try {
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bno);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, null);
		}
	}

	
	
	
	
	
	
	
	
	
	















}
