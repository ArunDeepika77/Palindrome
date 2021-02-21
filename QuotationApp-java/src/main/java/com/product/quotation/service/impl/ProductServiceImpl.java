package com.product.quotation.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.product.quotation.entity.Palindrome;
import com.product.quotation.repository.PalindromeDetailsRepository;
import com.product.quotation.service.ProductService;



@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	PalindromeDetailsRepository palindromeRepo;


	@Override
	public List<Palindrome> getPalindrome() {
		return palindromeRepo.findAll(Sort.by(Sort.Direction.ASC, "id"));

	}

	@Override
	public String addPalindrome(Palindrome palindromeDetails) {
		String keyword = palindromeDetails.getList();
		List<Palindrome> palindromeDetailsList = new ArrayList<>();
		palindromeDetailsList = palindromeRepo.getPalindrome(keyword);
		char[] stringInChar = keyword.toCharArray();
		String reverseInput = "";
		// reading char by char from end of array till first and forming a string
		for(int i= stringInChar.length - 1;i>=0;i--) {
			reverseInput = reverseInput + stringInChar[i];
		}
		if(palindromeDetailsList.isEmpty()){
			if(keyword.equalsIgnoreCase(reverseInput)){
				palindromeRepo.save(palindromeDetails);
				return "Input Text is Palindrome and saved"; 
			}else{
				return "Input Text is not Palindrome";
			}
		}else{
			return"Input Text already exists";
		}
	}

	@Override
	public List<Palindrome> getPalindrome(String keyword) {
		// TODO Auto-generated method stub
		return 	palindromeRepo.getPalindrome(keyword);

	}

}