import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import Entities.CDR;
import Entities.LineaTelefonica;
import Entities.Plan;
import Entities.PlanPostpago;
import Entities.PlanPrepago;
import Entities.PlanWow;
import Entities.TarifaFija;
import Entities.TarifaPorHoras;

class CDRTest {

	@Test
	void SetAndGetTest() {
		CDR cdr=new CDR();
		cdr.setTelfOrigen("5555");
		cdr.setTelfDestino("2222");
		cdr.setDuracionLlamada("3:30");
		cdr.setHoraLlamada("22:00");
		cdr.setFecha("01-01-2020");
		cdr.setId(200);
		cdr.setTarifa(1.0);
		assertEquals("5555", cdr.getTelfOrigen());
		assertEquals("2222",cdr.getTelfDestino());
		assertEquals("22:00",cdr.getHoraLlamada());
		assertEquals("3:30",cdr.getDuracionLlamada());
		assertEquals("01-01-2020",cdr.getFecha());
		assertEquals(200,cdr.getId());
		assertEquals(1.0,cdr.getTarifa(), 0.001);
	}
	@Test
	void ConstructorTest() {
		CDR cdr=new CDR("5555","2222","01-01-2020","22:00","2:30");
		assertEquals("5555", cdr.getTelfOrigen());
		assertEquals("2222",cdr.getTelfDestino());
		assertEquals("22:00",cdr.getHoraLlamada());
		assertEquals("2:30",cdr.getDuracionLlamada());
		assertEquals(0.0,cdr.getTarifa(), 0.001);
	}
	@Test
	void CalculateTarifaTest() {
		CDR cdr1=new CDR("5555","1111","01-01-2020","22:00","1:00");
		CDR cdr2=new CDR("5555","2222","01-01-2020","05:00","1:00");
		CDR cdr3=new CDR("5555","2222","01-01-2020","15:00","1:00");
		
		Plan prepago=new PlanPrepago(new TarifaPorHoras());
		Plan postpago=new PlanPostpago(new TarifaFija());
		List<String>numerosAmigos=new ArrayList<String>();
		numerosAmigos.add("2222");
		Plan wow= new PlanWow(new TarifaFija(),numerosAmigos);
		LineaTelefonica linea1=new LineaTelefonica("5555","Javi",prepago);
		
			//linea1.getPlan().setTarifa(new TarifaPorHoras());
		cdr3.calcularTarifaSegunLinea(linea1);
		cdr2.calcularTarifaSegunLinea(linea1);
		cdr1.calcularTarifaSegunLinea(linea1);
		assertEquals(1.45,cdr3.getTarifa(), 0.001);
		assertEquals(0.70,cdr2.getTarifa(), 0.001);
		assertEquals(0.95,cdr1.getTarifa(), 0.001);
		linea1.setPlan(postpago);
			//linea1.getPlan().setTarifa(new TarifaFija());
		cdr3.calcularTarifaSegunLinea(linea1);
		assertEquals(0.99,cdr3.getTarifa(), 0.001);
		linea1.setPlan(wow);
			//linea1.getPlan().setTarifa(new TarifaFija());
		//linea1.addNumeroAmigo("2222");
		cdr3.calcularTarifaSegunLinea(linea1);
		cdr1.calcularTarifaSegunLinea(linea1);
		assertEquals(0.0,cdr3.getTarifa(), 0.001);
		assertEquals(0.99,cdr1.getTarifa(), 0.001);
	}

}
