package rest;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ClickDataDTO {
	protected String device;
	protected String publisherId;
	protected String campaignId;
	protected Timestamp timestamp_sent;
	protected String publisherChannelType;

	public ClickDataDTO() {
		this.device = "";
		this.publisherId = "";
		this.campaignId = "";
		this.timestamp_sent = new Timestamp(1000*3600*24);
		this.publisherChannelType = "";
	}

	public ClickDataDTO(
			String device,
			String publisherId,
			String campaignId,
			Timestamp timestamp_sent,
			String publisherChannelType
			) {
		super();
		this.device = device;
		this.publisherId = publisherId;
		this.campaignId = campaignId;
		this.timestamp_sent = timestamp_sent;
		this.publisherChannelType = publisherChannelType;
	}


	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(String publisherId) {
		this.publisherId = publisherId;
	}

	public String getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(String campaignId) {
		this.campaignId = campaignId;
	}

	public Timestamp getTimestamp_sent() {
		return timestamp_sent;
	}

	public void setTimestamp_sent(Timestamp timestamp_sent) {
		this.timestamp_sent = timestamp_sent;
	}

	public String getPublisherChannelType() {
		return publisherChannelType;
	}

	public void setPublisherChannelType(String publisherChannelType) {
		this.publisherChannelType = publisherChannelType;
	}

}
