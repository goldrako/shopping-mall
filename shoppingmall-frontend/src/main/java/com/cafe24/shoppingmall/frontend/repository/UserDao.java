package com.cafe24.shoppingmall.frontend.repository;

import org.springframework.stereotype.Repository;

import com.cafe24.shoppingmall.frontend.vo.UserVo;


@Repository
public class UserDao {

//	@Autowired
//	private SqlSession sqlSession;
	
	public UserVo get(String id) {
	    UserVo vo = new UserVo();
	    vo.setName("이정은");
	    vo.setNo(1L);
	    vo.setEmail("aaa");
	    vo.setPassword("1234");
	    vo.setRole("ROLE_USER");
	    return vo;
	}
	
	public UserVo get(Long no){
		return null;
	}
	
	public UserVo get(String email, String password) {
		return null;
	}	
	
	public Boolean insert(UserVo vo) {
		return false;
	}
	
	public int update( UserVo userVo ) {
		return 0;
	}	
}