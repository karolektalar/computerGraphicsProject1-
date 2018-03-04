package gui;



import javax.swing.*;
import java.io.IOException;


/**
 * Created by karol on 26.02.2018.
 */
public class FiltersGui {
    public static void main(String[] args) throws IOException {
        FiltersForm ff = new FiltersForm();
        ff.setVisible(true);
        ff.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
