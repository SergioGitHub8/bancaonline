package es.eoi.app.controller;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import es.eoi.app.dto.ClienteBasicoDto;
import es.eoi.app.dto.ClienteDto;
import es.eoi.app.dto.NewPrestamoDto;
import es.eoi.app.dto.PrestamoDto;

@Controller
public class MenuController {
		
	private final Scanner scan= new Scanner(System.in);
	private final RestTemplate restTemplate = new RestTemplate();
	private static Gson gson;
	
	@Value("${bancaonline.server.url}")
	public String url;
	
	public String printMainMenu() {	
		
		
		System.out.println("*************************************************");
		System.out.println("Bienvenido a tu aplicación de Banca Flipante!!!!!");
		System.out.println("*************************************************");
		System.out.println();
		System.out.println("¿Que operacion desea realizar?");
		System.out.println();
		System.out.println("1 - Modificar información de usuario");
		System.out.println("2 - Consultar mis cuentas");
		System.out.println("3 - Gestionar/ solicitar prestamos");
		System.out.println("4 - amortizar prestamos");
		System.out.println("5 - Generacion de reportes");
		System.out.println("6 - Exit");
		System.out.print("Opcion: ");
		System.out.println();
		
		return scan.nextLine();
	}
	
	public void menu() {
		
		boolean exit = false;
		String option = "";
		String idCliente = "";
		String idCuenta = "";
		
		do {
			option = printMainMenu();
			switch (option) {
			case "1":
				updateCliente(idCliente);
				break;
				
			case "2":				
				getCuentasCliente(idCliente);
				break;
			case "3":
				String uri = url.concat("cuentas/").concat(idCuenta).concat("/prestamos");
				
				System.out.println("Quiere consultar los prestamos o solicitar(1/2): ");
				String opt = scan.nextLine();
				if(opt.equals("1")) {
					System.out.println("Introduzca el numero de cuenta para consultar los prestamos: ");
					idCuenta = scan.nextLine();
					HttpHeaders headers = new HttpHeaders();
					HttpEntity<List<PrestamoDto>> requestEntity = new HttpEntity<>(headers);
					ResponseEntity<List<PrestamoDto>> prestamo = restTemplate
							.exchange(
									uri, 
									HttpMethod.GET, 
									requestEntity, 
									new ParameterizedTypeReference <List<PrestamoDto>>(){});
					
					gson = new GsonBuilder().setPrettyPrinting().create();
					
					System.out.println(gson.toJson(prestamo.getBody()));				
					
				}
				else if(opt.equals("2")) {
					System.out.println("Introduzca el numero de cuenta para solicitar un préstamo: ");
					idCuenta = scan.nextLine();
					
					
					NewPrestamoDto newPrestamo = new NewPrestamoDto();
					System.out.println("Descripción del préstamo: ");
					String descripcion = scan.nextLine();
				
					long fecha = Instant.now().toEpochMilli();
					
					System.out.println("Importe del préstamo: ");
					double importe = scan.nextDouble();
					
					System.out.println("Plazos del préstamo: ");
					String plazos = scan.nextLine();
					
					System.out.println("Id de la cuenta para el préstamo: ");
					
					
					
				}
				break;
			case "4":
				
				HttpEntity<ClienteDto> requeste = new HttpEntity<>(new ClienteDto());
				ClienteDto clientee = restTemplate.postForObject(url + "/cuentas/ejecutarAmortizacionesDiarias", requeste, ClienteDto.class);
					
				break;
			case "5":

				break;
				
			case "6":
				exit = true;
				break;
			default:
				break;
			}
		}while(!exit);
	}
	
	private void updateCliente(String idCliente) {
		ClienteBasicoDto clienteDto = null;
		
		System.out.println("Introduzca el id de usuario a modificar: ");
		idCliente= scan.nextLine();
		String uri = url.concat("clientes/").concat(idCliente);
		try {
			clienteDto = restTemplate.getForObject(uri, ClienteBasicoDto.class);
			
		} catch (RestClientException e) {
			System.err.println(e.getMessage());
		}
		
		if(clienteDto == null) {			
			System.out.println("Cliente not found");
			return;
		}
		
		System.out.println("Introduzca el nuevo email(" + clienteDto.getEmail() + "): ");
		String email = scan.nextLine();
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(uri)
				.queryParam("email",email);

		Map<String, String> params = new HashMap<String, String>();
		params.put("email", email);
		HttpEntity<Map<String, String>> requestUpdate = new HttpEntity<Map<String,String>>(params);
		ResponseEntity<ClienteBasicoDto> response = restTemplate.exchange(
				builder.toUriString(), 
				HttpMethod.PUT, 
				requestUpdate, 
				ClienteBasicoDto.class, 
				params);
		
		if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println("response received");
            System.out.println(response.getBody());
        } else {
            System.out.println("error occurred");
            System.out.println(response.getStatusCode());
        }
	}
	
	private void getCuentasCliente(String idCliente) {
		ClienteDto clienteDto = null;
		System.out.println("Introduzca el id del cliente para ver sus cuentas: ");
		idCliente= scan.nextLine();
		
		gson = new GsonBuilder().setPrettyPrinting().create();
		String uri = url.concat("clientes/").concat(idCliente).concat("/cuentas");
		try {
			clienteDto = restTemplate.getForObject(uri, ClienteDto.class);
			
			System.out.println(gson.toJson(clienteDto, ClienteDto.class));
		} catch (RestClientException e) {
			System.err.println(e.getMessage());
		}
		
		if(clienteDto == null) {			
			System.out.println("Cliente not found");
			return;
		}
	}
	
}
