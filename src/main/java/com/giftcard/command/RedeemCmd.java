package com.giftcard.command;


import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class RedeemCmd {
	
	@TargetAggregateIdentifier
	@NonNull
    private final String id;
	@NonNull
    private final Integer amount;

}
