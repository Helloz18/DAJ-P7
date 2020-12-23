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

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.TradeService;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(roles = { "USER", "ADMIN" })
public class TradeControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	TradeService tradeService;
	
	@Before
	public void initTest() {
		Optional<Trade> trade = Optional.ofNullable(new Trade("an account", "a type", 1.5));
		given(tradeService.getById(1)).willReturn(trade);
		
	}
	
	@Test
	public void testGetAllTrade() throws Exception {
		mockMvc.perform(get("/trade/list")).andExpect(status().isOk());
	}
	
	@Test
	public void testAddTradeForm() throws Exception {
		mockMvc.perform(get("/trade/add")).andExpect(status().isOk());
	}
	
	@Test
	public void testValidate() throws Exception {
		mockMvc.perform(post("/trade/validate"))
				.andExpect(status().isOk())
				.andExpect(view().name("trade/add"))
				.andDo(print());
	}
	
	@Test
	public void testValidateWithoutError() throws Exception {
		mockMvc.perform(post("/trade/validate"))
		    .andExpect(status().isOk())
		    .andExpect(model().attributeHasErrors("trade"));
	}
	
	@Test
	public void testGetUpdate() throws Exception {
		mockMvc.perform(get("/trade/update/1"))
			.andExpect(status().isOk())
			.andDo(print());    
	}
	
	@Test
	public void testUpdate() throws Exception {
		mockMvc.perform(post("/trade/update/1"))
			.andExpect(status().isOk())
			.andDo(print());    
	}
	
	@Test
	public void testDeleteBid() throws Exception {
		mockMvc.perform(get("/trade/delete/1"))
			.andExpect(redirectedUrl("/trade/list"))
			.andDo(print());    
	}
	
}
