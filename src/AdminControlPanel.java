import java.awt.Component;
import java.awt.EventQueue;

import javax.print.DocFlavor.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Enumeration;

import javax.swing.tree.DefaultTreeModel;

public class AdminControlPanel extends JFrame {

	private JPanel contentPane;
	private static AdminControlPanel instance = new AdminControlPanel();
	private JTree treeView;
	private int totalUsers;
	private int totalGroups;

	/**
	 * Create the frame.
	 */
	private AdminControlPanel() {
		this.setTitle("Admin Control Panel");
		totalUsers = 0;
		totalGroups = 0;
		AdminControlPanel temp = this;
		//code for JPanel
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		//Code for JTree
		treeView = new JTree();
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(new User("Root"));
		treeView.setModel(new DefaultTreeModel(root));
		DefaultTreeModel model = (DefaultTreeModel) treeView.getModel();
		treeView.setBounds(10, 11, 230, 450);
		contentPane.add(treeView);
		
		//Code for textAreas
		JTextArea userNameArea = new JTextArea();
		userNameArea.setBounds(275, 31, 185, 40);
		contentPane.add(userNameArea);
		
		//Code for textAreas
		JTextArea groupNameArea = new JTextArea();
		groupNameArea.setBounds(275, 87, 185, 40);
		contentPane.add(groupNameArea);
		
		//Code for buttons that add users to the JTree
		JButton addUserBtn = new JButton("Add User");
		addUserBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!userNameArea.getText().trim().equals(""))
				{
					DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) treeView.getLastSelectedPathComponent();
					DefaultMutableTreeNode newUser = new DefaultMutableTreeNode(new User(userNameArea.getText()));
					if(selectedNode != null && (!(selectedNode.getUserObject() instanceof User) || selectedNode.getUserObject().toString().equals("Root")))
					{
						model.insertNodeInto(newUser, selectedNode, selectedNode.getChildCount());
						userNameArea.setText(null);
						totalUsers++;
					}
					else {
						JOptionPane.showMessageDialog(null, "Please select a different node");
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please enter in text");
				}
			}
		});
		addUserBtn.setBounds(478, 31, 172, 40);
		contentPane.add(addUserBtn);
		
		//Code for buttons that add userGroups to the Jtree
		JButton addGroupBtn = new JButton("Add Group");
		addGroupBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!groupNameArea.getText().trim().equals(""))
				{
					DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) treeView.getLastSelectedPathComponent();
					DefaultMutableTreeNode newUserGroup = new DefaultMutableTreeNode(new UserGroup(groupNameArea.getText()));
					if(selectedNode != null)
					{
						model.insertNodeInto(newUserGroup, selectedNode, selectedNode.getChildCount());
						groupNameArea.setText(null);
						totalGroups++;
					}
					else {
						JOptionPane.showMessageDialog(null, "Please enter in text");
					}
				}
			}
		});
		addGroupBtn.setBounds(478, 87, 172, 40);
		contentPane.add(addGroupBtn);
		
		//Code to open the userView window
		JButton userViewBtn = new JButton("Open User View");
		userViewBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) treeView.getLastSelectedPathComponent();
				if(selectedNode.getUserObject() instanceof User )
				{
					User currentNode = (User) selectedNode.getUserObject();
					if(!currentNode.toString().equals("Root"))
					{
						UserView userwindow = UserView.getInstance(currentNode, temp);
						userwindow.setResizable(false);
						userwindow.setVisible(true);
					}
				}
				else 
				{
					JOptionPane.showMessageDialog(null, "Please select a User");
				}
			}
		});
		userViewBtn.setBounds(250, 179, 424, 65);
		contentPane.add(userViewBtn);
		
		//code to calculate total users
		JButton userTotalBtn = new JButton("Show User Total");
		userTotalBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Total number of Users: " + totalUsers) ;
			}
		});
		userTotalBtn.setBounds(250, 304, 204, 55);
		contentPane.add(userTotalBtn);	
		
		//code to show total number of groups
		JButton groupTotalBtn = new JButton("Show Group Total");
		groupTotalBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Total number of Groups: " + totalGroups);
			}
		});
		groupTotalBtn.setBounds(470, 304, 204, 55);
		contentPane.add(groupTotalBtn);
		
		//Code to show total number of messages
				JButton messageTotalBtn = new JButton("Show Message Total");
				messageTotalBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						TreeVisitor visit = new TreeVisitor();
						DefaultMutableTreeNode root = (DefaultMutableTreeNode) treeView.getModel().getRoot();
						for(Enumeration en = root.breadthFirstEnumeration(); en.hasMoreElements();)
						{
							DefaultMutableTreeNode node = (DefaultMutableTreeNode) en.nextElement();
							((TreeComponent)node.getUserObject()).accept(visit);
						}
						JOptionPane.showMessageDialog(null, "Total Number of Tweets: " + visit.getTotalTweets());
					}
				});
				messageTotalBtn.setBounds(250, 381, 204, 55);
				contentPane.add(messageTotalBtn);
		
		//code to get the percentage of positive messages
		JButton positiveBtn = new JButton("Show Positive %");
		positiveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TreeVisitor visit = new TreeVisitor();
				DefaultMutableTreeNode root = (DefaultMutableTreeNode) treeView.getModel().getRoot();
				for(Enumeration en = root.breadthFirstEnumeration(); en.hasMoreElements();)
				{
					DefaultMutableTreeNode node = (DefaultMutableTreeNode) en.nextElement();
					((TreeComponent)node.getUserObject()).accept(visit);
				}
				JOptionPane.showMessageDialog(null, "Percentage of positive Tweets (Contains: Happy or Positive): " + visit.getPosPercent() + "%");
			}
		});
		positiveBtn.setBounds(470, 381, 204, 55);
		contentPane.add(positiveBtn);
		treeView.setCellRenderer(new TreeRenderer());
		
	}
	
	public JTree getTree()
	{
		return treeView;
	}
	
	public User findUser(String userID)
	{
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) treeView.getModel().getRoot();
		DefaultMutableTreeNode target = null;
		for(Enumeration en = root.breadthFirstEnumeration(); en.hasMoreElements() && target == null;)
		{
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) en.nextElement();
			if(node.getUserObject() instanceof User)
			{
				User follow = (User) node.getUserObject();
				if(follow.toString().equals(userID))
				{
					return follow;
				}
			}
		}
		return null;
	}
	
	public static AdminControlPanel getInstance()
	{
		return instance;
	}
}
