package es.eoi.mundobancario.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.eoi.mundobancario.entity.Amortizacion;

public interface AmortizacionRepository extends JpaRepository<Amortizacion, Integer> {

}