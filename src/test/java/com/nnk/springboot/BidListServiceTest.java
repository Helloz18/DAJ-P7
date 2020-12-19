//package com.nnk.springboot;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.Assert.assertEquals;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.nnk.springboot.domain.BidList;
//import com.nnk.springboot.repositories.BidListRepository;
//import com.nnk.springboot.services.BidListService;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
//public class BidListServiceTest {
//
//	@Autowired
//	BidListService bidListService;
//	
//	@MockBean
//	BidListRepository bidListRepo;
//	
//	@Before
//	public void setup() {
//		BidList bid1 = new BidList("test", "test0", 456.0);
//		List<BidList> bidList = new ArrayList<BidList>();
//		bidList.add(bid1);	
//		Mockito.when(bidListRepo.findAll()).thenReturn(bidList);		
//		
//		Optional<BidList> bid = Optional.ofNullable(new BidList("Account", "Type", 123.0));
//		Mockito.when(bidListRepo.findById(1)).thenReturn(bid);
//		
//	}
//	
//	@Test
//	public void when_getAllBidList_then_bidIsReturn() {
//		Iterable<BidList> allBid = bidListService.getAllBidList();
//		assertEquals(true, allBid.iterator().hasNext());
//	}
//	
//	@Test //??
//	public void when_saveBidList_then_bidIsSaved() {
//		BidList bid2 = new BidList("test2", "test1", 789.0);
//		bidListService.saveBid(bid2);
//		assertThat(bidListRepo.findAll().contains(bid2));
//	}
//	
//	@Test
//	public void when_deleteBidList_then_bidIsDeleted() {
//		System.out.println(bidListRepo.findAll().get(0).getAccount());
//		BidList bid = bidListRepo.findById(1).orElseThrow(() -> new IllegalArgumentException("Invalid bidList"));
//		bidListService.delete(bid);
//		assertEquals(null,bidListRepo.findAll());
//	}
//}
