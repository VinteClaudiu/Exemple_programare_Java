package exemplu_jaxb;

import java.util.Map;
import java.util.TreeMap;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="studenti")

public class StudentMap {
	private Map<String, Student> studentMap;

	public StudentMap() {
		super();
		this.studentMap = new TreeMap<String, Student>();
	}
	
	public Map<String, Student> get() {
		return this.studentMap;
	}
	
	public void set(Map<String, Student> studentMap) {
		this.studentMap = studentMap;
	}
	
}
