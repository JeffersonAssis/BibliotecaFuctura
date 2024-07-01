package com.fuctura.biblioteca;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.fuctura.biblioteca.config.ProfileDev;
import com.fuctura.biblioteca.service.DBService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class BibliotecaApplicationTests {

	@Test
	void contextLoads() {
		
			
	}
	
		  @Mock
	    private DBService dbService;

	    @InjectMocks
	    private ProfileDev profileTest;

	    @Test
	    public void testInstanceDB() {
	        // Configuração do mock para retornar "create" para a propriedade spring.jpa.hibernate.ddl-auto
	        when(profileTest.ddl).thenReturn("create");

	        // Chama o método instanceDB
	        profileTest.instanceDB();

	        // Verifica se o método instanceDB do DBService foi chamado
	        verify(dbService).instanceDB();
	    }
}
