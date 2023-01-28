package view;

import java.io.File;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.Features;
import model.ImageManipulatorViewModel;

/**
 * Represents a graphical view of the Image Manipulation Software. Makes use of the Java Swing
 * library.
 */
public class ImageManipulatorSwingView extends JFrame implements ImageManipulatorGUIView {

  private JLabel messages;
  private JButton load;

  private JButton save;

  private JButton vertFlip;

  private JButton horFlip;


  private JTextField input;
  private JButton brightenButton;

  private JTextField input2;

  private JButton greyscaleButton;

  private JButton blurButton;

  private JButton sharpenButton;

  private JButton sepia;

  private ImagePanel imgPanel;

  private HistogramPanel histogramPanel;

  private JScrollPane scroll;
  private JScrollPane scroll2;

  private JPanel mainPanel;

  private JScrollPane mainScrollPane;

  /**
   * Constructs an {@code ImageManipulatorSwingView} object. Takes in a view model in order to
   * get information about the state of the model.
   *
   * @param model the view model to use.
   */
  public ImageManipulatorSwingView(ImageManipulatorViewModel model) {
    super("Image Manipulator Program");


    this.setSize(500, 750);

    this.setLocation(0, 0);

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//    this.setLayout(new FlowLayout());

    mainPanel = new JPanel();
    mainPanel.setLayout(new FlowLayout());
    mainPanel.setPreferredSize(new Dimension(1000, 700));
    mainScrollPane = new JScrollPane(mainPanel);
    this.add(mainScrollPane);

    JPanel loadSave = new JPanel();
    loadSave.setLayout(new BoxLayout(loadSave, BoxLayout.PAGE_AXIS));
    this.mainPanel.add(loadSave);

    this.load = new JButton("Load");
    this.load.setActionCommand("Loads");
    loadSave.add(this.load, BorderLayout.AFTER_LAST_LINE);

    this.save = new JButton("Save");
    this.save.setActionCommand("Saves");
    loadSave.add(this.save);


    JPanel flip = new JPanel();
    flip.setLayout(new BoxLayout(flip, BoxLayout.PAGE_AXIS));
    this.mainPanel.add(flip);

    this.vertFlip = new JButton("Vertical Flip");
    this.vertFlip.setActionCommand("Vertically Flips");
    flip.add(this.vertFlip);

    this.horFlip = new JButton("Horizontal Flip");
    this.horFlip.setActionCommand("Horizontally Flips");
    flip.add(this.horFlip);

    JPanel brightenGreyscale = new JPanel();
    brightenGreyscale.setLayout(new BoxLayout(brightenGreyscale, BoxLayout.PAGE_AXIS));
    this.mainPanel.add(brightenGreyscale);

    JPanel brightenPanel = new JPanel(new FlowLayout());
    brightenGreyscale.add(brightenPanel);

    this.brightenButton = new JButton("Brighten");
    this.brightenButton.setActionCommand("Brightens");
    brightenPanel.add(this.brightenButton);

    this.input = new JTextField(10);
    this.input.setActionCommand("input");
    brightenPanel.add(this.input);

    JPanel greyscalePanel = new JPanel(new FlowLayout());
    brightenGreyscale.add(greyscalePanel);

    this.greyscaleButton = new JButton("Greyscale");
    this.greyscaleButton.setActionCommand("Greyscales");
    greyscalePanel.add(this.greyscaleButton);

    this.input2 = new JTextField(10);
    this.input2.setActionCommand("input2");
    greyscalePanel.add(this.input2);

    this.blurButton = new JButton("Blur");
    this.blurButton.setActionCommand("Blurs");
    this.mainPanel.add(this.blurButton);

    this.sharpenButton = new JButton("Sharpen");
    this.sharpenButton.setActionCommand("Sharpens");
    this.mainPanel.add(this.sharpenButton);

    this.sepia = new JButton("Sepia");
    this.sepia.setActionCommand("Sepias");
    this.mainPanel.add(this.sepia);

    JPanel imageStuffAndMessages = new JPanel();
    imageStuffAndMessages.setLayout(new BoxLayout(imageStuffAndMessages, BoxLayout.PAGE_AXIS));
    this.mainPanel.add(imageStuffAndMessages);

    JPanel imageAndHistogram = new JPanel(new FlowLayout());
    imageStuffAndMessages.add(imageAndHistogram);

    this.imgPanel = new ImagePanel(model);
    this.imgPanel.setPreferredSize(new Dimension(1920, 1080));

    this.scroll = new JScrollPane(this.imgPanel);
    this.scroll.setPreferredSize(new Dimension(512, 512));
    this.scroll.createHorizontalScrollBar();
    this.scroll.createVerticalScrollBar();
    this.scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    this.scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    imageAndHistogram.add(scroll, BorderLayout.AFTER_LAST_LINE);

    this.histogramPanel = new HistogramPanel(model);
    this.histogramPanel.setPreferredSize(new Dimension(256, 10000));

    this.scroll2 = new JScrollPane(this.histogramPanel);
    this.scroll2.setPreferredSize(new Dimension(256, 256));
    imageAndHistogram.add(scroll2);

    this.messages = new JLabel();
    imageStuffAndMessages.add(this.messages);

    this.pack();
    this.setVisible(true);

  }

  @Override
  public void addFeatures(Features features) {
    this.load.addActionListener(evt -> features.loadImage(this.loadImage()));
    this.save.addActionListener(evt -> features.saveImage(this.saveImage()));
    this.vertFlip.addActionListener(evt -> features.verticalFlip());
    this.horFlip.addActionListener(evt -> features.horizontalFlip());
    this.brightenButton.addActionListener(evt -> features.brighten(this.brighten()));
    this.greyscaleButton.addActionListener(evt -> features.greyscale(this.greyscale()));
    this.blurButton.addActionListener(evt -> features.filter("blur"));
    this.sharpenButton.addActionListener(evt -> features.filter("sharpen"));
    this.sepia.addActionListener(evt -> features.transform("sepia"));
    this.imgPanel.acceptController(features);
    this.histogramPanel.acceptController(features);

  }

  @Override
  public File loadImage() {

    JFileChooser fileChooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Types",
            "png", "jpg", "jpeg", "gif", "ppm");
    fileChooser.setFileFilter(filter);
    fileChooser.showOpenDialog(this.load);
    File f = fileChooser.getSelectedFile();
    return f;
  }

  @Override
  public File saveImage() {
    JFileChooser fileChooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Types",
            "png", "jpg", "jpeg", "gif", "ppm");
    fileChooser.setFileFilter(filter);
    fileChooser.showSaveDialog(this.save);
    File f = fileChooser.getSelectedFile();
    return f;
  }

  @Override
  public int brighten() {
    int output = 0;

    String inputText = this.input.getText();

    try {
      output += Integer.parseInt(inputText);
    } catch (NumberFormatException e) {
      this.renderMessage("Must type an integer into the box, positive to brighten, " +
              "negative to darken");
    }


    return output;
  }

  @Override
  public String greyscale() {

    String inputText = this.input2.getText();

    return inputText;
  }


  @Override
  public void renderMessage(String message) {
    this.messages.setText(message);
  }

  @Override
  public void refresh() {
    this.repaint();
  }
}
