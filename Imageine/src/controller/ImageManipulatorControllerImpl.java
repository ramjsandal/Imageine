package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

import model.BetterImageManipulatorModel;
import view.ImageManipulatorView;

/**
 * The implementation of the controller for the {@code ImageManipulator} program.
 * In specific, this class contains operations and methods
 * that enables a player to supply arguments
 * and conduct operations on an image through the {@code ImageManipulator} program.
 */
public class ImageManipulatorControllerImpl implements ImageManipulatorController {

  private final BetterImageManipulatorModel model;

  private final ImageManipulatorView view;

  private final Readable input;

  /**
   * Constructs an {@code ImageManipulatorImpl} object.
   *
   * @param model The model that the {@code ImageManipulator} instantiates with.
   * @param view  The view that the {@code ImageManipulator} uses to display operations.
   * @param input Represents the user inputs that are supplied into the controller.
   * @throws IllegalArgumentException if any of the supplied arguments (model, view, or input) is
   *                                  null.
   */
  public ImageManipulatorControllerImpl(BetterImageManipulatorModel model,
                                        ImageManipulatorView view,
                                        Readable input) throws IllegalArgumentException {

    if (model == null || view == null || input == null) {
      throw new IllegalArgumentException("Cannot have null fields.");
    }

    this.model = model;
    this.view = view;
    this.input = input;

  }

  /**
   * Runs the {@code ImageManipulator} program.
   * In specific, the program runs based on/using the user's inputs, and outputs the appropriate
   * view and model based on those inputs.
   * This class handles the various operations and commands
   * that a user can supply into the program.
   */
  @Override
  public void launchProgram() throws IllegalStateException {

    String userInput;
    Scanner scan = new Scanner(this.input);
    boolean quit = false;
    String name;
    String destName;

    try {
      this.view.renderMessage("Welcome to the ImageManipulation software!" + "\n"
              + "Type 'help' to see a list of commands." + "\n");
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
    while (!quit) {

      try {
        userInput = scan.next();
      } catch (NoSuchElementException | IllegalStateException e) {
        throw new IllegalStateException("Cannot read Readable.");
      }


      switch (userInput) {
        case "load":
          String loadPath = scan.next();
          String loadName = scan.next();
          this.tryLoad(loadPath, loadName);
          break;
        case "save":
          String savePath = scan.next();
          String saveName = scan.next();
          this.trySave(savePath, saveName);
          break;
        case "greyscale":
          String component = scan.next();
          name = scan.next();
          destName = scan.next();
          this.tryGreyscale(component, name, destName);
          break;
        case "vertical-flip":
          name = scan.next();
          destName = scan.next();
          this.tryVerticalFlip(name, destName);
          break;
        case "horizontal-flip":
          name = scan.next();
          destName = scan.next();
          this.tryHorizontalFlip(name, destName);
          break;
        case "brighten":
          int increment = scan.nextInt();
          name = scan.next();
          destName = scan.next();
          this.tryBrighten(increment, name, destName);
          break;
        case "filter":
          String filter = scan.next();
          name = scan.next();
          destName = scan.next();
          this.tryFilter(filter, name, destName);
          break;
        case "transform":
          String transform = scan.next();
          name = scan.next();
          destName = scan.next();
          this.tryTransform(transform, name, destName);
          break;
        case "help":
          this.helpMessage();
          break;
        case "quit":
        case "Quit":
        case "q":
        case "Q":
          try {
            this.view.renderMessage("Quitting program...");
          } catch (IOException e) {
            throw new IllegalStateException(e.getMessage());
          }
          quit = true;
          break;
        default:
          throw new IllegalStateException("Unsupported Operation" + System.lineSeparator());

      }

    }

  }

  // Applies the supplied transform onto the supplied file.
  private void tryTransform(String transform, String name, String destName) {

    HashMap<String, double[][]> transforms = this.getTransforms();
    double[][] transf = transforms.get(transform);

    try {
      this.model.colorTransformation(transf, name, destName);

      try {
        this.view.renderMessage("Created transformed version of " + name + " called "
                + destName + " with transform: " + transform + "\n");
      } catch (IOException ioException) {
        throw new IllegalStateException(ioException.getMessage());
      }


    } catch (IllegalArgumentException e) {

      try {
        this.view.renderMessage("Could not find image, or transform type unsupported.");
      } catch (IOException exception) {
        throw new IllegalStateException(e.getMessage());
      }

    }

  }

  // Tries to apply the supplied filter onto the supplied file.
  private void tryFilter(String filter, String name, String destName)
          throws IllegalStateException {

    HashMap<String, double[][]> filters = this.getFilters();
    double[][] filt = filters.get(filter);

    try {
      this.model.filter(filt, name, destName);

      try {
        this.view.renderMessage("Created filtered version of " + name + " called "
                + destName + " with filter: " + filter + "\n");
      } catch (IOException ioException) {
        throw new IllegalStateException(ioException.getMessage());
      }


    } catch (IllegalArgumentException e) {

      try {
        this.view.renderMessage("Could not find image, or filter type unsupported.");
      } catch (IOException exception) {
        throw new IllegalStateException(e.getMessage());
      }

    }

  }

  // prints out the help message when the user supplies "help" as an argument.
  private void helpMessage() throws IllegalStateException {
    try {
      this.view.renderMessage("List of commands:" + "\n");
      this.view.renderMessage("'load file-path file-name' -> loads a file at the path " +
              "'file-path' as 'file-name'."
              + "\n");
      this.view.renderMessage("'save file-path file-name' -> saves a file 'file-name' " +
              "to the location 'file-path'."
              + "\n");
      this.view.renderMessage("'greyscale component image-name dest-image-name' -> " +
              "creates a greyscaled version of 'image-name' called 'dest-image-name' " +
              "according to 'component'.\n'component' must be one of [red, green, blue, value, " +
              "intensity, luma]."
              + "\n");
      this.view.renderMessage("'vertical-flip image-name dest-image-name' -> " +
              "creates a vertically flipped version of 'image-name' called 'dest-image-name'."
              + "\n");
      this.view.renderMessage("'horizontal-flip image-name dest-image-name' -> " +
              "creates a horizontally flipped version of 'image-name' " +
              "called 'dest-image-name'."
              + "\n");
      this.view.renderMessage("'brighten increment image-name dest-image-name' -> " +
              "creates a brightened version of 'image-name' called 'dest-image-name', where " +
              "'increment' determines the how much the image is brightened or, if negative, "
              + "darkened."
              + "\n");
      this.view.renderMessage("'filter filter-type image-name dest-image-name' -> creates a " +
              "filtered version of 'image-name' called 'dest-image-name' using the filter " +
              "'filter-type'.\n Supported filter types: 'blur', 'sharpen'.\n");
      this.view.renderMessage("'transform transform-type image-name dest-image-name' -> creates a "
              + "transformed version of 'image-name' called 'dest-image-name' using the " +
              "transform 'transform-type'.\n Supported transform types: 'greyscale', 'sepia'.\n");
      this.view.renderMessage("'q' or 'Q' or 'Quit' or 'quit' -> quits the program" + "\n");
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

  // loads the image in the supplied path as the supplied name.
  private void tryLoad(String path, String name) throws IllegalStateException {

    // try to load the image
    try {
      this.model.loadImage(path, name);

      // transmit message to the user to tell them the file has loaded
      try {
        this.view.renderMessage("Loaded " + path + " as " + name + "\n");
      } catch (IOException e) {
        throw new IllegalStateException(e.getMessage());
      }

    } catch (FileNotFoundException fe) {

      // if the final cannot be found, tell the user
      try {
        this.view.renderMessage("Could not find file, please try again.\n");
      } catch (IOException e) {
        throw new IllegalStateException(e.getMessage());
      }
    }
  }

  // saves the image of the supplied name as a file in the supplied path.
  private void trySave(String path, String name) throws IllegalStateException {

    try {
      // try to save the image
      try {
        this.model.saveImage(path, name);
      } catch (IOException e) {
        throw new IllegalStateException("Transmission to output file failed.");
      }

      // tell the user that the image has been saved
      try {
        this.view.renderMessage("Saved " + name + " to " + path + "\n");
      } catch (IOException ex) {
        throw new IllegalStateException(ex.getMessage());
      }

    } catch (IllegalArgumentException e) {

      try {
        this.view.renderMessage("Image to save could not be found, please try again.\n");
      } catch (IOException exception) {
        throw new IllegalStateException(e.getMessage());
      }

    }

  }

  // applies greyscale of type component to the file supplied (oldName) and outputs
  // a new image in the hashmap of files with the key of the supplied greyscaledName.
  private void tryGreyscale(String component, String oldName, String greyscaledName)
          throws IllegalStateException {

    try {
      this.model.greyscale(component, oldName, greyscaledName);

      try {
        this.view.renderMessage("Created greyscaled version of "
                + oldName + " called " + greyscaledName + "\n");
      } catch (IOException e) {
        throw new IllegalStateException(e.getMessage());
      }

    } catch (IllegalArgumentException e) {
      try {
        this.view.renderMessage(e.getMessage() + " Please try again.\n");
      } catch (IOException exception) {
        throw new IllegalStateException(e.getMessage());
      }
    }
  }

  // vertically flips the file of the given name and outputs a new 2-D pixel array representation
  // of the image in the hashmap of files with the key of destName.
  // throws an IllegalStateException if the supplied file doesn't exist, or the appendable
  // to render the view is null.
  private void tryVerticalFlip(String name, String destName) throws IllegalStateException {

    try {
      this.model.verticalFlip(name, destName);

      try {
        this.view.renderMessage("Created vertically flipped version of "
                + name + " called " + destName + "\n");
      } catch (IOException exception) {
        throw new IllegalStateException(exception.getMessage());
      }

    } catch (IllegalArgumentException e) {

      try {
        this.view.renderMessage(e.getMessage() + " Please try again.");
      } catch (IOException exception) {
        throw new IllegalStateException(e.getMessage());
      }

    }

  }

  // horizontally flips the file of the given name and outputs a new 2-D pixel array representation
  // of the file in the hashmap of files with the key of destName.
  // throws an IllegalStateException if the supplied file doesn't exist, or the appendable
  // to render the view is null/non-transmittable
  private void tryHorizontalFlip(String name, String destName) throws IllegalStateException {

    try {
      this.model.horizontalFlip(name, destName);

      try {
        this.view.renderMessage("Created horizontally flipped version of "
                + name + " called " + destName + "\n");
      } catch (IOException exception) {
        throw new IllegalStateException(exception.getMessage());
      }

    } catch (IllegalArgumentException e) {

      try {
        this.view.renderMessage(e.getMessage() + " Please try again.\n");
      } catch (IOException exception) {
        throw new IllegalStateException(e.getMessage());
      }

    }

  }

  // brightens the file of the given name by the supplied increment and outputs a new 2-D pixel
  // representation of the brightened image in the hashmap of files with the key of destName.
  // throws an IllegalStateException if the supplied file doesn't exist, or the appendable
  // to render the view is null/non-transmittable.
  private void tryBrighten(int increment, String name, String destName)
          throws IllegalStateException {

    try {
      this.model.brighten(increment, name, destName);

      try {
        this.view.renderMessage("Created brightened version of "
                + name + " called " + destName + "\n");
      } catch (IOException e) {
        throw new IllegalStateException(e.getMessage());
      }

    } catch (IllegalArgumentException e) {

      try {
        this.view.renderMessage(e.getMessage() + " Please try again.\n");
      } catch (IOException exception) {
        throw new IllegalStateException(exception.getMessage());
      }

    }

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
