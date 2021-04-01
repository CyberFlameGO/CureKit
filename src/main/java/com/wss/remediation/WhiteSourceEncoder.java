package com.wss.remediation;

import java.util.ArrayList;
import java.util.List;
import lombok.NonNull;
import org.apache.commons.lang3.SystemUtils;
import org.owasp.esapi.codecs.UnixCodec;
import org.owasp.esapi.codecs.WindowsCodec;

/**
 * Remediation Solver static class written by WhiteSource with the community ❤. Here you can find
 * wrapper functions to secure unsafe operations in your code.
 */
public class WhiteSourceEncoder {

  /**
   * Encoding operating system parameters.
   *
   * @param params The parameters for the operating systems.
   * @return Encoded parameters.
   */
  public static String OSParameterEncoder(@NonNull final String params)
      throws UnsupportedOperationException {
    if (SystemUtils.IS_OS_WINDOWS) {
      return Utils.esapiEncoder(new WindowsCodec(), params);
    } else if (SystemUtils.IS_OS_UNIX) {
      return Utils.esapiEncoder(new UnixCodec(), params);
    }

    throw new UnsupportedOperationException("Unsupported encoder for operating system");
  }

  /**
   * Encoding content for logs.
   *
   * @param contents arrays {@link Object} contains all the contents.
   * @return encoded log content.
   */
  public static String[] multiLogContentEncoder(@NonNull final Object[] contents) {

    List<String> results = new ArrayList<>();

    for (Object content : contents) {
      results.add(logContentEncoder(content));
    }
    return results.toArray(String[]::new);
  }

  /**
   * Encoding content for logs.
   *
   * @param content {@link Object} contains the content.
   * @return encoded log content.
   */
  public static String logContentEncoder(@NonNull final Object content) {
    return content
        .toString()
        .replaceAll("[\n|\r|\t]", "_")
        .replaceAll("<", "&lt")
        .replaceAll(">", "&gt");
  }
}
