package com.mezsiah.nuclearmachines.config;

import com.mezsiah.nuclearmachines.NuclearMachines;
import com.mezsiah.nuclearmachines.block.entity.UraniumGeneratorEntity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import team.reborn.energy.api.EnergyStorage;

public class registerBlockEntity {
    public static  BlockEntityType<UraniumGeneratorEntity> URANIUM_GENERATOR_ENTITY =
    Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(NuclearMachines.MODID, "uranium_generator"),
    FabricBlockEntityTypeBuilder.create(UraniumGeneratorEntity::new,
    registerBlock.URANIUM_GENERATOR).build(null));

    public registerBlockEntity(){
        EnergyStorage.SIDED.registerForBlockEntity((URANIUM_GENERATOR_ENTITY, direction) -> URANIUM_GENERATOR_ENTITY.energyStorage, URANIUM_GENERATOR_ENTITY);
    }

}
