package es.eoi.mundobancario.Service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import es.eoi.mundobancario.Repository.CuentasRepository;
import es.eoi.mundobancario.entity.Cuenta;

@Service
public class CuentaServiceImpl implements CuentaService {

	CuentasRepository cuentasrepository;

	public Cuenta CreateCuenta(Cuenta cuenta) {
		return cuentasrepository.save(cuenta);
	}

	public Optional<Cuenta> findCuentaByNumCuenta(int numCuenta) {
		return cuentasrepository.findById(numCuenta);
	}

	public Cuenta updateCuenta(Cuenta cuenta) {
		return cuentasrepository.save(cuenta);
	}

	public void removeCuenta(int numCuenta) {
		cuentasrepository.deleteById(numCuenta);
	}
}