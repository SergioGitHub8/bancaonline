package es.eoi.mundobancario.controller;

import java.util.List;
import java.util.stream.Collectors;

import es.eoi.mundobancario.dto.LoginDto;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.eoi.mundobancario.dto.CuentaDto;
import es.eoi.mundobancario.dto.FullClienteDto;
import es.eoi.mundobancario.entity.Cliente;
import es.eoi.mundobancario.service.ClienteService;
import es.eoi.mundobancario.service.CuentaService;
import lombok.RequiredArgsConstructor;

/**
 * Cliente controller ===============
 * 
 * Controller for the ClienteDTO.
 * 
 * @author Carlos Sanchez <karlos.sangar@gmail.com>
 */

@RequiredArgsConstructor
@RequestMapping("/clientes")
@RestController
public class ClienteController implements IController<ClienteDto, Integer> {
	private final ModelMapper mapper;
	private final ClienteService clienteService;
	private final CuentaService cuentaService;

	/**
	 * Devuelve un listado con todos los clientes sin la contraseña
	 * 
	 */
	@GetMapping
	public List<ClienteDto> findAll() {
		return clienteService.find().stream().map(c -> mapper.map(c, ClienteDto.class))
				.collect(Collectors.toList());

	}

	/**
	 * Devuelve el cliente solicitado por id sin la contraseña.
	 * 
	 */
	@GetMapping("/{id}")
<<<<<<< HEAD
	public FullClienteDto findById(@PathVariable Integer id) {
		return mapper.map(clienteService.find(id).orElseThrow(RuntimeException::new), FullClienteDto.class);
=======
	public ClienteDto findById(@PathVariable Integer id) {
		return mapper.map(
				clienteService.find(id).orElseThrow(RuntimeException::new),
				ClienteDto.class
		);
	}

	/**
	 * Creates an entity.
	 *
	 * @param entity Entity to create.
	 *
	 * @return Created entity.
	 */
	@Override
	public ClienteDto create(ClienteDto entity) {
		return null;
>>>>>>> 538ef91967123b6a44646889a30dab162dc77103
	}

	/**
	 * Devuelve el cliente solicitado.
	 * 
	 */
	@GetMapping("/login")
<<<<<<< HEAD
	public FullClienteDto login(@PathVariable Integer id, String nombre, String email, String usuario,
			@RequestBody FullClienteDto entity) {
		Cliente cliente = clienteService.find(id).orElseThrow(RuntimeException::new);
		cliente.getNombre();
		cliente.getEmail();
		cliente.get
		
		return mapper.map(clienteService..stream().map(c -> mapper.map(c, FullClienteDto.class)));
=======
	public ClienteDto login(@RequestBody LoginDto cliente) {
		return mapper.map(
				clienteService.login(cliente.getUsuario(), cliente.getPass()),
				ClienteDto.class
		);
>>>>>>> 538ef91967123b6a44646889a30dab162dc77103
	}

	/**
	 * Modifica un cliente.
	 * 
	 */
	@PutMapping("/{id}")
<<<<<<< HEAD
	public FullClienteDto update(@PathVariable Integer id, @RequestBody FullClienteDto entity) {
		Cliente cliente = clienteService.find(id).orElseThrow(RuntimeException::new);
=======
	public ClienteDto update(@PathVariable Integer id, @RequestBody ClienteDto entity) {
		Cliente cliente = mapper.map(findById(id), Cliente.class);
>>>>>>> 538ef91967123b6a44646889a30dab162dc77103
		cliente.setEmail(entity.getEmail());
		return mapper.map(clienteService.update(cliente), ClienteDto.class);
	}

	/**
	 * Devuelve las cuentas del cliente solicitado.
	 * 
	 */
	@GetMapping("/{id}/cuentas")
	public List<CuentaDto> listCuentas(@PathVariable Integer id) {
		return cuentaService.findAllByClientesId(id).stream().map(c -> mapper.map(c, CuentaDto.class))
				.collect(Collectors.toList());
	}

	/**
	 * Crea un nuevo cliente.
	 * 
	 */
<<<<<<< HEAD
	@PostMapping("")
	public FullClienteDto create(@RequestBody FullClienteDto entity) {
		return mapper.map(clienteService.create(mapper.map(entity, Cliente.class)), FullClienteDto.class);
	}

=======
	@PostMapping
	public ClienteDto create(@RequestBody FullClienteDto cliente) {
		return mapper.map(
				clienteService.create(mapper.map(cliente, Cliente.class)),
				ClienteDto.class
		);
	}
>>>>>>> 538ef91967123b6a44646889a30dab162dc77103
}
