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

import com.nts.wl9322.web.entity.Post;
import com.nts.wl9322.web.entity.PostView;
import com.nts.wl9322.web.service.PostService;

//JDBC로 구현된 게시글 서비스 기능 구현
@Service
public class JDBCPostService implements PostService{
	
	@Autowired
	private DataSource dataSource;
	
	//JDBC 기본 정보
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	//게시글 얻어오기
	public int insertPost(Post post) {
		int result = 0;
		//id는 시퀀스를 이용하여 1씩 증가, 조회수,좋아요,싫어요의 경우 등록 시 0
		String sql = "INSERT INTO POST(id, title, writer, content, regdate, hit, \"LIKE\", unlike, hashtag, password) VALUES(POST_SEQ.NEXTVAL, ?, ?, ?, ?, 0, 0, 0, ?, ?)";
		
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			//게시글 등록을 위해 정보 입력
			preparedStatement.setString(1, post.getTitle());
			preparedStatement.setString(2, post.getWriter());
			preparedStatement.setString(3, post.getContent());
			preparedStatement.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			preparedStatement.setString(5, post.getHashtag());
			preparedStatement.setString(6, post.getPassword());
		
			result = preparedStatement.executeUpdate();
			
			preparedStatement.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public List<PostView> getPostList() {
		return getPostList("TITLE", "", 1);
	}
	
	public List<PostView> getPostList(int page) {
		return getPostList("TITLE", "", page);
	}
	
	public List<PostView> getPostList(String field, String query, int page) {
		//게시글을 10개 씩 묶어서 처리
		final int pager = 10;
		//''를 field에 넣지 않기 위하여 미리 설정
		String sql = "SELECT * FROM (SELECT ROWNUM NUM, P.* FROM (SELECT * FROM POST_VIEW ORDER BY REGDATE DESC) P WHERE " + field + " LIKE ?) WHERE NUM BETWEEN ? AND ?";
		
		List<PostView> list = new ArrayList<>();
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			//검색어
			preparedStatement.setString(1, "%" + query + "%");
			//해당 페이지의 게시글 시작 번호
			preparedStatement.setInt(2, 1 + (page - 1) * pager);
			//해당 페이지의 게시글 끝 번호
			preparedStatement.setInt(3, page * pager);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("ID");
				String title = rs.getString("TITLE"); 
				String writer = rs.getString("WRITER"); 
				Date regdate = rs.getDate("REGDATE");
				int hit = rs.getInt("HIT"); 
				int like = rs.getInt("LIKE");
				int cmt_count = 0;
				
				PostView post = new PostView(id, title, writer, regdate, hit, like, cmt_count);
				list.add(post);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public int getPostCount() {
		String sql = "SELECT COUNT(ID) FROM POST";
		int result = 0;
		
		try {
			Connection con = dataSource.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			rs.next();
			result = rs.getInt("count(id)");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	
}
