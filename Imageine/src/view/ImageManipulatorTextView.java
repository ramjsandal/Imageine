package view;

import java.io.IOException;

/**
 * Displays a view of the image manipulator.
 */
public class ImageManipulatorTextView implements ImageManipulatorView {

  private final Appendable output;

  /**
   * Constructs an {@code ImageManipulatorTextView} object.
   *
   * @param output the location to output to.
   * @throws IllegalStateException if the appendable is null.
   */
  public ImageManipulatorTextView(Appendable output) throws IllegalArgumentException {
    if (output == null) {
      throw new IllegalArgumentException("Appendable can't be null.");
    }
    this.output = output;
  }

  /**
   * Renders a specific message to the provided data destination.
   *
   * @param message the message to render.
   * @throws IOException if the transmission of the message to the provided data destination fails.
   */
  @Override
  public void renderMessage(String message) throws IOException {
    this.output.append(message);
  }
}
