import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Test;

import DTOs.LineaDTO;
import Entities.CDR;
import Entities.Tarificacion;
import Gateways.PersistenciaCDR;
import Gateways.PersistenciaLinea;
import Interactors.GuardarLineasUseCase;
import Interactors.ObtenerYValidarCDRsDeArchivoUseCase;
import Interactors.ObtenerCDRsSegunTarificacionUseCase;
import Interactors.ObtenerYValidarLineasTelefonicasDeArchivoUseCase;
import Interactors.AgregarTarificacionUseCase;
import Interactors.ObtenerTarificacionesUseCase;
import Interactors.GestionarConfiguracionPersistenciaUseCase;
import Interactors.TarificarYGuardarCDRsUseCase;
import Repositories.LineaSqlRepository;

class UseCasesTest { 

	@Test
	void FlujoTarificacionDesdeArchivoTest() {  
		ObtenerYValidarLineasTelefonicasDeArchivoUseCase OLTDAUC= new ObtenerYValidarLineasTelefonicasDeArchivoUseCase();
		List<LineaDTO> lineasAIngresar=OLTDAUC.ObtenerLineasDeArchivo(Paths.get("C:/Users/PC/Desktop/ejemplo_entrada_lineas.txt"));
		GuardarLineasUseCase GLUC=new GuardarLineasUseCase();
		GestionarConfiguracionPersistenciaUseCase selecP = new GestionarConfiguracionPersistenciaUseCase();
		selecP.seleccionarPersistencia("sql");
		
		assertEquals(3,GLUC.guardarLineasDesdeArchivo(lineasAIngresar, selecP.getPersistenciaLinea()).size());
		
		
		AgregarTarificacionUseCase agreT = new AgregarTarificacionUseCase();
		Tarificacion tarificacion=agreT.agregarTarificacion("sql");
		
		
		
		ObtenerYValidarCDRsDeArchivoUseCase OCDAUC= new ObtenerYValidarCDRsDeArchivoUseCase();
		List<CDR> cdrsAIngresar=OCDAUC.ObtenerCDRsDeArchivo(Paths.get("C:/Users/PC/Desktop/ejemplo_entrada_cdrs.txt"));
		TarificarYGuardarCDRsUseCase TYGCUC=new TarificarYGuardarCDRsUseCase();

		assertEquals(6,TYGCUC.agregarCDR(cdrsAIngresar, selecP.getPersistenciaCDR(), selecP.getPersistenciaLinea(), tarificacion.getId()).size());
		
		
		selecP.seleccionarPersistencia("archivo");
		assertEquals(3,GLUC.guardarLineasDesdeArchivo(lineasAIngresar, selecP.getPersistenciaLinea()).size());
		
		ObtenerTarificacionesUseCase OTUC=new ObtenerTarificacionesUseCase();
		assertFalse(OTUC.obtenerTarificaciones("sql").isEmpty());
		
		ObtenerCDRsSegunTarificacionUseCase OCSTUC=new ObtenerCDRsSegunTarificacionUseCase();
		assertFalse(OCSTUC.obtenerCDRS(tarificacion).isEmpty());
	}
	

}
