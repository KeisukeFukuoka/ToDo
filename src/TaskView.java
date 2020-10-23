package src;
import java.awt.*;
import javax.swing.*;

public class TaskView {
	JPanel panel;
	Task task;
	JCheckBox ck;
	JButton button;
	
	TaskView(Task task) {
		int startyear, startmonth, startdate,starthour,startmin;
		String taskName, category, context;
	
		this.task = task;
		this.panel = new JPanel();
		this.panel.setPreferredSize(new Dimension(700,60));
		this.panel.setBackground(Color.WHITE);
		SpringLayout layout = new SpringLayout();
		this.panel.setLayout(layout);
		this.ck = new JCheckBox();
		
		taskName = task.getName();
		JLabel taskNameLabel = new JLabel(taskName);
		layout.putConstraint(SpringLayout.NORTH, ck, 15, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, ck, 20, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, taskNameLabel, -5, SpringLayout.NORTH, ck);
		layout.putConstraint(SpringLayout.WEST, taskNameLabel, 20, SpringLayout.EAST, ck);
		this.panel.add(ck);
		this.panel.add(taskNameLabel);
		
		if(task.getTaskProperty().getProperty(PropertyName.STARTYEAR.toString()) != null) { //STARTYEARがnullでない時,日付ラベルを表示
			startyear = (int)task.getTaskProperty().getProperty(PropertyName.STARTYEAR.toString());
			startmonth = (int)task.getTaskProperty().getProperty(PropertyName.STARTMONTH.toString());
			startdate = (int)task.getTaskProperty().getProperty(PropertyName.STARTDATE.toString());			
			JLabel dateLabel = new JLabel(String.format("%4d/%02d/%02d", startyear, startmonth, startdate));
			layout.putConstraint(SpringLayout.NORTH, dateLabel, 12, SpringLayout.NORTH, ck);
			layout.putConstraint(SpringLayout.WEST, dateLabel, 20, SpringLayout.EAST, ck);
			this.panel.add(dateLabel);
		}
		if(task.getTaskProperty().getProperty(PropertyName.STARTHOUR.toString()) != null) { //STARTHOURがnullでない時,時間のラベルを表示
			starthour = (int)task.getTaskProperty().getProperty(PropertyName.STARTHOUR.toString());
			startmin = (int)task.getTaskProperty().getProperty(PropertyName.STARTMINUTE.toString());			
			JLabel hourLabel = new JLabel(String.format("%02d:%02d", starthour, startmin));
			layout.putConstraint(SpringLayout.NORTH, hourLabel, 12, SpringLayout.NORTH, ck);
			layout.putConstraint(SpringLayout.WEST, hourLabel, 100, SpringLayout.EAST, ck);
			this.panel.add(hourLabel);
		}
		if(task.getTaskProperty().getProperty(PropertyName.CATEGORY.toString()) != null) { //CATEGORYがnullでない時,コンテクストラベルを表示
			category = (String)task.getTaskProperty().getProperty(PropertyName.CATEGORY.toString());			
			JLabel categoryLabel = new JLabel(category);
			categoryLabel.setPreferredSize(new Dimension(100,16));
			layout.putConstraint(SpringLayout.NORTH, categoryLabel, 12, SpringLayout.NORTH, ck);
			layout.putConstraint(SpringLayout.WEST, categoryLabel, 250, SpringLayout.EAST, ck);
			this.panel.add(categoryLabel);
		}
		if(task.getTaskProperty().getProperty(PropertyName.CONTEXT.toString()) != null) { //CONTEXTがnullでない時,カテゴリーラベルを表示
			context = (String)task.getTaskProperty().getProperty(PropertyName.CONTEXT.toString());			
			JLabel contextLabel = new JLabel(context);
			contextLabel.setPreferredSize(new Dimension(100,16));
			layout.putConstraint(SpringLayout.NORTH, contextLabel, 12, SpringLayout.NORTH, ck);
			layout.putConstraint(SpringLayout.WEST, contextLabel, 250, SpringLayout.EAST, ck);
			this.panel.add(contextLabel);
		}
		
		this.button = new JButton("編集"); //Task編集用のボタンを作成
		layout.putConstraint(SpringLayout.NORTH, button, 0, SpringLayout.NORTH, ck);
		layout.putConstraint(SpringLayout.EAST, button, -30, SpringLayout.EAST, this.panel);
		this.panel.add(button);
	}
	JPanel getPanel() {return this.panel;}
	JCheckBox getCheckBox() {return this.ck;}
	Task getTask() {return this.task;}
}
