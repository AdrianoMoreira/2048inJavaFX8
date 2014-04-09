package br.eti.adrianomoreira.fx2048;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application{

	private static final String TITLE  = "jfx 2048";
	private static final double WIDTH  = 550;
	private static final double HEIGHT = 700;


	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();

		AnchorPane panelTop = new AnchorPane();
		panelTop.setPrefHeight(100);

		Label label = new Label("2048");
		panelTop.getChildren().add(label);

		StackPane panelCenter = new StackPane();

		View view = new View();
		Model  model = new Model(view);

		panelCenter.getChildren().add(view);


		AnchorPane panelBotton = new AnchorPane();
		panelBotton.setPrefHeight(100);

		root.setTop(panelTop);
		root.setCenter(panelCenter);
		root.setBottom(panelBotton);

		Scene scene = new Scene(root);

        scene.setOnKeyPressed((KeyEvent e) -> {

            if(e.getCode().equals(KeyCode.UP)
                    || e.getCode().equals(KeyCode.DOWN)
                    || e.getCode().equals(KeyCode.LEFT)
                    || e.getCode().equals(KeyCode.RIGHT)){
                model.move(e.getCode());
            }

            System.out.println(model.toString());

        });

//		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setScene(scene);
		primaryStage.setTitle(TITLE);
		primaryStage.setWidth(WIDTH);
		primaryStage.setHeight(HEIGHT);
//		primaryStage.setResizable(false);

		primaryStage.show();
	}



	public static void main(String... args) {
		launch(args);
	}
}
