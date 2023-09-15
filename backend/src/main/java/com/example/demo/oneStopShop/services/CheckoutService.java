package com.example.demo.oneStopShop.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.oneStopShop.dtos.GetUserCartProductsDto;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import com.stripe.param.checkout.SessionCreateParams.LineItem;
import com.stripe.param.checkout.SessionCreateParams.Mode;
import com.stripe.param.checkout.SessionCreateParams.PaymentMethodType;

@Service
public class CheckoutService {
	
	@Value(value = "${BASE_URL}")
	private String BASE_URL;
	
	@Value(value = "${STRIPE_SECRET_KEY}")
	private String stripeKey;

	public Session createSession(List<GetUserCartProductsDto> data) throws StripeException {
		
		String successURL = BASE_URL + "checkout?step=4";
		
		String failedURL = BASE_URL + "failed";
		
		Stripe.apiKey = stripeKey;
		
		List<SessionCreateParams.LineItem> sessionItemList = new ArrayList<>();
		
		for(GetUserCartProductsDto item:data) {
			
			sessionItemList.add(createSessionLineItem(item));
			
		}
		
		SessionCreateParams params = SessionCreateParams.builder().addPaymentMethodType(PaymentMethodType.CARD).setMode(Mode.PAYMENT)
					.setCancelUrl(failedURL).setSuccessUrl(successURL).addAllLineItem(sessionItemList).setCurrency("inr").build();
		
		return Session.create(params);
	}

	private LineItem createSessionLineItem(GetUserCartProductsDto item) {
		
		return SessionCreateParams.LineItem.builder()
				.setPriceData(createPriceData(item))
				.setQuantity((long)item.getNumItems())
						.build();
	}

	private SessionCreateParams.LineItem.PriceData createPriceData(GetUserCartProductsDto item) {
		return SessionCreateParams.LineItem.PriceData.builder()
				.setCurrency("inr").setUnitAmount((long) item.getProductDetails().getDiscountedPrice()*100)
				.setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
						.setName(item.getProductDetails().getProductTitle()).build()).build();
	}

}
