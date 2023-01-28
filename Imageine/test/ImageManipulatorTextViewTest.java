import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import view.ImageManipulatorTextView;
import view.ImageManipulatorView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests for the ImageManipulatorTextView class.
 */
public class ImageManipulatorTextViewTest {

  ImageManipulatorTextView view1;
  Appendable out;

  @Before
  public void init() {
    this.out = new StringBuilder();
    this.view1 = new ImageManipulatorTextView(out);
  }

  @Test
  public void testConstructor() {

    // make sure the valid constructor does not throw an exception
    try {
      this.view1 = new ImageManipulatorTextView(this.out);
    } catch (IllegalArgumentException e) {
      fail("Should not have thrown an exception.");
    }

    // throws an exception when output is null
    try {
      this.view1 = new ImageManipulatorTextView(null);
      fail("Should have thrown an Exception");
    } catch (IllegalArgumentException e) {
      assertEquals("Appendable can't be null.", e.getMessage());
    }
  }

  @Test
  public void testRenderMessage() {
    Appendable out1 = new StringBuilder();
    this.view1 = new ImageManipulatorTextView(out1);
    try {
      view1.renderMessage("Game Over!");
    } catch (IOException e) {
      fail("Shouldn't have thrown out an IOException.");
    }
    assertEquals(out1.toString(), "Game Over!");

    Appendable out2 = new StringBuilder();
    this.view1 = new ImageManipulatorTextView(out2);
    try {
      view1.renderMessage("Koala looks nice.");
    } catch (IOException e) {
      fail("Shouldn't have thrown out an IOException.");
    }
    assertEquals(out2.toString(), "Koala looks nice.");

    Appendable out3 = new StringBuilder();
    this.view1 = new ImageManipulatorTextView(out2);
    try {
      view1.renderMessage("");
    } catch (IOException e) {
      fail("Shouldn't have thrown out an IOException.");
    }
    assertEquals(out3.toString(), "");

  }

  @Test
  public void testIOException() {
    try {
      ImageManipulatorView v1 = new ImageManipulatorTextView(new BadAppendable());
      v1.renderMessage("Fails");
      fail("Should have thrown an IOException.");
    } catch (IOException io) {
      assertEquals("I throw an IOException.", io.getMessage());
    }
  }
}

