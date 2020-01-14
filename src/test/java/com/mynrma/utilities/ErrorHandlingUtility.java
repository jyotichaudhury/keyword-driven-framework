package com.mynrma.utilities;

public class ErrorHandlingUtility {

	private String sErrorMessage;

	private String sTruncatedError;

	private int iErrorTruncatedLimit;

	public ErrorHandlingUtility(String sErrorMessage) {

		this.sErrorMessage = sErrorMessage;

	}

	public String truncateErrorMessage() {

		if(sErrorMessage.contains("(Session info:")) {

			iErrorTruncatedLimit = sErrorMessage.indexOf("(Session info:");

			sTruncatedError = sErrorMessage.substring(0, iErrorTruncatedLimit-1);
		}

		else

			sTruncatedError = sErrorMessage;

		return sTruncatedError;
	}

}
