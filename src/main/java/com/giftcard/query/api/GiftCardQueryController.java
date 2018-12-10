package com.giftcard.query.api;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.giftcard.query.CardSummary;
import com.giftcard.query.CardSummaryDataProvider;

@RestController
@Profile("query")
public class GiftCardQueryController {

	@Autowired
	private CardSummaryDataProvider cardSummaryDataProvider;

	// QUERY PART
	@GetMapping("/cards")
	public CompletableFuture<List<CardSummary>> getAllCards() {
		return cardSummaryDataProvider.getAllCards();
	}

}
