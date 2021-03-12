package com.Denfop.integration.crafttweaker;

import minetweaker.MineTweakerAPI;

public class CTCore {
  public CTCore() {
    MineTweakerAPI.registerClass(CTMolecularTransformer.class);
    MineTweakerAPI.registerClass(CTAlloySmelter.class);
    
  }
}
