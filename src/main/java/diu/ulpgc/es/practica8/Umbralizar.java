/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package diu.ulpgc.es.practica8;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author Jorge Santana
 */
public class Umbralizar extends JPanel {
    private BufferedImage imagen;
    private Mat imgOr;
    private int value;
    private Mat save;

    public void setImagen(BufferedImage imagen) {
        this.imagen = imagen;
        repaint();
    }

    public void setOriginalImage(Mat imgOr) {
        this.imgOr = imgOr;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setSave(Mat save) {
        this.save = save;
    }

    public BufferedImage getImagen() {
        return imagen;
    }

    public Mat getOriginalImage() {
        return imgOr;
    }

    public int getValue() {
        return value;
    }

    public Mat getSave() {
        return save;
    }

    public Umbralizar() {
    }
    
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.setPreferredSize(new Dimension(imagen.getWidth(),imagen.getHeight()));
        g.drawImage(imagen, 0, 0, null);
    }


    public Mat umbralizar(Mat imagen_original, Integer umbral) {
        // crear dos imágenes en niveles de gris con el mismo
        // tamaño que la original
        Mat imagenGris = new Mat(imagen_original.rows(),
                                imagen_original.cols(),
                                CvType.CV_8U);
        Mat imagenUmbralizada = new Mat(imagen_original.rows(),
                                        imagen_original.cols(),
                                        CvType.CV_8U);
        // convierte a niveles de grises la imagen original
        Imgproc.cvtColor(imagen_original,
                        imagenGris,
                        Imgproc.COLOR_BGR2GRAY);
        // umbraliza la imagen:
        // - píxeles con nivel de gris > umbral se ponen a 1
        // - píxeles con nivel de gris <= umbra se ponen a 0
        Imgproc.threshold(imagenGris,
                        imagenUmbralizada,
                        umbral,
                        255,
                        Imgproc.THRESH_BINARY);
        // se devuelve la imagen umbralizada
        return imagenUmbralizada;
    }    
}
