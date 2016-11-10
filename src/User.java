import java.util.ArrayList;

public class User implements TreeComponent {
	private String userID;
	//members that are following this user
	private ArrayList<User> followers;
	//members that this user is following
	private ArrayList<User> following;
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
	
	public String[] currentlyFollowing()
	{
		String[] allMembers = new String[following.size()];
		for(int i = 0; i < following.size(); i++)
		{
			allMembers[i] = following.get(i).toString();
		}
		return allMembers;
	}
	
	public ArrayList<User> getFollowing()
	{
		return following;
	}
	
	public void followUser(User userID)
	{
		if(!following.contains(userID))
		{
			following.add(userID);	
		}
	}
	
	public void tweet(String message)
	{
		String tweet = userID + ": " + message;
		updateAllFollowers(tweet);
	}
	
	public void updateAllFollowers(String message)
	{
		for(int i = 0; i < followers.size(); i++)
		{
			followers.get(i).getNewsFeed().add(message);
		}
	}
	
	public ArrayList<String> getNewsFeed()
	{
		return tweets;
	}
	
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