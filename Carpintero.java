/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Carpinteria;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author informatica
 */
public class Carpintero implements Runnable {

    Carpinteria carpinteria;
    int tipoCarpintero;

    public Carpintero(Carpinteria c, int tipoC) {
        this.carpinteria = c;
        this.tipoCarpintero = tipoC;
    }
    
    private void termiarMueble(){
        try {
            Thread.sleep((int) (1000 * Math.random() * 6 + 1));
        } catch (InterruptedException ex) {
            Logger.getLogger(Carpintero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        switch (tipoCarpintero) {
            case 1:
                boolean seguirProdP1 = true;
                while (seguirProdP1) {                 
                    seguirProdP1 = carpinteria.putBuffer1();
                }
                break;
            case 2:
                boolean seguirProdP2 = true;
                while (seguirProdP2) {
                    seguirProdP2 = carpinteria.putBuffer2();
                }
                break;
            case 3:
                boolean seguirProdP3 = true;
                while (seguirProdP3) {
                    seguirProdP3 = carpinteria.putBuffer3();
                }
                break;
            case 4:
                boolean seguirEnsamblando = true;
                while (seguirEnsamblando) {
                    seguirEnsamblando = carpinteria.ensamblar();
                    termiarMueble();
                }
                break;
        }
    }
}
