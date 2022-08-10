package com.nts.wl9322.web.service.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.wl9322.web.entity.Comment;
import com.nts.wl9322.web.service.CommentService;

//JDBC로 구현된 댓글 서비스 기능 구현
@Service
public class JDBCCommentService implements CommentService{
	
	@Autowired
	private DataSource dataSource;

	//JDBC 기본 정보
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	//댓글 등록
	public int insertComment(Comment comment) {
		int result = 0;
		//id는 시퀀스를 이용하여 1씩 증가
		String sql = "INSERT INTO \"COMMENT\"(id, writer, content, regdate, post_id, password) VALUES(COMMENT_SEQ.NEXTVAL, ?, ?, ?, ?, ?)";

		try {
			Connection con = dataSource.getConnection();
			PreparedStatement prepared_statement = con.prepareStatement(sql);
			//댓글 등록을 위해 정보 입력
			prepared_statement.setString(1, comment.getWriter());
			prepared_statement.setString(2, comment.getContent());
			prepared_statement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			prepared_statement.setInt(4, comment.getPost_id());
			prepared_statement.setString(5, comment.getPassword());

			result = prepared_statement.executeUpdate();

			prepared_statement.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	//특정 게시글의 댓글 수 얻어오기
	public int getCommentCount(int post_id) {
		String sql = "SELECT COUNT(ID) FROM \"COMMENT\" WHERE POST_ID = ?";
		int result = 0;

		try {
			Connection con = dataSource.getConnection();
			PreparedStatement prepared_statement = con.prepareStatement(sql);
			//검색어 설정
			prepared_statement.setInt(1, post_id);
			ResultSet rs = prepared_statement.executeQuery();

			rs.next();
			result = rs.getInt("count(id)");

			prepared_statement.close();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
	
	//전체 댓글 수 얻어오기
	public int getCommentTotalCount() {
		String sql = "SELECT COUNT(ID) FROM \"COMMENT\"";
		int result = 0;

		try {
			Connection con = dataSource.getConnection();
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			rs.next();
			result = rs.getInt("count(id)");

			statement.close();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
	
	//특정 게시글의 모든 원하는 만큼 얻어오기(여기서는 5의 배수로 읽어옴)
	public List<Comment> getComment(int post_id, int page) {
		String sql = "SELECT ROWNUM, C.* FROM (SELECT * FROM \"COMMENT\" WHERE POST_ID = ? ORDER BY REGDATE DESC) C WHERE ROWNUM BETWEEN ? AND ?";
		List<Comment> list = new ArrayList<>();
		//댓글을 늘려갈 수
		int pager = 5;

		try {
			Connection con = dataSource.getConnection();
			PreparedStatement prepared_statement = con.prepareStatement(sql);
			//검색어
			prepared_statement.setInt(1, post_id);
			//1 ~ 현재 상태에서 +5 수만큼 댓글을 더 읽어옴
			prepared_statement.setInt(2, 1);
			prepared_statement.setInt(3, page * pager);
			ResultSet rs = prepared_statement.executeQuery();
			
			//읽어온 댓글들을 리스트에 등록
			while(rs.next()) {
				int id = rs.getInt("id");
				String content = rs.getString("content");
				String writer = rs.getString("writer");
				String password = rs.getString("password");
				Date regdate = rs.getDate("regdate");
				
				list.add(new Comment(id, post_id, content, writer, password, regdate));
			}

			prepared_statement.close();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	//댓글 수정
	public int updateComment(Comment comment) {
		String sql = "UPDATE \"COMMENT\" SET WRITER = ?, CONTENT = ? WHERE ID = ?";
		int result = 0;

		try {
			Connection con = dataSource.getConnection();
			PreparedStatement prepared_statement = con.prepareStatement(sql);
			//업데이트할 댓글 내용 및 작성자 설정
			prepared_statement.setString(1, comment.getWriter());
			prepared_statement.setString(2, comment.getContent());
			//업데이트할 댓글 id 설정
			prepared_statement.setInt(3, comment.getId());

			//댓글 비밀번호 얻어오기
			result = prepared_statement.executeUpdate();

			prepared_statement.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	//댓글 비밀번호 확인
	public boolean checkPassword(int id, String password) {
		String sql = "SELECT PASSWORD FROM \"COMMENT\" WHERE ID = ?";
		boolean result = false;

		try {
			Connection con = dataSource.getConnection();
			PreparedStatement prepared_statement = con.prepareStatement(sql);
			//특정 댓글을 위한 id 설정
			prepared_statement.setInt(1, id);

			//댓글 비밀번호 얻어오기
			ResultSet rs = prepared_statement.executeQuery();
			
			//일치 여부 판단
			if(rs.next()) {
				String encrypt_password = rs.getString("password");
				if(encrypt_password.compareTo(password) == 0) result = true;
			}

			prepared_statement.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
}
