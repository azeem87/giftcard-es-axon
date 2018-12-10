package com.giftcard.query;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@NoArgsConstructor
@Setter
@ToString
@EqualsAndHashCode
@Entity
@NamedQueries({
		@NamedQuery(name = "CardSummary.fetch", query = "SELECT c FROM CardSummary c WHERE c.id LIKE CONCAT(:idStartsWith, '%') ORDER BY c.id"),
		@NamedQuery(name = "CardSummary.count", query = "SELECT COUNT(c) FROM CardSummary c WHERE c.id LIKE CONCAT(:idStartsWith, '%')"),
		@NamedQuery(name = "CardSummary.fetchAll", query = "SELECT c FROM CardSummary c")})
public class CardSummary implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7063698958809200934L;

	@Id
	private  String id;
	
	private  Integer initialAmount;
	private  Integer remainingAmount;

	public CardSummary(String id, Integer initialAmount, int remainingAmount) {
		this.id = id;
		this.initialAmount = initialAmount;
		this.remainingAmount = remainingAmount;
	}

	public CardSummary deductAmount(Integer toBeDeducted) {
		return new CardSummary(this.id, this.initialAmount, this.remainingAmount - toBeDeducted);
	}

}