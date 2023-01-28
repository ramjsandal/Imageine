import org.junit.Before;
import org.junit.Test;

import model.Pixel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

/**
 * Represents tests for operations found and expected in {@code model.Pixel}.
 */
public class PixelTest {

  Pixel one;

  Pixel two;

  Pixel three;

  // initializing the previously set fields
  @Before
  public void init() {

    this.one = new Pixel(1, 2, 3);
    this.two = new Pixel(0, 1, 2);
    this.three = new Pixel(1, 1, 1);

  }

  // tests invalid constructor
  @Test
  public void testInvalidConstructor() {

    // construct new pixel with negative red value
    try {
      Pixel invalid = new Pixel(-1, 2, 3);
      assertEquals(new Pixel(0, 2, 3), invalid);
    } catch (IllegalArgumentException e) {
      fail("Should not have thrown IllegalArgumentException.");
    }

    // construct new pixel with negative green value
    try {
      Pixel invalid = new Pixel(1, -2, 3);
      assertEquals(new Pixel(1, 0, 3), invalid);
    } catch (IllegalArgumentException e) {
      fail("Should have thrown IllegalArgumentException.");
    }

    // construct new pixel with negative blue value
    try {
      Pixel invalid = new Pixel(1, 2, -3);
      assertEquals(new Pixel(1, 2, 0), invalid);
    } catch (IllegalArgumentException e) {
      fail("Should have thrown IllegalArgumentException.");
    }

    // constructs new pixel with negative red and green
    try {
      Pixel invalid = new Pixel(-1, -2, 3);
      assertEquals(new Pixel(0, 0, 3), invalid);
    } catch (IllegalArgumentException e) {
      fail("Should have thrown IllegalArgumentException.");
    }

    // constructs new pixel with negative red and blue
    try {
      Pixel invalid = new Pixel(-1, 2, -3);
      assertEquals(new Pixel(0, 2, 0), invalid);
    } catch (IllegalArgumentException e) {
      fail("Should have thrown IllegalArgumentException.");
    }

    // constructs new pixel with negative green and blue
    try {
      Pixel invalid = new Pixel(1, -2, -3);
      assertEquals(new Pixel(1, 0, 0), invalid);
    } catch (IllegalArgumentException e) {
      fail("Should have thrown IllegalArgumentException.");
    }

    // constructs new pixel with 2 negative one over 255
    try {
      Pixel invalid = new Pixel(-1, -2, 256);
      assertEquals(new Pixel(0, 0, 255), invalid);
    } catch (IllegalArgumentException e) {
      fail("Should have thrown IllegalArgumentException.");
    }


  }

  @Test
  public void testValidConstructor() {
    // construct a valid pixel
    Pixel valid = new Pixel(0, 255, 48);

    assertEquals(new Pixel(0, 255, 48), valid);

    Pixel valid1 = new Pixel(0, 0, 0);

    assertEquals(new Pixel(0, 0, 0), valid1);

    Pixel valid2 = new Pixel(300, 255, 0);

    assertEquals(new Pixel(300, 255, 0), valid2);
  }

  @Test
  public void testBrighten() {

    assertEquals(new Pixel(1, 2, 3), this.one);
    assertEquals(new Pixel(0, 1, 2), this.two);
    assertEquals(new Pixel(1, 1, 1), this.three);

    // testing darkening
    Pixel darkOne = this.one.brighten(-1);

    assertEquals(darkOne, this.two);

    // testing brightening
    Pixel brightTwo = this.two.brighten(5);

    assertEquals(new Pixel(5, 6, 7), brightTwo);

    // testing brightening all below 0

    Pixel clampZero = this.three.brighten(-10);

    assertEquals(new Pixel(0, 0, 0), clampZero);

    // testing brightening all above 255

    Pixel clamp255 = clampZero.brighten(300);

    assertEquals(new Pixel(255, 255, 255), clamp255);

    // testing brightening some below 0

    Pixel oneBlue = this.one.brighten(-2);

    assertEquals(new Pixel(0, 0, 1), oneBlue);

    // testing brightening some above 255

    Pixel oneBelow = this.one.brighten(253);

    assertEquals(new Pixel(254, 255, 255), oneBelow);

  }

  @Test
  public void testGreyscale() {

    // test greyscaling to red

    assertEquals(new Pixel(1, 2, 3), this.one);

    Pixel greyscaleRed = this.one.greyscale("red");

    assertEquals(this.three, greyscaleRed);


    // test greyscaling to green

    assertEquals(new Pixel(1, 2, 3), this.one);

    Pixel greyscaleGreen = this.one.greyscale("green");

    assertEquals(new Pixel(2, 2, 2), greyscaleGreen);


    // test greyscaling to blue

    assertEquals(new Pixel(1, 2, 3), this.one);

    Pixel greyscaleBlue = this.one.greyscale("blue");

    assertEquals(new Pixel(3, 3, 3), greyscaleBlue);


    // test greyscaling to value

    assertEquals(new Pixel(0, 1, 2), this.two);

    Pixel greyscaleValue = this.two.greyscale("value");

    assertEquals(new Pixel(2, 2, 2), greyscaleValue);


    // test greyscaling to intensity

    assertEquals(new Pixel(0, 1, 2), this.two);

    Pixel greyscaleIntensity = this.two.greyscale("intensity");

    assertEquals(new Pixel(1, 1, 1), greyscaleIntensity);


    // test greyscaling to luma

    Pixel intensityPixel = new Pixel(40, 123, 200);

    Pixel greyscaleLuma = intensityPixel.greyscale("luma");

    assertEquals(new Pixel(110, 110, 110), greyscaleLuma);


    // test greyscaling with an invalid input

    try {
      this.one.greyscale("I am not a valid input >:D");
      fail("Should have thrown an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Not a valid component.", e.getMessage());
    }
  }

  @Test
  public void testEquals() {

    // test equals says two things that have the same fields are equal
    Pixel equalOne = new Pixel(1, 2, 3);

    assertEquals(equalOne, this.one);

    Pixel equalThree = new Pixel(1, 1, 1);

    assertEquals(equalThree, this.three);

    // test equals says two things that do not have the same fields are equal
    Pixel notEqualOne = new Pixel(1, 2, 4);

    assertNotEquals(notEqualOne, this.one);

    Pixel stillNotEqualOne = new Pixel(1, 3, 3);

    assertNotEquals(stillNotEqualOne, this.one);

    Pixel evenNowNotEqualOne = new Pixel(0, 2, 3);

    assertNotEquals(evenNowNotEqualOne, this.one);

  }

  @Test
  public void testHashCode() {

    Pixel equalOne = new Pixel(1, 2, 3);

    assertEquals(equalOne.hashCode(), this.one.hashCode());

  }
}