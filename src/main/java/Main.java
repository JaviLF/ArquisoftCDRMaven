import Presenters.UiPresenter;
import ViewModels.SparkUiViewModel;

public class Main {

	public static void main(String[] args) {
		UiPresenter ui = new SparkUiViewModel();
		ui.main();
	}

}
