import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import DTOs.InputLineaTelefonicaDTO;
import Entities.CDR;
import Entities.LineaTelefonica;
import Entities.Plan;
import Entities.PlanPrepago;
import Entities.PlanWow;
import Entities.TarifaFija;
import Entities.TarifaPorHoras;
import Entities.Tarificacion;
import Gateways.PersistenciaCDR;
import Gateways.PersistenciaLineaTelefonica;
import Gateways.PersistenciaTarificacion;
import Repositories.CDRFileRepository;
import Repositories.CDRSqlRepository;
import Repositories.LineaFileRepository;
import Repositories.LineaSqlRepository;
import Repositories.TarificacionFileRepository;
import Repositories.TarificacionSqlRepository;

class PersitenciaTest {
 
	
	@Test
	void SQLSaveCDRTest() {
		CDR cdr=new CDR("5555","2222","01-01-2020","22:00","2:30");
		PersistenciaCDR persistencia1= new CDRSqlRepository();
		persistencia1.guardarCDR(cdr,1);
		CDR cdr2=persistencia1.getCDR(cdr.getId());
		assertEquals(cdr.getFecha(),cdr2.getFecha());
		assertEquals(cdr2.getId(),persistencia1.getLastId());
		assertEquals(cdr.getHoraLlamada(),cdr.getHoraLlamada());
		
		assertFalse(persistencia1.getCDRSbyTelfOrigen("5555").isEmpty());
		assertFalse(persistencia1.getCDRSbyTarificationId(1).isEmpty());
	} 

	@Test
	void SQLSaveLineaTest() {
		InputLineaTelefonicaDTO dto;
		Plan plan=new PlanPrepago(new TarifaPorHoras());
		
		LineaTelefonica lineaTelefonica=new LineaTelefonica("5555","Jose",plan);
		dto=new InputLineaTelefonicaDTO(lineaTelefonica);
		PersistenciaLineaTelefonica persistencia2=new LineaSqlRepository();
		persistencia2.guardarLineaTelefonica(dto);
		LineaTelefonica linea2=persistencia2.getLineaTelefonicaByNumero("5555");
		assertEquals(lineaTelefonica.getNumero(),linea2.getNumero());
		
		List<String>numerosAmigos=new ArrayList<String>();
		numerosAmigos.add("1111");
		Plan plan2=new PlanWow(new TarifaFija(),numerosAmigos);
		LineaTelefonica linea3=new LineaTelefonica("4444","Andres",plan2);
		dto=new InputLineaTelefonicaDTO(linea3,numerosAmigos);
		persistencia2.guardarLineaTelefonica(dto);
		LineaTelefonica linea4=persistencia2.getLineaTelefonicaByNumero("4444");
		assertEquals(linea3.getNumero(),linea4.getNumero()); 
		assertFalse(persistencia2.getLineasTelefonicas().isEmpty());
	}

	@Test
	void FilesSaveCDRTest() {
		CDR cdr=new CDR("5555","2222","01-01-2020","22:00","2:30");
		PersistenciaCDR persistencia1= new CDRFileRepository();
		persistencia1.guardarCDR(cdr,1);
		CDR cdr2=persistencia1.getCDR(cdr.getId());
		assertEquals(cdr.getId(),cdr2.getId());
		assertEquals(cdr.getId(),persistencia1.getLastId());
		CDR cdr3=new CDR("5555","2222","01-01-2020","22:00","2:30");
		persistencia1.guardarCDR(cdr3,1);
		CDR cdr4=persistencia1.getCDR(cdr3.getId());
		assertEquals(cdr3.getId(),cdr4.getId());
		assertEquals(cdr3.getId(),persistencia1.getLastId());
		
		assertFalse(persistencia1.getCDRSbyTarificationId(1).isEmpty());
		assertFalse(persistencia1.getCDRSbyTelfOrigen("5555").isEmpty());
	}
	@Test
	void FilesSaveLineaTest() {
		InputLineaTelefonicaDTO dto;
		Plan plan=new PlanPrepago(new TarifaPorHoras());
		
		LineaTelefonica lineaTelefonica=new LineaTelefonica("5555","Jose",plan);
		dto=new InputLineaTelefonicaDTO(lineaTelefonica);
		PersistenciaLineaTelefonica persistencia2=new LineaFileRepository();
		persistencia2.guardarLineaTelefonica(dto);
		LineaTelefonica linea2=persistencia2.getLineaTelefonicaByNumero("5555");
		assertEquals(lineaTelefonica.getNumero(),linea2.getNumero());
		
		List<String>numerosAmigos=new ArrayList<String>();
		numerosAmigos.add("1111");
		Plan plan2=new PlanWow(new TarifaFija(),numerosAmigos);
		LineaTelefonica linea3=new LineaTelefonica("4444","Andres",plan2);
		dto=new InputLineaTelefonicaDTO(linea3,numerosAmigos);
		persistencia2.guardarLineaTelefonica(dto);
		LineaTelefonica linea4=persistencia2.getLineaTelefonicaByNumero("4444");
		assertEquals(linea3.getNumero(),linea4.getNumero()); 
		assertFalse(persistencia2.getLineasTelefonicas().isEmpty());
		
	}

	/*@Test
	void SQLSaveTarificacionTest() {
		Tarificacion tarificacion= new Tarificacion("sql");
		PersistenciaTarificacion persistencia1=new TarificacionSqlRepository();
		
		persistencia1.guardarTarificacion(tarificacion);
		Tarificacion tarificacion2=persistencia1.getTarificacionById(tarificacion.getId());
		assertEquals(tarificacion.getFecha(),tarificacion2.getFecha());
		assertFalse(persistencia1.getTarificaciones().isEmpty());
	}
	
	@Test
	void FilesSaveTarificacionTest() {
		Tarificacion tarificacion= new Tarificacion("archivo");
		PersistenciaTarificacion persistencia1=new TarificacionFileRepository();

		persistencia1.guardarTarificacion(tarificacion);
		Tarificacion tarificacion2=persistencia1.getTarificacionById(tarificacion.getId());
		assertEquals(tarificacion.getFecha(),tarificacion2.getFecha());
		assertFalse(persistencia1.getTarificaciones().isEmpty());
	}*/
	
}
