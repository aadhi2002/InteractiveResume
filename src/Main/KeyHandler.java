package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean upPressed,downPressed,leftPressed,rightPressed;
    Panel p;
    public KeyHandler(Panel p){
        this.p=p;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code=e.getKeyCode();

        //title
        if(p.gameState==p.titleState){
            if(code==KeyEvent.VK_W || code==KeyEvent.VK_UP){
                if(p.ui.commandNumber <= 0) p.ui.commandNumber=0;
                else p.ui.commandNumber-=1;
                //DEBUG
//                System.out.println("COMMAND NUMBER = "+p.ui.commandNumber);
            }
            if(code==KeyEvent.VK_S || code==KeyEvent.VK_DOWN){
                if(p.ui.commandNumber>=1) p.ui.commandNumber=1;
                else p.ui.commandNumber+=1;
                //DEBUG
//                System.out.println("COMMAND NUMBER = "+p.ui.commandNumber);
            }
            if(code == KeyEvent.VK_ENTER) {
                if(p.ui.commandNumber==0){
                    p.gameState=p.playState;
                }else{
                    System.exit(0);
                }
            }
        }

        //play
        if(p.gameState==p.playState){
            if(code==KeyEvent.VK_W){
                upPressed=true;
            }
            if(code==KeyEvent.VK_S){
                downPressed=true;
            }
            if(code==KeyEvent.VK_A){
                leftPressed=true;
            }
            if(code==KeyEvent.VK_D){
                rightPressed=true;
            }
            if(code==KeyEvent.VK_P){
                p.gameState=p.pauseState;
            }
        }

        //pause
        else if(p.gameState==p.pauseState){
            if(code==KeyEvent.VK_P){
                p.gameState=p.playState;
            }
        }

        //dialogue
        if(p.gameState==p.dialogueState){
            if(code==KeyEvent.VK_ENTER){
                p.gameState=p.playState;
            }
//            if(code==KeyEvent.VK_H){
//                if(p.ch.temp[p.ch.i].dialogueIndex >= p.ch.temp[p.ch.i].dialogues.length) p.ch.temp[p.ch.i].dialogueIndex = 0;
//                p.ch.temp[p.ch.i].dialogueIndex+=1;
//            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code=e.getKeyCode();
        if(code==KeyEvent.VK_W){
            upPressed=false;
        }
        if(code==KeyEvent.VK_S){
            downPressed=false;
        }
        if(code==KeyEvent.VK_A){
            leftPressed=false;
        }
        if(code==KeyEvent.VK_D){
            rightPressed=false;
        }
    }
}
