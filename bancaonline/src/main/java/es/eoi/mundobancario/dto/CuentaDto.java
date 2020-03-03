package es.eoi.mundobancario.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CuentaDto {

	private int numCuenta;
	
	private String alias;
	
	private double saldo;
	
	private ClienteDto cliente;
	
	private List<MovimientoDto> movimientos;
	
	private List<PrestamoDto> prestamos;
}