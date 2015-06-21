package rest;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import util.Util;

@RestController
@RequestMapping("/clicks")
public class ClickController {

	private static Logger log = Logger.getLogger("MoniterController");
	private static ClickDataManager clickdataMgr = new ClickDataManager();
	
	private MessageSource messageSource;
	 
    @Autowired
    public ClickController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
 
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorDTO processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
 
        return processFieldErrors(fieldErrors);
    }
 
    private ValidationErrorDTO processFieldErrors(List<FieldError> fieldErrors) {
        ValidationErrorDTO dto = new ValidationErrorDTO();
 
        for (FieldError fieldError: fieldErrors) {
            String localizedErrorMessage = resolveLocalizedErrorMessage(fieldError);
            dto.addFieldError(fieldError.getField(), localizedErrorMessage);
        }
 
        return dto;
    }
 
    private String resolveLocalizedErrorMessage(FieldError fieldError) {
        Locale currentLocale =  LocaleContextHolder.getLocale();
        String localizedErrorMessage = messageSource.getMessage(fieldError, currentLocale);
 
        //If the message was not found, return the most accurate field error code instead.
        //You can remove this check if you prefer to get the default error message.
        if (localizedErrorMessage.equals(fieldError.getDefaultMessage())) {
            String[] fieldErrorCodes = fieldError.getCodes();
            localizedErrorMessage = fieldErrorCodes[0];
        }
 
        return localizedErrorMessage;
    }
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ClickDataDTO getInformation(
			@RequestParam(value = "id", defaultValue = "-1") long tiId) {
		return new ClickDataDTO();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody boolean addInformation(HttpServletRequest request,
			@Valid @RequestBody ClickDataDTO ti) {
//		String ipAddress = Util.getClientIP(request);
		//for test
		String ipAddress = Util.getRandomIP();
		Timestamp timestamp_received = new Timestamp(new Date().getTime());
		//Referer id, this value can be changed by front end script
		String refererid = request.getHeader("Referer");
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
