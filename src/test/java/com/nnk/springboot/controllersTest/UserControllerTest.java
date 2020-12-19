package com.nnk.springboot.controllersTest;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.UserService;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(roles = { "USER", "ADMIN" })
public class UserControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	UserService userService;
	
	@Before
	public void initTest() {
		Optional<User> user = Optional.ofNullable(new User("username","1Az*test","fullname","ROLE_USER",true));
		given(userService.getById(1)).willReturn(user);		
	}
	
	@Test
	public void testGetAllUser() throws Exception {
		mockMvc.perform(get("/user/list")).andExpect(status().isOk());
	}
	
	@Test
	public void testAddUserForm() throws Exception {
		mockMvc.perform(get("/user/add")).andExpect(status().isOk());
	}
	
	@Test
	public void testValidate() throws Exception {
		mockMvc.perform(post("/user/validate"))
				.andExpect(status().isOk())
				.andExpect(view().name("user/add"))
				.andDo(print());
	}
	
	@Test
	public void testValidateWithError() throws Exception {
		mockMvc.perform(post("/user/validate"))
		    .andExpect(status().isOk())
		    .andExpect(model().attributeHasErrors("user"));
	}
	
	@Test
	public void testGetUpdate() throws Exception {
		mockMvc.perform(get("/user/update/1"))
			.andExpect(status().isOk())
			.andDo(print());    
	}
	
	@Test
	public void testUpdate() throws Exception {
		mockMvc.perform(post("/user/update/1"))
			.andExpect(status().isOk())
			.andDo(print());    
	}
	
	@Test
	public void testDeleteBid() throws Exception {
		mockMvc.perform(get("/user/delete/1"))
			.andExpect(redirectedUrl("/user/list"))
			.andDo(print());    
	}
	
}
