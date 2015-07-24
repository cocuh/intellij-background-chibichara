package org.typowriter.intellij.plugins.backgroundchibichara.settings;

import org.jetbrains.annotations.NotNull;
import org.typowriter.intellij.plugins.backgroundchibichara.BackgroundImageBorder;

import java.util.List;

public class BackgroundChibiCharaSettings {
    public static final BackgroundChibiCharaSettings DEFAULT = new BackgroundChibiCharaSettings();
    List<String> filepathList;
    int alignId;
    int margin;
    int spacing;
    double alpha;

    public BackgroundChibiCharaSettings(List<String> filepathList, BackgroundImageBorder.Align align, int margin, int spacing, double alpha) {
        this.filepathList = filepathList;
        this.alignId = align.id;
        this.margin = margin;
        this.spacing = spacing;
        this.alpha = alpha;
    }

    public BackgroundChibiCharaSettings() {
        this.alignId = BackgroundImageBorder.Align.RIGHT.id;
        this.margin = 10;
        this.spacing = 30;
        this.alpha = 0.3;
    }

    public BackgroundImageBorder.Align getAlign() {
        return BackgroundImageBorder.Align.getById(this.alignId);
    }

    public interface Holder {
        @NotNull
        BackgroundChibiCharaSettings getSettings();

        void setSettings(@NotNull BackgroundChibiCharaSettings settings);
    }
}