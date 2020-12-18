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

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurvePointService;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(roles = { "USER", "ADMIN" })
public class CurveControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	CurvePointService curveService;
	
	@Before
	public void initTest() {
		Optional<CurvePoint> curve = Optional.ofNullable(new CurvePoint(1, 1.5, 1.5));
		given(curveService.getById(1)).willReturn(curve);
		
	}
	
	@Test
	public void testGetAllCurvePoint() throws Exception {
		mockMvc.perform(get("/curvePoint/list")).andExpect(status().isOk());
	}
	
	@Test
	public void testAddCurveForm() throws Exception {
		mockMvc.perform(get("/curvePoint/add")).andExpect(status().isOk());
	}
	
	@Test
	public void testValidate() throws Exception {
		mockMvc.perform(post("/curvePoint/validate"))
				.andExpect(status().isOk())
				.andExpect(view().name("curvePoint/add"))
				.andDo(print());
	}
	
	@Test
	public void testValidateWithError() throws Exception {
		mockMvc.perform(post("/curvePoint/validate"))
		    .andExpect(status().isOk())
		    .andExpect(model().attributeHasErrors("curvePoint"));
	}
	
	@Test
	public void testGetUpdate() throws Exception {
		mockMvc.perform(get("/curvePoint/update/1"))
			.andExpect(status().isOk())
			.andDo(print());    
	}
	
	@Test
	public void testUpdate() throws Exception {
		mockMvc.perform(post("/curvePoint/update/1"))
			.andExpect(status().isOk())
			.andDo(print());    
	}
	
	@Test
	public void testDeleteCurvePoint() throws Exception {
		mockMvc.perform(get("/curvePoint/delete/1"))
			.andExpect(redirectedUrl("/curvePoint/list"))
			.andDo(print());    
	}
}
