
public class MiniTwitterDriver {

	public static void main(String[] args) {
		try {
			AdminControlPanel frame = AdminControlPanel.getInstance();
			frame.setResizable(false);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
