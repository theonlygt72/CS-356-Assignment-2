import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.URI;
import java.net.URL;

public class UserView extends JFrame {

	private JPanel contentPane;
	private static UserView instance;
	/**
	 * Create the frame.
	 */
	private UserView(User currentUser, AdminControlPanel parent) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle(currentUser.toString());
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		
		JLabel followTitle = new JLabel("Members that you are following");
		followTitle.setBounds(15, 42, 648, 80);
		contentPane.add(followTitle);
		
		JList<String> following = new JList<String>();
		following.setListData(currentUser.currentlyFollowing());
		following.setBounds(15, 91, 648, 121);
		contentPane.add(following);
		
		JTextArea followUserTxt = new JTextArea();
		followUserTxt.setBounds(15, 16, 338, 40);
		contentPane.add(followUserTxt);
		
		JButton followUserBtn = new JButton("Follow User");
		followUserBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				User target = parent.findUser(followUserTxt.getText());
				if(target != null)
				{
					currentUser.followUser(target);
					target.addMember(currentUser);
					following.setListData(currentUser.currentlyFollowing());
					followUserTxt.setText(null);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Target not available");
				}
			}
		});
		followUserBtn.setBounds(418, 16, 181, 40);
		contentPane.add(followUserBtn);
		
		
		JTextArea tweetMessage = new JTextArea();
		tweetMessage.setBounds(15, 228, 338, 59);
		contentPane.add(tweetMessage);
		

		JLabel newsTitle = new JLabel("News feed");
		newsTitle.setBounds(15, 290, 648, 25);
		contentPane.add(newsTitle);
		
		JList<String> newsFeed = new JList<String>();
		newsFeed.setListData(currentUser.getTweets());
		newsFeed.setBounds(15, 315, 648, 90);
		contentPane.add(newsFeed);
		
		JButton postTweetBtn = new JButton("Post Tweet");
		postTweetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentUser.tweet(tweetMessage.getText());
				newsFeed.setListData(currentUser.getTweets());
				tweetMessage.setText(null);
			}
		});
		postTweetBtn.setBounds(418, 228, 181, 59);
		contentPane.add(postTweetBtn);
		
		JButton refreshButton = new JButton("Refesh Newsfeed");
		refreshButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				newsFeed.setListData(currentUser.getTweets());
			}
		});
		refreshButton.setBounds(15, 410, 150, 50);
		contentPane.add(refreshButton);
	}
	
	/**
	 * @param currentUser the user that is being viewed
	 * @param parent the original panel that opened this view
	 * @return a static instance of this panel
	 */
	public static UserView getInstance(User currentUser, AdminControlPanel parent)
	{
		instance = new UserView(currentUser, parent);
		return instance;
	}
	
}
