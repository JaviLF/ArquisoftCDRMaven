//<<<<<<< HEAD:src/main/java/ViewModels/SparkUiViewModel.java
package ViewModels;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import Entities.CDR;
import Entities.Linea;
import Entities.Plan;
import Entities.PlanPostpago;
import Entities.PlanPrepago;
import Entities.PlanWow;
import Gateways.PersistenciaCDR;
import Gateways.PersistenciaLinea;
import Presenters.UiPresenter;
import Repositories.PersistenciaCDRSql;
import Repositories.PersistenciaLineaSql;

import TemplateEngine.FreeMarkerEngine;
import spark.ModelAndView;
  
//import spark.template.velocity.VelocityTemplateEngine;

public class SparkUIViewModel implements UiPresenter{

	public void main() {
		PersistenciaCDR cdrs= new PersistenciaCDRSql();
		PersistenciaLinea lineas= new PersistenciaLineaSql();
		
		 get("/", (request, response) -> {
	           Map<String, Object> viewObjects = new HashMap<String, Object>();
	           viewObjects.put("title", "Welcome to Spark Project");
	           return new ModelAndView(viewObjects, "home.ftl");
	        }, new FreeMarkerEngine());
		
		post("/addCDR", (request, response) -> addCDR());
		post("/addLinea", (request, response) -> addLinea());
		post("/SaveLinea",(request, response) ->{
			String telf=request.queryParams("telefono");
			String usuario=request.queryParams("usuario");
			String tipoPlan=request.queryParams("tipo");
			Plan plan=new PlanPrepago();
			if(Integer.parseInt(tipoPlan)==2)
				plan=new PlanPostpago();
			if(Integer.parseInt(tipoPlan)==3)
				plan=new PlanWow();
			Linea linea=new Linea(telf,usuario,plan);
			lineas.guardarLinea(linea);
			return addCDR();
		});
		post("/SaveCDR",(request, response) ->{
			String telf_origen=request.queryParams("telf_origen");
			String telf_destino=request.queryParams("telf_destino");
			String horaLlamada=request.queryParams("horaLlamada");
			String duracionLlamada=request.queryParams("duracionLlamada");
			CDR cdr=new CDR(telf_origen,telf_destino,"01-01-2020",horaLlamada,duracionLlamada);//fechaHardcodeada
			cdr.calcularTarifaParaLinea(lineas.getLinea(telf_origen));
			cdrs.guardarCDR(cdr,1);
			return mostrarTarificado(cdr.getId());
		});
		
		post("/CDRTarificado",(request, response) -> {
			String telf_origen=request.queryParams("telf_origen");
			String telf_destino=request.queryParams("telf_destino");
			String horaLlamada=request.queryParams("horaLlamada");
			String duracionLlamada=request.queryParams("duracionLlamada");
			String plan=request.queryParams("plan");
			System.out.println(plan);
			CDR cdr=new CDR(telf_origen,telf_destino,"01-01-2020",horaLlamada,duracionLlamada);//fechaHardcodeada
			Plan plan1=new PlanPrepago();
			if(plan=="2") {
				plan1=new PlanPostpago();
			}
			if(plan=="3") {
				plan1=new PlanWow();
			}
			Linea linea=new Linea(telf_origen,"Pepe",plan1);
			cdr.calcularTarifaParaLinea(linea);
			PersistenciaCDR persis=new PersistenciaCDRSql();
			persis.guardarCDR(cdr,1);
			return mostrarTarificado(cdr.getId());
		});
	}
	
	
	
	public static String addLinea() {
		return "<html>"
				+ "<body>"
					+ "<form method='post' action='/SaveLinea'>"//
					+ "<label>telefono:</label>"
					+ "<input type='text' name='telefono'>"
					+ "<br/>"
					+ "<label>Usuario:</label>"
					+ "<input type='text' name='usuario'>"
					+ "<br/>"
					+ "<b>Tipo:</b>"
					+ "<select name='tipo'  id='tipo'>"
                    +"<option value='3'>Plan Wow</option>"
                    +"<option value='1'>Plan PrePago</option>"
                    +"<option value='2'>Plan PostPago</option>"
                    +"</select>"
					+ "<br/>"
					+ "<input type='submit' value='Guardar Linea'"
				+ "</body>"
			+ "</html>";
	}
	
	
	private static String mostrarTarificado(int id) {
		PersistenciaCDR persis=new PersistenciaCDRSql();
		CDR cdr=persis.getCDR(id);
		return "<html>"
				+ "<body>"
					+ "<form method='get' action='/'>"
					+ "<label>telf_origen:</label>"
					+ "<label>  "+cdr.getNumeroLlamante()+"</label>"
					+ "<br/>"
					+ "<label>telf_destino:</label>"
					+ "<label> "+cdr.getNumeroLlamado()+"</label>"
					+ "<br/>"
					+ "<label>horaLlamada:</label>"
					+ "<label> "+cdr.getHoraLlamada()+"</label>"
					+ "<br/>"
					+ "<label>duracionLlamada:</label>"
					+ "<label> "+cdr.getDuracionLlamada()+"</label>"
					+ "<br/>"
					+ "<label>tarifaLlamada:</label>"
					+ "<label> "+cdr.getTarifa()+"</label>"
					+ "<br/>"
					+ "<input type='submit' value='Volver Inicio'"
				+ "</body>"
			+ "</html>";
	}
	public static String addCDR() {
		return "<html>"
				+ "<body>"
					+ "<form method='post' action='/SaveCDR'>"
					+ "<label>telf_origen:</label>"
					+ "<input type='text' name='telf_origen'>"
					+ "<br/>"
					+ "<label>telf_destino:</label>"
					+ "<input type='text' name='telf_destino'>"
					+ "<br/>"
					+ "<label>horaLlamada:</label>"
					+ "<input type='number' name='horaLlamada'>"
					+ "<br/>"
					+ "<label>duracionLlamada:</label>"
					+ "<input type='number' step='0.01' name='duracionLlamada'>"
					+ "<br/>"
					+ "<input type='submit' value='Guardar y Tarifar'"
					//+ "</form>"
				+ "</body>"
			+ "</html>";
	}
}
