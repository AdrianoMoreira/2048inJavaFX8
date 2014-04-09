package br.eti.adrianomoreira.fx2048;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class View extends AnchorPane{
    
    private static final int BORDER = 15;
    private static final int DESKTOP = 500;
    private static final int BORDER_RADIUS = 5;
    
    String colorFontLigth = "#ffffff";
    String colorFontDark = "#776265";
    public static final String colorBackgound = "#faf8ef";
    String colorDestopBackgound = "#bbada0";
    String colorTileBackground = "#ccc0b3";
    String colorTileBackground2 = "#eee4da";
    String colorTileBackground4 = "#ede0c8";
    String colorTileBackground8 = "#f2b179";
    String colorTileBackground16 = "#f59563";
    String colorTileBackground32 = "#f67c5f";
    String colorTileBackground64 = "#f65e3b";
    String colorTileBackground128 = "#edcf72";
    String colorTileBackground256 = "#edcc61";
    String colorTileBackground512 = "red";
    String colorTileBackground1024 = "red";
    String colorTileBackground2048 = "red";

    private Node[][] tiles;
    
    public View() {
        
        tiles = new Node[4][4];
        
        setMinSize(DESKTOP, DESKTOP);
        setPrefSize(DESKTOP, DESKTOP);
        setMaxSize(DESKTOP, DESKTOP);
        
        setStyle("-fx-background-color: " + colorDestopBackgound + ";" +
                 "-fx-background-radius: 10;"    
                );
        
        //backgound
        for (int y = 0 ; y < 4; y++) {
            for (int x = 0 ; x < 4; x++) {
                final Node tile = getTileBackground();
                getChildren().add(tile);

                tile.setLayoutX( getBooderSize() + 
                        ((getBooderSize() + getTileSize()) * x) );

                tile.setLayoutY(getBooderSize() +
                        ((getBooderSize() + getTileSize()) * y) );
                
            }
            
        }
        
    }
    
    private Node getTileBackground() {
        StackPane stackPane = new StackPane();
        double d = getTileSize();
        stackPane.setPrefSize(d, d);
                
        String style = "-fx-background-color: " + colorTileBackground + ";";
        style += "-fx-background-radius: " + BORDER_RADIUS;
        stackPane.setStyle(style);
        
        return stackPane;
    }
    
    private Node getTile(Integer value){
        StackPane stackPane = new StackPane();
        
        Label label = new Label(value.toString());
        
        String lbStyle = "";
        
        if(value == 2 || value == 4)
            lbStyle += "-fx-text-fill: "+ colorFontDark + ";";
        else
            lbStyle += "-fx-text-fill: "+ colorFontLigth + ";";
        
        if(value < 1000)
            label.setFont(new Font(getTileSize()/2));
        else
            label.setFont(new Font(getTileSize()/3));
        
        
        lbStyle += "-fx-font: console bold 13;";
        label.setStyle(lbStyle);
        
        stackPane.getChildren().add(label);
        
        double d = getTileSize();
        stackPane.setPrefSize(d, d);
        
        String style = "";
        
        switch(value){
            case 2: 
                style += "-fx-background-color: " + colorTileBackground2 + ";";
                break;
            case 4:
                style += "-fx-background-color: " + colorTileBackground4 + ";";
                break;
            case 8:
                style += "-fx-background-color: " + colorTileBackground8 + ";";
                break;
            case 16:
                style += "-fx-background-color: " + colorTileBackground16 + ";";
                break;
            case 32: 
                style += "-fx-background-color: " + colorTileBackground32 + ";";
                break;
            case 64:
                style += "-fx-background-color: " + colorTileBackground64 + ";";
                break;
            case 128:
                style += "-fx-background-color: " + colorTileBackground128 + ";";
                break;
            case 256:
                style += "-fx-background-color: " + colorTileBackground256 + ";";
                break;
            case 512: 
                style += "-fx-background-color: " + colorTileBackground512 + ";";
                break;
            case 1024:
                style += "-fx-background-color: " + colorTileBackground1024 + ";";
                break;
            case 2048:
                style += "-fx-background-color: " + colorTileBackground2048 + ";";
                break;
                
        }
        style += "-fx-background-radius: " + BORDER_RADIUS;
        stackPane.setStyle(style);
        
        return stackPane;    
    }
    
    public static double getTileSize() {
        return (DESKTOP - (BORDER * 5)) / 4;
    }
    
    public static double getDeskTopSize() {
        return DESKTOP;
    }
    
    public static double getBooderSize(){
        return BORDER;
    }

    public void addTile(int x,int y, int value){
        Node tile = getTile(value);
        tiles[x][y] = tile;
        
        tile.setLayoutX(getBooderSize()
                + ((getBooderSize() + getTileSize()) * x));

        tile.setLayoutY(getBooderSize()
                + ((getBooderSize() + getTileSize()) * y));
        
        tile.setScaleX(0.0);
        tile.setScaleY(0.0);
        
        getChildren().add(tile);
        
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), tile);
        scaleTransition.setToX(1);
        scaleTransition.setToY(1);
        scaleTransition.setDelay(Duration.millis(100));
        scaleTransition.play();
    }
    
    public void moveTile(int xStart,int yStart, int xEnd, int yEnd){
        
        Node tile = tiles[xStart][yStart];
        if(tile == null) return;
        
        tiles[xStart][yStart] = null;
        tiles[xEnd][yEnd] = tile;
        
        double x = getBooderSize()
                + ((getBooderSize() + getTileSize()) * xEnd);

        double y = getBooderSize()
                + ((getBooderSize() + getTileSize()) * yEnd);
        
        
        Timeline timeline = new Timeline();
        
        KeyValue kvX = new KeyValue(tile.layoutXProperty(), x);
        KeyValue kvY = new KeyValue(tile.layoutYProperty(), y);
        
        KeyFrame kf = new KeyFrame(Duration.millis(200), kvX, kvY);
        timeline.getKeyFrames().add(kf);
        timeline.play();
        
    }

    void remove(int x, int y) {
        Node tile = tiles[x][y];
        if(tile != null){
            getChildren().remove(tile);
            tiles[x][y] = null;
        }
        
    }

    void merge(int x1, int y1, int x2, int y2, int value) {
        
        Node tileClone1 = getTile(value/2);
        tileClone1.setLayoutX(tiles[x1][y1].getLayoutX());
        tileClone1.setLayoutY(tiles[x1][y1].getLayoutY());
        
        Node tileClone2 = getTile(value/2);
        tileClone2.setLayoutX(tiles[x2][y2].getLayoutX());
        tileClone2.setLayoutY(tiles[x2][y2].getLayoutY());
        
        getChildren().add(tileClone1);
        getChildren().add(tileClone2);
        Timeline timeline = new Timeline();
        
        double x = getBooderSize()
                + ((getBooderSize() + getTileSize()) * x2);
        
        double y = getBooderSize()
                + ((getBooderSize() + getTileSize()) * y2);
        
        KeyValue kvX = new KeyValue(tileClone1.layoutXProperty(), x);
        KeyValue kvY = new KeyValue(tileClone1.layoutYProperty(), y);
        
        KeyFrame kf = new KeyFrame(Duration.millis(200), kvX, kvY);
        timeline.getKeyFrames().add(kf);

        Node tileNovo = getTile(value);
        tileNovo.setLayoutX(x);
        tileNovo.setLayoutY(y);
        
        Timeline timeline2 = new Timeline();
        timeline2.getKeyFrames().add(new KeyFrame(Duration.ONE, (ActionEvent event) -> {
            getChildren().remove(tileClone1);
            getChildren().remove(tileClone2);
            getChildren().add(tileNovo);
        }));
  
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), tileNovo);
        scaleTransition.setFromX(1.2);
        scaleTransition.setFromY(1.2);
        scaleTransition.setToX(1);
        scaleTransition.setToY(1);
        
        SequentialTransition sequentialTransition = new SequentialTransition();
        sequentialTransition.getChildren().addAll(timeline,timeline2,scaleTransition);
            
        sequentialTransition.play();
        
        remove(x1, y1);
        remove(x2, y2);
        tiles[x2][y2] = tileNovo;
        
    }
    
}
