package src;

public class Main {
	public static Box box;
	public static MainFrame mainFrame;
	public static void main(String[] args) {
		//TODO Auto-generated method stub
		Main.box = new Box(); //Boxはプログラムで唯一の存在に
		Main.mainFrame = new MainFrame("title");
		Main.mainFrame.setVisible(true);
	}

}
