package Entity;

import Main.Panel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.util.Objects;
import java.util.Random;

public class NPC_Mom extends Entity{

    public NPC_Mom(Panel p) {
        super(p);
        direction="down";
        speed=1;
        getImage();
        setDialogue();
    }
    public void getImage(){
        this.down1=super.setup("NPC/mom_down");
        this.right1=super.setup("NPC/mom_right1");
        this.right2=super.setup("NPC/mom_right2");
        this.right3=super.setup("NPC/mom_right3");
        this.left1=super.setup("NPC/mom_left1");
        this.left2=super.setup("NPC/mom_left2");
        this.left3=super.setup("NPC/mom_left3");

    }
    public void setDialogue(){
        dialogues[0]="Welcome Home, Aadhithya!";
        dialogues[1]="Your new friends eh?";
        dialogues[2]="How you doin' ?";
    }
    private int count = 0;
    public void setAction(){
        actionLockCounter++;
        if(actionLockCounter == 50){
//            Random r = new Random();
//            int i = r.nextInt(50)+1;
//            if(i<=25) direction="left";
//            else direction="right";
            count+=1;
            if(count<=5) direction="left";
            else direction="right";
            if(count==10) count=0;
            actionLockCounter=0;
        }
    }
    public void speak(){
        if(dialogues[dialogueIndex]==null) dialogueIndex=0;
//        while(dialogues[dialogueIndex]!=null){
//            p.ui.currentDialogue=dialogues[dialogueIndex++];
//        }
//        dialogueIndex=0;
        p.ui.currentDialogue=dialogues[dialogueIndex++];
        switch (p.ch.direction) {
            case "left" -> p.ch.direction = "right";
            case "right" -> direction = "left";
        }
    }
}
