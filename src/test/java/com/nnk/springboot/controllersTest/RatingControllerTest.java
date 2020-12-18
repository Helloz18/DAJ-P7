package com.nnk.springboot.controllersTest;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingService;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(roles = { "USER", "ADMIN" })
public class RatingControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	RatingService ratingService;
	
	@Before
	public void initTest() {
		Optional<Rating> rating = Optional.ofNullable(new Rating("a text","a second text","a third text", 1));
		given(ratingService.getById(1)).willReturn(rating);
		
	}
	
	@Test
	public void testGetAllRating() throws Exception {
		mockMvc.perform(get("/rating/list")).andExpect(status().isOk());
	}
	
	@Test
	public void testAddRatingForm() throws Exception {
		mockMvc.perform(get("/rating/add")).andExpect(status().isOk());
	}
	
	@Test
	public void testValidate() throws Exception {
		mockMvc.perform(post("/rating/validate"))
			.andExpect(redirectedUrl("/rating/list"))
				.andDo(print());
	}
	
	@Test
	public void testGetUpdate() throws Exception {
		mockMvc.perform(get("/rating/update/1"))
			.andExpect(status().isOk())
			.andDo(print());    
	}
	
	@Test
	public void testUpdate() throws Exception {
		mockMvc.perform(post("/rating/update/1"))
		.andExpect(redirectedUrl("/rating/list"))
		.andDo(print());    
	}
	
	@Test
	public void testDeleteRating() throws Exception {
		mockMvc.perform(get("/rating/delete/1"))
			.andExpect(redirectedUrl("/rating/list"))
			.andDo(print());    
	}
}
