package model;

import java.awt.*;

/**
 * Represents the Image Manipulation Software. Can return information about its state.
 */
public class EvenBetterImageManipulatorModelImpl extends GeneralImageManipulatorModel
        implements EvenBetterImageManipulatorModel {

  /**
   * Constructs an {@code EvenBetterImageManipulatorModel} object. Has a state, as opposed to the
   * BetterImageManipulatorModel implementation.
   */
  public EvenBetterImageManipulatorModelImpl() {
    super();
  }

  @Override
  public Color getColorAt(int x, int y, String imageName) {
    Pixel[][] img = this.files.get(imageName);

    return new Color(img[y][x].red, img[y][x].green, img[y][x].blue);

  }

  @Override
  public int getBoardWidth(String imageName) {
    Pixel[][] img = this.files.get(imageName);

    if (img == null) {
      return 0;
    } else {
      return this.files.get(imageName)[0].length;
    }
  }

  @Override
  public int getBoardHeight(String imageName) {
    Pixel[][] img = this.files.get(imageName);

    if (img == null) {
      return 0;
    } else {
      return this.files.get(imageName).length;
    }
  }

  @Override
  public int[] getHistogram(String component, String imageName) {
    this.greyscale(component, imageName, imageName
            + component + "h1$t0gr4m");
    Pixel[][] img = this.files.get(imageName + component + "h1$t0gr4m");
    int[] output = new int[256];

    for (int i = 0; i < img.length; i++) {
      for (int j = 0; j < img[0].length; j++) {

        output[img[i][j].red] = output[img[i][j].red] + 1;

      }
    }

    return output;
  }
}
