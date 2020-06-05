//<<<<<<< HEAD:src/main/java/ViewModels/SparkUiViewModel.java
package ViewModels;

import static spark.Spark.*;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.Part;

import Controllers.CDRController;
import Controllers.HomeController;
import Controllers.LineaController;
import Entities.CDR;
import Entities.Linea;
import Entities.Plan;
import Entities.PlanPostpago;
import Entities.PlanPrepago;
import Entities.PlanWow;
import Gateways.PersistenciaCDR;
import Gateways.PersistenciaLinea;
import Interactors.AgregarLineasDesdeArchivoUseCase;
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
		LineaController lineaController=new LineaController();
		CDRController cdrController=new CDRController();
		HomeController homeController=new HomeController();
		homeController.main();
		lineaController.main();
		cdrController.main();

	}
	
	
	

	
	
}
