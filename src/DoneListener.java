package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DoneListener implements ActionListener{
	public void  actionPerformed(ActionEvent e) {
		
		String cmd = e.getActionCommand();
		List<TaskView> tasks = Main.mainFrame.getTaskViews(cmd);
		for(Iterator i = tasks.iterator(); i.hasNext();) {
			TaskView taskView = (TaskView) i.next();
			if (taskView.getCheckBox().isSelected()) {
				Task task = taskView.getTask();
				Calendar calendar = Calendar.getInstance();
				Map properties = task.getTaskProperty().getProperties();
				if(!(boolean)task.getTaskProperty().getProperty(PropertyName.FINISH.toString())) {
					properties.put(PropertyName.FINISH.toString(), true);
					properties.put(PropertyName.WHETHER.toString(), false);
					properties.put(PropertyName.FINISHYEAR.toString(), calendar.YEAR);
					properties.put(PropertyName.FINISHMONTH.toString(), calendar.MONTH);
					properties.put(PropertyName.FINISHDATE.toString(), calendar.DATE);
					properties.put(PropertyName.FINISHWEEK.toString(), calendar.WEEK_OF_YEAR);
					properties.put(PropertyName.FINISHOUR.toString(), calendar.HOUR_OF_DAY);
					properties.put(PropertyName.FINISHMINUTE.toString(), calendar.MINUTE);
				}
			}
		}
		Main.mainFrame.actionPerformed(e);
	}
}
