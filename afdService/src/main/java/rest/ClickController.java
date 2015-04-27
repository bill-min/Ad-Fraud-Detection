package rest;

import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import util.Util;

@RestController
@RequestMapping("/clicks")
public class ClickController {

	private static Logger log = Logger.getLogger("MoniterController");
	private static ClickDataManager clickdataMgr = new ClickDataManager();
	
	@RequestMapping(method = RequestMethod.GET)
	public ClickDataDTO getInformation(
			@RequestParam(value = "id", defaultValue = "-1") long tiId) {
		return new ClickDataDTO();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody boolean addInformation(HttpServletRequest request,
			 @RequestBody ClickDataDTO ti) {
		String ipAddress = Util.getClientIP(request);
		Timestamp timestamp_received = new Timestamp(new Date().getTime());
		//Referer id, this value can be changed by front end script
		String refererid = request.getHeader("Referer");
		//TODO validation
		if(ti.getDevice().length() > 10 || ti.getCampaignId().length() > 50
				|| ti.getPublisherChannelType().length() > 10 
				|| ti.getPublisherId().length() > 50){
			return false;
		}
		clickdataMgr.addClickData(
				ipAddress, 
				ti.device, 
				ti.publisherId, 
				ti.campaignId, 
				ti.timestamp_sent, 
				timestamp_received, 
				ti.publisherChannelType, 
				refererid);
		log.info(
				"ip=" + ipAddress
				+ ", device=" + ti.getDevice()
				+ ", publisher id=" + ti.getPublisherId()
				+ ", campaign id=" + ti.getCampaignId()
				+ ", timestamp of sending="+ti.getTimestamp_sent()
				+ ", timestamp of receiving="+timestamp_received
				+ ", publisher channel type="+ti.getPublisherChannelType()
				);
		
		return true;
	}
}
