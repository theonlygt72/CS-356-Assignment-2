
public interface TreeComponentVisitor {
	
	/**
	 * Visits the User object and collects data.
	 * 
	 * @param user The user that is being visited
	 */
	public void visit(User user);
	
	/**
	 * Visits the UserGroup and collects data.
	 * 
	 * @param usergroup The userGroup that is being visited
	 */
	public void visit(UserGroup usergroup);
	

}
