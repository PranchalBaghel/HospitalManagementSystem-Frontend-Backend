package entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {

    private int id;
    private String name;
    private String email;
    private String phone;
    private String specialization;
    private int experience;
    private int departmentId;
    
    
    // Dashboard display ke liye (JOIN ke baad)
   	private int totalAppointments;
    
   	
	public Doctor(String name, String email, String phone, String specialization, int experience, int departmentId) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.specialization = specialization;
		this.experience = experience;
		this.departmentId = departmentId;
	}

	public Doctor(int id, String name, String email, String phone, String specialization, int experience,
			int departmentId) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.specialization = specialization;
		this.experience = experience;
		this.departmentId = departmentId;
	}  
	
	
    
}