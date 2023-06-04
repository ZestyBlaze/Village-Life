package dev.zestyblaze.villagelife.entity;

import dev.zestyblaze.villagelife.mixin.VillagerMixin;
import eu.pb4.polymer.core.api.entity.PolymerEntity;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.npc.VillagerData;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.level.Level;

import java.util.List;

public class SmartVillagerMob extends PathfinderMob implements PolymerEntity {
    public SmartVillagerMob(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0d)
                .add(Attributes.MOVEMENT_SPEED, 0.3d)
                .add(Attributes.ATTACK_DAMAGE, 1.0d);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1.0d, false));
        this.goalSelector.addGoal(1, new WaterAvoidingRandomStrollGoal(this, 1.0d));
        this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, Animal.class, false));
    }

    @Override
    public EntityType<?> getPolymerEntityType(ServerPlayer player) {
        return EntityType.VILLAGER;
    }

    @Override
    public void modifyRawTrackedData(List<SynchedEntityData.DataValue<?>> data, ServerPlayer player, boolean initial) {
        data.add(SynchedEntityData.DataValue.create(VillagerMixin.getVillagerData(),
                new VillagerData(VillagerType.JUNGLE, VillagerProfession.BUTCHER, 5))
        );
    }
}
