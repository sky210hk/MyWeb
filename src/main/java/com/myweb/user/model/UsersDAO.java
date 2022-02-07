package com.myweb.user.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.myweb.util.JdbcUtil;

public class UsersDAO {

	//1. 스스로 객체를 생성하고 1개로 제한
	static UsersDAO dao = new UsersDAO();

	//2. 생성자에 private를 붙인다. (다른 데서 쓸 수 없도록 하기 위해)
	private UsersDAO() {

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
	public static UsersDAO getInstance() {
		return dao;
	}

	//데이터베이스 연결주소
	String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
	String uid = "jsp";
	String upw = "jsp";
	
	DataSource ds;

	//아이디 중복검사메서드
	public int idCheck(String id) {
		int result = 0;

		String sql = "SELECT * FROM USERS WHERE ID = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			//conn = DriverManager.getConnection(url, uid, upw);
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			if(rs.next()) { //true
				result = 1; //id가 있는 경우(중복)
			} else { //false
				result = 0; //id가 없는 경우(중복x)
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs); //미리 만들어둔 finally메서드로 간단히 쓴다.
		}

		return result;
	}
	//회원가입
	public int insert(UsersVO vo) {
		int result = 0;

		String sql = "INSERT INTO USERS (ID, PW, NAME, EMAIL, ADDRESS) VALUES (?, ?, ?, ?, ?)";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DriverManager.getConnection(url, uid, upw);

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getEmail());
			pstmt.setString(5, vo.getAddress());

			result = pstmt.executeUpdate();

		} catch (Exception e) {

		} finally {
			JdbcUtil.close(conn, pstmt, null);
		}

		return result;
	}
	//로그인
	// 2. 반환유형 UsersVO, login메서드에 (id, pw)를 매개변수로 넘깁니다.
	// id, pw기반으로 로그인 검증해서 결과가 있다면, UsersVO에 select결과를 저장합니다.
	// 없다면 null을 반환합니다.
	public UsersVO login(String id, String pw) {
		UsersVO vo = null;

		String sql = "SELECT * FROM USERS WHERE ID = ? AND PW = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(url, uid, upw);

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);

			rs = pstmt.executeQuery();

			if(rs.next()) {
				String id2 = rs.getString("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String address = rs.getString("address");
				Timestamp regdate = rs.getTimestamp("regdate");

				vo = new UsersVO(id2, null, name, email, address, regdate);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		return vo;
	}
	//회원정보수정
	//2. DAO에 update메서드를 생성하고, 업데이트 구문을 수행합니다. 
	//성공, 실패여부를 1 또는 0으로 받아오세요
	public int update(UsersVO vo) {
		int result = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "UPDATE USERS SET PW = ?, NAME = ?, EMAIL = ?, ADDRESS =? WHERE ID = ?";

		try {
			conn = DriverManager.getConnection(url, uid, upw);

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getPw());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getAddress());
			pstmt.setString(5, vo.getId());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, null);
		}
		return result;
	}
	//회원정보삭제
	//2. login() 가 null을 반환하면 "현재 비밀번호를 확인하세요" 출력 뒤로가기
    //login() 가 값을 가진다면 delete()메서드를 호출해서 삭제를 진행하면 됩니다.
	public int delete(String id) {
		int result = 0;
		
		String sql = "DELETE FROM USERS WHERE ID = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DriverManager.getConnection(url, uid, upw);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, null);
		}
		return result;
	}











}







