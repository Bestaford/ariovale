package ru.bestaford.ariovale.util;

import cn.nukkit.Server;
import cn.nukkit.block.BlockID;
import cn.nukkit.level.Level;
import cn.nukkit.level.format.generic.BaseFullChunk;
import cn.nukkit.utils.Config;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.util.Map;
import java.util.Objects;

@SuppressWarnings("deprecation")
@Log4j2
public final class ChunkImporter {

    public static void perform(File folder) {
        Level level = Server.getInstance().getDefaultLevel();
        for (File file : Objects.requireNonNull(folder.listFiles())) {
            String fileName = file.getName()
                    .replace("chunk_", "")
                    .replace(".json", "");
            String[] parts = fileName.split("_");
            int chunkX = Integer.parseInt(parts[0]);
            int chunkZ = Integer.parseInt(parts[1]);
            BaseFullChunk chunk = level.getChunk(chunkX, chunkZ, true);
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    for (int y = level.getMinHeight(); y < level.getMaxHeight(); y++) {
                        chunk.setBlock(x, y, z, BlockID.AIR);
                    }
                }
            }
            Config chunkConfig = new Config(file, Config.JSON);
            for (Map.Entry<String, Object> configEntry : chunkConfig.getAll().entrySet()) {
                String positionString = configEntry.getKey();
                String[] position = positionString.split(":");
                int x = Integer.parseInt(position[0]);
                int y = Integer.parseInt(position[1]);
                int z = Integer.parseInt(position[2]);
                String blockString = String.valueOf(configEntry.getValue());
                String[] block = blockString.split(":");
                int id = Integer.parseInt(block[0]);
                int meta = Integer.parseInt(block[1]);
                if (id == 36) {
                    id = BlockID.AIR;
                    meta = 0;
                }
                if ((id == BlockID.LOG || id == BlockID.WOOD2) && meta >= 12) {
                    meta = meta - 12;
                }
                chunk.setBlock(x, y, z, id, meta);
            }
        }
        level.save();
        log.info("done!");
    }
}