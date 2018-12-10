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
public class RedeemedEvt implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1421957026785621851L;
	private final String id;
    private final Integer amount;

}
