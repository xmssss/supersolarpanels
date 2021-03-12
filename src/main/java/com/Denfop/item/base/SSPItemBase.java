
package com.Denfop.item.base;

import com.Denfop.IUCore;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class SSPItemBase extends Item {

	public SSPItemBase() {
		this.setCreativeTab((CreativeTabs) IUCore.tabssp3);
	}

	public int getItemStackLimit() {
		return this.maxStackSize;
	}

	

}
