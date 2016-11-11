import java.util.ArrayList;


public interface TreeComponent {
	
	/**
	 * Adds a User to a list that is encapsulated within the class.
	 * 
	 * @param member The user that is going to be added to a list.
	 */
	public void addMember(User member);
	
	/**
	 * Gets the arrayList of Users that is stored within the class.
	 * 
	 * @return An arrayList of Users
	 */
	public ArrayList<User> getMembers();
	
	/**
	 * Accepts a visitor that handles TreeComponents
	 * 
	 * @param tree The visitor
	 */
	public void accept(TreeComponentVisitor tree);

}
