import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;

import controller.ImageManipulatorController;
import controller.ImageManipulatorControllerImpl;
import model.BetterImageManipulatorModel;
import model.GeneralImageManipulatorModel;
import model.Pixel;
import view.ImageManipulatorTextView;
import view.ImageManipulatorView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

/**
 * Tests {@code ImageManipulatorControllerImpl} and its methods.
 */
public class ImageManipulatorControllerImplTest {


  Appendable out1;
  Appendable badAppendable;

  ImageManipulatorController controller1;
  ImageManipulatorController inputController;

  BetterImageManipulatorModel modelNull;
  BetterImageManipulatorModel model1;
  BetterImageManipulatorModel inputModel;

  ImageManipulatorView viewNull;
  ImageManipulatorView view1;

  Readable readableNull;
  Readable readable1;
  Readable badReadable;

  StringWriter log;

  // initializing the previously defined fields.
  @Before
  public void init() {
    this.out1 = new StringBuilder();
    this.badAppendable = new BadAppendable();
    this.modelNull = null;
    this.model1 = new GeneralImageManipulatorModel();
    this.badReadable = new BadReadable();
    this.log = new StringWriter();
    this.inputModel = new InputManipulatorModel(this.log);

    this.viewNull = null;
    this.view1 = new ImageManipulatorTextView(this.out1);

    this.readableNull = null;
    this.readable1 = new StringReader("");

    this.controller1 = new ImageManipulatorControllerImpl(
            this.model1, this.view1, this.readable1);
  }

  // testing an invalid readable for file-type PPM.
  @Test
  public void testInvalidReadablePPM() {
    Appendable a1 = new StringBuilder();
    ImageManipulatorTextView v1 = new ImageManipulatorTextView(a1);
    this.model1 = new GeneralImageManipulatorModel();
    try {
      this.model1.loadImage("test/testFiles/testTwo2.ppm", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable r1 = new StringReader("");
    ImageManipulatorControllerImpl controller1 = new ImageManipulatorControllerImpl(
            this.model1, v1, r1);
    try {
      controller1.launchProgram();
      fail("Should have thrown an IllegalStateException");
    } catch (IllegalStateException e) {
      assertEquals("Cannot read Readable.", e.getMessage());
      // readable ran out of inputs
    }

  }

  // testing an invalid readable for file-type BMP
  @Test
  public void testInvalidReadableBMP() {
    Appendable a1 = new StringBuilder();
    ImageManipulatorTextView v1 = new ImageManipulatorTextView(a1);
    this.model1 = new GeneralImageManipulatorModel();
    try {
      this.model1.loadImage("test/testFiles/testTwo2.bmp", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable r1 = new StringReader("");
    ImageManipulatorControllerImpl controller1 = new ImageManipulatorControllerImpl(
            this.model1, v1, r1);
    try {
      controller1.launchProgram();
      fail("Should have thrown an IllegalStateException");
    } catch (IllegalStateException e) {
      assertEquals("Cannot read Readable.", e.getMessage());
      // readable ran out of inputs
    }

  }

  // testing invalid readable for file-type Jpeg
  @Test
  public void testInvalidReadableJPEG() {
    Appendable a1 = new StringBuilder();
    ImageManipulatorTextView v1 = new ImageManipulatorTextView(a1);
    this.model1 = new GeneralImageManipulatorModel();
    try {
      this.model1.loadImage("test/testFiles/test.jpeg", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable r1 = new StringReader("");
    ImageManipulatorControllerImpl controller1 = new ImageManipulatorControllerImpl(
            this.model1, v1, r1);
    try {
      controller1.launchProgram();
      fail("Should have thrown an IllegalStateException");
    } catch (IllegalStateException e) {
      assertEquals("Cannot read Readable.", e.getMessage());
      // readable ran out of inputs
    }

  }

  // testing invalid readable for file-type png
  @Test
  public void testInvalidReadablePNG() {
    Appendable a1 = new StringBuilder();
    ImageManipulatorTextView v1 = new ImageManipulatorTextView(a1);
    this.model1 = new GeneralImageManipulatorModel();
    try {
      this.model1.loadImage("test/testFiles/testTwo.png", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable r1 = new StringReader("");
    ImageManipulatorControllerImpl controller1 = new ImageManipulatorControllerImpl(
            this.model1, v1, r1);
    try {
      controller1.launchProgram();
      fail("Should have thrown an IllegalStateException");
    } catch (IllegalStateException e) {
      assertEquals("Cannot read Readable.", e.getMessage());
      // readable ran out of inputs
    }

  }

  // testing for valid constructors
  @Test
  public void testValidConstructor() {
    GeneralImageManipulatorModel manipulatorModel = new GeneralImageManipulatorModel();
    BetterImageManipulatorModel model = manipulatorModel;
    ImageManipulatorTextView viewTextView = new ImageManipulatorTextView(new StringBuilder());
    ImageManipulatorTextView view = viewTextView;
    StringReader test = new StringReader("");
    Readable read = test;
    // For this test, we make sure that supplying the above fields into the constructor
    // doesn't mutate them or change them. Therefore, it shows that there is a successful
    // construction of the controller!
    ImageManipulatorControllerImpl controller = new ImageManipulatorControllerImpl(
            model, view, read);

    assertEquals(model, manipulatorModel);
    assertEquals(view, viewTextView);
    assertEquals(read, test);

    assertFalse(model == null);
    assertFalse(view == null);
    assertFalse(read == null);
  }

  // testing for invalid constructors
  @Test
  public void testInvalidConstructor() {
    // model null
    try {
      this.controller1 = new ImageManipulatorControllerImpl(this.modelNull,
              this.view1, this.readable1);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Cannot have null fields.", e.getMessage());
    }

    // view null
    try {
      this.controller1 = new ImageManipulatorControllerImpl(this.model1,
              this.viewNull, this.readable1);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Cannot have null fields.", e.getMessage());
    }

    // readable null
    try {
      this.controller1 = new ImageManipulatorControllerImpl(this.modelNull,
              this.view1, this.readable1);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Cannot have null fields.", e.getMessage());
    }

    // model and view null
    try {
      this.controller1 = new ImageManipulatorControllerImpl(this.modelNull,
              this.viewNull, this.readable1);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Cannot have null fields.", e.getMessage());
    }

    // model and readable null
    try {
      this.controller1 = new ImageManipulatorControllerImpl(this.modelNull,
              this.view1, this.readableNull);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Cannot have null fields.", e.getMessage());
    }

    // readable and view null
    try {
      this.controller1 = new ImageManipulatorControllerImpl(this.model1,
              this.viewNull, this.readableNull);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Cannot have null fields.", e.getMessage());
    }

    // all null
    try {
      this.controller1 = new ImageManipulatorControllerImpl(this.modelNull,
              this.viewNull, this.readableNull);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Cannot have null fields.", e.getMessage());
    }
  }

  // testing the quit command for file-type bmp
  @Test
  public void testLaunchProgramQuitBMP() {
    // Q
    Appendable bigQQuit = new StringBuilder();
    ImageManipulatorTextView view1 = new ImageManipulatorTextView(bigQQuit);
    try {
      this.model1.loadImage("test/testFiles/testTwo2.bmp", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable bigQ = new StringReader("Q");
    ImageManipulatorControllerImpl controller1 = new ImageManipulatorControllerImpl(
            this.model1, view1, bigQ);
    controller1.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Quitting program...", bigQQuit.toString());

    // q
    Appendable smallQQuit = new StringBuilder();
    ImageManipulatorTextView view2 = new ImageManipulatorTextView(smallQQuit);
    this.model1 = new GeneralImageManipulatorModel();
    try {
      this.model1.loadImage("test/testFiles/testTwo2.bmp", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable smallQ = new StringReader("q");
    ImageManipulatorControllerImpl controller2 = new ImageManipulatorControllerImpl(
            this.model1, view2, smallQ);
    controller2.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Quitting program...", smallQQuit.toString());

    // Quit
    Appendable quitQuit = new StringBuilder();
    ImageManipulatorTextView view3 = new ImageManipulatorTextView(quitQuit);
    this.model1 = new GeneralImageManipulatorModel();
    try {
      this.model1.loadImage("test/testFiles/testTwo2.bmp", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable quitQuitR = new StringReader("Quit");
    ImageManipulatorControllerImpl controller3 = new ImageManipulatorControllerImpl(
            this.model1, view3, quitQuitR);
    controller3.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Quitting program...", quitQuit.toString());
    // quit
    Appendable quitquit = new StringBuilder();
    ImageManipulatorTextView view4 = new ImageManipulatorTextView(quitquit);
    this.model1 = new GeneralImageManipulatorModel();
    try {
      this.model1.loadImage("test/testFiles/testTwo2.bmp", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable quitquitR = new StringReader("Quit");
    ImageManipulatorControllerImpl controller4 = new ImageManipulatorControllerImpl(
            this.model1, view4, quitquitR);
    controller4.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Quitting program...", quitquit.toString());

    // Do something to quit
    Appendable somethingToQuit = new StringBuilder();
    ImageManipulatorTextView view5 = new ImageManipulatorTextView(somethingToQuit);
    this.model1 = new GeneralImageManipulatorModel();
    try {
      this.model1.loadImage("test/testFiles/testTwo2.bmp", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable somethingToQuitR = new StringReader("help load test/testFiles/testTwo2.bmp" +
            " file2 brighten 30 file2 brightenfile2 q");
    ImageManipulatorControllerImpl controller5 = new ImageManipulatorControllerImpl(
            this.model1, view5, somethingToQuitR);
    controller5.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "List of commands:\n" +
            "'load file-path file-name' -> loads a file at the path 'file-path' as 'file-name'.\n"
            + "'save file-path file-name' -> saves a file 'file-name' to the location " +
            "'file-path'.\n" +
            "'greyscale component image-name dest-image-name' -> " +
            "creates a greyscaled version of 'image-name' called 'dest-image-name' " +
            "according to 'component'.\n'component' must be one of [red, green, blue, value, " +
            "intensity, luma].\n" +
            "'vertical-flip image-name dest-image-name' -> creates a vertically flipped " +
            "version " +
            "of 'image-name' called 'dest-image-name'.\n" +
            "'horizontal-flip image-name dest-image-name' -> creates a horizontally flipped " +
            "version of 'image-name' called 'dest-image-name'.\n" +
            "'brighten increment image-name dest-image-name' -> creates a brightened" +
            " version of" +
            " 'image-name' called 'dest-image-name', where 'increment' determines the " +
            "how much " +
            "the image is brightened or, if negative, darkened.\n" +
            "'filter filter-type image-name dest-image-name' -> creates a filtered version" +
            " of 'image-name' called 'dest-image-name' using the filter 'filter-type'.\n" +
            " Supported filter types: 'blur', 'sharpen'.\n" +
            "'transform transform-type image-name dest-image-name' -> creates a transformed" +
            " version of 'image-name' called 'dest-image-name' using the transform" +
            " 'transform-type'.\n" +
            " Supported transform types: 'greyscale', 'sepia'.\n" +
            "'q' or 'Q' or 'Quit' or 'quit' -> quits the program\n" +
            "Loaded test/testFiles/testTwo2.bmp as file2\n" +
            "Created brightened version of file2 called brightenfile2\n" +
            "Quitting program...", somethingToQuit.toString());
  }

  // testing the quit command with file-type png
  @Test
  public void testLaunchProgramQuitPNG() {
    // Q
    Appendable bigQQuit = new StringBuilder();
    ImageManipulatorTextView view1 = new ImageManipulatorTextView(bigQQuit);
    try {
      this.model1.loadImage("test/testFiles/testTwo.png", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable bigQ = new StringReader("Q");
    ImageManipulatorControllerImpl controller1 = new ImageManipulatorControllerImpl(
            this.model1, view1, bigQ);
    controller1.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Quitting program...", bigQQuit.toString());

    // q
    Appendable smallQQuit = new StringBuilder();
    ImageManipulatorTextView view2 = new ImageManipulatorTextView(smallQQuit);
    this.model1 = new GeneralImageManipulatorModel();
    try {
      this.model1.loadImage("test/testFiles/testTwo.png", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable smallQ = new StringReader("q");
    ImageManipulatorControllerImpl controller2 = new ImageManipulatorControllerImpl(
            this.model1, view2, smallQ);
    controller2.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Quitting program...", smallQQuit.toString());

    // Quit
    Appendable quitQuit = new StringBuilder();
    ImageManipulatorTextView view3 = new ImageManipulatorTextView(quitQuit);
    this.model1 = new GeneralImageManipulatorModel();
    try {
      this.model1.loadImage("test/testFiles/testTwo.png", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable quitQuitR = new StringReader("Quit");
    ImageManipulatorControllerImpl controller3 = new ImageManipulatorControllerImpl(
            this.model1, view3, quitQuitR);
    controller3.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Quitting program...", quitQuit.toString());
    // quit
    Appendable quitquit = new StringBuilder();
    ImageManipulatorTextView view4 = new ImageManipulatorTextView(quitquit);
    this.model1 = new GeneralImageManipulatorModel();
    try {
      this.model1.loadImage("test/testFiles/testTwo.png", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable quitquitR = new StringReader("Quit");
    ImageManipulatorControllerImpl controller4 = new ImageManipulatorControllerImpl(
            this.model1, view4, quitquitR);
    controller4.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Quitting program...", quitquit.toString());

    // Do something to quit
    Appendable somethingToQuit = new StringBuilder();
    ImageManipulatorTextView view5 = new ImageManipulatorTextView(somethingToQuit);
    this.model1 = new GeneralImageManipulatorModel();
    try {
      this.model1.loadImage("test/testFiles/testTwo.png", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable somethingToQuitR = new StringReader("help load test/testFiles/testTwo.png file2 " +
            "brighten 30" +
            " file2 brightenfile2 q");
    ImageManipulatorControllerImpl controller5 = new ImageManipulatorControllerImpl(
            this.model1, view5, somethingToQuitR);
    controller5.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "List of commands:\n" +
            "'load file-path file-name' -> loads a file at the path 'file-path' as 'file-name'.\n"
            + "'save file-path file-name' -> saves a file 'file-name' to the location " +
            "'file-path'.\n" +
            "'greyscale component image-name dest-image-name' -> " +
            "creates a greyscaled version of 'image-name' called 'dest-image-name' " +
            "according to 'component'.\n'component' must be one of [red, green, blue, value, " +
            "intensity, luma].\n" +
            "'vertical-flip image-name dest-image-name' -> creates a vertically flipped version " +
            "of 'image-name' called 'dest-image-name'.\n" +
            "'horizontal-flip image-name dest-image-name' -> creates a horizontally flipped " +
            "version of 'image-name' called 'dest-image-name'.\n" +
            "'brighten increment image-name dest-image-name' -> creates a brightened version of" +
            " 'image-name' called 'dest-image-name', where 'increment' determines the how much " +
            "the image is brightened or, if negative, darkened.\n" +
            "'filter filter-type image-name dest-image-name' -> creates a filtered version of" +
            " 'image-name' called 'dest-image-name' using the filter 'filter-type'.\n" +
            " Supported filter types: 'blur', 'sharpen'.\n" +
            "'transform transform-type image-name dest-image-name' -> creates a transformed" +
            " version of 'image-name' called 'dest-image-name' using the transform" +
            " 'transform-type'.\n" +
            " Supported transform types: 'greyscale', 'sepia'.\n" +
            "'q' or 'Q' or 'Quit' or 'quit' -> quits the program\n" +
            "Loaded test/testFiles/testTwo.png as file2\n" +
            "Created brightened version of file2 called brightenfile2\n" +
            "Quitting program...", somethingToQuit.toString());

  }

  // testing the quit command for file-type Jpeg
  @Test
  public void testLaunchProgramQuitJPEG() {
    // Q
    Appendable bigQQuit = new StringBuilder();
    ImageManipulatorTextView view1 = new ImageManipulatorTextView(bigQQuit);
    try {
      this.model1.loadImage("test/testFiles/test.jpeg", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable bigQ = new StringReader("Q");
    ImageManipulatorControllerImpl controller1 = new ImageManipulatorControllerImpl(
            this.model1, view1, bigQ);
    controller1.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Quitting program...", bigQQuit.toString());

    // q
    Appendable smallQQuit = new StringBuilder();
    ImageManipulatorTextView view2 = new ImageManipulatorTextView(smallQQuit);
    this.model1 = new GeneralImageManipulatorModel();
    try {
      this.model1.loadImage("test/testFiles/test.jpeg", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable smallQ = new StringReader("q");
    ImageManipulatorControllerImpl controller2 = new ImageManipulatorControllerImpl(
            this.model1, view2, smallQ);
    controller2.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Quitting program...", smallQQuit.toString());

    // Quit
    Appendable quitQuit = new StringBuilder();
    ImageManipulatorTextView view3 = new ImageManipulatorTextView(quitQuit);
    this.model1 = new GeneralImageManipulatorModel();
    try {
      this.model1.loadImage("test/testFiles/test.jpeg", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable quitQuitR = new StringReader("Quit");
    ImageManipulatorControllerImpl controller3 = new ImageManipulatorControllerImpl(
            this.model1, view3, quitQuitR);
    controller3.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Quitting program...", quitQuit.toString());
    // quit
    Appendable quitquit = new StringBuilder();
    ImageManipulatorTextView view4 = new ImageManipulatorTextView(quitquit);
    this.model1 = new GeneralImageManipulatorModel();
    try {
      this.model1.loadImage("test/testFiles/test.jpeg", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable quitquitR = new StringReader("Quit");
    ImageManipulatorControllerImpl controller4 = new ImageManipulatorControllerImpl(
            this.model1, view4, quitquitR);
    controller4.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Quitting program...", quitquit.toString());

    // Do something to quit
    Appendable somethingToQuit = new StringBuilder();
    ImageManipulatorTextView view5 = new ImageManipulatorTextView(somethingToQuit);
    this.model1 = new GeneralImageManipulatorModel();
    try {
      this.model1.loadImage("test/testFiles/test.jpeg", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable somethingToQuitR = new StringReader("help load test/testFiles/test.jpeg file2 " +
            "brighten 30" +
            " file2 brightenfile2 q");
    ImageManipulatorControllerImpl controller5 = new ImageManipulatorControllerImpl(
            this.model1, view5, somethingToQuitR);
    controller5.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "List of commands:\n" +
            "'load file-path file-name' -> loads a file at the path 'file-path' as 'file-name'.\n"
            + "'save file-path file-name' -> saves a file 'file-name' to the location " +
            "'file-path'.\n" +
            "'greyscale component image-name dest-image-name' -> " +
            "creates a greyscaled version of 'image-name' called 'dest-image-name' " +
            "according to 'component'.\n'component' must be one of [red, green, blue, value, " +
            "intensity, luma].\n" +
            "'vertical-flip image-name dest-image-name' -> creates a vertically flipped version " +
            "of 'image-name' called 'dest-image-name'.\n" +
            "'horizontal-flip image-name dest-image-name' -> creates a horizontally flipped " +
            "version of 'image-name' called 'dest-image-name'.\n" +
            "'brighten increment image-name dest-image-name' -> creates a brightened version of" +
            " 'image-name' called 'dest-image-name', where 'increment' determines the how much " +
            "the image is brightened or, if negative, darkened.\n" +
            "'filter filter-type image-name dest-image-name' -> creates a filtered version" +
            " of 'image-name' called 'dest-image-name' using the filter 'filter-type'.\n" +
            " Supported filter types: 'blur', 'sharpen'.\n" +
            "'transform transform-type image-name dest-image-name' -> creates a transformed" +
            " version of 'image-name' called 'dest-image-name' using the transform" +
            " 'transform-type'.\n" +
            " Supported transform types: 'greyscale', 'sepia'.\n" +
            "'q' or 'Q' or 'Quit' or 'quit' -> quits the program\n" +
            "Loaded test/testFiles/test.jpeg as file2\n" +
            "Created brightened version of file2 called brightenfile2\n" +
            "Quitting program...", somethingToQuit.toString());
  }

  // testing the quit command for file-type PPM
  @Test
  public void testLaunchProgramQuitPPM() {
    // Q
    Appendable bigQQuit = new StringBuilder();
    ImageManipulatorTextView view1 = new ImageManipulatorTextView(bigQQuit);
    try {
      this.model1.loadImage("test/testFiles/testTwo2.ppm", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable bigQ = new StringReader("Q");
    ImageManipulatorControllerImpl controller1 = new ImageManipulatorControllerImpl(
            this.model1, view1, bigQ);
    controller1.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Quitting program...", bigQQuit.toString());

    // q
    Appendable smallQQuit = new StringBuilder();
    ImageManipulatorTextView view2 = new ImageManipulatorTextView(smallQQuit);
    this.model1 = new GeneralImageManipulatorModel();
    try {
      this.model1.loadImage("test/testFiles/testTwo2.ppm", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable smallQ = new StringReader("q");
    ImageManipulatorControllerImpl controller2 = new ImageManipulatorControllerImpl(
            this.model1, view2, smallQ);
    controller2.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Quitting program...", smallQQuit.toString());

    // Quit
    Appendable quitQuit = new StringBuilder();
    ImageManipulatorTextView view3 = new ImageManipulatorTextView(quitQuit);
    this.model1 = new GeneralImageManipulatorModel();
    try {
      this.model1.loadImage("test/testFiles/testTwo2.ppm", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable quitQuitR = new StringReader("Quit");
    ImageManipulatorControllerImpl controller3 = new ImageManipulatorControllerImpl(
            this.model1, view3, quitQuitR);
    controller3.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Quitting program...", quitQuit.toString());
    // quit
    Appendable quitquit = new StringBuilder();
    ImageManipulatorTextView view4 = new ImageManipulatorTextView(quitquit);
    this.model1 = new GeneralImageManipulatorModel();
    try {
      this.model1.loadImage("test/testFiles/testTwo2.ppm", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable quitquitR = new StringReader("Quit");
    ImageManipulatorControllerImpl controller4 = new ImageManipulatorControllerImpl(
            this.model1, view4, quitquitR);
    controller4.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Quitting program...", quitquit.toString());

    // Do something to quit
    Appendable somethingToQuit = new StringBuilder();
    ImageManipulatorTextView view5 = new ImageManipulatorTextView(somethingToQuit);
    this.model1 = new GeneralImageManipulatorModel();
    try {
      this.model1.loadImage("test/testFiles/testTwo2.ppm", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable somethingToQuitR = new StringReader("help load test/testFiles/testTwo2.ppm file2 " +
            "brighten 30" +
            " file2 brightenfile2 q");
    ImageManipulatorControllerImpl controller5 = new ImageManipulatorControllerImpl(
            this.model1, view5, somethingToQuitR);
    controller5.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "List of commands:\n" +
            "'load file-path file-name' -> loads a file at the path 'file-path' as 'file-name'.\n"
            + "'save file-path file-name' -> saves a file 'file-name' to the location " +
            "'file-path'.\n" +
            "'greyscale component image-name dest-image-name' -> " +
            "creates a greyscaled version of 'image-name' called 'dest-image-name' " +
            "according to 'component'.\n'component' must be one of [red, green, blue, value, " +
            "intensity, luma].\n" +
            "'vertical-flip image-name dest-image-name' -> creates a vertically flipped version " +
            "of 'image-name' called 'dest-image-name'.\n" +
            "'horizontal-flip image-name dest-image-name' -> creates a horizontally flipped " +
            "version of 'image-name' called 'dest-image-name'.\n" +
            "'brighten increment image-name dest-image-name' -> creates a brightened version of" +
            " 'image-name' called 'dest-image-name', where 'increment' determines the how much " +
            "the image is brightened or, if negative, darkened.\n" +
            "'filter filter-type image-name dest-image-name' -> creates a filtered version " +
            "of 'image-name' called 'dest-image-name' using the filter 'filter-type'.\n" +
            " Supported filter types: 'blur', 'sharpen'.\n" +
            "'transform transform-type image-name dest-image-name' -> creates a transformed" +
            " version of 'image-name' called 'dest-image-name' using the transform" +
            " 'transform-type'.\n" +
            " Supported transform types: 'greyscale', 'sepia'.\n" +
            "'q' or 'Q' or 'Quit' or 'quit' -> quits the program\n" +
            "Loaded test/testFiles/testTwo2.ppm as file2\n" +
            "Created brightened version of file2 called brightenfile2\n" +
            "Quitting program...", somethingToQuit.toString());


    // quitting with bad appendable
    ImageManipulatorTextView view10 = new ImageManipulatorTextView(this.badAppendable);
    try {
      this.model1.loadImage("test/testFiles/test2.txt", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable bigQ2 = new StringReader("Q");
    ImageManipulatorControllerImpl controller10 = new ImageManipulatorControllerImpl(
            this.model1, view10, bigQ);
    try {
      controller10.launchProgram();
    } catch (IllegalStateException e) {
      assertEquals("I throw an IOException.", e.getMessage());
    }

  }

  // testing the help command with file-type png
  @Test
  public void testLaunchProgramHelpPNG() {
    // help
    Appendable help1 = new StringBuilder();
    ImageManipulatorTextView view1 = new ImageManipulatorTextView(help1);
    this.model1 = new GeneralImageManipulatorModel();
    try {
      this.model1.loadImage("test/testFiles/testTwo.png", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable helpReadable = new StringReader("help quit");
    ImageManipulatorControllerImpl controller1 = new ImageManipulatorControllerImpl(
            this.model1, view1, helpReadable);
    controller1.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "List of commands:\n" +
            "'load file-path file-name' -> loads a file at the path 'file-path' as " +
            "'file-name'.\n" +
            "'save file-path file-name' -> saves a file 'file-name' to the location " +
            "'file-path'.\n" +
            "'greyscale component image-name dest-image-name' -> " +
            "creates a greyscaled version of 'image-name' called 'dest-image-name' " +
            "according to 'component'.\n'component' must be one of [red, green, blue, value, " +
            "intensity, luma].\n" +
            "'vertical-flip image-name dest-image-name' -> creates a vertically flipped version " +
            "of 'image-name' called 'dest-image-name'.\n" +
            "'horizontal-flip image-name dest-image-name' -> creates a horizontally flipped " +
            "version of 'image-name' called 'dest-image-name'.\n" +
            "'brighten increment image-name dest-image-name' -> creates a brightened version " +
            "of 'image-name' called 'dest-image-name', where 'increment' determines the how " +
            "much the image is brightened or, if negative, darkened.\n" +
            "'filter filter-type image-name dest-image-name' -> creates a filtered" +
            " version of 'image-name' called 'dest-image-name' using the filter 'filter-type'.\n" +
            " Supported filter types: 'blur', 'sharpen'.\n" +
            "'transform transform-type image-name dest-image-name' -> creates a transformed" +
            " version of 'image-name' called 'dest-image-name' using the transform" +
            " 'transform-type'.\n" +
            " Supported transform types: 'greyscale', 'sepia'.\n" +
            "'q' or 'Q' or 'Quit' or 'quit' -> quits the program\n" +
            "Quitting program...", help1.toString());
    // load to help
    Appendable help2 = new StringBuilder();
    ImageManipulatorTextView view2 = new ImageManipulatorTextView(help2);
    this.model1 = new GeneralImageManipulatorModel();
    try {
      this.model1.loadImage("test/testFiles/testTwo.png", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable helpReadable1 = new StringReader("load test/testFiles/testTwo.png file1 help quit");
    ImageManipulatorControllerImpl controller2 = new ImageManipulatorControllerImpl(this.model1,
            view2, helpReadable1);
    controller2.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/testTwo.png as file1\n" +
            "List of commands:\n" +
            "'load file-path file-name' -> loads a file at the path 'file-path' as " +
            "'file-name'.\n" +
            "'save file-path file-name' -> saves a file 'file-name' to the location " +
            "'file-path'.\n" +
            "'greyscale component image-name dest-image-name' -> " +
            "creates a greyscaled version of 'image-name' called 'dest-image-name' " +
            "according to 'component'.\n'component' must be one of [red, green, blue, value, " +
            "intensity, luma].\n" +
            "'vertical-flip image-name dest-image-name' -> creates a vertically flipped version" +
            " of 'image-name' called 'dest-image-name'.\n" +
            "'horizontal-flip image-name dest-image-name' -> creates a horizontally flipped" +
            " version of 'image-name' called 'dest-image-name'.\n" +
            "'brighten increment image-name dest-image-name' -> creates a brightened version" +
            " of 'image-name' called 'dest-image-name', where 'increment' determines the how" +
            " much the image is brightened or, if negative, darkened.\n" +
            "'filter filter-type image-name dest-image-name' -> creates a filtered" +
            " version of 'image-name' called 'dest-image-name' using the filter 'filter-type'.\n" +
            " Supported filter types: 'blur', 'sharpen'.\n" +
            "'transform transform-type image-name dest-image-name' -> creates" +
            " a transformed version of 'image-name' called 'dest-image-name' using the" +
            " transform 'transform-type'.\n" +
            " Supported transform types: 'greyscale', 'sepia'.\n" +
            "'q' or 'Q' or 'Quit' or 'quit' -> quits the program\n" +
            "Quitting program...", help2.toString());

    // help with bad readable
    ImageManipulatorTextView view10 = new ImageManipulatorTextView(badAppendable);
    this.model1 = new GeneralImageManipulatorModel();
    try {
      this.model1.loadImage("test/testFiles/testTwo.png", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable helpReadable10 = new StringReader("help quit");
    ImageManipulatorControllerImpl controller10 = new ImageManipulatorControllerImpl(
            this.model1, view10, helpReadable10);
    try {
      controller10.launchProgram();
      fail("Should have thrown an IOException.");
    } catch (IllegalStateException e) {
      assertEquals("I throw an IOException.", e.getMessage());
    }
  }

  // testing the help command with file-type jpeg
  @Test
  public void testLaunchProgramHelpJPEG() {
    // help
    Appendable help1 = new StringBuilder();
    ImageManipulatorTextView view1 = new ImageManipulatorTextView(help1);
    this.model1 = new GeneralImageManipulatorModel();
    try {
      this.model1.loadImage("test/testFiles/test.jpeg", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable helpReadable = new StringReader("help quit");
    ImageManipulatorControllerImpl controller1 = new ImageManipulatorControllerImpl(
            this.model1, view1, helpReadable);
    controller1.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "List of commands:\n" +
            "'load file-path file-name' -> loads a file at the path 'file-path' as " +
            "'file-name'.\n" +
            "'save file-path file-name' -> saves a file 'file-name' to the location " +
            "'file-path'.\n" +
            "'greyscale component image-name dest-image-name' -> " +
            "creates a greyscaled version of 'image-name' called 'dest-image-name' " +
            "according to 'component'.\n'component' must be one of [red, green, blue, value, " +
            "intensity, luma].\n" +
            "'vertical-flip image-name dest-image-name' -> creates a vertically flipped version " +
            "of 'image-name' called 'dest-image-name'.\n" +
            "'horizontal-flip image-name dest-image-name' -> creates a horizontally flipped " +
            "version of 'image-name' called 'dest-image-name'.\n" +
            "'brighten increment image-name dest-image-name' -> creates a brightened version " +
            "of 'image-name' called 'dest-image-name', where 'increment' determines the how " +
            "much the image is brightened or, if negative, darkened.\n" +
            "'filter filter-type image-name dest-image-name' -> creates a filtered version of" +
            " 'image-name' called 'dest-image-name' using the filter 'filter-type'.\n" +
            " Supported filter types: 'blur', 'sharpen'.\n" +
            "'transform transform-type image-name dest-image-name' -> creates a transformed" +
            " version of 'image-name' called 'dest-image-name' using the" +
            " transform 'transform-type'.\n" +
            " Supported transform types: 'greyscale', 'sepia'.\n" +
            "'q' or 'Q' or 'Quit' or 'quit' -> quits the program\n" +
            "Quitting program...", help1.toString());
    // load to help
    Appendable help2 = new StringBuilder();
    ImageManipulatorTextView view2 = new ImageManipulatorTextView(help2);
    this.model1 = new GeneralImageManipulatorModel();
    try {
      this.model1.loadImage("test/testFiles/test.jpeg", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable helpReadable1 = new StringReader("load test/testFiles/test.jpeg file1 help quit");
    ImageManipulatorControllerImpl controller2 = new ImageManipulatorControllerImpl(this.model1,
            view2, helpReadable1);
    controller2.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/test.jpeg as file1\n" +
            "List of commands:\n" +
            "'load file-path file-name' -> loads a file at the path 'file-path' as " +
            "'file-name'.\n" +
            "'save file-path file-name' -> saves a file 'file-name' to the location " +
            "'file-path'.\n" +
            "'greyscale component image-name dest-image-name' -> " +
            "creates a greyscaled version of 'image-name' called 'dest-image-name' " +
            "according to 'component'.\n'component' must be one of [red, green, blue, value, " +
            "intensity, luma].\n" +
            "'vertical-flip image-name dest-image-name' -> creates a vertically flipped version" +
            " of 'image-name' called 'dest-image-name'.\n" +
            "'horizontal-flip image-name dest-image-name' -> creates a horizontally flipped" +
            " version of 'image-name' called 'dest-image-name'.\n" +
            "'brighten increment image-name dest-image-name' -> creates a brightened version" +
            " of 'image-name' called 'dest-image-name', where 'increment' determines the how" +
            " much the image is brightened or, if negative, darkened.\n" +
            "'filter filter-type image-name dest-image-name' -> creates a" +
            " filtered version of 'image-name' called 'dest-image-name' using" +
            " the filter 'filter-type'.\n" +
            " Supported filter types: 'blur', 'sharpen'.\n" +
            "'transform transform-type image-name dest-image-name' -> creates" +
            " a transformed version of 'image-name' called 'dest-image-name' using " +
            "the transform 'transform-type'.\n" +
            " Supported transform types: 'greyscale', 'sepia'.\n" +
            "'q' or 'Q' or 'Quit' or 'quit' -> quits the program\n" +
            "Quitting program...", help2.toString());

    // help with bad readable
    ImageManipulatorTextView view10 = new ImageManipulatorTextView(badAppendable);
    this.model1 = new GeneralImageManipulatorModel();
    try {
      this.model1.loadImage("test/testFiles/test.jpeg", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable helpReadable10 = new StringReader("help quit");
    ImageManipulatorControllerImpl controller10 = new ImageManipulatorControllerImpl(
            this.model1, view10, helpReadable10);
    try {
      controller10.launchProgram();
      fail("Should have thrown an IOException.");
    } catch (IllegalStateException e) {
      assertEquals("I throw an IOException.", e.getMessage());
    }
  }

  // testing the help command with file-type bmp
  @Test
  public void testLaunchProgramHelpBMP() {
    // help
    Appendable help1 = new StringBuilder();
    ImageManipulatorTextView view1 = new ImageManipulatorTextView(help1);
    this.model1 = new GeneralImageManipulatorModel();
    try {
      this.model1.loadImage("test/testFiles/testTwo2.bmp", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable helpReadable = new StringReader("help quit");
    ImageManipulatorControllerImpl controller1 = new ImageManipulatorControllerImpl(
            this.model1, view1, helpReadable);
    controller1.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "List of commands:\n" +
            "'load file-path file-name' -> loads a file at the path 'file-path' as " +
            "'file-name'.\n" +
            "'save file-path file-name' -> saves a file 'file-name' to the location " +
            "'file-path'.\n" +
            "'greyscale component image-name dest-image-name' -> " +
            "creates a greyscaled version of 'image-name' called 'dest-image-name' " +
            "according to 'component'.\n'component' must be one of [red, green, blue, value, " +
            "intensity, luma].\n" +
            "'vertical-flip image-name dest-image-name' -> creates a vertically flipped version " +
            "of 'image-name' called 'dest-image-name'.\n" +
            "'horizontal-flip image-name dest-image-name' -> creates a horizontally flipped " +
            "version of 'image-name' called 'dest-image-name'.\n" +
            "'brighten increment image-name dest-image-name' -> creates a brightened version " +
            "of 'image-name' called 'dest-image-name', where 'increment' determines the how " +
            "much the image is brightened or, if negative, darkened.\n" +
            "'filter filter-type image-name dest-image-name' -> creates a filtered version of " +
            "'image-name' called 'dest-image-name' using the filter 'filter-type'.\n" +
            " Supported filter types: 'blur', 'sharpen'.\n" +
            "'transform transform-type image-name dest-image-name' -> creates a transformed" +
            " version of 'image-name' called 'dest-image-name' using the transform" +
            " 'transform-type'.\n" +
            " Supported transform types: 'greyscale', 'sepia'.\n" +
            "'q' or 'Q' or 'Quit' or 'quit' -> quits the program\n" +
            "Quitting program...", help1.toString());
    // load to help
    Appendable help2 = new StringBuilder();
    ImageManipulatorTextView view2 = new ImageManipulatorTextView(help2);
    this.model1 = new GeneralImageManipulatorModel();
    try {
      this.model1.loadImage("test/testFiles/testTwo2.bmp", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable helpReadable1 = new StringReader(
            "load test/testFiles/testTwo2.bmp file1 help quit");
    ImageManipulatorControllerImpl controller2 = new ImageManipulatorControllerImpl(this.model1,
            view2, helpReadable1);
    controller2.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/testTwo2.bmp as file1\n" +
            "List of commands:\n" +
            "'load file-path file-name' -> loads a file at the path 'file-path' as " +
            "'file-name'.\n" +
            "'save file-path file-name' -> saves a file 'file-name' to the location " +
            "'file-path'.\n" +
            "'greyscale component image-name dest-image-name' -> " +
            "creates a greyscaled version of 'image-name' called 'dest-image-name' " +
            "according to 'component'.\n'component' must be one of [red, green, blue, value, " +
            "intensity, luma].\n" +
            "'vertical-flip image-name dest-image-name' -> creates a vertically flipped version" +
            " of 'image-name' called 'dest-image-name'.\n" +
            "'horizontal-flip image-name dest-image-name' -> creates a horizontally flipped" +
            " version of 'image-name' called 'dest-image-name'.\n" +
            "'brighten increment image-name dest-image-name' -> creates a brightened version" +
            " of 'image-name' called 'dest-image-name', where 'increment' determines the how" +
            " much the image is brightened or, if negative, darkened.\n" +
            "'filter filter-type image-name dest-image-name' -> creates a filtered version of" +
            " 'image-name' called 'dest-image-name' using the filter 'filter-type'.\n" +
            " Supported filter types: 'blur', 'sharpen'.\n" +
            "'transform transform-type image-name dest-image-name' -> creates a transformed" +
            " version of 'image-name' called 'dest-image-name' using the transform" +
            " 'transform-type'.\n" +
            " Supported transform types: 'greyscale', 'sepia'.\n" +
            "'q' or 'Q' or 'Quit' or 'quit' -> quits the program\n" +
            "Quitting program...", help2.toString());

    // help with bad readable
    ImageManipulatorTextView view10 = new ImageManipulatorTextView(badAppendable);
    this.model1 = new GeneralImageManipulatorModel();
    try {
      this.model1.loadImage("test/testFiles/testTwo2.ppm", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable helpReadable10 = new StringReader("help quit");
    ImageManipulatorControllerImpl controller10 = new ImageManipulatorControllerImpl(
            this.model1, view10, helpReadable10);
    try {
      controller10.launchProgram();
      fail("Should have thrown an IOException.");
    } catch (IllegalStateException e) {
      assertEquals("I throw an IOException.", e.getMessage());
    }
  }

  // testing the help command with file-type PPM
  @Test
  public void testLaunchProgramHelpPPM() {
    // help
    Appendable help1 = new StringBuilder();
    ImageManipulatorTextView view1 = new ImageManipulatorTextView(help1);
    this.model1 = new GeneralImageManipulatorModel();
    try {
      this.model1.loadImage("test/testFiles/testTwo2.ppm", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable helpReadable = new StringReader("help quit");
    ImageManipulatorControllerImpl controller1 = new ImageManipulatorControllerImpl(
            this.model1, view1, helpReadable);
    controller1.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "List of commands:\n" +
            "'load file-path file-name' -> loads a file at the path 'file-path' as " +
            "'file-name'.\n" +
            "'save file-path file-name' -> saves a file 'file-name' to the location " +
            "'file-path'.\n" +
            "'greyscale component image-name dest-image-name' -> " +
            "creates a greyscaled version of 'image-name' called 'dest-image-name' " +
            "according to 'component'.\n'component' must be one of [red, green, blue, value, " +
            "intensity, luma].\n" +
            "'vertical-flip image-name dest-image-name' -> creates a vertically flipped version " +
            "of 'image-name' called 'dest-image-name'.\n" +
            "'horizontal-flip image-name dest-image-name' -> creates a horizontally flipped " +
            "version of 'image-name' called 'dest-image-name'.\n" +
            "'brighten increment image-name dest-image-name' -> creates a brightened version " +
            "of 'image-name' called 'dest-image-name', where 'increment' determines the how " +
            "much the image is brightened or, if negative, darkened.\n" +
            "'filter filter-type image-name dest-image-name' -> creates a filtered" +
            " version of 'image-name' called 'dest-image-name' using the filter 'filter-type'.\n" +
            " Supported filter types: 'blur', 'sharpen'.\n" +
            "'transform transform-type image-name dest-image-name' -> creates a" +
            " transformed version of 'image-name' called 'dest-image-name' using the" +
            " transform 'transform-type'.\n" +
            " Supported transform types: 'greyscale', 'sepia'.\n" +
            "'q' or 'Q' or 'Quit' or 'quit' -> quits the program\n" +
            "Quitting program...", help1.toString());
    // load to help
    Appendable help2 = new StringBuilder();
    ImageManipulatorTextView view2 = new ImageManipulatorTextView(help2);
    this.model1 = new GeneralImageManipulatorModel();
    try {
      this.model1.loadImage("test/testFiles/testTwo2.ppm", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable helpReadable1 = new StringReader(
            "load test/testFiles/testTwo2.ppm file1 help quit");
    ImageManipulatorControllerImpl controller2 = new ImageManipulatorControllerImpl(this.model1,
            view2, helpReadable1);
    controller2.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/testTwo2.ppm as file1\n" +
            "List of commands:\n" +
            "'load file-path file-name' -> loads a file at the path 'file-path' as " +
            "'file-name'.\n" +
            "'save file-path file-name' -> saves a file 'file-name' to the location " +
            "'file-path'.\n" +
            "'greyscale component image-name dest-image-name' -> " +
            "creates a greyscaled version of 'image-name' called 'dest-image-name' " +
            "according to 'component'.\n'component' must be one of [red, green, blue, value, " +
            "intensity, luma].\n" +
            "'vertical-flip image-name dest-image-name' -> creates a vertically flipped version" +
            " of 'image-name' called 'dest-image-name'.\n" +
            "'horizontal-flip image-name dest-image-name' -> creates a horizontally flipped" +
            " version of 'image-name' called 'dest-image-name'.\n" +
            "'brighten increment image-name dest-image-name' -> creates a brightened version" +
            " of 'image-name' called 'dest-image-name', where 'increment' determines the how" +
            " much the image is brightened or, if negative, darkened.\n" +
            "'filter filter-type image-name dest-image-name' -> creates a filtered version" +
            " of 'image-name' called 'dest-image-name' using the filter 'filter-type'.\n" +
            " Supported filter types: 'blur', 'sharpen'.\n" +
            "'transform transform-type image-name dest-image-name' -> creates a " +
            "transformed version of 'image-name' called 'dest-image-name' using the" +
            " transform 'transform-type'.\n" +
            " Supported transform types: 'greyscale', 'sepia'.\n" +
            "'q' or 'Q' or 'Quit' or 'quit' -> quits the program\n" +
            "Quitting program...", help2.toString());
    // save to help
    Appendable help3 = new StringBuilder();
    ImageManipulatorTextView view3 = new ImageManipulatorTextView(help3);
    this.model1 = new GeneralImageManipulatorModel();
    try {
      this.model1.loadImage("test/testFiles/testTwo2.ppm", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable helpReadable2 = new StringReader(
            "save test/testFiles/testTwo2.ppm file1 help quit");
    ImageManipulatorControllerImpl controller3 = new ImageManipulatorControllerImpl(
            this.model1, view3, helpReadable2);
    controller3.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Saved file1 to test/testFiles/testTwo2.ppm\n" +
            "List of commands:\n" +
            "'load file-path file-name' -> loads a file at the path 'file-path' as " +
            "'file-name'.\n" +
            "'save file-path file-name' -> saves a file 'file-name' to the location " +
            "'file-path'.\n" +
            "'greyscale component image-name dest-image-name' -> " +
            "creates a greyscaled version of 'image-name' called 'dest-image-name' " +
            "according to 'component'.\n'component' must be one of [red, green, blue, value, " +
            "intensity, luma].\n" +
            "'vertical-flip image-name dest-image-name' -> creates a vertically flipped version" +
            " of 'image-name' called 'dest-image-name'.\n" +
            "'horizontal-flip image-name dest-image-name' -> creates a horizontally flipped" +
            " version of 'image-name' called 'dest-image-name'.\n" +
            "'brighten increment image-name dest-image-name' -> creates a brightened version" +
            " of 'image-name' called 'dest-image-name', where 'increment' determines the how" +
            " much the image is brightened or, if negative, darkened.\n" +
            "'filter filter-type image-name dest-image-name' -> creates a filtered" +
            " version of 'image-name' called 'dest-image-name' using the filter 'filter-type'.\n" +
            " Supported filter types: 'blur', 'sharpen'.\n" +
            "'transform transform-type image-name dest-image-name' -> creates a transformed" +
            " version of 'image-name' called 'dest-image-name' using the transform" +
            " 'transform-type'.\n" +
            " Supported transform types: 'greyscale', 'sepia'.\n" +
            "'q' or 'Q' or 'Quit' or 'quit' -> quits the program\n" +
            "Quitting program...", help3.toString());
    // operation to help
    Appendable help4 = new StringBuilder();
    ImageManipulatorTextView view4 = new ImageManipulatorTextView(help4);
    this.model1 = new GeneralImageManipulatorModel();
    try {
      this.model1.loadImage("test/testFiles/testTwo2.ppm", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable helpReadable3 = new StringReader("load test/testFiles/testTwo2.ppm file1 " +
            "brighten 20 file1 " +
            "file1brighten help quit");
    ImageManipulatorControllerImpl controller4 = new ImageManipulatorControllerImpl(
            this.model1, view4, helpReadable3);
    controller4.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/testTwo2.ppm as file1\n" +
            "Created brightened version of file1 called file1brighten\n" +
            "List of commands:\n" +
            "'load file-path file-name' -> loads a file at the path 'file-path' as " +
            "'file-name'.\n" +
            "'save file-path file-name' -> saves a file 'file-name' to the location " +
            "'file-path'.\n" +
            "'greyscale component image-name dest-image-name' -> " +
            "creates a greyscaled version of 'image-name' called 'dest-image-name' " +
            "according to 'component'.\n'component' must be one of [red, green, blue, value, " +
            "intensity, luma].\n" +
            "'vertical-flip image-name dest-image-name' -> creates a vertically flipped version" +
            " of 'image-name' called 'dest-image-name'.\n" +
            "'horizontal-flip image-name dest-image-name' -> creates a horizontally flipped" +
            " version of 'image-name' called 'dest-image-name'.\n" +
            "'brighten increment image-name dest-image-name' -> creates a brightened version" +
            " of 'image-name' called 'dest-image-name', where 'increment' determines the how" +
            " much the image is brightened or, if negative, darkened.\n" +
            "'filter filter-type image-name dest-image-name' -> creates a filtered" +
            " version of 'image-name' called 'dest-image-name' using the filter 'filter-type'.\n" +
            " Supported filter types: 'blur', 'sharpen'.\n" +
            "'transform transform-type image-name dest-image-name' -> creates a" +
            " transformed version of 'image-name' called 'dest-image-name' using the" +
            " transform 'transform-type'.\n" +
            " Supported transform types: 'greyscale', 'sepia'.\n" +
            "'q' or 'Q' or 'Quit' or 'quit' -> quits the program\n" +
            "Quitting program...", help4.toString());

    // help with bad readable
    ImageManipulatorTextView view10 = new ImageManipulatorTextView(badAppendable);
    this.model1 = new GeneralImageManipulatorModel();
    try {
      this.model1.loadImage("test/testFiles/testTwo2.ppm", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable helpReadable10 = new StringReader("help quit");
    ImageManipulatorControllerImpl controller10 = new ImageManipulatorControllerImpl(
            this.model1, view10, helpReadable10);
    try {
      controller10.launchProgram();
      fail("Should have thrown an IOException.");
    } catch (IllegalStateException e) {
      assertEquals("I throw an IOException.", e.getMessage());
    }
  }


  // test the load command with file-type PNG
  @Test
  public void testLaunchProgramLoadPNG() {
    // default load
    Appendable appendable1 = new StringBuilder();
    ImageManipulatorTextView view1 = new ImageManipulatorTextView(appendable1);
    this.model1 = new GeneralImageManipulatorModel();
    try {
      this.model1.loadImage("test/testFiles/testTwo.png", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable1 = new StringReader("load test/testFiles/testTwo.png file1 quit");
    ImageManipulatorControllerImpl controller1 = new ImageManipulatorControllerImpl(
            this.model1, view1, readable1);
    controller1.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/testTwo.png as file1\n" +
            "Quitting program...", appendable1.toString());

    // load with bad appendable
    ImageManipulatorTextView view10 = new ImageManipulatorTextView(badAppendable);
    this.model1 = new GeneralImageManipulatorModel();
    try {
      this.model1.loadImage("test/testFiles/testTwo.png", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable10 = new StringReader("load test/testFiles/testTwo.png file1 quit");
    ImageManipulatorControllerImpl controller10 = new ImageManipulatorControllerImpl(
            this.model1, view10, readable10);
    try {
      controller10.launchProgram();
    } catch (IllegalStateException e) {
      assertEquals("I throw an IOException.", e.getMessage());
    }

    // tests load override
    HashMap<String, Pixel[][]> testMap = new HashMap<String, Pixel[][]>();
    GeneralImageManipulatorModel override = new GeneralImageManipulatorModel(testMap);
    Appendable appendable = new StringBuilder();
    ImageManipulatorTextView view = new ImageManipulatorTextView(appendable);
    Readable readable = new StringReader("load test/testFiles/testTwo.png test load" +
            " test/testFiles/testOne.png test q");
    ImageManipulatorController controller = new ImageManipulatorControllerImpl(
            override, view, readable);
    controller.launchProgram();

    Pixel[][] temp = testMap.get("test");

    assertEquals(new Pixel(255, 0, 125), temp[0][0]);
    assertEquals(new Pixel(255, 0, 255), temp[0][1]);
    assertEquals(new Pixel(255, 125, 0), temp[0][2]);
    assertEquals(new Pixel(255, 255, 0), temp[1][0]);
    assertEquals(new Pixel(0, 255, 0), temp[1][1]);
    assertEquals(new Pixel(125, 0, 255), temp[1][2]);

  }

  // tests the load command with file-type JPEG
  @Test
  public void testLaunchProgramLoadJPEG() {
    // default load
    Appendable appendable1 = new StringBuilder();
    ImageManipulatorTextView view1 = new ImageManipulatorTextView(appendable1);
    this.model1 = new GeneralImageManipulatorModel();
    try {
      this.model1.loadImage("test/testFiles/test.jpeg", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable1 = new StringReader("load test/testFiles/test.jpeg file1 quit");
    ImageManipulatorControllerImpl controller1 = new ImageManipulatorControllerImpl(
            this.model1, view1, readable1);
    controller1.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/test.jpeg as file1\n" +
            "Quitting program...", appendable1.toString());


    // load with bad appendable
    ImageManipulatorTextView view10 = new ImageManipulatorTextView(badAppendable);
    this.model1 = new GeneralImageManipulatorModel();
    try {
      this.model1.loadImage("test/testFiles/test.jpeg", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable10 = new StringReader("load test/testFiles/test.jpeg file1 quit");
    ImageManipulatorControllerImpl controller10 = new ImageManipulatorControllerImpl(
            this.model1, view10, readable10);
    try {
      controller10.launchProgram();
    } catch (IllegalStateException e) {
      assertEquals("I throw an IOException.", e.getMessage());
    }

    // tests load override
    HashMap<String, Pixel[][]> testMap = new HashMap<String, Pixel[][]>();
    GeneralImageManipulatorModel override = new GeneralImageManipulatorModel(testMap);
    Appendable appendable = new StringBuilder();
    ImageManipulatorTextView view = new ImageManipulatorTextView(appendable);
    Readable readable = new StringReader("load test/testFiles/drake.jpeg test load" +
            " test/testFiles/test.jpeg test q");
    ImageManipulatorController controller = new ImageManipulatorControllerImpl(
            override, view, readable);
    controller.launchProgram();

    Pixel[][] temp = testMap.get("test");

    assertEquals(new Pixel(145, 72, 39), temp[0][0]);
    assertEquals(new Pixel(176, 103, 70), temp[0][1]);
    assertEquals(new Pixel(255, 197, 164), temp[1][0]);
    assertEquals(new Pixel(206, 133, 100), temp[1][1]);

  }

  // tests the load command with file-type bmp
  @Test
  public void testLaunchProgramLoadBMP() {
    // default load
    Appendable appendable1 = new StringBuilder();
    ImageManipulatorTextView view1 = new ImageManipulatorTextView(appendable1);
    this.model1 = new GeneralImageManipulatorModel();
    try {
      this.model1.loadImage("test/testFiles/testTwo2.bmp", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable1 = new StringReader("load test/testFiles/testTwo2.bmp file1 quit");
    ImageManipulatorControllerImpl controller1 = new ImageManipulatorControllerImpl(
            this.model1, view1, readable1);
    controller1.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/testTwo2.bmp as file1\n" +
            "Quitting program...", appendable1.toString());


    // load with bad appendable
    ImageManipulatorTextView view10 = new ImageManipulatorTextView(badAppendable);
    this.model1 = new GeneralImageManipulatorModel();
    try {
      this.model1.loadImage("test/testFiles/testTwo2.bmp", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable10 = new StringReader("load test/testFiles/testTwo2.bmp file1 quit");
    ImageManipulatorControllerImpl controller10 = new ImageManipulatorControllerImpl(
            this.model1, view10, readable10);
    try {
      controller10.launchProgram();
    } catch (IllegalStateException e) {
      assertEquals("I throw an IOException.", e.getMessage());
    }

    // tests load override
    HashMap<String, Pixel[][]> testMap = new HashMap<String, Pixel[][]>();
    GeneralImageManipulatorModel override = new GeneralImageManipulatorModel(testMap);
    Appendable appendable = new StringBuilder();
    ImageManipulatorTextView view = new ImageManipulatorTextView(appendable);
    Readable readable = new StringReader("load test/testFiles/testTwo2.bmp test load" +
            " test/testFiles/testOne.bmp test q");
    ImageManipulatorController controller = new ImageManipulatorControllerImpl(
            override, view, readable);
    controller.launchProgram();

    Pixel[][] temp = testMap.get("test");

    assertEquals(new Pixel(255, 0, 125), temp[0][0]);
    assertEquals(new Pixel(255, 0, 255), temp[0][1]);
    assertEquals(new Pixel(255, 125, 0), temp[0][2]);
    assertEquals(new Pixel(255, 255, 0), temp[1][0]);
    assertEquals(new Pixel(0, 255, 0), temp[1][1]);
    assertEquals(new Pixel(125, 0, 255), temp[1][2]);

  }

  // tests the load command for file-type ppm
  @Test
  public void testLaunchProgramLoadPPM() {
    // default load
    Appendable appendable1 = new StringBuilder();
    ImageManipulatorTextView view1 = new ImageManipulatorTextView(appendable1);
    this.model1 = new GeneralImageManipulatorModel();
    try {
      this.model1.loadImage("test/testFiles/testTwo2.ppm", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable1 = new StringReader("load test/testFiles/testTwo2.ppm file1 quit");
    ImageManipulatorControllerImpl controller1 = new ImageManipulatorControllerImpl(
            this.model1, view1, readable1);
    controller1.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/testTwo2.ppm as file1\n" +
            "Quitting program...", appendable1.toString());

    // load non-existent file
    Appendable appendable2 = new StringBuilder();
    ImageManipulatorTextView view2 = new ImageManipulatorTextView(appendable2);
    this.model1 = new GeneralImageManipulatorModel();
    try {
      this.model1.loadImage("test/testFiles/testTwo2.ppm", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable2 = new StringReader("load noexist file2");
    ImageManipulatorControllerImpl controller2 = new ImageManipulatorControllerImpl(
            this.model1, view2, readable2);
    try {
      controller2.launchProgram();
    } catch (IllegalStateException e) {
      // fails as the file being loaded doesn't exist.
    }

    // load to save to load
    Appendable appendable4 = new StringBuilder();
    ImageManipulatorTextView view4 = new ImageManipulatorTextView(appendable4);
    this.model1 = new GeneralImageManipulatorModel();
    try {
      this.model1.loadImage("test/testFiles/testTwo2.ppm", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable4 = new StringReader("load test/testFiles/testTwo2.ppm file1 " +
            "save test/testFiles/file1saved.ppm file1 " +
            "load test/testFiles/file1saved.ppm file1savedloaded quit");
    ImageManipulatorControllerImpl controller4 = new ImageManipulatorControllerImpl(
            this.model1, view4, readable4);

    controller4.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/testTwo2.ppm as file1\n" +
            "Saved file1 to test/testFiles/file1saved.ppm\n" +
            "Loaded test/testFiles/file1saved.ppm as file1savedloaded\n" +
            "Quitting program...", appendable4.toString());

    // load with bad appendable
    ImageManipulatorTextView view10 = new ImageManipulatorTextView(badAppendable);
    this.model1 = new GeneralImageManipulatorModel();
    try {
      this.model1.loadImage("test/testFiles/testTwo2.ppm", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable10 = new StringReader("load test/testFiles/testTwo2.ppm file1 quit");
    ImageManipulatorControllerImpl controller10 = new ImageManipulatorControllerImpl(
            this.model1, view10, readable10);
    try {
      controller10.launchProgram();
    } catch (IllegalStateException e) {
      assertEquals("I throw an IOException.", e.getMessage());
    }

    // tests load override
    HashMap<String, Pixel[][]> testMap = new HashMap<String, Pixel[][]>();
    GeneralImageManipulatorModel override = new GeneralImageManipulatorModel(testMap);
    Appendable appendable = new StringBuilder();
    ImageManipulatorTextView view = new ImageManipulatorTextView(appendable);
    Readable readable = new StringReader("load test/testFiles/testTwo2.ppm test load" +
            " test/testFiles/testOne.ppm test q");
    ImageManipulatorController controller = new ImageManipulatorControllerImpl(
            override, view, readable);
    controller.launchProgram();

    Pixel[][] temp = testMap.get("test");

    assertEquals(new Pixel(255, 0, 125), temp[0][0]);
    assertEquals(new Pixel(255, 0, 255), temp[0][1]);
    assertEquals(new Pixel(255, 125, 0), temp[0][2]);
    assertEquals(new Pixel(255, 255, 0), temp[1][0]);
    assertEquals(new Pixel(0, 255, 0), temp[1][1]);
    assertEquals(new Pixel(125, 0, 255), temp[1][2]);

  }

  // tests the save command with file-type png
  @Test
  public void testLaunchProgramSavePNG() {
    Appendable appendable1 = new StringBuilder();
    ImageManipulatorTextView view1 = new ImageManipulatorTextView(appendable1);
    this.model1 = new GeneralImageManipulatorModel();
    try {
      this.model1.loadImage("test/testFiles/testTwo.png", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable1 = new StringReader("load test/testFiles/testTwo.png file1 " +
            "save test/testFiles/file1saved.png file1 quit");
    ImageManipulatorControllerImpl controller1 = new ImageManipulatorControllerImpl(
            this.model1, view1, readable1);
    controller1.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/testTwo.png as file1\n" +
            "Saved file1 to test/testFiles/file1saved.png\n" +
            "Quitting program...", appendable1.toString());
    // save after one operation
    Appendable appendable2 = new StringBuilder();
    ImageManipulatorTextView view2 = new ImageManipulatorTextView(appendable2);
    this.model1 = new GeneralImageManipulatorModel();
    try {
      this.model1.loadImage("test/testFiles/testTwo.png", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable2 = new StringReader("load test/testFiles/testTwo.png file1" +
            " greyscale luma file1 greyscaleFile1" +
            " save test/testFiles/file1GreyscaleSaved.png greyscaleFile1 quit");
    ImageManipulatorControllerImpl controller2 = new ImageManipulatorControllerImpl(
            this.model1, view2, readable2);
    controller2.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/testTwo.png as file1\n" +
            "Created greyscaled version of file1 called greyscaleFile1\n" +
            "Saved greyscaleFile1 to test/testFiles/file1GreyscaleSaved.png\n" +
            "Quitting program...", appendable2.toString());

    // saving with bad appendable
    ImageManipulatorTextView view10 = new ImageManipulatorTextView(badAppendable);
    this.model1 = new GeneralImageManipulatorModel();
    try {
      this.model1.loadImage("test/testFiles/testTwo.png", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable10 = new StringReader("load test/testFiles/testTwo.png file1 " +
            "save test/testFiles/file1saved.png file1 quit");
    ImageManipulatorControllerImpl controller10 = new ImageManipulatorControllerImpl(
            this.model1, view10, readable10);
    try {
      controller10.launchProgram();
      fail("Should throw exception.");
    } catch (IllegalStateException e) {
      assertEquals("I throw an IOException.", e.getMessage());
    }
  }


  // tests the save command for file-type jpeg
  @Test
  public void testLaunchProgramSaveJPEG() {
    Appendable appendable1 = new StringBuilder();
    ImageManipulatorTextView view1 = new ImageManipulatorTextView(appendable1);
    this.model1 = new GeneralImageManipulatorModel();
    try {
      this.model1.loadImage("test/testFiles/test.jpeg", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable1 = new StringReader("load test/testFiles/test.jpeg file1 " +
            "save test/testFiles/file1saved.jpeg file1 quit");
    ImageManipulatorControllerImpl controller1 = new ImageManipulatorControllerImpl(
            this.model1, view1, readable1);
    controller1.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/test.jpeg as file1\n" +
            "Saved file1 to test/testFiles/file1saved.jpeg\n" +
            "Quitting program...", appendable1.toString());
    // save after one operation
    Appendable appendable2 = new StringBuilder();
    ImageManipulatorTextView view2 = new ImageManipulatorTextView(appendable2);
    this.model1 = new GeneralImageManipulatorModel();
    try {
      this.model1.loadImage("test/testFiles/test.jpeg", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable2 = new StringReader("load test/testFiles/test.jpeg file1" +
            " greyscale luma file1 greyscaleFile1" +
            " save test/testFiles/file1GreyscaleSaved.jpeg greyscaleFile1 quit");
    ImageManipulatorControllerImpl controller2 = new ImageManipulatorControllerImpl(
            this.model1, view2, readable2);
    controller2.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/test.jpeg as file1\n" +
            "Created greyscaled version of file1 called greyscaleFile1\n" +
            "Saved greyscaleFile1 to test/testFiles/file1GreyscaleSaved.jpeg\n" +
            "Quitting program...", appendable2.toString());

    // saving with bad appendable
    ImageManipulatorTextView view10 = new ImageManipulatorTextView(badAppendable);
    this.model1 = new GeneralImageManipulatorModel();
    try {
      this.model1.loadImage("test/testFiles/test.jpeg", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable10 = new StringReader("load test/testFiles/test.jpeg file1 " +
            "save test/testFiles/file1saved.bmp file1 quit");
    ImageManipulatorControllerImpl controller10 = new ImageManipulatorControllerImpl(
            this.model1, view10, readable10);
    try {
      controller10.launchProgram();
      fail("Should throw exception.");
    } catch (IllegalStateException e) {
      assertEquals("I throw an IOException.", e.getMessage());
    }
  }

  // tests the save command with file-type bmp
  @Test
  public void testLaunchProgramSaveBMP() {
    Appendable appendable1 = new StringBuilder();
    ImageManipulatorTextView view1 = new ImageManipulatorTextView(appendable1);
    this.model1 = new GeneralImageManipulatorModel();
    try {
      this.model1.loadImage("test/testFiles/testTwo2.bmp", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable1 = new StringReader("load test/testFiles/testTwo2.bmp file1 " +
            "save test/testFiles/file1saved.bmp file1 quit");
    ImageManipulatorControllerImpl controller1 = new ImageManipulatorControllerImpl(
            this.model1, view1, readable1);
    controller1.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/testTwo2.bmp as file1\n" +
            "Saved file1 to test/testFiles/file1saved.bmp\n" +
            "Quitting program...", appendable1.toString());
    // save after one operation
    Appendable appendable2 = new StringBuilder();
    ImageManipulatorTextView view2 = new ImageManipulatorTextView(appendable2);
    this.model1 = new GeneralImageManipulatorModel();
    try {
      this.model1.loadImage("test/testFiles/testTwo2.bmp", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable2 = new StringReader("load test/testFiles/testTwo2.bmp file1" +
            " greyscale luma file1 greyscaleFile1" +
            " save test/testFiles/file1GreyscaleSaved.bmp greyscaleFile1 quit");
    ImageManipulatorControllerImpl controller2 = new ImageManipulatorControllerImpl(
            this.model1, view2, readable2);
    controller2.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/testTwo2.bmp as file1\n" +
            "Created greyscaled version of file1 called greyscaleFile1\n" +
            "Saved greyscaleFile1 to test/testFiles/file1GreyscaleSaved.bmp\n" +
            "Quitting program...", appendable2.toString());

    // saving with bad appendable
    ImageManipulatorTextView view10 = new ImageManipulatorTextView(badAppendable);
    this.model1 = new GeneralImageManipulatorModel();
    try {
      this.model1.loadImage("test/testFiles/testTwo2.bmp", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable10 = new StringReader("load test/testFiles/testTwo2.bmp file1 " +
            "save test/testFiles/file1saved.bmp file1 quit");
    ImageManipulatorControllerImpl controller10 = new ImageManipulatorControllerImpl(
            this.model1, view10, readable10);
    try {
      controller10.launchProgram();
      fail("Should throw exception.");
    } catch (IllegalStateException e) {
      assertEquals("I throw an IOException.", e.getMessage());
    }
  }

  // tests the save command with file-type ppm
  @Test
  public void testLaunchProgramSavePPM() {
    Appendable appendable1 = new StringBuilder();
    ImageManipulatorTextView view1 = new ImageManipulatorTextView(appendable1);
    this.model1 = new GeneralImageManipulatorModel();
    try {
      this.model1.loadImage("test/testFiles/testTwo2.ppm", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable1 = new StringReader("load test/testFiles/testTwo2.ppm file1 " +
            "save test/testFiles/file1saved file1 quit");
    ImageManipulatorControllerImpl controller1 = new ImageManipulatorControllerImpl(
            this.model1, view1, readable1);
    controller1.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/testTwo2.ppm as file1\n" +
            "Saved file1 to test/testFiles/file1saved\n" +
            "Quitting program...", appendable1.toString());
    // save after one operation
    Appendable appendable2 = new StringBuilder();
    ImageManipulatorTextView view2 = new ImageManipulatorTextView(appendable2);
    this.model1 = new GeneralImageManipulatorModel();
    try {
      this.model1.loadImage("test/testFiles/testTwo2.ppm", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable2 = new StringReader("load test/testFiles/testTwo2.ppm file1" +
            " greyscale luma file1 greyscaleFile1" +
            " save test/testFiles/file1GreyscaleSaved.ppm greyscaleFile1 quit");
    ImageManipulatorControllerImpl controller2 = new ImageManipulatorControllerImpl(
            this.model1, view2, readable2);
    controller2.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/testTwo2.ppm as file1\n" +
            "Created greyscaled version of file1 called greyscaleFile1\n" +
            "Saved greyscaleFile1 to test/testFiles/file1GreyscaleSaved.ppm\n" +
            "Quitting program...", appendable2.toString());

    // save a non-existent file
    Appendable appendable3 = new StringBuilder();
    ImageManipulatorTextView view3 = new ImageManipulatorTextView(appendable3);
    this.model1 = new GeneralImageManipulatorModel();
    try {
      this.model1.loadImage("test/testFiles/testTwo2.ppm", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable3 = new StringReader("save saved nonexistent q");
    ImageManipulatorControllerImpl controller3 = new ImageManipulatorControllerImpl(
            this.model1, view3, readable3);
    try {
      controller3.launchProgram();
      assertEquals("Welcome to the ImageManipulation software!\n" +
              "Type 'help' to see a list of commands.\n" +
              "Image to save could not be found, please try again.\n" +
              "Quitting program...", appendable3.toString());
    } catch (Exception e) {
      fail("Shouldn't throw an exception.");
    }

    // saving with bad appendable
    ImageManipulatorTextView view10 = new ImageManipulatorTextView(badAppendable);
    this.model1 = new GeneralImageManipulatorModel();
    try {
      this.model1.loadImage("test/testFiles/testTwo2.ppm", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable10 = new StringReader("load test/testFiles/testTwo2.ppm file1 " +
            "save test/testFiles/file1saved.ppm file1 quit");
    ImageManipulatorControllerImpl controller10 = new ImageManipulatorControllerImpl(
            this.model1, view10, readable10);
    try {
      controller10.launchProgram();
      fail("Should throw exception.");
    } catch (IllegalStateException e) {
      assertEquals("I throw an IOException.", e.getMessage());
    }
  }

  // tests the horizontal-flip command for file-type png
  @Test
  public void testLaunchProgramCommandsHorizontalFlipPNG() {
    // horizontal flip
    Appendable appendable9 = new StringBuilder();
    ImageManipulatorTextView view9 = new ImageManipulatorTextView(appendable9);
    try {
      this.model1.loadImage("test/testFiles/testTwo.png", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable9 = new StringReader("load test/testFiles/testTwo.png" +
            " file1 horizontal-flip" +
            " file1 file1horizontal q");
    ImageManipulatorControllerImpl controller9 = new ImageManipulatorControllerImpl(
            this.model1, view9, readable9);
    controller9.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/testTwo.png as file1\n" +
            "Created horizontally flipped version of file1 called file1horizontal\n" +
            "Quitting program...", appendable9.toString());

    ImageManipulatorTextView view90 = new ImageManipulatorTextView(this.badAppendable);
    try {
      this.model1.loadImage("test/testFiles/testTwo.png", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable90 = new StringReader("load test/testFiles/testTwo.png file1 " +
            "horizontal-flip" +
            " file1 file1horizontal q");
    ImageManipulatorControllerImpl controller90 = new ImageManipulatorControllerImpl(
            this.model1, view90, readable90);
    try {
      controller90.launchProgram();
      fail("Should have thrown an Exception");
    } catch (IllegalStateException e) {
      assertEquals("I throw an IOException.", e.getMessage());
    }
    // horizontal-flip to brighten
    Appendable appendable10 = new StringBuilder();
    ImageManipulatorTextView view10 = new ImageManipulatorTextView(appendable10);
    try {
      this.model1.loadImage("test/testFiles/testTwo.png", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable10 = new StringReader("load test/testFiles/testOne.png file1 " +
            "horizontal-flip" +
            " file1 file1horizontal brighten 10 file1horizontal file1bh " +
            "save test/testFiles/file1bhsaved.png file1bh q");
    ImageManipulatorControllerImpl controller10 = new ImageManipulatorControllerImpl(
            this.model1, view10, readable10);
    controller10.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/testOne.png as file1\n" +
            "Created horizontally flipped version of file1 called file1horizontal\n" +
            "Created brightened version of file1horizontal called file1bh\n" +
            "Saved file1bh to test/testFiles/file1bhsaved.png\n" +
            "Quitting program...", appendable10.toString());
  }

  // tests the vertical-flip command for file-type png
  @Test
  public void testLaunchProgramCommandsVerticalFlipPNG() {
    // vertical flip
    Appendable appendable8 = new StringBuilder();
    ImageManipulatorTextView view8 = new ImageManipulatorTextView(appendable8);
    try {
      this.model1.loadImage("test/testFiles/testTwo.png", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable8 = new StringReader("load test/testFiles/testTwo.png file1 vertical-flip" +
            " file1 file1vertical q");
    ImageManipulatorControllerImpl controller8 = new ImageManipulatorControllerImpl(
            this.model1, view8, readable8);
    controller8.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/testTwo.png as file1\n" +
            "Created vertically flipped version of file1 called file1vertical\n" +
            "Quitting program...", appendable8.toString());

    ImageManipulatorTextView view80 = new ImageManipulatorTextView(this.badAppendable);
    try {
      this.model1.loadImage("test/testFiles/testTwo.png", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable80 = new StringReader("load test/testFiles/testTwo.png file1 " +
            "vertical-flip" +
            " file1 file1vertical q");
    ImageManipulatorControllerImpl controller80 = new ImageManipulatorControllerImpl(
            this.model1, view80, readable80);
    try {
      controller80.launchProgram();
      fail("Should throw an exception");
    } catch (IllegalStateException e) {
      assertEquals("I throw an IOException.", e.getMessage());
    }
  }

  // tests the brighten command for file-type png
  @Test
  public void testLaunchProgramCommandsBrightenPNG() {
    // brighten
    Appendable appendable7 = new StringBuilder();
    ImageManipulatorTextView view7 = new ImageManipulatorTextView(appendable7);
    try {
      this.model1.loadImage("test/testFiles/testTwo.png", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable7 = new StringReader("load test/testFiles/testTwo.png file1 brighten 10" +
            " file1 file1brighten q");
    ImageManipulatorControllerImpl controller7 = new ImageManipulatorControllerImpl(
            this.model1, view7, readable7);
    controller7.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/testTwo.png as file1\n" +
            "Created brightened version of file1 called file1brighten\n" +
            "Quitting program...", appendable7.toString());

    // brighten
    ImageManipulatorTextView view70 = new ImageManipulatorTextView(badAppendable);
    try {
      this.model1.loadImage("test/testFiles/testTwo.png", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable70 = new StringReader("load test/testFiles/testTwo.png file1 brighten 10" +
            " file1 file1brighten q");
    ImageManipulatorControllerImpl controller70 = new ImageManipulatorControllerImpl(
            this.model1, view70, readable70);

    try {
      controller70.launchProgram();
      fail("Should throw an exception.");
    } catch (IllegalStateException e) {
      assertEquals("I throw an IOException.", e.getMessage());
    }

  }

  // tests the greyscale luma command for file-type png
  @Test
  public void testLaunchProgramCommandsGreyscaleLumaPNG() {

    // Greyscale luma
    Appendable appendable6 = new StringBuilder();
    ImageManipulatorTextView view6 = new ImageManipulatorTextView(appendable6);
    try {
      this.model1.loadImage("test/testFiles/testTwo.png", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable6 = new StringReader("load test/testFiles/testTwo.png file1 " +
            "greyscale luma" +
            " file1 file1luma q");
    ImageManipulatorControllerImpl controller6 = new ImageManipulatorControllerImpl(
            this.model1, view6, readable6);
    controller6.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/testTwo.png as file1\n" +
            "Created greyscaled version of file1 called file1luma\n" +
            "Quitting program...", appendable6.toString());
  }

  // tests the greyscale intensity command for png
  @Test
  public void testLaunchProgramCommandsGreyscaleIntensityPNG() {
    // Greyscale intensity
    Appendable appendable5 = new StringBuilder();
    ImageManipulatorTextView view5 = new ImageManipulatorTextView(appendable5);
    try {
      this.model1.loadImage("test/testFiles/testTwo.png", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable5 = new StringReader("load test/testFiles/testTwo.png file1 " +
            "greyscale intensity" +
            " file1 file1intensity q");
    ImageManipulatorControllerImpl controller5 = new ImageManipulatorControllerImpl(
            this.model1, view5, readable5);
    controller5.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/testTwo.png as file1\n" +
            "Created greyscaled version of file1 called file1intensity\n" +
            "Quitting program...", appendable5.toString());
  }

  //tests the greyscale value command for file-type png
  @Test
  public void testLaunchProgramCommandsGreyscaleValuePNG() {

    // Greyscale value
    Appendable appendable4 = new StringBuilder();
    ImageManipulatorTextView view4 = new ImageManipulatorTextView(appendable4);
    try {
      this.model1.loadImage("test/testFiles/testTwo.png", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable4 = new StringReader("load test/testFiles/testTwo.png file1" +
            " greyscale value" +
            " file1 file1value q");
    ImageManipulatorControllerImpl controller4 = new ImageManipulatorControllerImpl(
            this.model1, view4, readable4);
    controller4.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/testTwo.png as file1\n" +
            "Created greyscaled version of file1 called file1value\n" +
            "Quitting program...", appendable4.toString());

  }

  // tests the greyscale blue command for file-type png
  @Test
  public void testLaunchProgramCommandsGreyscaleBluePNG() {
    // Greyscale blue
    Appendable appendable3 = new StringBuilder();
    ImageManipulatorTextView view3 = new ImageManipulatorTextView(appendable3);
    try {
      this.model1.loadImage("test/testFiles/testTwo.png", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable3 = new StringReader("load test/testFiles/testTwo.png file1 " +
            "greyscale blue file1 file1blue q");
    ImageManipulatorControllerImpl controller3 = new ImageManipulatorControllerImpl(
            this.model1, view3, readable3);
    controller3.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/testTwo.png as file1\n" +
            "Created greyscaled version of file1 called file1blue\n" +
            "Quitting program...", appendable3.toString());
  }

  //tests the greyscale green command for file-type png
  @Test
  public void testLaunchProgramCommandsGreyscaleGreenPNG() {
    // Greyscale green
    Appendable appendable2 = new StringBuilder();
    ImageManipulatorTextView view2 = new ImageManipulatorTextView(appendable2);
    try {
      this.model1.loadImage("test/testFiles/testTwo.png", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable2 = new StringReader("load test/testFiles/testTwo.png file1 " +
            "greyscale green file1 file1green q");
    ImageManipulatorControllerImpl controller2 = new ImageManipulatorControllerImpl(
            this.model1, view2, readable2);
    controller2.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/testTwo.png as file1\n" +
            "Created greyscaled version of file1 called file1green\n" +
            "Quitting program...", appendable2.toString());
  }

  // tests the greyscale red command for file-type png
  @Test
  public void testLaunchProgramCommandsGreyscaleRedPNG() {
    // Greyscale Red
    Appendable appendable1 = new StringBuilder();
    ImageManipulatorTextView view1 = new ImageManipulatorTextView(appendable1);
    try {
      this.model1.loadImage("test/testFiles/testTwo.png", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable1 = new StringReader("load test/testFiles/testTwo.png file1 greyscale " +
            "red file1 file1red q");
    ImageManipulatorControllerImpl controller1 = new ImageManipulatorControllerImpl(
            this.model1, view1, readable1);
    controller1.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/testTwo.png as file1\n" +
            "Created greyscaled version of file1 called file1red\n" +
            "Quitting program...", appendable1.toString());

    // greyscale red bad appendable
    ImageManipulatorTextView view20 = new ImageManipulatorTextView(badAppendable);
    try {
      this.model1.loadImage("test/testFiles/test2.txt", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable20 = new StringReader("load test/testFiles/test2.txt file1 greyscale " +
            "red file1 file1red q");
    ImageManipulatorControllerImpl controller20 = new ImageManipulatorControllerImpl(
            this.model1, view20, readable20);
    try {
      controller20.launchProgram();
      fail("Should throw an exception.");
    } catch (IllegalStateException e) {
      assertEquals("I throw an IOException.", e.getMessage());
    }

  }

  // tests the sepia transform command on png
  @Test
  public void testLaunchProgramCommandsSepiaPNG() {
    Appendable appendable1 = new StringBuilder();
    ImageManipulatorTextView view1 = new ImageManipulatorTextView(appendable1);
    try {
      this.model1.loadImage("test/testFiles/testTwo.png", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable1 = new StringReader("load test/testFiles/testTwo.png file1 transform " +
            "sepia file1 file1sepia q");
    ImageManipulatorControllerImpl controller1 = new ImageManipulatorControllerImpl(
            this.model1, view1, readable1);
    controller1.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/testTwo.png as file1\n" +
            "Created transformed version of file1 called file1sepia with transform: sepia\n" +
            "Quitting program...", appendable1.toString());

    // bad appendable
    ImageManipulatorTextView view20 = new ImageManipulatorTextView(badAppendable);
    try {
      this.model1.loadImage("test/testFiles/testTwo.png", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable20 = new StringReader("load test/testFiles/testTwo.png file1 transform " +
            "sepia file1 file1red q");
    ImageManipulatorControllerImpl controller20 = new ImageManipulatorControllerImpl(
            this.model1, view20, readable20);
    try {
      controller20.launchProgram();
      fail("Should throw an exception.");
    } catch (IllegalStateException e) {
      assertEquals("I throw an IOException.", e.getMessage());
    }
  }

  // tests the greyscale transform command for file-type png
  @Test
  public void testLaunchProgramCommandsGreyscaleTransformPNG() {
    Appendable appendable1 = new StringBuilder();
    ImageManipulatorTextView view1 = new ImageManipulatorTextView(appendable1);
    try {
      this.model1.loadImage("test/testFiles/testTwo.png", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable1 = new StringReader("load test/testFiles/testTwo.png file1 transform " +
            "greyscale file1 file1greyscale q");
    ImageManipulatorControllerImpl controller1 = new ImageManipulatorControllerImpl(
            this.model1, view1, readable1);
    controller1.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/testTwo.png as file1\n" +
            "Created transformed version of file1 called file1greyscale with transform: " +
            "greyscale\n" +
            "Quitting program...", appendable1.toString());

    ImageManipulatorTextView view20 = new ImageManipulatorTextView(badAppendable);
    try {
      this.model1.loadImage("test/testFiles/testTwo.png", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable20 = new StringReader("load test/testFiles/testTwo.png file1 transform " +
            "greyscale file1 file1red q");
    ImageManipulatorControllerImpl controller20 = new ImageManipulatorControllerImpl(
            this.model1, view20, readable20);
    try {
      controller20.launchProgram();
      fail("Should throw an exception.");
    } catch (IllegalStateException e) {
      assertEquals("I throw an IOException.", e.getMessage());
    }
  }


  //tests the blur filter for file-type png
  @Test
  public void testLaunchProgramCommandsBlurPNG() {
    Appendable appendable1 = new StringBuilder();
    ImageManipulatorTextView view1 = new ImageManipulatorTextView(appendable1);
    try {
      this.model1.loadImage("test/testFiles/testTwo.png", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable1 = new StringReader("load test/testFiles/testTwo.png file1 filter " +
            "blur file1 file1blur q");
    ImageManipulatorControllerImpl controller1 = new ImageManipulatorControllerImpl(
            this.model1, view1, readable1);
    controller1.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/testTwo.png as file1\n" +
            "Created filtered version of file1 called file1blur with filter: blur\n" +
            "Quitting program...", appendable1.toString());

    ImageManipulatorTextView view20 = new ImageManipulatorTextView(badAppendable);
    try {
      this.model1.loadImage("test/testFiles/testTwo.png", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable20 = new StringReader("load test/testFiles/testTwo.png file1 filter " +
            "blur file1 file1blur q");
    ImageManipulatorControllerImpl controller20 = new ImageManipulatorControllerImpl(
            this.model1, view20, readable20);
    try {
      controller20.launchProgram();
      fail("Should throw an exception.");
    } catch (IllegalStateException e) {
      assertEquals("I throw an IOException.", e.getMessage());
    }
  }

  // tests the sharpen filter on file-type png
  @Test
  public void testLaunchProgramCommandsSharpenPNG() {
    Appendable appendable1 = new StringBuilder();
    ImageManipulatorTextView view1 = new ImageManipulatorTextView(appendable1);
    try {
      this.model1.loadImage("test/testFiles/testTwo.png", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable1 = new StringReader("load test/testFiles/testTwo.png file1 filter " +
            "sharpen file1 file1sharpen q");
    ImageManipulatorControllerImpl controller1 = new ImageManipulatorControllerImpl(
            this.model1, view1, readable1);
    controller1.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/testTwo.png as file1\n" +
            "Created filtered version of file1 called file1sharpen with filter: sharpen\n" +
            "Quitting program...", appendable1.toString());

    ImageManipulatorTextView view20 = new ImageManipulatorTextView(badAppendable);
    try {
      this.model1.loadImage("test/testFiles/testTwo.png", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable20 = new StringReader("load test/testFiles/testTwo.png file1 filter " +
            "sharpen file1 file1sharpen q");
    ImageManipulatorControllerImpl controller20 = new ImageManipulatorControllerImpl(
            this.model1, view20, readable20);
    try {
      controller20.launchProgram();
      fail("Should throw an exception.");
    } catch (IllegalStateException e) {
      assertEquals("I throw an IOException.", e.getMessage());
    }
  }

  // tests the horizontal-flip command for file-type bmp
  @Test
  public void testLaunchProgramCommandsHorizontalFlipBMP() {
    // horizontal flip
    Appendable appendable9 = new StringBuilder();
    ImageManipulatorTextView view9 = new ImageManipulatorTextView(appendable9);
    try {
      this.model1.loadImage("test/testFiles/testTwo2.bmp", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable9 = new StringReader("load test/testFiles/testTwo2.bmp file1" +
            " horizontal-flip" +
            " file1 file1horizontal q");
    ImageManipulatorControllerImpl controller9 = new ImageManipulatorControllerImpl(
            this.model1, view9, readable9);
    controller9.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/testTwo2.bmp as file1\n" +
            "Created horizontally flipped version of file1 called file1horizontal\n" +
            "Quitting program...", appendable9.toString());

    ImageManipulatorTextView view90 = new ImageManipulatorTextView(this.badAppendable);
    try {
      this.model1.loadImage("test/testFiles/testTwo2.bmp", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable90 = new StringReader("load test/testFiles/testTwo2.bmp file1 " +
            "horizontal-flip" +
            " file1 file1horizontal q");
    ImageManipulatorControllerImpl controller90 = new ImageManipulatorControllerImpl(
            this.model1, view90, readable90);
    try {
      controller90.launchProgram();
      fail("Should have thrown an Exception");
    } catch (IllegalStateException e) {
      assertEquals("I throw an IOException.", e.getMessage());
    }
    // horizontal-flip to brighten
    Appendable appendable10 = new StringBuilder();
    ImageManipulatorTextView view10 = new ImageManipulatorTextView(appendable10);
    try {
      this.model1.loadImage("test/testFiles/testTwo2.bmp", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable10 = new StringReader("load test/testFiles/testTwo2.bmp file1 " +
            "horizontal-flip" +
            " file1 file1horizontal brighten 10 file1horizontal file1bh " +
            "save test/testFiles/file1bhsaved.bmp file1bh q");
    ImageManipulatorControllerImpl controller10 = new ImageManipulatorControllerImpl(
            this.model1, view10, readable10);
    controller10.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/testTwo2.bmp as file1\n" +
            "Created horizontally flipped version of file1 called file1horizontal\n" +
            "Created brightened version of file1horizontal called file1bh\n" +
            "Saved file1bh to test/testFiles/file1bhsaved.bmp\n" +
            "Quitting program...", appendable10.toString());
  }


  // tests the vertical-flip command for file-type bmp
  @Test
  public void testLaunchProgramCommandsVerticalFlipBMP() {
    // vertical flip
    Appendable appendable8 = new StringBuilder();
    ImageManipulatorTextView view8 = new ImageManipulatorTextView(appendable8);
    try {
      this.model1.loadImage("test/testFiles/testTwo2.bmp", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable8 = new StringReader("load test/testFiles/testTwo2.bmp file1 " +
            "vertical-flip" +
            " file1 file1vertical q");
    ImageManipulatorControllerImpl controller8 = new ImageManipulatorControllerImpl(
            this.model1, view8, readable8);
    controller8.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/testTwo2.bmp as file1\n" +
            "Created vertically flipped version of file1 called file1vertical\n" +
            "Quitting program...", appendable8.toString());

    ImageManipulatorTextView view80 = new ImageManipulatorTextView(this.badAppendable);
    try {
      this.model1.loadImage("test/testFiles/testTwo2.bmp", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable80 = new StringReader("load test/testFiles/testTwo2.bmp file1" +
            " vertical-flip" +
            " file1 file1vertical q");
    ImageManipulatorControllerImpl controller80 = new ImageManipulatorControllerImpl(
            this.model1, view80, readable80);
    try {
      controller80.launchProgram();
      fail("Should throw an exception");
    } catch (IllegalStateException e) {
      assertEquals("I throw an IOException.", e.getMessage());
    }
  }

  // tests the brighten command for file-type bmp
  @Test
  public void testLaunchProgramCommandsBrightenBMP() {
    // brighten
    Appendable appendable7 = new StringBuilder();
    ImageManipulatorTextView view7 = new ImageManipulatorTextView(appendable7);
    try {
      this.model1.loadImage("test/testFiles/testTwo2.bmp", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable7 = new StringReader("load test/testFiles/testTwo2.bmp file1 brighten 10" +
            " file1 file1brighten q");
    ImageManipulatorControllerImpl controller7 = new ImageManipulatorControllerImpl(
            this.model1, view7, readable7);
    controller7.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/testTwo2.bmp as file1\n" +
            "Created brightened version of file1 called file1brighten\n" +
            "Quitting program...", appendable7.toString());

    // brighten
    ImageManipulatorTextView view70 = new ImageManipulatorTextView(badAppendable);
    try {
      this.model1.loadImage("test/testFiles/testTwo2.bmp", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable70 = new StringReader("load test/testFiles/testTwo2.bmp file1 brighten 10" +
            " file1 file1brighten q");
    ImageManipulatorControllerImpl controller70 = new ImageManipulatorControllerImpl(
            this.model1, view70, readable70);

    try {
      controller70.launchProgram();
      fail("Should throw an exception.");
    } catch (IllegalStateException e) {
      assertEquals("I throw an IOException.", e.getMessage());
    }

  }

  // tests the greyscale luma command for file-type bmp
  @Test
  public void testLaunchProgramCommandsGreyscaleLumaBMP() {

    // Greyscale luma
    Appendable appendable6 = new StringBuilder();
    ImageManipulatorTextView view6 = new ImageManipulatorTextView(appendable6);
    try {
      this.model1.loadImage("test/testFiles/testTwo2.bmp", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable6 = new StringReader("load test/testFiles/testTwo2.bmp file1 " +
            "greyscale luma" +
            " file1 file1luma q");
    ImageManipulatorControllerImpl controller6 = new ImageManipulatorControllerImpl(
            this.model1, view6, readable6);
    controller6.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/testTwo2.bmp as file1\n" +
            "Created greyscaled version of file1 called file1luma\n" +
            "Quitting program...", appendable6.toString());
  }

  // tests the greyscale intensity command for BMP
  @Test
  public void testLaunchProgramCommandsGreyscaleIntensityBMP() {
    // Greyscale intensity
    Appendable appendable5 = new StringBuilder();
    ImageManipulatorTextView view5 = new ImageManipulatorTextView(appendable5);
    try {
      this.model1.loadImage("test/testFiles/testTwo2.bmp", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable5 = new StringReader("load test/testFiles/testTwo2.bmp file1 " +
            "greyscale intensity" +
            " file1 file1intensity q");
    ImageManipulatorControllerImpl controller5 = new ImageManipulatorControllerImpl(
            this.model1, view5, readable5);
    controller5.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/testTwo2.bmp as file1\n" +
            "Created greyscaled version of file1 called file1intensity\n" +
            "Quitting program...", appendable5.toString());
  }

  // tests the greyscale value command for file-type bmp
  @Test
  public void testLaunchProgramCommandsGreyscaleValueBMP() {

    // Greyscale value
    Appendable appendable4 = new StringBuilder();
    ImageManipulatorTextView view4 = new ImageManipulatorTextView(appendable4);
    try {
      this.model1.loadImage("test/testFiles/testTwo2.bmp", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable4 = new StringReader(
            "load test/testFiles/testTwo2.bmp file1 greyscale value" +
                    " file1 file1value q");
    ImageManipulatorControllerImpl controller4 = new ImageManipulatorControllerImpl(
            this.model1, view4, readable4);
    controller4.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/testTwo2.bmp as file1\n" +
            "Created greyscaled version of file1 called file1value\n" +
            "Quitting program...", appendable4.toString());

  }

  // tests the greyscale blue command on file-type bmp
  @Test
  public void testLaunchProgramCommandsGreyscaleBlueBMP() {
    // Greyscale blue
    Appendable appendable3 = new StringBuilder();
    ImageManipulatorTextView view3 = new ImageManipulatorTextView(appendable3);
    try {
      this.model1.loadImage("test/testFiles/testTwo2.bmp", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable3 = new StringReader("load test/testFiles/testTwo2.bmp file1 " +
            "greyscale blue file1 file1blue q");
    ImageManipulatorControllerImpl controller3 = new ImageManipulatorControllerImpl(
            this.model1, view3, readable3);
    controller3.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/testTwo2.bmp as file1\n" +
            "Created greyscaled version of file1 called file1blue\n" +
            "Quitting program...", appendable3.toString());
  }

  // tests the greyscale green command for file-type bmp
  @Test
  public void testLaunchProgramCommandsGreyscaleGreenBMP() {
    // Greyscale green
    Appendable appendable2 = new StringBuilder();
    ImageManipulatorTextView view2 = new ImageManipulatorTextView(appendable2);
    try {
      this.model1.loadImage("test/testFiles/testTwo2.bmp", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable2 = new StringReader("load test/testFiles/testTwo2.bmp file1 " +
            "greyscale green file1 file1green q");
    ImageManipulatorControllerImpl controller2 = new ImageManipulatorControllerImpl(
            this.model1, view2, readable2);
    controller2.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/testTwo2.bmp as file1\n" +
            "Created greyscaled version of file1 called file1green\n" +
            "Quitting program...", appendable2.toString());
  }

  // tests the greyscale red command for file-type bmp
  @Test
  public void testLaunchProgramCommandsGreyscaleRedBMP() {
    // Greyscale Red
    Appendable appendable1 = new StringBuilder();
    ImageManipulatorTextView view1 = new ImageManipulatorTextView(appendable1);
    try {
      this.model1.loadImage("test/testFiles/testTwo2.bmp", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable1 = new StringReader("load test/testFiles/testTwo2.bmp file1 greyscale " +
            "red file1 file1red q");
    ImageManipulatorControllerImpl controller1 = new ImageManipulatorControllerImpl(
            this.model1, view1, readable1);
    controller1.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/testTwo2.bmp as file1\n" +
            "Created greyscaled version of file1 called file1red\n" +
            "Quitting program...", appendable1.toString());

    // greyscale red bad appendable
    ImageManipulatorTextView view20 = new ImageManipulatorTextView(badAppendable);
    try {
      this.model1.loadImage("test/testFiles/testTwo2.bmp", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable20 = new StringReader("load test/testFiles/testTwo2.bmp file1 greyscale " +
            "red file1 file1red q");
    ImageManipulatorControllerImpl controller20 = new ImageManipulatorControllerImpl(
            this.model1, view20, readable20);
    try {
      controller20.launchProgram();
      fail("Should throw an exception.");
    } catch (IllegalStateException e) {
      assertEquals("I throw an IOException.", e.getMessage());
    }

  }

  // tests the sepia transform command for file-type bmp
  @Test
  public void testLaunchProgramCommandsSepiaBMP() {
    Appendable appendable1 = new StringBuilder();
    ImageManipulatorTextView view1 = new ImageManipulatorTextView(appendable1);
    try {
      this.model1.loadImage("test/testFiles/testTwo2.bmp", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable1 = new StringReader("load test/testFiles/testTwo2.bmp file1 transform " +
            "sepia file1 file1sepia q");
    ImageManipulatorControllerImpl controller1 = new ImageManipulatorControllerImpl(
            this.model1, view1, readable1);
    controller1.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/testTwo2.bmp as file1\n" +
            "Created transformed version of file1 called file1sepia with transform: sepia\n" +
            "Quitting program...", appendable1.toString());

    ImageManipulatorTextView view20 = new ImageManipulatorTextView(badAppendable);
    try {
      this.model1.loadImage("test/testFiles/testTwo2.bmp", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable20 = new StringReader("load test/testFiles/testTwo2.bmp file1 transform " +
            "sepia file1 file1red q");
    ImageManipulatorControllerImpl controller20 = new ImageManipulatorControllerImpl(
            this.model1, view20, readable20);
    try {
      controller20.launchProgram();
      fail("Should throw an exception.");
    } catch (IllegalStateException e) {
      assertEquals("I throw an IOException.", e.getMessage());
    }
  }

  // tests the greyscale transform command for file-type bmp
  @Test
  public void testLaunchProgramCommandsGreyscaleTransformBMP() {
    Appendable appendable1 = new StringBuilder();
    ImageManipulatorTextView view1 = new ImageManipulatorTextView(appendable1);
    try {
      this.model1.loadImage("test/testFiles/testTwo2.bmp", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable1 = new StringReader("load test/testFiles/testTwo2.bmp file1 transform " +
            "greyscale file1 file1greyscale q");
    ImageManipulatorControllerImpl controller1 = new ImageManipulatorControllerImpl(
            this.model1, view1, readable1);
    controller1.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/testTwo2.bmp as file1\n" +
            "Created transformed version of file1 called file1greyscale with transform: " +
            "greyscale\n" +
            "Quitting program...", appendable1.toString());

    ImageManipulatorTextView view20 = new ImageManipulatorTextView(badAppendable);
    try {
      this.model1.loadImage("test/testFiles/testTwo2.bmp", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable20 = new StringReader("load test/testFiles/testTwo2.bmp file1 transform " +
            "greyscale file1 file1red q");
    ImageManipulatorControllerImpl controller20 = new ImageManipulatorControllerImpl(
            this.model1, view20, readable20);
    try {
      controller20.launchProgram();
      fail("Should throw an exception.");
    } catch (IllegalStateException e) {
      assertEquals("I throw an IOException.", e.getMessage());
    }
  }

  // tests the blur filter command for file-type bmp
  @Test
  public void testLaunchProgramCommandsBlurBMP() {
    Appendable appendable1 = new StringBuilder();
    ImageManipulatorTextView view1 = new ImageManipulatorTextView(appendable1);
    try {
      this.model1.loadImage("test/testFiles/testTwo2.bmp", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable1 = new StringReader("load test/testFiles/testTwo2.bmp file1 filter " +
            "blur file1 file1blur q");
    ImageManipulatorControllerImpl controller1 = new ImageManipulatorControllerImpl(
            this.model1, view1, readable1);
    controller1.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/testTwo2.bmp as file1\n" +
            "Created filtered version of file1 called file1blur with filter: blur\n" +
            "Quitting program...", appendable1.toString());

    ImageManipulatorTextView view20 = new ImageManipulatorTextView(badAppendable);
    try {
      this.model1.loadImage("test/testFiles/testTwo2.bmp", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable20 = new StringReader("load test/testFiles/testTwo2.bmp file1 filter " +
            "blur file1 file1blur q");
    ImageManipulatorControllerImpl controller20 = new ImageManipulatorControllerImpl(
            this.model1, view20, readable20);
    try {
      controller20.launchProgram();
      fail("Should throw an exception.");
    } catch (IllegalStateException e) {
      assertEquals("I throw an IOException.", e.getMessage());
    }
  }

  // tests the sharpen filter command for file-type bmp
  @Test
  public void testLaunchProgramCommandsSharpenBMP() {
    Appendable appendable1 = new StringBuilder();
    ImageManipulatorTextView view1 = new ImageManipulatorTextView(appendable1);
    try {
      this.model1.loadImage("test/testFiles/testTwo2.bmp", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable1 = new StringReader("load test/testFiles/testTwo2.bmp file1 filter " +
            "sharpen file1 file1sharpen q");
    ImageManipulatorControllerImpl controller1 = new ImageManipulatorControllerImpl(
            this.model1, view1, readable1);
    controller1.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/testTwo2.bmp as file1\n" +
            "Created filtered version of file1 called file1sharpen with filter: sharpen\n" +
            "Quitting program...", appendable1.toString());

    ImageManipulatorTextView view20 = new ImageManipulatorTextView(badAppendable);
    try {
      this.model1.loadImage("test/testFiles/testTwo2.bmp", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable20 = new StringReader("load test/testFiles/testTwo2.bmp file1 filter " +
            "sharpen file1 file1sharpen q");
    ImageManipulatorControllerImpl controller20 = new ImageManipulatorControllerImpl(
            this.model1, view20, readable20);
    try {
      controller20.launchProgram();
      fail("Should throw an exception.");
    } catch (IllegalStateException e) {
      assertEquals("I throw an IOException.", e.getMessage());
    }
  }

  // tests the horizontal-flip command for file-type jpeg
  @Test
  public void testLaunchProgramCommandsHorizontalFlipJPEG() {
    // horizontal flip
    Appendable appendable9 = new StringBuilder();
    ImageManipulatorTextView view9 = new ImageManipulatorTextView(appendable9);
    try {
      this.model1.loadImage("test/testFiles/test.jpeg", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable9 = new StringReader("load test/testFiles/test.jpeg file1 horizontal-flip" +
            " file1 file1horizontal q");
    ImageManipulatorControllerImpl controller9 = new ImageManipulatorControllerImpl(
            this.model1, view9, readable9);
    controller9.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/test.jpeg as file1\n" +
            "Created horizontally flipped version of file1 called file1horizontal\n" +
            "Quitting program...", appendable9.toString());

    ImageManipulatorTextView view90 = new ImageManipulatorTextView(this.badAppendable);
    try {
      this.model1.loadImage("test/testFiles/test.jpeg", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable90 = new StringReader("load test/testFiles/test.jpeg file1 " +
            "horizontal-flip" +
            " file1 file1horizontal q");
    ImageManipulatorControllerImpl controller90 = new ImageManipulatorControllerImpl(
            this.model1, view90, readable90);
    try {
      controller90.launchProgram();
      fail("Should have thrown an Exception");
    } catch (IllegalStateException e) {
      assertEquals("I throw an IOException.", e.getMessage());
    }
    // horizontal-flip to brighten
    Appendable appendable10 = new StringBuilder();
    ImageManipulatorTextView view10 = new ImageManipulatorTextView(appendable10);
    try {
      this.model1.loadImage("test/testFiles/test.jpeg", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable10 = new StringReader("load test/testFiles/test.jpeg file1 " +
            "horizontal-flip" +
            " file1 file1horizontal brighten 10 file1horizontal file1bh " +
            "save test/testFiles/file1bhsaved.jpeg file1bh q");
    ImageManipulatorControllerImpl controller10 = new ImageManipulatorControllerImpl(
            this.model1, view10, readable10);
    controller10.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/test.jpeg as file1\n" +
            "Created horizontally flipped version of file1 called file1horizontal\n" +
            "Created brightened version of file1horizontal called file1bh\n" +
            "Saved file1bh to test/testFiles/file1bhsaved.jpeg\n" +
            "Quitting program...", appendable10.toString());
  }

  // tests the vertical-flip command for file-type jpeg
  @Test
  public void testLaunchProgramCommandsVerticalFlipJPEG() {
    // vertical flip
    Appendable appendable8 = new StringBuilder();
    ImageManipulatorTextView view8 = new ImageManipulatorTextView(appendable8);
    try {
      this.model1.loadImage("test/testFiles/test.jpeg", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable8 = new StringReader("load test/testFiles/test.jpeg file1 vertical-flip" +
            " file1 file1vertical q");
    ImageManipulatorControllerImpl controller8 = new ImageManipulatorControllerImpl(
            this.model1, view8, readable8);
    controller8.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/test.jpeg as file1\n" +
            "Created vertically flipped version of file1 called file1vertical\n" +
            "Quitting program...", appendable8.toString());

    ImageManipulatorTextView view80 = new ImageManipulatorTextView(this.badAppendable);
    try {
      this.model1.loadImage("test/testFiles/test.jpeg", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable80 = new StringReader("load test/testFiles/test.jpeg file1 vertical-flip" +
            " file1 file1vertical q");
    ImageManipulatorControllerImpl controller80 = new ImageManipulatorControllerImpl(
            this.model1, view80, readable80);
    try {
      controller80.launchProgram();
      fail("Should throw an exception");
    } catch (IllegalStateException e) {
      assertEquals("I throw an IOException.", e.getMessage());
    }
  }

  // tests the brighten command for file-type jpeg
  @Test
  public void testLaunchProgramCommandsBrightenJPEG() {
    // brighten
    Appendable appendable7 = new StringBuilder();
    ImageManipulatorTextView view7 = new ImageManipulatorTextView(appendable7);
    try {
      this.model1.loadImage("test/testFiles/test.jpeg", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable7 = new StringReader("load test/testFiles/test.jpeg file1 brighten 10" +
            " file1 file1brighten q");
    ImageManipulatorControllerImpl controller7 = new ImageManipulatorControllerImpl(
            this.model1, view7, readable7);
    controller7.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/test.jpeg as file1\n" +
            "Created brightened version of file1 called file1brighten\n" +
            "Quitting program...", appendable7.toString());

    // brighten
    ImageManipulatorTextView view70 = new ImageManipulatorTextView(badAppendable);
    try {
      this.model1.loadImage("test/testFiles/test.jpeg", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable70 = new StringReader("load test/testFiles/test.jpeg file1 brighten 10" +
            " file1 file1brighten q");
    ImageManipulatorControllerImpl controller70 = new ImageManipulatorControllerImpl(
            this.model1, view70, readable70);

    try {
      controller70.launchProgram();
      fail("Should throw an exception.");
    } catch (IllegalStateException e) {
      assertEquals("I throw an IOException.", e.getMessage());
    }

  }

  // tests the greyscale luma command for file-type jpeg
  @Test
  public void testLaunchProgramCommandsGreyscaleLumaJPEG() {

    // Greyscale luma
    Appendable appendable6 = new StringBuilder();
    ImageManipulatorTextView view6 = new ImageManipulatorTextView(appendable6);
    try {
      this.model1.loadImage("test/testFiles/test.jpeg", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable6 = new StringReader("load test/testFiles/test.jpeg file1 greyscale luma" +
            " file1 file1luma q");
    ImageManipulatorControllerImpl controller6 = new ImageManipulatorControllerImpl(
            this.model1, view6, readable6);
    controller6.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/test.jpeg as file1\n" +
            "Created greyscaled version of file1 called file1luma\n" +
            "Quitting program...", appendable6.toString());
  }

  // tests the greyscale intensity command for file-type jpeg
  @Test
  public void testLaunchProgramCommandsGreyscaleIntensityJPEG() {
    // Greyscale intensity
    Appendable appendable5 = new StringBuilder();
    ImageManipulatorTextView view5 = new ImageManipulatorTextView(appendable5);
    try {
      this.model1.loadImage("test/testFiles/test.jpeg", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable5 = new StringReader("load test/testFiles/test.jpeg file1 " +
            "greyscale intensity" +
            " file1 file1intensity q");
    ImageManipulatorControllerImpl controller5 = new ImageManipulatorControllerImpl(
            this.model1, view5, readable5);
    controller5.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/test.jpeg as file1\n" +
            "Created greyscaled version of file1 called file1intensity\n" +
            "Quitting program...", appendable5.toString());
  }

  // tests the greyscale value command for file-type jpeg
  @Test
  public void testLaunchProgramCommandsGreyscaleValueJPEG() {

    // Greyscale value
    Appendable appendable4 = new StringBuilder();
    ImageManipulatorTextView view4 = new ImageManipulatorTextView(appendable4);
    try {
      this.model1.loadImage("test/testFiles/test.jpeg", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable4 = new StringReader("load test/testFiles/test.jpeg file1 greyscale value" +
            " file1 file1value q");
    ImageManipulatorControllerImpl controller4 = new ImageManipulatorControllerImpl(
            this.model1, view4, readable4);
    controller4.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/test.jpeg as file1\n" +
            "Created greyscaled version of file1 called file1value\n" +
            "Quitting program...", appendable4.toString());

  }

  // tests the greyscale blue command for file-type jpeg
  @Test
  public void testLaunchProgramCommandsGreyscaleBlueJPEG() {
    // Greyscale blue
    Appendable appendable3 = new StringBuilder();
    ImageManipulatorTextView view3 = new ImageManipulatorTextView(appendable3);
    try {
      this.model1.loadImage("test/testFiles/test.jpeg", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable3 = new StringReader("load test/testFiles/test.jpeg file1 " +
            "greyscale blue file1 file1blue q");
    ImageManipulatorControllerImpl controller3 = new ImageManipulatorControllerImpl(
            this.model1, view3, readable3);
    controller3.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/test.jpeg as file1\n" +
            "Created greyscaled version of file1 called file1blue\n" +
            "Quitting program...", appendable3.toString());
  }

  // tests the greyscale green command for file-type jpeg
  @Test
  public void testLaunchProgramCommandsGreyscaleGreenJPEG() {
    // Greyscale green
    Appendable appendable2 = new StringBuilder();
    ImageManipulatorTextView view2 = new ImageManipulatorTextView(appendable2);
    try {
      this.model1.loadImage("test/testFiles/test.jpeg", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable2 = new StringReader("load test/testFiles/test.jpeg file1 " +
            "greyscale green file1 file1green q");
    ImageManipulatorControllerImpl controller2 = new ImageManipulatorControllerImpl(
            this.model1, view2, readable2);
    controller2.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/test.jpeg as file1\n" +
            "Created greyscaled version of file1 called file1green\n" +
            "Quitting program...", appendable2.toString());
  }

  // tests the greyscale red command for file-type jpeg
  @Test
  public void testLaunchProgramCommandsGreyscaleRedJPEG() {
    // Greyscale Red
    Appendable appendable1 = new StringBuilder();
    ImageManipulatorTextView view1 = new ImageManipulatorTextView(appendable1);
    try {
      this.model1.loadImage("test/testFiles/test.jpeg", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable1 = new StringReader("load test/testFiles/test.jpeg file1 greyscale " +
            "red file1 file1red q");
    ImageManipulatorControllerImpl controller1 = new ImageManipulatorControllerImpl(
            this.model1, view1, readable1);
    controller1.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/test.jpeg as file1\n" +
            "Created greyscaled version of file1 called file1red\n" +
            "Quitting program...", appendable1.toString());

    // greyscale red bad appendable
    ImageManipulatorTextView view20 = new ImageManipulatorTextView(badAppendable);
    try {
      this.model1.loadImage("test/testFiles/test.jpeg", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable20 = new StringReader("load test/testFiles/test.jpeg file1 greyscale " +
            "red file1 file1red q");
    ImageManipulatorControllerImpl controller20 = new ImageManipulatorControllerImpl(
            this.model1, view20, readable20);
    try {
      controller20.launchProgram();
      fail("Should throw an exception.");
    } catch (IllegalStateException e) {
      assertEquals("I throw an IOException.", e.getMessage());
    }

  }


  // tests the transform sepia command for file-type jpeg
  @Test
  public void testLaunchProgramCommandsSepiaJPEG() {
    Appendable appendable1 = new StringBuilder();
    ImageManipulatorTextView view1 = new ImageManipulatorTextView(appendable1);
    try {
      this.model1.loadImage("test/testFiles/test.jpeg", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }

    Readable readable1 = new StringReader("load test/testFiles/test.jpeg file1 transform " +
            "sepia file1 file1sepia q");
    ImageManipulatorControllerImpl controller1 = new ImageManipulatorControllerImpl(
            this.model1, view1, readable1);
    controller1.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/test.jpeg as file1\n" +
            "Created transformed version of file1 called file1sepia with transform: sepia\n" +
            "Quitting program...", appendable1.toString());

    ImageManipulatorTextView view20 = new ImageManipulatorTextView(badAppendable);
    try {
      this.model1.loadImage("test/testFiles/test.jpeg", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable20 = new StringReader("load test/testFiles/test.jpeg file1 transform " +
            "sepia file1 file1red q");
    ImageManipulatorControllerImpl controller20 = new ImageManipulatorControllerImpl(
            this.model1, view20, readable20);
    try {
      controller20.launchProgram();
      fail("Should throw an exception.");
    } catch (IllegalStateException e) {
      assertEquals("I throw an IOException.", e.getMessage());
    }
  }

  // tests the transform command for file-type jpeg
  @Test
  public void testLaunchProgramCommandsGreyscaleTransformJPEG() {
    Appendable appendable1 = new StringBuilder();
    ImageManipulatorTextView view1 = new ImageManipulatorTextView(appendable1);
    try {
      this.model1.loadImage("test/testFiles/test.jpeg", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable1 = new StringReader("load test/testFiles/test.jpeg file1 transform " +
            "greyscale file1 file1greyscale q");
    ImageManipulatorControllerImpl controller1 = new ImageManipulatorControllerImpl(
            this.model1, view1, readable1);
    controller1.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/test.jpeg as file1\n" +
            "Created transformed version of file1 called file1greyscale with transform:" +
            " greyscale\n" +
            "Quitting program...", appendable1.toString());

    ImageManipulatorTextView view20 = new ImageManipulatorTextView(badAppendable);
    try {
      this.model1.loadImage("test/testFiles/test.jpeg", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable20 = new StringReader("load test/testFiles/test.jpeg file1 transform " +
            "greyscale file1 file1red q");
    ImageManipulatorControllerImpl controller20 = new ImageManipulatorControllerImpl(
            this.model1, view20, readable20);
    try {
      controller20.launchProgram();
      fail("Should throw an exception.");
    } catch (IllegalStateException e) {
      assertEquals("I throw an IOException.", e.getMessage());
    }
  }

  // tests the filter blur command for file-type jpeg
  @Test
  public void testLaunchProgramCommandsBlurJPEG() {
    Appendable appendable1 = new StringBuilder();
    ImageManipulatorTextView view1 = new ImageManipulatorTextView(appendable1);
    try {
      this.model1.loadImage("test/testFiles/test.jpeg", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable1 = new StringReader("load test/testFiles/test.jpeg file1 filter " +
            "blur file1 file1blur q");
    ImageManipulatorControllerImpl controller1 = new ImageManipulatorControllerImpl(
            this.model1, view1, readable1);
    controller1.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/test.jpeg as file1\n" +
            "Created filtered version of file1 called file1blur with filter: blur\n" +
            "Quitting program...", appendable1.toString());

    ImageManipulatorTextView view20 = new ImageManipulatorTextView(badAppendable);
    try {
      this.model1.loadImage("test/testFiles/test.jpeg", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable20 = new StringReader("load test/testFiles/test.jpeg file1 filter " +
            "blur file1 file1blur q");
    ImageManipulatorControllerImpl controller20 = new ImageManipulatorControllerImpl(
            this.model1, view20, readable20);
    try {
      controller20.launchProgram();
      fail("Should throw an exception.");
    } catch (IllegalStateException e) {
      assertEquals("I throw an IOException.", e.getMessage());
    }
  }

  // tests the filter sharpen command for jpeg
  @Test
  public void testLaunchProgramCommandsSharpenJPEG() {
    Appendable appendable1 = new StringBuilder();
    ImageManipulatorTextView view1 = new ImageManipulatorTextView(appendable1);
    try {
      this.model1.loadImage("test/testFiles/test.jpeg", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable1 = new StringReader("load test/testFiles/test.jpeg file1 filter " +
            "sharpen file1 file1sharpen q");
    ImageManipulatorControllerImpl controller1 = new ImageManipulatorControllerImpl(
            this.model1, view1, readable1);
    controller1.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/test.jpeg as file1\n" +
            "Created filtered version of file1 called file1sharpen with filter: sharpen\n" +
            "Quitting program...", appendable1.toString());

    ImageManipulatorTextView view20 = new ImageManipulatorTextView(badAppendable);
    try {
      this.model1.loadImage("test/testFiles/test.jpeg", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable20 = new StringReader("load test/testFiles/test.jpeg file1 filter " +
            "sharpen file1 file1sharpen q");
    ImageManipulatorControllerImpl controller20 = new ImageManipulatorControllerImpl(
            this.model1, view20, readable20);
    try {
      controller20.launchProgram();
      fail("Should throw an exception.");
    } catch (IllegalStateException e) {
      assertEquals("I throw an IOException.", e.getMessage());
    }
  }

  // tests the horizontal-flip command for file-type ppm
  @Test
  public void testLaunchProgramCommandsHorizontalFlipPPM() {
    // horizontal flip
    Appendable appendable9 = new StringBuilder();
    ImageManipulatorTextView view9 = new ImageManipulatorTextView(appendable9);
    try {
      this.model1.loadImage("test/testFiles/testTwo2.ppm", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable9 = new StringReader("load test/testFiles/testTwo2.ppm file1" +
            " horizontal-flip" +
            " file1 file1horizontal q");
    ImageManipulatorControllerImpl controller9 = new ImageManipulatorControllerImpl(
            this.model1, view9, readable9);
    controller9.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/testTwo2.ppm as file1\n" +
            "Created horizontally flipped version of file1 called file1horizontal\n" +
            "Quitting program...", appendable9.toString());

    ImageManipulatorTextView view90 = new ImageManipulatorTextView(this.badAppendable);
    try {
      this.model1.loadImage("test/testFiles/testTwo2.ppm", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable90 = new StringReader("load test/testFiles/testTwo2.ppm file1 " +
            "horizontal-flip" +
            " file1 file1horizontal q");
    ImageManipulatorControllerImpl controller90 = new ImageManipulatorControllerImpl(
            this.model1, view90, readable90);
    try {
      controller90.launchProgram();
      fail("Should have thrown an Exception");
    } catch (IllegalStateException e) {
      assertEquals("I throw an IOException.", e.getMessage());
    }
    // horizontal-flip to brighten
    Appendable appendable10 = new StringBuilder();
    ImageManipulatorTextView view10 = new ImageManipulatorTextView(appendable10);
    try {
      this.model1.loadImage("test/testFiles/testTwo2.ppm", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable10 = new StringReader("load test/testFiles/testTwo2.ppm file1 " +
            "horizontal-flip" +
            " file1 file1horizontal brighten 10 file1horizontal file1bh " +
            "save test/testFiles/file1bhsaved.ppm file1bh q");
    ImageManipulatorControllerImpl controller10 = new ImageManipulatorControllerImpl(
            this.model1, view10, readable10);
    controller10.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/testTwo2.ppm as file1\n" +
            "Created horizontally flipped version of file1 called file1horizontal\n" +
            "Created brightened version of file1horizontal called file1bh\n" +
            "Saved file1bh to test/testFiles/file1bhsaved.ppm\n" +
            "Quitting program...", appendable10.toString());
  }

  // tests the vertical-flip command for file-type PPM
  @Test
  public void testLaunchProgramCommandsVerticalFlipPPM() {
    // vertical flip
    Appendable appendable8 = new StringBuilder();
    ImageManipulatorTextView view8 = new ImageManipulatorTextView(appendable8);
    try {
      this.model1.loadImage("test/testFiles/testTwo2.ppm", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable8 = new StringReader("load test/testFiles/testTwo2.ppm file1" +
            " vertical-flip" +
            " file1 file1vertical q");
    ImageManipulatorControllerImpl controller8 = new ImageManipulatorControllerImpl(
            this.model1, view8, readable8);
    controller8.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/testTwo2.ppm as file1\n" +
            "Created vertically flipped version of file1 called file1vertical\n" +
            "Quitting program...", appendable8.toString());

    ImageManipulatorTextView view80 = new ImageManipulatorTextView(this.badAppendable);
    try {
      this.model1.loadImage("test/testFiles/testTwo2.ppm", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable80 = new StringReader("load test/testFiles/testTwo2.ppm file1" +
            " vertical-flip" +
            " file1 file1vertical q");
    ImageManipulatorControllerImpl controller80 = new ImageManipulatorControllerImpl(
            this.model1, view80, readable80);
    try {
      controller80.launchProgram();
      fail("Should throw an exception");
    } catch (IllegalStateException e) {
      assertEquals("I throw an IOException.", e.getMessage());
    }
  }

  // tests the brighten command for file-type ppm
  @Test
  public void testLaunchProgramCommandsBrightenPPM() {
    // brighten
    Appendable appendable7 = new StringBuilder();
    ImageManipulatorTextView view7 = new ImageManipulatorTextView(appendable7);
    try {
      this.model1.loadImage("test/testFiles/testTwo2.ppm", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable7 = new StringReader("load test/testFiles/testTwo2.ppm file1 brighten 10" +
            " file1 file1brighten q");
    ImageManipulatorControllerImpl controller7 = new ImageManipulatorControllerImpl(
            this.model1, view7, readable7);
    controller7.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/testTwo2.ppm as file1\n" +
            "Created brightened version of file1 called file1brighten\n" +
            "Quitting program...", appendable7.toString());

    // brighten
    ImageManipulatorTextView view70 = new ImageManipulatorTextView(badAppendable);
    try {
      this.model1.loadImage("test/testFiles/testTwo2.ppm", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable70 = new StringReader("load test/testFiles/testTwo2.ppm file1 brighten 10" +
            " file1 file1brighten q");
    ImageManipulatorControllerImpl controller70 = new ImageManipulatorControllerImpl(
            this.model1, view70, readable70);

    try {
      controller70.launchProgram();
      fail("Should throw an exception.");
    } catch (IllegalStateException e) {
      assertEquals("I throw an IOException.", e.getMessage());
    }

  }

  // tests the greyscale luma command for file-type PPM
  @Test
  public void testLaunchProgramCommandsGreyscaleLumaPPM() {

    // Greyscale luma
    Appendable appendable6 = new StringBuilder();
    ImageManipulatorTextView view6 = new ImageManipulatorTextView(appendable6);
    try {
      this.model1.loadImage("test/testFiles/testTwo2.ppm", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable6 = new StringReader("load test/testFiles/testTwo2.ppm file1" +
            " greyscale luma" +
            " file1 file1luma q");
    ImageManipulatorControllerImpl controller6 = new ImageManipulatorControllerImpl(
            this.model1, view6, readable6);
    controller6.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/testTwo2.ppm as file1\n" +
            "Created greyscaled version of file1 called file1luma\n" +
            "Quitting program...", appendable6.toString());
  }

  // tests the greyscale intensity command for file-type ppm
  @Test
  public void testLaunchProgramCommandsGreyscaleIntensityPPM() {
    // Greyscale intensity
    Appendable appendable5 = new StringBuilder();
    ImageManipulatorTextView view5 = new ImageManipulatorTextView(appendable5);
    try {
      this.model1.loadImage("test/testFiles/testTwo2.ppm", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable5 = new StringReader("load test/testFiles/testTwo2.ppm file1 " +
            "greyscale intensity" +
            " file1 file1intensity q");
    ImageManipulatorControllerImpl controller5 = new ImageManipulatorControllerImpl(
            this.model1, view5, readable5);
    controller5.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/testTwo2.ppm as file1\n" +
            "Created greyscaled version of file1 called file1intensity\n" +
            "Quitting program...", appendable5.toString());
  }

  // tests the greyscale value command for file-type ppm
  @Test
  public void testLaunchProgramCommandsGreyscaleValuePPM() {

    // Greyscale value
    Appendable appendable4 = new StringBuilder();
    ImageManipulatorTextView view4 = new ImageManipulatorTextView(appendable4);
    try {
      this.model1.loadImage("test/testFiles/testTwo2.ppm", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable4 = new StringReader("load test/testFiles/testTwo2.ppm file1 " +
            "greyscale value" +
            " file1 file1value q");
    ImageManipulatorControllerImpl controller4 = new ImageManipulatorControllerImpl(
            this.model1, view4, readable4);
    controller4.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/testTwo2.ppm as file1\n" +
            "Created greyscaled version of file1 called file1value\n" +
            "Quitting program...", appendable4.toString());
  }

  // tests the greyscale blue command for file-type ppm
  @Test
  public void testLaunchProgramCommandsGreyscaleBluePPM() {
    // Greyscale blue
    Appendable appendable3 = new StringBuilder();
    ImageManipulatorTextView view3 = new ImageManipulatorTextView(appendable3);
    try {
      this.model1.loadImage("test/testFiles/testTwo2.ppm", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable3 = new StringReader("load test/testFiles/testTwo2.ppm file1 " +
            "greyscale blue file1 file1blue q");
    ImageManipulatorControllerImpl controller3 = new ImageManipulatorControllerImpl(
            this.model1, view3, readable3);
    controller3.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/testTwo2.ppm as file1\n" +
            "Created greyscaled version of file1 called file1blue\n" +
            "Quitting program...", appendable3.toString());
  }

  // tests the greyscale green command for file-type ppm
  @Test
  public void testLaunchProgramCommandsGreyscaleGreenPPM() {
    // Greyscale green
    Appendable appendable2 = new StringBuilder();
    ImageManipulatorTextView view2 = new ImageManipulatorTextView(appendable2);
    try {
      this.model1.loadImage("test/testFiles/testTwo2.ppm", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable2 = new StringReader("load test/testFiles/testTwo2.ppm file1 " +
            "greyscale green file1 file1green q");
    ImageManipulatorControllerImpl controller2 = new ImageManipulatorControllerImpl(
            this.model1, view2, readable2);
    controller2.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/testTwo2.ppm as file1\n" +
            "Created greyscaled version of file1 called file1green\n" +
            "Quitting program...", appendable2.toString());
  }

  // tests the greyscale red command for file-type PPM
  @Test
  public void testLaunchProgramCommandsGreyscaleRedPPM() {
    // Greyscale Red
    Appendable appendable1 = new StringBuilder();
    ImageManipulatorTextView view1 = new ImageManipulatorTextView(appendable1);
    try {
      this.model1.loadImage("test/testFiles/testTwo2.ppm", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable1 = new StringReader("load test/testFiles/testTwo2.ppm file1 greyscale " +
            "red file1 file1red q");
    ImageManipulatorControllerImpl controller1 = new ImageManipulatorControllerImpl(
            this.model1, view1, readable1);
    controller1.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/testTwo2.ppm as file1\n" +
            "Created greyscaled version of file1 called file1red\n" +
            "Quitting program...", appendable1.toString());

    // greyscale red bad appendable
    ImageManipulatorTextView view20 = new ImageManipulatorTextView(badAppendable);
    try {
      this.model1.loadImage("test/testFiles/testTwo2.ppm", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable20 = new StringReader("load test/testFiles/testTwo2.ppm file1 greyscale " +
            "red file1 file1red q");
    ImageManipulatorControllerImpl controller20 = new ImageManipulatorControllerImpl(
            this.model1, view20, readable20);
    try {
      controller20.launchProgram();
      fail("Should throw an exception.");
    } catch (IllegalStateException e) {
      assertEquals("I throw an IOException.", e.getMessage());
    }

  }

  // tests the transform sepia command for file type PPM
  @Test
  public void testLaunchProgramCommandsSepiaPPM() {
    Appendable appendable1 = new StringBuilder();
    ImageManipulatorTextView view1 = new ImageManipulatorTextView(appendable1);
    try {
      this.model1.loadImage("test/testFiles/testTwo2.ppm", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable1 = new StringReader("load test/testFiles/testTwo2.ppm file1 transform " +
            "sepia file1 file1sepia q");
    ImageManipulatorControllerImpl controller1 = new ImageManipulatorControllerImpl(
            this.model1, view1, readable1);
    controller1.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/testTwo2.ppm as file1\n" +
            "Created transformed version of file1 called file1sepia with transform: sepia\n" +
            "Quitting program...", appendable1.toString());

    ImageManipulatorTextView view20 = new ImageManipulatorTextView(badAppendable);
    try {
      this.model1.loadImage("test/testFiles/testTwo2.ppm", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable20 = new StringReader("load test/testFiles/testTwo2.ppm file1 transform " +
            "sepia file1 file1red q");
    ImageManipulatorControllerImpl controller20 = new ImageManipulatorControllerImpl(
            this.model1, view20, readable20);
    try {
      controller20.launchProgram();
      fail("Should throw an exception.");
    } catch (IllegalStateException e) {
      assertEquals("I throw an IOException.", e.getMessage());
    }
  }


  // tests the transform greyscale command for file-type PPM
  @Test
  public void testLaunchProgramCommandsGreyscaleTransformPPM() {
    Appendable appendable1 = new StringBuilder();
    ImageManipulatorTextView view1 = new ImageManipulatorTextView(appendable1);
    try {
      this.model1.loadImage("test/testFiles/testTwo2.ppm", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable1 = new StringReader("load test/testFiles/testTwo2.ppm file1 transform " +
            "greyscale file1 file1greyscale q");
    ImageManipulatorControllerImpl controller1 = new ImageManipulatorControllerImpl(
            this.model1, view1, readable1);
    controller1.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/testTwo2.ppm as file1\n" +
            "Created transformed version of file1 called file1greyscale with transform: greyscale\n"
            + "Quitting program...", appendable1.toString());

    ImageManipulatorTextView view20 = new ImageManipulatorTextView(badAppendable);
    try {
      this.model1.loadImage("test/testFiles/testTwo2.ppm", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable20 = new StringReader("load test/testFiles/testTwo2.ppm file1 transform " +
            "greyscale file1 file1red q");
    ImageManipulatorControllerImpl controller20 = new ImageManipulatorControllerImpl(
            this.model1, view20, readable20);
    try {
      controller20.launchProgram();
      fail("Should throw an exception.");
    } catch (IllegalStateException e) {
      assertEquals("I throw an IOException.", e.getMessage());
    }
  }

  // tests the filter blur command for file-type PPM
  @Test
  public void testLaunchProgramCommandsBlurPPM() {
    Appendable appendable1 = new StringBuilder();
    ImageManipulatorTextView view1 = new ImageManipulatorTextView(appendable1);
    try {
      this.model1.loadImage("test/testFiles/testTwo2.ppm", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable1 = new StringReader("load test/testFiles/testTwo2.ppm file1 filter " +
            "blur file1 file1blur q");
    ImageManipulatorControllerImpl controller1 = new ImageManipulatorControllerImpl(
            this.model1, view1, readable1);
    controller1.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/testTwo2.ppm as file1\n" +
            "Created filtered version of file1 called file1blur with filter: blur\n" +
            "Quitting program...", appendable1.toString());

    ImageManipulatorTextView view20 = new ImageManipulatorTextView(badAppendable);
    try {
      this.model1.loadImage("test/testFiles/testTwo2.ppm", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable20 = new StringReader("load test/testFiles/testTwo2.ppm file1 filter " +
            "blur file1 file1blur q");
    ImageManipulatorControllerImpl controller20 = new ImageManipulatorControllerImpl(
            this.model1, view20, readable20);
    try {
      controller20.launchProgram();
      fail("Should throw an exception.");
    } catch (IllegalStateException e) {
      assertEquals("I throw an IOException.", e.getMessage());
    }
  }

  // tests the filter sharpen command for file-type PPM
  @Test
  public void testLaunchProgramCommandsSharpenPPM() {
    Appendable appendable1 = new StringBuilder();
    ImageManipulatorTextView view1 = new ImageManipulatorTextView(appendable1);
    try {
      this.model1.loadImage("test/testFiles/testTwo2.ppm", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable1 = new StringReader("load test/testFiles/testTwo2.ppm file1 filter " +
            "sharpen file1 file1sharpen q");
    ImageManipulatorControllerImpl controller1 = new ImageManipulatorControllerImpl(
            this.model1, view1, readable1);
    controller1.launchProgram();
    assertEquals("Welcome to the ImageManipulation software!\n" +
            "Type 'help' to see a list of commands.\n" +
            "Loaded test/testFiles/testTwo2.ppm as file1\n" +
            "Created filtered version of file1 called file1sharpen with filter: sharpen\n" +
            "Quitting program...", appendable1.toString());

    ImageManipulatorTextView view20 = new ImageManipulatorTextView(badAppendable);
    try {
      this.model1.loadImage("test/testFiles/testTwo2.ppm", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable20 = new StringReader("load test/testFiles/testTwo2.ppm file1 filter " +
            "sharpen file1 file1sharpen q");
    ImageManipulatorControllerImpl controller20 = new ImageManipulatorControllerImpl(
            this.model1, view20, readable20);
    try {
      controller20.launchProgram();
      fail("Should throw an exception.");
    } catch (IllegalStateException e) {
      assertEquals("I throw an IOException.", e.getMessage());
    }
  }

  // tests supplying a bad readable for file-type PPM
  @Test
  public void testLaunchProgramBadReadablePPM() {

    Appendable appendable1 = new StringBuilder();
    ImageManipulatorTextView view1 = new ImageManipulatorTextView(appendable1);
    try {
      this.model1.loadImage("test/testFiles/testTwo2.ppm", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }

    ImageManipulatorControllerImpl controller1 = new ImageManipulatorControllerImpl(
            this.model1, view1, this.badReadable);
    try {
      controller1.launchProgram();
      fail("Should have thrown IllegalStateException");
    } catch (IllegalStateException e) {
      assertEquals("Cannot read Readable.", e.getMessage());
    }

  }

  // tests supplying a bad readable for file-type bmp
  @Test
  public void testLaunchProgramBadReadableBMP() {

    Appendable appendable1 = new StringBuilder();
    ImageManipulatorTextView view1 = new ImageManipulatorTextView(appendable1);
    try {
      this.model1.loadImage("test/testFiles/testTwo2.bmp", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }

    ImageManipulatorControllerImpl controller1 = new ImageManipulatorControllerImpl(
            this.model1, view1, this.badReadable);
    try {
      controller1.launchProgram();
      fail("Should have thrown IllegalStateException");
    } catch (IllegalStateException e) {
      assertEquals("Cannot read Readable.", e.getMessage());
    }

  }

  // tests supplying a bad readable for file-type png
  @Test
  public void testLaunchProgramBadReadablePNG() {

    Appendable appendable1 = new StringBuilder();
    ImageManipulatorTextView view1 = new ImageManipulatorTextView(appendable1);
    try {
      this.model1.loadImage("test/testFiles/testTwo.png", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }

    ImageManipulatorControllerImpl controller1 = new ImageManipulatorControllerImpl(
            this.model1, view1, this.badReadable);
    try {
      controller1.launchProgram();
      fail("Should have thrown IllegalStateException");
    } catch (IllegalStateException e) {
      assertEquals("Cannot read Readable.", e.getMessage());
    }

  }

  // tests supplying a bad readable for file-type jpeg
  @Test
  public void testLaunchProgramBadReadableJPEG() {

    Appendable appendable1 = new StringBuilder();
    ImageManipulatorTextView view1 = new ImageManipulatorTextView(appendable1);
    try {
      this.model1.loadImage("test/testFiles/test.jpeg", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }

    ImageManipulatorControllerImpl controller1 = new ImageManipulatorControllerImpl(
            this.model1, view1, this.badReadable);
    try {
      controller1.launchProgram();
      fail("Should have thrown IllegalStateException");
    } catch (IllegalStateException e) {
      assertEquals("Cannot read Readable.", e.getMessage());
    }

  }

  // tests invalid commands.
  // we don't need to test for all different file types as the same
  // conditional is being tested regardless of file-type.
  @Test
  public void testLaunchProgramInvalidCommand() {
    //image can't be found
    Appendable appendable1 = new StringBuilder();
    ImageManipulatorTextView view1 = new ImageManipulatorTextView(appendable1);
    try {
      this.model1.loadImage("test/testFiles/test2.txt", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable1 = new StringReader("load nonexistent file1 q");
    ImageManipulatorControllerImpl controller1 = new ImageManipulatorControllerImpl(
            this.model1, view1, readable1);
    try {
      controller1.launchProgram();
      assertEquals("Welcome to the ImageManipulation software!\n" +
              "Type 'help' to see a list of commands.\n" +
              "Could not find file, please try again.\n" +
              "Quitting program...", appendable1.toString());
    } catch (IllegalStateException e) {
      // fails as the file trying to be loaded doesn't exist
    }

    // cannot read readable

    Appendable appendable3 = new StringBuilder();
    ImageManipulatorTextView view3 = new ImageManipulatorTextView(appendable3);
    try {
      this.model1.loadImage("test/testFiles/test2.txt", "file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown a FileNotFoundException.");
    }
    Readable readable3 = new StringReader("");
    ImageManipulatorControllerImpl controller3 = new ImageManipulatorControllerImpl(
            this.model1, view3, readable3);
    try {
      controller3.launchProgram();
      fail("Should have thrown an exception.");
    } catch (IllegalStateException e) {
      // fails as the readable can't be read (it's empty!)
    }

    // wrong readable command
    Appendable appendable4 = new StringBuilder();
    ImageManipulatorTextView view4 = new ImageManipulatorTextView(appendable4);
    Readable readable4 = new StringReader("warp test2.txt file1 q");
    ImageManipulatorControllerImpl controller4 = new ImageManipulatorControllerImpl(
            this.model1, view4, readable3);
    try {
      controller4.launchProgram();
      fail("Should have thrown an exception.");
    } catch (IllegalStateException e) {
      // fails as the command doesn't exist
    }

    // wrong manipulator command
    Appendable appendable5 = new StringBuilder();
    ImageManipulatorTextView view5 = new ImageManipulatorTextView(appendable5);
    Readable readable5 = new StringReader("load test/testFiles/test2.txt file1 " +
            "greyscale increase file1 file2 q");
    ImageManipulatorControllerImpl controller5 = new ImageManipulatorControllerImpl(
            this.model1, view5, readable5);
    try {
      controller5.launchProgram();
      assertEquals("Welcome to the ImageManipulation software!\n" +
              "Type 'help' to see a list of commands.\n" +
              "Loaded test/testFiles/test2.txt as file1\n" +
              "Not a valid component. Please try again.\n" +
              "Quitting program...", appendable5.toString());
    } catch (IllegalStateException e) {
      fail("Should have thrown an exception.");
    }

  }

  // input testing for load
  @Test
  public void testInputLoad() {
    Readable reader = new StringReader("load test/testFiles/txttwo.ppm test " +
            "load test2 test3 load cool ap q");
    this.inputController = new ImageManipulatorControllerImpl(this.inputModel, this.view1, reader);

    inputController.launchProgram();
    assertEquals("Image path: test/testFiles/txttwo.ppm Dest name: test"
            + "Image path: test2 Dest name: test3"
            + "Image path: cool Dest name: ap", this.log.toString());
  }

  // input testing for save
  @Test
  public void testInputSave() {
    Readable reader = new StringReader("save test/testFiles/txttwo.ppm test " +
            "save r p save 123 456 q");
    this.inputController = new ImageManipulatorControllerImpl(this.inputModel, this.view1, reader);

    inputController.launchProgram();
    assertEquals("Dest path: test/testFiles/txttwo.ppm Image name: test"
            + "Dest path: r Image name: p"
            + "Dest path: 123 Image name: 456", this.log.toString());
  }

  // input testing for filter
  @Test
  public void testInputFilter() {
    Readable reader = new StringReader("filter blur test blurTest " +
            "filter sharpen 2! 5? filter blur pterodactyl quail q");
    this.inputController = new ImageManipulatorControllerImpl(this.inputModel, this.view1, reader);

    inputController.launchProgram();
    assertEquals("Filter: 0.06250.1250.0625 Image name: test Dest name: blurTest"
                    + "Filter: -0.125-0.125-0.125 Image name: 2! Dest name: 5?"
                    + "Filter: 0.06250.1250.0625 Image name: pterodactyl Dest name: quail",
            this.log.toString());
  }

  // input testing for transform
  @Test
  public void testInputTransform() {
    Readable reader = new StringReader("transform sepia test sepiaTest transform " +
            "greyscale g reyscale transform sepia ! # q");
    this.inputController = new ImageManipulatorControllerImpl(this.inputModel, this.view1, reader);

    inputController.launchProgram();
    assertEquals("Transform: 0.3930.7690.189 Image name: test Dest name: sepiaTest"
                    + "Transform: 0.21260.71520.0722 Image name: g Dest name: reyscale"
                    + "Transform: 0.3930.7690.189 Image name: ! Dest name: #",
            this.log.toString());
  }

  // input testing for greyscale
  @Test
  public void testInputGreyscale() {
    Readable reader = new StringReader("greyscale red new newGrey " +
            "greyscale luma greyscale ggreyscale " +
            "greyscale value 1! $2 q");
    this.inputController = new ImageManipulatorControllerImpl(this.inputModel, this.view1, reader);

    inputController.launchProgram();
    assertEquals("Greyscale: red Image name: new Dest name: newGrey"
                    + "Greyscale: luma Image name: greyscale Dest name: ggreyscale"
                    + "Greyscale: value Image name: 1! Dest name: $2",
            this.log.toString());
  }

  // input testing for vertical flip
  @Test
  public void testInputVerticalFlip() {
    Readable reader = new StringReader("vertical-flip new vertNew " +
            "vertical-flip ! ^ " +
            "vertical-flip H3LL0 g00dby3 q");
    this.inputController = new ImageManipulatorControllerImpl(this.inputModel, this.view1, reader);

    inputController.launchProgram();
    assertEquals("Vert Flip Image name: new Dest name: vertNew"
                    + "Vert Flip Image name: ! Dest name: ^"
                    + "Vert Flip Image name: H3LL0 Dest name: g00dby3",
            this.log.toString());
  }

  // input testing for horizontal flip
  @Test
  public void testInputHorizontalFlip() {
    Readable reader = new StringReader("horizontal-flip new horNew " +
            "horizontal-flip 1 2 " +
            "horizontal-flip % $ q");
    this.inputController = new ImageManipulatorControllerImpl(this.inputModel, this.view1, reader);

    inputController.launchProgram();
    assertEquals("Hor Flip Image name: new Dest name: horNew"
                    + "Hor Flip Image name: 1 Dest name: 2"
                    + "Hor Flip Image name: % Dest name: $",
            this.log.toString());
  }

  // input testing for brightness
  @Test
  public void testInputBrighten() {
    Readable reader = new StringReader("brighten 10 img brightImg " +
            "brighten -4 1 2 " +
            "brighten 0 ] f q");
    this.inputController = new ImageManipulatorControllerImpl(this.inputModel, this.view1, reader);

    inputController.launchProgram();
    assertEquals("Increment: 10 Image name: img Dest name: brightImg"
                    + "Increment: -4 Image name: 1 Dest name: 2"
                    + "Increment: 0 Image name: ] Dest name: f",
            this.log.toString());
  }


}