package com.example.examplemod.mc_14_original;

import com.example.examplemod.ExampleMod;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.util.EnumHelper;

public class ItemIceSword extends ItemSword {

    private int count = 0;

    public ItemIceSword() {
        super(EnumHelper.addToolMaterial("ice_sword", 3, 1000, 1.0F, 2.0F, 100));
        setCreativeTab(CreativeTabs.COMBAT);
        setRegistryName("ice_sword");
        setUnlocalizedName(ExampleMod.MODID + "_ice_sword");
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        if (stack == null) {
            return true;
        }
        if (!(attacker instanceof EntityPlayer)) {
            return true;
        }


        if (count == 0) {
            enhanceArmament(target, attacker);
            count++;
        } else if (count == 1) {
            blueRose(target, attacker);
            count++;
        } else if (count == 2) {
            clearEnhance(target, attacker);
            count = 0;
        }

        return true;
    }

    public void enhanceArmament(EntityLivingBase target, EntityLivingBase attacker) {
        Minecraft.getMinecraft().thePlayer.sendChatMessage("エンハンス・アーマメントッ！");

        for (int a = 0; a < 5; a++) {
            for (int b = 0; b < 5; b++) {
                for (int c = 0; c < 5; c++) {
                    BlockPos enemyPos = new BlockPos(target.posX + a, target.posY + b, target.posZ + c);
                    target.worldObj.setBlockState(enemyPos, Blocks.ICE.getDefaultState());

                }
            }
        }
    }


    public void blueRose(EntityLivingBase target, EntityLivingBase attacker) {

        Minecraft.getMinecraft().thePlayer.sendChatMessage("咲け！青薔薇！");

        for (int i = -10; i < 10; i++) {
            for (int j = -10; j < 10; j++) {
                for (int k = -2; k < 4; k++) {
                    BlockPos skillPos = new BlockPos(attacker.posX + i, attacker.posY + k, attacker.posZ + j);
                    attacker.worldObj.setBlockState(skillPos, Blocks.ICE.getDefaultState());
                }
            }
        }

        attacker.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 300, 1));
        attacker.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 1000, 10));

        target.setFire(600);

        attacker.setPosition(attacker.posX, attacker.posY + 10, attacker.posZ);
        target.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 200, 10));
    }

    public void clearEnhance(EntityLivingBase target, EntityLivingBase attacker) {

        Minecraft.getMinecraft().thePlayer.sendChatMessage("エンハンス解除！");

        for (int o = -10; o < 10; o++) {
            for (int p = -10; p < 10; p++) {
                for (int q = -2; q < 4; q++) {

                    BlockPos skillPos = new BlockPos(attacker.posX + o, attacker.posY - q, attacker.posZ + p);
                    attacker.worldObj.setBlockState(skillPos, Blocks.AIR.getDefaultState());
                }
            }
        }
    }
}
