import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;

import model.PPMImageManipulatorModel;
import model.Pixel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests for the PPMImageManipulatorModel class.
 */
public class PPMImageManipulatorModelTest {

  private final PrintStream standardOut = System.out;
  private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
  PPMImageManipulatorModel test;
  HashMap<String, Pixel[][]> files;
  PPMImageManipulatorModel test2;
  HashMap<String, Pixel[][]> files2;
  Pixel[][] image;
  // Pixels of "test.txt"
  Pixel one1;
  Pixel two1;
  Pixel three1;
  Pixel four1;
  Pixel five1;
  Pixel six1;
  // Pixels of "test2.txt"
  Pixel one2;
  Pixel two2;
  Pixel three2;
  Pixel four2;

  @Before
  public void setUp() {
    System.setOut(new PrintStream(outputStream));
  }

  @Before
  public void init() {
    this.files = new HashMap<String, Pixel[][]>();
    this.test = new PPMImageManipulatorModel(this.files);
    this.files2 = new HashMap<String, Pixel[][]>();
    this.test2 = new PPMImageManipulatorModel(this.files2);

    try {
      this.test.loadImage("test/testFiles/test.txt", "file1");
      this.image = this.files.get("file1");
    } catch (FileNotFoundException e) {
      fail("Shouldn't have thrown FileNotFoundException.");
    }

    // Initializing the pixels as variables for "test.txt"
    this.one1 = new Pixel(255, 0, 125);
    this.two1 = new Pixel(255, 0, 255);
    this.three1 = new Pixel(255, 125, 0);
    this.four1 = new Pixel(255, 255, 0);
    this.five1 = new Pixel(0, 255, 0);
    this.six1 = new Pixel(125, 0, 255);

    // Initializing the pixels as varaibles for "test2.txt"
    this.one2 = new Pixel(38, 31, 23);
    this.two2 = new Pixel(78, 29, 58);
    this.three2 = new Pixel(23, 41, 74);
    this.four2 = new Pixel(123, 215, 201);

  }

  @Test
  public void test123() {
    try {
      this.test.loadImage("test/testFiles/test2.txt", "file");
    } catch (Exception e) {
      fail(e.getMessage());
    }

    try {
      this.test.saveImage("test/testFiles/testTwo2.ppm", "file");
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }

  // tests the valid construction of a model.PPMImageManipulatorModel
  @Test
  public void testValidConstructor() {
    // Nothing has been done to test2, so it has no files
    // but just a new HashMap<String, model.Pixel[][]>();
    assertEquals(new HashMap<String, Pixel[][]>(), this.files2);
    // checking that since we have put in one file in this.test
    // that the size of the hashmap is one.
    assertEquals(1, this.files.size());

    // make sure 1 arg constructor throws an exception when given a null map.
    try {
      new PPMImageManipulatorModel(null);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Cannot have null files", e.getMessage());
    }

    // make sure the 1 arg constructor initializes correctly
    HashMap<String, Pixel[][]> newMap = new HashMap<String, Pixel[][]>();
    PPMImageManipulatorModel model =
            new PPMImageManipulatorModel(new HashMap<String, Pixel[][]>());

    assertEquals(this.files2, newMap);


  }

  // tests loading a file.
  @Test
  public void testLoadFile() {
    // Loading a normal PPM file

    try {
      this.test.loadImage("test/testFiles/try.txt", "file");
      Pixel[][] img = this.files.get("file");

      Pixel one = new Pixel(10, 12, 15);
      Pixel two = new Pixel(1, 4, 7);
      Pixel three = new Pixel(8, 26, 4);

      assertEquals(one, img[0][0]);
      assertEquals(two, img[0][1]);
      assertEquals(three, img[0][2]);


    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    try {
      this.test.loadImage("test/testFiles/test.txt", "file1");
      Pixel[][] temp1 = this.files.get("file1");

      assertEquals(this.one1, temp1[0][0]);
      assertEquals(this.two1, temp1[0][1]);
      assertEquals(this.three1, temp1[0][2]);
      assertEquals(this.four1, temp1[1][0]);
      assertEquals(this.five1, temp1[1][1]);
      assertEquals(this.six1, temp1[1][2]);
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    try {
      this.test.loadImage("test/testFiles/test2.txt", "file2");
      Pixel[][] temp2 = this.files.get("file2");
      assertEquals(this.one2, temp2[0][0]);
      assertEquals(this.two2, temp2[0][1]);
      assertEquals(this.three2, temp2[1][0]);
      assertEquals(this.four2, temp2[1][1]);
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException");
    }

    // Loading a non-ppm file

    try {
      this.test.loadImage("test/testFiles/notaPPM.txt", "fileNotPPM");
    } catch (IllegalArgumentException | IOException e) {
      // not a ppm file (starts with P2 not P3). Therefore, prints out
      // "Invalid PPM file: plain RAW file should begin with P3.
      assertEquals("Invalid PPM file: plain RAW file should begin with P3", e.getMessage());
    }

    try {
      this.test.loadImage("test/testFiles/drake.jpeg", "fileNotPPM");
      fail("Should have thrown an exception.");
    } catch (Exception e) {
      // not a ppm file (is jpeg). Therefore, prints out
      // "Invalid PPM file: plain RAW file should begin with P3.
    }

    // Loading a file that doesn't exist
    try {
      this.test.loadImage("test/testFiles/thisfiledoesntexistlol.txt", "file10");
      fail("Should have thrown a FileNotFoundException.");
    } catch (FileNotFoundException e) {
      // fails as the supplied file doesn't exist.
      assertEquals("Cannot find file", e.getMessage());
    }

    // loading an empty file

    try {
      this.test.loadImage("test/testFiles/empty.txt", "emptyFile");
      fail("Should have thrown an exception");
    } catch (Exception e) {
      // can't load as empty.
    }

    // Loading a file that has insufficient data

    try {
      this.test.loadImage("test/testFiles/Insufficient.txt", "insufficientFile");
      fail("Should have thrown an exception");
    } catch (Exception e) {
      // can't load as there is insufficient pixels
    }

    // Loading a file that doesn't set an image size
    try {
      this.test.loadImage("test/testFiles/nosize.txt", "noSize");
      fail("Should have thrown an exception");
    } catch (Exception e) {
      // can't load as there is insufficient pixels
    }

    try {
      HashMap<String, Pixel[][]> testMap = new HashMap<String, Pixel[][]>();
      PPMImageManipulatorModel override = new PPMImageManipulatorModel(testMap);
      override.loadImage("test/testFiles/test2.txt", "test");
      override.loadImage("test/testFiles/test.txt", "test");
      Pixel[][] temp = testMap.get("test");

      assertEquals(new Pixel(255, 0, 125), temp[0][0]);
      assertEquals(new Pixel(255, 0, 255), temp[0][1]);
      assertEquals(new Pixel(255, 125, 0), temp[0][2]);
      assertEquals(new Pixel(255, 255, 0), temp[1][0]);
      assertEquals(new Pixel(0, 255, 0), temp[1][1]);
      assertEquals(new Pixel(125, 0, 255), temp[1][2]);

    } catch (IOException e) {
      fail("Should not have thrown.");
    }
  }

  // tests the saveImage method
  @Test
  public void testSaveImage() {
    // saving a greyscale image
    try {
      this.test.loadImage("test/testFiles/test.txt", "file1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    this.test.greyscale("red", "file1", "greyscaleRed");

    try {
      this.test.saveImage("test/testFiles/greyscaleRedSaved", "greyscaleRed");
    } catch (IOException io) {
      fail("Should not have thrown IOException.");
    }

    try {
      this.test.loadImage("test/testFiles/greyscaleRedSaved", "result1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    Pixel[][] greyScaleResult = this.files.get("result1");
    assertEquals(new Pixel(255, 255, 255), greyScaleResult[0][0]);
    assertEquals(new Pixel(255, 255, 255), greyScaleResult[0][1]);
    assertEquals(new Pixel(255, 255, 255), greyScaleResult[0][2]);
    assertEquals(new Pixel(255, 255, 255), greyScaleResult[1][0]);
    assertEquals(new Pixel(0, 0, 0), greyScaleResult[1][1]);
    assertEquals(new Pixel(125, 125, 125), greyScaleResult[1][2]);


    // saving a brighten image
    try {
      this.test.loadImage("test/testFiles/test2.txt", "file2");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    this.test.brighten(100, "file2", "normalBrighten1");

    try {
      this.test.saveImage("test/testFiles/brightenSaved1", "normalBrighten1");
    } catch (IOException io) {
      fail("Should not have thrown IOException.");
    }

    try {
      this.test.loadImage("test/testFiles/brightenSaved1", "result2");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    Pixel[][] brightenSaved = this.files.get("result2");
    assertEquals(new Pixel(138, 131, 123), brightenSaved[0][0]);
    assertEquals(new Pixel(178, 129, 158), brightenSaved[0][1]);
    assertEquals(new Pixel(123, 141, 174), brightenSaved[1][0]);
    assertEquals(new Pixel(223, 255, 255), brightenSaved[1][1]);

    // saving a verticalFlip image
    try {
      this.test.loadImage("test/testFiles/test.txt", "file1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }
    this.test.verticalFlip("file1", "verticalFlip1");

    try {
      this.test.saveImage("test/testFiles/verticalFlipSaved1", "verticalFlip1");
    } catch (IOException io) {
      fail("Should not have thrown IOException.");
    }

    try {
      this.test.loadImage("test/testFiles/verticalFlipSaved1", "result3");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    Pixel[][] verticalFlipSaved1 = this.files.get("result3");

    assertEquals(this.four1, verticalFlipSaved1[0][0]);
    assertEquals(this.five1, verticalFlipSaved1[0][1]);
    assertEquals(this.six1, verticalFlipSaved1[0][2]);
    assertEquals(this.one1, verticalFlipSaved1[1][0]);
    assertEquals(this.two1, verticalFlipSaved1[1][1]);
    assertEquals(this.three1, verticalFlipSaved1[1][2]);

    // saving a horizontalFlip image
    try {
      this.test.loadImage("test/testFiles/test.txt", "file1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }
    this.test.horizontalFlip("file1", "horizontalFlip1");

    try {
      this.test.saveImage("test/testFiles/horizontalFlipSaved1", "horizontalFlip1");
    } catch (IOException io) {
      fail("Should not have thrown IOException.");
    }

    try {
      this.test.loadImage("test/testFiles/horizontalFlipSaved1", "result4");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    Pixel[][] horizontalSaved = this.files.get("result4");


    assertEquals(this.three1, horizontalSaved[0][0]);
    assertEquals(this.two1, horizontalSaved[0][1]);
    assertEquals(this.one1, horizontalSaved[0][2]);
    assertEquals(this.six1, horizontalSaved[1][0]);
    assertEquals(this.five1, horizontalSaved[1][1]);
    assertEquals(this.four1, horizontalSaved[1][2]);

    // test saving an image that hasn't been changed
    try {
      this.test.loadImage("test/testFiles/try.txt", "file");
      Pixel[][] img = this.files.get("file");

      Pixel one = new Pixel(10, 12, 15);
      Pixel two = new Pixel(1, 4, 7);
      Pixel three = new Pixel(8, 26, 4);

      assertEquals(one, img[0][0]);
      assertEquals(two, img[0][1]);
      assertEquals(three, img[0][2]);
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    try {
      this.test.saveImage("test/testFiles/tryUnchanged", "file");
    } catch (IOException io) {
      fail("Should not have thrown IOException.");
    }

    try {
      this.test.loadImage("test/testFiles/tryUnchanged", "tryUnchangedSaved");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    Pixel[][] unchanged = this.files.get("tryUnchangedSaved");
    Pixel one = new Pixel(10, 12, 15);
    Pixel two = new Pixel(1, 4, 7);
    Pixel three = new Pixel(8, 26, 4);

    assertEquals(one, unchanged[0][0]);
    assertEquals(two, unchanged[0][1]);
    assertEquals(three, unchanged[0][2]);

    // saving an image that doesn't exist.
    try {
      this.test.saveImage("test/testFiles/thisdoesntexist.txt", "nonexistant");
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException | IOException e) {
      assertEquals("Cannot find image.", e.getMessage());
    }

    // saving an image after two alterations.

    try {
      this.test.loadImage("test/testFiles/test.txt", "fileAltered2");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    this.test.greyscale("red", "fileAltered2", "fileAlteredGrey");
    this.test.brighten(10, "fileAlteredGrey", "fileAlteredGreyBrighten");
    try {
      this.test.saveImage("test/testFiles/fileAlteredGreyBrightenSaved",
              "fileAlteredGreyBrighten");
    } catch (IOException io) {
      fail("Should not have thrown IOException.");
    }

    try {
      this.test.loadImage("test/testFiles/fileAlteredGreyBrightenSaved",
              "resultTwoAlteration");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    Pixel[][] resultTwoAlteration = this.files.get("resultTwoAlteration");
    assertEquals(new Pixel(255, 255, 255), resultTwoAlteration[0][0]);
    assertEquals(new Pixel(255, 255, 255), resultTwoAlteration[0][1]);
    assertEquals(new Pixel(255, 255, 255), resultTwoAlteration[0][2]);
    assertEquals(new Pixel(255, 255, 255), resultTwoAlteration[1][0]);
    assertEquals(new Pixel(10, 10, 10), resultTwoAlteration[1][1]);
    assertEquals(new Pixel(135, 135, 135), resultTwoAlteration[1][2]);


  }

  // tests the greyscale method
  @Test
  public void testGreyscale() {
    // greyscale on non-existent file
    try {
      this.test.greyscale("luma", "filedoesntexist", "greyscaleLumaFail");
    } catch (IllegalArgumentException e) {
      // fails as file doesnt exist (model.Pixel[][] is null).
    }

    // Tests greyscale with red component

    try {
      this.test.loadImage("test/testFiles/test.txt", "file1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    this.test.greyscale("red", "file1", "greyscaleRed");
    Pixel[][] redGrey1 = this.files.get("greyscaleRed");

    assertEquals(new Pixel(255, 255, 255), redGrey1[0][0]);
    assertEquals(new Pixel(255, 255, 255), redGrey1[0][1]);
    assertEquals(new Pixel(255, 255, 255), redGrey1[0][2]);
    assertEquals(new Pixel(255, 255, 255), redGrey1[1][0]);
    assertEquals(new Pixel(0, 0, 0), redGrey1[1][1]);
    assertEquals(new Pixel(125, 125, 125), redGrey1[1][2]);

    // red component when model.Pixel[][] is null

    try {
      this.test.greyscale("red", "filedoesntexist", "greyscaleRedFail");
    } catch (IllegalArgumentException e) {
      // fails as file doesnt exist (model.Pixel[][] is null).
    }

    // Tests greyscale with green component

    try {
      this.test.loadImage("test/testFiles/test.txt", "file1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    this.test.greyscale("green", "file1", "greyscaleGreen");
    Pixel[][] greenGrey1 = this.files.get("greyscaleGreen");

    assertEquals(new Pixel(0, 0, 0), greenGrey1[0][0]);
    assertEquals(new Pixel(0, 0, 0), greenGrey1[0][1]);
    assertEquals(new Pixel(125, 125, 125), greenGrey1[0][2]);
    assertEquals(new Pixel(255, 255, 255), greenGrey1[1][0]);
    assertEquals(new Pixel(255, 255, 255), greenGrey1[1][1]);
    assertEquals(new Pixel(0, 0, 0), greenGrey1[1][2]);

    // green component when model.Pixel[][] is null

    try {
      this.test.greyscale("green", "filedoesntexist", "greyscaleGreenFail");
    } catch (IllegalArgumentException e) {
      // fails as file doesnt exist (model.Pixel[][] is null).
    }


    // Tests greyscale with blue component

    try {
      this.test.loadImage("test/testFiles/test.txt", "file1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    this.test.greyscale("blue", "file1", "greyscaleBlue");
    Pixel[][] blueGrey1 = this.files.get("greyscaleBlue");

    assertEquals(new Pixel(125, 125, 125), blueGrey1[0][0]);
    assertEquals(new Pixel(255, 255, 255), blueGrey1[0][1]);
    assertEquals(new Pixel(0, 0, 0), blueGrey1[0][2]);
    assertEquals(new Pixel(0, 0, 0), blueGrey1[1][0]);
    assertEquals(new Pixel(0, 0, 0), blueGrey1[1][1]);
    assertEquals(new Pixel(255, 255, 255), blueGrey1[1][2]);

    // blue component when model.Pixel[][] is null

    try {
      this.test.greyscale("blue", "filedoesntexist", "greyscaleBlueFail");
    } catch (IllegalArgumentException e) {
      // fails as file doesnt exist (model.Pixel[][] is null).
    }

    // Tests greyscale with value component

    try {
      this.test.loadImage("test/testFiles/test.txt", "file1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }
    this.test.greyscale("value", "file1", "greyscaleValue");
    Pixel[][] valueGrey1 = this.files.get("greyscaleValue");

    assertEquals(new Pixel(255, 255, 255), valueGrey1[0][0]);
    assertEquals(new Pixel(255, 255, 255), valueGrey1[0][1]);
    assertEquals(new Pixel(255, 255, 255), valueGrey1[0][2]);
    assertEquals(new Pixel(255, 255, 255), valueGrey1[1][0]);
    assertEquals(new Pixel(255, 255, 255), valueGrey1[1][1]);
    assertEquals(new Pixel(255, 255, 255), valueGrey1[1][2]);


    // Value component when model.Pixel[][] is null

    try {
      this.test.greyscale("value", "filedoesntexist", "greyscaleValueFail");
    } catch (IllegalArgumentException e) {
      // fails as file doesnt exist (model.Pixel[][] is null).
    }

    // Tests greyscale with intensity component

    try {
      this.test.loadImage("test/testFiles/test.txt", "file1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }
    this.test.greyscale("intensity", "file1", "greyscaleIntensity");
    Pixel[][] intensityGrey1 = this.files.get("greyscaleIntensity");

    assertEquals(new Pixel(380 / 3, 380 / 3, 380 / 3), intensityGrey1[0][0]);
    assertEquals(new Pixel(510 / 3, 510 / 3, 510 / 3), intensityGrey1[0][1]);
    assertEquals(new Pixel(380 / 3, 380 / 3, 380 / 3), intensityGrey1[0][2]);
    assertEquals(new Pixel(510 / 3, 510 / 3, 510 / 3), intensityGrey1[1][0]);
    assertEquals(new Pixel(255 / 3, 255 / 3, 255 / 3), intensityGrey1[1][1]);
    assertEquals(new Pixel(380 / 3, 380 / 3, 380 / 3), intensityGrey1[1][2]);

    // intensity component when model.Pixel[][] is null

    try {
      this.test.greyscale("intensity", "filedoesntexist", "greyscaleIntensityFail");
    } catch (IllegalArgumentException e) {
      // fails as file doesnt exist (model.Pixel[][] is null).
    }

    // Tests greyscale with luma component

    try {
      this.test.loadImage("test/testFiles/test.txt", "file1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }
    this.test.greyscale("luma", "file1", "greyscaleLuma");
    Pixel[][] lumaGrey1 = this.files.get("greyscaleLuma");

    assertEquals(new Pixel(63, 63, 63), lumaGrey1[0][0]);
    assertEquals(new Pixel(72, 72, 72), lumaGrey1[0][1]);
    assertEquals(new Pixel(143, 143, 143), lumaGrey1[0][2]);
    assertEquals(new Pixel(236, 236, 236), lumaGrey1[1][0]);
    assertEquals(new Pixel(182, 182, 182), lumaGrey1[1][1]);
    assertEquals(new Pixel(44, 44, 44), lumaGrey1[1][2]);


    try {
      this.test.loadImage("test/testFiles/try.txt", "file");
      Pixel[][] img = this.files.get("file");

      Pixel one = new Pixel(10, 12, 15);
      Pixel two = new Pixel(1, 4, 7);
      Pixel three = new Pixel(8, 26, 4);

      assertEquals(one, img[0][0]);
      assertEquals(two, img[0][1]);
      assertEquals(three, img[0][2]);


    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    this.test.greyscale("red", "file", "greyscaledVersion");

    Pixel[][] grey = this.files.get("greyscaledVersion");

    Pixel oneGrey = new Pixel(10, 10, 10);
    Pixel twoGrey = new Pixel(1, 1, 1);
    Pixel threeGrey = new Pixel(8, 8, 8);

    assertEquals(oneGrey, grey[0][0]);
    assertEquals(twoGrey, grey[0][1]);
    assertEquals(threeGrey, grey[0][2]);

  }

  // tests the Brighten method
  @Test
  public void testBrighten() {
    // brighten above max
    try {
      this.test.loadImage("test/testFiles/test2.txt", "file2");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    this.test.brighten(100, "file2", "normalBrighten1");
    Pixel[][] brighten1 = this.files.get("normalBrighten1");

    assertEquals(new Pixel(138, 131, 123), brighten1[0][0]);
    assertEquals(new Pixel(178, 129, 158), brighten1[0][1]);
    assertEquals(new Pixel(123, 141, 174), brighten1[1][0]);
    assertEquals(new Pixel(223, 255, 255), brighten1[1][1]);

    // negative brighten to negative pixel
    try {
      this.test.loadImage("test/testFiles/test2.txt", "file2");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    this.test.brighten(-1000, "file2", "normalBrighten1");
    Pixel[][] brightenToNegative = this.files.get("normalBrighten1");

    assertEquals(new Pixel(0, 0, 0), brightenToNegative[0][0]);
    assertEquals(new Pixel(0, 0, 0), brightenToNegative[0][1]);
    assertEquals(new Pixel(0, 0, 0), brightenToNegative[1][0]);
    assertEquals(new Pixel(0, 0, 0), brightenToNegative[1][1]);

    // negative brighten
    try {
      this.test.loadImage("test/testFiles/test2.txt", "file2");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    this.test.brighten(-20, "file2", "normalBrighten1");
    Pixel[][] brighten2 = this.files.get("normalBrighten1");

    assertEquals(new Pixel(18, 11, 3), brighten2[0][0]);
    assertEquals(new Pixel(58, 9, 38), brighten2[0][1]);
    assertEquals(new Pixel(3, 21, 54), brighten2[1][0]);
    assertEquals(new Pixel(103, 195, 181), brighten2[1][1]);

    try {
      this.test.loadImage("test/testFiles/test2.txt", "file2");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    // negative brighten below 0
    this.test.brighten(-100, "file2", "normalBrighten1");
    Pixel[][] brighten0 = this.files.get("normalBrighten1");

    assertEquals(new Pixel(0, 0, 0), brighten0[0][0]);
    assertEquals(new Pixel(0, 0, 0), brighten0[0][1]);
    assertEquals(new Pixel(0, 0, 0), brighten0[1][0]);
    assertEquals(new Pixel(23, 115, 101), brighten0[1][1]);


    // brighten by 0
    try {
      this.test.loadImage("test/testFiles/test2.txt", "file2");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    this.test.brighten(0, "file2", "normalBrighten1");
    Pixel[][] brighten3 = this.files.get("normalBrighten1");

    assertEquals(this.one2, brighten3[0][0]);
    assertEquals(this.two2, brighten3[0][1]);
    assertEquals(this.three2, brighten3[1][0]);
    assertEquals(this.four2, brighten3[1][1]);

    // normal brighten
    try {
      this.test.loadImage("test/testFiles/test2.txt", "file2");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    this.test.brighten(23, "file2", "normalBrighten1");
    Pixel[][] brighten4 = this.files.get("normalBrighten1");

    assertEquals(new Pixel(61, 54, 46), brighten4[0][0]);
    assertEquals(new Pixel(101, 52, 81), brighten4[0][1]);
    assertEquals(new Pixel(46, 64, 97), brighten4[1][0]);
    assertEquals(new Pixel(146, 238, 224), brighten4[1][1]);

    // Brighten when file doesn't exist (model.Pixel[][] is null)

    try {
      this.test.brighten(10, "filedoesntexist", "brightenfail");
    } catch (IllegalArgumentException e) {
      // fails as file doesnt exist (model.Pixel[][] is null).
    }
  }

  // tests the HorizontalFlip method
  @Test
  public void testHorizontalFlip() {
    // default Horizontal Flip
    try {
      this.test.loadImage("test/testFiles/test.txt", "file1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }
    this.test.horizontalFlip("file1", "horizontalFlip1");
    Pixel[][] horizontalFlip1 = this.files.get("horizontalFlip1");

    assertEquals(this.three1, horizontalFlip1[0][0]);
    assertEquals(this.two1, horizontalFlip1[0][1]);
    assertEquals(this.one1, horizontalFlip1[0][2]);
    assertEquals(this.six1, horizontalFlip1[1][0]);
    assertEquals(this.five1, horizontalFlip1[1][1]);
    assertEquals(this.four1, horizontalFlip1[1][2]);

    try {
      this.test.loadImage("test/testFiles/test2.txt", "file2");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    this.test.horizontalFlip("file2", "horizontalFlip2");
    Pixel[][] horizontalFlip2 = this.files.get("horizontalFlip2");

    assertEquals(this.two2, horizontalFlip2[0][0]);
    assertEquals(this.one2, horizontalFlip2[0][1]);
    assertEquals(this.four2, horizontalFlip2[1][0]);
    assertEquals(this.three2, horizontalFlip2[1][1]);

    // Brighten to horizontal Flip

    try {
      this.test.loadImage("test/testFiles/try.txt", "file");
      Pixel[][] img = this.files.get("file");

      Pixel one = new Pixel(10, 12, 15);
      Pixel two = new Pixel(1, 4, 7);
      Pixel three = new Pixel(8, 26, 4);

      assertEquals(one, img[0][0]);
      assertEquals(two, img[0][1]);
      assertEquals(three, img[0][2]);

    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    this.test.horizontalFlip("file", "horizontalTry");
    this.test.brighten(10, "horizontalTry", "horizontalToBrighten");

    Pixel one = new Pixel(20, 22, 25);
    Pixel two = new Pixel(11, 14, 17);
    Pixel three = new Pixel(18, 36, 14);

    Pixel[][] result1 = this.files.get("horizontalToBrighten");

    assertEquals(three, result1[0][0]);
    assertEquals(two, result1[0][1]);
    assertEquals(one, result1[0][2]);


    // horizontalFlip when file doesn't exist (model.Pixel[][] is null)

    try {
      this.test.horizontalFlip("filedoesntexist", "horizontalFlipFail");
    } catch (IllegalArgumentException e) {
      // fails as file doesnt exist (model.Pixel[][] is null).
    }

  }

  // Tests the verticalFlip method
  @Test
  public void testVerticalFlip() {
    // default Vertical Flip

    try {
      this.test.loadImage("test/testFiles/test.txt", "file1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }
    this.test.verticalFlip("file1", "verticalFlip1");
    Pixel[][] verticalFlip1 = this.files.get("verticalFlip1");

    assertEquals(this.four1, verticalFlip1[0][0]);
    assertEquals(this.five1, verticalFlip1[0][1]);
    assertEquals(this.six1, verticalFlip1[0][2]);
    assertEquals(this.one1, verticalFlip1[1][0]);
    assertEquals(this.two1, verticalFlip1[1][1]);
    assertEquals(this.three1, verticalFlip1[1][2]);


    try {
      this.test.loadImage("test/testFiles/test2.txt", "file2");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    this.test.verticalFlip("file2", "verticalFlip1");
    Pixel[][] verticalFlip2 = this.files.get("verticalFlip1");

    assertEquals(this.three2, verticalFlip2[0][0]);
    assertEquals(this.four2, verticalFlip2[0][1]);
    assertEquals(this.one2, verticalFlip2[1][0]);
    assertEquals(this.two2, verticalFlip2[1][1]);

    // verticalFlip when file doesn't exist (model.Pixel[][] is null)

    try {
      this.test.verticalFlip("filedoesntexist", "verticalFlipFail");
    } catch (IllegalArgumentException e) {
      // fails as file doesnt exist (model.Pixel[][] is null).
    }

    // Brighten to verticalFlip
    try {
      this.test.loadImage("test/testFiles/test.txt", "verticaltest");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }
    this.test.verticalFlip("verticaltest", "verticalresult1");
    this.test.brighten(20, "verticalresult1", "verticalToBrighten");

    Pixel[][] temp = this.files.get("verticalToBrighten");

    this.one1 = new Pixel(255, 20, 145);
    this.two1 = new Pixel(255, 20, 255);
    this.three1 = new Pixel(255, 145, 20);
    this.four1 = new Pixel(255, 255, 20);
    this.five1 = new Pixel(20, 255, 20);
    this.six1 = new Pixel(145, 20, 255);

    assertEquals(this.four1, temp[0][0]);
    assertEquals(this.five1, temp[0][1]);
    assertEquals(this.six1, temp[0][2]);
    assertEquals(this.one1, temp[1][0]);
    assertEquals(this.two1, temp[1][1]);
    assertEquals(this.three1, temp[1][2]);

  }
}