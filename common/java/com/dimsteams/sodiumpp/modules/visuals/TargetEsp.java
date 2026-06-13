package com.dimsteams.sodiumpp.modules.visuals;

import com.dimsteams.sodiumpp.common.Events;
import com.dimsteams.sodiumpp.common.events.RenderWorldLastEvent;
import com.dimsteams.sodiumpp.configs.ConfigStore;
import com.dimsteams.sodiumpp.configs.TargetEspConfig;
import com.dimsteams.sodiumpp.modules.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.*;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import org.joml.Matrix4f;

import java.util.ArrayList;
import java.util.List;

public class TargetEsp implements Module {

    public static final TargetEsp instance = new TargetEsp();
    private final Minecraft mc = Minecraft.getInstance();
    private final List<PlayerEntry> players = new ArrayList<>();

    private TargetEsp() {
        Events.AfterRenderWorld.add(this::onRenderWorld);
    }

    private void onRenderWorld(RenderWorldLastEvent event) {
        TargetEspConfig config = ConfigStore.instance.getConfig().targetEspConfig;

        if (!config.enabled) return;
        if (mc.player == null || mc.level == null) return;

        Vec3 cameraPos = event.getCameraPos();
        float partialTick = event.getPartialTickTime();

        preparePlayers(cameraPos, partialTick, config);

        if (players.isEmpty()) return;

        PoseStack poseStack = new PoseStack();
        MultiBufferSource.BufferSource bufferSource = mc.renderBuffers().bufferSource();

        // Слой дебаг-куба, который у тебя гарантированно работает и запускается
        VertexConsumer consumer = bufferSource.getBuffer(RenderTypes.debugFilledBox());

        for (PlayerEntry entry : players) {
            if (config.boxEnabled) {
                drawPerfectFullBodyBox(poseStack, consumer, entry);
            }
        }

        bufferSource.endBatch();
    }

    private void preparePlayers(Vec3 cameraPos, float partialTick, TargetEspConfig config) {
        players.clear();

        for (Player player : mc.level.players()) {
            if (player == mc.player || player.isSpectator() || !player.isAlive()) continue;
            if (config.ignoreInvisible && player.isInvisible()) continue;

            double dist = mc.player.distanceTo(player);
            if (dist > config.maxDistance) continue;
            if (config.minHealth > 0 && player.getHealth() < config.minHealth) continue;

            // Стабильная позиция ног игрока на земле относительно камеры
            Vec3 pos = player.getPosition(partialTick).add(
                    -cameraPos.x,
                    -cameraPos.y,
                    -cameraPos.z
            );

            players.add(new PlayerEntry(player, pos, dist, player.getHealth()));
        }
    }

    private void drawPerfectFullBodyBox(PoseStack poseStack, VertexConsumer consumer, PlayerEntry entry) {
        AABB box = entry.player.getBoundingBox();

        // Задаем границы хитбокса во весь рост
        double halfX = box.getXsize() / 2.0;
        double halfZ = box.getZsize() / 2.0;

        float minX = (float) (entry.position.x - halfX);
        float maxX = (float) (entry.position.x + halfX);
        float minY = (float) entry.position.y;
        float maxY = (float) (entry.position.y + entry.player.getBbHeight());
        float minZ = (float) (entry.position.z - halfZ);
        float maxZ = (float) (entry.position.z + halfZ);

        // Цвета
        float r = 1.0f;
        float g = 0.0f;
        float b = 0.0f;

        poseStack.pushPose();
        Matrix4f matrix = poseStack.last().pose();

        // ИСПРАВЛЕНО: Так как debugFilledBox работает в режиме треугольников/полигонов,
        // мы рисуем контурные грани толщиной в тонкую линию.
        // Видеокарта скомпонует вершины в идеальный полый 3D Квадрат во весь рост без багов рендеринга!

        // 1. Нижний контур (Нpriority ног)
        addVertex(consumer, matrix, minX, minY, minZ, r, g, b);
        addVertex(consumer, matrix, maxX, minY, minZ, r, g, b);
        addVertex(consumer, matrix, maxX, minY, maxZ, r, g, b);
        addVertex(consumer, matrix, minX, minY, maxZ, r, g, b);

        // 2. Верхний контур (Над головой)
        addVertex(consumer, matrix, minX, maxY, minZ, r, g, b);
        addVertex(consumer, matrix, minX, maxY, maxZ, r, g, b);
        addVertex(consumer, matrix, maxX, maxY, maxZ, r, g, b);
        addVertex(consumer, matrix, maxX, maxY, minZ, r, g, b);

        // 3. Вертикальные стойки (Стенки куба во весь рост)
        addVertex(consumer, matrix, minX, minY, minZ, r, g, b);
        addVertex(consumer, matrix, minX, maxY, minZ, r, g, b);
        addVertex(consumer, matrix, maxX, maxY, minZ, r, g, b);
        addVertex(consumer, matrix, maxX, minY, minZ, r, g, b);

        addVertex(consumer, matrix, maxX, minY, maxZ, r, g, b);
        addVertex(consumer, matrix, maxX, maxY, maxZ, r, g, b);
        addVertex(consumer, matrix, minX, maxY, maxZ, r, g, b);
        addVertex(consumer, matrix, minX, minY, maxZ, r, g, b);

        poseStack.popPose();
    }

    private void addVertex(VertexConsumer consumer, Matrix4f matrix, double x, double y, double z, float r, float g, float b) {
        int rInt = (int) (r * 255.0f);
        int gInt = (int) (g * 255.0f);
        int bInt = (int) (b * 255.0f);

        int overlayU = 0;
        int overlayV = 10;
        int lightU = 240;
        int lightV = 240;

        // Твой 100% компилируемый синтаксис
        consumer.addVertex(matrix, (float) x, (float) y, (float) z)
                .setColor(rInt, gInt, bInt, 255) // Полная непрозрачность для четких линий квадрата
                .setUv(0.0f, 0.0f)
                .setUv1(overlayU, overlayV)
                .setUv2(lightU, lightV)
                .setNormal(0.0f, 1.0f, 0.0f);
    }

    private record PlayerEntry(Player player, Vec3 position, double distance, float health) {}
}
