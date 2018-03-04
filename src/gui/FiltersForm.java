package gui;

import filters.BrightnessCorrection;
import filters.ContrastEnhancment;
import filters.ConvolutionFilters;
import filters.Inversion;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

/**
 * Created by karol on 04.03.2018.
 */
public class FiltersForm extends JFrame {

    private String pathToImg = "C:\\Users\\karol\\Desktop\\Computer Graphics\\JavaProject1\\src\\gui\\lenna.jpg";

    private JButton inversionButton;
    private JButton brightnessCorrectionButton;
    private JButton contrastEnhancmentButton;
    private JLabel imageLabel;
    private JLabel processedImageLabel = new JLabel();
    private JButton originalImageButton;
    private JButton blurButton;
    private JButton edgeDetectionButton;
    private JButton embossButton;
    private JButton gaussianBlurButton;
    private JButton sharpenButton;
    private JTable kernel;
    private JTextField kernelWidth;
    private JTextField kernelHeight;
    private JButton createKernelButton;
    private JTextField offset;
    private JTextField coreHeightTextField;
    private JTextField divisor;
    private JTextField coreWidthTextField;
    private JButton applyYourFilterButton;
    private JLabel kernelHeightLabel;
    private JLabel kernelWidthLabel;
    private JLabel offsetLabel = new JLabel();
    private JLabel divisorLabel = new JLabel();


    BufferedImage img = ImageIO.read(new File(pathToImg));
    BufferedImage oldImg = ImageIO.read(new File(pathToImg));
    BufferedImage processedImg = ImageIO.read(new File(pathToImg));
    DefaultTableModel model = new DefaultTableModel();

    public FiltersForm() throws IOException {
        ImageIcon icon = new ImageIcon(img);
        ImageIcon processedIcon = new ImageIcon(processedImg);
        imageLabel.setSize(img.getWidth(), img.getHeight());
        imageLabel.setIcon(icon);
        processedImageLabel.setSize(img.getWidth(),img.getHeight());
        processedImageLabel.setIcon(processedIcon);
        this.add(imageLabel);
        this.add(processedImageLabel);
        this.add(originalImageButton);
        this.add(inversionButton);
        this.add(brightnessCorrectionButton);
        this.add(contrastEnhancmentButton);
        this.add(edgeDetectionButton);
        this.add(embossButton);
        this.add(gaussianBlurButton);
        this.add(blurButton);
        this.add(sharpenButton);

//        kernelWidth.setPreferredSize(new Dimension(40,24));
//        kernelHeight.setPreferredSize(new Dimension(40,24));
        kernelHeight.setText(" Set height ");
        kernelWidth.setText("Set width ");
        this.add(kernelHeightLabel);
        this.add(kernelHeight);
        this.add(kernelWidthLabel);
        this.add(kernelWidth);
        this.add(createKernelButton);

        kernel.setModel(model);
        kernel.setRowHeight(50);

        this.add(kernel);

        offsetLabel.setText("Offset");
        offsetLabel.setBorder(new EmptyBorder(10, 100, 10, 10));
        divisor.setText("divisor");
        this.add(offsetLabel);
        this.add(offset);
        this.add(divisorLabel);
        this.add(divisor);
        this.add(coreHeightTextField);
        this.add(coreWidthTextField);
        this.add(applyYourFilterButton);

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLayout(new FlowLayout());
        contrastEnhancmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                img = ContrastEnhancment.contrast(img, 2);
                ImageIcon icon = new ImageIcon(img);
                imageLabel.setIcon(icon);


            }
        });

        originalImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    img = ImageIO.read(new File(pathToImg));
                    oldImg = ImageIO.read(new File(pathToImg));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                ImageIcon icon = new ImageIcon(img);
                imageLabel.setIcon(icon);
            }
        });

        brightnessCorrectionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                img = BrightnessCorrection.correctBrightness(img, 50);
                ImageIcon icon = new ImageIcon(img);
                imageLabel.setIcon(icon);
            }
        });
        inversionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                img = Inversion.inversion(img);
                ImageIcon icon = new ImageIcon(img);
                imageLabel.setIcon(icon);
            }
        });

        blurButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                img = ConvolutionFilters.blur(img, oldImg);
                ImageIcon icon = new ImageIcon(img);
                imageLabel.setIcon(icon);
                setKernel(3, 3);
                model.setValueAt("1", 0, 0);
                model.setValueAt("1", 1, 0);
                model.setValueAt("1", 2, 0);
                model.setValueAt("1", 0, 1);
                model.setValueAt("1", 1, 1);
                model.setValueAt("1", 2, 1);
                model.setValueAt("1", 0, 2);
                model.setValueAt("1", 1, 2);
                model.setValueAt("1", 2, 2);
                offset.setText("0");
                divisor.setText("9");
                coreHeightTextField.setText("1");
                coreWidthTextField.setText("1");

            }
        });
        sharpenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConvolutionFilters.sharpenFilter(img, oldImg);
                ImageIcon icon = new ImageIcon(oldImg);
                imageLabel.setIcon(icon);
                setKernel(3, 3);
                model.setValueAt("0", 0, 0);
                model.setValueAt("-1", 1, 0);
                model.setValueAt("0", 2, 0);
                model.setValueAt("-1", 0, 1);
                model.setValueAt("5", 1, 1);
                model.setValueAt("-1", 2, 1);
                model.setValueAt("0", 0, 2);
                model.setValueAt("-1", 1, 2);
                model.setValueAt("0", 2, 2);
                offset.setText("0");
                divisor.setText("1");
                coreHeightTextField.setText("1");
                coreWidthTextField.setText("1");
            }
        });
        gaussianBlurButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                img = ConvolutionFilters.gaussianBlur(img, oldImg);
                ImageIcon icon = new ImageIcon(img);
                imageLabel.setIcon(icon);
                setKernel(3, 3);
                model.setValueAt("0", 0, 0);
                model.setValueAt("1", 1, 0);
                model.setValueAt("0", 2, 0);
                model.setValueAt("1", 0, 1);
                model.setValueAt("4", 1, 1);
                model.setValueAt("1", 2, 1);
                model.setValueAt("0", 0, 2);
                model.setValueAt("1", 1, 2);
                model.setValueAt("0", 2, 2);
                offset.setText("0");
                divisor.setText("8");
                coreHeightTextField.setText("1");
                coreWidthTextField.setText("1");
            }
        });
        edgeDetectionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConvolutionFilters.edgeDetection(img, oldImg);
                ImageIcon icon = new ImageIcon(oldImg);
                imageLabel.setIcon(icon);
                setKernel(3, 3);
                model.setValueAt("0", 0, 0);
                model.setValueAt("-1", 1, 0);
                model.setValueAt("0", 2, 0);
                model.setValueAt("-1", 0, 1);
                model.setValueAt("4", 1, 1);
                model.setValueAt("-1", 2, 1);
                model.setValueAt("0", 0, 2);
                model.setValueAt("-1", 1, 2);
                model.setValueAt("0", 2, 2);
                offset.setText("0");
                divisor.setText("1");
                coreHeightTextField.setText("1");
                coreWidthTextField.setText("1");
            }
        });
        embossButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConvolutionFilters.embos(img, oldImg);
                ImageIcon icon = new ImageIcon(oldImg);
                imageLabel.setIcon(icon);
                setKernel(3, 3);
                model.setValueAt("-1", 0, 0);
                model.setValueAt("-1", 1, 0);
                model.setValueAt("-1", 2, 0);
                model.setValueAt("0", 0, 1);
                model.setValueAt("1", 1, 1);
                model.setValueAt("0", 2, 1);
                model.setValueAt("1", 0, 2);
                model.setValueAt("1", 1, 2);
                model.setValueAt("1", 2, 2);
                offset.setText("0");
                divisor.setText("1");
                coreHeightTextField.setText("1");
                coreWidthTextField.setText("1");
            }
        });


        createKernelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setKernel(Integer.parseInt(kernelHeight.getText()), Integer.parseInt(kernelWidth.getText()));
            }
        });
        kernelWidth.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                kernelWidth.setText("");
            }
        });
        kernelHeight.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                kernelHeight.setText("");
            }
        });
        divisor.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                divisor.setText("");
            }
        });
        offset.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                offset.setText("");
            }
        });

        coreHeightTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                coreHeightTextField.setText("");
            }
        });
        coreWidthTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                coreWidthTextField.setText("");
            }
        });
        applyYourFilterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double[][] matrix = new double[model.getColumnCount()][model.getRowCount()];
                for (int i = 0; i < model.getColumnCount(); i++) {
                    for (int j = 0; j < model.getRowCount(); j++) {
                        matrix[i][j] = Double.parseDouble(model.getValueAt(i, j).toString());
                    }
                }
                img = ConvolutionFilters.applyFilterWithMatrix(img, oldImg, matrix, Integer.parseInt(offset.getText()),
                        Integer.parseInt(divisor.getText()), Integer.parseInt(coreWidthTextField.getText()), Integer.parseInt(coreHeightTextField.getText()));
                ImageIcon icon = new ImageIcon(img);
                imageLabel.setIcon(icon);
            }
        });
    }

    public void setKernel(int width, int height) {
        model = new DefaultTableModel();
        kernel.setModel(model);
        kernel.setRowHeight(50);
        for (int i = 0; i < width; i++)
            model.addColumn("column" + i);

        for (int i = 0; i < height; i++) {
            Vector row = new Vector();
            model.addRow(row);
        }
    }
}
