package codeu.chat.client;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.Status;
import twitter4j.auth.AccessToken;
import twitter4j.ResponseList;
import twitter4j.Paging;
import twitter4j.TwitterException;
import twitter4j.Trend;
import twitter4j.Trends;

import java.util.Random;
import java.util.ArrayList;

public class TwitterBot{
    private static final String API_KEY = "X2fxULRgFSuH2d4Pj0nXQowfi";
    private static final String API_SECRET = "PqutMwdt8jewssMYMu96c6uV7CzeTiTNXXi3My8NADXkpXNVgT";
    private static final String ACCESS_TOKEN = "1435740475-oU2spayZF3ZO43eBmEXefNOP79yurmUjwgK2KCz";
    private static final String ACCESS_TOKEN_SECRET = "WqWWfOU2HUoEW14j9aTT0e4s6c9redevTyOQ2nnmjV7W3";
	private static final String SOURCE = "ShakespeareSong";
	private static final int NUMBER_TWEETS = 20;
    private static final int woeID = 2450022;
    private static Twitter twitter;
    
    public TwitterBot(){
        twitter = new TwitterFactory().getInstance();
        twitter.setOAuthConsumer(API_KEY, API_SECRET);
        AccessToken accessToken = new AccessToken(ACCESS_TOKEN, ACCESS_TOKEN_SECRET);
        twitter.setOAuthAccessToken(accessToken);
    }
    
    public String generateTweet(){
		String tweet = "";
		try{
            //Twitter twitter = new TwitterFactory().getInstance();
			//twitter.setOAuthConsumer(API_KEY, API_SECRET);
			//AccessToken accessToken = new AccessToken(ACCESS_TOKEN, ACCESS_TOKEN_SECRET);
			//twitter.setOAuthAccessToken(accessToken);
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
    
    public ArrayList<String> getTrends(){
        ArrayList<String> allTrends = new ArrayList<String>();
        try{
            Trends trends = twitter.getPlaceTrends(woeID);
            for (Trend trend : trends.getTrends()){
                allTrends.add(trend.getName());
            }
            
        } catch(TwitterException te){
            te.printStackTrace();
            System.out.println("Failed to retrieve tweets: " + te.getMessage());
        }
        return allTrends;

    }
}




        
