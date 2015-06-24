package rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import util.AppConfig;

@RestController
public class ModeController {

    @RequestMapping("/mode")
    public String setStatusOfWS(@RequestParam(value="realworld", defaultValue="true") String realworldFlag) {
        boolean bStatus = true;
        
    	if(realworldFlag.equals("false")) {
        	bStatus = false;
        }
    	
    	AppConfig.setRealworldFlag(bStatus);
    	
    	return "Switch status to "+(AppConfig.isRealworldFlag()?"real world":"exhibition");
    }
    
}