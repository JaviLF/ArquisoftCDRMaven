import Presenters.UiPresenter;
import ViewModels.SparkUIViewModel;
import static spark.Spark.*;

import java.util.ArrayList;
import java.util.List;


public class Main {

	public static void main(String[] args) {
		UiPresenter ui = new SparkUIViewModel();
		ui.main(); 
	}

}
