import java.io.IOException;

/**
 * Appendable that throws IOExceptions for testing purposes.
 */
public class BadAppendable implements Appendable {

  /**
   * Throws an IOException no matter what.
   *
   * @param csq The character sequence to append.  If {@code csq} is
   *            {@code null}, then the four characters {@code "null"} are
   *            appended to this Appendable.
   * @return an IOException
   * @throws IOException no matter what.
   */
  @Override
  public Appendable append(CharSequence csq) throws IOException {
    throw new IOException("I throw an IOException.");
  }

  /**
   * Do nothing, unused.
   *
   * @param csq   The character sequence from which a subsequence will be
   *              appended.  If {@code csq} is {@code null}, then characters
   *              will be appended as if {@code csq} contained the four
   *              characters {@code "null"}.
   * @param start The index of the first character in the subsequence
   * @param end   The index of the character following the last character in the
   *              subsequence
   * @return null
   */
  @Override
  public Appendable append(CharSequence csq, int start, int end) {
    return null;
  }

  /**
   * Does nothing.
   *
   * @param c The character to append
   * @return null
   */
  @Override
  public Appendable append(char c) {
    return null;
  }
}
