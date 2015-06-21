package util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

public class Util {

	public static String getClientIP(HttpServletRequest httpservletrequest) {
		if (httpservletrequest == null)
			return null;
		String s = httpservletrequest.getHeader("X-Forwarded-For");
		if (s == null || s.length() == 0 || "unknown".equalsIgnoreCase(s))
			s = httpservletrequest.getHeader("Proxy-Client-IP");
		if (s == null || s.length() == 0 || "unknown".equalsIgnoreCase(s))
			s = httpservletrequest.getHeader("WL-Proxy-Client-IP");
		if (s == null || s.length() == 0 || "unknown".equalsIgnoreCase(s))
			s = httpservletrequest.getHeader("HTTP_CLIENT_IP");
		if (s == null || s.length() == 0 || "unknown".equalsIgnoreCase(s))
			s = httpservletrequest.getHeader("HTTP_X_FORWARDED_FOR");
		if (s == null || s.length() == 0 || "unknown".equalsIgnoreCase(s))
			s = httpservletrequest.getRemoteAddr();
		if ("127.0.0.1".equals(s) || "0:0:0:0:0:0:0:1".equals(s))
			try {
				s = InetAddress.getLocalHost().getHostAddress();
			} catch (UnknownHostException unknownhostexception) {
			}
		return s;
	}

	public static String getRandomIP() {
		int b1 = randInt(0, 255);
		int b2 = randInt(0, 255);
		int b3 = randInt(0, 255);
		int b4 = randInt(0, 255);

		StringBuilder sb = null;

		String ip1 = Integer.toString(b1);
		String ip2 = Integer.toString(b2);
		String ip3 = Integer.toString(b3);
		String ip4 = Integer.toString(b4);

		// Now the IP is b1.b2.b3.b4
		sb = new StringBuilder();
		sb.append(ip1).append(".").append(ip2).append(".").append(ip3)
				.append(".").append(ip4);
		// System.out.println(sb);

		return sb.toString();
	}

	public static int randInt(int min, int max) {

		// NOTE: Usually this should be a field rather than a method
		// variable so that it is not re-seeded every call.
		Random rand = new Random();

		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}
}
