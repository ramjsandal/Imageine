package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;


import model.EvenBetterImageManipulatorModel;
import model.EvenBetterImageManipulatorModelImpl;
import view.ImageManipulatorGUIView;
import view.ImageManipulatorSwingView;

/**
 * Controls the Image Manipulator Model. Communicates between the model and the view, and helps
 * take in user input
 */
public class ImageManipulatorGUIController implements Features {

  private final EvenBetterImageManipulatorModel model;

  private final ImageManipulatorGUIView view;

  private String currentImageName;

  /**
   * Constructs an {@code ImageManipulatorGUIController} object. Uses the given model and view.
   *
   * @param model the model to use.
   * @param view  the view to use.
   * @throws IllegalArgumentException if either field is null.
   */
  public ImageManipulatorGUIController(EvenBetterImageManipulatorModel model, ImageManipulatorGUIView view)
          throws IllegalArgumentException {
    if (model == null || view == null) {
      throw new IllegalArgumentException("Cannot have null fields");
    }

    this.model = model;
    this.view = view;
    this.view.addFeatures(this);
  }

  /**
   * Constructs an {@code ImageManipulatorGUIController} object. Sets the model and view to
   * the defaults for {@code GeneralImageManipulatorModel} and {@code ImageManipulatorSwingView}
   * respectively.
   */
  public ImageManipulatorGUIController() {
    EvenBetterImageManipulatorModel model = new EvenBetterImageManipulatorModelImpl();
    this.model = model;
    this.view = new ImageManipulatorSwingView(model);
    this.view.addFeatures(this);
  }

  @Override
  public void loadImage(File f) {

    if (f != null) {

      try {
        String path = f.getAbsolutePath();
        this.model.loadImage(path, f.getName());
        this.currentImageName = f.getName();
        this.view.refresh();
        this.view.renderMessage("Loaded image at: " + path);
      } catch (FileNotFoundException fileNotFoundException) {

        this.view.renderMessage("Could not load file");

      }
    }
  }

  @Override
  public void saveImage(File f) {
    if (f != null) {
      try {
        String path = f.getAbsolutePath();
        this.model.saveImage(path, this.currentImageName);
        this.view.refresh();
        this.view.renderMessage("Saved the image at " + path);
      } catch (IOException e) {

        this.view.renderMessage("Could not save file");

      }
    }
  }

  @Override
  public void verticalFlip() {

    if (this.currentImageName != null) {

      try {

        String destName = this.currentImageName.concat("vert");
        this.model.verticalFlip(this.currentImageName, destName);
        this.currentImageName = destName;
        this.view.refresh();
        this.view.renderMessage("Vertically flipped the image");
      } catch (IllegalArgumentException e) {
        this.view.renderMessage("Could not find image");
      }

    } else {
      this.view.renderMessage("Must load an image before you can manipulate it!");
    }

  }

  @Override
  public void horizontalFlip() {
    if (this.currentImageName != null) {

      try {

        String destName = this.currentImageName.concat("hor");
        this.model.horizontalFlip(this.currentImageName, destName);
        this.currentImageName = destName;
        this.view.refresh();
        this.view.renderMessage("Horizontally flipped the image");
      } catch (IllegalArgumentException e) {
        this.view.renderMessage("Could not find image");
      }

    } else {
      this.view.renderMessage("Must load an image before you can manipulate it!");
    }
  }

  public void brighten(int increment) {
    if (this.currentImageName != null) {

      try {
        String destName = this.currentImageName.concat("br");
        this.model.brighten(increment, this.currentImageName, destName);
        this.currentImageName = destName;
        this.view.refresh();
        this.view.renderMessage("Brightened the image by " + increment);
      } catch (IllegalArgumentException e) {
        this.view.renderMessage("Could not find image");
      }

    } else {
      this.view.renderMessage("Must load an image before you can manipulate it!");
    }
  }

  @Override
  public void greyscale(String component) {
    if (this.currentImageName != null) {

      try {
        String destName = this.currentImageName.concat("gr" + component);
        this.model.greyscale(component, this.currentImageName, destName);
        this.currentImageName = destName;
        this.view.refresh();
        this.view.renderMessage("Greyscaled the image");
      } catch (IllegalArgumentException e) {
        this.view.renderMessage(e.getMessage());
      }

    } else {
      this.view.renderMessage("Must load an image before you can manipulate it!");
    }
  }

  @Override
  public void filter(String filter) {
    if (this.currentImageName != null) {

      try {
        String destName = this.currentImageName.concat(filter);
        this.model.filter(this.getFilters().get(filter), this.currentImageName, destName);
        this.currentImageName = destName;
        this.view.refresh();
        this.view.renderMessage("Applied " + filter + " to the image");
      } catch (IllegalArgumentException e) {
        this.view.renderMessage(e.getMessage());
      }

    } else {
      this.view.renderMessage("Must load an image before you can manipulate it!");
    }
  }

  @Override
  public void transform(String transform) {
    if (this.currentImageName != null) {

      try {
        String destName = this.currentImageName.concat(transform);
        this.model.colorTransformation(this.getTransforms().get(transform),
                this.currentImageName, destName);
        this.currentImageName = destName;
        this.view.refresh();
        this.view.renderMessage("Applied " + transform + " to the image");
      } catch (IllegalArgumentException e) {
        this.view.renderMessage(e.getMessage());
      }

    } else {
      this.view.renderMessage("Must load an image before you can manipulate it!");
    }
  }

  @Override
  public String getCurrentImageName() {
    return this.currentImageName;
  }




  // gets the matrix of the available filters.
  private HashMap<String, double[][]> getFilters() {
    HashMap<String, double[][]> map = new HashMap<String, double[][]>();

    double[][] blur = new double[3][3];
    blur[0][0] = .0625;
    blur[0][1] = .125;
    blur[0][2] = .0625;
    blur[1][0] = .125;
    blur[1][1] = .25;
    blur[1][2] = .125;
    blur[2][0] = .0625;
    blur[2][1] = .125;
    blur[2][2] = .0625;
    map.put("blur", blur);

    double[][] sharpen = new double[5][5];
    sharpen[0][0] = -.125;
    sharpen[0][1] = -.125;
    sharpen[0][2] = -.125;
    sharpen[0][3] = -.125;
    sharpen[0][4] = -.125;
    sharpen[1][0] = -.125;
    sharpen[1][1] = .25;
    sharpen[1][2] = .25;
    sharpen[1][3] = .25;
    sharpen[1][4] = -.125;
    sharpen[2][0] = -.125;
    sharpen[2][1] = .25;
    sharpen[2][2] = 1;
    sharpen[2][3] = .25;
    sharpen[2][4] = -.125;
    sharpen[3][0] = -.125;
    sharpen[3][1] = .25;
    sharpen[3][2] = .25;
    sharpen[3][3] = .25;
    sharpen[3][4] = -.125;
    sharpen[4][0] = -.125;
    sharpen[4][1] = -.125;
    sharpen[4][2] = -.125;
    sharpen[4][3] = -.125;
    sharpen[4][4] = -.125;
    map.put("sharpen", sharpen);

    return map;
  }

  // gets the matrix of the available transformations.
  private HashMap<String, double[][]> getTransforms() {
    HashMap<String, double[][]> map = new HashMap<String, double[][]>();

    double[][] greyscale = new double[3][3];
    greyscale[0][0] = .2126;
    greyscale[0][1] = .7152;
    greyscale[0][2] = .0722;
    greyscale[1][0] = .2126;
    greyscale[1][1] = .7152;
    greyscale[1][2] = .0722;
    greyscale[2][0] = .2126;
    greyscale[2][1] = .7152;
    greyscale[2][2] = .0722;
    map.put("greyscale", greyscale);

    double[][] sepia = new double[3][3];
    sepia[0][0] = .393;
    sepia[0][1] = .769;
    sepia[0][2] = .189;
    sepia[1][0] = .349;
    sepia[1][1] = .686;
    sepia[1][2] = .168;
    sepia[2][0] = .272;
    sepia[2][1] = .543;
    sepia[2][2] = .131;
    map.put("sepia", sepia);

    return map;
  }
}
