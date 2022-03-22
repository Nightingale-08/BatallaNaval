import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
/**
 *Esta clase controla la cracion y funcionamiento del tablero enemigo
 * @autor Marlon-A. Anacona - O. 2023777 <marlon.anacona@correounivalle.edu.co>
 * @autor Luis-F. Belalcazar - A. 2028783 <luis.felipe.belalcazar@correounivalle.edu.co>
 *  @Version v.1.0.0 date 21/03/22
 */


public class tableroRival extends JFrame {

    GridBagConstraints constraints = new GridBagConstraints();
    private Header headerProject;
    private JPanel panelBarcos,panelMiTablero,panelTableroPc,panelTableroPc2, MiBase, baseEnemiga;
    private  ImageIcon image;
    private static boolean portaaviones,submarino,destructores,fragatas;
    private static int porta=1,subma=2,destruc=3,fragata=4;
    private int numerobarcos;
    private static JButton [][] btBase1 =new JButton[10][10];;
    public static ArrayList<JButton> botonesEnemigosColocados =new ArrayList<JButton>();
    public static ArrayList<String> direcciones =new ArrayList<String>();
    private JButton [][] btEnemy;
    public static boolean contador;
    private JButton salir;
    public static int s,s2;
    private int x1=0;
    private int y1=0;
    private Escucha escucha;
    public static int posicionx[]=new int[20];
    public static int posiciony[]=new int[20];
    barcosRecibidos setInfo= new barcosRecibidos();

    public tableroRival(){
        initGUI();

        //Default JFrame configuration
        this.setTitle("Batalla Naval");

       // this.setPreferredSize(new Dimension(1095,528));
        this.setBackground(new Color(255,255,255));
        this.setUndecorated(true);
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        llenarBaseEnemiga();
        if(setInfo.setNumero()<1){
            colocarBarcosEnemigos();

        }else{

            mostrarbarcos();

        }


    }


    /**
     *Este metodo devuelve las direcciones del arraylist
     */

    public ArrayList direccion(){

        return  direcciones;
    }


    /**
     * Este metodo muestra el tablero enemigo
     */

    private void mostrarbarcos(){


       for (int j = 0; j< btBase1.length; j++){
            for (int i = 0; i< btBase1[j].length; i++){

               if(direcciones.contains(btBase1[i][j].toString())){


                        image = new ImageIcon(getClass().getResource("/resources/porta.png"));

                        btBase1[i][j].setIcon(image);
                    }

            }
        }

    }

    /**
     * Este metodo llena la base enemiga con los barcos correspondientes
     */

    public void llenarBaseEnemiga(){
        int x=10,y=10;

        for (int j = 0; j< btBase1.length; j++){
            for (int i = 0; i< btBase1[j].length; i++){
                btBase1[i][j]=new JButton();
                btBase1[i][j].setBackground(Color.ORANGE);
                btBase1[i][j].setBounds(x,y,35,33);
                MiBase.add(btBase1[i][j],BorderLayout.CENTER);
                x+=35;
            }
            x=10;
            y+=33;
        }
    }

    /**
     * Este metodo elige que cantidad y que tipo de barcos tiene que tener el tablero enemigo
     */
    public int elegirBarcos(int num){
        if(portaaviones==true){

            this.numerobarcos=num-1;

            portaaviones=false;
            submarino=true;
            colocarBarcosEnemigos();
        }else{
            if(submarino==true&&subma!=0){
                subma--;

                if(subma==0){
                    this.numerobarcos=num-1;
                    submarino=false;
                    destructores=true;

                }
                colocarBarcosEnemigos();
            }else{
                if(destructores==true&&destruc!=0){
                    destruc--;
                    if(destruc==0){
                        this.numerobarcos=num-1;
                        destructores=false;
                        fragatas=true;

                    }
                    colocarBarcosEnemigos();
                }else{
                    if(fragatas==true&&fragata!=0){
                        fragata--;
                        if(fragata==0){
                            this.numerobarcos=num-1;
                            fragatas=false;

                        }else{
                            colocarBarcosEnemigos();
                        }

                    }
                }
            }
        }
        return  numerobarcos;
    }

    /**
     * Este metodo coloca barcos de manera vertical en el tablero enemigo
     */

    public boolean colocarbarcoVertical(Object botonP){
        boolean tieneBarco = false;


        for (int i = 0; i < btBase1.length; i++) {
            for (int j = 0; j < btBase1[i].length; j++) {
                if (j < 10 - numerobarcos) {
                    if (botonP == btBase1[i][j]) {

                        for (int h = 0; h < numerobarcos; h++) {
                            j++;
                            if (botonesEnemigosColocados.contains(botonP) || botonesEnemigosColocados.contains(btBase1[i][j])) {

                                tieneBarco = true;
                                break;
                            } else {
                                tieneBarco = false;
                            }
                        }
                    }
                }
            }
        }
        return tieneBarco;
    }
    /**
     * Este metodo coloca barcos de manera Horizontal en el tablero enemigo
     */
    public boolean colocarbarcoHorizontal(Object botonP) {
        boolean tieneBarco = false;

        for (int i = 0; i < btBase1.length; i++) {
            for (int j = 0; j < btBase1[i].length; j++) {
                if (i < 10 - numerobarcos && j >= 10 - numerobarcos) {

                    if (botonP == btBase1[i][j].toString()) {

                        for (int h = 0; h < numerobarcos; h++) {
                            i++;
                            if (botonesEnemigosColocados.contains(botonP) || botonesEnemigosColocados.contains(btBase1[i][j])) {
                                tieneBarco = true;
                                break;
                            } else {
                                tieneBarco = false;
                            }
                        }
                    }
                }
            }
        }
        return tieneBarco;
    }
    /**
     * Este metodo devuelve la posicion en el eje x del boton
     */
    public int[] getPosicionX(){


        return posicionx;
    }
    /**
     * Este metodo devuelve la posicion en el eje y del boton
     */
    public int[] getPosiciony(){

        return posiciony;
    }

    /**
     * Este metodo coloca los barcos en el tablero enemigo
     */

    public void colocarBarcosEnemigos(){


        Random aleatorio=new Random();

        int x,y;
        x=aleatorio.nextInt(9);
        y=aleatorio.nextInt(9);

        boolean horizontal=colocarbarcoHorizontal(btBase1[x][y]);
        boolean vertical=colocarbarcoVertical(btBase1[x][y]);

        if(horizontal==true||vertical==true){


            colocarBarcosEnemigos();
        }else {
            if (portaaviones == true) {
                // System.out.println(x1);
                for (int h = 0; h < numerobarcos; h++) {
                    for (int i = 0; i < btBase1.length; i++) {
                        for (int j = 0; j < btBase1[i].length; j++) {
                            if (j < (10 - numerobarcos) + 1) {
                                if (btBase1[x][y] == btBase1[i][j]) {

                                    image = new ImageIcon(getClass().getResource("/resources/porta.png"));
                                    if (botonesEnemigosColocados.contains(btBase1[i][j])) {

                                    } else {
                                        botonesEnemigosColocados.add(btBase1[i][j]);

                                        direcciones.add(btBase1[i][j].toString());
                                        btBase1[i][j].setIcon(image);
                                        s = i;
                                        s2 = j;
                                        posicionx[x1]=s;
                                        posiciony[x1]=s2;
                                        x1++;
                                    }
                                    j++;
                                    if (botonesEnemigosColocados.contains(btBase1[i][(h + j) - 1])) {

                                    } else {
                                        botonesEnemigosColocados.add(btBase1[i][(h + j) - 1]);
                                        direcciones.add(btBase1[i][(h + j) - 1].toString());
                                        btBase1[i][(h + j) - 1].setIcon(image);
                                        s = i;
                                        s2 =( h + j) - 1;
                                        posicionx[x1]=s;
                                        posiciony[x1]=s2;
                                        x1++;
                                    }
                                }


                            } else {
                                if (i < (10 - numerobarcos) + 1) {
                                    if (btBase1[x][y] == btBase1[i][j]) {
                                        image = new ImageIcon(getClass().getResource("/resources/porta.png"));
                                        if (botonesEnemigosColocados.contains(btBase1[i][j])) {

                                        } else {
                                            botonesEnemigosColocados.add(btBase1[i][j]);
                                            direcciones.add(btBase1[i][j].toString());
                                            btBase1[x][y].setIcon(image);
                                            s = i;
                                            s2 = j;
                                            posicionx[x1]=s;
                                            posiciony[x1]=s2;
                                            x1++;
                                        }
                                        i++;
                                        if (botonesEnemigosColocados.contains(btBase1[(i + h) - 1][j])) {

                                        } else {
                                            botonesEnemigosColocados.add(btBase1[(i + h) - 1][j]);
                                            direcciones.add(btBase1[(i + h) - 1][j].toString());
                                            btBase1[(i + h) - 1][j].setIcon(image);
                                            s = (i + h) - 1;
                                            s2 = j;
                                            posicionx[x1]=s;
                                            posiciony[x1]=s2;
                                            x1++;
                                        }
                                    }
                                } else {
                                    if (btBase1[x][y] == btBase1[i][j]) {
                                        image = new ImageIcon(getClass().getResource("/resources/porta.png"));
                                        direcciones.add(btBase1[i][j].toString());
                                        botonesEnemigosColocados.add(btBase1[i][j]);
                                        btBase1[(i)][j].setIcon(image);
                                        s = i;
                                        s2 = j;
                                        posicionx[x1]=s;
                                        posiciony[x1]=s2;
                                        x1++;

                                        direcciones.add(btBase1[i - h][j].toString());
                                        botonesEnemigosColocados.add(btBase1[i - h][j]);
                                        btBase1[i - h][j].setIcon(image);
                                        s = i - h;
                                        s2 = j;
                                        posicionx[x1]=s;
                                        posiciony[x1]=s2;
                                        x1++;
                                    }
                                }
                            }

                        }
                    }
                }
            } else {
                if (submarino == true) {

                    for (int h = 0; h < numerobarcos; h++) {
                        for (int i = 0; i < btBase1.length; i++) {
                            for (int j = 0; j < btBase1[i].length; j++) {
                                if (j < (10 - numerobarcos) + 1) {
                                    if (btBase1[x][y] == btBase1[i][j]) {

                                        image = new ImageIcon(getClass().getResource("/resources/submarin.png"));

                                        if (botonesEnemigosColocados.contains(btBase1[i][j])) {

                                        } else {

                                            botonesEnemigosColocados.add(btBase1[i][j]);
                                            direcciones.add(btBase1[i][j].toString());
                                            btBase1[i][j].setIcon(image);

                                            s = i;
                                            s2 = j;
                                            posicionx[x1]=s;
                                            posiciony[x1]=s2;
                                            x1++;
                                        }


                                        j++;
                                        if (botonesEnemigosColocados.contains(btBase1[i][(h + j) - 1])) {

                                        } else {
                                            botonesEnemigosColocados.add(btBase1[i][(h + j) - 1]);
                                            direcciones.add(btBase1[i][(h + j) - 1].toString());
                                            s = i;
                                            s2 = (h + j) - 1;
                                            posicionx[x1]=s;
                                            posiciony[x1]=s2;
                                            x1++;
                                        }
                                        btBase1[i][(h + j) - 1].setIcon(image);


                                    }


                                } else {

                                    if (i < (10 - numerobarcos) + 1) {
                                        if (btBase1[x][y] == btBase1[i][j]) {


                                            image = new ImageIcon(getClass().getResource("/resources/submarin.png"));


                                            if (botonesEnemigosColocados.contains(btBase1[i][j])) {

                                            } else {
                                                botonesEnemigosColocados.add(btBase1[i][j]);
                                                direcciones.add(btBase1[i][j].toString());
                                                btBase1[x][y].setIcon(image);
                                                s = i;
                                                s2 = j;
                                                posicionx[x1]=s;
                                                posiciony[x1]=s2;
                                                x1++;
                                            }
                                            i++;
                                            if (botonesEnemigosColocados.contains(btBase1[(i + h) - 1][j])) {

                                            } else {
                                                botonesEnemigosColocados.add(btBase1[(i + h) - 1][j]);
                                                direcciones.add(btBase1[(i + h) - 1][j].toString());
                                                btBase1[(i + h) - 1][j].setIcon(image);
                                                s = (i + h) - 1;
                                                s2 = j;
                                                posicionx[x1]=s;
                                                posiciony[x1]=s2;
                                                x1++;
                                            }
                                        }
                                    } else {
                                        if (btBase1[x][y] == btBase1[i][j]) {
                                            image = new ImageIcon(getClass().getResource("/resources/submarin.png"));
                                            btBase1[(i)][j].setIcon(image);
                                            botonesEnemigosColocados.add(btBase1[i][j]);
                                            s =i;
                                            s2 = j;
                                            posicionx[x1]=s;
                                            posiciony[x1]=s2;
                                            x1++;

                                            btBase1[i - h][j].setIcon(image);
                                            botonesEnemigosColocados.add(btBase1[i - h][j]);
                                            s = i - h ;
                                            s2 = j;
                                            posicionx[x1]=s;
                                            posiciony[x1]=s2;
                                            x1++;
                                        }
                                    }
                                }

                            }
                        }
                    }
                } else {
                    if (destructores == true) {

                        for (int h = 0; h < numerobarcos; h++) {
                            for (int i = 0; i < btBase1.length; i++) {
                                for (int j = 0; j < btBase1[i].length; j++) {
                                    if (j < (10 - numerobarcos) + 1) {
                                        if (btBase1[x][y] == btBase1[i][j]) {

                                            image = new ImageIcon(getClass().getResource("/resources/destructo.png"));

                                            if (botonesEnemigosColocados.contains(btBase1[i][j])) {

                                            } else {

                                                botonesEnemigosColocados.add(btBase1[i][j]);
                                                direcciones.add(btBase1[i][j].toString());
                                                btBase1[i][j].setIcon(image);

                                                s =i;
                                                s2 = j;
                                                posicionx[x1]=s;
                                                posiciony[x1]=s2;
                                                x1++;
                                            }


                                            j++;
                                            if (botonesEnemigosColocados.contains(btBase1[i][(h + j) - 1])) {

                                            } else {
                                                botonesEnemigosColocados.add(btBase1[i][(h + j) - 1]);
                                                direcciones.add(btBase1[i][(h + j) - 1].toString());
                                                btBase1[i][(h + j) - 1].setIcon(image);
                                                s =i;
                                                s2 = (h + j) - 1;
                                                posicionx[x1]=s;
                                                posiciony[x1]=s2;
                                                x1++;
                                            }
                                        }

                                    } else {

                                        if (i < (10 - numerobarcos) + 1) {

                                            if (btBase1[x][y] == btBase1[i][j]) {

                                                image = new ImageIcon(getClass().getResource("/resources/destructo.png"));
                                                if (botonesEnemigosColocados.contains(btBase1[i][j])) {

                                                } else {
                                                    botonesEnemigosColocados.add(btBase1[i][j]);
                                                    direcciones.add(btBase1[i][j].toString());
                                                    btBase1[x][y].setIcon(image);
                                                    s =i;
                                                    s2 = j;
                                                    posicionx[x1]=s;
                                                    posiciony[x1]=s2;
                                                    x1++;
                                                }

                                                i++;
                                                if (botonesEnemigosColocados.contains(btBase1[(i + h) - 1][j])) {

                                                } else {
                                                    botonesEnemigosColocados.add(btBase1[(i + h) - 1][j]);
                                                    direcciones.add(btBase1[(i + h) - 1][j].toString());
                                                    btBase1[(i + h) - 1][j].setIcon(image);

                                                    s =(i + h) - 1;
                                                    s2 = j;
                                                    posicionx[x1]=s;
                                                    posiciony[x1]=s2;
                                                    x1++;
                                                }
                                            }
                                        } else {
                                            if (btBase1[x][y] == btBase1[i][j]) {
                                               /* image = new ImageIcon(getClass().getResource("/resources/destructo.png"));
                                                btBase1[(i)][j].setIcon(image);
                                                botonesEnemigosColocados.add(btBase1[i][j]);
                                                s =i;
                                                s2 = j;
                                                posicionx[x1]=s;
                                                posiciony[x1]=s2;
                                                x1++;
                                                btBase1[i - h][j].setIcon(image);
                                                botonesEnemigosColocados.add(btBase1[i - h][j]);
                                                s =i - h;
                                                s2 = j;
                                                posicionx[x1]=s;
                                                posiciony[x1]=s2;
                                                x1++;*/
                                            }
                                        }
                                    }

                                }
                            }
                        }
                    } else {
                        if (fragatas == true) {

                            for (int h = 0; h < numerobarcos; h++) {
                                for (int i = 0; i < btBase1.length; i++) {
                                    for (int j = 0; j < btBase1[i].length; j++) {
                                        if (j < (10 - numerobarcos) + 1) {
                                            if (btBase1[x][y] == btBase1[i][j]) {

                                                image = new ImageIcon(getClass().getResource("/resources/fragat.png"));

                                                if (botonesEnemigosColocados.contains(btBase1[i][j])) {

                                                } else {
                                                    botonesEnemigosColocados.add(btBase1[i][j]);
                                                    direcciones.add(btBase1[i][j].toString());
                                                    btBase1[i][j].setIcon(image);
                                                    s =i;
                                                    s2 = j;
                                                    posicionx[x1]=s;
                                                    posiciony[x1]=s2;
                                                    x1++;
                                                }
                                                j++;
                                                if (botonesEnemigosColocados.contains(btBase1[i][(h + j) - 1])) {

                                                } else {
                                                    botonesEnemigosColocados.add(btBase1[i][(h + j) - 1]);
                                                    direcciones.add(btBase1[i][(h + j) - 1].toString());
                                                    btBase1[i][(h + j) - 1].setIcon(image);
                                                    s =i;
                                                    s2 = (h + j) - 1;
                                                    posicionx[x1]=s;
                                                    posiciony[x1]=s2;
                                                    x1++;
                                                }
                                            }
                                        } else {
                                            if (i < (10 - numerobarcos) + 1) {
                                                if (btBase1[x][y] == btBase1[i][j]) {
                                                    image = new ImageIcon(getClass().getResource("/resources/fragat.png"));
                                                    if (botonesEnemigosColocados.contains(btBase1[i][j])) {

                                                    } else {
                                                        botonesEnemigosColocados.add(btBase1[i][j]);
                                                        direcciones.add(btBase1[i][j].toString());
                                                        btBase1[x][y].setIcon(image);
                                                        s =i;
                                                        s2 = j;
                                                        posicionx[x1]=s;
                                                        posiciony[x1]=s2;
                                                        x1++;
                                                    }
                                                    i++;
                                                    if (botonesEnemigosColocados.contains(btBase1[(i + h) - 1][j])) {

                                                    } else {
                                                        botonesEnemigosColocados.add(btBase1[(i + h) - 1][j]);
                                                        direcciones.add(btBase1[(i + h) - 1][j].toString());
                                                        btBase1[(i + h) - 1][j].setIcon(image);
                                                        s =(i + h) - 1;
                                                        s2 = j;
                                                        posicionx[x1]=s;
                                                        posiciony[x1]=s2;
                                                        x1++;
                                                    }
                                                }
                                            } else {
                                                if (btBase1[x][y] == btBase1[i][j]) {
                                                   /* image = new ImageIcon(getClass().getResource("/resources/fragat.png"));
                                                    btBase1[(i)][j].setIcon(image);
                                                    botonesEnemigosColocados.add(btBase1[i][j]);
                                                    s =i;
                                                    s2 = j;
                                                    posicionx[x1]=s;
                                                    posiciony[x1]=s2;
                                                    x1++;
                                                    btBase1[i - h][j].setIcon(image);
                                                    botonesEnemigosColocados.add(btBase1[i - h][j]);
                                                    s =i - h;
                                                    s2 = j;
                                                    posicionx[x1]=s;
                                                    posiciony[x1]=s2;
                                                    x1++;*/
                                                }
                                            }
                                        }

                                    }
                                }
                            }
                        }
                    }
                }
            }

            elegirBarcos(numerobarcos);
            setInfo.getBarcos(botonesEnemigosColocados);

        }
    }

    private void initGUI() {
        this.getContentPane().setLayout(new GridBagLayout());
        Container contentPane = this.getContentPane();
        //contentPane.setLayout(new ());
        escucha=new Escucha();
        MiBase =new JPanel();
        MiBase.setLayout(null);
        numerobarcos=4;
        portaaviones=true;
        MiBase.setPreferredSize(new Dimension(370,350));
        MiBase.setBackground(Color.DARK_GRAY);
        baseEnemiga =new JPanel();
        baseEnemiga.setLayout(null);
        baseEnemiga.setPreferredSize(new Dimension(370,350));
        baseEnemiga.setBackground(Color.darkGray);


        salir = new JButton( " X ");
        constraints.gridx=1;
        constraints.gridy=1;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.LINE_END;
        salir.addActionListener(escucha);
        this.add(salir,constraints);

        this.add(salir,constraints);

        headerProject = new Header("Tablero enemigo", Color.BLUE);
        constraints.gridx=0;
        constraints.gridy=0;
        constraints.gridwidth=4;
        constraints.fill=GridBagConstraints.HORIZONTAL;
        this.add(headerProject,constraints);


        panelMiTablero=new JPanel();
        panelMiTablero.setPreferredSize(new Dimension(440, 400));
        panelMiTablero.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0, 0, 0),3,true),"BASE",TitledBorder.CENTER,TitledBorder.TOP,new Font("Tahoma", 1, 15)));
        constraints.gridx=1;
        constraints.gridy=2;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.CENTER;

        panelMiTablero.add(MiBase);
        add(panelMiTablero,constraints);


    }

    private class Escucha implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==salir){
                setVisible(false);
            }
        }
    }
}