package com.cafe24.shoppingmall.controller;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cafe24.shoppingmall.vo.CategoryVo;
import com.cafe24.shoppingmall.vo.UserVo;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminCategoryControllerTest {

	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	

	@Test
	public void getList() throws Exception {

		ResultActions resultActions = 
				mockMvc
				.perform(get("/api/admin/category/list")
				.contentType(MediaType.APPLICATION_JSON)); 
		
		resultActions 
		.andExpect(status().isOk()).andDo(print());
		
	}
	
	
	// ?????? ???????????? ?????? ??????
	@Ignore
	@Test
	public void checkName() throws Exception {
		String cateName = "??????";
		
		ResultActions resultActions = 
				mockMvc
				.perform(get("/api/admin/category/checkName?name={name}", cateName)
				.contentType(MediaType.APPLICATION_JSON)); 
		
		resultActions 
		.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.data", is(false))); 
		
		cateName = "??????";
		
		resultActions = 
				mockMvc
				.perform(get("/api/admin/category/checkName?name={name}", cateName)
						.contentType(MediaType.APPLICATION_JSON)); 
		
		resultActions 
		.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.data", is(true))); 
		
	}
	
	// ?????? ???????????? ?????? ??????
	@Ignore
	@Test
	public void checkLowName() throws Exception {
		// ?????? ??????????????? depth(?????? or ??????)??? ?????? groupNo(?????? ????????????????????? ??????)????????? ????????????
		/*
			select * 
			from category
			where group_no=1
			and depth=2
			and name = "??????";
		*/
		
		// ????????? ??????????????? ??????! ??? ?????? ?????? ??? -> ?????? ??????
		CategoryVo categoryVo = new CategoryVo();
		categoryVo.setName("??????"); 
		categoryVo.setGroupNo(1L); // ????????? ?????? ????????????
		categoryVo.setDepth(2L);   // ????????? ?????? ??????

		
		// ?????? ??????
		ResultActions resultActions = 
				mockMvc
				.perform(get("/api/admin/category/checkLowName")
						.contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(categoryVo)));
		resultActions 
		.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.data", is(false))); 
		
		// ????????? ??????????????? ??????! ??? ?????? ?????? ??? -> ??????!
		categoryVo.setGroupNo(2L); // ?????? ????????????
		categoryVo.setDepth(2L);   // ????????? ?????? ??????
		
		
		// ?????? ??????
		resultActions = 
				mockMvc
				.perform(get("/api/admin/category/checkLowName")
						.contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(categoryVo)));
		resultActions 
		.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.data", is(true))); 

	}
	
	// ?????? ???????????? ??????
	@Ignore
	@Test
	public void addCategory() throws Exception {
		CategoryVo categoryVo = new CategoryVo();
		categoryVo.setName("??????"); 

		ResultActions resultActions = 
				mockMvc
				.perform(post("/api/admin/category/add")
						.contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(categoryVo)));
		resultActions 
		.andExpect(status().isOk()).andDo(print()); 
	}
	
	// ?????? ???????????? ??????
	@Ignore
	@Test
	public void addLowCategory() throws Exception {
		CategoryVo categoryVo = new CategoryVo();
		// ?????? ??????????????? ????????? ??????
		categoryVo.setName("?????????"); 
		categoryVo.setDepth(1L);   // ?????? ???????????? ?????? + 1
		categoryVo.setGroupNo(4L); // ?????? ????????????

		
		// ?????? ??????
		ResultActions resultActions = 
				mockMvc
				.perform(post("/api/admin/category/addLow")
						.contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(categoryVo)));
		resultActions 
		.andExpect(status().isOk()).andDo(print()); 
	}
	
	@Ignore
	@Test
	public void modifyCategory() throws Exception {
		CategoryVo categoryVo = new CategoryVo();
		// ?????? ??????????????? ????????? ??????
		categoryVo.setName("??????!"); 
		categoryVo.setNo(1L);  

		ResultActions resultActions = 
				mockMvc
				.perform(put("/api/admin/category/modify")
				.contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(categoryVo)));
		
		resultActions 
		.andExpect(status().isOk()).andDo(print()); 
	}
	
	@Ignore
	@Test
	public void deleteCategory() throws Exception {
		CategoryVo categoryVo = new CategoryVo();
		categoryVo.setNo(1L);  
		
		ResultActions resultActions = 
				mockMvc
				.perform(delete("/api/admin/category/delete")
				.contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(categoryVo)));
		
		resultActions 
		.andExpect(status().isOk()).andDo(print()); 
	}
	
}
















