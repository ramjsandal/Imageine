import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import model.ImageUtil;
import model.PPMImageManipulatorModel;
import model.Pixel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests for the ImageUtil class.
 */
public class ImageUtilTest {

  ImageUtil util2 = new ImageUtil();

  // tests the ReadPPM method
  @Test
  public void testReadPPM() {

    //test readPPM reads a file correctly
    try {
      Pixel[][] bruh = this.util2.readPPM("test/testFiles/try.txt");

      assertEquals(new Pixel(10, 12, 15), bruh[0][0]);
      assertEquals(new Pixel(1, 4, 7), bruh[0][1]);
      assertEquals(new Pixel(8, 26, 4), bruh[0][2]);
    } catch (FileNotFoundException e) {
      fail("Should not have thrown an exception.");
      // shouldn't throw an exception
    }

    //test it throws an exception when told to read a file that does not exist
    try {
      this.util2.readPPM("idonotexist");
      fail("Should have thrown a file not found exception.");
    } catch (FileNotFoundException e) {
      assertEquals("Cannot find file", e.getMessage());
    }

    //test it throws an exception when the file does not begin with "P3"

    try {
      this.util2.readPPM("test/testFiles/missingP3");
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException | FileNotFoundException e) {
      assertEquals("Invalid PPM file: plain RAW file should begin with P3",
              e.getMessage());
    }

    // tests for non-PPM files

    try {
      this.util2.readPPM("test/testFiles/testTwo2.bmp");
    } catch (IllegalArgumentException | FileNotFoundException e) {
      assertEquals("Invalid PPM file: plain RAW file should begin with P3",
              e.getMessage());
    }

    try {
      this.util2.readPPM("test/testFiles/testTwo.png");
    } catch (IllegalArgumentException | FileNotFoundException e) {
      assertEquals("Invalid PPM file: plain RAW file should begin with P3",
              e.getMessage());
    }
  }

  /*
   * For these tests, I'm using the assertEquals we KNOW is true from other tests.
   * We already know the contents of these files from the many other tests.
   * Therefore, we just make sure that when we read it, it's still the same.
   */
  // tests reading a PPM through readGeneral
  @Test
  public void testReadGeneralPPM() {

    try {
      Pixel[][] bruh = this.util2.readGeneral("test/testFiles/try.txt");

      assertEquals(new Pixel(10, 12, 15), bruh[0][0]);
      assertEquals(new Pixel(1, 4, 7), bruh[0][1]);
      assertEquals(new Pixel(8, 26, 4), bruh[0][2]);
    } catch (IOException e) {
      fail("Should not have thrown an exception.");
      // shouldn't throw an exception
    }

    //test it throws an exception when told to read a file that does not exist
    try {
      this.util2.readGeneral("idonotexist.ppm");
      fail("Should have thrown a file not found exception.");
    } catch (IOException e) {
      assertEquals("Cannot find file", e.getMessage());
    }

  }

  // testing read General on a BMP
  @Test
  public void testReadGeneralBMP() {

    try {
      Pixel[][] bmp = this.util2.readGeneral("test/testFiles/testTwo2.bmp");

      assertEquals(new Pixel(38, 31, 23), bmp[0][0]);
      assertEquals(new Pixel(78, 29, 58), bmp[0][1]);
      assertEquals(new Pixel(23, 41, 74), bmp[1][0]);
      assertEquals(new Pixel(123, 215, 201), bmp[1][1]);
    } catch (IOException e) {
      fail("Should not have thrown an exception.");
      // shouldn't throw an exception
    }

    //test it throws an exception when told to read a file that does not exist
    try {
      this.util2.readGeneral("idonotexist.bmp");
      fail("Should have thrown a file not found exception.");
    } catch (IOException e) {
      assertEquals("Can't read input file!", e.getMessage());
    }
  }

  // test readGeneral on a PNG
  @Test
  public void testReadPNG() {

    try {
      Pixel[][] png = this.util2.readGeneral("test/testFiles/testTwo.png");

      assertEquals(new Pixel(38, 31, 23), png[0][0]);
      assertEquals(new Pixel(78, 29, 58), png[0][1]);
      assertEquals(new Pixel(23, 41, 74), png[1][0]);
      assertEquals(new Pixel(123, 215, 201), png[1][1]);
    } catch (IOException e) {
      fail("Should not have thrown an exception.");
      // shouldn't throw an exception
    }

    //test it throws an exception when told to read a file that does not exist
    try {
      this.util2.readGeneral("idonotexist.png");
      fail("Should have thrown a file not found exception.");
    } catch (IOException e) {
      assertEquals("Can't read input file!", e.getMessage());
    }

  }

  // tests read general on a JPEG
  @Test
  public void testReadGeneralJPEG() {
    try {
      Pixel[][] jpeg = this.util2.readGeneral("test/testFiles/test.jpeg");

      assertEquals(new Pixel(145, 72, 39), jpeg[0][0]);
      assertEquals(new Pixel(176, 103, 70), jpeg[0][1]);
      assertEquals(new Pixel(255, 197, 164), jpeg[1][0]);
      assertEquals(new Pixel(206, 133, 100), jpeg[1][1]);

    } catch (IOException e) {
      fail("Should not have thrown an exception.");
      // shouldn't throw an exception
    }

    //test it throws an exception when told to read a file that does not exist
    try {
      this.util2.readGeneral("idonotexist.jpeg");
      fail("Should have thrown a file not found exception.");
    } catch (IOException e) {
      assertEquals("Can't read input file!", e.getMessage());
    }

  }

  // tests writing a PPM with readPPM
  @Test
  public void testWritePPM() {
    Scanner sc;
    try {
      Pixel[][] bruh = this.util2.readPPM("test/testFiles/try.txt");

      this.util2.writePPM(bruh, "test/testFiles/txttwo.ppm");

      try {
        sc = new Scanner(new FileInputStream("test/testFiles/txttwo.ppm"));
      } catch (FileNotFoundException e) {
        throw new FileNotFoundException("Cannot find file");
      }
      StringBuilder builder = new StringBuilder();
      //read the file line by line, and populate a string. This will throw away any comment lines
      while (sc.hasNextLine()) {
        String s = sc.nextLine();
        if (s.charAt(0) != '#') {
          builder.append(s + "\n");
        }
      }

      String outputString = builder.toString();

      String[] out = outputString.split("\n");

      assertEquals("P3", out[0]);
      assertEquals("3 1", out[1]);
      assertEquals("255", out[2]);
      assertEquals("10", out[3]);
      assertEquals("12", out[4]);
      assertEquals("15", out[5]);
      assertEquals("1", out[6]);
      assertEquals("4", out[7]);
      assertEquals("7", out[8]);
      assertEquals("8", out[9]);
      assertEquals("26", out[10]);
      assertEquals("4", out[11]);

    } catch (IOException e) {
      fail("Should not have thrown an exception");
    }
  }

  // tests writePPM on a brightened PPM
  @Test
  public void testWriteBrightnessPPM() {
    Scanner sc;

    try {

      PPMImageManipulatorModel ppm = new PPMImageManipulatorModel();

      ppm.loadImage("test/testFiles/try.txt", "try");

      ppm.brighten(100, "try", "Bright try");

      ppm.saveImage("test/testFiles/btry.ppm", "Bright try");

      try {
        sc = new Scanner(new FileInputStream("test/testFiles/btry.ppm"));
      } catch (FileNotFoundException e) {
        throw new FileNotFoundException("Cannot find file");
      }
      StringBuilder builder = new StringBuilder();
      //read the file line by line, and populate a string. This will throw away any comment lines
      while (sc.hasNextLine()) {
        String s = sc.nextLine();
        if (s.charAt(0) != '#') {
          builder.append(s + "\n");
        }
      }

      String outputString = builder.toString();

      String[] out = outputString.split("\n");

      assertEquals("P3", out[0]);
      assertEquals("3 1", out[1]);
      assertEquals("255", out[2]);
      assertEquals("110", out[3]);
      assertEquals("112", out[4]);
      assertEquals("115", out[5]);
      assertEquals("101", out[6]);
      assertEquals("104", out[7]);
      assertEquals("107", out[8]);
      assertEquals("108", out[9]);
      assertEquals("126", out[10]);
      assertEquals("104", out[11]);

    } catch (IOException e) {
      fail("Should not have thrown an exception");
    }

  }

  // tests writePPM on a greyscaled ppm
  @Test
  public void testWriteGreyscalePPM() {

    Scanner sc;

    try {

      PPMImageManipulatorModel ppm = new PPMImageManipulatorModel();

      ppm.loadImage("test/testFiles/try.txt", "try");

      ppm.greyscale("red", "try", "Grey Red try");

      ppm.saveImage("test/testFiles/greyRedTry.ppm", "Grey Red try");


      try {
        sc = new Scanner(new FileInputStream("test/testFiles/greyRedTry.ppm"));
      } catch (FileNotFoundException e) {
        throw new FileNotFoundException("Cannot find file");
      }
      StringBuilder builder = new StringBuilder();
      //read the file line by line, and populate a string. This will throw away any comment lines
      while (sc.hasNextLine()) {
        String s = sc.nextLine();
        if (s.charAt(0) != '#') {
          builder.append(s + "\n");
        }
      }

      String outputString = builder.toString();

      String[] out = outputString.split("\n");

      assertEquals("P3", out[0]);
      assertEquals("3 1", out[1]);
      assertEquals("255", out[2]);
      assertEquals("10", out[3]);
      assertEquals("10", out[4]);
      assertEquals("10", out[5]);
      assertEquals("1", out[6]);
      assertEquals("1", out[7]);
      assertEquals("1", out[8]);
      assertEquals("8", out[9]);
      assertEquals("8", out[10]);
      assertEquals("8", out[11]);

    } catch (IOException e) {
      fail("Should not have thrown an exception");
    }
  }

  // tests writePPM on a vertically flipped ppm
  @Test
  public void testWriteVerticalFlipPPM() {

    Scanner sc;

    try {

      PPMImageManipulatorModel ppm = new PPMImageManipulatorModel();

      ppm.loadImage("test/testFiles/try.txt", "try");

      ppm.verticalFlip("try", "Upside Down try");

      ppm.saveImage("test/testFiles/upsideDownTry.ppm", "Upside Down try");


      try {
        sc = new Scanner(new FileInputStream("test/testFiles/upsideDownTry.ppm"));
      } catch (FileNotFoundException e) {
        throw new FileNotFoundException("Cannot find file");
      }
      StringBuilder builder = new StringBuilder();
      //read the file line by line, and populate a string. This will throw away any comment lines
      while (sc.hasNextLine()) {
        String s = sc.nextLine();
        if (s.charAt(0) != '#') {
          builder.append(s + "\n");
        }
      }

      String outputString = builder.toString();

      String[] out = outputString.split("\n");

      assertEquals("P3", out[0]);
      assertEquals("3 1", out[1]);
      assertEquals("255", out[2]);
      assertEquals("10", out[3]);
      assertEquals("12", out[4]);
      assertEquals("15", out[5]);
      assertEquals("1", out[6]);
      assertEquals("4", out[7]);
      assertEquals("7", out[8]);
      assertEquals("8", out[9]);
      assertEquals("26", out[10]);
      assertEquals("4", out[11]);

    } catch (IOException e) {
      fail("Should not have thrown an exception");
    }
  }

  // tests writePPM on a horizontally flipped PPM
  @Test
  public void testWriteHorizontalFlipPPM() {

    Scanner sc;

    try {

      PPMImageManipulatorModel ppm = new PPMImageManipulatorModel();

      ppm.loadImage("test/testFiles/try.txt", "try");

      ppm.horizontalFlip("try", "Flipped try");

      ppm.saveImage("test/testFiles/flippedTry.ppm", "Flipped try");


      try {
        sc = new Scanner(new FileInputStream("test/testFiles/flippedTry.ppm"));
      } catch (FileNotFoundException e) {
        throw new FileNotFoundException("Cannot find file");
      }
      StringBuilder builder = new StringBuilder();
      //read the file line by line, and populate a string. This will throw away any comment lines
      while (sc.hasNextLine()) {
        String s = sc.nextLine();
        if (s.charAt(0) != '#') {
          builder.append(s + "\n");
        }
      }

      String outputString = builder.toString();

      String[] out = outputString.split("\n");

      assertEquals("P3", out[0]);
      assertEquals("3 1", out[1]);
      assertEquals("255", out[2]);
      assertEquals("10", out[9]);
      assertEquals("12", out[10]);
      assertEquals("15", out[11]);
      assertEquals("1", out[6]);
      assertEquals("4", out[7]);
      assertEquals("7", out[8]);
      assertEquals("8", out[3]);
      assertEquals("26", out[4]);
      assertEquals("4", out[5]);

    } catch (IOException e) {
      fail("Should not have thrown an exception");
    }
  }

  /*
   * For these tests, I'm going to read a file, write it and then re-read it.
   * We are able to re-read it as we have tested the read method and know it works.
   * Then, I'm going to check that everything in the file written by writeGeneral
   * matches the original file.
   */

  // tests writeGeneral for a JPEG
//  @Test
//  public void testWriteGeneralJPEG() {
//    try {
//      Pixel[][] jpg = ImageUtil.readGeneral("test/testFiles/test.jpeg");
//      ImageUtil.writeGeneral(jpg, "test/testFiles/newTest.jpeg");
//      Pixel[][] jpgWrite = ImageUtil.readGeneral("test/testFiles/newTest.jpeg");
//
//      for (int i = 0; i < jpg.length; i++) {
//        for (int j = 0; j < jpg[0].length; j++) {
//          assertEquals(jpg[i][j], jpgWrite[i][j]);
//
//        }
//      }
//
//    } catch (IOException e) {
//      fail("Should not have failed.");
//    }
//
//  }

  // tests writeGeneral on a jpeg
//  @Test
//  public void testWriteGeneralPNG() {
//    try {
//      Pixel[][] png = ImageUtil.readGeneral("test/testFiles/drake.png");
//      ImageUtil.writeGeneral(png, "test/testFiles/newDrake.png");
//      Pixel[][] pngWrite = ImageUtil.readGeneral("test/testFiles/newDrake.png");
//
//
//      for (int i = 0; i < png.length; i++) {
//        for (int j = 0; j < png[0].length; j++) {
//          assertEquals(png[i][j], pngWrite[i][j]);
//        }
//      }
//
//    } catch (IOException e) {
//      fail("Should not have failed.");
//    }
//  }

  // tests writeGeneral on a BMP
  @Test
  public void testWriteGeneralBMP() {
    try {
      Pixel[][] bmp = ImageUtil.readGeneral("test/testFiles/testTwo2.bmp");
      ImageUtil.writeGeneral(bmp, "test/testFiles/newTestTwo2.bmp");
      Pixel[][] bmpWrite = ImageUtil.readGeneral("test/testFiles/newTestTwo2.bmp");

      for (int i = 0; i < bmp.length; i++) {
        for (int j = 0; j < bmp[0].length; j++) {
          assertEquals(bmp[i][j], bmpWrite[i][j]);
        }
      }

    } catch (IOException e) {
      fail("Should not have failed.");
    }

  }


  // tests the writeGeneral for PPM
//  @Test
//  public void testWriteGeneralPPM() {
//    try {
//      Pixel[][] ppm = ImageUtil.readGeneral("test/testFiles/testTwo2.ppm");
//      ImageUtil.writeGeneral(ppm, "test/testFiles/newTestTwo2.ppm");
//      Pixel[][] ppmWrite = ImageUtil.readGeneral("test/testFiles/newTestTwo2.ppm");
//
//      for (int i = 0; i < ppm.length; i++) {
//        for (int j = 0; j < ppm[0].length; j++) {
//          assertEquals(ppm[i][j], ppmWrite[i][j]);
//        }
//      }
//    } catch (IOException e) {
//      fail(e.getMessage());
//    }
//  }
}