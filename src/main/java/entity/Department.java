package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    private int id;
    private String name;
    private String description;
    
	public Department(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
    
    
}