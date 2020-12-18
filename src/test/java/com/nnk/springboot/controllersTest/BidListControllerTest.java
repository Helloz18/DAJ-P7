package com.nnk.springboot.controllersTest;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.validation.BindingResult;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(roles = { "USER", "ADMIN" })
public class BidListControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	BidListService bidService;
	
	@Before
	public void initTest() {
		Optional<BidList> bid = Optional.ofNullable(new BidList("an account", "a type", 1.5));
		given(bidService.getById(1)).willReturn(bid);
		
	}
	
	@Test
	public void testGetAllBidList() throws Exception {
		mockMvc.perform(get("/bidList/list")).andExpect(status().isOk());
	}
	
	@Test
	public void testAddBidForm() throws Exception {
		mockMvc.perform(get("/bidList/add")).andExpect(status().isOk());
	}
	
	@Test
	public void testValidate() throws Exception {
		mockMvc.perform(post("/bidList/validate"))
				.andExpect(status().isOk())
				.andExpect(view().name("bidList/add"))
				.andDo(print());
	}
	
	@Test
	public void testValidateWithError() throws Exception {
		mockMvc.perform(post("/bidList/validate"))
		    .andExpect(status().isOk())
		    .andExpect(model().attributeHasErrors("bidList"));
	}
	
	@Test
	public void testGetUpdate() throws Exception {
		mockMvc.perform(get("/bidList/update/1"))
			.andExpect(status().isOk())
			.andDo(print());    
	}
	
	@Test
	public void testUpdate() throws Exception {
		mockMvc.perform(post("/bidList/update/1"))
			.andExpect(status().isOk())
			.andDo(print());    
	}
	
	@Test
	public void testDeleteBid() throws Exception {
		mockMvc.perform(get("/bidList/delete/1"))
			.andExpect(redirectedUrl("/bidList/list"))
			.andDo(print());    
	}
//	@Test
//	public void testValidateWithBid() throws Exception {
//		//GIVEN
//		BidList bid = new BidList();
//		bid.setAccount("an account");
//		bid.setType("a type");
//		bid.setBidQuantity(1.5);
//		bid.setBidListId(1);
//		
//		given(bidService.saveBid(bid)).willReturn(bid);
//		List<BidList> list = new ArrayList<BidList>();
//		list.add(bid);
//		given(bidService.getAllBidList()).willReturn((list));
//         
//		MvcResult mvcResult = mockMvc.perform(post("/bidList/validate")).andExpect(status().isOk()).andReturn();
//			
//		
//		// THEN
//		mockMvc.perform(post("/bidList/validate"))
//				.andExpect(status().isOk())
//				.andExpect(view().name("bidList/list"))
//				.andDo(print());
//	}
	
//	@Test
//	public void testGetHome() throws Exception {
//		mockMvc.perform(get("/"))
//		.andDo(print())//permet d'afficher la réponse dans la console
//        .andExpect(status().isOk())//vérifie le status = 200
//        .andExpect(view().name("home")) //vérifie le nom de la vue qui est appelée
//        .andExpect(content().string(containsString("Laurent"))); // vérifie qu'il y a bien une valeur qui contient Laurent
//	}
	
//	mockMvc.perform(post(YOUR_URL).contentType(APPLICATION_FORM_URLENCODED)
//		    .param(...)
//		    .param(...)
//		    .andExpect(model().hasErrors())
//		    .andExpect(model().attributeHasFieldErrors(FORM_MODEL_NAME, "field"))
//		    .andExpect(model().attributeHasFieldErrorCode(FORM_MODEL_NAME, "anotherfield", "error")
//		    .andExpect(view().name(YOUR_VIEW));
	
//    @Test
//    public void givenAUser_whenRequestIsMadeToAdd_thenASolutionSouldBeShown() throws Exception {
//        when(calculator.add(2,3)).thenReturn(5);
//        mockMvc.perform(post("/calculator")
//        .param("leftArgument", "2")
//        .param("rightArgument", "3")
//        .param("calculationType", "ADDITION")
//        ).andExpect(status().is2xxSuccessful()).
//                andExpect(content().string(containsString("id=\"solution\""))).
//                andExpect(content().string(containsString(">5</span>")));
//
//
//        verify(calculator).add(2, 3);
//        verify(solutionFormatter).format(5);
//    }
	
//	The following test asserts that binding or validation failed:
//
//		mockMvc.perform(post("/persons"))
//		    .andExpect(status().isOk())
//		    .andExpect(model().attributeHasErrors("person"));
}
