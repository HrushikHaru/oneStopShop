package com.example.demo.oneStopShop.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.oneStopShop.dtos.GetUserCartProductsDto;
import com.example.demo.oneStopShop.services.CheckoutService;
import com.stripe.exception.StripeException;

import com.stripe.model.checkout.Session;
import com.example.demo.oneStopShop.responses.StripeResponse;

@RestController
@RequestMapping("/admin")
public class CheckoutControllerStripeAPI {
	
	private final CheckoutService checkoutServ;
	
	public CheckoutControllerStripeAPI(CheckoutService checkoutServ) {
		this.checkoutServ = checkoutServ;
	}
	
	@PostMapping(path = "/checkout")
	public ResponseEntity<StripeResponse> stripeCreateRequest(@RequestBody List<GetUserCartProductsDto> data) throws StripeException {

		Session session = checkoutServ.createSession(data);
		
		StripeResponse stripeResponse = new StripeResponse(session.getId());
	    
		return new ResponseEntity<StripeResponse>(stripeResponse,HttpStatus.OK);
	}

}
