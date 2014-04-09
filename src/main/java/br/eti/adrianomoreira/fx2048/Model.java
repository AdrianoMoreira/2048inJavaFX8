
package br.eti.adrianomoreira.fx2048;

import java.util.Random;
import javafx.scene.input.KeyCode;

public class Model {

    private int[][] desktop;
    private final View view;

    public Model(View view) {
        this.view = view;
        init();
    }
    
    public void newGame(){
        init();
    }
    
    private void init(){
        desktop = new int[4][4];
        
        addTile();
        addTile();
    }

    private void addTile() {
        while(true){
            int x = new Random().nextInt(4);
            int y = new Random().nextInt(4);
            
            if(desktop[x][y] == 0 ){
                desktop[x][y] = 2;
                view.addTile(x, y, 2);
                break;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if(desktop[x][y] != 0)
                    sb.append(String.format("[%4s] ", String.valueOf(desktop[x][y])));
                else
                    sb.append("[    ] ");
            }
            sb.append("\n");
        }

        
        return sb.toString();
    }

    void move(KeyCode code) {
        
        //primeiro move tudo
        //depois tenta fundir
        //depois move novamente
                
                
                
        
        if(code == KeyCode.UP){
            
            for (int y = 1; y < 4; y++) {
                for (int x = 0; x < 4; x++) {
                    if(desktop[x][y] == 0) continue;
                    
                    while(desktop[x][y] != 0 && desktop[x][y -1] == 0){
                        desktop[x][y -1] = desktop[x][y];
                        desktop[x][y] = 0;
                        view.moveTile(x, y , x, y-1);
                        
                        if(y > 1) y--;
                    }

                }
            }
            for (int y = 1; y < 4; y++) {
                for (int x = 0; x < 4; x++) {
                    if(desktop[x][y] == 0) continue;
                    
                    if(desktop[x][y] == desktop[x][y -1]){
                        desktop[x][y -1] = desktop[x][y -1] * 2;
                        desktop[x][y] = 0;
                        
                        view.merge(x, y , x, y-1, desktop[x][y -1]);
                    }
                    
                }
            }
            for (int y = 1; y < 4; y++) {
                for (int x = 0; x < 4; x++) {
                    if(desktop[x][y] == 0) continue;
                    
                    while(desktop[x][y] != 0 && desktop[x][y -1] == 0){
                        desktop[x][y -1] = desktop[x][y];
                        desktop[x][y] = 0;
                        view.moveTile(x, y , x, y-1);
                        
                        if(y > 1) y--;
                    }
                    
                }
            } 
        }
        
        if(code == KeyCode.DOWN){
            
            for (int y = 2; y > -1; y--) {
                for (int x = 3; x > -1; x--) {
                    
                    if(desktop[x][y] == 0) continue;
                    
                    while(desktop[x][y] != 0 && desktop[x][y + 1] == 0){
                        desktop[x][y +1] = desktop[x][y];
                        desktop[x][y] = 0;
                        
                        view.moveTile(x, y , x, y +1);
                        
                        if(y < 2) y++;
                    }

                }
            }
            for (int y = 2; y > -1; y--) {
                for (int x = 3; x > -1; x--) {
                    
                    if(desktop[x][y] == 0) continue;

                    if(desktop[x][y] == desktop[x][y +1]){
                        desktop[x][y +1] = desktop[x][y +1] * 2;
                        desktop[x][y] = 0;
                        
                        view.merge(x, y , x, y+1,desktop[x][y +1]);
                    }
                }
            }
            for (int y = 2; y > -1; y--) {
                for (int x = 3; x > -1; x--) {
                    
                    if(desktop[x][y] == 0) continue;
                    
                    while(desktop[x][y] != 0 && desktop[x][y + 1] == 0){
                        desktop[x][y +1] = desktop[x][y];
                        desktop[x][y] = 0;
                        
                        view.moveTile(x, y , x, y +1);
                        
                        if(y < 2) y++;
                    }
                    
                }
            }
            
            
        }
        
        if(code == KeyCode.LEFT){
            
            for (int x = 1; x < 4; x++) {
                for (int y = 0; y < 4; y++) {
                    if(desktop[x][y] == 0) continue;
                    
                    while(desktop[x][y] != 0 && desktop[x-1][y] == 0){
                        desktop[x-1][y] = desktop[x][y];
                        desktop[x][y] = 0;
                        view.moveTile(x, y , x-1, y);
                        
                        if(x > 1) x--;
                    }

                }
            }
            for (int x = 1; x < 4; x++) {
                for (int y = 0; y < 4; y++) {
                    if(desktop[x][y] == 0) continue;
                    
                    if(desktop[x][y] == desktop[x -1][y]){
                        desktop[x -1 ][y] = desktop[x - 1][y] * 2;
                        desktop[x][y] = 0;
                        
                        view.merge(x, y , x -1, y, desktop[x -1][y]);
                    }
                    
                }
            }
            for (int x = 1; x < 4; x++) {
                for (int y = 0; y < 4; y++) {
                    if(desktop[x][y] == 0) continue;
                    
                     while(desktop[x][y] != 0 && desktop[x-1][y] == 0){
                        desktop[x-1][y] = desktop[x][y];
                        desktop[x][y] = 0;
                        view.moveTile(x, y , x-1, y);
                        
                        if(x > 1) x--;
                    }
                    
                }
            } 
        }

        if(code == KeyCode.RIGHT){
            
            for (int x = 2; x > -1; x--) {
                for (int y = 3; y > -1; y--) {
                    
                    if(desktop[x][y] == 0) continue;
                    
                    while(desktop[x][y] != 0 && desktop[x+1][y] == 0){
                        desktop[x+1][y] = desktop[x][y];
                        desktop[x][y] = 0;
                        
                        view.moveTile(x, y , x + 1, y);
                        
                        if(x < 2) x++;
                    }

                }
            }
            for (int x = 2; x > -1; x--) {
                for (int y = 3; y > -1; y--) {
                    
                    if(desktop[x][y] == 0) continue;

                    if(desktop[x][y] == desktop[x+1][y]){
                        desktop[x+1][y] = desktop[x+1][y] * 2;
                        desktop[x][y] = 0;
                        
                        view.merge(x, y , x+1, y,desktop[x+1][y]);
                    }
                }
            }
            for (int x = 2; x > -1; x--) {
                for (int y = 3; y > -1; y--) {
                    
                    if(desktop[x][y] == 0) continue;
                    
                     while(desktop[x][y] != 0 && desktop[x+1][y] == 0){
                        desktop[x+1][y] = desktop[x][y];
                        desktop[x][y] = 0;
                        
                        view.moveTile(x, y , x + 1, y);
                        
                        if(x < 2) x++;
                    }
                    
                }
            }
        }
        
        boolean temLugar = false;
        
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if(desktop[x][y] == 0){
                    temLugar = true;
                    break;
                }
            }
            if(temLugar) break;
        }
        
        if(!temLugar){
            throw new RuntimeException("Game Over");
        }
        
        addTile();
    }
    
    
    
}
