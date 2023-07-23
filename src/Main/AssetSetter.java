package Main;
import Entity.NPC_Mom;
import Object.OBJ_Door;

public class AssetSetter {
    Panel p;
    AssetSetter(Panel p){
        this.p=p;
    }
    public void setObject(){
        p.obj[0]=new OBJ_Door(p);
        p.obj[0].worldX=p.tileSize*14;
        p.obj[0].worldY=p.tileSize*2;
    }
    public void setNPC(){
        p.npc[0]=new NPC_Mom(p);
        p.npc[0].worldX=p.tileSize*17;
//        p.npc[0].worldY=p.screenHeight/2 - p.tileSize/2;
        p.npc[0].worldY=p.tileSize*2;
    }
}
