package com.nts.wl9322.web.service.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.wl9322.web.entity.Post;
import com.nts.wl9322.web.service.PostService;

//게시글을 위해 인터페이스를 상속받아 JDBC로 기능을 구현할 서비스
@Service
public class JDBCPostService implements PostService{
	
	@Autowired
	private DataSource dataSource;
	
	//JDBC 설정정보
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	//게시글 등록
	public int insertPost(Post post) {
		int result = 0;
		String sql = "INSERT INTO POST(id, title, writer, content, regdate, hit, \"LIKE\", unlike, hashtag, password) VALUES(POST_SEQ.NEXTVAL, ?, ?, ?, ?, 0, 0, 0, ?, ?)";
		
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement = con.prepareStatement(sql);
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
}
