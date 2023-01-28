package model;

import java.awt.*;


/**
 * Represents a view model for an Image Manipulator. Returns relevant information about the model.
 */
public interface ImageManipulatorViewModel {

  /**
   * Returns the color of a given pixel at a given coordinate on an image.
   *
   * @param x         the x coordinate of the pixel
   * @param y         the y coordinate of the pixel
   * @param imageName the image to grab from
   * @return the color of the pixel.
   */
  public Color getColorAt(int x, int y, String imageName);

  /**
   * Gets the width of the board.
   *
   * @param imageName the image whose width we measure.
   * @return the width of the board.
   */
  public int getBoardWidth(String imageName);

  /**
   * Gets the height of the board.
   *
   * @param imageName the image whose height we measure.
   * @return the height of the board.
   */
  public int getBoardHeight(String imageName);

  /**
   * Returns a histogram of the image using the given component.
   *
   * @param component the component to generate histogram with.
   * @param imageName the image to generate histogram of.
   * @return the histogram in the form of an array of integers.
   */
  public int[] getHistogram(String component, String imageName);

}
