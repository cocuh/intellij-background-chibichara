package org.typowriter.intellij.plugins.backgroundchibichara;
import java.util.ArrayList;
import java.util.List;

public class BackgroundChibiCharaSettings {
    public List<String> filepathList;
    public BackgroundImageBorder.Align align;
    public int margin;
    public int spacing;
    public double alpha;

    public BackgroundChibiCharaSettings(List<String> filepathList, BackgroundImageBorder.Align align, int margin, int spacing, double alpha) {
        this.filepathList = filepathList;
        this.align = align;
        this.margin = margin;
        this.spacing = spacing;
        this.alpha = alpha;
    }

    BackgroundChibiCharaSettings() {
        this.filepathList = new ArrayList<String>();
        this.align = BackgroundImageBorder.Align.RIGHT;
        this.margin = 50;
        this.spacing = 300;
        this.alpha = 0.15;
    }
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BackgroundChibiCharaSettings)) {
            return false;
        }
        BackgroundChibiCharaSettings otherSettings = (BackgroundChibiCharaSettings) obj;
        return filepathList.equals(otherSettings.filepathList)
                & align == otherSettings.align
                & margin == otherSettings.margin
                & spacing == otherSettings.spacing
                & alpha == otherSettings.alpha;
    }
}