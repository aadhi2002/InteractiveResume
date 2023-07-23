package Main;
import Entity.Entity;
import Entity.MyCharacter;
import Map.MapManager;
import Object.SuperObject;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Panel extends JPanel implements Runnable{
    public final int ogTileSize=48;
    public final int scale=3;
    public final int tileSize=scale*ogTileSize;
    public final int screenRow=5;
    public final int screenCol=10;
    public final int screenWidth=screenCol*tileSize;
    public final int screenHeight=screenRow*tileSize;

    public final int maxWorldCol =  25;
    public final int maxWorldRow = 5;
    public final int worldWidth = tileSize*maxWorldCol;
    public final int worldHeight = tileSize*maxWorldRow;

    //FPS
    private final int FPS=60;
    //ROOM
//    public final int roomCol=30;
//    public final int roomRow=20;

    Thread portThread;
    KeyHandler keyH=new KeyHandler(this);
    public MyCharacter ch=new MyCharacter(this,keyH);
    MapManager mapManager=new MapManager(this);
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public SuperObject obj[] = new SuperObject[10];
    public AssetSetter assetSetter=new AssetSetter(this);
    public Entity[] npc = new Entity[10];

    //UI
    public UI ui = new UI(this);

    //GAME STATE
    public int gameState;
    public final int titleState=0;
    public final int playState=1;
    public final int pauseState=2;
    public final int dialogueState=3;


    public Panel(){
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    public void setUpStage(){
        assetSetter.setObject();
        assetSetter.setNPC();
//        gameState=playState;
        gameState=titleState;
    }
    public void startThread(){
        portThread=new Thread(this);
        portThread.start();
    }
    @Override
    public void run() {
        double drawTime=1000000000/FPS;
        double nextDrawTime=System.nanoTime() + drawTime;
        while (portThread!=null){
            update();
            repaint();
            try{
                double delta=nextDrawTime-System.nanoTime();
                delta/=1000000;
                if(delta<0){
                    delta=0;
                }
                Thread.sleep((long)delta);
                nextDrawTime+=drawTime;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public void update(){

        if(gameState==playState){
            ch.update();
            for (Entity entity : npc) {
                if (entity != null) entity.update();
            }
        }
        if(gameState==pauseState){
            //no update
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D) g;

        //check Time
        long start=0;
        start=System.nanoTime();

        //title screen
        if(gameState==titleState){
            ui.draw(g2);
        }

        else{
            //map
            mapManager.draw(g2);

            //object
            for (SuperObject superObject : obj) {
                if (superObject != null) {
                    superObject.draw(g2, this);
                }
            }

            //npc
            for (Entity entity : npc) {
                if (entity != null) entity.draw(g2);
            }

            //player
            ch.draw(g2);

            //ui
            ui.draw(g2);
        }

        //end
        long end = System.nanoTime()-start;
//        System.out.println("DRAW TIME: "+end);
        g2.dispose();
    }
}
