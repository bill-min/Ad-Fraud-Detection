package rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import util.GeoIP;


@ComponentScan
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args){
    	//TODO configured by Spring
    	GeoIP.loadDB("/GeoLite2-City.mmdb");
        SpringApplication.run(Application.class, args);
    }
}
