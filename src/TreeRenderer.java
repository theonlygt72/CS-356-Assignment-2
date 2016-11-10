import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;


public class TreeRenderer extends DefaultTreeCellRenderer {

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean sel, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon groupIcon = new ImageIcon(cl.getResource("group.png"));
		if(node.getUserObject() instanceof UserGroup && groupIcon != null)
		{
			setIcon(groupIcon);
		}
		return this;
		
	}
	
	

}
