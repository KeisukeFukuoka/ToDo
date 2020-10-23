package src;

public class Task {
	private String name; //タスク名
	private String description; //タスクの説明
	private TaskProperty properties; //タスクの説明
	
	Task(String name, String description, TaskProperty p){ //TaskPropertyを受け取るコンストラクタに変更
		this.name = name;
		this.description = description;
		this.properties = p;
	}
	public String getName() {return name;} //getメソッド
	public String getDescription() {return description;}
	public TaskProperty getTaskProperty() {return properties;}	
	public void setName (String n) {this.name = n;} //setメソッド (追加登録時？)
	public void setDescription (String d) {this.description = d;}
	public void setTaskProperty (TaskProperty p) {this.properties = p;}	

}
