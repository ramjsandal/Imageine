package model;

import java.util.Objects;

/**
 * Represents a pixel in an image.
 */
public class Pixel {

  protected final int red;

  protected final int green;

  protected final int blue;

  /**
   * Constructs an {@code model.Pixel} object based on the given colors. Corrects them into the
   * range 0 - 255 if outside the range
   *
   * @param red   the amount of red in the pixel
   * @param green the amount of green in the pixel
   * @param blue  the amount of blue in the pixel
   */
  public Pixel(int red, int green, int blue) {

    if (red < 0) {
      this.red = 0;
    } else if (red > 255) {
      this.red = 255;
    } else {
      this.red = red;
    }

    if (green < 0) {
      this.green = 0;
    } else if (green > 255) {
      this.green = 255;
    } else {
      this.green = green;
    }

    if (blue < 0) {
      this.blue = 0;
    } else if (blue > 255) {
      this.blue = 255;
    } else {
      this.blue = blue;
    }
  }

  /**
   * Brightens a pixel by the given integer value, maxing out at 255 if i is positive. If i
   * is negative, darkens the image by the given integer to a minimum of 0.
   *
   * @param i amount to brighten the pixel by.
   * @return a new pixel that has been brightened.
   */
  public Pixel brighten(int i) {

    return new Pixel(red + i, green + i, blue + i);
  }

  /**
   * Greyscales the pixel according to the given component.
   *
   * @param component the component by which to greyscale
   * @return a grayscaled version of the pixel.
   * @throws IllegalArgumentException if the given string is not a valid component
   */
  public Pixel greyscale(String component) throws IllegalArgumentException {

    int val = 0;

    switch (component) {
      case "red":
        val = this.red;
        break;
      case "green":
        val = this.green;
        break;
      case "blue":
        val = this.blue;
        break;
      case "value":
        val = Math.max(Math.max(this.red, this.green), this.blue);
        break;
      case "intensity":
        val = (this.red + this.green + this.blue) / 3;
        break;
      case "luma":
        val = (int) ((this.red * .2126) + (this.green * .7152) + (this.blue * .0722));
        break;
      default:
        throw new IllegalArgumentException("Not a valid component.");
    }

    return new Pixel(val, val, val);
  }

  /**
   * Determines if this {@code Pixel} is the same as the supplied object by comparing fields.
   *
   * @param o the object to be compared to the current {@code Pixel}.
   * @return true if the two objects are the same. Else, returns false if they aren't.
   */
  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Pixel)) {
      return false;
    }

    if (this == o) {
      return true;
    }

    Pixel compare = (Pixel) o;

    return this.red == compare.red && this.green == compare.green && this.blue == compare.blue;
  }

  /**
   * Generates a new hashcode for the object (used for overriding equals).
   *
   * @return the integer value of the object's new hash.
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.red, this.green, this.blue);
  }


}
