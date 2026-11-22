package org.solo.core;

import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonMain;
import org.solo.core.commands.ExamplePingCommand;
import org.solo.core.listener.ExampleGameTickListener;
import org.solo.core.module.ModuleCategory;
import org.solo.core.module.ModuleManager;
import org.solo.core.modules.ExampleModule;
import org.solo.core.modules.SimpleModule;
import net.labymod.api.Laby;
import org.solo.core.hud.KeystrokesHudWidget;



@AddonMain
public class ExampleAddon extends LabyAddon<ExampleConfiguration> {

  private final ModuleManager moduleManager = new ModuleManager();

  @Override
  protected void enable() {
    this.registerSettingCategory();

    this.registerListener(new ExampleGameTickListener(this));
    this.registerCommand(new ExamplePingCommand());
    this.moduleManager.register(new ExampleModule());

    this.logger().info("Enabled the Addon");

    this.moduleManager.register(new SimpleModule(
        "Click GUI", "Open and customize the client menu", ModuleCategory.RENDER
    ));

    this.moduleManager.register(new SimpleModule(
        "HUD Editor", "Arrange overlays anywhere on screen", ModuleCategory.RENDER
    ));

    this.moduleManager.register(new SimpleModule(
        "Keystrokes", "Display pressed movement keys", ModuleCategory.RENDER
    ));

    this.moduleManager.register(new SimpleModule(
        "Session Info", "Track your current play session", ModuleCategory.MISC
    ));

    Laby.labyAPI().hudWidgetRegistry()
        .register(KeystrokesHudWidget::new);
  }

  @Override
  protected Class<ExampleConfiguration> configurationClass() {
    return ExampleConfiguration.class;
  }

  public ModuleManager moduleManager() {
    return this.moduleManager;
  }

}
