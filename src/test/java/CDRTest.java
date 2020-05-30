import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CDRTest {

	@Test
	void SetAndGetTest() {
		CDR cdr=new CDR();
		cdr.setNumeroLlamante("5555");
		cdr.setNumeroLlamado("2222");
		cdr.setDuracionLlamada(3.5);
		cdr.setHoraLlamada(22);
		cdr.setId(200);
		cdr.setTarifa(1.0);
		assertEquals("5555", cdr.getNumeroLlamante());
		assertEquals("2222",cdr.getNumeroLlamado());
		assertEquals(22,cdr.getHoraLlamada());
		assertEquals(3.5,cdr.getDuracionLlamada(), 0.001);
		assertEquals(200,cdr.getId());
		assertEquals(1.0,cdr.getTarifa(), 0.001);
	}
	@Test
	void ConstructorTest() {
		CDR cdr=new CDR("5555","2222",22,2.5);
		assertEquals("5555", cdr.getNumeroLlamante());
		assertEquals("2222",cdr.getNumeroLlamado());
		assertEquals(22,cdr.getHoraLlamada());
		assertEquals(2.5,cdr.getDuracionLlamada(), 0.001);
		//assertEquals(6,cdr.getId());
		assertEquals(0.0,cdr.getTarifa(), 0.001);
		//CDR cdr2=new CDR("333","111",22,2.5);
		//assertEquals(7,cdr2.getId());
	}
	@Test
	void CalculateTarifaTest() {
		CDR cdr1=new CDR("5555","1111",22,1.0);
		CDR cdr2=new CDR("5555","2222",5,1.0);
		CDR cdr3=new CDR("5555","2222",15,1.0);
		Plan prepago=new PlanPrepago();
		Plan postpago=new PlanPostpago();
		Plan wow= new PlanWow();
		Linea linea1=new Linea("5555","Javi",prepago);
		assertEquals(1.45,cdr3.calcularTarifaParaLinea(linea1), 0.001);
		assertEquals(0.70,cdr2.calcularTarifaParaLinea(linea1), 0.001);
		assertEquals(0.95,cdr1.calcularTarifaParaLinea(linea1), 0.001);
		linea1.setPlan(postpago);
		assertEquals(0.99,cdr3.calcularTarifaParaLinea(linea1), 0.001);
		linea1.setPlan(wow);
		linea1.addNumeroAmigo("2222");
		assertEquals(0.0,cdr3.calcularTarifaParaLinea(linea1), 0.001);
		assertEquals(0.99,cdr1.calcularTarifaParaLinea(linea1), 0.001);
	}

}
