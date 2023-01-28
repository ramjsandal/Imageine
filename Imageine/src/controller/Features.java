package controller;

import java.io.File;

/**
 * Represents the features that the controller will have. The controller should implement this
 * interface and support the methods provided.
 */
public interface Features {

  /**
   * Loads the image at the given file.
   *
   * @param f the file to load.
   */
  public void loadImage(File f);

  /**
   * Saves the image to the given file.
   *
   * @param f the file to save to.
   */
  public void saveImage(File f);

  /**
   * Vertically flips the image being worked on.
   */
  public void verticalFlip();

  /**
   * Horizontally flips the image being worked on.
   */
  public void horizontalFlip();

  /**
   * Brightens the image being worked on.
   *
   * @param increment the amount to brighten the image by.
   */
  public void brighten(int increment);

  /**
   * Greyscales the image by the given component.
   *
   * @param component the component to greyscale the image by.
   */
  public void greyscale(String component);

  /**
   * Filters the image currently being worked on with the given filter.
   *
   * @param filter the filter to use on the image.
   */
  public void filter(String filter);

  /**
   * Transforms the image currently being worked on with the given transform.
   *
   * @param transform the transform to use on the image.
   */
  public void transform(String transform);

  /**
   * Gets the name of the current image.
   *
   * @return the name of the image.
   */
  public String getCurrentImageName();
}
