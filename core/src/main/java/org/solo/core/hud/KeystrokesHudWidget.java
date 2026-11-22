
package org.solo.core.hud;

import net.labymod.api.client.gui.hud.binding.category.HudWidgetCategory;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidget;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidgetConfig;
import net.labymod.api.client.gui.hud.hudwidget.text.TextLine;
import net.labymod.api.client.gui.screen.key.Key;

public class KeystrokesHudWidget
    extends TextHudWidget<TextHudWidgetConfig> {

  private TextLine movementLine;
  private TextLine actionLine;

  public KeystrokesHudWidget() {
    super("solo_keystrokes");
    bindCategory(HudWidgetCategory.INGAME);
  }

  @Override
  public void load(TextHudWidgetConfig config) {
    super.load(config);

    movementLine = createLine("Movement", "");
    actionLine = createLine("Actions", "");
  }

  @Override
  public void updateTextContent() {
    if (movementLine == null || actionLine == null) {
      return;
    }

    String movement =
        key("W", Key.W) + " " +
            key("A", Key.A) + " " +
            key("S", Key.S) + " " +
            key("D", Key.D);

    String actions =
        key("SPACE", Key.SPACE) + " " +
            key("SHIFT", Key.R_SHIFT);

    movementLine.updateAndFlush(movement);
    actionLine.updateAndFlush(actions);
  }

  private String key(String name, Key key) {
    return key.isPressed()
        ? "[" + name + "]"
        : name;
  }
}
