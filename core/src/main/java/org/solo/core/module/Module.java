
package org.solo.core.module;

public abstract class Module {

  private final String name;
  private final String description;
  private final ModuleCategory category;

  private boolean enabled;

  public Module(
      String name,
      String description,
      ModuleCategory category
  ) {
    this.name = name;
    this.description = description;
    this.category = category;
    this.enabled = false;
  }

  public void toggle() {
    setEnabled(!this.enabled);
  }

  public void setEnabled(boolean enabled) {
    if (this.enabled == enabled) {
      return;
    }

    this.enabled = enabled;

    if (enabled) {
      onEnable();
    } else {
      onDisable();
    }
  }

  protected void onEnable() {
  }

  protected void onDisable() {
  }

  public String getName() {
    return this.name;
  }

  public String getDescription() {
    return this.description;
  }

  public ModuleCategory getCategory() {
    return this.category;
  }

  public boolean isEnabled() {
    return this.enabled;
  }
}
