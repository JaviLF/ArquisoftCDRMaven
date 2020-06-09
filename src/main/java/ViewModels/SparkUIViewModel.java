
package ViewModels;


import Controllers.APIController;
import Controllers.CDRController;
import Controllers.HomeController;
import Controllers.LineaController;

import Presenters.UiPresenter; 

  

public class SparkUIViewModel implements UiPresenter{

	public void main() {
		LineaController lineaController=new LineaController();
		CDRController cdrController=new CDRController();
		HomeController homeController=new HomeController();
		APIController apiController=new APIController();
		homeController.main();
		lineaController.main();
		cdrController.main();
		apiController.main();
	}

}
