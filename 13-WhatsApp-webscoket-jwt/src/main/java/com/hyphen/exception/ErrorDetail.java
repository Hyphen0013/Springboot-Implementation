package com.hyphen.exception;

import java.time.LocalDateTime;

public class ErrorDetail {

	private String error;
	private String message;
	private LocalDateTime timeStaTime;

	public ErrorDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ErrorDetail(String error, String message, LocalDateTime timeStaTime) {
		super();
		this.error = error;
		this.message = message;
		this.timeStaTime = timeStaTime;
	}

}
