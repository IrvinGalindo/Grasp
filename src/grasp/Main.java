
import grasp.GraspInterfaz;
import javax.swing.*;


public class Main {


    public static void main(String[] ars) {
           GraspInterfaz ig = new GraspInterfaz();
            ig.setTitle("Problema de la mochila");
            ig.setResizable(false);
            ig.setSize(700, 500);
            ig.setLocationRelativeTo(null);
            ig.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ig.setVisible(true);
    }

}
