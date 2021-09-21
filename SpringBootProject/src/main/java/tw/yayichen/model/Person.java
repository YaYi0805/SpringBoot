package tw.yayichen.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Person {

	@Value("${name}")
	private String name;
	
	@Value("${age}")
	private int age;
	
	public String getName() {
		return name;
	}
	public void setNameString(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}
