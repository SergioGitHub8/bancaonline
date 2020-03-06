package es.eoi.mundobancario.Service;

import java.util.List;
import java.util.Optional;

import es.eoi.mundobancario.entity.Cliente;
import es.eoi.mundobancario.entity.Prestamo;

public interface PrestamoService {
	
	public List<Prestamo> findAllVivos();
	
	public List<Prestamo> findAllAmortizados();

	public Prestamo create(Prestamo prestamo);

	public Optional<Prestamo> findById(int id);

	public Prestamo update(Prestamo prestamo);

	public void remove(int id);
}