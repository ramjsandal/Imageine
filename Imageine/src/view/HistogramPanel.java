package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

import javax.swing.*;

import controller.Features;
import model.ImageManipulatorViewModel;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class HistogramPanel extends JPanel {

  private ImageManipulatorViewModel model;

  private Features controller;
  private int maxVal;

  /**
   * Constructs an {@code Histogram} object. Takes in a view model to do so.
   *
   * @param model the model to base the histogram off of.
   * @throws IllegalArgumentException
   */
  public HistogramPanel(ImageManipulatorViewModel model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Cannot have null model");
    }
    this.model = model;
    this.maxVal = 0;
  }


  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    String imgName = this.controller.getCurrentImageName();
    int width = this.model.getBoardWidth(imgName);
    int height = this.model.getBoardHeight(imgName);

    if (width <= 0 || height <= 0) {
      // do nothing, because we do not have an image to draw
    } else {

      int[] red = this.model.getHistogram("red", imgName);
      int[] green = this.model.getHistogram("green", imgName);
      int[] blue = this.model.getHistogram("blue", imgName);
      int[] intensity = this.model.getHistogram("intensity", imgName);

      for (int i = 0; i < 256; i++) {
        this.maxVal = Math.max(red[i], Math.max(green[i], Math.max(blue[i],
                Math.max(intensity[i], this.maxVal))));
      }

      BufferedImage image = new BufferedImage(257, this.maxVal + 1, TYPE_INT_RGB);


      for (int i = 0; i < 256; i++) {

        HashMap<Integer, String> values = new HashMap<>();
        values.put(red[i], "red");
        values.put(green[i], "green");
        values.put(blue[i], "blue");
        values.put(intensity[i], "intensity");

        ArrayList<Integer> vals =
                new ArrayList<>(Arrays.asList(red[i], green[i], blue[i], intensity[i]));

        vals.sort(new Comparator<Integer>() {
          @Override
          public int compare(Integer o1, Integer o2) {
            return o2 - o1;
          }
        });

        String[] array = new String[4];
        array[0] = values.get(vals.get(0));
        array[1] = values.get(vals.get(1));
        array[2] = values.get(vals.get(2));
        array[3] = values.get(vals.get(3));

        for (int f = 0; f < 4; f++) {
          if (array[f].equals("red")) {
            for (int j = maxVal; j > this.maxVal - red[i]; j--) {
              image.setRGB(i, j, Color.red.getRGB());
            }
          }

          if (array[f].equals("green")) {
            for (int k = maxVal; k > this.maxVal - green[i]; k--) {
              image.setRGB(i, k, Color.green.getRGB());
            }
          }

          if (array[f].equals("blue")) {
            for (int l = maxVal; l > this.maxVal - blue[i]; l--) {
              image.setRGB(i, l, Color.blue.getRGB());
            }
          }

          if (array[f].equals("intensity")) {
            for (int r = maxVal; r > this.maxVal - intensity[i]; r--) {
              image.setRGB(i, r, Color.magenta.getRGB());
            }
          }

        }


      }

      this.setPreferredSize(new Dimension(256, maxVal));
      g.drawImage(image, 0, 0, this);

    }


  }


  public void acceptController(Features c) {
    this.controller = c;
  }

}
