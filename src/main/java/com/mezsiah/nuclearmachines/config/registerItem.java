package com.mezsiah.nuclearmachines.config;

import com.mezsiah.nuclearmachines.NuclearMachines;
import com.mezsiah.nuclearmachines.item.Uranium;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;


public class registerItem {
    

    private final Item URANIUM = new Uranium(new FabricItemSettings().group(ItemGroup.MATERIALS));

    private void register(Registry registry, String itemName, Item item){
        registry.register(Registry.ITEM, new Identifier(NuclearMachines.MODID, itemName), item);
    }

    public registerItem(Registry registry){
    
        this.register(registry, "uranium", this.URANIUM);
        


    }


    
}
