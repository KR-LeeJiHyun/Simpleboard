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
import com.nts.wl9322.web.service.CommentService;
import com.nts.wl9322.web.service.PostService;

//JDBC로 구현된 게시글 서비스 기능 구현
@Service
public class JDBCPostService implements PostService{

	@Autowired
	private DataSource dataSource;
	@Autowired
	private CommentService jdbcCommentService;

	//JDBC 기본 정보
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	//게시글 등록하기
	public int insertPost(Post post) {
		int result = 0;
		//id는 시퀀스를 이용하여 1씩 증가, 조회수,좋아요,싫어요의 경우 등록 시 0
		String sql = "INSERT INTO POST(id, title, writer, content, regdate, hit, \"LIKE\", unlike, hashtag, password) VALUES(POST_SEQ.NEXTVAL, ?, ?, ?, ?, 0, 0, 0, ?, ?)";

		try {
			Connection con = dataSource.getConnection();
			PreparedStatement prepared_statement = con.prepareStatement(sql);
			//게시글 등록을 위해 정보 입력
			prepared_statement.setString(1, post.getTitle());
			prepared_statement.setString(2, post.getWriter());
			prepared_statement.setString(3, post.getContent());
			prepared_statement.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			prepared_statement.setString(5, post.getHashtag());
			prepared_statement.setString(6, post.getPassword());

			result = prepared_statement.executeUpdate();

			prepared_statement.close();
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
			PreparedStatement prepared_statement = con.prepareStatement(sql);
			//검색어
			prepared_statement.setString(1, "%" + query + "%");
			//해당 페이지의 게시글 시작 번호
			prepared_statement.setInt(2, 1 + (page - 1) * pager);
			//해당 페이지의 게시글 끝 번호
			prepared_statement.setInt(3, page * pager);
			ResultSet rs = prepared_statement.executeQuery();
			
			//읽어온 게시글들을 리스트에 등록
			while(rs.next()) {
				int id = rs.getInt("ID");
				String title = rs.getString("TITLE"); 
				String writer = rs.getString("WRITER"); 
				Date regdate = rs.getDate("REGDATE");
				int hit = rs.getInt("HIT"); 
				int like = rs.getInt("LIKE");
				int cmt_count = jdbcCommentService.getCommentCount(id);

				PostView post = new PostView(id, title, writer, regdate, hit, like, cmt_count);
				list.add(post);
			}

			prepared_statement.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}
	
	//아무것도 들어오지 않았을 시에 게시글 수
	public int getPostCount() {
		return getPostCount("TITLE", "");
	}
	
	//검색어에 맞는 게시글 수
	public int getPostCount(String field, String query) {
		String sql = "SELECT COUNT(ID) FROM (SELECT P.* FROM (SELECT * FROM POST_VIEW ORDER BY REGDATE DESC) P WHERE " + field + " LIKE ?)";
		int result = 0;

		try {
			Connection con = dataSource.getConnection();
			PreparedStatement prepared_statement = con.prepareStatement(sql);
			//검색어
			prepared_statement.setString(1, "%" + query + "%");
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

	//특정 게시글 얻어오기
	public Post getPost(int id) {
		String sql = "SELECT * FROM POST WHERE ID = ?";
		String update_sql = "UPDATE POST SET HIT = ? WHERE ID = ?";
		Post post = null;
		int result = 0;

		try {
			Connection con = dataSource.getConnection();
			PreparedStatement prepared_statement = con.prepareStatement(sql);
			PreparedStatement update_prepared_statement = con.prepareStatement(update_sql);
			prepared_statement.setInt(1, id);

			//게시글 얻어오기
			ResultSet rs = prepared_statement.executeQuery();

			if(rs.next()) {
				String title = rs.getString("title");
				String writer = rs.getString("writer"); 
				String content = rs.getString("content"); 
				Date regdate = rs.getDate("regdate"); 
				int hit = rs.getInt("hit");
				int like = rs.getInt("like");
				int unlike = rs.getInt("unlike");
				String hashtag = rs.getString("hashtag");

				post = new Post(id, title, writer, content, regdate, hit + 1, like, unlike, hashtag, "");
			}

			//조회수 업데이트
			update_prepared_statement.setInt(1, post.getHit());
			update_prepared_statement.setInt(2, id);
			result = update_prepared_statement.executeUpdate();

			prepared_statement.close();
			update_prepared_statement.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return post;
	}

	//특정 게시글 좋아요 업데이트
	public Post likePost(int id) {
		String sql = "SELECT * FROM POST WHERE ID = ?";
		String update_sql = "UPDATE POST SET \"LIKE\" = ? WHERE ID = ?";
		Post post = null;
		int result = 0;

		try {
			Connection con = dataSource.getConnection();
			PreparedStatement prepared_statement = con.prepareStatement(sql);
			PreparedStatement update_prepared_statement = con.prepareStatement(update_sql);
			prepared_statement.setInt(1, id);

			//게시글 얻어오기
			ResultSet rs = prepared_statement.executeQuery();

			if(rs.next()) {
				String title = rs.getString("title");
				String writer = rs.getString("writer"); 
				String content = rs.getString("content"); 
				Date regdate = rs.getDate("regdate"); 
				int hit = rs.getInt("hit");
				int like = rs.getInt("like");
				int unlike = rs.getInt("unlike");
				String hashtag = rs.getString("hashtag");

				post = new Post(id, title, writer, content, regdate, hit, like + 1, unlike, hashtag, "");
			}

			//좋아요 업데이트
			update_prepared_statement.setInt(1, post.getLike());
			update_prepared_statement.setInt(2, id);
			result = update_prepared_statement.executeUpdate();

			prepared_statement.close();
			update_prepared_statement.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return post;
	}
	
	//특정 게시글 싫어요 업데이트
	public Post unlikePost(int id) {
		String sql = "SELECT * FROM POST WHERE ID = ?";
		String update_sql = "UPDATE POST SET UNLIKE = ? WHERE ID = ?";
		Post post = null;
		int result = 0;

		try {
			Connection con = dataSource.getConnection();
			PreparedStatement prepared_statement = con.prepareStatement(sql);
			PreparedStatement update_prepared_statement = con.prepareStatement(update_sql);
			prepared_statement.setInt(1, id);

			//게시글 얻어오기
			ResultSet rs = prepared_statement.executeQuery();

			if(rs.next()) {
				String title = rs.getString("title");
				String writer = rs.getString("writer"); 
				String content = rs.getString("content"); 
				Date regdate = rs.getDate("regdate"); 
				int hit = rs.getInt("hit");
				int like = rs.getInt("like");
				int unlike = rs.getInt("unlike");
				String hashtag = rs.getString("hashtag");

				post = new Post(id, title, writer, content, regdate, hit, like, unlike + 1, hashtag, "");
			}

			//싫어요 업데이트
			update_prepared_statement.setInt(1, post.getUnlike());
			update_prepared_statement.setInt(2, id);
			result = update_prepared_statement.executeUpdate();

			prepared_statement.close();
			update_prepared_statement.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return post;
	}
	
	//게시글 비밀번호 확인
	public boolean checkPassword(int id, String password) {
		String sql = "SELECT PASSWORD FROM POST WHERE ID = ?";
		boolean result = false;

		try {
			Connection con = dataSource.getConnection();
			PreparedStatement prepared_statement = con.prepareStatement(sql);
			prepared_statement.setInt(1, id);

			//게시글 비밀번호 얻어오기
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
	
	//특정 게시글 수정
	public int updatePost(int id, String title, String writer, String content, String hashtag) {
		String sql = "UPDATE POST SET TITLE=?, WRITER=?, CONTENT=?, HASHTAG=? WHERE ID=?";
		int result = 0;

		try {
			Connection con = dataSource.getConnection();
			PreparedStatement prepared_statement = con.prepareStatement(sql);
			//특정 게시글 업데이할 제목, 작성자, 내용, 해시태그 설정
			prepared_statement.setString(1, title);
			prepared_statement.setString(2, writer);
			prepared_statement.setString(3, content);
			prepared_statement.setString(4, hashtag);
			//업데이트할 게시글 id 설정
			prepared_statement.setInt(5, id);

			result = prepared_statement.executeUpdate();

			prepared_statement.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
	
	//특정 게시글 삭제
	public int deletePost(int id) {
		String sql = "DELETE FROM POST WHERE ID=?";
		int result = 0;

		try {
			Connection con = dataSource.getConnection();
			PreparedStatement prepared_statement = con.prepareStatement(sql);
			//삭제할 게시글 id 설정
			prepared_statement.setInt(1, id);

			result = prepared_statement.executeUpdate();

			prepared_statement.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

}
