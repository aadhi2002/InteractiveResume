package Object;

import Main.Panel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Door extends SuperObject{
    public OBJ_Door(Panel p){
        name = "door";
        image=super.setup("doorClose",p);
        collision=true;
    }
}
