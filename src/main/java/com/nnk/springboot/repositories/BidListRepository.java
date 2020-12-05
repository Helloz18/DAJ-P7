package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.BidList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BidListRepository extends JpaRepository<BidList, Integer> {

//	@Query("SELECT b FROM BidList b WHERE b.bidListId = ?1")
//	public BidList getByBidListId(int bidListId);
}
