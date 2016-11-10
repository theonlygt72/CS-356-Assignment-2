
public class TreeVisitor implements TreeComponentVisitor {
	
	//total number of tweets among all users
	private int totalTweets;
	//total number of words containing happy or positive among all users
	private int positiveWords;
	//percentage of positive words over the the total amount of words
	private double posPercent;
	
	/**
	 * Initializes all private variables and sets them as 0;
	 */
	public TreeVisitor() {
		totalTweets = 0;
		positiveWords = 0;
		posPercent = 0;
	}

	@Override
	public void visit(User user) {
		totalTweets += user.getNewsFeed().size();
		for(int i = 0; i < user.getNewsFeed().size(); i++)
		{
			if(user.getNewsFeed().get(i).toLowerCase().contains("happy") || user.getNewsFeed().get(i).toLowerCase().contains("positive"))
			{
				positiveWords++;
			}
		}
	}

	@Override
	public void visit(UserGroup usergroup) {

	}
	
	/**
	 * Returns the total number of tweets in the newsfeed of all users.
	 * Counts tweets among followers multiple times since those tweets are in multiple newsfeeds
	 * @return total number of tweets
	 */
	public int getTotalTweets()
	{
		return totalTweets;
	}

	
	/**
	 * Calculates the percentage of positive words divided by the total number of tweets
	 * @return the percentage of words that are positive
	 */
	public double getPosPercent()
	{
		return ((double)positiveWords) / totalTweets * 100;
	}

}
