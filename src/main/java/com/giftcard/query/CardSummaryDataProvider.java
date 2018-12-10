package com.giftcard.query;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CardSummaryDataProvider {
	
	@Autowired
	private QueryGateway queryGateway;

	public CompletableFuture<List<CardSummary>> getAllCards() {
		FetchAllCardsQuery query = new FetchAllCardsQuery();
		return queryGateway.query(query, ResponseTypes.multipleInstancesOf(CardSummary.class));
	}

}
