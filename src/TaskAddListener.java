package src;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;

public class TaskAddListener implements ActionListener{

	public void actionPerformed(ActionEvent e) {
		String taskName = AddModePanel.taskNameField.getText(); //タスク名入力欄から文字列get
		if((taskName == null) | (taskName.equals(""))) return; //タスク名が空の時はreturnする
		String description = AddModePanel.descriptionField.getText(); //タスク説明入力欄から文字列get
		Map<String, Comparable> properties = new HashMap<String, Comparable>(); //ハッシュマップ利用する為のオブジェクト作成
		Boolean dateCheck = AddModePanel.ckbox.isSelected(); //日付設定ボタンの状態を返す。
		if(dateCheck) { //日付設定チェックされている場合は、、、
			Calendar date = Calendar.getInstance();
			date.setTime((java.util.Date)AddModePanel.spinner.getValue()); //日付入力ボックス(spinner)から値を取得する
			properties.put(PropertyName.STARTYEAR.toString(), date.get(Calendar.YEAR)); //ハッシュマップで年月日週と併せて要素を格納
			properties.put(PropertyName.STARTMONTH.toString(), date.get(Calendar.MONTH));
			properties.put(PropertyName.STARTDATE.toString(), date.get(Calendar.DATE));
			properties.put(PropertyName.STARTWEEK.toString(), date.get(Calendar.WEEK_OF_YEAR));
			String hour = (String)AddModePanel.hourcombo.getSelectedItem();
			String minute = (String)AddModePanel.minutecombo.getSelectedItem();
			if((!hour.equals("未指定")) && (!minute.equals("未指定"))) {
				properties.put(PropertyName.STARTHOUR.toString(), Integer.parseInt(hour)); //時間が指定されているときはコンボボックスから値を取得する
				properties.put(PropertyName.STARTMINUTE.toString(), Integer.parseInt(minute)); //Integer.parseIntでStringからintへ変換
			}
		}
		String category = (String)AddModePanel.categorycombo.getSelectedItem();
		properties.put(PropertyName.CATEGORY.toString(), category); //Stringからintへ変換
		String context = (String)AddModePanel.contextcombo.getSelectedItem();
		properties.put(PropertyName.CONTEXT.toString(), context); //ハッシュマップで要素を格納
		properties.put(PropertyName.FINISH.toString(), false);
		Main.box.addTask(taskName, description, new TaskProperty(properties));
		properties.clear(); //マップの要素を削除し空にする
		AddModePanel.taskNameField.setText(""); //フィールドを空にする
		AddModePanel.descriptionField.setText(""); //フィールドを空にする
		
		for(Iterator<?> i1 = Main.box.getTasks().iterator(); i1.hasNext();){ //タスクが追加される度にboxに入っているタスク名が全て表示されるテスト文
			Task task1 = (Task) i1.next();
			System.out.println(task1.getName());
			}
		
	}
}
