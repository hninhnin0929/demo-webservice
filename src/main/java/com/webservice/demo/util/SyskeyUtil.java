package com.webservice.demo.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

public class SyskeyUtil {
	private static int count = 1;

	public static long getSyskey() {
		Calendar cal = Calendar.getInstance();
		Integer r = new Random().nextInt(99 - 1) + 1;
		count = count >=99999 ? 1 : count+1;
		String syseky = getPrefixSyskey(cal.getTime()) + (r.toString().length() == 1 ? ("0" + r) : r) + "" + formatNumber();
		return Long.parseLong(syseky);


	}

	public static String getPrefixSyskey(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		return sdf.format(date);
	}

	private static String formatNumber() {
		DecimalFormat df = new DecimalFormat("00000");
		return df.format(count);
	}
}
