import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Entities.CDR;
import Entities.Linea;
import Entities.Plan;
import Entities.PlanPostpago;
import Entities.PlanPrepago;
import Entities.PlanWow;

class CDRTest {
 
	@Test
	void SetAndGetTest() {
		CDR cdr=new CDR();
		cdr.setNumeroLlamante("5555");
		cdr.setNumeroLlamado("2222");
		cdr.setDuracionLlamada("3:30");
		cdr.setHoraLlamada("22:00");
		cdr.setId(200);
		cdr.setTarifa(1.0);
		assertEquals("5555", cdr.getNumeroLlamante());
		assertEquals("2222",cdr.getNumeroLlamado());
		assertEquals("22:00",cdr.getHoraLlamada());
		assertEquals("3:30",cdr.getDuracionLlamada());
		assertEquals(200,cdr.getId());
		assertEquals(1.0,cdr.getTarifa(), 0.001);
	}
	@Test
	void ConstructorTest() {
		CDR cdr=new CDR("5555","2222","01-01-2020","22:00","2:30");
		assertEquals("5555", cdr.getNumeroLlamante());
		assertEquals("2222",cdr.getNumeroLlamado());
		assertEquals("22:00",cdr.getHoraLlamada());
		assertEquals("2:30",cdr.getDuracionLlamada());
		//assertEquals(6,cdr.getId());
		assertEquals(0.0,cdr.getTarifa(), 0.001);
		//CDR cdr2=new CDR("333","111",22,2.5);
		//assertEquals(7,cdr2.getId());
	}
	@Test
	void CalculateTarifaTest() {
		CDR cdr1=new CDR("5555","1111","01-01-2020","22:00","1:00");
		CDR cdr2=new CDR("5555","2222","01-01-2020","05:00","1:00");
		CDR cdr3=new CDR("5555","2222","01-01-2020","15:00","1:00");
		Plan prepago=new PlanPrepago();
		Plan postpago=new PlanPostpago();
		Plan wow= new PlanWow();
		Linea linea1=new Linea("5555","Javi",prepago);
		cdr3.calcularTarifaParaLinea(linea1);
		cdr2.calcularTarifaParaLinea(linea1);
		cdr1.calcularTarifaParaLinea(linea1);
		assertEquals(1.45,cdr3.getTarifa(), 0.001);
		assertEquals(0.70,cdr2.getTarifa(), 0.001);
		assertEquals(0.95,cdr1.getTarifa(), 0.001);
		linea1.setPlan(postpago);
		cdr3.calcularTarifaParaLinea(linea1);
		assertEquals(0.99,cdr3.getTarifa(), 0.001);
		linea1.setPlan(wow);
		linea1.addNumeroAmigo("2222");
		cdr3.calcularTarifaParaLinea(linea1);
		cdr1.calcularTarifaParaLinea(linea1);
		assertEquals(0.0,cdr3.getTarifa(), 0.001);
		assertEquals(0.99,cdr1.getTarifa(), 0.001);
	}

}
