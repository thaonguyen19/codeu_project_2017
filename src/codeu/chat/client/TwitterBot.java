package codeu.chat.client;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.Status;
import twitter4j.auth.AccessToken;
import twitter4j.ResponseList;
import twitter4j.Paging;
import twitter4j.TwitterException;

import java.util.Random;

public class TwitterBot{
    private static final String API_KEY = "";
    private static final String API_SECRET = "";
    private static final String ACCESS_TOKEN = "";
    private static final String ACCESS_TOKEN_SECRET = "";
	private static final String SOURCE = "ShakespeareSong";
	private static final int NUMBER_TWEETS = 20;
    
    public String generateTweet(){
		String tweet = "";

		try{
			Twitter twitter = new TwitterFactory().getInstance();
			twitter.setOAuthConsumer(API_KEY, API_SECRET);
			AccessToken accessToken = new AccessToken(ACCESS_TOKEN, ACCESS_TOKEN_SECRET);
			twitter.setOAuthAccessToken(accessToken);
			Paging paging = new Paging(1, NUMBER_TWEETS);
			ResponseList<Status> tweets = twitter.getUserTimeline(SOURCE, paging);
			Random rand = new Random();
			int index = rand.nextInt(NUMBER_TWEETS);
			tweet = tweets.get(index).getText();

		} catch(TwitterException te){
			te.printStackTrace();
			System.out.println("Failed to retrieve tweets: " + te.getMessage());
		}

		return tweet;
	}
}




        
