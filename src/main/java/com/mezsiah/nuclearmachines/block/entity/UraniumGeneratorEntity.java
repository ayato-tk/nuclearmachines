package com.mezsiah.nuclearmachines.block.entity;

import com.mezsiah.nuclearmachines.NuclearMachines;
import com.mezsiah.nuclearmachines.config.registerBlockEntity;
import com.mezsiah.nuclearmachines.config.registerItem;
import com.mezsiah.nuclearmachines.item.inventory.ImplementedInventory;
import com.mezsiah.nuclearmachines.screen.UraniumGeneratorScreen;

import io.github.cottonmc.cotton.gui.PropertyDelegateHolder;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import team.reborn.energy.api.base.SimpleEnergyStorage;

public class UraniumGeneratorEntity extends BlockEntity implements PropertyDelegateHolder, NamedScreenHandlerFactory, ImplementedInventory{

    private final DefaultedList<ItemStack> Inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);
   
    private int currentEnergy;
    private int fuel;

    private final long MAX_ENERGY_BUFFER = 100000;
    private final int MAX_FUEL = 50000;
    
    public  final SimpleEnergyStorage energyStorage = new SimpleEnergyStorage(MAX_ENERGY_BUFFER, 500, 500) {
        @Override
        protected void onFinalCommit() {
            markDirty();
        }

        @Override
        public boolean supportsInsertion() {
            return true;
        }
        @Override
        public long insert(long maxAmount, TransactionContext transaction) {
            return super.insert(maxAmount, transaction);
        }

    };

    private final PropertyDelegate propertyDelegate = new PropertyDelegate() {

        @Override
        public int size() {
            return 2;
        }
        @Override
        public int get(int index){
            if(index == 0){
                return currentEnergy;
            } else if (index == 1){
                return fuel;
            }
            return -1;
        }
        @Override
        public void set(int index, int value){}

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
        currentEnergy = nbt.getInt("uranium.generator_energy");
        fuel = nbt.getInt("uranium.generator_fuel");
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, Inventory);

        nbt.putInt("uranium.generator_energy", currentEnergy);
        nbt.putInt("uranium.generator_fuel", fuel);
    }

    public void setEnergy(UraniumGeneratorEntity entity){
        try(Transaction transaction = Transaction.openOuter()){
            entity.energyStorage.insert(20, transaction);
            transaction.commit();
        }
        currentEnergy = (int) entity.energyStorage.getAmount();
        NuclearMachines.log(currentEnergy + "");
        markDirty();
    }

    private static void addFuel(UraniumGeneratorEntity entity, int quantity){
        entity.fuel = entity.fuel + quantity;
    }

    private static void removeFuel(UraniumGeneratorEntity entity, int quantity){
        entity.fuel =  entity.fuel - quantity;
    }


    public static void tick(World world, BlockPos pos, BlockState state, UraniumGeneratorEntity entity) {
           
        if(hasRecipe(entity)) {
            if(entity.fuel == entity.MAX_FUEL){ return; }
            addFuel(entity, 1000);
            convertBucket(entity, world);
        }

        if(containsFuel(entity)){
            if( entity.energyStorage.getAmount() == entity.energyStorage.getCapacity() ){
                return;
            }

            try {
                removeFuel(entity, 25);
                entity.setEnergy(entity);
            } catch (Exception e) {
                removeFuel(entity, 25);
                entity.setEnergy(entity);
            }
       
        }
    }
    
    private static boolean containsFuel(UraniumGeneratorEntity entity){
        if(entity.fuel > 0){
            return true;
        } else{
            return false;
        }
    }

    private static void convertBucket(UraniumGeneratorEntity entity, World world) {
        entity.removeStack(0, 1);
        entity.setStack(0, new ItemStack(Items.BUCKET, 1));
    }

    private static boolean hasRecipe(UraniumGeneratorEntity entity) {
        boolean hasItemInFirstSlot = entity.getStack(0).getItem() == registerItem.MOLTEN_URANIUM_BUCKET;
        return hasItemInFirstSlot;
    }

    @Override
    public PropertyDelegate getPropertyDelegate() {
        return propertyDelegate;
    }

}
