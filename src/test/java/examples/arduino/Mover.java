/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examples.arduino;

import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import com.panamahitek.PanamaHitek_MultiMessage;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

public class Mover {
public int servo;
public int distancia;
private String registro;
JLabel label = new JLabel("Distancia: "+distancia+" CM");
    public static void main(String[] args) {
             try {
            ino.arduinoRX("COM8", 9600, listener);
        } catch (ArduinoException ex) {
            Logger.getLogger(rxSimple.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SerialPortException ex) {
            Logger.getLogger(rxSimple.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    static PanamaHitek_Arduino ino = new PanamaHitek_Arduino();
    static PanamaHitek_MultiMessage MultiM = new PanamaHitek_MultiMessage(2,ino);
    //Se crea un eventListener para el puerto serie
    static SerialPortEventListener listener = new SerialPortEventListener() {
        
                Mover ff = new Mover();
        @Override
        public void serialEvent(SerialPortEvent serialPortEvent) {
            try {
                if (MultiM.dataReceptionCompleted()) {
                    int servo;
                    int distancia;
                    servo=Integer.parseInt(MultiM.getMessage(0));
                    distancia=Integer.parseInt(MultiM.getMessage(1));
                    ff.jalar1(servo);
                    ff.jalar2(distancia);
                    System.out.println(distancia);
                    MultiM.flushBuffer();
                }
            } catch (SerialPortException ex) {
                Logger.getLogger(rxSimple.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ArduinoException ex) {
                Logger.getLogger(rxSimple.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };
    
    public Mover() {
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }
                JFrame frame = new JFrame("Aver");
                AnimationPane gg = new AnimationPane();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                gg.setBackground(Color.black);
                frame.add(gg);
                label.setBounds(50, 200, 200, 30);
                gg.add(label);
                label.setForeground(Color.white);
                frame.pack();
                frame.setSize(1350,720);
                frame.setResizable(true);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                
            }

        });
    }

    public class AnimationPane extends JPanel {
        
        private BufferedImage BarraH;
        private BufferedImage BarraV;
        private BufferedImage fondo;
        
         public AnimationPane() {
            try {
                BarraH = ImageIO.read(new File("C:/Users/marti/barrita.png"));
                BarraV = ImageIO.read(new File("C:/Users/marti/palillo.png"));
               Timer timer = new Timer(10, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                       
                       label.setText("Distancia: "+distancia+" CM");
                        repaint();
                    }

                });
                timer.setRepeats(true);
                timer.setCoalesce(true);
                timer.start();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int x=servo*(8);
            int y=distancia;
           g.drawImage(BarraV, x, 10, 20, 650, this);
            g.drawImage(BarraH, x-230, 650-(y*2), 500, 20, this);
            if(distancia < 1000){
                
                try {
                    BarraH = ImageIO.read(new File("C:/Users/marti/barrita.png"));
                } catch (IOException ex) {
                    Logger.getLogger(Mover.class.getName()).log(Level.SEVERE, null, ex);
                }
        }else{
                
                try {
                    BarraH = ImageIO.read(new File("C:/Users/marti/rectangulo.png"));
                } catch (IOException ex) {
                    Logger.getLogger(Mover.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            
            if(distancia < 50){ try
        {
           File pitido = new File("C:/Users/marti/Documents/NetBeansProjects/leerArchivos/beep.wav");
           if(pitido.exists()){
               AudioInputStream PSA = AudioSystem.getAudioInputStream(pitido);
               Clip clip = AudioSystem.getClip();
               clip.open(PSA);
               clip.start();
           }
           else
           {
        System.out.println("Error en el Sistema");
           }
           
                 
         Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    try {
      FileWriter myWriter = new FileWriter("C:/Users/marti/Desktop/registro.txt");
      registro=registro+"\n Se detecto movimiento el "+timestamp+" a "+distancia+"cm";
      myWriter.write(registro);
      myWriter.close();
    } catch (IOException e) {
      System.out.println(">:[");
      e.printStackTrace();
    }
       }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
            
            }

    }
    }
    public  int jalar1(int a){
        servo=a;
        return a;
    }
    public  int jalar2(int a){
        distancia=a;
        return a;
    }
    

}