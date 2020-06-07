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
		assertEquals(4,GLUC.guardarLineasDesdeArchivo(lineasAIngresar, "archivo").size());
		
		SeleccionarPersistenciaCDRUseCase selecP = new SeleccionarPersistenciaCDRUseCase();
		PersistenciaCDR persistencia= selecP.seleccionarPersistencia("archivo");
		AgregarTarificacionUseCase agreT = new AgregarTarificacionUseCase();
		Tarificacion tarificacion=new Tarificacion("archivo");
		
		agreT.agregarTarificacion(tarificacion);
		//ObtenerTarificacionesUseCase obtT=new ObtenerTarificacionesUseCase();
		//tarificacion=obtT.obtenerTarificaciones().get(0);
		ObtenerCDRsDesdeArchivoUseCase OCDAUC= new ObtenerCDRsDesdeArchivoUseCase();
		List<String> cdrsAIngresar=OCDAUC.ObtenerCDRsDeArchivo(Paths.get("C:/Users/PC/Desktop/ejemplo_entrada_cdrs.txt"));
		TarificarYGuardarCDRsUseCase TYGCUC=new TarificarYGuardarCDRsUseCase();
		
		//Path path = Paths.get("C:/Users/PC/Desktop/ejemplo_entrada_cdrs.txt");
		assertEquals(6,TYGCUC.agregarCDRDesdeArchivo(cdrsAIngresar, "archivo", tarificacion.getId()).size());
		
		/*persistencia= selecP.seleccionarPersistencia("sql");
		tarificacion=new Tarificacion("sql");
		agreT.agregarTarificacion(tarificacion);
		assertEquals(6,aggC.agregarCDRDesdeArchivo(path, persistencia, tarificacion.getId()));*/
	}
	

}
