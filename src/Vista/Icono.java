package Vista;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * Icono representa un Icon de las librerias de java.
 * Su finalidad es extraer una imagen de este paquete Vista y transformala en un objeto Icon.
 * @author Sofito-Chan
 */

public class Icono implements Icon{

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        //El objetivo de esta implementación es borrar los iconos por defecto de JOptionPane.
        Image image = new ImageIcon(getClass().getResource("")).getImage();
        g.drawImage(image, x, y, c);
    }

    @Override
    public int getIconWidth() {
        return 1;
    }

    @Override
    public int getIconHeight() {
        return 1;
    }

}
