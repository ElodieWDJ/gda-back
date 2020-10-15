package dev.domain.dto;

public class ErrorRequestException extends Exception {
	public ErrorRequestException(String message) {
		super(message);
	}
}
