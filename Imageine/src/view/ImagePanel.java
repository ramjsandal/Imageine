package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import controller.Features;
import model.ImageManipulatorViewModel;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

/**
 * Draws the current image being manipulated. Gets the current image from a controller
 * and information about that image from a view model.
 */
public class ImagePanel extends JPanel {

  private ImageManipulatorViewModel model;

  private Features controller;

  /**
   * Constructs an {@code ImagePanel} object. Takes in a view model to do so.
   *
   * @param model the model to base the image off of.
   * @throws IllegalArgumentException
   */
  public ImagePanel(ImageManipulatorViewModel model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Cannot have null model");
    }
    this.model = model;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    int width = this.model.getBoardWidth(this.controller.getCurrentImageName());
    int height = this.model.getBoardHeight(this.controller.getCurrentImageName());

    if (width <= 0 || height <= 0) {
      // do nothing, because we do not have an image to draw
    } else {
      BufferedImage image2 = new BufferedImage(width, height, TYPE_INT_RGB);

      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          Color c = this.model.getColorAt(j, i, this.controller.getCurrentImageName());
          image2.setRGB(j, i, c.getRGB());
        }
      }

      this.setPreferredSize(new Dimension(width, height));
      g.drawImage(image2, 0, 0, this);
    }

  }


  /**
   * Accepts the controller. Used for callbacks.
   *
   * @param c the controller to accept.
   */
  public void acceptController(Features c) {
    this.controller = c;
  }

}
