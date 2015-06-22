package util;

import java.util.Random;

public class Exhibition {

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
	
	private static String[] devicesStrings = {"Desktop","Tablet","Mobile","Vehicle","SmartTV","IPTV"};
	private static String[] publisherStrings = {"Pepsi","IBM","Microsoft","Baidu","Google","AceOnion","Tencent","Amazon","Facebook"};
	private static String[] campaignIdStrings = {"2015Fall","2016Spring","2016Fall","2016Winter","2017Spring"};
	private static String[] publisherChannelTypeStrings = {"NewYorkTimes","CNN","WSJ","YahooNews","Washington Post"};
	public static String getRandomDevice() {
		return devicesStrings[randInt(0, devicesStrings.length)];
	}
	
	public static String getRandomPublisher() {
		return publisherStrings[randInt(0, publisherStrings.length)];
	}
	
	public static String getRandomCampaign() {
		return campaignIdStrings[randInt(0, campaignIdStrings.length)];
	}
	
	public static String getRandomPublisherChannelType() {
		return publisherChannelTypeStrings[randInt(0, publisherChannelTypeStrings.length)];
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
