package rest;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

@Entity
@Table(name="clickdata")
public class ClickData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	@Column(name="id")
	protected long id;
	@Column(name="ip_address")
	protected String ipAddress;
	@Column(name="device")
	protected String device;
	@Column(name="id_publisher")
	protected String publisherId;
	@Column(name="id_campaign")
	protected String campaignId;
	@Column(name="country")
	protected String country;
	@Column(name="city")
	protected String city;
	@Column(name="timestamp_sent")
	protected Timestamp timestamp_sent;
	@Column(name="timestamp_received")
	protected Timestamp timestamp_received;
	@Column(name="publisher_channel_type")
	protected String publisherChannelType;
	@Column(name="id_referrer")
	protected String referrerId;

	public ClickData() {
		this.id = -1;
		this.ipAddress = "";
		this.device = "";
		this.publisherId = "";
		this.campaignId = "";
		this.country = "";
		this.timestamp_sent = new Timestamp(1000*3600*24);
		this.timestamp_received = new Timestamp(1000*3600*24);
		this.publisherChannelType = "";
		this.referrerId = "";
	}

	public ClickData(long id, String ipAddress, String device,
			String publisherId, String campaignId, String country,
			Timestamp timestamp_sent, Timestamp timestamp_received, String publisherChannelType,
			String referrerId) {
		super();
		this.id = id;
		this.ipAddress = ipAddress;
		this.device = device;
		this.publisherId = publisherId;
		this.campaignId = campaignId;
		this.country = country;
		this.timestamp_sent = timestamp_sent;
		this.timestamp_received = timestamp_received;
		this.publisherChannelType = publisherChannelType;
		this.referrerId = referrerId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public Timestamp getTimestamp_sent() {
		return timestamp_sent;
	}

	public void setTimestamp_sent(Timestamp timestamp_sent) {
		this.timestamp_sent = timestamp_sent;
	}

	public Timestamp getTimestamp_received() {
		return timestamp_received;
	}

	public void setTimestamp_received(Timestamp timestamp_received) {
		this.timestamp_received = timestamp_received;
	}

	public String getPublisherChannelType() {
		return publisherChannelType;
	}

	public void setPublisherChannelType(String publisherChannelType) {
		this.publisherChannelType = publisherChannelType;
	}

	public String getReferrerId() {
		return referrerId;
	}

	public void setReferrerId(String referrerId) {
		this.referrerId = referrerId;
	}

	@Override
	public String toString() {
		return " {'id':'" + id + "', 'ipAddress':'" + ipAddress
				+ "', 'device':'" + device + "', 'publisherId':'" + publisherId
				+ "', 'campaignId':'" + campaignId + "', 'country':'" + country
				+ "', 'city':'" + city + "', 'timestamp_sent':'"
				+ timestamp_sent + "', 'timestamp_received':'"
				+ timestamp_received + "', 'publisherChannelType':'"
				+ publisherChannelType + "', 'referrerId':'" + referrerId
				+ "'}";
	}

	

	

	

	

	

	

}
