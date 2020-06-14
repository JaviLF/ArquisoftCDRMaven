import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import Entities.LineaTelefonica;
import Entities.Plan;
import Entities.PlanPrepago;
import Entities.PlanWow;
import Entities.TarifaFija;

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
	/*@Test
	void FriendNumbersTest() {
		List<String>numAmigos=new ArrayList<String>();
		
		Plan plan=new PlanWow(new TarifaFija(),);
		Linea linea1=new Linea("7777","Javi",plan);
		linea1.addNumeroAmigo("2222");
		assertTrue(linea1.esNumeroAmigo("2222"));
		Plan plan2=new PlanPrepago();
		Linea linea2=new Linea("5555","Henry",plan2);
		linea2.addNumeroAmigo("2222");
		assertFalse(linea2.esNumeroAmigo("2222"));
	}*/

}
