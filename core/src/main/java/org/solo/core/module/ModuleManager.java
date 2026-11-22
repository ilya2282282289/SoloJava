
package org.solo.core.module;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ModuleManager {

  private final List<Module> modules = new ArrayList<>();

  public void register(Module module) {
    this.modules.add(module);
  }

  public List<Module> getModules() {
    return Collections.unmodifiableList(this.modules);
  }

  public Module getModule(String name) {
    for (Module module : this.modules) {
      if (module.getName().equalsIgnoreCase(name)) {
        return module;
      }
    }

    return null;
  }

  public List<Module> getModulesByCategory(ModuleCategory category) {
    List<Module> result = new ArrayList<>();

    for (Module module : this.modules) {
      if (module.getCategory() == category) {
        result.add(module);
      }
    }

    return result;
  }
}
