import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Esta clase contiene la clase main y es la encargada de crear la vista del proyecto
 * @author Marlon Anacona marlon.anacona@correounivalle.edu.co
 * @author Luis Belalcazar luis.Belalcazar@correounivalle.edu.co
 * @version v.1.0.0 date:21/03/2022
 */
public class GUI extends JFrame {
    GridBagConstraints constraints = new GridBagConstraints();
    private Header headerProject;
    private JPanel panelBarcos,panelMiTablero,panelTableroPc,panelTableroPc2, MiBase, baseEnemiga;
    private Escucha escucha;
    private boolean iniciarJuego;
    public static ArrayList<JButton> botonesEnemigos=new ArrayList<JButton>();


    public static boolean portaaviones,submarino,destructores,fragatas;
    public static int porta=1,subma=2,destruc=3,fragata=4;
    private  ImageIcon image=  new ImageIcon(getClass().getResource("/resources/porta.png"));
    private Mouse mouse=new Mouse();
    private JButton ayuda,salir,tableroPc,quitarTablero;
    private JButton [][] btBase;
    private JButton [][] btEnemy;
    private tableroRival mitablero= new tableroRival();
    private ArrayList<JButton> botonesUsados=new ArrayList<JButton>();
    private boolean usado;

    public static  int vida=420;
    public static int numerobarcos;



    /**
     * Constructor of GUI class
     */
    public GUI(){
        initGUI();
        numerobarcos=3;
        portaaviones=true;
        //Default JFrame configuration
        this.setTitle("Batalla Naval");
        //this.setPreferredSize(new Dimension(1095,528));
        this.setBackground(new Color(255,255,255));
        this.setUndecorated(true);
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * This method is used to set up the default JComponent Configuration,
     * create Listener and control Objects used for the GUI class
     */

    public void llenarBase(){
        int x=10,y=10;
        btBase=new JButton[10][10];
        for (int j=0;j<btBase.length;j++){
            for (int i=0;i<btBase[j].length;i++){
                btBase[i][j]=new JButton();
                btBase[i][j].setBackground(Color.ORANGE);
                btBase[i][j].setBounds(x,y,35,33);
                btBase[i][j].addMouseListener(mouse);
                MiBase.add(btBase[i][j],BorderLayout.CENTER);
                x+=35;
            }
            x=10;
            y+=33;
        }
    }

    /**
     * Este metodo habilita poder ver la base enemiga
     */

public void habilitarBaseEnemiga(){
    for (int j= 0; j < btBase.length; j++) {
        for (int i = 0; i < btBase[j].length; i++) {

            btEnemy[i][j].setEnabled(true);

        }}
}
    /**
     *Este metodo sirve para crear el tablero enemigo
     */

    public void llenarEnemy() {
        int x = 10, y = 10;
        btEnemy = new JButton[10][10];
        for (int j= 0; j < btBase.length; j++) {
            for (int i = 0; i < btBase[j].length; i++) {
                btEnemy[i][j] = new JButton();
                btEnemy[i][j].setBackground(Color.ORANGE);
                btEnemy[i][j].setBounds(x, y, 35, 33);
                btEnemy[i][j].addActionListener(escucha);
                btEnemy[i][j].setEnabled(false);
                baseEnemiga.add(btEnemy[i][j], BorderLayout.CENTER);
                x += 35;
            }
            x = 10;
            y += 33;
        }
    }

    /**
     * Este metodo sirve para colocar los diferentes barcos en el tablero del jugador y del enemigo
     */


    public void colocarBarcos() {


        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                for(int y=0;y<10;y++){
                    if(mitablero.getPosicionX()[i]==j && mitablero.getPosiciony()[i]==y){

                        botonesEnemigos.add( btEnemy[j][y]);
                        btEnemy[j][y].addActionListener(escucha);
                        break;
                    }else{
                        btEnemy[j][y].addActionListener(escucha);
                    }
                }
            }

    }

    }
    /**
     * Este metodo sirve para elegir que tipo de barco es el que se coloca y si el jugador ya coloco todos sus barcos correspondientes
     */

    public int elegirBarcos(int num){
        if(portaaviones==true){

        this.numerobarcos=num-1;

        portaaviones=false;
        submarino=true;
            image=  new ImageIcon(getClass().getResource("/resources/submarin.png"));
        }else{
        if(submarino==true&&subma!=0){
        subma--;

        if(subma==0){
            this.numerobarcos=num-1;
            submarino=false;
            destructores=true;
            image=  new ImageIcon(getClass().getResource("/resources/destructo.png"));
        }

    }else{
        if(destructores==true&&destruc!=0){
            destruc--;
            if(destruc==0){
            this.numerobarcos=num-1;
            destructores=false;
            fragatas=true;
                image=  new ImageIcon(getClass().getResource("/resources/fragat.png"));
            }
        }else{
            if(fragatas==true&&fragata!=0){
                fragata--;
                if(fragata==0){
                this.numerobarcos=num-1;
                fragatas=false;
                JOptionPane.showMessageDialog(null, "Ya puede empezar a jugar");
                colocarBarcos();
                habilitarBaseEnemiga();

            }
            }
        }
    }
}
        return  numerobarcos;
    }

    /**
     * Este metodo coloca los barcos de manera vertical
     */


    public boolean colocarbarcoVertical(Object botonP){
        boolean tieneBarco = false;

        for (int i = 0; i < btBase.length; i++) {
            for (int j = 0; j < btBase[i].length; j++) {
                if (j < 10 - numerobarcos) {
                    if (botonP == btBase[i][j]) {
                        for (int h = 0; h < numerobarcos; h++) {
                            j++;
                            if (botonesUsados.contains(botonP) || botonesUsados.contains(btBase[i][j])) {
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
     * Este metodo coloca los barcos de manera horizontal
     */


    public boolean colocarbarcoHorizontal(Object botonP) {
        boolean tieneBarco = false;

        for (int i = 0; i < btBase.length; i++) {
            for (int j = 0; j < btBase[i].length; j++) {
                if (i < 10 - numerobarcos && j >= 10 - numerobarcos) {
                    if (botonP == btBase[i][j]) {
                        for (int h = 0; h < numerobarcos; h++) {
                            i++;
                            if (botonesUsados.contains(botonP) || botonesUsados.contains(btBase[i][j])) {
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
     * Este metodo cambia el color si un barco fue impactado
     */


    public void cambiarcolor(JButton red){

        ImageIcon f=new ImageIcon(getClass().getResource("/resources/bomba.jpeg"));
        red.setIcon(f);
        red.setEnabled(false);

        if(vida==10){
            JOptionPane.showMessageDialog(null, "HAS GANADO");
        }
    }
    /**
     * Este metodo cambia la imagen de fondo del boton presionado si este fue presionado en el agua
     */

    public void colocarFallo(JButton red){

        ImageIcon f=new ImageIcon(getClass().getResource("/resources/fallo.jpeg"));
        red.setIcon(f);
    }
    /**
     * This method is used to set up the default JComponent Configuration,
     * create Listener and control Objects used for the GUI class
     */
    private void initGUI() {
        this.getContentPane().setLayout(new GridBagLayout());
        Container contentPane = this.getContentPane();
        //contentPane.setLayout(new ());
        mitablero.setVisible(false);
        MiBase =new JPanel();
        MiBase.setLayout(null);
iniciarJuego=false;
        MiBase.setPreferredSize(new Dimension(370,350));
        MiBase.setBackground(Color.DARK_GRAY);
        baseEnemiga =new JPanel();
        baseEnemiga.setLayout(null);
        baseEnemiga.setPreferredSize(new Dimension(370,350));
        baseEnemiga.setBackground(Color.darkGray);
        escucha =new Escucha();

        headerProject = new Header("Batalla Naval", Color.BLACK);
        constraints.gridx=0;
        constraints.gridy=0;
        constraints.gridwidth=4;
        constraints.fill=GridBagConstraints.HORIZONTAL;
        this.add(headerProject,constraints);

        ayuda = new JButton(" ? ");
        ayuda.addActionListener(escucha);
        constraints.gridx=0;
        constraints.gridy=1;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.LINE_START;
        this.add(ayuda,constraints);

        salir = new JButton(" X ");
        salir.addActionListener(escucha);
        constraints.gridx=2;
        constraints.gridy=1;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.LINE_END;
        this.add(salir,constraints);



        tableroPc = new JButton("Mostrar Tablero");
        tableroPc.addActionListener(escucha);
        constraints.gridx=2;
        constraints.gridy=3;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.FIRST_LINE_START;
        this.add(tableroPc,constraints);

        quitarTablero = new JButton("Ocultar Tablero");
        quitarTablero.addActionListener(escucha);
        constraints.gridx=2;
        constraints.gridy=3;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.FIRST_LINE_END;
        this.add(quitarTablero,constraints);

        panelMiTablero=new JPanel();
        panelMiTablero.setPreferredSize(new Dimension(440, 400));
        panelMiTablero.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0, 0, 0),3,true),"BASE",TitledBorder.CENTER,TitledBorder.TOP,new Font("Tahoma", 1, 15)));
        constraints.gridx=1;
        constraints.gridy=2;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.CENTER;
       llenarBase();
        panelMiTablero.add(MiBase);
        add(panelMiTablero,constraints);

        panelTableroPc=new JPanel();
        panelTableroPc.setPreferredSize(new Dimension(440, 400));
        panelTableroPc.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0, 0, 0),3,true),"MAPA",TitledBorder.CENTER,TitledBorder.TOP,new Font("Tahoma", 1, 15)));
        constraints.gridx=2;
        constraints.gridy=2;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.CENTER;
        llenarEnemy();
        panelTableroPc.add(baseEnemiga);
        add(panelTableroPc,constraints);

    }

    /**
     * Main process of the Java program
     * @param args Object used in order to send input data from command line when
     *             the program is execute by console.
     */
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            GUI miProjectGUI = new GUI();
        });
    }

    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUI class
     */
    private class Escucha implements ActionListener {
public int sf=0;
        @Override
        public void actionPerformed(ActionEvent e) {
            barcosRecibidos setInfo= new barcosRecibidos();
            if (e.getSource()==salir){
                System.exit(0);
            }else{

                if(e.getSource()==tableroPc){

                    setInfo.setcolocados(true);
                    mitablero.setVisible(true);
                  //  colocarBarcos();

                }else{
                    if((botonesEnemigos.contains((JButton) e.getSource()))){

                        cambiarcolor((JButton)e.getSource());

                    }else{
                        colocarFallo((JButton) e.getSource());
                    }
                }

            }






        }
    }

    private  class Mouse implements MouseListener {

        public void mouseMove(MouseEvent e) {

        }

        @Override
        public void mouseClicked(MouseEvent e) {

            boolean horizontal=colocarbarcoHorizontal(e.getSource());
            boolean vertical=colocarbarcoVertical(e.getSource());

            if(horizontal==true||vertical==true){
                JOptionPane.showMessageDialog(null,"No puedes colocar el barco aqui");
            }else{

                if (numerobarcos == 0) {
                    for (int h = 0; h < 1; h++) {

                        for (int i = 0; i < btBase.length; i++) {
                            for (int j = 0; j < btBase[i].length; j++) {
                                if (!(botonesUsados.contains(btBase[i][j]))) {
                                    if (j < 10) {
                                        if (e.getSource() == btBase[i][j]) {

                                            if(botonesUsados.contains(btBase[i][j])){

                                            }else{
                                                botonesUsados.add(btBase[i][j]);
                                                btBase[i][j].setIcon(image);
                                            }
                                        }
                                    } else {

                                    }
                                } else {
                                }
                            }
                        }
                    }
                    elegirBarcos(numerobarcos);
                }else{
                for (int h = 0; h < numerobarcos; h++) {
                    for (int i = 0; i < btBase.length; i++) {
                        for (int j = 0; j < btBase[i].length; j++) {
                            if (j < 10 - numerobarcos) {
                                if (e.getSource() == btBase[i][j]) {

                                    if (botonesUsados.contains(btBase[i][j])) {

                                    }else{
                                        btBase[i][j].setIcon(image);
                                        botonesUsados.add(btBase[i][j]);
                                    }
                                    j++;
                                    if (botonesUsados.contains(btBase[i][h + j])) {

                                    }else {
                                        btBase[i][h + j].setIcon(image);
                                        botonesUsados.add(btBase[i][h + j]);
                                    }
                                }
                            }else{
                                if (i <(10 - numerobarcos) ) {
                                    if (e.getSource() == btBase[i][j]) {

                                        if (botonesUsados.contains(btBase[i][j])) {

                                        }else{
                                            btBase[i][j].setIcon(image);
                                            botonesUsados.add(btBase[i][j]);
                                        }
                                        i++;
                                        if(botonesUsados.contains(btBase[(i + h)][j])) {



                                        }else{
                                            btBase[(i + h)][j].setIcon(image);
                                            botonesUsados.add(btBase[(i + h) ][j]);
                                        }
                                    }
                                } else {
                                    if (e.getSource() == btBase[i][j]) {
                                        if (botonesUsados.contains(btBase[i][j])) {

                                        }else{
                                            btBase[i][j].setIcon(image);
                                            botonesUsados.add(btBase[i][j]);
                                        }

                                        if(botonesUsados.contains(btBase[i - h][j])) {


                                        }else{
                                            btBase[i - h][j].setIcon(image);
                                            botonesUsados.add(btBase[i - h][j]);
                                        }
                                    }
                                }
                            }

                        }
                    }
                }
                elegirBarcos(numerobarcos);
            }
            }
        }


        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

            if (numerobarcos == 0) {
                for (int h = 0; h < 1; h++) {

                    for (int i = 0; i < btBase.length; i++) {
                        for (int j = 0; j < btBase[i].length; j++) {
                            if (!(botonesUsados.contains(btBase[i][j]))) {
                                if (j < 10) {
                                    if (e.getSource() == btBase[i][j]) {
                                        btBase[i][j].setIcon(image);
                                    }
                                } else {

                                }
                            } else {
                            }
                        }
                    }
                }
            } else {
                for (int h = 0; h < numerobarcos; h++) {

                    for (int i = 0; i < btBase.length; i++) {
                        for (int j = 0; j < btBase[i].length; j++) {
                            if (!(botonesUsados.contains(btBase[i][j]))) {
                                if (j < 10 - numerobarcos) {
                                    if (e.getSource() == btBase[i][j]) {

                                        btBase[i][j].setIcon(image);
                                        j++;
                                        btBase[i][h + j].setIcon(image);
                                    }
                                } else {
                                    if (i < (10 - numerobarcos)) {
                                        if (e.getSource() == btBase[i][j]) {
                                            btBase[i][j].setIcon(image);
                                            i++;
                                            btBase[(i + h) ][j].setIcon(image);
                                        }
                                    }
                                }
                            } else {
                                if (e.getSource() == btBase[i][j]) {

                                }

                            }
                        }
                    }
                }
            }
        }


        @Override
        public void mouseExited(MouseEvent e) {

            if (numerobarcos == 0) {
                for (int h = 0; h < 1; h++) {
                    for (int i = 0; i < btBase.length; i++) {
                        for (int j = 0; j < btBase[i].length; j++) {
                            if (j < 10 - numerobarcos) {
                                if (e.getSource() == btBase[i][j]) {
                                    if (btBase[i][j].isEnabled() == true) {
                                        btBase[i][j].setIcon(null);
                                    }

                                }
                            } else {

                            }

                        }
                    }
                }

            } else {

                for (int h = 0; h < numerobarcos; h++) {
                    for (int i = 0; i < btBase.length; i++) {
                        for (int j = 0; j < btBase[i].length; j++) {
                            if (j < 10 - numerobarcos) {
                                if (e.getSource() == btBase[i][j]) {
                                    if (btBase[i][j].isEnabled() == true) {
                                        btBase[i][j].setIcon(null);
                                        j++;
                                        if (btBase[i][h + j].isEnabled() == true) {
                                            btBase[i][h + j].setIcon(null);
                                        }
                                    }

                                }
                            } else {

                                if (i < (10 - numerobarcos)) {
                                    if (e.getSource() == btBase[i][j]) {


                                        btBase[i][j].setIcon(null);
                                        i++;

                                        btBase[(i + h) ][j].setIcon(null);
                                    }
                                } else {
                                    if (e.getSource() == btBase[i][j]) {

                                        btBase[i][j].setIcon(null);


                                        btBase[i - h][j].setIcon(null);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            for (JButton bt :
                    botonesUsados) {
                image = new ImageIcon(getClass().getResource("/resources/porta.png"));
                bt.setIcon(image);
            }

        }
    }
}

