package es.eoi.mundobancario.service;

import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Movimiento;

import java.util.List;

/**
 * Cuenta service
 * ===============
 * 
 * Service for the Cuenta repository.
 * 
 * @author Carlos Sanchez <karlos.sangar@gmail.com>
 */
public interface CuentaService extends IService<Cuenta, String> {
    /**
     * Find and returns all Cuentas with negative balance.
     *
     * @return Negative balanaced Cuentas.
     */
    List<Cuenta> findDeudoras();

    /**
     * Adds a movimiento to the cuenta.
     *
     * @param id         Cuenta Id.
     * @param movimiento Movimiento to add.
     *
     * @return Created movimiento.
     */
    Movimiento movimiento(String id, Movimiento movimiento);
}
