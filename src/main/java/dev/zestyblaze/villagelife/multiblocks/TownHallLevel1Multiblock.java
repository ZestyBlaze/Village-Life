package dev.zestyblaze.villagelife.multiblocks;

import com.google.common.base.Predicates;
import dev.mayaqq.nexusframe.api.multiblock.Multiblock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;
import net.minecraft.world.level.block.state.properties.Half;

import java.util.HashMap;
import java.util.function.Predicate;

public class TownHallLevel1Multiblock {
    public static char[][][] COMPLETED_TOWN_HALL_1;
    public static final HashMap<BlockPos, Multiblock> townHallMultiblock = new HashMap<>();

    public static char[][][] getTownHall() {
        if (COMPLETED_TOWN_HALL_1 == null) {
            COMPLETED_TOWN_HALL_1 = new char[][][] {
                    {{'a', 'a', 'a', 'a', 'a', 'c', 's', 's', 's', 's', 'c', 'a', 'a', 'a', 'a', 'a'}, {'$'}}
            };
        }
        return COMPLETED_TOWN_HALL_1;
    }

    public static HashMap<Character, Predicate<BlockState>> getPredicates() {
        HashMap<Character, Predicate<BlockState>> predicates = new HashMap<>();
        predicates.put('a', BlockStatePredicate.forBlock(Blocks.AIR));
        predicates.put('c', BlockStatePredicate.forBlock(Blocks.COBBLESTONE));
        //predicates.put('s', BlockStatePredicate.forBlock(Blocks.COBBLESTONE_STAIRS).where(StairBlock.FACING, Predicates.equalTo(Direction.EAST)).where(StairBlock.HALF, Predicates.equalTo(Half.BOTTOM)));
        predicates.put('s', BlockStatePredicate.forBlock(Blocks.COBBLESTONE_STAIRS));
        predicates.put('$', BlockStatePredicate.forBlock(Blocks.ANVIL));
        return predicates;
    }

    public static void getTownHallMultiBlock(BlockPos pos) {
        if (townHallMultiblock.get(pos) == null) {
            townHallMultiblock.put(pos, new Multiblock(getTownHall(), getPredicates(), true, false));
        }
    }
}
