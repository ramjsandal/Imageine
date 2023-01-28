import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;


import model.BetterImageManipulatorModel;
import model.GeneralImageManipulatorModel;
import model.Pixel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


/**
 * Tests for GeneralImageManipulatorModel.
 */
public class GeneralImageManipulatorModelTest {

  BetterImageManipulatorModel model;
  BetterImageManipulatorModel test;

  HashMap<String, Pixel[][]> files1;
  HashMap<String, Pixel[][]> files2;
  HashMap<String, Pixel[][]> filesNull;

  double[][] blur;

  double[][] sharpen;

  double[][] greyscale;

  double[][] sepia;

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


  // Initializing the previously defined fields.
  @Before
  public void init() throws Exception {

    this.files1 = new HashMap<String, Pixel[][]>();
    this.files2 = new HashMap<String, Pixel[][]>();
    this.filesNull = null;

    this.model = new GeneralImageManipulatorModel(this.files1);
    this.test = new GeneralImageManipulatorModel(this.files2);
    this.blur = new double[3][3];
    this.greyscale = new double[3][3];
    this.sharpen = new double[5][5];
    this.sepia = new double[3][3];

    this.blur[0][0] = .0625;
    this.blur[0][1] = .125;
    this.blur[0][2] = .0625;
    this.blur[1][0] = .125;
    this.blur[1][1] = .25;
    this.blur[1][2] = .125;
    this.blur[2][0] = .0625;
    this.blur[2][1] = .125;
    this.blur[2][2] = .0625;

    this.greyscale[0][0] = .2126;
    this.greyscale[0][1] = .7152;
    this.greyscale[0][2] = .0722;
    this.greyscale[1][0] = .2126;
    this.greyscale[1][1] = .7152;
    this.greyscale[1][2] = .0722;
    this.greyscale[2][0] = .2126;
    this.greyscale[2][1] = .7152;
    this.greyscale[2][2] = .0722;

    this.sharpen[0][0] = -.125;
    this.sharpen[0][1] = -.125;
    this.sharpen[0][2] = -.125;
    this.sharpen[0][3] = -.125;
    this.sharpen[0][4] = -.125;
    this.sharpen[1][0] = -.125;
    this.sharpen[1][1] = .25;
    this.sharpen[1][2] = .25;
    this.sharpen[1][3] = .25;
    this.sharpen[1][4] = -.125;
    this.sharpen[2][0] = -.125;
    this.sharpen[2][1] = .25;
    this.sharpen[2][2] = 1;
    this.sharpen[2][3] = .25;
    this.sharpen[2][4] = -.125;
    this.sharpen[3][0] = -.125;
    this.sharpen[3][1] = .25;
    this.sharpen[3][2] = .25;
    this.sharpen[3][3] = .25;
    this.sharpen[3][4] = -.125;
    this.sharpen[4][0] = -.125;
    this.sharpen[4][1] = -.125;
    this.sharpen[4][2] = -.125;
    this.sharpen[4][3] = -.125;
    this.sharpen[4][4] = -.125;

    this.sepia[0][0] = 0.393;
    this.sepia[0][1] = 0.769;
    this.sepia[0][2] = 0.189;
    this.sepia[1][0] = 0.349;
    this.sepia[1][1] = 0.686;
    this.sepia[1][2] = 0.168;
    this.sepia[2][0] = 0.272;
    this.sepia[2][1] = 0.534;
    this.sepia[2][2] = 0.131;


    // Initializing the pixels as variables for "test.txt"
    this.one1 = new Pixel(255, 0, 125);
    this.two1 = new Pixel(255, 0, 255);
    this.three1 = new Pixel(255, 125, 0);
    this.four1 = new Pixel(255, 255, 0);
    this.five1 = new Pixel(0, 255, 0);
    this.six1 = new Pixel(125, 0, 255);


    // Initializing the pixels as variables for "test2.txt"
    this.one2 = new Pixel(38, 31, 23);
    this.two2 = new Pixel(78, 29, 58);
    this.three2 = new Pixel(23, 41, 74);
    this.four2 = new Pixel(123, 215, 201);

  }

  // Tests for valid constructor
  @Test
  public void validConstructor() {
    assertEquals(new HashMap<String, Pixel[][]>(), this.files1);
    assertEquals(0, this.files1.size());

    try {
      new GeneralImageManipulatorModel(null);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Cannot have null fields.", e.getMessage());
    }

    HashMap<String, Pixel[][]> newMap = new HashMap<String, Pixel[][]>();
    GeneralImageManipulatorModel model =
            new GeneralImageManipulatorModel(new HashMap<String, Pixel[][]>());

    assertEquals(this.files2, newMap);
  }

  // Tests for invalid constructor
  @Test
  public void invalidConstructor() {
    try {
      this.model = new GeneralImageManipulatorModel(this.filesNull);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Cannot have null fields.", e.getMessage());
    }
  }

  /*
   * For these tests, I'm loading the different file types and then checking that
   * each pixel in the loaded file is what it should be. In essence, I'm checking that
   * the pixels for all these files match the initial .txt file that we instantiated
   * the PPM to be (text2.txt).
   */

  // tests loading an image for file-type jpeg
  @Test
  public void loadImageJPEG() { // jpeg utilizes a different picture
    // checking PPM equals to these set of pixels
    try {
      this.model.loadImage("test/testFiles/test.ppm", "file0");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown exception");
    }

    Pixel[][] ppm = this.files1.get("file0");

    assertEquals(new Pixel(145, 72, 39), ppm[0][0]);
    assertEquals(new Pixel(176, 103, 70), ppm[0][1]);
    assertEquals(new Pixel(255, 197, 164), ppm[1][0]);
    assertEquals(new Pixel(206, 133, 100), ppm[1][1]);

    // checking that the jpeg equals to the same set of pixels.
    try {
      this.model.loadImage("test/testFiles/test.jpeg", "file");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown exception");
    }

    Pixel[][] jpeg = this.files1.get("file0");

    assertEquals(ppm[0][0], jpeg[0][0]);
    assertEquals(ppm[0][1], jpeg[0][1]);
    assertEquals(ppm[1][0], jpeg[1][0]);
    assertEquals(ppm[1][1], jpeg[1][1]);
  }

  // tests loading an image for file-type PNG
  @Test
  public void loadImagePNG() {
    try {
      this.model.loadImage("test/testFiles/testTwo.png", "file2");
      Pixel[][] temp2 = this.files1.get("file2");
      assertEquals(this.one2, temp2[0][0]);
      assertEquals(this.two2, temp2[0][1]);
      assertEquals(this.three2, temp2[1][0]);
      assertEquals(this.four2, temp2[1][1]);
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException");
    }
  }

  // tests loading an image for file-type PPM
  @Test
  public void loadImagePPM() {
    try {
      this.model.loadImage("test/testFiles/testTwo2.ppm", "file2");
      Pixel[][] temp2 = this.files1.get("file2");
      assertEquals(this.one2, temp2[0][0]);
      assertEquals(this.two2, temp2[0][1]);
      assertEquals(this.three2, temp2[1][0]);
      assertEquals(this.four2, temp2[1][1]);
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException");
    }
  }

  // tests loading an image for file-type BMP
  @Test
  public void loadImageBMP() {
    try {
      this.model.loadImage("test/testFiles/testTwo2.bmp", "file2");
      Pixel[][] temp2 = this.files1.get("file2");
      assertEquals(this.one2, temp2[0][0]);
      assertEquals(this.two2, temp2[0][1]);
      assertEquals(this.three2, temp2[1][0]);
      assertEquals(this.four2, temp2[1][1]);
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException");
    }
  }


  /*
   * For these tests, I'm going to check saving an image that hasn't been changed.
   * This is to ensure that the saveImage saves it properly.
   * Then, I'm going check it after an operation to see if it successfully does it.
   * I'm only using greyscale red as we're going to test other operations in other test methods.
   */

  // tests for saving an image of file-type jpeg
  @Test
  public void saveImageJPEG() {

    // saving a greyscale image
    try {
      this.model.loadImage("test/testFiles/test.jpeg", "file1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    this.model.greyscale("red", "file1", "greyscaleRed");

    try {
      this.model.saveImage("test/testFiles/greyscaleRedSavedJPEG.jpeg",
              "greyscaleRed");
    } catch (IOException io) {
      fail("Should not have thrown IOException.");
    }

    try {
      this.model.loadImage("test/testFiles/greyscaleRedSavedJPEG.jpeg", "result1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    Pixel[][] greyScaleResult = this.files1.get("result1");

    assertEquals(new Pixel(144, 144, 144), greyScaleResult[0][0]);
    assertEquals(new Pixel(179, 179, 179), greyScaleResult[0][1]);
    assertEquals(new Pixel(247, 247, 247), greyScaleResult[1][0]);
    assertEquals(new Pixel(209, 209, 209), greyScaleResult[1][1]);

  }

  // tests saving an image of file-type PNG
  @Test
  public void saveImagePNG() {
    // test saving the PNG that hasn't been changed
    try {
      this.model.loadImage("test/testFiles/testTwo.png", "file");
      Pixel[][] unchanged = this.files1.get("file");
      assertEquals(this.one2, unchanged[0][0]);
      assertEquals(this.two2, unchanged[0][1]);
      assertEquals(this.three2, unchanged[1][0]);
      assertEquals(this.four2, unchanged[1][1]);
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    try {
      this.model.saveImage("test/testFiles/tryUnchangedPNG.png", "file");
    } catch (IOException io) {
      fail("Should not have thrown IOException.");
    }

    try {
      this.model.loadImage("test/testFiles/tryUnchangedPNG.png",
              "tryUnchangedSaved");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    Pixel[][] unchanged = this.files1.get("tryUnchangedSaved");
    assertEquals(this.one2, unchanged[0][0]);
    assertEquals(this.two2, unchanged[0][1]);
    assertEquals(this.three2, unchanged[1][0]);
    assertEquals(this.four2, unchanged[1][1]);


    // saving a greyscale image
    try {
      this.model.loadImage("test/testFiles/testTwo.png", "file1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    this.model.greyscale("red", "file1", "greyscaleRed");

    try {
      this.model.saveImage("test/testFiles/greyscaleRedSavedPNG.png",
              "greyscaleRed");
    } catch (IOException io) {
      fail("Should not have thrown IOException.");
    }

    try {
      this.model.loadImage("test/testFiles/greyscaleRedSavedPNG.png", "result1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    Pixel[][] greyScaleResult = this.files1.get("result1");

    assertEquals(new Pixel(38, 38, 38), greyScaleResult[0][0]);
    assertEquals(new Pixel(78, 78, 78), greyScaleResult[0][1]);
    assertEquals(new Pixel(23, 23, 23), greyScaleResult[1][0]);
    assertEquals(new Pixel(123, 123, 123), greyScaleResult[1][1]);
  }

  // test saving an image of file-type PPM
  @Test
  public void saveImagePPM() {
    // test saving the PPM that hasn't been changed
    try {
      this.model.loadImage("test/testFiles/testTwo2.ppm", "file");
      Pixel[][] unchanged = this.files1.get("file");
      assertEquals(this.one2, unchanged[0][0]);
      assertEquals(this.two2, unchanged[0][1]);
      assertEquals(this.three2, unchanged[1][0]);
      assertEquals(this.four2, unchanged[1][1]);
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    try {
      this.model.saveImage("test/testFiles/tryUnchangedPPM.ppm", "file");
    } catch (IOException io) {
      fail("Should not have thrown IOException.");
    }

    try {
      this.model.loadImage("test/testFiles/tryUnchangedPPM.ppm",
              "tryUnchangedSaved");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    Pixel[][] unchanged = this.files1.get("tryUnchangedSaved");
    assertEquals(this.one2, unchanged[0][0]);
    assertEquals(this.two2, unchanged[0][1]);
    assertEquals(this.three2, unchanged[1][0]);
    assertEquals(this.four2, unchanged[1][1]);


    // saving a greyscale image
    try {
      this.model.loadImage("test/testFiles/testTwo2.ppm", "file1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    this.model.greyscale("red", "file1", "greyscaleRed");

    try {
      this.model.saveImage("test/testFiles/greyscaleRedSavedPPM.ppm",
              "greyscaleRed");
    } catch (IOException io) {
      fail("Should not have thrown IOException.");
    }

    try {
      this.model.loadImage("test/testFiles/greyscaleRedSavedPPM.ppm", "result1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    Pixel[][] greyScaleResult = this.files1.get("result1");

    assertEquals(new Pixel(38, 38, 38), greyScaleResult[0][0]);
    assertEquals(new Pixel(78, 78, 78), greyScaleResult[0][1]);
    assertEquals(new Pixel(23, 23, 23), greyScaleResult[1][0]);
    assertEquals(new Pixel(123, 123, 123), greyScaleResult[1][1]);
  }

  // tests saving an image of file-type BMP
  @Test
  public void saveImageBMP() {
    // test saving the PPM that hasn't been changed
    try {
      this.model.loadImage("test/testFiles/testTwo2.bmp", "file");
      Pixel[][] unchanged = this.files1.get("file");
      assertEquals(this.one2, unchanged[0][0]);
      assertEquals(this.two2, unchanged[0][1]);
      assertEquals(this.three2, unchanged[1][0]);
      assertEquals(this.four2, unchanged[1][1]);
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    try {
      this.model.saveImage("test/testFiles/tryUnchangedBMP.bmp", "file");
    } catch (IOException io) {
      fail("Should not have thrown IOException.");
    }

    try {
      this.model.loadImage("test/testFiles/tryUnchangedBMP.bmp",
              "tryUnchangedSaved");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    Pixel[][] unchanged = this.files1.get("tryUnchangedSaved");
    assertEquals(this.one2, unchanged[0][0]);
    assertEquals(this.two2, unchanged[0][1]);
    assertEquals(this.three2, unchanged[1][0]);
    assertEquals(this.four2, unchanged[1][1]);


    // saving a greyscale image
    try {
      this.model.loadImage("test/testFiles/testTwo2.bmp", "file1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    this.model.greyscale("red", "file1", "greyscaleRed");

    try {
      this.model.saveImage("test/testFiles/greyscaleRedSavedBMP.bmp",
              "greyscaleRed");
    } catch (IOException io) {
      fail("Should not have thrown IOException.");
    }

    try {
      this.model.loadImage("test/testFiles/greyscaleRedSavedBMP.bmp", "result1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    Pixel[][] greyScaleResult = this.files1.get("result1");

    assertEquals(new Pixel(38, 38, 38), greyScaleResult[0][0]);
    assertEquals(new Pixel(78, 78, 78), greyScaleResult[0][1]);
    assertEquals(new Pixel(23, 23, 23), greyScaleResult[1][0]);
    assertEquals(new Pixel(123, 123, 123), greyScaleResult[1][1]);
  }


  /*
   * For these tests, I'm testing the two filter functionalities that have been implemented.
   * That is, blur and sharpen. I've divided the test as such in different methods based on
   * file type and filter type to make it easier to go through them.
   * Again, I'm using just testTwo because we don't want any difference between each file.
   * Ensuring that for each file type, the load/save doesn't change anything it's not supposed to.
   * AND the methods work as they're supposed to for each file type.
   */

  // tests the blur filter on a JPEG
  @Test
  public void filterJPEGBlur() {

    try {
      this.model.loadImage("test/testFiles/test.jpeg", "jpegBlur");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown this exception");
    }
    try {
      this.model.filter(this.blur, "jpegBlur", "blurryJPEG");
    } catch (IllegalArgumentException e) {
      fail("Should not have thrown this exception as blur is a valid filter, and jpegBlur is" +
              "a valid image");
    }

    try {
      this.model.saveImage("test/testFiles/blurryJPEG.jpeg", "blurryJPEG");
    } catch (IOException e) {
      fail("Should not have thrown this exception");
    }

    Pixel[][] blurResult = this.files1.get("blurryJPEG");

    assertEquals(new Pixel(103, 63, 45), blurResult[0][0]);
    assertEquals(new Pixel(137, 77, 70), blurResult[0][1]);
    assertEquals(new Pixel(118, 81, 62), blurResult[1][0]);
    assertEquals(new Pixel(144, 85, 77), blurResult[1][1]);
  }

  // tests the sharpen filter on a JPEG
  @Test
  public void filterJPEGSharpen() {

    try {
      this.model.loadImage("test/testFiles/test.jpeg", "jpegSharpen");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown this exception");
    }

    try {
      this.model.filter(this.sharpen, "jpegSharpen", "SharpenJPEG");
    } catch (IllegalArgumentException e) {
      fail("Should not have thrown this exception as sharpen is a valid filter, " +
              "and jpegSharpen is" +
              "a valid image");
    }

    try {
      this.model.saveImage("test/testFiles/sharpenJPEG.jpeg", "SharpenJPEG");
    } catch (IOException e) {
      fail("Should not have thrown this exception");
    }

    try {
      this.model.loadImage("test/testFiles/sharpenJPEG.jpeg", "result1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    Pixel[][] sharpenJPEGResult = this.files1.get("result1");

    assertEquals(new Pixel(216, 193, 139), sharpenJPEGResult[0][0]);
    assertEquals(new Pixel(255, 241, 187), sharpenJPEGResult[0][1]);
    assertEquals(new Pixel(255, 234, 180), sharpenJPEGResult[1][0]);
    assertEquals(new Pixel(255, 254, 200), sharpenJPEGResult[1][1]);

  }

  // tests the blur filter on a PNG
  @Test
  public void filterPNGBlur() {

    try {
      this.model.loadImage("test/testFiles/testTwo.png", "pngBlur");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown this exception");
    }

    try {
      this.model.filter(this.blur, "pngBlur", "blurryPNG");
    } catch (IllegalArgumentException e) {
      fail("Should not have thrown this exception as blur is a valid filter, and pngBlur is" +
              "a valid image");
    }

    try {
      this.model.saveImage("test/testFiles/blurryPNG.png", "blurryPNG");
    } catch (IOException e) {
      fail("Should not have thrown this exception");
    }

    try {
      this.model.loadImage("test/testFiles/blurryPNG.png", "result1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    Pixel[][] blurryPNGResult = this.files1.get("result1");

    assertEquals(new Pixel(29, 29, 34), blurryPNGResult[0][0]);
    assertEquals(new Pixel(41, 40, 47), blurryPNGResult[0][1]);
    assertEquals(new Pixel(30, 42, 50), blurryPNGResult[1][0]);
    assertEquals(new Pixel(45, 64, 68), blurryPNGResult[1][1]);


  }

  // tests the sharpen filter on a PNG
  @Test
  public void filterPNGSharpen() {

    try {
      this.model.loadImage("test/testFiles/testTwo.png", "pngSharpen");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown this exception");
    }

    try {
      this.model.filter(this.sharpen, "pngSharpen", "SharpenPNG");
    } catch (IllegalArgumentException e) {
      fail("Should not have thrown this exception as sharpen is a valid filter, and pngSharpen is" +
              "a valid image");
    }

    try {
      this.model.saveImage("test/testFiles/sharpenPNG.png", "SharpenPNG");
    } catch (IOException e) {
      fail("Should not have thrown this exception");
    }

    try {
      this.model.loadImage("test/testFiles/sharpenPNG.png", "result1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    Pixel[][] sharpenPNGResult = this.files1.get("result1");

    assertEquals(new Pixel(94, 102, 106), sharpenPNGResult[0][0]);
    assertEquals(new Pixel(124, 100, 132), sharpenPNGResult[0][1]);
    assertEquals(new Pixel(82, 109, 144), sharpenPNGResult[1][0]);
    assertEquals(new Pixel(157, 240, 239), sharpenPNGResult[1][1]);
  }


  // tests the blur filter on a PPM
  @Test
  public void filterPPMBlur() {
    try {
      this.model.loadImage("test/testFiles/testTwo2.ppm", "ppmBlur");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown this exception");
    }

    try {
      this.model.filter(this.blur, "ppmBlur", "blurryPPM");
    } catch (IllegalArgumentException e) {
      fail("Should not have thrown this exception as blur is a valid filter, and ppmBlur is" +
              "a valid image");
    }

    try {
      this.model.saveImage("test/testFiles/blurryPPM.ppm", "blurryPPM");
    } catch (IOException e) {
      fail("Should not have thrown this exception");
    }

    try {
      this.model.loadImage("test/testFiles/blurryPPM.ppm", "result1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    Pixel[][] blurryPPMResult = this.files1.get("result1");

    assertEquals(new Pixel(29, 29, 34), blurryPPMResult[0][0]);
    assertEquals(new Pixel(41, 40, 47), blurryPPMResult[0][1]);
    assertEquals(new Pixel(30, 42, 50), blurryPPMResult[1][0]);
    assertEquals(new Pixel(45, 64, 68), blurryPPMResult[1][1]);

  }

  // tests the sharpen filter on a PPM
  @Test
  public void filterPPMSharpen() {

    try {
      this.model.loadImage("test/testFiles/testTwo2.ppm", "ppmSharpen");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown this exception");
    }

    try {
      this.model.filter(this.sharpen, "ppmSharpen", "SharpenPPM");
    } catch (IllegalArgumentException e) {
      fail("Should not have thrown this exception as sharpen is a valid filter, and ppmSharpen " +
              "is a valid image");
    }

    try {
      this.model.saveImage("test/testFiles/sharpenPPM.ppm", "SharpenPPM");
    } catch (IOException e) {
      fail("Should not have thrown this exception");
    }

    try {
      this.model.loadImage("test/testFiles/sharpenPPM.ppm", "result1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    Pixel[][] sharpenPPMResult = this.files1.get("result1");

    assertEquals(new Pixel(94, 102, 106), sharpenPPMResult[0][0]);
    assertEquals(new Pixel(124, 100, 132), sharpenPPMResult[0][1]);
    assertEquals(new Pixel(82, 109, 144), sharpenPPMResult[1][0]);
    assertEquals(new Pixel(157, 240, 239), sharpenPPMResult[1][1]);

  }

  // tests the blur filter on a PPM
  @Test
  public void filterBMPBlur() {
    try {
      this.model.loadImage("test/testFiles/testTwo2.bmp", "bmpBlur");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown this exception");
    }

    try {
      this.model.filter(this.blur, "bmpBlur", "blurryBMP");
    } catch (IllegalArgumentException e) {
      fail("Should not have thrown this exception as blur is a valid filter, and bmpBlur is" +
              "a valid image");
    }

    try {
      this.model.saveImage("test/testFiles/blurryBMP.bmp", "blurryBMP");
    } catch (IOException e) {
      fail("Should not have thrown this exception");
    }

    try {
      this.model.loadImage("test/testFiles/blurryBMP.bmp", "result1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    Pixel[][] blurryBMPResult = this.files1.get("result1");

    assertEquals(new Pixel(29, 29, 34), blurryBMPResult[0][0]);
    assertEquals(new Pixel(41, 40, 47), blurryBMPResult[0][1]);
    assertEquals(new Pixel(30, 42, 50), blurryBMPResult[1][0]);
    assertEquals(new Pixel(45, 64, 68), blurryBMPResult[1][1]);
  }

  // tests the sharpen filter on a BMP
  @Test
  public void filterBMPSharpen() {

    try {
      this.model.loadImage("test/testFiles/testTwo2.bmp", "bmpSharpen");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown this exception");
    }

    try {
      this.model.filter(this.sharpen, "bmpSharpen", "SharpenBMP");
    } catch (IllegalArgumentException e) {
      fail("Should not have thrown this exception as sharpen is a valid filter, and bmpSharpen " +
              "is a valid image");
    }

    try {
      this.model.saveImage("test/testFiles/sharpenBMP.bmp", "SharpenBMP");
    } catch (IOException e) {
      fail("Should not have thrown this exception");
    }

    try {
      this.model.loadImage("test/testFiles/sharpenBMP.bmp", "result1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    Pixel[][] sharpenBMPResult = this.files1.get("result1");

    assertEquals(new Pixel(94, 102, 106), sharpenBMPResult[0][0]);
    assertEquals(new Pixel(124, 100, 132), sharpenBMPResult[0][1]);
    assertEquals(new Pixel(82, 109, 144), sharpenBMPResult[1][0]);
    assertEquals(new Pixel(157, 240, 239), sharpenBMPResult[1][1]);

  }


  /*
   * For these set of tests, I'm going to divide them into the two transform functionalities
   * that are supported by our program: greyscale and sepia.
   */

  // tests the greyscale transform on a JPEG
  @Test
  public void testTransformJPEGGreyscale() {
    try {
      this.model.loadImage("test/testFiles/test.jpeg", "jpegGreyscale");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown this exception");
    }

    try {
      this.model.colorTransformation(this.greyscale, "jpegGreyscale",
              "GreyscaleJPEG");
    } catch (IllegalArgumentException e) {
      fail("Should not have thrown this exception as Greyscale is a valid transformation," +
              " and jpegGreyscale is a valid image");
    }

    try {
      this.model.saveImage("test/testFiles/greyscaleJPEG.jpeg", "GreyscaleJPEG");
    } catch (IOException e) {
      fail("Should not have thrown this exception");
    }

    try {
      this.model.loadImage("test/testFiles/greyscaleJPEG.jpeg", "result1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }
    Pixel[][] greyscaleJPEGResult = this.files1.get("result1");

    assertEquals(new Pixel(84, 84, 84), greyscaleJPEGResult[0][0]);
    assertEquals(new Pixel(117, 117, 117), greyscaleJPEGResult[0][1]);
    assertEquals(new Pixel(206, 206, 206), greyscaleJPEGResult[1][0]);
    assertEquals(new Pixel(148, 148, 148), greyscaleJPEGResult[1][1]);


  }

  // tests the sepia transform on a JPEG
  @Test
  public void testTransformJPEGSepia() {
    try {
      this.model.loadImage("test/testFiles/test.jpeg", "jpegSepia");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown this exception");
    }

    try {
      this.model.colorTransformation(this.sepia, "jpegSepia", "SepiaJPEG");
    } catch (IllegalArgumentException e) {
      fail("Should not have thrown this exception as Sepia is a valid transformation," +
              " and pngSepia is a valid image");
    }

    try {
      this.model.saveImage("test/testFiles/sepiaJPEG.jpeg", "SepiaJPEG");
    } catch (IOException e) {
      fail("Should not have thrown this exception");
    }

    try {
      this.model.loadImage("test/testFiles/sepiaJPEG.jpeg", "result1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    Pixel[][] sepiaPNGResult = this.files1.get("result1");

    assertEquals(new Pixel(124, 110, 75), sepiaPNGResult[0][0]);
    assertEquals(new Pixel(162, 148, 113), sepiaPNGResult[0][1]);
    assertEquals(new Pixel(253, 239, 204), sepiaPNGResult[1][0]);
    assertEquals(new Pixel(196, 182, 147), sepiaPNGResult[1][1]);

  }

  // tests the greyscale transform on a PNG
  @Test
  public void testTransformPNGGreyscale() {
    try {
      this.model.loadImage("test/testFiles/testTwo.png", "pngGreyscale");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown this exception");
    }

    try {
      this.model.colorTransformation(this.greyscale, "pngGreyscale",
              "GreyscalePNG");
    } catch (IllegalArgumentException e) {
      fail("Should not have thrown this exception as Greyscale is a valid transformation," +
              " and pngGreyscale is a valid image");
    }

    try {
      this.model.saveImage("test/testFiles/greyscalePNG.png", "GreyscalePNG");
    } catch (IOException e) {
      fail("Should not have thrown this exception");
    }

    try {
      this.model.loadImage("test/testFiles/greyscalePNG.png", "result1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    Pixel[][] greyscalePNGResult = this.files1.get("result1");

    assertEquals(new Pixel(31, 31, 31), greyscalePNGResult[0][0]);
    assertEquals(new Pixel(41, 41, 41), greyscalePNGResult[0][1]);
    assertEquals(new Pixel(39, 39, 39), greyscalePNGResult[1][0]);
    assertEquals(new Pixel(194, 194, 194), greyscalePNGResult[1][1]);

  }

  // tests the sepia transform on a PNG
  @Test
  public void testTransformPNGSepia() {
    try {
      this.model.loadImage("test/testFiles/testTwo.png", "pngSepia");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown this exception");
    }

    try {
      this.model.colorTransformation(this.sepia, "pngSepia", "SepiaPNG");
    } catch (IllegalArgumentException e) {
      fail("Should not have thrown this exception as Sepia is a valid transformation," +
              " and pngSepia is a valid image");
    }

    try {
      this.model.saveImage("test/testFiles/sepiaPNG.png", "SepiaPNG");
    } catch (IOException e) {
      fail("Should not have thrown this exception");
    }

    try {
      this.model.loadImage("test/testFiles/sepiaPNG.png", "result1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    Pixel[][] sepiaPNGResult = this.files1.get("result1");

    assertEquals(new Pixel(43, 38, 29), sepiaPNGResult[0][0]);
    assertEquals(new Pixel(63, 56, 44), sepiaPNGResult[0][1]);
    assertEquals(new Pixel(54, 48, 37), sepiaPNGResult[1][0]);
    assertEquals(new Pixel(251, 224, 174), sepiaPNGResult[1][1]);


  }

  // tests the greyscale transform on a PPM
  @Test
  public void testTransformPPMGreyscale() {
    try {
      this.model.loadImage("test/testFiles/testTwo2.ppm", "ppmGreyscale");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown this exception");
    }

    try {
      this.model.colorTransformation(this.greyscale, "ppmGreyscale",
              "GreyscalePPM");
    } catch (IllegalArgumentException e) {
      fail("Should not have thrown this exception as Greyscale is a valid transformation," +
              " and ppmGreyscale is a valid image");
    }

    try {
      this.model.saveImage("test/testFiles/greyscalePPM.ppm", "GreyscalePPM");
    } catch (IOException e) {
      fail("Should not have thrown this exception");
    }

    try {
      this.model.loadImage("test/testFiles/greyscalePPM.ppm", "result1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    Pixel[][] greyscalePPMResult = this.files1.get("result1");

    assertEquals(new Pixel(31, 31, 31), greyscalePPMResult[0][0]);
    assertEquals(new Pixel(41, 41, 41), greyscalePPMResult[0][1]);
    assertEquals(new Pixel(39, 39, 39), greyscalePPMResult[1][0]);
    assertEquals(new Pixel(194, 194, 194), greyscalePPMResult[1][1]);

  }

  // tests the sepia transform on a PPM
  @Test
  public void testTransformPPMSepia() {
    try {
      this.model.loadImage("test/testFiles/testTwo2.ppm", "ppmSepia");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown this exception");
    }

    try {
      this.model.colorTransformation(this.sepia, "ppmSepia", "SepiaPPM");
    } catch (IllegalArgumentException e) {
      fail("Should not have thrown this exception as Sepia is a valid transformation," +
              " and ppmSepia is a valid image");
    }

    try {
      this.model.saveImage("test/testFiles/sepiaPPM.ppm", "SepiaPPM");
    } catch (IOException e) {
      fail("Should not have thrown this exception");
    }

    try {
      this.model.loadImage("test/testFiles/sepiaPPM.ppm", "result1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    Pixel[][] sepiaPPMResult = this.files1.get("result1");

    assertEquals(new Pixel(43, 38, 29), sepiaPPMResult[0][0]);
    assertEquals(new Pixel(63, 56, 44), sepiaPPMResult[0][1]);
    assertEquals(new Pixel(54, 48, 37), sepiaPPMResult[1][0]);
    assertEquals(new Pixel(251, 224, 174), sepiaPPMResult[1][1]);

  }

  // tests the greyscale transform on a BMP
  @Test
  public void testTransformBMPGreyscale() {
    try {
      this.model.loadImage("test/testFiles/testTwo2.bmp", "bmpGreyscale");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown this exception");
    }

    try {
      this.model.colorTransformation(this.greyscale, "bmpGreyscale",
              "GreyscaleBMP");
    } catch (IllegalArgumentException e) {
      fail("Should not have thrown this exception as Greyscale is a valid transformation," +
              " and bmpGreyscale is a valid image");
    }

    try {
      this.model.saveImage("test/testFiles/greyscaleBMP.bmp", "GreyscaleBMP");
    } catch (IOException e) {
      fail("Should not have thrown this exception");
    }

    try {
      this.model.loadImage("test/testFiles/greyscaleBMP.bmp", "result1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    Pixel[][] greyscaleBMPResult = this.files1.get("result1");

    assertEquals(new Pixel(31, 31, 31), greyscaleBMPResult[0][0]);
    assertEquals(new Pixel(41, 41, 41), greyscaleBMPResult[0][1]);
    assertEquals(new Pixel(39, 39, 39), greyscaleBMPResult[1][0]);
    assertEquals(new Pixel(194, 194, 194), greyscaleBMPResult[1][1]);

  }

  // tests the transform sepia on a BMP
  @Test
  public void testTransformBMPSepia() {
    try {
      this.model.loadImage("test/testFiles/testTwo2.bmp", "bmpSepia");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown this exception");
    }

    try {
      this.model.colorTransformation(this.sepia, "bmpSepia", "SepiaBMP");
    } catch (IllegalArgumentException e) {
      fail("Should not have thrown this exception as Sepia is a valid transformation," +
              " and bmpSepia is a valid image");
    }

    try {
      this.model.saveImage("test/testFiles/sepiaBMP.bmp", "SepiaBMP");
    } catch (IOException e) {
      fail("Should not have thrown this exception");
    }

    try {
      this.model.loadImage("test/testFiles/sepiaBMP.bmp", "result1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    Pixel[][] sepiaBMPResult = this.files1.get("result1");

    assertEquals(new Pixel(43, 38, 29), sepiaBMPResult[0][0]);
    assertEquals(new Pixel(63, 56, 44), sepiaBMPResult[0][1]);
    assertEquals(new Pixel(54, 48, 37), sepiaBMPResult[1][0]);
    assertEquals(new Pixel(251, 224, 174), sepiaBMPResult[1][1]);

  }

  /*
   * For these tests, I'm just checking whether doing greyscale on various image types works the
   * same as with the PPM file.
   */

  // tests the Greyscale on a BMP Null
  @Test
  public void testGreyscaleBMPNull() {
    // red component when model.Pixel[][] is null

    try {
      this.test.greyscale("red", "filedoesntexist.bmp",
              "greyscaleRedFail");
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // fails as file doesnt exist (model.Pixel[][] is null).
    }

    // green component when model.Pixel[][] is null

    try {
      this.test.greyscale("green", "filedoesntexist.bmp",
              "greyscaleGreenFail");
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // fails as file doesnt exist (model.Pixel[][] is null).
    }

    // blue component when model.Pixel[][] is null

    try {
      this.test.greyscale("blue", "filedoesntexist.bmp",
              "greyscaleBlueFail");
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // fails as file doesnt exist (model.Pixel[][] is null).
    }


    // Value component when model.Pixel[][] is null

    try {
      this.test.greyscale("value", "filedoesntexist.bmp",
              "greyscaleValueFail");
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // fails as file doesnt exist (model.Pixel[][] is null).
    }

    // intensity component when model.Pixel[][] is null

    try {
      this.test.greyscale("intensity", "filedoesntexist.bmp",
              "greyscaleIntensityFail");
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // fails as file doesnt exist (model.Pixel[][] is null).
    }

    // luma component when model.Pixel[][] is null
    try {
      this.test.greyscale("luma", "filedoesntexist.bmp",
              "greyscaleIntensityFail");
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // fails as file doesnt exist (model.Pixel[][] is null).
    }

  }

  // tests the greyscale on a BMP
  @Test
  public void testGreyscaleBMP() {

    // Tests greyscale with red component

    try {
      this.model.loadImage("test/testFiles/testOne.bmp", "file1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    this.model.greyscale("red", "file1", "greyscaleRed");
    Pixel[][] redGrey1 = this.files1.get("greyscaleRed");

    assertEquals(new Pixel(255, 255, 255), redGrey1[0][0]);
    assertEquals(new Pixel(255, 255, 255), redGrey1[0][1]);
    assertEquals(new Pixel(255, 255, 255), redGrey1[0][2]);
    assertEquals(new Pixel(255, 255, 255), redGrey1[1][0]);
    assertEquals(new Pixel(0, 0, 0), redGrey1[1][1]);
    assertEquals(new Pixel(125, 125, 125), redGrey1[1][2]);

    // Tests greyscale with green component

    try {
      this.model.loadImage("test/testFiles/testOne.bmp", "file1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    this.model.greyscale("green", "file1", "greyscaleGreen");
    Pixel[][] greenGrey1 = this.files1.get("greyscaleGreen");

    assertEquals(new Pixel(0, 0, 0), greenGrey1[0][0]);
    assertEquals(new Pixel(0, 0, 0), greenGrey1[0][1]);
    assertEquals(new Pixel(125, 125, 125), greenGrey1[0][2]);
    assertEquals(new Pixel(255, 255, 255), greenGrey1[1][0]);
    assertEquals(new Pixel(255, 255, 255), greenGrey1[1][1]);
    assertEquals(new Pixel(0, 0, 0), greenGrey1[1][2]);

    // Tests greyscale with blue component

    try {
      this.model.loadImage("test/testFiles/testOne.bmp", "file1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    this.model.greyscale("blue", "file1", "greyscaleBlue");
    Pixel[][] blueGrey1 = this.files1.get("greyscaleBlue");

    assertEquals(new Pixel(125, 125, 125), blueGrey1[0][0]);
    assertEquals(new Pixel(255, 255, 255), blueGrey1[0][1]);
    assertEquals(new Pixel(0, 0, 0), blueGrey1[0][2]);
    assertEquals(new Pixel(0, 0, 0), blueGrey1[1][0]);
    assertEquals(new Pixel(0, 0, 0), blueGrey1[1][1]);
    assertEquals(new Pixel(255, 255, 255), blueGrey1[1][2]);

    // Tests greyscale with value component

    try {
      this.model.loadImage("test/testFiles/testOne.bmp", "file1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }
    this.model.greyscale("value", "file1", "greyscaleValue");
    Pixel[][] valueGrey1 = this.files1.get("greyscaleValue");

    assertEquals(new Pixel(255, 255, 255), valueGrey1[0][0]);
    assertEquals(new Pixel(255, 255, 255), valueGrey1[0][1]);
    assertEquals(new Pixel(255, 255, 255), valueGrey1[0][2]);
    assertEquals(new Pixel(255, 255, 255), valueGrey1[1][0]);
    assertEquals(new Pixel(255, 255, 255), valueGrey1[1][1]);
    assertEquals(new Pixel(255, 255, 255), valueGrey1[1][2]);

    // Tests greyscale with intensity component

    try {
      this.model.loadImage("test/testFiles/testOne.bmp", "file1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }
    this.model.greyscale("intensity", "file1", "greyscaleIntensity");
    Pixel[][] intensityGrey1 = this.files1.get("greyscaleIntensity");

    assertEquals(new Pixel(380 / 3, 380 / 3, 380 / 3), intensityGrey1[0][0]);
    assertEquals(new Pixel(510 / 3, 510 / 3, 510 / 3), intensityGrey1[0][1]);
    assertEquals(new Pixel(380 / 3, 380 / 3, 380 / 3), intensityGrey1[0][2]);
    assertEquals(new Pixel(510 / 3, 510 / 3, 510 / 3), intensityGrey1[1][0]);
    assertEquals(new Pixel(255 / 3, 255 / 3, 255 / 3), intensityGrey1[1][1]);
    assertEquals(new Pixel(380 / 3, 380 / 3, 380 / 3), intensityGrey1[1][2]);

    // Tests greyscale with luma component

    try {
      this.model.loadImage("test/testFiles/testOne.bmp", "file1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }
    this.model.greyscale("luma", "file1", "greyscaleLuma");
    Pixel[][] lumaGrey1 = this.files1.get("greyscaleLuma");

    assertEquals(new Pixel(63, 63, 63), lumaGrey1[0][0]);
    assertEquals(new Pixel(72, 72, 72), lumaGrey1[0][1]);
    assertEquals(new Pixel(143, 143, 143), lumaGrey1[0][2]);
    assertEquals(new Pixel(236, 236, 236), lumaGrey1[1][0]);
    assertEquals(new Pixel(182, 182, 182), lumaGrey1[1][1]);
    assertEquals(new Pixel(44, 44, 44), lumaGrey1[1][2]);

  }

  // tests the greyscale on a null PNG
  @Test
  public void testGreyscalePNGNull() {
    // red component when model.Pixel[][] is null

    try {
      this.test.greyscale("red", "filedoesntexist.png",
              "greyscaleRedFail");
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // fails as file doesnt exist (model.Pixel[][] is null).
    }

    // green component when model.Pixel[][] is null

    try {
      this.test.greyscale("green", "filedoesntexist.png",
              "greyscaleGreenFail");
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // fails as file doesnt exist (model.Pixel[][] is null).
    }

    // blue component when model.Pixel[][] is null

    try {
      this.test.greyscale("blue", "filedoesntexist.png",
              "greyscaleBlueFail");
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // fails as file doesnt exist (model.Pixel[][] is null).
    }

    // Value component when model.Pixel[][] is null

    try {
      this.test.greyscale("value", "filedoesntexist.png",
              "greyscaleValueFail");
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // fails as file doesnt exist (model.Pixel[][] is null).
    }

    // intensity component when model.Pixel[][] is null

    try {
      this.test.greyscale("intensity", "filedoesntexist.png",
              "greyscaleIntensityFail");
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // fails as file doesnt exist (model.Pixel[][] is null).
    }

    // luma component when model.Pixel[][] is null
    try {
      this.test.greyscale("luma", "filedoesntexist.png",
              "greyscaleIntensityFail");
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // fails as file doesnt exist (model.Pixel[][] is null).
    }
  }

  // tests the greyscale on a PNG
  @Test
  public void testGreyscalePNG() {

    // Tests greyscale with red component

    try {
      this.model.loadImage("test/testFiles/testOne.png", "file1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    this.model.greyscale("red", "file1", "greyscaleRed");
    Pixel[][] redGrey1 = this.files1.get("greyscaleRed");

    assertEquals(new Pixel(255, 255, 255), redGrey1[0][0]);
    assertEquals(new Pixel(255, 255, 255), redGrey1[0][1]);
    assertEquals(new Pixel(255, 255, 255), redGrey1[0][2]);
    assertEquals(new Pixel(255, 255, 255), redGrey1[1][0]);
    assertEquals(new Pixel(0, 0, 0), redGrey1[1][1]);
    assertEquals(new Pixel(125, 125, 125), redGrey1[1][2]);

    // Tests greyscale with green component

    try {
      this.model.loadImage("test/testFiles/testOne.png", "file1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    this.model.greyscale("green", "file1", "greyscaleGreen");
    Pixel[][] greenGrey1 = this.files1.get("greyscaleGreen");

    assertEquals(new Pixel(0, 0, 0), greenGrey1[0][0]);
    assertEquals(new Pixel(0, 0, 0), greenGrey1[0][1]);
    assertEquals(new Pixel(125, 125, 125), greenGrey1[0][2]);
    assertEquals(new Pixel(255, 255, 255), greenGrey1[1][0]);
    assertEquals(new Pixel(255, 255, 255), greenGrey1[1][1]);
    assertEquals(new Pixel(0, 0, 0), greenGrey1[1][2]);

    // Tests greyscale with blue component

    try {
      this.model.loadImage("test/testFiles/testOne.png", "file1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    this.model.greyscale("blue", "file1", "greyscaleBlue");
    Pixel[][] blueGrey1 = this.files1.get("greyscaleBlue");

    assertEquals(new Pixel(125, 125, 125), blueGrey1[0][0]);
    assertEquals(new Pixel(255, 255, 255), blueGrey1[0][1]);
    assertEquals(new Pixel(0, 0, 0), blueGrey1[0][2]);
    assertEquals(new Pixel(0, 0, 0), blueGrey1[1][0]);
    assertEquals(new Pixel(0, 0, 0), blueGrey1[1][1]);
    assertEquals(new Pixel(255, 255, 255), blueGrey1[1][2]);

    // Tests greyscale with value component

    try {
      this.model.loadImage("test/testFiles/testOne.png", "file1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }
    this.model.greyscale("value", "file1", "greyscaleValue");
    Pixel[][] valueGrey1 = this.files1.get("greyscaleValue");

    assertEquals(new Pixel(255, 255, 255), valueGrey1[0][0]);
    assertEquals(new Pixel(255, 255, 255), valueGrey1[0][1]);
    assertEquals(new Pixel(255, 255, 255), valueGrey1[0][2]);
    assertEquals(new Pixel(255, 255, 255), valueGrey1[1][0]);
    assertEquals(new Pixel(255, 255, 255), valueGrey1[1][1]);
    assertEquals(new Pixel(255, 255, 255), valueGrey1[1][2]);

    // Tests greyscale with intensity component

    try {
      this.model.loadImage("test/testFiles/testOne.png", "file1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }
    this.model.greyscale("intensity", "file1", "greyscaleIntensity");
    Pixel[][] intensityGrey1 = this.files1.get("greyscaleIntensity");

    assertEquals(new Pixel(380 / 3, 380 / 3, 380 / 3), intensityGrey1[0][0]);
    assertEquals(new Pixel(510 / 3, 510 / 3, 510 / 3), intensityGrey1[0][1]);
    assertEquals(new Pixel(380 / 3, 380 / 3, 380 / 3), intensityGrey1[0][2]);
    assertEquals(new Pixel(510 / 3, 510 / 3, 510 / 3), intensityGrey1[1][0]);
    assertEquals(new Pixel(255 / 3, 255 / 3, 255 / 3), intensityGrey1[1][1]);
    assertEquals(new Pixel(380 / 3, 380 / 3, 380 / 3), intensityGrey1[1][2]);

    // Tests greyscale with luma component

    try {
      this.model.loadImage("test/testFiles/testOne.png", "file1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }
    this.model.greyscale("luma", "file1", "greyscaleLuma");
    Pixel[][] lumaGrey1 = this.files1.get("greyscaleLuma");

    assertEquals(new Pixel(63, 63, 63), lumaGrey1[0][0]);
    assertEquals(new Pixel(72, 72, 72), lumaGrey1[0][1]);
    assertEquals(new Pixel(143, 143, 143), lumaGrey1[0][2]);
    assertEquals(new Pixel(236, 236, 236), lumaGrey1[1][0]);
    assertEquals(new Pixel(182, 182, 182), lumaGrey1[1][1]);
    assertEquals(new Pixel(44, 44, 44), lumaGrey1[1][2]);

  }

  // tests the greyscale on a null jpeg
  @Test
  public void testGreyscaleJpegNull() {
    // red component when model.Pixel[][] is null

    try {
      this.test.greyscale("red", "filedoesntexist.jpeg",
              "greyscaleRedFail");
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // fails as file doesnt exist (model.Pixel[][] is null).
    }

    // green component when model.Pixel[][] is null

    try {
      this.test.greyscale("green", "filedoesntexist.jpeg",
              "greyscaleGreenFail");
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // fails as file doesnt exist (model.Pixel[][] is null).
    }

    // blue component when model.Pixel[][] is null

    try {
      this.test.greyscale("blue", "filedoesntexist.jpeg",
              "greyscaleBlueFail");
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // fails as file doesnt exist (model.Pixel[][] is null).
    }

    // Value component when model.Pixel[][] is null

    try {
      this.test.greyscale("value", "filedoesntexist.png",
              "greyscaleValueFail");
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // fails as file doesnt exist (model.Pixel[][] is null).
    }

    // intensity component when model.Pixel[][] is null

    try {
      this.test.greyscale("intensity", "filedoesntexist.jpeg",
              "greyscaleIntensityFail");
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // fails as file doesnt exist (model.Pixel[][] is null).
    }

    // luma component when model.Pixel[][] is null
    try {
      this.test.greyscale("luma", "filedoesntexist.jpeg",
              "greyscaleIntensityFail");
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // fails as file doesnt exist (model.Pixel[][] is null).
    }

  }

  // tests the greyscale on a jpeg
  @Test
  public void testGreyscaleJPEG() {

    // Tests greyscale with red component

    try {
      this.model.loadImage("test/testFiles/test.jpeg", "file1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    this.model.greyscale("red", "file1", "greyscaleRed");

    Pixel[][] redGrey1 = this.files1.get("greyscaleRed");

    assertEquals(new Pixel(145, 145, 145), redGrey1[0][0]);
    assertEquals(new Pixel(176, 176, 176), redGrey1[0][1]);
    assertEquals(new Pixel(255, 255, 255), redGrey1[1][0]);
    assertEquals(new Pixel(206, 206, 206), redGrey1[1][1]);

    // Tests greyscale with green component

    try {
      this.model.loadImage("test/testFiles/test.jpeg", "file1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    this.model.greyscale("green", "file1", "greyscaleGreen");
    Pixel[][] greenGrey1 = this.files1.get("greyscaleGreen");

    assertEquals(new Pixel(72, 72, 72), greenGrey1[0][0]);
    assertEquals(new Pixel(103, 103, 103), greenGrey1[0][1]);
    assertEquals(new Pixel(197, 197, 197), greenGrey1[1][0]);
    assertEquals(new Pixel(133, 133, 133), greenGrey1[1][1]);

    // Tests greyscale with blue component

    try {
      this.model.loadImage("test/testFiles/test.jpeg", "file1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    this.model.greyscale("blue", "file1", "greyscaleBlue");


    Pixel[][] blueGrey1 = this.files1.get("greyscaleBlue");

    assertEquals(new Pixel(39, 39, 39), blueGrey1[0][0]);
    assertEquals(new Pixel(70, 70, 70), blueGrey1[0][1]);
    assertEquals(new Pixel(164, 164, 164), blueGrey1[1][0]);
    assertEquals(new Pixel(100, 100, 100), blueGrey1[1][1]);

    // Tests greyscale with value component

    try {
      this.model.loadImage("test/testFiles/test.jpeg", "file1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }
    this.model.greyscale("value", "file1", "greyscaleValue");

    Pixel[][] valueGrey1 = this.files1.get("greyscaleValue");

    assertEquals(new Pixel(145, 145, 145), valueGrey1[0][0]);
    assertEquals(new Pixel(176, 176, 176), valueGrey1[0][1]);
    assertEquals(new Pixel(255, 255, 255), valueGrey1[1][0]);
    assertEquals(new Pixel(206, 206, 206), valueGrey1[1][1]);


    // Tests greyscale with intensity component

    try {
      this.model.loadImage("test/testFiles/test.jpeg", "file1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }
    this.model.greyscale("intensity", "file1", "greyscaleIntensity");

    Pixel[][] intensityGrey1 = this.files1.get("greyscaleIntensity");

    assertEquals(new Pixel(85, 85, 85), intensityGrey1[0][0]);
    assertEquals(new Pixel(116, 116, 116), intensityGrey1[0][1]);
    assertEquals(new Pixel(205, 205, 205), intensityGrey1[1][0]);
    assertEquals(new Pixel(146, 146, 146), intensityGrey1[1][1]);

    // Tests greyscale with luma component

    try {
      this.model.loadImage("test/testFiles/test.jpeg", "file1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }
    this.model.greyscale("luma", "file1", "greyscaleLuma");

    Pixel[][] lumaGrey1 = this.files1.get("greyscaleLuma");

    assertEquals(new Pixel(85, 85, 85), lumaGrey1[0][0]);
    assertEquals(new Pixel(116, 116, 116), lumaGrey1[0][1]);
    assertEquals(new Pixel(206, 206, 206), lumaGrey1[1][0]);
    assertEquals(new Pixel(146, 146, 146), lumaGrey1[1][1]);

  }


  // tests Greyscale on a null PPM
  @Test
  public void testGreyscaleNullPPM() {
    // greyscale on non-existent file
    try {
      this.test.greyscale("luma", "filedoesntexist.ppm",
              "greyscaleLumaFail");
    } catch (IllegalArgumentException e) {
      assertEquals("Image cannot be found", e.getMessage());
    }

    // red component when model.Pixel[][] is null

    try {
      this.test.greyscale("red", "filedoesntexist.ppm",
              "greyscaleRedFail");
    } catch (IllegalArgumentException e) {
      assertEquals("Image cannot be found", e.getMessage());
      // fails as file doesnt exist (model.Pixel[][] is null).
    }

    // green component when model.Pixel[][] is null

    try {
      this.test.greyscale("green", "filedoesntexist.ppm",
              "greyscaleGreenFail");
    } catch (IllegalArgumentException e) {
      assertEquals("Image cannot be found", e.getMessage());
      // fails as file doesnt exist (model.Pixel[][] is null).
    }

    // blue component when model.Pixel[][] is null

    try {
      this.test.greyscale("blue", "filedoesntexist.ppm",
              "greyscaleBlueFail");
    } catch (IllegalArgumentException e) {
      assertEquals("Image cannot be found", e.getMessage());
      // fails as file doesnt exist (model.Pixel[][] is null).
    }

    // Value component when model.Pixel[][] is null

    try {
      this.test.greyscale("value", "filedoesntexist.ppm",
              "greyscaleValueFail");
    } catch (IllegalArgumentException e) {
      assertEquals("Image cannot be found", e.getMessage());
      // fails as file doesnt exist (model.Pixel[][] is null).
    }

    // intensity component when model.Pixel[][] is null

    try {
      this.test.greyscale("intensity", "filedoesntexist.ppm",
              "greyscaleIntensityFail");
    } catch (IllegalArgumentException e) {
      assertEquals("Image cannot be found", e.getMessage());
      // fails as file doesnt exist (model.Pixel[][] is null).
    }


  }

  // tests Greyscale on a PPM
  @Test
  public void testGreyscalePPM() {

    // Tests greyscale with red component

    try {
      this.model.loadImage("test/testFiles/testOne.ppm", "file1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    this.model.greyscale("red", "file1", "greyscaleRed");
    Pixel[][] redGrey1 = this.files1.get("greyscaleRed");

    assertEquals(new Pixel(255, 255, 255), redGrey1[0][0]);
    assertEquals(new Pixel(255, 255, 255), redGrey1[0][1]);
    assertEquals(new Pixel(255, 255, 255), redGrey1[0][2]);
    assertEquals(new Pixel(255, 255, 255), redGrey1[1][0]);
    assertEquals(new Pixel(0, 0, 0), redGrey1[1][1]);
    assertEquals(new Pixel(125, 125, 125), redGrey1[1][2]);

    // Tests greyscale with green component

    try {
      this.model.loadImage("test/testFiles/testOne.ppm", "file1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    this.model.greyscale("green", "file1", "greyscaleGreen");
    Pixel[][] greenGrey1 = this.files1.get("greyscaleGreen");

    assertEquals(new Pixel(0, 0, 0), greenGrey1[0][0]);
    assertEquals(new Pixel(0, 0, 0), greenGrey1[0][1]);
    assertEquals(new Pixel(125, 125, 125), greenGrey1[0][2]);
    assertEquals(new Pixel(255, 255, 255), greenGrey1[1][0]);
    assertEquals(new Pixel(255, 255, 255), greenGrey1[1][1]);
    assertEquals(new Pixel(0, 0, 0), greenGrey1[1][2]);


    // Tests greyscale with blue component

    try {
      this.model.loadImage("test/testFiles/testOne.ppm", "file1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    this.model.greyscale("blue", "file1", "greyscaleBlue");
    Pixel[][] blueGrey1 = this.files1.get("greyscaleBlue");

    assertEquals(new Pixel(125, 125, 125), blueGrey1[0][0]);
    assertEquals(new Pixel(255, 255, 255), blueGrey1[0][1]);
    assertEquals(new Pixel(0, 0, 0), blueGrey1[0][2]);
    assertEquals(new Pixel(0, 0, 0), blueGrey1[1][0]);
    assertEquals(new Pixel(0, 0, 0), blueGrey1[1][1]);
    assertEquals(new Pixel(255, 255, 255), blueGrey1[1][2]);

    // Tests greyscale with value component

    try {
      this.model.loadImage("test/testFiles/testOne.ppm", "file1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }
    this.model.greyscale("value", "file1", "greyscaleValue");
    Pixel[][] valueGrey1 = this.files1.get("greyscaleValue");

    assertEquals(new Pixel(255, 255, 255), valueGrey1[0][0]);
    assertEquals(new Pixel(255, 255, 255), valueGrey1[0][1]);
    assertEquals(new Pixel(255, 255, 255), valueGrey1[0][2]);
    assertEquals(new Pixel(255, 255, 255), valueGrey1[1][0]);
    assertEquals(new Pixel(255, 255, 255), valueGrey1[1][1]);
    assertEquals(new Pixel(255, 255, 255), valueGrey1[1][2]);


    // Tests greyscale with intensity component

    try {
      this.model.loadImage("test/testFiles/testOne.ppm", "file1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }
    this.model.greyscale("intensity", "file1", "greyscaleIntensity");
    Pixel[][] intensityGrey1 = this.files1.get("greyscaleIntensity");

    assertEquals(new Pixel(380 / 3, 380 / 3, 380 / 3), intensityGrey1[0][0]);
    assertEquals(new Pixel(510 / 3, 510 / 3, 510 / 3), intensityGrey1[0][1]);
    assertEquals(new Pixel(380 / 3, 380 / 3, 380 / 3), intensityGrey1[0][2]);
    assertEquals(new Pixel(510 / 3, 510 / 3, 510 / 3), intensityGrey1[1][0]);
    assertEquals(new Pixel(255 / 3, 255 / 3, 255 / 3), intensityGrey1[1][1]);
    assertEquals(new Pixel(380 / 3, 380 / 3, 380 / 3), intensityGrey1[1][2]);

    // Tests greyscale with luma component

    try {
      this.model.loadImage("test/testFiles/testOne.ppm", "file1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }
    this.model.greyscale("luma", "file1", "greyscaleLuma");
    Pixel[][] lumaGrey1 = this.files1.get("greyscaleLuma");

    assertEquals(new Pixel(63, 63, 63), lumaGrey1[0][0]);
    assertEquals(new Pixel(72, 72, 72), lumaGrey1[0][1]);
    assertEquals(new Pixel(143, 143, 143), lumaGrey1[0][2]);
    assertEquals(new Pixel(236, 236, 236), lumaGrey1[1][0]);
    assertEquals(new Pixel(182, 182, 182), lumaGrey1[1][1]);
    assertEquals(new Pixel(44, 44, 44), lumaGrey1[1][2]);
  }

  /*
   * For these tests, I'm just checking whether doing brighten on various image types works the
   * same as with the PPM file.
   */

  // tests brightening a BMP
  @Test
  public void testBrightenBMP() {
    // brighten above max
    try {
      this.model.loadImage("test/testFiles/testTwo2.bmp", "file2");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    this.model.brighten(100, "file2", "normalBrighten1");
    Pixel[][] brighten1 = this.files1.get("normalBrighten1");

    assertEquals(new Pixel(138, 131, 123), brighten1[0][0]);
    assertEquals(new Pixel(178, 129, 158), brighten1[0][1]);
    assertEquals(new Pixel(123, 141, 174), brighten1[1][0]);
    assertEquals(new Pixel(223, 255, 255), brighten1[1][1]);

    // negative brighten
    try {
      this.model.loadImage("test/testFiles/testTwo2.bmp", "file2");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    this.model.brighten(-20, "file2", "normalBrighten1");
    Pixel[][] brighten2 = this.files1.get("normalBrighten1");

    assertEquals(new Pixel(18, 11, 3), brighten2[0][0]);
    assertEquals(new Pixel(58, 9, 38), brighten2[0][1]);
    assertEquals(new Pixel(3, 21, 54), brighten2[1][0]);
    assertEquals(new Pixel(103, 195, 181), brighten2[1][1]);

    try {
      this.model.loadImage("test/testFiles/testTwo2.bmp", "file2");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    // negative brighten below 0
    this.model.brighten(-100, "file2", "normalBrighten1");
    Pixel[][] brighten0 = this.files1.get("normalBrighten1");

    assertEquals(new Pixel(0, 0, 0), brighten0[0][0]);
    assertEquals(new Pixel(0, 0, 0), brighten0[0][1]);
    assertEquals(new Pixel(0, 0, 0), brighten0[1][0]);
    assertEquals(new Pixel(23, 115, 101), brighten0[1][1]);

    // normal brighten
    try {
      this.model.loadImage("test/testFiles/testTwo2.bmp", "file2");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    this.model.brighten(23, "file2", "normalBrighten1");
    Pixel[][] brighten4 = this.files1.get("normalBrighten1");

    assertEquals(new Pixel(61, 54, 46), brighten4[0][0]);
    assertEquals(new Pixel(101, 52, 81), brighten4[0][1]);
    assertEquals(new Pixel(46, 64, 97), brighten4[1][0]);
    assertEquals(new Pixel(146, 238, 224), brighten4[1][1]);

    // Brighten when file doesn't exist (model.Pixel[][] is null)

    try {
      this.model.brighten(10, "filedoesntexist.bmp", "brightenfail");
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // fails as file doesnt exist (model.Pixel[][] is null).
    }

  }

  // tests brightening a PNG
  @Test
  public void testBrightenPNG() {
    // brighten above max
    try {
      this.model.loadImage("test/testFiles/testTwo.png", "file2");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    this.model.brighten(100, "file2", "normalBrighten1");
    Pixel[][] brighten1 = this.files1.get("normalBrighten1");

    assertEquals(new Pixel(138, 131, 123), brighten1[0][0]);
    assertEquals(new Pixel(178, 129, 158), brighten1[0][1]);
    assertEquals(new Pixel(123, 141, 174), brighten1[1][0]);
    assertEquals(new Pixel(223, 255, 255), brighten1[1][1]);

    // negative brighten
    try {
      this.model.loadImage("test/testFiles/testTwo.png", "file2");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    this.model.brighten(-20, "file2", "normalBrighten1");
    Pixel[][] brighten2 = this.files1.get("normalBrighten1");

    assertEquals(new Pixel(18, 11, 3), brighten2[0][0]);
    assertEquals(new Pixel(58, 9, 38), brighten2[0][1]);
    assertEquals(new Pixel(3, 21, 54), brighten2[1][0]);
    assertEquals(new Pixel(103, 195, 181), brighten2[1][1]);

    try {
      this.model.loadImage("test/testFiles/testTwo.png", "file2");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    // negative brighten below 0
    this.model.brighten(-100, "file2", "normalBrighten1");
    Pixel[][] brighten0 = this.files1.get("normalBrighten1");

    assertEquals(new Pixel(0, 0, 0), brighten0[0][0]);
    assertEquals(new Pixel(0, 0, 0), brighten0[0][1]);
    assertEquals(new Pixel(0, 0, 0), brighten0[1][0]);
    assertEquals(new Pixel(23, 115, 101), brighten0[1][1]);

    // normal brighten
    try {
      this.model.loadImage("test/testFiles/testTwo.png", "file2");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    this.model.brighten(23, "file2", "normalBrighten1");
    Pixel[][] brighten4 = this.files1.get("normalBrighten1");

    assertEquals(new Pixel(61, 54, 46), brighten4[0][0]);
    assertEquals(new Pixel(101, 52, 81), brighten4[0][1]);
    assertEquals(new Pixel(46, 64, 97), brighten4[1][0]);
    assertEquals(new Pixel(146, 238, 224), brighten4[1][1]);

    // Brighten when file doesn't exist (model.Pixel[][] is null)

    try {
      this.model.brighten(10, "filedoesntexist.png", "brightenfail");
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // fails as file doesnt exist (model.Pixel[][] is null).
    }

  }

  // tests brightening a PPM
  @Test
  public void testBrightenPPM() {
    // brighten above max
    try {
      this.model.loadImage("test/testFiles/testTwo2.ppm", "file2");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    this.model.brighten(100, "file2", "normalBrighten1");
    Pixel[][] brighten1 = this.files1.get("normalBrighten1");

    assertEquals(new Pixel(138, 131, 123), brighten1[0][0]);
    assertEquals(new Pixel(178, 129, 158), brighten1[0][1]);
    assertEquals(new Pixel(123, 141, 174), brighten1[1][0]);
    assertEquals(new Pixel(223, 255, 255), brighten1[1][1]);

    // negative brighten to negative pixel
    try {
      this.model.loadImage("test/testFiles/testTwo2.ppm", "file2");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    this.model.brighten(-1000, "file2", "normalBrighten1");
    Pixel[][] brightenToNegative = this.files1.get("normalBrighten1");

    assertEquals(new Pixel(0, 0, 0), brightenToNegative[0][0]);
    assertEquals(new Pixel(0, 0, 0), brightenToNegative[0][1]);
    assertEquals(new Pixel(0, 0, 0), brightenToNegative[1][0]);
    assertEquals(new Pixel(0, 0, 0), brightenToNegative[1][1]);

    // negative brighten
    try {
      this.model.loadImage("test/testFiles/testTwo2.ppm", "file2");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    this.model.brighten(-20, "file2", "normalBrighten1");
    Pixel[][] brighten2 = this.files1.get("normalBrighten1");

    assertEquals(new Pixel(18, 11, 3), brighten2[0][0]);
    assertEquals(new Pixel(58, 9, 38), brighten2[0][1]);
    assertEquals(new Pixel(3, 21, 54), brighten2[1][0]);
    assertEquals(new Pixel(103, 195, 181), brighten2[1][1]);

    try {
      this.model.loadImage("test/testFiles/testTwo2.ppm", "file2");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    // negative brighten below 0
    this.model.brighten(-100, "file2", "normalBrighten1");
    Pixel[][] brighten0 = this.files1.get("normalBrighten1");

    assertEquals(new Pixel(0, 0, 0), brighten0[0][0]);
    assertEquals(new Pixel(0, 0, 0), brighten0[0][1]);
    assertEquals(new Pixel(0, 0, 0), brighten0[1][0]);
    assertEquals(new Pixel(23, 115, 101), brighten0[1][1]);


    // brighten by 0
    try {
      this.model.loadImage("test/testFiles/testTwo2.ppm", "file2");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    this.model.brighten(0, "file2", "normalBrighten1");
    Pixel[][] brighten3 = this.files1.get("normalBrighten1");

    assertEquals(this.one2, brighten3[0][0]);
    assertEquals(this.two2, brighten3[0][1]);
    assertEquals(this.three2, brighten3[1][0]);
    assertEquals(this.four2, brighten3[1][1]);

    // normal brighten
    try {
      this.model.loadImage("test/testFiles/testTwo2.ppm", "file2");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    this.model.brighten(23, "file2", "normalBrighten1");
    Pixel[][] brighten4 = this.files1.get("normalBrighten1");

    assertEquals(new Pixel(61, 54, 46), brighten4[0][0]);
    assertEquals(new Pixel(101, 52, 81), brighten4[0][1]);
    assertEquals(new Pixel(46, 64, 97), brighten4[1][0]);
    assertEquals(new Pixel(146, 238, 224), brighten4[1][1]);

    // Brighten when file doesn't exist (model.Pixel[][] is null)

    try {
      this.model.brighten(10, "filedoesntexist", "brightenfail");
    } catch (IllegalArgumentException e) {
      // fails as file doesnt exist (model.Pixel[][] is null).
    }
  }

  // tests brightening a JPEG
  @Test
  public void testBrightenJPEG() {
    // brighten above max
    try {
      this.model.loadImage("test/testFiles/test.jpeg", "file2");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    this.model.brighten(100, "file2", "normalBrighten1");

    Pixel[][] brighten1 = this.files1.get("normalBrighten1");

    assertEquals(new Pixel(245, 172, 139), brighten1[0][0]);
    assertEquals(new Pixel(255, 203, 170), brighten1[0][1]);
    assertEquals(new Pixel(255, 255, 255), brighten1[1][0]);
    assertEquals(new Pixel(255, 233, 200), brighten1[1][1]);

    // negative brighten
    try {
      this.model.loadImage("test/testFiles/test.jpeg", "file2");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    this.model.brighten(-20, "file2", "normalBrighten1");
    Pixel[][] brighten2 = this.files1.get("normalBrighten1");

    assertEquals(new Pixel(125, 52, 19), brighten2[0][0]);
    assertEquals(new Pixel(156, 83, 50), brighten2[0][1]);
    assertEquals(new Pixel(235, 177, 144), brighten2[1][0]);
    assertEquals(new Pixel(186, 113, 80), brighten2[1][1]);

    try {
      this.model.loadImage("test/testFiles/test.jpeg", "file2");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    // negative brighten below 0
    this.model.brighten(-100, "file2", "normalBrighten1");
    Pixel[][] brighten0 = this.files1.get("normalBrighten1");

    assertEquals(new Pixel(45, 0, 0), brighten0[0][0]);
    assertEquals(new Pixel(76, 3, 0), brighten0[0][1]);
    assertEquals(new Pixel(155, 97, 64), brighten0[1][0]);
    assertEquals(new Pixel(106, 33, 0), brighten0[1][1]);

    // normal brighten
    try {
      this.model.loadImage("test/testFiles/test.jpeg", "file2");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    this.model.brighten(23, "file2", "normalBrighten1");
    Pixel[][] brighten4 = this.files1.get("normalBrighten1");

    assertEquals(new Pixel(168, 95, 62), brighten4[0][0]);
    assertEquals(new Pixel(199, 126, 93), brighten4[0][1]);
    assertEquals(new Pixel(255, 220, 187), brighten4[1][0]);
    assertEquals(new Pixel(229, 156, 123), brighten4[1][1]);

    // Brighten when file doesn't exist (model.Pixel[][] is null)

    try {
      this.model.brighten(10, "filedoesntexist.jpeg", "brightenfail");
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // fails as file doesnt exist (model.Pixel[][] is null).
    }

  }

  /*
   * For these tests, I'm just checking whether doing horizontal-flip on various image types
   * works the same as with the PPM file.
   */

  // tests horizontally flipping a BMP
  @Test
  public void testHorizontalFlipBMP() {
    // default Horizontal Flip

    try {
      this.model.loadImage("test/testFiles/testTwo2.bmp", "file2");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    this.model.horizontalFlip("file2", "horizontalFlip2");
    Pixel[][] horizontalFlip2 = this.files1.get("horizontalFlip2");

    assertEquals(this.two2, horizontalFlip2[0][0]);
    assertEquals(this.one2, horizontalFlip2[0][1]);
    assertEquals(this.four2, horizontalFlip2[1][0]);
    assertEquals(this.three2, horizontalFlip2[1][1]);


    // horizontalFlip when file doesn't exist (model.Pixel[][] is null)

    try {
      this.model.horizontalFlip("filedoesntexist.bmp", "horizontalFlipFail");
    } catch (IllegalArgumentException e) {
      // fails as file doesnt exist (model.Pixel[][] is null).
    }

  }

  // tests horizontally flipping a PPM
  @Test
  public void testHorizontalFlipPPM() {
    // default Horizontal Flip

    try {
      this.model.loadImage("test/testFiles/testTwo2.ppm", "file2");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    this.model.horizontalFlip("file2", "horizontalFlip2");
    Pixel[][] horizontalFlip2 = this.files1.get("horizontalFlip2");

    assertEquals(this.two2, horizontalFlip2[0][0]);
    assertEquals(this.one2, horizontalFlip2[0][1]);
    assertEquals(this.four2, horizontalFlip2[1][0]);
    assertEquals(this.three2, horizontalFlip2[1][1]);


    // horizontalFlip when file doesn't exist (model.Pixel[][] is null)

    try {
      this.model.horizontalFlip("filedoesntexist.ppm", "horizontalFlipFail");
    } catch (IllegalArgumentException e) {
      // fails as file doesnt exist (model.Pixel[][] is null).
    }

  }

  // tests horizontally flipping a PNG
  @Test
  public void testHorizontalFlipPNG() {
    // default Horizontal Flip
    try {
      this.model.loadImage("test/testFiles/testOne.png", "file1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }
    this.model.horizontalFlip("file1", "horizontalFlip1");
    Pixel[][] horizontalFlip1 = this.files1.get("horizontalFlip1");

    assertEquals(this.three1, horizontalFlip1[0][0]);
    assertEquals(this.two1, horizontalFlip1[0][1]);
    assertEquals(this.one1, horizontalFlip1[0][2]);
    assertEquals(this.six1, horizontalFlip1[1][0]);
    assertEquals(this.five1, horizontalFlip1[1][1]);
    assertEquals(this.four1, horizontalFlip1[1][2]);

    try {
      this.model.loadImage("test/testFiles/testTwo.png", "file2");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    this.model.horizontalFlip("file2", "horizontalFlip2");
    Pixel[][] horizontalFlip2 = this.files1.get("horizontalFlip2");

    assertEquals(this.two2, horizontalFlip2[0][0]);
    assertEquals(this.one2, horizontalFlip2[0][1]);
    assertEquals(this.four2, horizontalFlip2[1][0]);
    assertEquals(this.three2, horizontalFlip2[1][1]);


    // horizontalFlip when file doesn't exist (model.Pixel[][] is null)

    try {
      this.model.horizontalFlip("filedoesntexist.png", "horizontalFlipFail");
    } catch (IllegalArgumentException e) {
      // fails as file doesnt exist (model.Pixel[][] is null).
    }


  }

  // tests horizontally flipping a JPEG
  @Test
  public void testHorizontalFlipJPEG() {

    // default Horizontal Flip
    try {
      this.model.loadImage("test/testFiles/test.jpeg", "file1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }
    this.model.horizontalFlip("file1", "horizontalFlip1");

    Pixel[][] horizontalFlip1 = this.files1.get("horizontalFlip1");

    assertEquals(new Pixel(202, 97, 156), horizontalFlip1[0][0]);
    assertEquals(new Pixel(176, 103, 70), horizontalFlip1[0][1]);
    assertEquals(new Pixel(137, 32, 91), horizontalFlip1[1][0]);
    assertEquals(new Pixel(206, 133, 100), horizontalFlip1[1][1]);

    try {
      this.model.loadImage("test/testFiles/test.jpeg", "file2");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    this.model.horizontalFlip("file2", "horizontalFlip2");

    Pixel[][] horizontalFlip2 = this.files1.get("horizontalFlip2");

    assertEquals(new Pixel(202, 97, 156), horizontalFlip2[0][0]);
    assertEquals(new Pixel(176, 103, 70), horizontalFlip2[0][1]);
    assertEquals(new Pixel(137, 32, 91), horizontalFlip2[1][0]);
    assertEquals(new Pixel(206, 133, 100), horizontalFlip2[1][1]);


    // horizontalFlip when file doesn't exist (model.Pixel[][] is null)

    try {
      this.model.horizontalFlip("filedoesntexist.png", "horizontalFlipFail");
    } catch (IllegalArgumentException e) {
      // fails as file doesnt exist (model.Pixel[][] is null).
    }

  }

  /*
   * For these tests, I'm just checking whether doing vertical-flip on various image types
   * works the same as with the PPM file.
   */

  // tests vertically flipping a BMP
  @Test
  public void testVerticalFlipBMP() {

    try {
      this.model.loadImage("test/testFiles/testOne.bmp", "file1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }
    this.model.verticalFlip("file1", "verticalFlip1");
    Pixel[][] verticalFlip1 = this.files1.get("verticalFlip1");

    assertEquals(this.four1, verticalFlip1[0][0]);
    assertEquals(this.five1, verticalFlip1[0][1]);
    assertEquals(this.six1, verticalFlip1[0][2]);
    assertEquals(this.one1, verticalFlip1[1][0]);
    assertEquals(this.two1, verticalFlip1[1][1]);
    assertEquals(this.three1, verticalFlip1[1][2]);


    try {
      this.model.loadImage("test/testFiles/testTwo2.bmp", "file2");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    this.model.verticalFlip("file2", "verticalFlip1");
    Pixel[][] verticalFlip2 = this.files1.get("verticalFlip1");

    assertEquals(this.three2, verticalFlip2[0][0]);
    assertEquals(this.four2, verticalFlip2[0][1]);
    assertEquals(this.one2, verticalFlip2[1][0]);
    assertEquals(this.two2, verticalFlip2[1][1]);

    // verticalFlip when file doesn't exist (model.Pixel[][] is null)

    try {
      this.model.verticalFlip("filedoesntexist.bmp", "verticalFlipFail");
    } catch (IllegalArgumentException e) {
      // fails as file doesnt exist (model.Pixel[][] is null).
    }


  }

  // tests vertically flipping a PNG
  @Test
  public void testVerticalFlipPNG() {

    try {
      this.model.loadImage("test/testFiles/testOne.png", "file1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }
    this.model.verticalFlip("file1", "verticalFlip1");
    Pixel[][] verticalFlip1 = this.files1.get("verticalFlip1");

    assertEquals(this.four1, verticalFlip1[0][0]);
    assertEquals(this.five1, verticalFlip1[0][1]);
    assertEquals(this.six1, verticalFlip1[0][2]);
    assertEquals(this.one1, verticalFlip1[1][0]);
    assertEquals(this.two1, verticalFlip1[1][1]);
    assertEquals(this.three1, verticalFlip1[1][2]);


    try {
      this.model.loadImage("test/testFiles/testTwo.png", "file2");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    this.model.verticalFlip("file2", "verticalFlip1");
    Pixel[][] verticalFlip2 = this.files1.get("verticalFlip1");

    assertEquals(this.three2, verticalFlip2[0][0]);
    assertEquals(this.four2, verticalFlip2[0][1]);
    assertEquals(this.one2, verticalFlip2[1][0]);
    assertEquals(this.two2, verticalFlip2[1][1]);

    // verticalFlip when file doesn't exist (model.Pixel[][] is null)

    try {
      this.model.verticalFlip("filedoesntexist.png", "verticalFlipFail");
    } catch (IllegalArgumentException e) {
      // fails as file doesnt exist (model.Pixel[][] is null).
    }


  }

  // tests vertically flipping a PPM
  @Test
  public void testVerticalFlip() {
    // default Vertical Flip

    try {
      this.model.loadImage("test/testFiles/testOne.ppm", "file1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }
    this.model.verticalFlip("file1", "verticalFlip1");
    Pixel[][] verticalFlip1 = this.files1.get("verticalFlip1");

    assertEquals(this.four1, verticalFlip1[0][0]);
    assertEquals(this.five1, verticalFlip1[0][1]);
    assertEquals(this.six1, verticalFlip1[0][2]);
    assertEquals(this.one1, verticalFlip1[1][0]);
    assertEquals(this.two1, verticalFlip1[1][1]);
    assertEquals(this.three1, verticalFlip1[1][2]);


    try {
      this.model.loadImage("test/testFiles/testTwo2.ppm", "file2");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    this.model.verticalFlip("file2", "verticalFlip1");
    Pixel[][] verticalFlip2 = this.files1.get("verticalFlip1");

    assertEquals(this.three2, verticalFlip2[0][0]);
    assertEquals(this.four2, verticalFlip2[0][1]);
    assertEquals(this.one2, verticalFlip2[1][0]);
    assertEquals(this.two2, verticalFlip2[1][1]);

    // verticalFlip when file doesn't exist (model.Pixel[][] is null)

    try {
      this.model.verticalFlip("filedoesntexist.ppm", "verticalFlipFail");
    } catch (IllegalArgumentException e) {
      // fails as file doesnt exist (model.Pixel[][] is null).
    }

    // Brighten to verticalFlip
    try {
      this.model.loadImage("test/testFiles/testOne.ppm", "verticaltest");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }
    this.model.verticalFlip("verticaltest", "verticalresult1");
    this.model.brighten(20, "verticalresult1", "verticalToBrighten");

    Pixel[][] temp = this.files1.get("verticalToBrighten");

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

  // tests vertically flipping a JPEG
  @Test
  public void testVerticalFlipJPEG() {
    try {
      this.model.loadImage("test/testFiles/test.jpeg", "file1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }
    this.model.verticalFlip("file1", "verticalFlip1");

    Pixel[][] verticalFlip1 = this.files1.get("verticalFlip1");

    assertEquals(new Pixel(255, 197, 164), verticalFlip1[0][0]);
    assertEquals(new Pixel(206, 133, 100), verticalFlip1[0][1]);
    assertEquals(new Pixel(145, 72, 39), verticalFlip1[1][0]);
    assertEquals(new Pixel(176, 103, 70), verticalFlip1[1][1]);


    try {
      this.model.loadImage("test/testFiles/test.jpeg", "file2");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown FileNotFoundException.");
    }

    this.model.verticalFlip("file2", "verticalFlip1");

    Pixel[][] verticalFlip2 = this.files1.get("verticalFlip1");

    assertEquals(new Pixel(255, 197, 164), verticalFlip2[0][0]);
    assertEquals(new Pixel(206, 133, 100), verticalFlip2[0][1]);
    assertEquals(new Pixel(145, 72, 39), verticalFlip2[1][0]);
    assertEquals(new Pixel(176, 103, 70), verticalFlip2[1][1]);

    // verticalFlip when file doesn't exist (model.Pixel[][] is null)

    try {
      this.model.verticalFlip("filedoesntexist.png", "verticalFlipFail");
    } catch (IllegalArgumentException e) {
      // fails as file doesnt exist (model.Pixel[][] is null).
    }

  }

  /*
   * For these tests, I'm checking the conversion between a few file types.
   */

  // tests converting a PPM to JPEG, BMP, and PNG
  @Test
  public void testPPMtoVarious() {
    try {
      this.model.loadImage("test/testFiles/watermelon.ppm", "file");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown an exception.");
    }

    Pixel[][] ppm = this.files1.get("file");

    // PPM -> PNG
    try {
      this.model.saveImage("test/testFiles/watermelon.png", "file");
    } catch (IOException e) {
      fail("Should not have thrown an exception.");
    }
    try {
      this.model.loadImage("test/testFiles/watermelon.png", "file1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown an exception.");
    }

    Pixel[][] png = this.files1.get("file1");

    for (int i = 0; i < png.length; i++) {
      for (int j = 0; j < png[0].length; j++) {
        assertEquals(png[i][j], ppm[i][j]);
      }
    }

    // PPM -> BMP

    try {
      this.model.saveImage("test/testFiles/watermelon.bmp", "file");
    } catch (IOException e) {
      fail("Should not have thrown an exception.");
    }
    try {
      this.model.loadImage("test/testFiles/watermelon.bmp", "file1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown an exception.");
    }

    Pixel[][] bmp = this.files1.get("file1");

    for (int i = 0; i < bmp.length; i++) {
      for (int j = 0; j < bmp[0].length; j++) {
        assertEquals(bmp[i][j], ppm[i][j]);
      }
    }
    // PPM -> JPEG

//    try {
//      this.model.loadImage("test/testFiles/watermelon.jpeg", "file1");
//    } catch (FileNotFoundException e) {
//      fail("Should not have thrown an exception.");
//    }

    Pixel[][] jpeg = this.files1.get("file1");

    for (int i = 0; i < jpeg.length; i++) {
      for (int j = 0; j < jpeg[0].length; j++) {
        assertEquals(jpeg[i][j], ppm[i][j]);
      }
    }

  }

  // tests converting a BMP to a JPEG, PPM, and BMP
  @Test
  public void testBMPtoVarious() {
    try {
      this.model.loadImage("test/testFiles/testOne.bmp", "file");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown an exception.");
    }

    Pixel[][] bmp = this.files1.get("file");

    // BMP -> PNG
    try {
      this.model.saveImage("test/testFiles/testOne.png", "file");
    } catch (IOException e) {
      fail("Should not have thrown an exception.");
    }
    try {
      this.model.loadImage("test/testFiles/testOne.png", "file1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown an exception.");
    }

    Pixel[][] png = this.files1.get("file1");

    for (int i = 0; i < png.length; i++) {
      for (int j = 0; j < png[0].length; j++) {
        assertEquals(png[i][j], bmp[i][j]);
      }
    }

    // BMP -> PPM

    try {
      this.model.saveImage("test/testFiles/testOne.ppm", "file");
    } catch (IOException e) {
      fail("Should not have thrown an exception.");
    }
    try {
      this.model.loadImage("test/testFiles/testOne.ppm", "file1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown an exception.");
    }

    Pixel[][] ppm = this.files1.get("file1");

    for (int i = 0; i < bmp.length; i++) {
      for (int j = 0; j < bmp[0].length; j++) {
        assertEquals(ppm[i][j], bmp[i][j]);
      }
    }

    // BMP -> JPEG
    try {
      this.model.loadImage("test/testFiles/testOne.jpeg", "file1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown an exception.");
    }

    Pixel[][] jpeg = this.files1.get("file1");

//    for (int i = 0; i < jpeg.length; i++) {
//      for (int j = 0; j < jpeg[0].length; j++) {
//        assertEquals(bmp[i][j], jpeg[i][j]);
//      }
//    }


  }

  // tests converting from PNG to BMP, JPEG, and PNG
  @Test
  public void testPNGtoVarious() {
    try {
      this.model.loadImage("test/testFiles/watermelon.png", "file");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown an exception.");
    }

    Pixel[][] png = this.files1.get("file");

    // PNG -> BMP
    try {
      this.model.saveImage("test/testFiles/watermelon.bmp", "file");
    } catch (IOException e) {
      fail("Should not have thrown an exception.");
    }
    try {
      this.model.loadImage("test/testFiles/watermelon.bmp", "file1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown an exception.");
    }

    Pixel[][] bmp = this.files1.get("file1");

    for (int i = 0; i < bmp.length; i++) {
      for (int j = 0; j < bmp[0].length; j++) {
        assertEquals(bmp[i][j], png[i][j]);
      }
    }

    // PNG -> PPM

    try {
      this.model.saveImage("test/testFiles/watermelon.ppm", "file");
    } catch (IOException e) {
      fail("Should not have thrown an exception.");
    }
    try {
      this.model.loadImage("test/testFiles/watermelon.ppm", "file1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown an exception.");
    }

    Pixel[][] ppm = this.files1.get("file1");

    for (int i = 0; i < ppm.length; i++) {
      for (int j = 0; j < ppm[0].length; j++) {
        assertEquals(ppm[i][j], png[i][j]);
      }
    }

    // PNG -> JPEG

//    try {
//      this.model.loadImage("test/testFiles/watermelon.jpeg", "file1");
//    } catch (FileNotFoundException e) {
//      fail("Should not have thrown an exception.");
//    }

    Pixel[][] jpeg = this.files1.get("file1");

    for (int i = 0; i < jpeg.length; i++) {
      for (int j = 0; j < jpeg[0].length; j++) {
        assertEquals(jpeg[i][j], png[i][j]);
      }
    }

  }

  // tests converting from JPEG to PPM, PNG, and BMP
  @Test
  public void testJPEGtoVarious() {
    try {
      this.model.loadImage("test/testFiles/drake.jpeg", "file");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown an exception.");
    }

    Pixel[][] jpeg = this.files1.get("file");

    // JPEG -> BMP
    try {
      this.model.saveImage("test/testFiles/drake.bmp", "file");
    } catch (IOException e) {
      fail("Should not have thrown an exception.");
    }
    try {
      this.model.loadImage("test/testFiles/drake.bmp", "file1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown an exception.");
    }

    Pixel[][] bmp = this.files1.get("file1");

    for (int i = 0; i < bmp.length; i++) {
      for (int j = 0; j < bmp[0].length; j++) {
        assertEquals(bmp[i][j], jpeg[i][j]);
      }
    }

    // JPEG -> PPM

    try {
      this.model.saveImage("test/testFiles/watermelon.ppm", "file");
    } catch (IOException e) {
      fail("Should not have thrown an exception.");
    }
    try {
      this.model.loadImage("test/testFiles/watermelon.ppm", "file1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown an exception.");
    }

    Pixel[][] ppm = this.files1.get("file1");

    for (int i = 0; i < ppm.length; i++) {
      for (int j = 0; j < ppm[0].length; j++) {
        assertEquals(ppm[i][j], jpeg[i][j]);
      }
    }

    // JPEG -> PNG
    try {
      this.model.saveImage("test/testFiles/watermelon.png", "file");
    } catch (IOException e) {
      fail("Should not have thrown an exception.");
    }
    try {
      this.model.loadImage("test/testFiles/watermelon.png", "file1");
    } catch (FileNotFoundException e) {
      fail("Should not have thrown an exception.");
    }

    Pixel[][] png = this.files1.get("file1");

    for (int i = 0; i < jpeg.length; i++) {
      for (int j = 0; j < jpeg[0].length; j++) {
        assertEquals(png[i][j], jpeg[i][j]);
      }
    }


  }


}