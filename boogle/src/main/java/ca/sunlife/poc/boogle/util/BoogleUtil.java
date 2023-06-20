package ca.sunlife.poc.boogle.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import ca.sunlife.poc.boogle.constants.Constants;
import ca.sunlife.poc.boogle.response.ErrorResponse;

public class BoogleUtil {

	public static ErrorResponse mapErrorResponse(String message, String code) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(message);
		errorResponse.setCode(code);
		return errorResponse;
	}

	public static int calculateStartRow(int pageNumber, int pageSize) {
		if (pageNumber <= 0) {
			pageNumber = 1;
		}
		int startRow = (pageNumber - 1) * pageSize;
		return startRow;
	}

	public static String parseHtml(String text) {
		String rawContent = StringUtils.EMPTY;
		if (!StringUtils.isEmpty(text)) {
			Document doc = Jsoup.parse(text);
			rawContent = doc.text();
		}
		return rawContent;
	}

	public static String getLastValue(String value, String delimiter) {
		String lastValue = StringUtils.EMPTY;
		if (!StringUtils.isEmpty(value)) {
			String[] parts = value.split(delimiter);
			if (parts.length > 0) {
				lastValue = parts[parts.length - 1];
			}
		}
		return lastValue;
	}

	public static String createUrl(String firstParameter, String secondParameter) {
		return firstParameter + secondParameter;
	}

	public static String formatDate(String date) {
		String formattedDate = "";
		if (!StringUtils.isEmpty(date)) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.LAST_MODIFIED_DATE_FORMAT);
			DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");

			LocalDateTime parsedDateTime = LocalDateTime.parse(date, formatter);
			formattedDate = parsedDateTime.format(outputFormatter);
		}
		return formattedDate;
	}

	public static String decodeValue(String value) {
		try {
			return URLDecoder.decode(value, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException ex) {
			throw new RuntimeException(ex.getCause());
		}
	}

}
