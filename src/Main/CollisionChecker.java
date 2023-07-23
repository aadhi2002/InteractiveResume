package Main;

import Entity.Entity;

public class CollisionChecker {
    Panel p;
    public CollisionChecker(Panel p){
        this.p=p;
    }
    public void checkTile(Entity entity){
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x +entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/p.tileSize;
        int entityRightCol = entityRightWorldX/p.tileSize;
        int entityTopRow = entityTopWorldY/p.tileSize;
        int entityBottomRow = entityBottomWorldY/p.tileSize;
        int tileNum1, tileNum2;
        switch (entity.direction) {
            case "left" -> {
                entityLeftCol = (entityLeftWorldX - entity.speed) / p.tileSize;
                tileNum1 = p.mapManager.mapTileNum[entityTopRow][entityLeftCol];
                tileNum2 = p.mapManager.mapTileNum[entityBottomRow][entityLeftCol];
                if (p.mapManager.map[tileNum1].collision || p.mapManager.map[tileNum2].collision) {
                    entity.collisionOn = true;
                }
            }
            case "right" -> {
                entityRightCol = (entityRightWorldX + entity.speed) / p.tileSize;
                tileNum1 = p.mapManager.mapTileNum[entityTopRow][entityRightCol];
                tileNum2 = p.mapManager.mapTileNum[entityBottomRow][entityRightCol];
                if (p.mapManager.map[tileNum1].collision || p.mapManager.map[tileNum2].collision) {
                    entity.collisionOn = true;
                }
            }
        }
    }
    public int checkObject(Entity entity,boolean player){
        int index=999;
        for(int i = 0;i<p.obj.length;i++){
            if(p.obj[i]!=null){
                entity.solidArea.x=entity.worldX + entity.solidArea.x;
                entity.solidArea.y=entity.worldY + entity.solidArea.y;
                p.obj[i].solidArea.x = p.obj[i].worldX + p.obj[i].solidArea.x;
                p.obj[i].solidArea.y = p.obj[i].worldY + p.obj[i].solidArea.y;

                switch (entity.direction) {
                    case "left" -> {
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(p.obj[i].solidArea)) {
                            if(p.obj[i].collision) entity.collisionOn=true;
                            if(player) index=i;
                        }
                    }
                    case "right" -> {
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(p.obj[i].solidArea)) {
                            if(p.obj[i].collision) entity.collisionOn=true;
                            if(player) index=i;
                        }
                    }
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y=entity.solidAreaDefaultY;
                p.obj[i].solidArea.x = p.obj[i].solidAreaDefaultX;
                p.obj[i].solidArea.y = p.obj[i].solidAreaDefaultY;
            }
        }
        return index;
    }

    //npc
    public int checkEntity(Entity entity, Entity[] target){
        int index=999;
        for(int i = 0;i<target.length;i++){
            if(target[i]!=null){
                entity.solidArea.x=entity.worldX + entity.solidArea.x;
                entity.solidArea.y=entity.worldY + entity.solidArea.y;
                target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
                target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;

                switch (entity.direction) {
                    case "left" -> {
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            entity.collisionOn=true;
                            index=i;
                        }
                    }
                    case "right" -> {
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            entity.collisionOn=true;
                            index=i;
                        }
                    }
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y=entity.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;
            }
        }
        return index;
    }
    public void checkPlayer(Entity entity){
        entity.solidArea.x=entity.worldX + entity.solidArea.x;
        entity.solidArea.y=entity.worldY + entity.solidArea.y;
        p.ch.solidArea.x = p.ch.worldX + p.ch.solidArea.x;
        p.ch.solidArea.y = p.ch.worldY + p.ch.solidArea.y;

        switch (entity.direction) {
            case "left" -> {
                entity.solidArea.x -= entity.speed;
                if (entity.solidArea.intersects(p.ch.solidArea)) {
                    entity.collisionOn=true;
                }
            }
            case "right" -> {
                entity.solidArea.x += entity.speed;
                if (entity.solidArea.intersects(p.ch.solidArea)) {
                    entity.collisionOn=true;
                }
            }
        }
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y=entity.solidAreaDefaultY;
        p.ch.solidArea.x = p.ch.solidAreaDefaultX;
        p.ch.solidArea.y = p.ch.solidAreaDefaultY;
    }
}
