package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class Main extends Application {
	
	private static Scene mainScene;//declara em uma variavel fora do start, para que seja possivel usar em outra classe
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
			ScrollPane scrollPane = loader.load();
			
			scrollPane.setFitToHeight(true);//para a scrollpane cobrir a tela toda
			scrollPane.setFitToWidth(true);//para a scrollpane cobrir a tela toda
			
			mainScene = new Scene(scrollPane);//instancia uma cena principal, passando o objeto principal
			primaryStage.setScene(mainScene);//seta o palco com a cena principal
			primaryStage.setTitle("Sample JavaFX application");
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Scene getMainScene() {
		return mainScene;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
