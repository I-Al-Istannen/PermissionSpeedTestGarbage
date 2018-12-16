package me.ialistannen.simplepermissionspeedtester;

public interface PermissionGenerationStrategy {

  /**
   * Generates a single permission.
   *
   * @param length the length
   * @return the generated permission
   */
  String generatePermission(int length);
}
