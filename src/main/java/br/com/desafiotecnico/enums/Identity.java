package br.com.desafiotecnico.enums;

import lombok.Getter;

public enum Identity {
	SALESMAN("001"),
	CUSTOMER("002"),
	SALES("003");

	@Getter 
	String identity;

	private Identity(String identity) {
		this.identity = identity;
	}
}
