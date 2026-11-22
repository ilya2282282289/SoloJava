
package org.solo.core.modules;

import org.solo.core.module.Module;
import org.solo.core.module.ModuleCategory;

public class ExampleModule extends Module {

  public ExampleModule() {
    super(
        "Example",
        "Our first ShadowClient module",
        ModuleCategory.MISC
    );
  }

  @Override
  protected void onEnable() {
    System.out.println("Example enabled!");
  }

  @Override
  protected void onDisable() {
    System.out.println("Example disabled!");
  }
}
