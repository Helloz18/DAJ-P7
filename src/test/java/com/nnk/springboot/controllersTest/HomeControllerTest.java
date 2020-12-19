package com.nnk.springboot.controllersTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(roles = { "USER", "ADMIN" })
public class HomeControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void getHome() throws Exception {
		mockMvc.perform(get("/")).andExpect(status().isOk());
	}
	
	@Test
	public void getAdminHome() throws Exception {
		mockMvc.perform(get("/admin/home"))
		.andExpect(redirectedUrl("/bidList/list"))
		.andDo(print());    
	}
	
	@Test
	public void getForbidden() throws Exception {
		mockMvc.perform(get("/forbidden"))
		.andExpect(view().name("403"))
		.andDo(print());    
	}
	
}
