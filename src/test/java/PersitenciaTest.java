import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import DTOs.PlanDTO;
import Entities.CDR;
import Entities.Linea;
import Entities.Plan;
import Entities.PlanPrepago;
import Entities.PlanWow;
import Entities.Tarificacion;
import Gateways.PersistenciaCDR;
import Gateways.PersistenciaLinea;
import Gateways.PersistenciaPlan;
import Gateways.PersistenciaTarificacion;
import Repositories.PersistenciaCDRArchivo;
import Repositories.PersistenciaCDRSql;
import Repositories.PersistenciaLineaArchivo;
import Repositories.PersistenciaLineaSql;
import Repositories.PersistenciaPlanSql;
import Repositories.PersistenciaTarificacionSql;

class PersitenciaTest {

	
	@Test
	void SQLSaveCDRTest() {
		CDR cdr=new CDR("5555","2222","01-01-2020","22:00","2:30");
		PersistenciaCDR persistencia1= new PersistenciaCDRSql();
		persistencia1.guardarCDR(cdr,1);
		CDR cdr2=persistencia1.getCDR(cdr.getId());
		assertEquals(cdr.getFecha(),cdr2.getFecha());
		assertEquals(cdr2.getId(),persistencia1.getLastId());
		assertEquals(cdr.getHoraLlamada(),cdr.getHoraLlamada());
		int cant=persistencia1.saveFromArchive("ejemplo_entrada_cdrs.txt", 1);
		assertEquals(6,cant);
		List<CDR> lista= persistencia1.getCDRSbyTarificationId(1);
		assertEquals(cdr2.getId(),lista.get(0).getId());
	} 

	@Test
	void SQLSaveLineaTest() {
		Plan plan=new PlanPrepago();
		Plan plan2=new PlanWow();
		Linea linea=new Linea("5555","Jose",plan);
		PersistenciaLinea persistencia2=new PersistenciaLineaSql();
		persistencia2.guardarLinea(linea);
		Linea linea2=persistencia2.getLinea("5555");
		assertEquals(linea.getNumero(),linea2.getNumero());
		Linea linea3=new Linea("4444","Andres",plan2);
		linea3.addNumeroAmigo("1111");
		persistencia2.guardarLinea(linea3);
		Linea linea4=persistencia2.getLinea("4444");
		assertEquals(linea3.getNumero(),linea4.getNumero()); 
		int cant=persistencia2.saveFromArchive("ejemplo_entrada_lineas.txt");
		assertEquals(3,cant);
	}

	@Test
	void ArchivosSaveCDRTest() {
		CDR cdr=new CDR("5555","2222","01-01-2020","22:00","2:30");
		PersistenciaCDR persistencia1= new PersistenciaCDRArchivo();
		persistencia1.guardarCDR(cdr,1);
		CDR cdr2=persistencia1.getCDR(cdr.getId());
		assertEquals(cdr.getId(),cdr2.getId());
		assertEquals(cdr.getId(),persistencia1.getLastId());
		CDR cdr3=new CDR("5555","2222","01-01-2020","22:00","2:30");
		persistencia1.guardarCDR(cdr3,1);
		CDR cdr4=persistencia1.getCDR(cdr3.getId());
		assertEquals(cdr3.getId(),cdr4.getId());
		assertEquals(cdr3.getId(),persistencia1.getLastId());
		int cant=persistencia1.saveFromArchive("ejemplo_entrada_cdrs.txt", 1);
		assertEquals(6,cant);
		List<CDR> lista = persistencia1.getCDRSbyTarificationId(1);
		//assertEquals(cdr.getId(),lista.get(0).getId());
	}
	@Test
	void ArchivosSaveLineaTest() {
		Plan plan=new PlanPrepago();
		Plan plan2=new PlanWow();
		Linea linea=new Linea("5555","Jose",plan);
		PersistenciaLinea persistencia2=new PersistenciaLineaArchivo();
		persistencia2.guardarLinea(linea);
		Linea linea2=persistencia2.getLinea("5555");
		assertEquals(linea.getNumero(),linea2.getNumero());
		Linea linea3=new Linea("4444","Andres",plan2);
		linea3.addNumeroAmigo("1111");
		persistencia2.guardarLinea(linea3);
		Linea linea4=persistencia2.getLinea("4444");
		assertEquals(linea3.getNumero(),linea4.getNumero());
		int cant=persistencia2.saveFromArchive("ejemplo_entrada_lineas.txt");
		assertEquals(3,cant);
	}
	
	@Test
	void SQLSaveTarificacionTest() {
		Tarificacion tarificacion= new Tarificacion("12-02-2020","sql");
		PersistenciaTarificacion persistencia1=new PersistenciaTarificacionSql();
		//int id=persistencia1.getNextId();
		//tarificacion.setId(id);
		persistencia1.guardarTarificacion(tarificacion);
		Tarificacion tarificacion2=persistencia1.getTarificacionById(tarificacion.getId());
		assertEquals(tarificacion.getFecha(),tarificacion2.getFecha());
		assertFalse(persistencia1.getTarificaciones().isEmpty());
	}
	
	/*@Test
	void DBshouldReturnPlanTableExistsIfItIsAlreadyStoredinDB() {
		PersistenciaPlan persi = new PersistenciaPlanSql();
		boolean expected = true;
		assertEquals(expected,persi.planExists("PREPAGO"));
	}*/
	
	@Test
	void DBreturnsTrueWhenNewPlanIsSavedOrAlreadyExists() {
		PersistenciaPlan persi = new PersistenciaPlanSql();
		PlanDTO dto = new PlanDTO("PREPAGO","HorarioReducido+HorarioNormal+HorarioSuperReducido","1.45+0.95+0.70");
		boolean expected = true;
		assertEquals(expected,persi.savePlan(dto));
		List<Plan> actual = persi.loadPlans();
		Plan expected1 = actual.get(0);
		assertEquals(expected1,actual.get(0));
		boolean expected2 = true;
		assertEquals(expected2,persi.planExists("PREPAGO"));
	}
	
	/*@Test
	void loadsAListOfPlans() {
		PersistenciaPlan persi = new PersistenciaPlanSql();
		List<Plan> actual = persi.loadPlans();
		Plan expected = actual.get(0);
		assertEquals(expected,actual.get(0));
	}*/
	
	@Test
	void DBshouldReturnPlanTableExistsIfItIsNotAlreadyStoredinDB() {
		PersistenciaPlan persi = new PersistenciaPlanSql();
		boolean expected = false;
		assertEquals(expected,persi.planExists("POSTPAGO"));
	}
	
	
}
