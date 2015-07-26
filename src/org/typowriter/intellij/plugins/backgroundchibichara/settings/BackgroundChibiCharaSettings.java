package org.typowriter.intellij.plugins.backgroundchibichara.settings;

import org.jetbrains.annotations.NotNull;
import org.typowriter.intellij.plugins.backgroundchibichara.BackgroundImageBorder;

import java.util.ArrayList;
import java.util.List;

public class BackgroundChibiCharaSettings {
    public static final BackgroundChibiCharaSettings DEFAULT = new BackgroundChibiCharaSettings();
    public List<String> filepathList;
    public int alignId;
    public int margin;
    public int spacing;
    public double alpha;

    public BackgroundChibiCharaSettings(List<String> filepathList, BackgroundImageBorder.Align align, int margin, int spacing, double alpha) {
        this.filepathList = filepathList;
        this.alignId = align.id;
        this.margin = margin;
        this.spacing = spacing;
        this.alpha = alpha;
    }

    public BackgroundChibiCharaSettings() {
        this.filepathList = new ArrayList<String>();
        this.alignId = BackgroundImageBorder.Align.RIGHT.id;
        this.margin = 50;
        this.spacing = 300;
        this.alpha = 0.15;
    }

    public BackgroundImageBorder.Align getAlign() {
        return BackgroundImageBorder.Align.getById(this.alignId);
    }

    public List<String> getFilepathList() {
        return new ArrayList<String>(filepathList);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BackgroundChibiCharaSettings)) {
            return false;
        }
        BackgroundChibiCharaSettings otherSettings = (BackgroundChibiCharaSettings) obj;
        return filepathList.equals(otherSettings.filepathList)
                & alignId == otherSettings.alignId
                & margin == otherSettings.margin
                & spacing == otherSettings.spacing
                & alpha == otherSettings.alpha;
    }

    public interface Holder {
        @NotNull
        BackgroundChibiCharaSettings getSettings();

        void setSettings(@NotNull BackgroundChibiCharaSettings settings);
    }
}