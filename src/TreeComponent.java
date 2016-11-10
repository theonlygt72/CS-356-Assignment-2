import java.util.ArrayList;


public interface TreeComponent {
	
	public void addMember(User member);
	
	public ArrayList<User> getMembers();
	
	public void accept(TreeComponentVisitor tree);

}
