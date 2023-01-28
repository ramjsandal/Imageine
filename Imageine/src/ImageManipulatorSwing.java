import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import controller.ImageManipulatorController;
import controller.ImageManipulatorControllerImpl;
import controller.ImageManipulatorGUIController;
import model.BetterImageManipulatorModel;
import model.EvenBetterImageManipulatorModel;
import model.EvenBetterImageManipulatorModelImpl;
import model.GeneralImageManipulatorModel;
import view.ImageManipulatorGUIView;
import view.ImageManipulatorSwingView;
import view.ImageManipulatorTextView;
import view.ImageManipulatorView;

/**
 * Contains methods which runs the program. Launches a new instance of the program.
 */
public class ImageManipulatorSwing {

  /**
   * Allows users to run and supply arguments to the main program. Launches a window which
   * the user can interact with.
   *
   * @param args arguments that the user can give.
   */
  public static void main(String[] args) {

    BetterImageManipulatorModel textModel = new GeneralImageManipulatorModel();
    ImageManipulatorView textView = new ImageManipulatorTextView(System.out);
    Readable reader;
    ImageManipulatorController textController;


    if (args.length != 0) {


      switch (args[0]) {
        case "-file":

          try {
            File f = new File(args[1]);

            reader = new InputStreamReader(new FileInputStream(f));

            textController = new ImageManipulatorControllerImpl(textModel, textView, reader);
            textController.launchProgram();

          } catch (NullPointerException | FileNotFoundException e) {
            throw new IllegalArgumentException("Invalid file path.");
          }
          break;

        case "-text":

          reader = new InputStreamReader(System.in);
          textController = new ImageManipulatorControllerImpl(textModel, textView, reader);
          textController.launchProgram();
          break;

        default:
          throw new IllegalArgumentException("Invalid Command Line argument: must be either " +
                  "'-file file-path' or '-text'");
      }


    } else {

      EvenBetterImageManipulatorModel model = new EvenBetterImageManipulatorModelImpl();
      ImageManipulatorGUIView view = new ImageManipulatorSwingView(model);
      ImageManipulatorGUIController controller = new ImageManipulatorGUIController(model, view);
      view.refresh();
    }

  }

}
