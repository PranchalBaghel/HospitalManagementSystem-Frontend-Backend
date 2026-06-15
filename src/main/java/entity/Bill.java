package entity;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bill {

    private int id;
    private int patientId;
    private double amount;
    private boolean paid;
    private Timestamp createdAt;
    private String patientName;
    
	public Bill(int patientId, double amount, boolean paid, Timestamp createdAt) {
		super();
		this.patientId = patientId;
		this.amount = amount;
		this.paid = paid;
		this.createdAt = createdAt;
	}
    
    

}