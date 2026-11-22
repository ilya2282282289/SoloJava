
package org.solo.core.listener;

import net.labymod.api.Laby;
import net.labymod.api.client.gui.screen.key.Key;
import net.labymod.api.event.Phase;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.lifecycle.GameTickEvent;

import org.solo.core.ExampleAddon;
import org.solo.core.gui.SoloClickGuiActivity;

public class ExampleGameTickListener {

  private final ExampleAddon addon;
  private boolean rightShiftWasPressed = false;
  private boolean guiOpen = false;

  public ExampleGameTickListener(ExampleAddon addon) {
    this.addon = addon;
  }

  @Subscribe
  public void onGameTick(GameTickEvent event) {
    if (event.phase() != Phase.PRE) {
      if (guiOpen && !Laby.labyAPI().minecraft().minecraftWindow().isScreenOpened()) {
        guiOpen = false;
      }
      return;
    }

    boolean pressed = Key.R_SHIFT.isPressed();

    if (pressed && !rightShiftWasPressed) {
      toggleSoloGui();
    }

    rightShiftWasPressed = pressed;
  }

  private void toggleSoloGui() {
    if (this.guiOpen) {
      Laby.labyAPI().minecraft().minecraftWindow().closeScreen();
      this.guiOpen = false;
      return;
    }

    Laby.labyAPI()
        .minecraft()
        .minecraftWindow()
        .displayScreen(new SoloClickGuiActivity(
            this.addon.moduleManager()
        ));
    this.guiOpen = true;
  }
}
