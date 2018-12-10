package com.giftcard.command;


import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class IssueCmd {

	@TargetAggregateIdentifier
	@NonNull
	private  String id;
	
	@NonNull
	private  Integer amount;
	
}
