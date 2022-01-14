package com.mezsiah.nuclearmachines.block.entity;

import com.mezsiah.nuclearmachines.config.registerBlockEntity;
import com.mezsiah.nuclearmachines.config.registerItem;
import com.mezsiah.nuclearmachines.item.inventory.ImplementedInventory;
import com.mezsiah.nuclearmachines.screen.UraniumGeneratorScreen;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import team.reborn.energy.api.base.SimpleEnergyStorage;

public class UraniumGeneratorEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory{

    private final DefaultedList<ItemStack> Inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);
   
    private long currentEnergy;
    
    public  final SimpleEnergyStorage energyStorage = new SimpleEnergyStorage(1000, 100, 100) {
        @Override
        protected void onFinalCommit() {
            markDirty();
        }

        @Override
        public boolean supportsInsertion() {
            return true;
        }
    };

    public UraniumGeneratorEntity( BlockPos pos, BlockState state) {
        super(registerBlockEntity.URANIUM_GENERATOR_ENTITY, pos, state);
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
         return new UraniumGeneratorScreen(syncId, inv, ScreenHandlerContext.create(world, pos));
    }

    @Override
    public Text getDisplayName() {
        return new TranslatableText(getCachedState().getBlock().getTranslationKey());
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return Inventory;
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, Inventory);
        
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, Inventory);
    }

    public boolean energyActivate(Boolean activate){
        if(activate){
            return false;
        } else{
            return true;
        }
    }

    public long updateEnergy(){
        long generateEnergy =  energyStorage.getAmount() + 1 * 10;
        markDirty();
        return generateEnergy;
    }

    public void setEnergy(UraniumGeneratorEntity entity){
        currentEnergy = entity.energyStorage.getAmount();
    }

    public long getEnergy(){
        return currentEnergy;
    }


    public static void tick(World world, BlockPos pos, BlockState state, UraniumGeneratorEntity entity) {
           
            if(containsFuel(entity)){
                entity.energyStorage.amount = entity.updateEnergy();
                entity.setEnergy(entity);
                for(int i = 0; i <= 100; i++){
                    if( i >= 100){
                        entity.setStack(2, new ItemStack(registerItem.URANIUM, entity.getStack(2).getCount() - 1));
                    }
                }
            }
        
        if(hasRecipe(entity) && hasNotReachedStackLimit(entity)) {
            craftItem(entity, world);
            if(!world.isClient()) {
               
              //  NuclearMachines.log(entity.energyStorage.getAmount() + "");
                
            }
        }
    }
    
    private static boolean containsFuel(UraniumGeneratorEntity entity){
        int fuelQuantity = entity.getStack(2).getCount();
        if(fuelQuantity > 0){
            return true;
        } else{
            return false;
        }
    }

    private static void craftItem(UraniumGeneratorEntity entity, World world) {
        entity.removeStack(0, 1);
        entity.removeStack(1, 1);
        entity.setStack(2, new ItemStack(registerItem.URANIUM, entity.getStack(2).getCount() + 64));
    }

    private static boolean hasRecipe(UraniumGeneratorEntity entity) {
        boolean hasItemInFirstSlot = entity.getStack(0).getItem() == registerItem.URANIUM;
        return hasItemInFirstSlot;
    }

    private static boolean hasNotReachedStackLimit(UraniumGeneratorEntity entity) {
        return entity.getStack(2).getCount() < entity.getStack(2).getMaxCount();
    }

}
