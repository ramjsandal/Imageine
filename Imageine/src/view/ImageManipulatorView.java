package view;

import java.io.IOException;

/**
 * Represents a view of the image.
 */
public interface ImageManipulatorView {


  /**
   * Renders a message.
   *
   * @param message the message to render.
   * @throws IOException if message cannot be transmitted
   */
  public void renderMessage(String message) throws IOException;

}
