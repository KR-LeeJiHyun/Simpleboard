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
	
	public int insertComment(Comment comment) {
		int result = 0;
		//id는 시퀀스를 이용하여 1씩 증가, 조회수,좋아요,싫어요의 경우 등록 시 0
		String sql = "INSERT INTO \"COMMENT\"(id, writer, content, regdate, post_id, password) VALUES(COMMENT_SEQ.NEXTVAL, ?, ?, ?, ?, ?)";

		try {
			Connection con = dataSource.getConnection();
			PreparedStatement prepared_statement = con.prepareStatement(sql);
			//게시글 등록을 위해 정보 입력
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
	
	public int getCommentCount(int post_id) {
		String sql = "SELECT COUNT(ID) FROM \"COMMENT\" WHERE POST_ID = ?";
		int result = 0;

		try {
			Connection con = dataSource.getConnection();
			PreparedStatement prepared_statement = con.prepareStatement(sql);
			//검색어
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
	
	@Override
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
	
	public List<Comment> getComment(int post_id, int page) {
		String sql = "SELECT ROWNUM, C.* FROM (SELECT * FROM \"COMMENT\" WHERE POST_ID = ? ORDER BY REGDATE DESC) C WHERE ROWNUM BETWEEN ? AND ?";
		List<Comment> list = new ArrayList<>();
		int pager = 5;

		try {
			Connection con = dataSource.getConnection();
			PreparedStatement prepared_statement = con.prepareStatement(sql);
			//검색어
			prepared_statement.setInt(1, post_id);
			prepared_statement.setInt(2, 1);
			prepared_statement.setInt(3, page * pager);
			ResultSet rs = prepared_statement.executeQuery();
			
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
	
	public int updateComment(Comment comment) {
		String sql = "UPDATE \"COMMENT\" SET WRITER = ?, CONTENT = ? WHERE ID = ?";
		int result = 0;

		try {
			Connection con = dataSource.getConnection();
			PreparedStatement prepared_statement = con.prepareStatement(sql);
			prepared_statement.setString(1, comment.getWriter());
			prepared_statement.setString(2, comment.getContent());
			prepared_statement.setInt(3, comment.getId());

			//게시글 비밀번호 얻어오기
			result = prepared_statement.executeUpdate();

			prepared_statement.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public boolean checkPassword(int id, String password) {
		String sql = "SELECT PASSWORD FROM \"COMMENT\" WHERE ID = ?";
		boolean result = false;

		try {
			Connection con = dataSource.getConnection();
			PreparedStatement prepared_statement = con.prepareStatement(sql);
			prepared_statement.setInt(1, id);

			//댓글 비밀번호 얻어오기
			ResultSet rs = prepared_statement.executeQuery();

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
