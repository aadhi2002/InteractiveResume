package Map;

import Main.Panel;
import Main.Utility;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class MapManager {
    public MapS[] map;
    Panel p;
    public int[][] mapTileNum;
    public MapManager(Panel p){
        this.p=p;
        map=new MapS[20];
        mapTileNum=new int[p.maxWorldRow][p.maxWorldCol];
        getTileImage();
        loadMap();
    }
    public void getTileImage(){
        setup(0,"grey",false);
        setup(1,"tile_",false);
        setup(2,"sprite_0",false);
        setup(3,"photo",false);
        setup(4,"side",true);
        setup(5,"cloud",false);
        setup(6,"car",false);
        setup(7,"blue",false);
//            setup(8,"doorOpen",false);
    }
    public void setup(int index,String imagePath, boolean collision){
        Utility utility = new Utility();
        try{
            map[index]=new MapS();
            map[index].image=ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/"+imagePath+".png")));
            map[index].image=utility.scaleImage(map[index].image,p.tileSize,p.tileSize);
            map[index].collision=collision;

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void loadMap(){
        try{
            InputStream is = getClass().getResourceAsStream("/Map/map.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col=0;
            int row=0;
            while(col<p.maxWorldCol && row<p.maxWorldRow){
                String s=br.readLine();
                while(col<p.maxWorldCol){
                    String[] num =s.split(" ");
                    mapTileNum[row][col]=Integer.parseInt(num[col]);
                    col+=1;
                }
                if(col==p.maxWorldCol){
                    col=0;
                    row+=1;
                }
            }
            br.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2){
        int worldRow = 0;
        int worldCol = 0;

        while(worldCol < p.maxWorldCol && worldRow < p.maxWorldRow){
            int tileNum=mapTileNum[worldRow][worldCol];
            int worldX = worldCol * p.tileSize;
            int worldY = worldRow * p.tileSize;
            int screenX = worldX - p.ch.worldX + p.ch.screenX;
            int screenY = worldY - p.ch.worldY + p.ch.screenY;
/*            if(
                    worldX > p.ch.worldX - p.ch.screenX &&
                    worldX < p.ch.worldX + p.ch.screenX &&
                    worldY > p.ch.worldY - p.ch.screenY &&
                    worldY < p.ch.worldY + p.ch.screenY
            ){
                g2.drawImage(map[tileNum].image,screenX,screenY,p.tileSize,p.tileSize,null);
            }*/
            g2.drawImage(map[tileNum].image,screenX,screenY,null);
            worldCol+=1;

            if(worldCol==p.maxWorldCol){
                worldCol=0;
                worldRow+=1;
            }
        }
    }
}
