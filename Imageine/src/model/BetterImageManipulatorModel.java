package model;

/**
 * An updated version of the ImageManipulatorModel that now contains two new operations
 * which are filter and transform. Having this retains the usability of all our previous
 * classes and interfaces, while adding new functionalities and support.
 */
public interface BetterImageManipulatorModel extends ImageManipulatorModel {

  /**
   * Creates a filtered version of the image called destName by applying the given filter.
   *
   * @param filt     the filter to be used
   * @param name     the name of the image to apply the filter to
   * @param destName the new, filtered image
   * @throws IllegalArgumentException if the image to filter cannot be found, or if the filter
   *                                  is invalid.
   */
  public void filter(double[][] filt, String name, String destName)
          throws IllegalArgumentException;

  /**
   * Creates a transformed version of the image called destName by applying the given transform.
   *
   * @param transform the transform to be used
   * @param name      the name of the image to apply the transform to
   * @param destName  the new, transformed image
   * @throws IllegalArgumentException if the image to transform cannot be found, or if the
   *                                  transform is invalid.
   */
  public void colorTransformation(double[][] transform, String name, String destName)
          throws IllegalArgumentException;

}
