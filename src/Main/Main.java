package Main;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        JFrame window=new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("AADHITHYA'S PORTFOLIO");
        BufferedImage image = null;
        try{
            image= ImageIO.read(Objects.requireNonNull(Main.class.getResourceAsStream("/Title/prog_.jpg")));
        }catch (IOException e){
            e.printStackTrace();
        }
        window.setIconImage(image);
        Panel p=new Panel();
        window.add(p);
        window.pack();
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        p.setUpStage();
        p.startThread();

    }
}