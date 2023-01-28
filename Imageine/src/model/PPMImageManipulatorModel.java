package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

/**
 * The PPM image type model implementation supported by the {@code ImageManipulator}.
 * This class contains all the operations/methods that can be used by the model
 * of a PPM type image.
 */
public class PPMImageManipulatorModel extends AbstractImageManipulatorModel {

  /**
   * Constructs an {@code PPMImageManipulatorModel} object with a given hashmap of images in
   * 2D pixel arrays.
   *
   * @param files the images in the form of 2D pixel arrays.
   * @throws IllegalArgumentException if the hashmap is null
   */
  public PPMImageManipulatorModel(HashMap<String, Pixel[][]> files)
          throws IllegalArgumentException {
    if (files == null) {
      throw new IllegalArgumentException("Cannot have null files");
    }

    this.files = files;
  }

  /**
   * Constructs an {@code PPMImageManipulatorModel} object.
   */
  public PPMImageManipulatorModel() {
    this(new HashMap<String, Pixel[][]>());
  }

  /**
   * Loads a certain image as a certain image name.
   *
   * @param path     the image to be loaded.
   * @param destName the name that the loaded image can be referred to as.
   * @throws FileNotFoundException if the path/image doesn't exist (or is mistyped).
   */
  public void loadImage(String path, String destName) throws FileNotFoundException {

    files.put(destName, ImageUtil.readPPM(path));

  }

  /**
   * Saves a certain image as another image file.
   *
   * @param destPath the location to save the file to.
   * @param name     the file to save.
   * @throws IllegalArgumentException if the image cannot be found in the hashmap
   * @throws IOException              if there exists a failure in the transmission
   *                                  between the existing file
   *                                  to the destined path or vice versa.
   */
  @Override
  public void saveImage(String destPath, String name)
          throws IOException, IllegalArgumentException {

    Pixel[][] img = files.get(name);

    if (img == null) {
      throw new IllegalArgumentException("Cannot find image.");
    }

    ImageUtil.writePPM(files.get(name), destPath);

  }

}
