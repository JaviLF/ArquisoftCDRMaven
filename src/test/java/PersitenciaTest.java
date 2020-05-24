import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PersitenciaTest {

	@Test
	void SaveCDRTest() {
		CDR cdr=new CDR("5555","2222",22,2.5);
		PersistenciaCDR persistencia1= new PersistenciaCDRSql();
		persistencia1.guardarCDR(cdr);
		CDR cdr2=persistencia1.getCDR(cdr.getId());
		assertEquals(cdr.getId(),cdr2.getId());
	}

}
