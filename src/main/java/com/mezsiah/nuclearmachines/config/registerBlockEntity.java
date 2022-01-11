package com.mezsiah.nuclearmachines.config;

import com.mezsiah.nuclearmachines.NuclearMachines;
import com.mezsiah.nuclearmachines.block.entity.UraniumFurnaceEntity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import team.reborn.energy.api.EnergyStorage;

public class registerBlockEntity {
    public static  BlockEntityType<UraniumFurnaceEntity> URANIUM_FURNACE_ENTITY =
    Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(NuclearMachines.MODID, "uranium_furnace"),
    FabricBlockEntityTypeBuilder.create(UraniumFurnaceEntity::new,
    registerBlock.URANIUM_FURNACE).build(null));

    public registerBlockEntity(){
        EnergyStorage.SIDED.registerForBlockEntity((URANIUM_FURNACE_ENTITY, direction) -> URANIUM_FURNACE_ENTITY.energyStorage, URANIUM_FURNACE_ENTITY);
    }

}
