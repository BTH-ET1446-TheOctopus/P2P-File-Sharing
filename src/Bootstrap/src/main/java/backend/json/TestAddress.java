package backend.json;

public class TestAddress {
	private String name;
	private String surename;
	private int age;
	
	public TestAddress(){	
	}
	
	public TestAddress(String fName, String lName, int age){
		this.name =  fName;
		this.surename = lName;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurename() {
		return surename;
	}
	public void setSurename(String surename) {
		this.surename = surename;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
