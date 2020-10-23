//sideButton専用クラス
package src;
import java.awt.*;
import javax.swing.*;

public class SideButton extends JButton { //JButtonを継承させる
	SideButton(String s) { //コンストラクタ
		setText(s); //ボタンテキストの代入
		setContentAreaFilled(false); //ボタンの背景を透明に
		setBorderPainted(false); //枠線を消す
		setForeground(Color.WHITE); //ボタン背景色
		setPreferredSize(new Dimension(190,40)); // ボタンサイズ
		setFont(new Font("", Font.BOLD, 14)); //ボタンテキストのフォント
	}
}
