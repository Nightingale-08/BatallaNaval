import javax.swing.*;
import java.util.ArrayList;
/**
 * Esta clase se encarga de controlar el arraylist de los barcos que va a colocar el usuario
 * @author Marlon Anacona marlon.anacona@correounivalle.edu.co
 * @author Luis Belalcazar luis.Belalcazar@correounivalle.edu.co
 * @version v.1.0.0 date:21/03/2022
 */
public class barcosRecibidos {

public boolean barcosColocados1;
public static  int barco=0;
public static  ArrayList<JButton> botonesUsados=new ArrayList<JButton>();


public barcosRecibidos() {

}
    /**
     * Metodo get del arraylist
     */

public void getBarcos(ArrayList enemigo){

     botonesUsados=enemigo;

}
    /**
     * Metodo set del arraylist
     */
public ArrayList setBarcos(){

    return botonesUsados;
}

    public int numero(int barco){
    this.barco=barco;
    return this.barco;
    }

    public int setNumero(){
    return  barco;
    }

    public boolean setcolocados(boolean barcosColocados){
       if (barcosColocados){
           barco++;
    numero(barco);
       }
        return barcosColocados1;
    }

    public boolean get(){

        return barcosColocados1;
    }
}
