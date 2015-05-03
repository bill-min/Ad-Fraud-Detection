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
