package Main;

import java.awt.*;

public class EventHandler {
    Panel p;
    Rectangle eventRect;
    int eventRectDefaultX,getEventRectDefaultY;
    public EventHandler(Panel p){
        this.p=p;eventRect=new Rectangle();
        eventRect.x=23;
        eventRect.y=23;
        eventRect.height=2;
        eventRect.width=2;
        eventRectDefaultX=eventRect.x;
        getEventRectDefaultY=eventRect.y;
    }
    public void checkEvent(){
        if(hit(27,3,"right")){
            //event happen
        }
    }
    public boolean hit(int eventCol,int eventRow,String reqDirection){
        boolean hit = false;
        p.ch.solidArea.x = p.ch.worldX + p.ch.solidArea.x;
        p.ch.solidArea.y = p.ch.worldY + p.ch.solidArea.y;
        eventRect.x = eventCol*p.tileSize + eventRect.x;
        eventRect.y = eventRow*p.tileSize + eventRect.y;
        if(p.ch.solidArea.intersects(eventRect)){
            if(p.ch.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")){
                hit=true;
            }
        }
        p.ch.solidArea.x = p.ch.solidAreaDefaultX;
        p.ch.solidArea.y = p.ch.solidAreaDefaultY;
        eventRect.x = eventRectDefaultX;
        eventRect.y = getEventRectDefaultY;

        return hit;
    }
}
