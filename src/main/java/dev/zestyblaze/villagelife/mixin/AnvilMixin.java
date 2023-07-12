package dev.zestyblaze.villagelife.mixin;

import dev.mayaqq.nexusframe.api.multiblock.Multiblock;
import dev.zestyblaze.villagelife.VillageLife;
import dev.zestyblaze.villagelife.multiblocks.TownHallLevel1Multiblock;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AnvilBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AnvilBlock.class)
public class AnvilMixin {
    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void villageLife$use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit, CallbackInfoReturnable<InteractionResult> cir) {
        if (level.isClientSide) {
            cir.setReturnValue(InteractionResult.SUCCESS);
        } else {
            TownHallLevel1Multiblock.getTownHallMultiBlock(pos);
            // get the block below the anvil
            BlockPos blockPos = pos.below();
            if (level.getBlockState(blockPos).is(Blocks.GRASS_BLOCK)) {
                Multiblock forge = TownHallLevel1Multiblock.townHallMultiblock.get(pos);
                if (player.hasPose(Pose.CROUCHING)) {
                    forge.rotate();
                    if (forge.getPreviewed()) {
                        forge.check(pos, level);
                    }
                } else if (forge.check(pos, level)) {
                    VillageLife.LOGGER.info("Completed");
                } else {
                    player.displayClientMessage(Component.translatable("gui.labyrinth.forge.message.negative.forge"), true);
                }
            } else {
                player.openMenu(state.getMenuProvider(level, pos));
            }
            player.awardStat(Stats.INTERACT_WITH_ANVIL);
            cir.setReturnValue(InteractionResult.CONSUME);
        }
    }
}
