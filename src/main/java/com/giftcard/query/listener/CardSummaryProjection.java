package com.giftcard.query.listener;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.AllowReplay;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.ReplayStatus;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.giftcard.query.CardSummary;
import com.giftcard.query.FetchAllCardsQuery;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
@RequiredArgsConstructor
@Profile("query")
@ProcessingGroup("summaryGroup")
public class CardSummaryProjection {

	@Autowired
	private final EntityManager entityManager;

	@EventHandler
	@AllowReplay(false)
	public void on(IssuedEvt event, ReplayStatus replayStatus) {
		
		if(replayStatus.isReplay())
			return;
		
		log.trace("projecting {}", event);
		/*
		 * Update our read model by inserting the new card. This is done so that
		 * upcoming regular (non-subscription) queries get correct data.
		 */
		entityManager.persist(new CardSummary(event.getId(), event.getAmount(), event.getAmount()));

	}

	@EventHandler
	@AllowReplay(false)
	public void on(RedeemedEvt event) {
		log.trace("projecting {}", event);
		/*
		 * Update our read model by updating the existing card. This is done so that
		 * upcoming regular (non-subscription) queries get correct data.
		 */
		CardSummary summary = entityManager.find(CardSummary.class, event.getId());
		summary.setRemainingAmount(summary.getRemainingAmount() - event.getAmount());
	}

	@QueryHandler
	public List<CardSummary> handle(FetchAllCardsQuery query) {
		log.trace("handling {}", query);
		TypedQuery<CardSummary> jpaQuery = entityManager.createNamedQuery("CardSummary.fetchAll", CardSummary.class);
		return jpaQuery.getResultList();
	}

}