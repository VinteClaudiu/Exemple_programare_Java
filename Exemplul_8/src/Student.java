package exemplu_jaxb;

public class Student {
	private String cnp;
	private String nume;
	private int grupa;
	private int notaPOO;
	private int notaSD;
	private int notaJava;
	
	public Student(String cnp, String nume, int grupa, int notaPOO, int notaSD, int notaJava) {
		super();
		this.cnp = cnp;
		this.nume = nume;
		this.grupa = grupa;
		this.notaPOO = notaPOO;
		this.notaSD = notaSD;
		this.notaJava = notaJava;
	}

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getCnp() {
		return cnp;
	}
	public void setCnp(String cnp) {
		this.cnp = cnp;
	}
	public String getNume() {
		return nume;
	}
	public void setNume(String nume) {
		this.nume = nume;
	}
	public int getGrupa() {
		return grupa;
	}
	public void setGrupa(int grupa) {
		this.grupa = grupa;
	}
	public int getNotaPOO() {
		return notaPOO;
	}
	public void setNotaPOO(int notaPOO) {
		this.notaPOO = notaPOO;
	}
	public int getNotaSD() {
		return notaSD;
	}
	public void setNotaSD(int notaSD) {
		this.notaSD = notaSD;
	}
	public int getNotaJava() {
		return notaJava;
	}
	public void setNotaJava(int notaJava) {
		this.notaJava = notaJava;
	}

	@Override
	public String toString() {
		return "Student [cnp=" + cnp + ", nume=" + nume + ", grupa=" + grupa + ", notaPOO=" + notaPOO + ", notaSD="
				+ notaSD + ", notaJava=" + notaJava + "]";
	}
	
}
