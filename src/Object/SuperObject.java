package Object;

import Main.Panel;
import Main.Utility;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class SuperObject {
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX,worldY;
    public Rectangle solidArea=new Rectangle(0,0,48*3,48*3);
    public int solidAreaDefaultX=0;
    public int solidAreaDefaultY=0;
        Utility utility = new Utility();
    public void draw(Graphics2D g2, Panel p){
        int screenX = worldX - p.ch.worldX + p.ch.screenX;
        int screenY = worldY - p.ch.worldY + p.ch.screenY;
        g2.drawImage(image,screenX,screenY,null);
    }
    public BufferedImage setup(String objectName,Panel p){
        BufferedImage image = null;
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Object/"+objectName+".png")));
            image=utility.scaleImage(image,p.tileSize,p.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }
        return image;
    }
}
