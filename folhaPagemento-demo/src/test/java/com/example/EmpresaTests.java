package com.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import com.example.repository.EmpresaRepository;



@TestInstance(Lifecycle.PER_CLASS)
@DisplayName("Empresa Controller")
class EmpresaTests {
	
	@Autowired
	private EmpresaRepository repository;
	
	@Autowired
	protected TestRestTemplate restTemplate;

	@Test
	void contextLoads() {
	}
	
//	protected org.springframework.http.HttpEntity<?> getRequestEntity() {
//		return new HttpEntity<>(getHeaders(accessToken.getToken()));
//	}

	public HttpHeaders getHeaders(final String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", "Bearer " + token);

		return headers;
	}
	
	@Test
	@DisplayName("quando nenhum filtro é fornecido")
	public void GetAllCompany() {
		
//		ContaCorrente conta = new ContaCorrente(1.500);
//		
//		Empresa empresa = new Empresa("ItauTesteTeste","Itau@gamail.com",conta);
//
//		repository.save(empresa);
//		
//		System.out.println("------------------------------");
		
	//	String url = "http://localhost:8080/api/empresa/all";
		
	//	ResponseEntity<List<EmpresaDTO>> responseGet =  restTemplate.exchange(url, HttpMethod.GET, null, null);
		
	}

}
