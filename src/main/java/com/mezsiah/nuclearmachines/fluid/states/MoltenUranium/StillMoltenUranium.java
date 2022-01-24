package com.mezsiah.nuclearmachines.fluid.states.MoltenUranium;

import com.mezsiah.nuclearmachines.fluid.MoltenUranium;

import net.minecraft.fluid.FluidState;

public class StillMoltenUranium extends MoltenUranium{

    @Override
    public int getLevel(FluidState state) { return 8; }
    
    @Override
    public boolean isStill(FluidState state) { return true; }
    
}
