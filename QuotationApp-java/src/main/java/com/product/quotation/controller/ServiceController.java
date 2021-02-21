package com.product.quotation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.product.quotation.entity.Palindrome;
import com.product.quotation.service.ProductService;

@RestController
@RequestMapping("/quotation/product/quotation")
public class ServiceController {
	
	@Autowired
	private ProductService service;
	
	@RequestMapping(path="/getPalindrome",method = RequestMethod.GET)
	@CrossOrigin(origins="http://localhost:4200",allowedHeaders = {})
	public List<Palindrome> getPalindrome() {
		return service.getPalindrome();
	}

	@RequestMapping(path="/addPalindrome",produces="application/json",method = RequestMethod.POST)
	@ResponseBody
	public String addPalindrome(@RequestBody Palindrome palindromeDetails){
		return service.addPalindrome(palindromeDetails);	
	}
}
