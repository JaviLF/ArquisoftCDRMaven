import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Test;

import DTOs.LineaDTO;
import Entities.CDR;
import Entities.Tarificacion;
import Gateways.PersistenciaCDR;
import Gateways.PersistenciaLineaTelefonica;
import Interactors.GuardarLineas;
import Interactors.ObtenerYValidarCDRsDeArchivo;
import Interactors.ObtenerCDRsSegunTarificacion;
import Interactors.ObtenerYValidarLineasTelefonicasDeArchivo;
import Interactors.AgregarTarificacion;
import Interactors.ObtenerTarificaciones;
import Interactors.GestionarConfiguracionPersistencia;
import Interactors.TarificarYGuardarCDRs;
import Repositories.LineaSqlRepository;

class UseCasesTest { 

	@Test
	void FlujoTarificacionDesdeArchivoTest() {  
		ObtenerYValidarLineasTelefonicasDeArchivo OLTDAUC= new ObtenerYValidarLineasTelefonicasDeArchivo();
		List<LineaDTO> lineasAIngresar=OLTDAUC.ObtenerLineasDeArchivo(Paths.get("ejemplo_entrada_lineas.txt"));
		GuardarLineas GLUC=new GuardarLineas();
		GestionarConfiguracionPersistencia selecP = new GestionarConfiguracionPersistencia();
		selecP.seleccionarPersistencia("sql");
		
		assertEquals(3,GLUC.guardarLineasDesdeArchivo(lineasAIngresar, selecP.getPersistenciaLinea()).size());
		
		
		AgregarTarificacion agreT = new AgregarTarificacion();
		Tarificacion tarificacion=agreT.agregarTarificacion("sql");
		
		
		
		ObtenerYValidarCDRsDeArchivo OCDAUC= new ObtenerYValidarCDRsDeArchivo();
		List<CDR> cdrsAIngresar=OCDAUC.ObtenerCDRsDeArchivo(Paths.get("ejemplo_entrada_cdrs.txt"));
		TarificarYGuardarCDRs TYGCUC=new TarificarYGuardarCDRs();

		assertEquals(6,TYGCUC.agregarCDR(cdrsAIngresar, selecP.getPersistenciaCDR(), selecP.getPersistenciaLinea(), tarificacion.getId()).size());
		
		
		selecP.seleccionarPersistencia("archivo");
		assertEquals(3,GLUC.guardarLineasDesdeArchivo(lineasAIngresar, selecP.getPersistenciaLinea()).size());
		
		ObtenerTarificaciones OTUC=new ObtenerTarificaciones();
		assertFalse(OTUC.obtenerTarificaciones("sql").isEmpty());
		
		ObtenerCDRsSegunTarificacion OCSTUC=new ObtenerCDRsSegunTarificacion();
		assertFalse(OCSTUC.obtenerCDRS(tarificacion).isEmpty());
	}
	

}
