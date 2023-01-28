import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;

import model.BetterImageManipulatorModel;

/**
 * An implementation of the Image Manipulator that returns the inputted values.
 */
public class InputManipulatorModel implements BetterImageManipulatorModel {
  private final StringWriter log;

  public InputManipulatorModel(StringWriter log) {
    this.log = log;
  }

  @Override
  public void filter(double[][] filt, String name, String destName)
          throws IllegalArgumentException {
    this.log.append("Filter: " + filt[0][0] + filt[0][1] + filt[0][2] + " Image name: " + name
            + " Dest name: " + destName);
  }

  @Override
  public void colorTransformation(double[][] transform, String name, String destName)
          throws IllegalArgumentException {
    this.log.append("Transform: " + transform[0][0] + transform[0][1] + transform[0][2]
            + " Image name: " + name
            + " Dest name: " + destName);
  }

  @Override
  public void loadImage(String path, String destName) throws FileNotFoundException {
    this.log.append("Image path: " + path + " Dest name: " + destName);
  }

  @Override
  public void saveImage(String destPath, String name) throws IOException {
    this.log.append("Dest path: " + destPath + " Image name: " + name);
  }

  @Override
  public void greyscale(String component, String name, String destName)
          throws IllegalArgumentException {
    this.log.append("Greyscale: " + component + " Image name: " + name
            + " Dest name: " + destName);
  }

  @Override
  public void verticalFlip(String name, String destName) throws IllegalArgumentException {
    this.log.append("Vert Flip Image name: " + name + " Dest name: " + destName);
  }

  @Override
  public void horizontalFlip(String name, String destName) throws IllegalArgumentException {
    this.log.append("Hor Flip Image name: " + name + " Dest name: " + destName);
  }

  @Override
  public void brighten(int increment, String name, String destName)
          throws IllegalArgumentException {
    this.log.append("Increment: " + increment + " Image name: " + name + " Dest name: " + destName);
  }
}