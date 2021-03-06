package com.example.examplemod.mc_14_original;

import com.example.examplemod.ExampleMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class ExplosionResistanceBlock extends Block {
    public ExplosionResistanceBlock() {
        super(Material.ROCK);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        setRegistryName("explosion_resistance");
        setUnlocalizedName(ExampleMod.MODID + "_explosion_resistance");
        setResistance(2000F); //爆発耐性
        setHardness(2); //硬さ
        setTickRandomly(false);
        //updateTickがランダムに呼ばれるかを設定する
        //updateTickは燃える判定も入るのでそれを防ぐことができる = 燃えない！！
    }
}
