package util;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;

public class GeoIP {

	private static Logger log = Logger.getLogger("GeoIP");

	private static CityResponse response = null;

	private static DatabaseReader reader = null;
	
	private static String EXCEPTION="UNKNOWN";

	public static String generateIPAddress(int p1, int p2, int p3) {

	    StringBuilder sb = null;

	    int b1 = (p1 >> 24) & 0xff;
	    int b2 = (p2 >> 16) & 0xff;
	    int b3 = (p3 >>  8) & 0xff;
	    int b4 = 0;

	    String ip1 = Integer.toString(b1);
	    String ip2 = Integer.toString(b2);
	    String ip3 = Integer.toString(b3);
	    String ip4 = Integer.toString(b4);

	    //Now the IP is b1.b2.b3.b4
	    sb = new StringBuilder();
	    sb.append(ip1).append(".").append(ip2).append(".").append(ip3).append(".").append(ip4);
	    // System.out.println(sb);

	    return sb.toString();

	}
	
	public static boolean loadDB(String path) {
		try {
			if (reader != null)
				reader.close();
			reader = new DatabaseReader.Builder(new File(path)).build();
		} catch (IOException e) {
			log.log(Level.SEVERE, "CITY DB NOT FOUND");
			return false;
		}
		return true;
	}

	public static boolean setIP(String IP) {
		try {
			InetAddress ipa = InetAddress.getByName(IP);
			if(ipa != null)
				response = reader.city(ipa);
			else
				response = null;
		} catch (UnknownHostException e) {
			log.log(Level.INFO, IP + " IS NOT A IP ADDRESS.");
			return false;
		} catch (GeoIp2Exception e) {
			log.log(Level.INFO, IP + " GENERATE A GEOIP EXCEPTION.");
			return false;
		} catch (IOException e) {
			log.log(Level.INFO, IP + " GENERATE A IO EXCEPTION.");
			return false;
		}
		return true;
	}

	public static String getCountryCode() {
		if (response == null)
			return EXCEPTION;
		return response.getCountry().getIsoCode();
	}

	public static String getCountryName() {
		if (response == null)
			return EXCEPTION;
		return response.getCountry().getName();
	}

	public static String getPostal() {
		if (response == null)
			return EXCEPTION;
		return response.getPostal().getCode();
	}

	public static String getCity() {
		if (response == null)
			return EXCEPTION;
		return response.getCity().getName();
	}

	public static String getLatitudeLongitude() {
		if (response == null)
			return EXCEPTION;
		return response.getLocation().getLatitude() + ","
				+ response.getLocation().getLongitude();
	}
}
