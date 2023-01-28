import org.junit.Test;

import java.io.IOException;

import model.EvenBetterImageManipulatorModel;
import model.EvenBetterImageManipulatorModelImpl;

import static org.junit.Assert.*;

public class EvenBetterImageManipulatorModelImplTest {


  @Test
  public void sanityCheck() {
    EvenBetterImageManipulatorModel model = new EvenBetterImageManipulatorModelImpl();
    try {
      model.loadImage("test/testFiles/test2.txt", "test");
    } catch (IOException e) {
      fail("Should not throw exception");
    }

    int[] out = model.getHistogram("red", "test");

    for (int i = 0; i < 256; i++) {
      System.out.println(i + " ," + out[i]);
    }


  }

}