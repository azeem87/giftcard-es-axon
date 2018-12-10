package com.giftcard.query.listener;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class IssuedEvt implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -435719124148593579L;
	private final String id;
    private final Integer amount;

}
