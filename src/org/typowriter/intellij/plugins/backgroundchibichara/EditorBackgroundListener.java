package org.typowriter.intellij.plugins.backgroundchibichara;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.event.EditorFactoryEvent;
import com.intellij.openapi.editor.event.EditorFactoryListener;
import org.typowriter.intellij.plugins.backgroundchibichara.settings.BackgroundChibiCharaSettings;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EditorBackgroundListener implements EditorFactoryListener,
        BackgroundChibiCharaApplicationSettings.SettingChangeListener {
    private int spacing;
    private int margin;
    private java.util.List<Image> imageList;
    private BackgroundImageBorder.Align align;
    private Editor editor;
    private double alpha;
    private JComponent editorComponent;

    public EditorBackgroundListener(BackgroundChibiCharaSettings settings) {
        setParams(settings);
    }

    private static List<Image> getImages(List<String> filepathList) {
        ArrayList<Image> images = new ArrayList<Image>();
        for (String filepath : filepathList) {
            try {
                images.add(ImageIO.read(new File(filepath)));
            } catch (IOException e) {
                Notifications.Bus.notify(new Notification("BakckgroundChibiChara", "FileOpenError", e.toString(), NotificationType.ERROR));
            }
        }
        return images;
    }

    private void setParams(BackgroundChibiCharaSettings settings) {
        this.imageList = getImages(settings.getFilepathList());
        this.align = settings.getAlign();
        this.alpha = settings.getAlpha();
        this.spacing = settings.getSpacing();
        this.margin = settings.getMargin();
    }

    @Override
    public void editorCreated(EditorFactoryEvent event) {
        editor = event.getEditor();
        editorComponent = editor.getContentComponent();
        refreshBackgroundImageBorder();
    }

    private Border getBackgroundImageBorder() throws IOException {
        Collections.shuffle(imageList);
        return new BackgroundImageBorder(new ArrayList<Image>(imageList), align, spacing, margin, alpha);
    }

    public void refreshBackgroundImageBorder() {
        if (editorComponent != null) {
            try {
                editorComponent.setBorder(getBackgroundImageBorder());
            } catch (Exception e) {
                Notifications.Bus.notify(new Notification(
                        "BackgroundChibiChara",
                        e.toString(), "error loading background image",
                        NotificationType.ERROR
                ));
            }
        }
    }

    @Override
    public void editorReleased(EditorFactoryEvent event) {
        event.getEditor().getContentComponent().setBorder(null);
    }

    @Override
    public void onChange(BackgroundChibiCharaSettings settings) {
        setParams(settings);
        refreshBackgroundImageBorder();
    }
}
