package es.eoi.mundobancario.dto;

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
public class NewClienteDto {
	
	private int id;
	
	private String nombre;
	
	private String usuario;
	
	private String pass;
	
	private String email;
	
}
