import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Entities.Tarificacion;
import Gateways.PersistenciaCDR;
import Gateways.PersistenciaLinea;
import Interactors.AgregarCDRDesdeArchivoUseCase;
import Interactors.AgregarLineasDesdeArchivoUseCase;
import Interactors.AgregarTarificacionUseCase;
import Interactors.ObtenerTarificacionesUseCase;
import Interactors.SeleccionarPersistenciaCDRUseCase;
import Repositories.PersistenciaLineaSql;

class UseCasesTest {

	@Test
	void FlujoTarificacionDesdeArchivoTest() {
		AgregarLineasDesdeArchivoUseCase aggL=new AgregarLineasDesdeArchivoUseCase();
		assertEquals(3,aggL.agregarLineasDesdeArchivo("ejemplo_entrada_lineas.txt"));
		
		SeleccionarPersistenciaCDRUseCase selecP = new SeleccionarPersistenciaCDRUseCase();
		PersistenciaCDR persistencia= selecP.seleccionarPersistencia("archivo");
		AgregarTarificacionUseCase agreT = new AgregarTarificacionUseCase();
		Tarificacion tarificacion=new Tarificacion("31-5-2020","archivo");
		agreT.agregarTarificacion(tarificacion);
		ObtenerTarificacionesUseCase obtT=new ObtenerTarificacionesUseCase();
		tarificacion=obtT.obtenerTarificaciones().get(0);
		AgregarCDRDesdeArchivoUseCase aggC=new AgregarCDRDesdeArchivoUseCase();
		assertEquals(6,aggC.agregarCDRDesdeArchivo("ejemplo_entrada_cdrs.txt", persistencia, tarificacion.getId()));
		
		
	}
	/*@Test
	void AgregarCDRDesdeArchivoTest() {
		fail("Not yet implemented");
	}
	@Test
	void AgregarLineaTest() {
		fail("Not yet implemented");
	}
	@Test
	void AgregarLineaDesdeArchivoTest() {
		fail("Not yet implemented");
	}
	@Test
	void AgregarCDRTest() {
		fail("Not yet implemented");
	}
	@Test
	void ObtenerTarificacionesTest() {
		fail("Not yet implemented");
	}
	@Test
	void AgregarCDRTest() {
		fail("Not yet implemented");
	}
	@Test
	void AgregarCDRTest() {
		fail("Not yet implemented");
	}
	@Test
	void AgregarCDRTest() {
		fail("Not yet implemented");
	}*/

}
