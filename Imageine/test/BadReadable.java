import java.io.IOException;
import java.nio.CharBuffer;

/**
 * Readable that throws an IOException for testing purposes.
 */
public class BadReadable implements Readable {

  /**
   * Throws an IOException no matter what.
   * @param cb Character buffer that represents the specified subsequence of the buffer relative
   *           to the current position.
   * @return an IOException.
   * @throws IOException no matter what.
   */
  @Override
  public int read(CharBuffer cb) throws IOException {
    throw new IOException("I throw an IOException");
  }
}
