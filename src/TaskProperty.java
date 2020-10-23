package src;

import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;

public class TaskProperty {
	private Map<?, ?> properties;

	public TaskProperty(Map<?, ?> properties) {
		if(properties == null) {
			this.properties = new HashMap<Object, Object>();
		} else {
			this.properties = new HashMap<Object, Object>(properties);			
		}
	}

	//propertyを１つ返す
	public Object getProperty(String propertyName) {
		return properties.get(propertyName);
	}

	//propertyを返す
	public Map<?, ?> getProperties() {
		return this.properties;
	}

	public boolean matches(TaskProperty otherProperty) {
		for(Iterator<?> i = otherProperty.getProperties().keySet().iterator(); i.hasNext();) {
			String propertyName = (String)i.next();
			if(this.properties.get(propertyName) == null)
				return false;
			if(!properties.get(propertyName).equals(otherProperty.getProperty(propertyName))) {
				return false;
			}
		}
		return true;
	}
}	
