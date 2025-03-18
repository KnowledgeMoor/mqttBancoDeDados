package br.edu.iftm.mqtt_abelhas.repository;

import br.edu.iftm.mqtt_abelhas.entity.SensorData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorDataRepository extends JpaRepository<SensorData, Long> {
}
