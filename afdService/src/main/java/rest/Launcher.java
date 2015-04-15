package rest;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

import util.GeoIP;

public class Launcher extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		//TODO Configured by Spring
		String path = this.getClass().getClassLoader().getResource("/").getPath()+"GeoLite2-City.mmdb";
		GeoIP.loadDB(path);
		application.sources(Application.class);
		return super.configure(application);
	}

}
