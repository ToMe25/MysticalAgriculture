package com.blakebr0.mysticalagriculture.items;

import java.util.List;

import javax.annotation.Nullable;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.config.ModConfig;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionAbsorption;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemInferiumApple extends ItemFood {
	
	public ItemInferiumApple(String name){
        super(6, 0.3F, false);
		this.setUnlocalizedName("ma." + name);
		this.setRegistryName(name);
		this.setCreativeTab(MysticalAgriculture.tabMysticalAgriculture);
		this.setAlwaysEdible();
	}
	
    @Override
    protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player){
        if(!world.isRemote){
        	int duration = 0;
            PotionEffect potion;
            int bufflength = ModConfig.confAppleBuffDuration * 20;
            
            potion = player.getActivePotionEffect(MobEffects.ABSORPTION);
            if(potion != null)
            	duration = potion.getDuration();
            player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, duration + 60 * bufflength, 1));
        }
    }
    
    @Override
    public boolean hasEffect(ItemStack stack){
    	return true;
    }
    
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World world, List tooltip, ITooltipFlag advanced){
		tooltip.add("Gives Buffs:");
		int duration = ModConfig.confAppleBuffDuration;
		tooltip.add("- \u00A7eAbsorption II \u00A77(" + duration + ":00)");
	}
}
