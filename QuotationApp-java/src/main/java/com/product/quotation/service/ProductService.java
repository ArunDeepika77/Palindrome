package com.product.quotation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.product.quotation.entity.Palindrome;

@Service
public interface ProductService {

	List<Palindrome> getPalindrome(); 
	
	String addPalindrome(Palindrome palindromeDetails);

	List<Palindrome> getPalindrome(String keyword);


}
