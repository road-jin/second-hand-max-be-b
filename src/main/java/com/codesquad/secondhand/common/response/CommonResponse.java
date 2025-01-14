package com.codesquad.secondhand.common.response;

import org.springframework.http.HttpStatus;

import com.codesquad.secondhand.common.exception.CustomException;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CommonResponse<T> {

	private final int code;
	private final String status;
	private final String message;
	private final T data;

	public static <T> CommonResponse createOK(T data, ResponseMessage responseMessage) {
		return new CommonResponse(
			HttpStatus.OK.value(),
			HttpStatus.OK.getReasonPhrase(),
			responseMessage.getMessage(),
			data
		);
	}

	public static <T> CommonResponse createOK(ResponseMessage responseMessage) {
		return createOK(null, responseMessage);
	}

	public static <T> CommonResponse createCreated(T data, ResponseMessage responseMessage) {
		return new CommonResponse(
			HttpStatus.CREATED.value(),
			HttpStatus.CREATED.getReasonPhrase(),
			responseMessage.getMessage(),
			data
		);
	}

	public static <T> CommonResponse createCreated(ResponseMessage responseMessage) {
		return createCreated(null, responseMessage);
	}

	public static <T> CommonResponse createNotFound(ResponseMessage responseMessage) {
		return new CommonResponse(
			HttpStatus.NOT_FOUND.value(),
			HttpStatus.NOT_FOUND.getReasonPhrase(),
			responseMessage.getMessage(),
			null
		);
	}

	public static <T> CommonResponse createCustomError(CustomException e) {
		return new CommonResponse(
			e.getCode(),
			e.getStatus(),
			e.getMessage(),
			null
		);
	}

	public static <T> CommonResponse createBadRequest(String message) {
		return new CommonResponse(
			HttpStatus.BAD_REQUEST.value(),
			HttpStatus.BAD_REQUEST.getReasonPhrase(),
			message,
			null
		);
	}

	public static <T> CommonResponse createInternalServer(Exception e) {
		return new CommonResponse(
			HttpStatus.INTERNAL_SERVER_ERROR.value(),
			HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
			e.getMessage(),
			null
		);
	}
}
