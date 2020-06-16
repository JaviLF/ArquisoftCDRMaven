
package ViewModels;


import Controllers.APIController;
import Controllers.CDRController;
import Controllers.HomeController;
import Controllers.LineaTelefonicaController;
import Controllers.TarificacionController;
import Presenters.UiPresenter; 

  

public class SparkUIViewModel implements UiPresenter{

	public void main() {
		LineaTelefonicaController lineaController=new LineaTelefonicaController();
		CDRController cdrController=new CDRController();
		HomeController homeController=new HomeController();
		APIController apiController=new APIController();
		TarificacionController tarificacionController=new TarificacionController();
		homeController.main();
		lineaController.main();
		cdrController.main();
		tarificacionController.main();
		apiController.main();
	}

}
