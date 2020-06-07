package Controllers;

import static spark.Spark.get;

import java.util.HashMap;
import java.util.Map;

import Presenters.UiPresenter;
import TemplateEngine.FreeMarkerEngine;
import spark.ModelAndView;

public class HomeController implements UiPresenter{

	@Override
	public void main() {
		get("/", (request, response) -> {
	           Map<String, Object> viewObjects = new HashMap<String, Object>();
	           viewObjects.put("title", "Welcome to Spark Project");
	           return new ModelAndView(viewObjects, "home.ftl");
	        }, new FreeMarkerEngine());
		
	}
 
}
