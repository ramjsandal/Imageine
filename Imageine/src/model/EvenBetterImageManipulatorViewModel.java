package model;

import java.awt.*;

/**
 * Represents a view model for the Image Manipulator. Allows users to get a view of the
 * model without exposing the implementation.
 */
public class EvenBetterImageManipulatorViewModel implements ImageManipulatorViewModel {

  private final EvenBetterImageManipulatorModel model;


  /**
   * Constructs an {@code BetterImageManipulatorViewModel} object. Takes in a model to
   * construct with.
   *
   * @param model the model to use in constructing the image.
   * @throws IllegalArgumentException if the model given is null
   */
  public EvenBetterImageManipulatorViewModel(EvenBetterImageManipulatorModel model)
          throws IllegalArgumentException {

    if (model != null) {
      this.model = model;
    } else {
      throw new IllegalArgumentException("Cannot have null model");
    }
  }

  @Override
  public Color getColorAt(int x, int y, String imageName) {
    return this.model.getColorAt(x, y, imageName);
  }

  @Override
  public int getBoardWidth(String imageName) {
    return this.model.getBoardWidth(imageName);
  }

  @Override
  public int getBoardHeight(String imageName) {
    return this.model.getBoardHeight(imageName);
  }

  @Override
  public int[] getHistogram(String component, String imageName) {
    return this.model.getHistogram(component, imageName);
  }

}
