package es.eoi.mundobancario.dto;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrestamoDTO {

	private String descripcion;
	private Date fecha;
	private float importe;
	private int plazos;

}