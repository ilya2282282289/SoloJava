package org.solo.core.modules;

import org.solo.core.module.Module;
import org.solo.core.module.ModuleCategory;

public class SimpleModule extends Module {

  public SimpleModule(String name, String description, ModuleCategory category) {
    super(name, description, category);
  }

  @Override
  protected void onEnable() {
    System.out.println("[Solo] " + getName() + " enabled!");
  }

  @Override
  protected void onDisable() {
    System.out.println("[Solo] " + getName() + " disabled!");
  }
}