
package org.solo.core.gui;

import java.util.List;

import net.labymod.api.Laby;
import net.labymod.api.client.gui.screen.Parent;
import net.labymod.api.client.gui.screen.activity.AutoActivity;
import net.labymod.api.client.gui.screen.activity.Link;
import net.labymod.api.client.gui.screen.activity.types.SimpleActivity;
import net.labymod.api.client.gui.screen.widget.widgets.ComponentWidget;
import net.labymod.api.client.gui.screen.widget.widgets.DivWidget;
import net.labymod.api.client.gui.screen.widget.widgets.input.ButtonWidget;

import org.solo.core.module.Module;
import org.solo.core.module.ModuleCategory;
import org.solo.core.module.ModuleManager;

@AutoActivity
@Link("solo-click-gui.lss")
public class SoloClickGuiActivity extends SimpleActivity {

  private final ModuleManager moduleManager;

  public SoloClickGuiActivity(ModuleManager moduleManager) {
    this.moduleManager = moduleManager;
  }

  @Override
  public void initialize(Parent parent) {
    super.initialize(parent);

    DivWidget root = new DivWidget();
    root.addId("solo-root");
    this.document().addChild(root);

    // Brand
    addText(root, "SHADOW", "brand-name");
    addText(root, "CLIENT", "brand-suffix");
    addText(root, "v0.3", "brand-version");

    // Category panels
    addCategoryPanel(root, ModuleCategory.COMBAT, "panel-combat");
    addCategoryPanel(root, ModuleCategory.MOVEMENT, "panel-movement");
    addCategoryPanel(root, ModuleCategory.PLAYER, "panel-player");
    addCategoryPanel(root, ModuleCategory.RENDER, "panel-render");
    addCategoryPanel(root, ModuleCategory.MISC, "panel-misc");

    // Additional panels
    addConfigsPanel(root);
    addThemesPanel(root);
  }

  private void addCategoryPanel(
      DivWidget root,
      ModuleCategory category,
      String panelId
  ) {
    DivWidget panel = new DivWidget();
    panel.addId(panelId);
    root.addChild(panel);

    addText(panel, category.name(), "panel-title");

    List<Module> modules =
        this.moduleManager.getModulesByCategory(category);

    if (modules.isEmpty()) {
      addText(panel, "No modules", "empty-label");
      return;
    }

    for (int i = 0; i < modules.size(); i++) {
      addModuleRow(panel, modules.get(i), i);
    }
  }


  private void addModuleRow(
      DivWidget panel,
      Module module,
      int index
  ) {
    DivWidget row = new DivWidget();
    row.addId("module-row-" + index);
    panel.addChild(row);

    addText(row, module.getName(), "module-name");

    ButtonWidget toggle = ButtonWidget.text(
        module.isEnabled() ? "●" : "○"
    );

    toggle.addId(
        module.isEnabled()
            ? "module-toggle-on"
            : "module-toggle-off"
    );

    toggle.setPressable(() -> {
      module.toggle();

      System.out.println(
          "[Solo] " + module.getName() +
              " is now " +
              (module.isEnabled() ? "ON" : "OFF")
      );

      Laby.labyAPI()
          .minecraft()
          .minecraftWindow()
          .displayScreen(
              new SoloClickGuiActivity(this.moduleManager)
          );
    });

    row.addChild(toggle);
  }


  private void addConfigsPanel(DivWidget root) {
    DivWidget panel = new DivWidget();
    panel.addId("panel-configs");
    root.addChild(panel);

    addText(panel, "CONFIGS", "panel-title");

    addText(panel, "Default", "config-item-0");
    addText(panel, "PvP", "config-item-1");
    addText(panel, "Visual", "config-item-2");
    addText(panel, "Create new", "config-create");
  }

  private void addThemesPanel(DivWidget root) {
    DivWidget panel = new DivWidget();
    panel.addId("panel-themes");
    root.addChild(panel);

    addText(panel, "THEMES", "panel-title");

    addText(panel, "Primary", "theme-item");
    addText(panel, "Background", "theme-item");
    addText(panel, "Foreground", "theme-item");
    addText(panel, "Accent: Purple", "theme-accent");
  }

  private void addText(
      DivWidget root,
      String text,
      String id
  ) {
    ComponentWidget component = ComponentWidget.text(text);
    component.addId(id);
    root.addChild(component);
  }
}
