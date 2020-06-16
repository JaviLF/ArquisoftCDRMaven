import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Test;

import DTOs.InputLineaTelefonicaDTO;
import Entities.CDR;
import Entities.Tarificacion;
import Interactors.GuardarLineasTelefonicas;
import Interactors.ObtenerYValidarCDRsDeArchivo;
import Interactors.ObtenerCDRsSegunTarificacion;
import Interactors.ObtenerLineaTelefonica;
import Interactors.ObtenerTarificacion;
import Interactors.ObtenerYValidarLineasTelefonicasDeArchivo;
import Interactors.TarificarCDRs;
import Interactors.AgregarTarificacion;
import Interactors.GenerarDatosFacturaJSON;
import Interactors.ObtenerTarificaciones;
import Interactors.GestionarConfiguracionPersistencia;
import Interactors.GuardarCDRs;

class UseCasesTest { 

	@Test
	void FlujoTarificacionDesdeArchivoTest() {  
		GestionarConfiguracionPersistencia configPersistencias = new GestionarConfiguracionPersistencia();
		ObtenerYValidarLineasTelefonicasDeArchivo obtenerLineas= new ObtenerYValidarLineasTelefonicasDeArchivo();
		GuardarLineasTelefonicas guardarLineas=new GuardarLineasTelefonicas();
		AgregarTarificacion agreT = new AgregarTarificacion();
		ObtenerYValidarCDRsDeArchivo obtenerCDRsArchivo= new ObtenerYValidarCDRsDeArchivo();
		ObtenerTarificaciones obtenerTarificaciones=new ObtenerTarificaciones();
		ObtenerTarificacion obtenerTarificacion=new ObtenerTarificacion();
		ObtenerCDRsSegunTarificacion obtenerCDRsTarificacion=new ObtenerCDRsSegunTarificacion();
		TarificarCDRs tarificarCDRs=new TarificarCDRs();
		GuardarCDRs guardarCDRs=new GuardarCDRs();
		
		configPersistencias.seleccionarPersistencia("sql");
		List<InputLineaTelefonicaDTO> lineasAIngresar=obtenerLineas.ObtenerLineasDeArchivo(Paths.get("entrada_lineas_test.txt"));
		assertEquals(3,guardarLineas.guardarLineasDesdeArchivo(lineasAIngresar, configPersistencias.getPersistenciaLinea()).size());
		
		Tarificacion tarificacion=agreT.agregarTarificacion("sql");
		List<CDR> cdrsAIngresar=obtenerCDRsArchivo.ObtenerCDRsDeArchivo(Paths.get("entrada_cdrs_test.txt"));
		List<CDR> cdrsTarificados=tarificarCDRs.tarificarCDRs(cdrsAIngresar, configPersistencias.getPersistenciaLinea());
		assertEquals(6,cdrsTarificados.size());
		guardarCDRs.guardarCDRs(cdrsTarificados, configPersistencias.getPersistenciaCDR(), tarificacion.getId());
		assertFalse(obtenerTarificaciones.obtenerTarificaciones( configPersistencias.getPersistenciaTarificacion()).isEmpty());
		assertFalse(obtenerCDRsTarificacion.obtenerCDRS(obtenerTarificacion.getTarificacion(tarificacion.getId(), configPersistencias.getPersistenciaTarificacion())).isEmpty());
		
		
		configPersistencias.seleccionarPersistencia("archivo");
		assertEquals(3,guardarLineas.guardarLineasDesdeArchivo(lineasAIngresar, configPersistencias.getPersistenciaLinea()).size());
		tarificacion=agreT.agregarTarificacion("archivo");
		cdrsTarificados=tarificarCDRs.tarificarCDRs(cdrsAIngresar, configPersistencias.getPersistenciaLinea());
		assertEquals(6,cdrsTarificados.size());
		guardarCDRs.guardarCDRs(cdrsTarificados, configPersistencias.getPersistenciaCDR(), tarificacion.getId());
		assertFalse(obtenerTarificaciones.obtenerTarificaciones(configPersistencias.getPersistenciaTarificacion()).isEmpty());
		assertFalse(obtenerCDRsTarificacion.obtenerCDRS(obtenerTarificacion.getTarificacion(tarificacion.getId(), configPersistencias.getPersistenciaTarificacion())).isEmpty());
		
		ObtenerLineaTelefonica obtenerLineaTelefonica=new ObtenerLineaTelefonica();
		GenerarDatosFacturaJSON generarDatosFactura=new GenerarDatosFacturaJSON();
		assertFalse(null==generarDatosFactura.getJSON(obtenerLineaTelefonica.getLinea("77422828", configPersistencias.getPersistenciaLinea()), configPersistencias.getPersistenciaCDR(), 5));
		assertFalse(null==generarDatosFactura.getJSON(obtenerLineaTelefonica.getLinea("77422828", configPersistencias.getPersistenciaLinea()), configPersistencias.getPersistenciaCDR(), 1));
	}
	

}
