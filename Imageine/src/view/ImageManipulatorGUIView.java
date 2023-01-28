package view;

import java.io.File;

import controller.Features;

/**
 * Represents the Image Manipulator Program. Displays a graphical view of the program.
 */
public interface ImageManipulatorGUIView {

  /**
   * Adds features by connecting the view to the controller, so that the user's inputs are
   * reflected in the view.
   *
   * @param features the controller to connect with.
   */
  void addFeatures(Features features);

  /**
   * Returns the file of the image that the client requests to load through the GUI.
   *
   * @return the file of the image that the client requests to load through the GUI.
   */
  File loadImage();

  /**
   * Returns the file where the client wants to save their image.
   *
   * @return the file where the client wants to save their image.
   */
  File saveImage();

  /**
   * Returns how much the client wants to brighten or darken the image by.
   *
   * @return the int to change the brightness by.
   */
  int brighten();

  /**
   * Returns the component that the client wants to greyscale their image by.
   *
   * @return the component as a string
   */
  String greyscale();

  /**
   * Renders a message to the client.
   *
   * @param message the message to render.
   */
  void renderMessage(String message);

  /**
   * Refreshes the view.
   */
  void refresh();
}
