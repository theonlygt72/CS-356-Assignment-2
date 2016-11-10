import java.util.ArrayList;

public class User implements TreeComponent {
	private String userID;
	//members that are following this user
	private ArrayList<User> followers;
	//members that this user is following
	private ArrayList<User> following;
	//tweets that will show up in the newsFeed
	//includes tweets from people this user is following
	private ArrayList<String> tweets;
	
	public User(String userID)
	{
		this.userID = userID;
		followers = new ArrayList<User>();
		following = new ArrayList<User>();
		tweets = new ArrayList<String>();
		//you follow yourself by default;
		followers.add(this);
		following.add(this);
	}
	
	@Override
	public void addMember(User member) {
		followers.add(member);
	}

	@Override
	public ArrayList<User> getMembers() {
		return null;
	}
	
	public String toString()
	{
		return userID;
	}
	
	/**
	 * Returns an array of Users that this user is following.
	 * The array will never be empty since a user follows itself by default
	 * @return an array of all people who this user is currently following. 
	 */
	public String[] currentlyFollowing()
	{
		String[] allMembers = new String[following.size()];
		for(int i = 0; i < following.size(); i++)
		{
			allMembers[i] = following.get(i).toString();
		}
		return allMembers;
	}
	
	/**
	 * Getter method for the arrayList of users that are this member is following
	 * @return an arrayList of users that this member is following
	 */
	public ArrayList<User> getFollowing()
	{
		return following;
	}
	
	/**
	 * Follows the user with the UserID that is passed in.
	 * Does nothing if no UserID is found.
	 * @param userID the user that this user wants to follow
	 */
	public void followUser(User userID)
	{
		if(!following.contains(userID))
		{
			following.add(userID);	
		}
	}
	
	/**
	 * Sends out a message to itself and all other users that are following this user
	 * @param message The message that the user wants to send out
	 */
	public void tweet(String message)
	{
		String tweet = userID + ": " + message;
		updateAllFollowers(tweet);
	}
	
	/**
	 * Adds a message to every followers newsFeed.
	 * @param message the message that will be added to a user's newsfeed
	 */
	public void updateAllFollowers(String message)
	{
		for(int i = 0; i < followers.size(); i++)
		{
			followers.get(i).getNewsFeed().add(message);
		}
	}
	
	/**
	 * Getter method for this user's newsFeed
	 * @return arrayList of messages
	 */
	public ArrayList<String> getNewsFeed()
	{
		return tweets;
	}
	
	/**
	 * Returns the user's newsfeed as an array.
	 * @return array of tweets
	 */
	public String[] getTweets()
	{
		String[] twitt = new String[tweets.size()];
		for(int i = 0; i < tweets.size(); i++)
		{
			twitt[i] = tweets.get(i);
		}
		return twitt;
	}

	@Override
	public void accept(TreeComponentVisitor tree) {
		tree.visit(this);
		
	}

	

}