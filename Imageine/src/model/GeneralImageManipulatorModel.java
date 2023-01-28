package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

/**
 * Manipulates images of many file types that include PNG, JPEG, PPM, and BMP.
 * This class contains the necessary methods that allow for the manipulation
 * of an image.
 */
public class GeneralImageManipulatorModel extends AbstractImageManipulatorModel
        implements BetterImageManipulatorModel {

  /**
   * A constructor that takes in a hashmap of string to pixel that denotes the files
   * being kept by our program.
   *
   * @param files the images in the form of 2D pixel arrays.
   * @throws IllegalArgumentException if the hashmap is null.
   */
  public GeneralImageManipulatorModel(HashMap<String, Pixel[][]> files)
          throws IllegalArgumentException {

    if (files == null) {
      throw new IllegalArgumentException("Cannot have null fields.");
    }
    this.files = files;
  }

  /**
   * Constructs an {@code GeneralImageManipulator} object.
   */
  public GeneralImageManipulatorModel() {
    this.files = new HashMap<String, Pixel[][]>();
  }

  @Override
  public void loadImage(String path, String destName) throws FileNotFoundException {

    if (path.substring(path.lastIndexOf(".") + 1).equals("ppm")) {
      files.put(destName, ImageUtil.readPPM(path));
    } else {
      try {
        files.put(destName, ImageUtil.readGeneral(path));
      } catch (IOException e) {
        throw new FileNotFoundException("Cannot find path.");
      }
    }

  }

  /**
   * Saves an image to the supplied destination path.
   *
   * @param destPath the location to save the file to
   * @param name     the file to save
   * @throws IOException              if there exists a failure in the transmission between the existing file
   *                                  to the destined path or vice versa.
   * @throws IllegalArgumentException if the supplied file is null or doesn't contain anything.
   */
  @Override
  public void saveImage(String destPath, String name)
          throws IOException, IllegalArgumentException {

    Pixel[][] img = files.get(name);

    if (img == null) {
      throw new IllegalArgumentException("Cannot find image.");
    }

    if (destPath.substring(destPath.lastIndexOf(".") + 1).equals("ppm")) {
      ImageUtil.writePPM(files.get(name), destPath);
    } else {
      ImageUtil.writeGeneral(files.get(name), destPath);
    }


  }

  /**
   * Applies the supplied filter to the supplied file.
   *
   * @param filt     the filter to be used
   * @param name     the name of the image to apply the filter to
   * @param destName the new, filtered image
   * @throws IllegalArgumentException if the given file is null or empty, or if the
   *                                  filter doesn't have odd dimensions.
   */
  @Override
  public void filter(double[][] filt, String name, String destName)
          throws IllegalArgumentException {

    if (filt.length % 2 == 0) {
      throw new IllegalArgumentException("Invalid filter, filters must have odd dimensions.");
    }

    Pixel[][] img = this.files.get(name);
    Pixel[][] output = new Pixel[img.length][img[0].length];

    if (img == null) {
      throw new IllegalArgumentException("Cannot find image.");
    }
    for (int i = 0; i < img.length; i++) {
      for (int j = 0; j < img[0].length; j++) {

        double redVal = 0;
        double greenVal = 0;
        double blueVal = 0;

        for (int k = 0; k < filt.length; k++) {
          for (int l = 0; l < filt[0].length; l++) {
            for (int r = 1; r <= 3; r++) {

              if ((i - (filt.length / 2)) + k < 0
                      || (j - (filt.length / 2)) + l < 0
                      || (i - (filt.length / 2)) + k >= img.length
                      || (j - (filt.length / 2)) + l >= img[0].length) {
                // nothing
              } else {

                switch (r) {
                  case 1:
                    redVal += (img[(i - (filt.length / 2) + k)][(j - (filt.length / 2)) + l].red
                            * filt[k][l]);
                    break;
                  case 2:
                    greenVal += (img[(i - (filt.length / 2) + k)][(j - (filt.length / 2)) + l].green
                            * filt[k][l]);
                    break;
                  default:
                    blueVal += (img[(i - (filt.length / 2) + k)][(j - (filt.length / 2)) + l].blue
                            * filt[k][l]);
                    break;
                }
              }
            }

          }
        }

        output[i][j] = new Pixel((int) redVal, (int) greenVal, (int) blueVal);

      }
    }


    this.files.put(destName, output);

  }

  /**
   * applies the supplied transformation onto the supplied file.
   *
   * @param transform the transform to be used
   * @param name      the name of the image to apply the transform to
   * @param destName  the new, transformed image
   * @throws IllegalArgumentException if the given transformation is null, or not odd.
   *                                  Or, if the supplied file is null or empty.
   */
  @Override
  public void colorTransformation(double[][] transform, String name, String destName)
          throws IllegalArgumentException {

    if (transform == null || transform.length != 3 || transform[0].length != 3) {
      throw new IllegalArgumentException("Transform must be a 3x3 non-null 2D Array.");
    }

    Pixel[][] img = files.get(name);

    if (img == null) {
      throw new IllegalArgumentException("Image cannot be found.");
    }

    Pixel[][] output = new Pixel[img.length][img[0].length];


    for (int i = 0; i < img.length; i++) {
      for (int j = 0; j < img[0].length; j++) {


        int red = (int) ((img[i][j].red * transform[0][0])
                + (img[i][j].green * transform[0][1])
                + (img[i][j].blue * transform[0][2]));

        int green = (int) ((img[i][j].red * transform[1][0])
                + (img[i][j].green * transform[1][1])
                + (img[i][j].blue * transform[1][2]));

        int blue = (int) ((img[i][j].red * transform[2][0])
                + (img[i][j].green * transform[2][1])
                + (img[i][j].blue * transform[2][2]));

        output[i][j] = new Pixel(red, green, blue);
      }

    }

    this.files.put(destName, output);

  }


}
