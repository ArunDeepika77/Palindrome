package com.product.quotation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.product.quotation.entity.Palindrome;


@Repository
public interface PalindromeDetailsRepository extends JpaRepository<Palindrome, Integer>{

	@Query(value="select * from palindrome where list = ?1",nativeQuery = true)
	List<Palindrome> getPalindrome(String palindrome);


}
