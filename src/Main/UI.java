package Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class UI {
    Panel p;
    Graphics2D g2;
    Font arial_40;
    public boolean messageOn=false;
    public String message="";
    public String currentDialogue="";
    public int commandNumber = 0;
    public UI(Panel p){
        arial_40=new Font("Arial",Font.PLAIN,40);
        this.p=p;
    }

    public void showMessage(String text){
        message=text;
        messageOn=true;
    }
    public void draw(Graphics2D g2){
        this.g2=g2;

        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);

        //title
        if(p.gameState==p.titleState){
            drawTitleScreen();
        }

        //play
        if(p.gameState== p.playState){
            //
        }

        //pause
        if(p.gameState==p.pauseState){
            drawPauseScreen();
        }

        //dialogue
        if(p.gameState==p.dialogueState){
            drawDialogueScreen();
        }
    }

    public void drawTitleScreen(){
        g2.setColor(new Color(32,26,38));
        g2.fillRect(0,0,p.screenWidth,p.screenHeight);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,80F));
        String text = " AADHITHYA'S PORTFOLIO";
        int x = getX(text);
        int y = p.tileSize;

        //shadow
        g2.setColor(new Color(0,0,0));
        g2.drawString(text,x+7,y+7 );

        //front text
        g2.setColor(new Color(255,255,255,200));
        g2.drawString(text,x,y);

        x=p.screenWidth/2 - p.tileSize;
        y+=p.tileSize/2;
        BufferedImage image;
        image=setup("Title/prog_");
        g2.drawImage(image,x,y,null);


        //menu text display
        text = "BEGIN";
        g2.setColor(new Color(0,0,0));
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,50F));
        x=getX(text);
        y+=p.tileSize*5/2;
        g2.drawString(text,x+7,y+7);
        g2.setColor(new Color(255,255,255,200));
        g2.drawString(text,x,y);
        if(commandNumber<=0){
            g2.setColor(new Color(0,0,0));
            g2.drawString(">",x-p.tileSize/2 + 5,y+5);
            g2.setColor(new Color(255,255,255,200));
            g2.drawString(">",x-p.tileSize/2,y);
        }


        text = "QUIT";
        g2.setColor(new Color(0,0,0));
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,50F));
        x=getX(text);
        y+=p.tileSize/2;
        g2.drawString(text,x+7,y+7);
        g2.setColor(new Color(255,255,255,200));
        g2.drawString(text,x,y);
        if(commandNumber>=1){
            g2.setColor(new Color(0,0,0));
            g2.drawString(">",x-p.tileSize/2 + 5,y+5);
            g2.setColor(new Color(255,255,255,200));
            g2.drawString(">",x-p.tileSize/2,y);
        }
    }
    /*public void drawText(String text,int x,int y,int size,boolean shadow){
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,size));
        if(shadow) drawShadow(text,x,y,size);
        g2.setColor(new Color(255,255,255,200));
        g2.drawString(text,x,y);
    }
    public void drawShadow(String text,int x,int y,int size){
        g2.setColor(new Color(0,0,0));
        g2.drawString(text,x+5,y+5);
    }*/
    public BufferedImage setup(String imageName){
        Utility utility = new Utility();
        BufferedImage scaledImage = null;
        try{
            scaledImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/"+imageName+".jpg")));
            scaledImage=utility.scaleImage(scaledImage,5*p.tileSize/2,5*p.tileSize/2);
        }catch (IOException e){
            e.printStackTrace();
        }
        return scaledImage;
    }
    public void drawPauseScreen(){
        String text = "PAUSE";
        int length = (int) g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x=getX(text),y=p.screenHeight/2;
        g2.drawString(text,x,y);
    }
    public void drawDialogueScreen(){
        //window settings HORIZONTAL
        int x=p.tileSize*2;
        int y=p.tileSize*3;
        int width=p.screenWidth-(p.tileSize*4);
        int height=p.tileSize*2;

        //VERTICAL
        /*int x=p.tileSize*6;
        int y=p.tileSize/2;
        int width=p.screenWidth-(p.tileSize*7);
        int height=p.tileSize*4*/

        drawSubWindow(x,y, width,height);

        //speak
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,20F));
        x+=p.tileSize/4;
        y+=p.tileSize/4;
        g2.drawString(currentDialogue,x,y);
    }
    public void drawSubWindow(int x,int y,int width,int height){
        Color c = new Color(0,0,0,200);
        g2.setColor(c);
        g2.fillRoundRect(x,y,width,height,50,50);
        c = new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5,y+5,width-10,height-10,50,50);
    }
    public int getX(String text){
        int length = (int) g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        return p.screenWidth/2 - length/2;
    }
}
