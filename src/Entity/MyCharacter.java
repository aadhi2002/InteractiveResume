package Entity;

import Main.KeyHandler;
import Main.Panel;
import Main.Utility;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class MyCharacter extends Entity{
    Panel p;
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    public MyCharacter(Panel p, KeyHandler keyH){
        super(p);
        this.p=p;
        this.keyH=keyH;
        screenX=p.screenHeight/2 + 3*p.tileSize/2;
        screenY=p.screenHeight/2 - p.tileSize/2;
//        screenX=p.screenHeight/2;
//        screenY=p.screenHeight/2;
        solidArea=new Rectangle();
        solidArea.x=16;
        solidArea.y=32;
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;
        solidArea.width=112;
        solidArea.height=112;
        setDefaultValues();
        getPlayer();
    }
    public void getPlayer(){
        up1=super.setup("Player/boy_up_1");
        up2=super.setup("Player/boy_up_2");
        up3=super.setup("Player/boy_up_3");
        down1=super.setup("Player/boy_down_1");
        down2=super.setup("Player/boy_down_2");
        down3=super.setup("Player/boy_down_3");
        left1= super.setup("Player/boy_left_1");
        left2= super.setup("Player/boy_left_2");
        left3= super.setup("Player/boy_left_3");
        right1= super.setup("Player/boy_right_1");
        right2= super.setup("Player/boy_right_2");
        right3= super.setup("Player/boy_right_3");
    }

//    public BufferedImage setup(String imageName){
//        Utility utility = new Utility();
//        BufferedImage scaledImage = null;
//        try{
//            scaledImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/"+imageName+".png")));
//            scaledImage=utility.scaleImage(scaledImage,p.tileSize,p.tileSize);
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//        return scaledImage;
//    }
    public void setDefaultValues(){
        speed=5;
        direction="down";
        worldX=p.screenHeight/2 + 6*p.tileSize;
        worldY=p.screenHeight/2 - p.tileSize/2;
    }
    public void update(){
        if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed){
            if(keyH.upPressed){
                direction="up";
            }else if(keyH.downPressed){
                direction="down";
            }else if(keyH.leftPressed){
                direction="left";
            }else {
                direction="right";
            }

            collisionOn=false;
            //tile collision
            p.collisionChecker.checkTile(this);

            //object collision
            int objCheck = p.collisionChecker.checkObject(this,true);
            pickUpObject(objCheck);

            //npc collision
            int npcColIndex=p.collisionChecker.checkEntity(this,p.npc);
//            setValuesForInteraction(npcColIndex,p.npc); //for KEY HANDLER
            interactWithNPC(npcColIndex);

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
    }

//    public int i;
//    public Entity[] temp;
//
//    private void setValuesForInteraction(int npcColIndex, Entity[] npc) {
//        this.temp=npc;
//        this.i=npcColIndex;
//    }


    public void pickUpObject(int i){
        if(i!=999){
            String objName=p.obj[i].name;
            switch (objName){
                case "door"-> {
                    p.obj[i].image = p.obj[i].setup("doorOpen",p);
                    p.obj[i].collision=false;
                }
                case "flower" -> {
                }
            }
        }
    }
    public void interactWithNPC(int index){
        if(index!=999){
            p.gameState=p.dialogueState;
            p.npc[index].speak();
        }
    }
    public void draw(Graphics2D g2){
        BufferedImage image = null;
        switch (direction) {
            case "up" -> {
                if (spriteNumber == 1) image = up2;
                if (spriteNumber == 2) image = up1;
                if (spriteNumber == 3) image = up3;
                if (spriteNumber == 4) image = up1;
            }
            case "down" -> {
                if (spriteNumber == 1) image = down2;
                if (spriteNumber == 2) image = down1;
                if (spriteNumber == 3) image = down3;
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
