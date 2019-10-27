package controller;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import model.Tweeter;
import service.tweeterEJB;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import model.TweerterEntity;;

@ManagedBean(name = "tweetcontroller")
@SessionScoped

@Path("/mytweets")
public class TweeterController {
	
	@EJB
	tweeterEJB tweeterservice;
	
	@ManagedProperty(value = "#{tweeter}")
	private Tweeter tweeter;
	private List<TweerterEntity> allTweets = new ArrayList<>();
	
	public static Twitter initializeTwitter() {
		/**
		 * if not using properties file, we can set access token by following way
		 */
		
		ConfigurationBuilder cb = new ConfigurationBuilder();
	    cb.setDebugEnabled(true)
	      .setOAuthConsumerKey("UVVv2xAklnBPKbnOyGniKrIev")
	      .setOAuthConsumerSecret("o3zZjWoddQ0QtoHltkhgYWQVWhNcKKfD0yGBNlwM1C0SJUwLW8")
	      .setOAuthAccessToken("1186223414706659333-iYhKzh8ChjM8DRPknfKnXHLY6G6lj2")
	      .setOAuthAccessTokenSecret("4oDGnFLAKMallnEY4CF1jaEBNg0EPgnKPoiD8TvEUXVKu");
	    TwitterFactory tf = new TwitterFactory(cb.build());
	    Twitter twitter = tf.getInstance();
	    
		return twitter;
		
	}
	
	public String addNewTweeter() throws TwitterException {
	    Twitter twitter = initializeTwitter();
	    System.out.println(tweeter.getEntity());
	    Status status = twitter.updateStatus(tweeter.getEntity().getTweetBody());
	    System.out.println(status.getText());
	    tweeterservice.addNewTweeter(tweeter.getEntity());
	    return status.getText();
	}
	@POST
	@Path("/postmytweet")
	public Response createTweetAPI(Tweeter twit) throws TwitterException {
	    Twitter twitter = initializeTwitter();
	    
	    twitter.updateStatus(twit.getEntity().getTweetBody());
	    return Response.ok().status(200).entity("Sent").build(); 
	}
	@Path("/getAllTweets")
	@GET
	public List<TweerterEntity> getTweetList() {
		 allTweets = tweeterservice.getTweets();
	     return allTweets;
	}
	public String viewTweets() {

		System.out.println("Nombuso");
		return "pages/tweets.xhtml";
	}

	public Tweeter getTweeter() {
		return tweeter;
	}

	public void setTweeter(Tweeter tweeter) {
		this.tweeter = tweeter;
	}
	
}
