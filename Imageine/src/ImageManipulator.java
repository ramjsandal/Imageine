import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import controller.ImageManipulatorController;
import controller.ImageManipulatorControllerImpl;
import model.BetterImageManipulatorModel;
import model.GeneralImageManipulatorModel;
import view.ImageManipulatorTextView;
import view.ImageManipulatorView;

/** Represents the class that contain methodologies which
 * allow users to run the {@code ImageManipulator} program.
 */
public class ImageManipulator {

  /** Represents the main class that allow users to run and supply arguments
   *  to the {@code ImageManipulator} program.
   *  @param args the supplied user arguments.
   */
  public static void main(String[] args) {
    BetterImageManipulatorModel model;
    ImageManipulatorView view;
    ImageManipulatorController controller;
    Readable reader;
    if (args.length != 0) {

      if (args[0].equals("-file")) {

        try {
          File f = new File(args[1]);

          reader = new InputStreamReader(new FileInputStream(f));


        } catch (NullPointerException | FileNotFoundException e) {
          throw new IllegalArgumentException("Invalid file path.");
        }

      } else {
        throw new IllegalArgumentException("Invalid Command Line argument, must be of the form: " +
                "-file file-name.txt");
      }

    } else {
      reader = new InputStreamReader(System.in);
    }

    model = new GeneralImageManipulatorModel();
    view = new ImageManipulatorTextView(System.out);
    controller = new ImageManipulatorControllerImpl(model, view, reader);

    controller.launchProgram();
  }
}