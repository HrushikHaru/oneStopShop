package com.example.demo.oneStopShop.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.oneStopShop.dtos.GetAllProductsDto;
import com.example.demo.oneStopShop.dtos.PostProductDetailsDto;
import com.example.demo.oneStopShop.responses.PostProductDetailsResponse;
import com.example.demo.oneStopShop.responses.ProductImgAddedResponse;
import com.example.demo.oneStopShop.services.ProductsService;

@RestController
@RequestMapping(path = "/admin")
public class AdminAddProductController {

	@Value(value = "${project.products}")
	private String imageValue;
	
	private ProductsService productsServ;
	
	public AdminAddProductController(ProductsService productsServ) {
		this.productsServ = productsServ;
	}
	
	@PostMapping(path = "/getProductImage")
	public ResponseEntity<ProductImgAddedResponse> getProductImg(@RequestParam MultipartFile productImg) {
		ProductImgAddedResponse response = null;
		try {
			String imgName = productsServ.saveProductImg(productImg, imageValue);
			
			response = new ProductImgAddedResponse();
			response.setImgName(imgName);
			response.setAddedOn(new Date());
			
			return new ResponseEntity<>(response,HttpStatus.CREATED);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping(path = "/postProductDetails")
	public ResponseEntity<PostProductDetailsResponse> postProductDetails(@RequestBody PostProductDetailsDto detailsDto) {
		PostProductDetailsResponse response = new PostProductDetailsResponse();
		
		try {
			productsServ.postProductDetails(detailsDto);
			response.setMessage("The Product has been added Successfully");
			response.setAddedOn(new Date());
			
			return new ResponseEntity<>(response,HttpStatus.CREATED);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		response.setMessage("The Product wasn't added successfully");
		response.setAddedOn(new Date());
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping(path = "/getProductDetails") 
	public ResponseEntity<List<GetAllProductsDto>> getAllProducts(){
		List<GetAllProductsDto> allProducts = null;
		
		try {
			allProducts = productsServ.getAllProducts(imageValue);
			
			return new ResponseEntity<List<GetAllProductsDto>>(allProducts, HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<List<GetAllProductsDto>>(allProducts,HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping(path = "/getProductDetails/{id}")
	public ResponseEntity<GetAllProductsDto> getParticularProductDetail(@PathVariable int id){
		GetAllProductsDto product = null;
		
		try {
			product = productsServ.getParticularProduct(id, imageValue);
			
			return new ResponseEntity<>(product,HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<GetAllProductsDto>(product,HttpStatus.BAD_REQUEST);
	}
	
}
