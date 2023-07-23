package Entity;

import Main.Panel;
import Main.Utility;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Entity {
    public int worldX,worldY;
    public int speed;
    BufferedImage up1,up2,up3,down1,down2,down3,left1,left2,left3,right1,right2,right3;
    public String direction;
    public int spriteCounter=0;
    public int spriteNumber=1;
    public Rectangle solidArea=new Rectangle(0,0,48*3,48*3);
    public int solidAreaDefaultX,solidAreaDefaultY;
    public boolean collisionOn=false;
    public int actionLockCounter=0;
    Panel p;
    public String[] dialogues = new String[20];
    public int dialogueIndex = 0;
    public Entity(Panel p){
        this.p=p;
    }
    public void setAction(){ }
    public void speak(){ }
    public void update(){
        setAction();
        collisionOn=false;
        p.collisionChecker.checkTile(this);
        p.collisionChecker.checkObject(this,false);
        p.collisionChecker.checkPlayer(this);

        if(!collisionOn){
            switch (direction) {
                case "left" -> worldX -= speed;
                case "right" -> worldX += speed;
            }
        }

        spriteCounter+=1;
        if(spriteCounter>10){
            spriteCounter=0;
            if(spriteNumber==1){
                spriteNumber=2;
            }else if(spriteNumber==2){
                spriteNumber=3;
            }else if(spriteNumber==3){
                spriteNumber=4;
            }else if(spriteNumber==4){
                spriteNumber=1;
            }
        }
    }

    //setup
    public BufferedImage setup(String imageName){
        Utility utility = new Utility();
        BufferedImage scaledImage = null;
        try{
            scaledImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/"+imageName+".png")));
            scaledImage=utility.scaleImage(scaledImage,p.tileSize,p.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }
        return scaledImage;
    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;
        int screenX = worldX - p.ch.worldX + p.ch.screenX;
        int screenY = worldY - p.ch.worldY + p.ch.screenY;
        switch (direction) {
            case "up" -> {
                if (spriteNumber == 1) image = up2;
                if (spriteNumber == 2) image = up1;
                if (spriteNumber == 3) image = up3;
                if (spriteNumber == 4) image = up1;
            }
            case "down" -> {
                if (spriteNumber == 1) image = down1;
                if (spriteNumber == 2) image = down1;
                if (spriteNumber == 3) image = down1;
                if (spriteNumber == 4) image = down1;
            }
            case "left" -> {
                if (spriteNumber == 1) image = left2;
                if (spriteNumber == 2) image = left1;
                if (spriteNumber == 3) image = left3;
                if (spriteNumber == 4) image = left1;
            }
            case "right" -> {
                if (spriteNumber == 1) image = right2;
                if (spriteNumber == 2) image = right1;
                if (spriteNumber == 3) image = right3;
                if (spriteNumber == 4) image = right1;
            }
        }
        g2.drawImage(image,screenX,screenY,null);
    }
}
