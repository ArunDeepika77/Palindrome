package com.product.quotation.service.impl;

import org.springframework.data.domain.Sort;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.quotation.entity.OutputEntity;
import com.product.quotation.entity.Palindrome;
import com.product.quotation.entity.ProductDetails;
import com.product.quotation.entity.QuotationDetails;
import com.product.quotation.repository.PalindromeDetailsRepository;
import com.product.quotation.repository.ProductDetailsRepository;
import com.product.quotation.repository.QuotationDetailsRepository;
import com.product.quotation.service.ProductService;



@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	QuotationDetailsRepository quotationRepo;

	@Autowired
	ProductDetailsRepository productRepo;

	@Autowired
	PalindromeDetailsRepository palindromeRepo;


	List<QuotationDetails> quotationDetailsList = new ArrayList<>();

	@Override
	public List<OutputEntity> getQuotationForToday() {
		List<OutputEntity> outputList = new ArrayList<>();
		try{
			quotationDetailsList = quotationRepo.getQuotationByToday();
		}catch(NullPointerException message){
			throw new NullPointerException("Record Not Found");
		}
		outputList = this.queryDetails(quotationDetailsList);
		return outputList;
	}

	@Override
	public List<OutputEntity> getQuotationForYesterday() {
		List<OutputEntity> outputList = new ArrayList<>();
		try{
			quotationDetailsList = quotationRepo.getQuotationByYesterday();
		}catch(NullPointerException message){
			throw new NullPointerException("Record Not Found");
		}
		outputList = this.queryDetails(quotationDetailsList);
		return outputList;
	}

	@Override
	public List<OutputEntity> getQuotationForWeek() {
		List<OutputEntity> outputList = new ArrayList<>();
		try{
			quotationDetailsList = quotationRepo.getQuotationByThisWeek();
		}catch(NullPointerException message){
			throw new NullPointerException("Record Not Found");
		}
		outputList = this.queryDetails(quotationDetailsList);
		return outputList;
	}

	@Override
	public List<OutputEntity> getQuotationForMonth() {
		List<OutputEntity> outputList = new ArrayList<>();
		try{
			quotationDetailsList = quotationRepo.getQuotationByThisMonth();
		}catch(NullPointerException message){
			throw new NullPointerException("Record Not Found");
		}
		outputList = this.queryDetails(quotationDetailsList);
		return outputList;
	}
	private List<OutputEntity> queryDetails(List<QuotationDetails> quotationDetailsList){
		List<OutputEntity> outputList = new ArrayList<>();
		quotationDetailsList.stream().forEach(data -> {
			OutputEntity quotationEntity = new OutputEntity();
			quotationEntity.setQuotationName(data.getQuotationName());
			quotationEntity.setQuotationNo(data.getQuotationNo());
			quotationEntity.setQuotationDate(data.getQuotationDate());
			quotationEntity.setCustomer(data.getCustomer());
			quotationEntity.setTotal(data.getTotal());
			outputList.add(quotationEntity);
		});
		return outputList;
	}

	@Override
	public List<OutputEntity> getQuotationListBySelectedDate(String dateValue)throws ParseException {
		List<OutputEntity> outputList = new ArrayList<>();
		DateFormat inputFormat = new SimpleDateFormat(
				"E MMM dd yyyy HH:mm:ss 'GMT'z", Locale.ENGLISH);
		Date date =  inputFormat.parse(dateValue);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		String mysqlDateString = formatter.format(date);
		try{
			quotationDetailsList = quotationRepo.getQuotationListBySelectedDate(mysqlDateString);
		}catch(NullPointerException message){
			throw new NullPointerException("Record Not Found");
		}
		quotationDetailsList.stream().forEach(data -> {
			OutputEntity quotationEntity = new OutputEntity();
			quotationEntity.setQuotationName(data.getQuotationName());
			quotationEntity.setQuotationNo(data.getQuotationNo());
			quotationEntity.setQuotationDate(data.getQuotationDate());
			quotationEntity.setCustomer(data.getCustomer());
			outputList.add(quotationEntity);
		});
		return outputList;
	}

	@Override
	public ProductDetails addProduct(ProductDetails productDetails) {

		// TODO Auto-generated method stub
		return productRepo.save(productDetails);
	}

	@Override
	public List<Palindrome> getPalindrome() {
		return palindromeRepo.findAll(Sort.by(Sort.Direction.ASC, "id"));

	}

	@Override
	public String addPalindrome(Palindrome palindromeDetails) {
		String keyword = palindromeDetails.getList();
		List<Palindrome> palindromeDetailsList = new ArrayList<>();
		palindromeDetailsList = palindromeRepo.getPalindrome(keyword);
		String strRev = new StringBuffer(keyword).reverse().toString();
		if(palindromeDetailsList.isEmpty()){
			if(keyword.equalsIgnoreCase(strRev)){
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