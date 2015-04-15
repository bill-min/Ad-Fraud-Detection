package rest;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import util.GeoIP;

public class ClickDataManager {

	private static SessionFactory factory = null;

	private SessionFactory createSessionFactory() {
		Configuration configuration = null;
		ServiceRegistry serviceRegistry = null;
		try {
			configuration = new Configuration().configure();
			serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		if (configuration == null)
			return null;
		else
			return configuration.buildSessionFactory(serviceRegistry);
	}

	public ClickDataManager() {
		if (factory == null)
			factory = createSessionFactory();
	}

	/**
	 * @param ipAddress
	 * The IP address of sender
	 * @param device
	 * Device type of sender: PC/Mobile phone
	 * @param publisherId
	 * Id of publisher (such as CNN, ABC)
	 * @param campaignId
	 * Represent who pay the advertisement and what’s the campaign, for example: pepsi_2 
	 * @param timestamp_sent
	 * When does the data sent from front end
	 * @param publisherChannelType
	 * Channel type of publisher, this may be one of app/email/web
	 * @param refererid
	 * Refers to previous page of this http request. 
	 * This may be search engine or publisher’s web page.
	 * @return Integer
	 * Primary key of new record
	 * */
	public Long addClickData(
			String ipAddress, 
			String device, 
			String publisherId, 			
			String campaignId, 
			Timestamp timestamp_sent,
			Timestamp timestamp_received,
			String publisherChannelType, 
			String refererid) {
		if(factory == null) factory = createSessionFactory();
		Session session = factory.openSession();
		Transaction trans = null;
		Long id_data = null;
		try{
			trans = session.beginTransaction();
			ClickData click = new ClickData();
			click.setIpAddress(ipAddress);
			click.setDevice(device);
			click.setPublisherId(publisherId);
			click.setCampaignId(campaignId);
			click.setTimestamp_sent(timestamp_sent);
			click.setPublisherChannelType(publisherChannelType);
			click.setReferrerId(refererid);
			click.setTimestamp_received(timestamp_received);
			//IP
			GeoIP.setIP(ipAddress);
			click.setCountry(GeoIP.getCountryCode());
			click.setCity(GeoIP.getCity());
			id_data = (Long)session.save(click);
			trans.commit();
		}catch(HibernateException e){
			if(trans!=null) trans.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return id_data;
	}
	
	@SuppressWarnings("unchecked")
	public List<ClickData> getAll(){
		if(factory == null) factory = createSessionFactory();
		Session session = factory.openSession();
		Transaction trans = null;
		List<ClickData> datas = null;
		try{
			trans = session.beginTransaction();
			datas = (List<ClickData>)session.createQuery("from ClickData").list();
			trans.commit();
		}catch(HibernateException e){
			if(trans!=null) trans.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return datas;
	}
}
