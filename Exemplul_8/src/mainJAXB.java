package exemplu_jaxb;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class mainJAXB {

	public static void main(String[] args) throws JAXBException {
		
		Map<String, Student> map = new TreeMap<String, Student>();
		
		Student stud1 = new Student();
		stud1.setCnp("1801203123456");
		stud1.setNume("Popescu Andrei");
		stud1.setGrupa(1043);
		stud1.setNotaPOO(9);
		stud1.setNotaSD(9);
		stud1.setNotaJava(10);
		
		Student stud2 = new Student();
		stud2.setCnp("2801203123456");
		stud2.setNume("Apopei Maria");
		stud2.setGrupa(1043);
		stud2.setNotaPOO(10);
		stud2.setNotaSD(9);
		stud2.setNotaJava(10);
		
		Student stud3 = new Student();
		stud3.setCnp("2901203123456");
		stud3.setNume("Panait Mihaela");
		stud3.setGrupa(1043);
		stud3.setNotaPOO(9);
		stud3.setNotaSD(10);
		stud3.setNotaJava(10);
		
		map.put(stud1.getCnp(), stud1);
		map.put(stud2.getCnp(), stud2);
		map.put(stud3.getCnp(), stud3);
		
		StudentMap studentOutMap = new StudentMap();
		studentOutMap.set(map);
		
		for (String cnp : studentOutMap.get().keySet()) {
			System.out.println(studentOutMap.get().get(cnp).toString());
		}
		
		//Marshalling
		
		JAXBContext jaxbContext = JAXBContext.newInstance(StudentMap.class);
		
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		
		marshaller.marshal(studentOutMap, System.out);
		marshaller.marshal(studentOutMap, new File("studenti.xml"));
		
		//Unmarshalling
		
		jaxbContext = JAXBContext.newInstance(StudentMap.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		
		StudentMap studentInMap = new StudentMap();
		studentInMap = (StudentMap) unmarshaller.unmarshal(new File("studenti.xml"));
		
		for (String cnp : studentInMap.get().keySet()) {
			System.out.println(studentInMap.get().get(cnp).toString());
		}
		marshaller.marshal(studentInMap, System.out);
		
		
	}

}
