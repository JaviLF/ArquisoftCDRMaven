package Controllers;
 
import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class HomeController {
 
	private static ThymeleafTemplateEngine engine = new ThymeleafTemplateEngine();
	 
	public void main() {
		redirect.get("/","/archivo");
		
	    get("/:tipo", (request, response) -> {
	    	
		    Map<String, Object> viewObjects = new HashMap<String, Object>();
		    viewObjects.put("tipo", request.params(":tipo"));
		    return engine.render(new ModelAndView(viewObjects, "UploadLineas"));
		           
		});
		
		get("/:tipo/Configuration",(request,response)->{
			Map<String, Object> viewObjects = new HashMap<String, Object>();
			viewObjects.put("tipo", request.params(":tipo"));
			return engine.render(new ModelAndView(viewObjects, "Configuration"));
		});
	}
 
}
