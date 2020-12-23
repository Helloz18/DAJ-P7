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

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RuleNameService;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(roles = { "USER", "ADMIN" })
public class RuleNameControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	RuleNameService ruleService;
	
	@Before
	public void initTest() {
		Optional<RuleName> rule = Optional.ofNullable(
				new RuleName("a text","a second text","a third text", "a fourth text", "a fifth text","a sixth text"));
		given(ruleService.getById(1)).willReturn(rule);
		
	}
	
	@Test
	public void testGetAllRuleName() throws Exception {
		mockMvc.perform(get("/ruleName/list")).andExpect(status().isOk());
	}
	
	@Test
	public void testAddRuleNameForm() throws Exception {
		mockMvc.perform(get("/ruleName/add")).andExpect(status().isOk());
	}
	
	@Test
	public void testValidate() throws Exception {
		mockMvc.perform(post("/ruleName/validate"))
			.andExpect(redirectedUrl("/ruleName/list"))
				.andDo(print());
	}
	
	@Test
	public void testGetUpdate() throws Exception {
		mockMvc.perform(get("/ruleName/update/1"))
			.andExpect(status().isOk())
			.andDo(print());    
	}
	
	@Test
	public void testUpdate() throws Exception {
		mockMvc.perform(post("/ruleName/update/1"))
		.andExpect(redirectedUrl("/ruleName/list"))
		.andDo(print());    
	}
	
	
	@Test
	public void testDeleteRuleName() throws Exception {
		mockMvc.perform(get("/ruleName/delete/1"))
			.andExpect(redirectedUrl("/ruleName/list"))
			.andDo(print());    
	}
}
