import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Test;

import Entities.Tarificacion;
import Gateways.PersistenciaCDR;
import Gateways.PersistenciaLinea;
import Interactors.GuardarLineasUseCase;
import Interactors.ObtenerCDRsDesdeArchivoUseCase;
import Interactors.ObtenerCDRsSegunTarificacionUseCase;
import Interactors.ObtenerLineasTelefonicasDeArchivoUseCase;
import Interactors.AgregarTarificacionUseCase;
import Interactors.ObtenerTarificacionesUseCase;
import Interactors.SeleccionarPersistenciaCDRUseCase;
import Interactors.TarificarYGuardarCDRsUseCase;
import Repositories.LineaSqlRepository;

class UseCasesTest { 

	@Test
	void FlujoTarificacionDesdeArchivoTest() {  
		ObtenerLineasTelefonicasDeArchivoUseCase OLTDAUC= new ObtenerLineasTelefonicasDeArchivoUseCase();
		List<String> lineasAIngresar=OLTDAUC.ObtenerLineasDeArchivo(Paths.get("C:/Users/PC/Desktop/ejemplo_entrada_lineas.txt"));
		GuardarLineasUseCase GLUC=new GuardarLineasUseCase();
		assertEquals(3,GLUC.guardarLineasDesdeArchivo(lineasAIngresar, "sql").size());
		
		SeleccionarPersistenciaCDRUseCase selecP = new SeleccionarPersistenciaCDRUseCase();
		PersistenciaCDR persistencia= selecP.seleccionarPersistencia("sql");
		AgregarTarificacionUseCase agreT = new AgregarTarificacionUseCase();
		Tarificacion tarificacion=agreT.agregarTarificacion("sql");
		
		
		
		ObtenerCDRsDesdeArchivoUseCase OCDAUC= new ObtenerCDRsDesdeArchivoUseCase();
		List<String> cdrsAIngresar=OCDAUC.ObtenerCDRsDeArchivo(Paths.get("C:/Users/PC/Desktop/ejemplo_entrada_cdrs.txt"));
		TarificarYGuardarCDRsUseCase TYGCUC=new TarificarYGuardarCDRsUseCase();

		assertEquals(6,TYGCUC.agregarCDRDesdeArchivo(cdrsAIngresar, "sql", tarificacion.getId()).size());
		
		assertEquals(3,GLUC.guardarLineasDesdeArchivo(lineasAIngresar, "archivo").size());
		
		ObtenerTarificacionesUseCase OTUC=new ObtenerTarificacionesUseCase();
		assertFalse(OTUC.obtenerTarificaciones("sql").isEmpty());
		
		ObtenerCDRsSegunTarificacionUseCase OCSTUC=new ObtenerCDRsSegunTarificacionUseCase();
		assertFalse(OCSTUC.obtenerCDRS(tarificacion).isEmpty());
	}
	

}
