package model;

import java.sql.Timestamp;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "tweeter")
@SessionScoped
public class Tweeter {
	
	private String tweetBody;
	
	public String getTweetBody() {
		return tweetBody;
	}
	public void setTweetBody(String tweetBody) {
		this.tweetBody = tweetBody;
	}
	
	public TweerterEntity getEntity()
	{
		TweerterEntity tweeterentity = new TweerterEntity();
		
		Date date= new Date();

		 long time = date.getTime();
	     System.out.println("Time in Milliseconds: " + time);
	     
	     Timestamp ts = new Timestamp(time);
		 System.out.println("Message :" + tweetBody);
		tweeterentity.setTweetBody(tweetBody);
		tweeterentity.setTimeStamp(ts);
		return tweeterentity;
	}
}

	