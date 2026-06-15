package entity;

import java.sql.Date;
import java.sql.Time;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
	 private int id;

	    private int patientId;
	    private int doctorId;

	    private Date appointmentDate;
	    private Time appointmentTime;
	    private String status;
	    
	    // Dashboard display ke liye (JOIN ke baad)
	    private String patientName;
	    private String doctorName;
	    
		public Appointment(int patientId, int doctorId, Date appointmentDate, Time appointmentTime, String status,
				String patientName, String doctorName) {
			super();
			this.patientId = patientId;
			this.doctorId = doctorId;
			this.appointmentDate = appointmentDate;
			this.appointmentTime = appointmentTime;
			this.status = status;
			this.patientName = patientName;
			this.doctorName = doctorName;
		}
	    
	    
    
}
