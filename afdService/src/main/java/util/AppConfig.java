package util;

public class AppConfig {
	private static boolean realworldFlag = false; //real world
	
	private AppConfig(){
		
	}

	public static boolean isRealworldFlag() {
		return realworldFlag;
	}

	public static void setRealworldFlag(boolean realworldFlag) {
		AppConfig.realworldFlag = realworldFlag;
	}

	
}
