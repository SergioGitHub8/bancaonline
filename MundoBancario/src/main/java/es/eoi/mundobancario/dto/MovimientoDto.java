package es.eoi.mundobancario.dto;

import java.util.Calendar;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovimientoDto {

	int id;
	String descripcion;
	Calendar fecha;
	float importe;
	TipoMovimientoDto tipo;

}
