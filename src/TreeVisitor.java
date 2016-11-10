
public class TreeVisitor implements TreeComponentVisitor {
	
	private int totalTweets;
	private int positiveWords;
	private double posPercent;
	
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
	
	public int getTotalTweets()
	{
		return totalTweets;
	}
	
	public void reset()
	{
		totalTweets = 0;
		positiveWords = 0;
	}
	
	public double getPosPercent()
	{
		return ((double)positiveWords) / totalTweets * 100;
	}

}
