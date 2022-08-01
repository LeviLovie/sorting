import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
 
public class Main {
    public static void main(String[] args) {
        SwingSquareMovingFrame frame=new SwingSquareMovingFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
 
class SwingSquareMovingFrame extends JFrame {
    SwingSquareMovingFrame() {
        int screenWidth=Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight=Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation(screenWidth/6, screenHeight/6);
        setSize((screenWidth*2)/3, (screenHeight*2)/3);
        setTitle("Square Moving Test Program");
        setContentPane(new SquareView());
    }
}
 
class SquareModel {
    SquareModel(int squareX, int squareY, int squareSide) {
        x=squareX;
        y=squareY;
        side=squareSide;
    }
    public Rectangle2D createSquare() {
        return new Rectangle2D.Float((float)x, (float)y, (float)side, (float)side);
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getSide() {
        return side;
    }
    public void setX(int inputX) {
        x=inputX;
    }
    public void setY(int inputY) {
        y=inputY;
    }
    public void setSide(int inputSide) {
        side=inputSide;
    }
    
    private int x;
    private int y;
    private int side;
}
 
class SquareView extends JPanel {
    SquareView() {
        data=new SquareModel(INITIAL_X, INITIAL_Y, SQUARE_SIDE);
        square=data.createSquare();
        addKeyListener(new SquareController());
        setFocusable(true);
    }
    public void moveSquare(int dx, int dy) {
        data.setX(data.getX()+dx);
        data.setY(data.getY()+dy);
        square=data.createSquare();
        repaint();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D)g;
        g2.setPaint(Color.BLUE);
        g2.fill(square);
    }
    
    private SquareModel data;
    private Rectangle2D square;
    
    public static final int INITIAL_X=100;
    public static final int INITIAL_Y=100;
    public static final int SQUARE_SIDE=20;
    
    class SquareController extends KeyAdapter {
        public void KeyPressed(KeyEvent event) {
            int keyCode=event.getKeyCode();
            int d;
            
            if(event.isShiftDown()) d=STEP_INCREMENT;
            else d=PIXEL_INCREMENT;
            
            if(keyCode==KeyEvent.VK_LEFT) moveSquare(-d, 0);
            else if(keyCode==KeyEvent.VK_RIGHT) moveSquare(d, 0);
            else if(keyCode==KeyEvent.VK_UP) moveSquare(0, -d);
            else if(keyCode==KeyEvent.VK_DOWN) moveSquare(0, d);
        }
        public void KeyTyped(KeyEvent event) {
            char keyChar=event.getKeyChar();
            int d;
            
            if(Character.isUpperCase(keyChar)) {
                d=STEP_INCREMENT;
                keyChar=Character.toLowerCase(keyChar);
            } else {
                d=PIXEL_INCREMENT;
            }
            
            if(keyChar=='a' || keyChar=='ф') moveSquare(-d, 0);
            else if(keyChar=='d' || keyChar=='в') moveSquare(d, 0);
            else if(keyChar=='w' || keyChar=='ц') moveSquare(0, -d);
            else if(keyChar=='s' || keyChar=='ы'|| keyChar=='і') moveSquare(0, d);
        }
        public static final int PIXEL_INCREMENT=1;
        public static final int STEP_INCREMENT=SQUARE_SIDE;
    }
}