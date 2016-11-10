import java.util.ArrayList;
import java.util.Hashtable;

public class UserGroup implements TreeComponent {
	private String groupID;
	private ArrayList<User> members;
	
	public UserGroup(String groupID) {
		this.groupID = groupID;
	}


	@Override
	public void addMember(User member) {
		members.add(member);
	}


	@Override
	public ArrayList<User> getMembers() {
		// TODO Auto-generated method stub
		return members;
	}
	
	public String toString()
	{
		return groupID;
	}


	@Override
	public void accept(TreeComponentVisitor tree) {
		tree.visit(this);
		
	}
}
