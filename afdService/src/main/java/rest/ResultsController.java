package rest;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/datas")
public class ResultsController {

	private static ClickDataManager clickdataMgr = new ClickDataManager();
	
	@RequestMapping(method = RequestMethod.GET)
	public List<ClickData> getInformation(
			@RequestParam(value = "id", defaultValue = "-1") long tiId) {
		return clickdataMgr.getAll();
	}
}
