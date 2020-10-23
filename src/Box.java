package src;

import java.util.Iterator; //集合の要素に順番にアクセスするインターフェース
import java.util.LinkedList; //データ格納できる
import java.util.List; //Listインターフェース、宣言は可だがインスタント生成は不可

public class Box {
	private List tasks;
	public Box() {
		this.tasks = new LinkedList();
	}

	public void addTask(String name, String description, TaskProperty p) { //Taskのコンストラクタが変更になったので、ここも変更
		Task task = new Task(name, description, p);
		this.tasks.add(task); //リスト最後にタスク追加している
	}

	public Task getTask(String name) { //タスク検索
		for(Iterator i = this.tasks.iterator(); i.hasNext();) { //hasNext()次の要素が有ればtrue無ければfalse、全ての要素で繰り返す
			Task task = (Task) i.next(); //next()次の要素を返す
			if(task.getName().equals(name)) { //登録されたタスク名と、検索で入力されたタスク名が同じなら返す
				return task;
			}
		}
		return null;
	}

	public List getTasks() {return this.tasks;} //タスクのリスト一覧getメソッド

	public List search(TaskProperty searchProperty) {
		List matchingTasks = new LinkedList();
		for(Iterator i = this.tasks.iterator(); i.hasNext();) { //hasNext()次の要素が有ればtrue無ければfalse、全ての要素で繰り返す
			Task task = (Task) i.next(); //next()次の要素を返す
			if(task.getTaskProperty().matches(searchProperty))
					matchingTasks.add(task); //条件に一致したものをmatchingTasksに追加
		}
		return matchingTasks;
	}
}
