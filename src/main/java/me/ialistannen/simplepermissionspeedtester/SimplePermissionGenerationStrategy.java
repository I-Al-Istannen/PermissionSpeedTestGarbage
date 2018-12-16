package me.ialistannen.simplepermissionspeedtester;

import java.util.Base64;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;

public enum SimplePermissionGenerationStrategy implements PermissionGenerationStrategy {
  RANDOM_CHARS(SimplePermissionGenerationStrategy::randomString),
  RANDOM_WITH_DOTS(length -> randomString(length / 2) + "." + randomString(length / 2 - 1));

  private Function<Integer, String> supplier;

  SimplePermissionGenerationStrategy(Function<Integer, String> supplier) {
    this.supplier = supplier;
  }

  @Override
  public String generatePermission(int length) {
    return supplier.apply(length);
  }

  private static String randomString(int charCount) {
    byte[] bytes = new byte[charCount * 6];
    ThreadLocalRandom.current().nextBytes(bytes);
    return Base64.getEncoder().encodeToString(bytes);
  }
}
