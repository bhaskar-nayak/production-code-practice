package com.production.exceptions;

public class ResourceAlreadyExistedException extends RuntimeException{
	public ResourceAlreadyExistedException(String message) {
		super(message);
	}
}
