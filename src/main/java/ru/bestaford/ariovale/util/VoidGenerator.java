package ru.bestaford.ariovale.util;

import cn.nukkit.level.ChunkManager;
import cn.nukkit.level.generator.Generator;
import cn.nukkit.math.NukkitRandom;
import cn.nukkit.math.Vector3;

import java.util.Collections;
import java.util.Map;

public final class VoidGenerator extends Generator {

    public final Map<String, Object> options;
    public ChunkManager chunkManager;

    public VoidGenerator() {
        this(Collections.emptyMap());
    }

    public VoidGenerator(Map<String, Object> options) {
        this.options = options;
    }

    @Override
    public int getId() {
        return TYPE_INFINITE;
    }

    @Override
    public ChunkManager getChunkManager() {
        return chunkManager;
    }

    @Override
    public void init(ChunkManager chunkManager, NukkitRandom random) {
        this.chunkManager = chunkManager;
    }

    @Override
    public void generateChunk(int chunkX, int chunkZ) {

    }

    @Override
    public void populateChunk(int chunkX, int chunkZ) {

    }

    @Override
    public Map<String, Object> getSettings() {
        return options;
    }

    @Override
    public String getName() {
        return "void";
    }

    @Override
    public Vector3 getSpawn() {
        return new Vector3(0.5, 256, 0.5);
    }
}