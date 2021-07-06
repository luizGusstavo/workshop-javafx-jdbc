package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class MainViewController implements Initializable{

	@FXML
	private MenuItem menuItemSeller;
	
	@FXML
	private MenuItem menuItemDepartment;
	
	@FXML
	private MenuItem menuItemAbout;
	
	@FXML
	public void onMenuItemSellerAction() {
		System.out.println("onMenuItemSellerAction");
	}
	
	@FXML
	public void onMenuItemDepartmentAction() {
		loadView("/gui/DepartmentList.fxml");
	}
	
	@FXML
	public void onMenuItemAboutAction() {
		loadView("/gui/About.fxml");
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
	}

	//Synchronized garante que o codigo n vai ser parado, e sera executado todo de uma vez
	private synchronized void loadView(String absolutName) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absolutName));
			VBox newVBox = loader.load();
			
			Scene mainScene = Main.getMainScene();
			//getRoot => pega o primeiro elemento da minha view	
			//getContent => pega o conteudo do ScrollPane
			//Ta fazendo 2 casting, um pra ScrollPane - pra conseguir pegar o conteudo da view. E um pra VBox - quando consegue pegar o VBox dentro da ScrollPane
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
			
			/*Com o codigo a seguir, nós conseguimos manter o menu da tela principal e abrir uma outra tela na tela principal
			 * com o menu armazenado, e os filhos da nova tela que foi aberta*/
			
			Node mainMenu = mainVBox.getChildren().get(0);//armazena o menu da tela principal
			mainVBox.getChildren().clear(); //limpa todo o conteudo do vbox
			mainVBox.getChildren().add(mainMenu); //adiciona o menu que tinha na tela principal
			mainVBox.getChildren().addAll(newVBox.getChildren());//adiciona os filhos que tem no objeto passado como argumento
		}
		catch(IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
		
	}
	
}
