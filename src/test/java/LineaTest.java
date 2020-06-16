import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import Entities.LineaTelefonica;
import Entities.Plan;
import Entities.PlanPrepago;
import Entities.PlanWow;

class LineaTest {
 
	@Test
	void SetsAndGetsTest() {
		Plan plan=new PlanPrepago();
		LineaTelefonica linea1=new LineaTelefonica();
		linea1.setNombreUsuario("Javi");
		linea1.setNumero("7777");
		linea1.setPlan(plan);
		assertEquals("7777", linea1.getNumero());
		assertEquals("Javi", linea1.getNombreUsuario());
		assertEquals(plan, linea1.getPlan());
	}
	@Test
	void ConstructorTest() {
		Plan plan=new PlanWow();
		LineaTelefonica linea1=new LineaTelefonica("7777","Javi",plan);
		assertEquals("7777", linea1.getNumero());
		assertEquals("Javi", linea1.getNombreUsuario());
		assertEquals(plan, linea1.getPlan());
	}

}
