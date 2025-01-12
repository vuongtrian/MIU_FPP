package edu.miu.cs489.dental_surgeries_appointment.repository;

import edu.miu.cs489.dental_surgeries_appointment.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
