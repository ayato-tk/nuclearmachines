package com.mezsiah.nuclearmachines.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class Uranium extends Item{

    public Uranium(Settings settings) {
       
        super(settings.group(ItemGroup.MATERIALS));
    }
    
}
