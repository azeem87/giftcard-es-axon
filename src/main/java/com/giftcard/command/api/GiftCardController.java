package com.giftcard.command.api;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.giftcard.command.IssueCmd;
import com.giftcard.command.RedeemCmd;
import com.giftcard.query.CardSummary;
import com.giftcard.query.CardSummaryDataProvider;

@RestController
public class GiftCardController {

	@Autowired
	CommandGateway commandGateway;

	@Autowired
	QueryGateway queryGateway;

	@Autowired
	private CardSummaryDataProvider cardSummaryDataProvider;

	// COMMMAND PART
	@GetMapping("/card/{id}/issue/{amount}/command")
	public CompletableFuture<String> issueCard(@PathVariable String id, @PathVariable String amount) {
		return commandGateway.send(new IssueCmd(id, Integer.parseInt(amount)));
	}

	@GetMapping("/card/{id}/redeem/{amount}/command")
	public CompletableFuture<String> reedeemCard(@PathVariable String id, @PathVariable String amount) {
		return commandGateway.send(new RedeemCmd(id, Integer.parseInt(amount)));
	}

	// QUERY PART
	@GetMapping("/cards")
	public CompletableFuture<List<CardSummary>> getAllCards() {
		return cardSummaryDataProvider.getAllCards();
	}

}
