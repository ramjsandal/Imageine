package model;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The interface that contains all the methods and operations available for the model.
 * This class contains the methods that allow for the manipulation of an image as
 * specified in the assignment. The ability to load, save, and operate on an image and
 * their respective methods are in this class.
 */
public interface ImageManipulatorModel {

  /**
   * Loads the image from the specified path, and names it so that it can be referred to by the
   * name the user inputs.
   *
   * @param path     the file path of the file
   * @param destName the name to refer to the image by
   * @throws FileNotFoundException if the file from the specified path cannot be found
   */
  public void loadImage(String path, String destName) throws FileNotFoundException;

  /**
   * Saves the image of the given name to the specified file path.
   *
   * @param destPath the location to save the file to
   * @param name     the file to save
   */
  public void saveImage(String destPath, String name) throws IOException;


  /**
   * Greyscales the given image according to the given component, referred to as the given name.
   *
   * @param component the component to greyscale by
   * @param name      the name of the image to greyscale
   * @param destName  the name of the new greyscaled image
   * @throws IllegalArgumentException if any of the fields are invalid
   */
  public void greyscale(String component, String name, String destName)
          throws IllegalArgumentException;

  /**
   * Flips an image of the given name vertically and refers to the flipped image as the new name.
   *
   * @param name     the name of the image to flip
   * @param destName the name of the flipped image
   * @throws IllegalArgumentException if the image to flip cannot be found
   */
  public void verticalFlip(String name, String destName) throws IllegalArgumentException;

  /**
   * Flips an image of the given name horizontally and refers to the flipped image as the new name.
   *
   * @param name     the name of the image to flip
   * @param destName the name of the flipped image
   * @throws IllegalArgumentException if the image to flip cannot be found
   */
  public void horizontalFlip(String name, String destName) throws IllegalArgumentException;


  /**
   * Brightens the given image by the given increment and refers to it as the new name.
   *
   * @param increment the amount to increase the brightness by
   * @param name      the name of the image to brighten
   * @param destName  the name of the brightened image
   * @throws IllegalArgumentException if the image to brighten cannot be found
   */
  public void brighten(int increment, String name, String destName)
          throws IllegalArgumentException;


}
