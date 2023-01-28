package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

/**
 * An abstract class with operations expected for an abstract {@code ImageManipulatorModel}.
 * This class includes all methodologies and fields expected for the model of an
 * {@code ImageManipulator}.
 */
public abstract class AbstractImageManipulatorModel implements ImageManipulatorModel {

  protected HashMap<String, Pixel[][]> files;


  /**
   * Loads a certain image as a certain image name.
   *
   * @param path     the image to be loaded.
   * @param destName the name that the loaded image can be referred to as.
   * @throws FileNotFoundException if the path/image doesn't exist (or is mistyped).
   */
  public abstract void loadImage(String path, String destName) throws FileNotFoundException;

  /**
   * Saves a certain image as another image file.
   *
   * @param destPath the location to save the file to.
   * @param name     the file to save.
   * @throws IOException if there exists a failure in the transmission between the existing file
   *                     to the destined path or vice versa.
   */
  public abstract void saveImage(String destPath, String name) throws IOException;


  /**
   * Greyscales an image based on the given component.
   *
   * @param component the component to greyscale by.
   * @param name      the name of the image to greyscale.
   * @param destName  the name of the new greyscaled image.
   * @throws IllegalArgumentException when the image (or name of the image file) can't be found,
   *                                  has been mistyped, or doesn't exist.
   */
  @Override
  public void greyscale(String component, String name, String destName)
          throws IllegalArgumentException {

    Pixel[][] img = files.get(name);

    if (img == null) {
      throw new IllegalArgumentException("Image cannot be found");
    }

    Pixel[][] output = new Pixel[img.length][img[0].length];

    for (int i = 0; i < img.length; i++) {
      for (int j = 0; j < img[0].length; j++) {

        output[i][j] = img[i][j].greyscale(component);

      }
    }

    files.put(destName, output);

  }

  /**
   * Flips or mirror an image in the vertical direction (up-down).
   *
   * @param name     the name of the image to flip vertically.
   * @param destName the name of the new vertically-flipped image.
   * @throws IllegalArgumentException when the image (or name of the image file) can't be found,
   *                                  has been mistyped, or doesn't exist.
   */
  @Override
  public void verticalFlip(String name, String destName) throws IllegalArgumentException {

    Pixel[][] img = files.get(name);

    if (img == null) {
      throw new IllegalArgumentException("Image cannot be found");
    }

    Pixel[][] output = new Pixel[img.length][img[0].length];

    for (int i = 0; i < img.length; i++) {
      for (int j = 0; j < img[0].length; j++) {

        output[img.length - 1 - i][j] = img[i][j];

      }
    }

    files.put(destName, output);

  }

  /**
   * Flips or mirrors an image in the horizontal direction (left-right).
   *
   * @param name     the name of the image to flip.
   * @param destName the name of the new horizontally-flipped image.
   * @throws IllegalArgumentException when the image (or name of the image file) can't be found,
   *                                  has been mistyped, or doesn't exist.
   */
  @Override
  public void horizontalFlip(String name, String destName) throws IllegalArgumentException {
    Pixel[][] img = files.get(name);

    if (img == null) {
      throw new IllegalArgumentException("Image cannot be found");
    }

    Pixel[][] output = new Pixel[img.length][img[0].length];

    for (int i = 0; i < img.length; i++) {
      for (int j = 0; j < img[0].length; j++) {

        output[i][img[0].length - 1 - j] = img[i][j];

      }
    }

    files.put(destName, output);
  }

  /**
   * Brightens every {@code Pixel} in an image by the supplied integer amount.
   *
   * @param increment the amount to increase the brightness by.
   * @param name      the name of the image to brighten.
   * @param destName  the name of the brightened image.
   * @throws IllegalArgumentException when the image (or name of the image file) can't be found,
   *                                  has been mistyped, or doesn't exist.
   */
  @Override
  public void brighten(int increment, String name, String destName)
          throws IllegalArgumentException {

    Pixel[][] img = files.get(name);

    if (img == null) {
      throw new IllegalArgumentException("Image cannot be found");
    }

    Pixel[][] output = new Pixel[img.length][img[0].length];

    for (int i = 0; i < img.length; i++) {
      for (int j = 0; j < img[0].length; j++) {

        output[i][j] = img[i][j].brighten(increment);

      }
    }

    files.put(destName, output);

  }


}
