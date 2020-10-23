package src;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class MainFrame extends JFrame implements ActionListener{ 
	JPanel mainPanel;
	CardLayout layout;
	AddModePanel addModePanel; //追加
	JPanel todayModePanel; //今日
	JPanel tomorrowModePanel; //明日
	JPanel somedayModePanel; //いつか
	JPanel doneModePanel; //完了
	List<TaskView> todayTasks,tomorrowTasks,somedayTasks,doneTasks;
	
	public MainFrame(String title) {
		setTitle(title); //Frameのタイトルの設定
		setBounds(100,100,900,600); //Frameの左上x座標,y座標,幅,高さの設定
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //☓ボタンが押された時に終了する
	
		Container contentPane = getContentPane(); //Content Paneの呼び出し
		JPanel headerPanel = new JPanel(); //パネル作成
		headerPanel.setBackground(new Color(67,135,233)); //パネル背景色
		headerPanel.setPreferredSize(new Dimension(900,50)); //パネルサイズ
		
		JPanel sidePanel = new JPanel(); //サイドパネル作成
		sidePanel.setBackground(new Color(120,120,120)); //サイドパネル背景色
		sidePanel.setPreferredSize(new Dimension(190,600)); //サイドパネルサイズ
		
		this.addModePanel = new AddModePanel();	//追加画面パネル
		//切り替え用パネル作成
		this.todayModePanel = new JPanel(); //今日
		this.tomorrowModePanel = new JPanel(); //明日
		this.somedayModePanel = new JPanel(); //いつか
		this.doneModePanel = new JPanel(); //完了済
		
		//CardLayout
		this.mainPanel = new JPanel();
		this.layout = new CardLayout(); //MainPanelにそれぞれのパネルを追加
		this.mainPanel.setLayout(this.layout);		
		this.mainPanel.add(addModePanel.getPanel(), "Add"); //add(コンポーネント,画面を識別する名前)
		this.mainPanel.add(todayModePanel, "Today");
		this.mainPanel.add(tomorrowModePanel, "Tomorrow");
		this.mainPanel.add(somedayModePanel, "Someday");
		this.mainPanel.add(doneModePanel, "Done");
		
		contentPane.add(headerPanel, BorderLayout.NORTH); //コンポーネントを追加
		contentPane.add(sidePanel, BorderLayout.WEST);
		////MainPanelをContentPaneに追加
		contentPane.add(mainPanel);

		JLabel headerLabel = new JLabel("To Do"); //ラベルの作成
		headerLabel.setForeground(Color.WHITE); //文字列の色
		headerLabel.setPreferredSize(new Dimension(900,50)); //ラベルサイズの変更
		headerLabel.setHorizontalAlignment(JLabel.CENTER); //文字列の垂直位置
		headerLabel.setFont(new Font("Century", Font.BOLD, 26)); //文字列のフォント
		headerPanel.add(headerLabel, BorderLayout.CENTER); //コンポーネントを追加
		
		SideButton buttonAddMode = new SideButton("追加"); //サイドバーボタン
		buttonAddMode.setActionCommand("Add"); //アクションコマンド追加
		buttonAddMode.addActionListener(this); //アクションリスナー追加
		SideButton buttonTodayMode = new SideButton("今日");
		buttonTodayMode.setActionCommand("Today");
		buttonTodayMode.addActionListener(this);
		SideButton buttonTomorrowMode = new SideButton("明日");
		buttonTomorrowMode.setActionCommand("Tomorrow");
		buttonTomorrowMode.addActionListener(this);
		SideButton buttonSomedayMode = new SideButton("いつか");
		buttonSomedayMode.setActionCommand("Someday");
		buttonSomedayMode.addActionListener(this);
		SideButton buttonDoneMode = new SideButton("完了済み");
		buttonDoneMode.setActionCommand("Done");
		buttonDoneMode.addActionListener(this);
		
		sidePanel.add(buttonAddMode);
		sidePanel.add(buttonTodayMode);
		sidePanel.add(buttonTomorrowMode);
		sidePanel.add(buttonSomedayMode);
		sidePanel.add(buttonDoneMode);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand(); //アクションコマンドを取得
		
		if(cmd == null) {
			return;
		} else {
			switch(cmd) {
				case "Today" :
					Calendar calendar = Calendar.getInstance();//今日の日付を取得
					Map mtProperty = new HashMap();
					//「今日」のタスクを検索するためのプロパティをつくる
					mtProperty.put(PropertyName.STARTYEAR.toString(), calendar.get(Calendar.YEAR));
					mtProperty.put(PropertyName.STARTMONTH.toString(), calendar.get(Calendar.MONTH));
					mtProperty.put(PropertyName.STARTDATE.toString(), calendar.get(Calendar.DATE));
					mtProperty.put(PropertyName.STARTWEEK.toString(), calendar.get(Calendar.WEEK_OF_YEAR));
					mtProperty.put(PropertyName.FINISH.toString(), false);
					this.todayTasks = this.view(mtProperty, this.todayModePanel, cmd);
					calendar = null;
					mtProperty = null;
					break;
				case "Tomorrow" :
					Calendar tomorrow = Calendar.getInstance();//今日の日付を取得
					tomorrow.add(Calendar.DATE, 1);
					Map mtProperty1 = new HashMap();
					mtProperty1.put(PropertyName.STARTYEAR.toString(), tomorrow.get(Calendar.YEAR));
					mtProperty1.put(PropertyName.STARTMONTH.toString(), tomorrow.get(Calendar.MONTH));
					mtProperty1.put(PropertyName.STARTDATE.toString(), tomorrow.get(Calendar.DATE));
					mtProperty1.put(PropertyName.STARTWEEK.toString(), tomorrow.get(Calendar.WEEK_OF_YEAR));
					mtProperty1.put(PropertyName.FINISH.toString(), false);
					this.tomorrowTasks = this.view(mtProperty1, this.tomorrowModePanel, cmd);
					tomorrow = null;
					mtProperty1 =null;
					break;
				case "Someday" :
					Map mtProperty3 = new HashMap();
					mtProperty3.put(PropertyName.FINISH.toString(), false);
					this.somedayTasks = this.view(mtProperty3, this.somedayModePanel, cmd);
					mtProperty3 =null;
					break;
				case "Done" :
					Map mtProperty4 = new HashMap();
					mtProperty4.put(PropertyName.FINISH.toString(), false);
					this.doneTasks = this.view(mtProperty4, this.doneModePanel, cmd);
					mtProperty4 =null;
					break;
				default :
			}
		}
		layout.show(this.mainPanel, cmd);
	}
	public List<TaskView> view(Map pr, JPanel panel, String cmd) {
		int n = 0; //Taskを数える用の変数
		JPanel outer = new JPanel(); //TaskViewを詰める用のパネル
		SpringLayout layout1 = new SpringLayout(); //ScrollPaneを設定するためのレイアウト
		outer.setLayout(layout1);
		SpringLayout layout2 = new SpringLayout();//更新ボタンを設置するためのレイアウト
		panel.setLayout(layout2);
		JScrollPane scroll = new JScrollPane(outer);
		TaskProperty matching = new TaskProperty(pr);
		List matchingTasks = Main.box.search(matching); //boxのサーチメソッドでタスクを検索
		LinkedList<TaskView> taskView = new LinkedList<TaskView>(); //taskView用のコレクションを用意
		if(!matchingTasks.isEmpty()) {
			for(Iterator i = matchingTasks.iterator(); i.hasNext();) {
				Task task = (Task) i.next();
				taskView.add(new TaskView(task));
				if(n > 0) {
					layout1.putConstraint(SpringLayout.NORTH, taskView.get(n).getPanel(), 1, SpringLayout.SOUTH, taskView.get(n-1).getPanel());//前のタスクの1px下に配置
				}
				outer.add(taskView.get(n).getPanel());
				n = n + 1;
			}
		}
		outer.setPreferredSize(new Dimension(700,61*(n))); //タスクの数に合わせてパネルのサイズを大きくする
		scroll.setPreferredSize(new Dimension(710,480));
		JButton update = new JButton("更新");
		update.setActionCommand(cmd);
		update.addActionListener(new DoneListener());//DoneListnerの作成
		layout2.putConstraint(SpringLayout.SOUTH, update, -10, SpringLayout.SOUTH, panel);//前のタスクの1px下に配置
		layout2.putConstraint(SpringLayout.EAST, update, -10, SpringLayout.EAST, panel);//前のタスクの1px下に配置
		panel.add(scroll);
		panel.add(update);
		panel.revalidate(); //コンポーネントのレイアウト等を変更した際の再描画
		return taskView;
		}
		public List<TaskView> getTaskViews(String cmd) {
			switch(cmd) {
			case "Today" : return this.todayTasks;
			case "Tomorrow" : return this.tomorrowTasks;
			case "Someday" : return this.somedayTasks;
			case "Done" : return this.doneTasks;
			default : return null;
		}
	}
}