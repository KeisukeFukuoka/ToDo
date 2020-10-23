package src;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Calendar;

public class AddModePanel extends JPanel implements ActionListener{
	static JPanel panel; //staticにする事でAddModePanelのみの存在となり他クラスからアクセス可能になる
	static JTextField categoryTextField;
	static DefaultComboBoxModel categorymodel;
	static JTextField contextTextField;
	static DefaultComboBoxModel contextmodel;
	static JTextField taskNameField;
	static JTextField descriptionField;
	static JSpinner spinner;
	static JCheckBox ckbox;
	static JComboBox minutecombo;
	static JComboBox categorycombo;
	static JComboBox contextcombo;
	static JComboBox hourcombo;
	
//	JButton button = new JButton("Button");
//	button.addActionListener(this); //呼び出し元のクラスに直接リスナーを設置している
//	button.setActionCommand("push"); //ボタン名登録
	
public AddModePanel() {
	
		JPanel panel = new JPanel(); //パネル作成
		panel.setLayout(null);
		panel.setBackground(new Color(240,240,240));
		JLabel label1 = new JLabel("タスク名:"); //ラベル作成
		label1.setBounds(30,30,60,30); //コンポーネントの配置とサイズ変更
		JLabel label2 = new JLabel("メモ:");//ラベル作成
		label2.setBounds(30,70,60,30); //コンポーネントの配置とサイズ変更
		JLabel label3 = new JLabel("日付:");
		label3.setBounds(30,110,60,30);
		JTextField taskNameField = new JTextField(10); //テキストフィールド作成(10)文字分表示される幅を指示している
		taskNameField.setBounds(100,30,200,30); //コンポーネントの配置とサイズ変更
		taskNameField.setFont(new Font("", Font.BOLD, 16)); //文字のフォントの変更。文字数で幅設定している場合は幅も変わる。
		JTextField descriptionField = new JTextField(25);//テキストフィールド作成(25)文字分表示される幅を指示している
		descriptionField.setBounds(100,70,450,30); //コンポーネントの配置とサイズ変更
		descriptionField.setFont(new Font("", Font.BOLD, 16)); //文字のフォントの変更。文字数で幅設定している場合は幅も変わる。
		
		//日付入力ボックスの作成
		Calendar calendar = Calendar.getInstance();
		java.util.Date initDate = calendar.getTime(); //今日の日付を初期値にする ※java.util.utilのDate
		calendar.set(2020, 1, 1, 0, 0);
		java.util.Date startDate = calendar.getTime(); //2020/1/1を最低値にする ※java.util.utilのDate
		
		SpinnerDateModel model = new SpinnerDateModel(initDate, startDate, null, Calendar.DAY_OF_MONTH);
		JSpinner spinner = new JSpinner(model);
		JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "yyyy.MM.dd"); //書式書き換え
		spinner.setEditor(editor);
		spinner.setBounds(100,110,200,30);
		
		JLabel label4 = new JLabel("時間:"); //ラベル作成
		label4.setBounds(30,150,60,30); //コンポーネントの配置とサイズ変更
		String[] hourdata = {"未指定","00","01","02","03","04","05",
							 "06","07","08","09","10","11","12","13","14",
							 "15","16","17","18","19","20","21","22","23"}; 
			DefaultComboBoxModel hourmodel = new DefaultComboBoxModel(hourdata); //ComboBoxモデル作成
		String[] minutedata = {"未指定","00","15","30","45"};
			DefaultComboBoxModel minutemodel = new DefaultComboBoxModel(minutedata); //ComboBoxモデル作成
		JComboBox hourcombo = new JComboBox(hourmodel); //ComboBox作成
			JComboBox minutecombo = new JComboBox(minutemodel); //ComboBox作成
			hourcombo.setBounds(100,150,70,30);
			minutecombo.setBounds(175,150,70,30);
		
		//コンボボックス2種の作成
		//カテゴリーComboBox
		JLabel label5 = new JLabel("カテゴリー:"); //ラベル作成
		label5.setBounds(25,190,100,30); //コンポーネントの配置とサイズ変更
		String[] categorydata = {"未分類", "仕事", "趣味"}; //カテゴリーの選択肢の定義
		this.categorymodel = new DefaultComboBoxModel(categorydata); //ComboBoxモデル作成
		JComboBox categorycombo = new JComboBox(categorymodel); //ComboBox作成
		categorycombo.setBounds(100,190,100,30);
		this.categoryTextField = new JTextField(25);//テキストフィールド作成(25)文字分表示される幅を指示している
		categoryTextField.setBounds(210,190,100,30);
		categoryTextField.setFont(new Font("", Font.BOLD, 16)); //文字のフォントの変更。文字数で幅設定している場合は幅も変わる。
		JButton categoryAdd = new JButton("カテゴリーを追加"); //ボタン作成
		categoryAdd.setBounds(420,190,140,30); 
		categoryAdd.addActionListener(this);
		categoryAdd.setActionCommand("btn_category_add"); 
		
		//コンテキストComboBox
		JLabel label6 = new JLabel("コンテキスト:"); //ラベル作成
		label6.setBounds(12,230,90,30);//コンポーネントの配置とサイズ変更
		String[] contextdata = {"未分類","家"}; //カテゴリーの選択肢の定義
		this.contextmodel = new DefaultComboBoxModel(contextdata); //ComboBoxモデル作成
		JComboBox contextcombo = new JComboBox(contextmodel); //ComboBox作成
		contextcombo.setBounds(100,230,100,30);
		this.contextTextField = new JTextField(25);//テキストフィールド作成(25)文字分表示される幅を指示している
		contextTextField.setBounds(210,230,200,30); 
		contextTextField.setFont(new Font("", Font.BOLD, 16)); //文字のフォントの変更。文字数で幅設定している場合は幅も変わる。
		JButton contextAdd = new JButton("コンテキスト追加"); //ボタン作成
		contextAdd.setBounds(420,230,140,30);
		contextAdd.addActionListener(this);
		contextAdd.setActionCommand("btn_context_add"); 
		
		//チェックボックス
		JCheckBox ckbox = new JCheckBox("日付設定");
		ckbox.setBounds(310,110,100,30);
		panel.add(ckbox);
		
		panel.add(label1);
		panel.add(taskNameField);
		panel.add(label2);
		panel.add(descriptionField);
		panel.add(label3);
		panel.add(spinner);
					panel.add(label4);
					panel.add(minutecombo);
					panel.add(hourcombo);
					panel.add(label5);
					panel.add(categorycombo);
					panel.add(categoryTextField);
					panel.add(categoryAdd);
					panel.add(label6);
					panel.add(contextcombo);
					panel.add(contextTextField);
					panel.add(contextAdd);
		this.panel = panel;
		
		AddModePanel.taskNameField = taskNameField; 
		AddModePanel.descriptionField = descriptionField;
		AddModePanel.spinner = spinner;
		AddModePanel.ckbox = ckbox;
		AddModePanel.minutecombo = minutecombo;
		AddModePanel.categorycombo = categorycombo;
		AddModePanel.contextcombo = contextcombo;
		
		//追加ボタン
		JButton taskAdd = new JButton("タスク追加");
		taskAdd.setBounds(420,280,140,23);
		taskAdd.addActionListener(new TaskAddListener());
		panel.add(taskAdd);
	}
public JPanel getPanel() {
	return panel;
}


public void actionPerformed(ActionEvent e) { //ボタン操作取得
	String cmd = e.getActionCommand();//テキストフィールドの文字列取り出し
	if(cmd == "btn_category_add") {
		String newdata = categoryTextField.getText();
		if((newdata.equals("")) | (newdata ==null)) {
			return;
		}
		categorymodel.addElement(newdata);
	  categoryTextField.setText("");
	}
	if(cmd == "btn_context_add") {
		String newdata = contextTextField.getText();
		if((newdata.equals("")) | (newdata == null)) {
			return;
		}
		contextmodel.addElement(newdata);
		contextTextField.setText("");
	}
	
	
}

}