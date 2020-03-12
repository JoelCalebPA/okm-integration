package com.domain.util;

import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FormatUtil {

	private static Logger log = LoggerFactory.getLogger(FormatUtil.class);

	public static String formatArray(String[] values) {
		if (values != null) {
			if (values.length == 1) {
				return values[0];
			}
			return ArrayUtils.toString(values);
		}

		return "NULL";
	}

	public static String formatObject(Object value) {
		if (value != null) {
			if (value instanceof Object[]) {
				return ArrayUtils.toString(value);
			}
			return value.toString();
		}

		return "NULL";
	}

}
