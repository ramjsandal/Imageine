package model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

/**
 * Utility class for images.
 */
public class ImageUtil {
  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   */
  public static Pixel[][] readPPM(String filename) throws FileNotFoundException,
          IllegalArgumentException {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException("Cannot find file");
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();

    Pixel[][] image = new Pixel[height][width];
    int maxValue = sc.nextInt();

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        image[i][j] = new Pixel(r, g, b);
      }
    }

    return image;
  }

  /**
   * Writes a file in the format of a PPM image.
   *
   * @param image    the image represented as a 2-D array of pixels.
   * @param filePath the file path that the image should be written to.
   * @throws IOException if the transmission between the image to the destination file path fails.
   */
  public static void writePPM(Pixel[][] image, String filePath) throws IOException {


    FileWriter text = new FileWriter(filePath);


    text.write("P3" + System.lineSeparator());
    text.write("#js & sr" + System.lineSeparator());
    text.write(image[0].length + " " + image.length + System.lineSeparator());
    text.write("255" + System.lineSeparator());

    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {
        text.write(Integer.toString(image[i][j].red) + System.lineSeparator());
        text.write(Integer.toString(image[i][j].green) + System.lineSeparator());
        text.write(Integer.toString(image[i][j].blue) + System.lineSeparator());
      }
    }

    text.close();
  }

  /**
   * Reads the supplied file as a 2-D array of pixels.
   * @param path the path of the file to be read.
   * @return a 2-D array of pixel representation of the supplied file.
   * @throws IOException if there exists a failure in the transmission between the existing file
   *                     to the destined path or vice versa.
   */
  public static Pixel[][] readGeneral(String path) throws IOException {
    String fileType = path.substring(path.lastIndexOf(".") + 1);

    if (fileType.equals("ppm") || fileType.equals("txt") || fileType.equals("")) {
      return readPPM(path);
    } else {
      File f = new File(path);
      BufferedImage image = ImageIO.read(f);
      int height = image.getHeight();
      int width = image.getWidth();
      Pixel[][] output = new Pixel[height][width];

      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          Color c = new Color(image.getRGB(j, i));
          int r = c.getRed();
          int g = c.getGreen();
          int b = c.getBlue();
          output[i][j] = new Pixel(r, g, b);
        }
      }

      return output;
    }
  }

  /**
   * Writes the supplied 2-D array of pixels as a file.
   * @param image The 2-D array of pixel to be written as a file.
   * @param path  The path of the file to be saved at.
   * @throws IOException if there exists a failure in the transmission between the existing file
   *                     to the destined path or vice versa.
   */
  public static void writeGeneral(Pixel[][] image, String path) throws IOException {
    String fileType = path.substring(path.lastIndexOf(".") + 1);
    File f = new File(path);
    int width = image[0].length;
    int height = image.length;
    BufferedImage image2 = new BufferedImage(image[0].length, image.length, TYPE_INT_RGB);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Color c = new Color(image[i][j].red, image[i][j].green, image[i][j].blue);
        image2.setRGB(j, i, c.getRGB());
      }
    }

    ImageIO.write(image2, fileType, f);

  }

}
