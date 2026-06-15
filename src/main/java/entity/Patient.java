package entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {

    private int id;
    private String name;
    private String email;
    private String phone;
    private String gender;
    private int age;
    private String address;
    private String disease;
    private String blood;
    
	public Patient(String name, String email, String phone, String gender, int age, String address, String disease,
			String blood) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.gender = gender;
		this.age = age;
		this.address = address;
		this.disease = disease;
		this.blood = blood;
	}
    
    
}