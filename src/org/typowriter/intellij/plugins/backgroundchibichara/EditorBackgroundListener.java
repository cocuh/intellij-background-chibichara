package org.typowriter.intellij.plugins.backgroundchibichara;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.event.EditorFactoryEvent;
import com.intellij.openapi.editor.event.EditorFactoryListener;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class EditorBackgroundListener implements EditorFactoryListener {
    private final int spacing;
    private final int margin;
    private final java.util.List<Image> imageList;
    private Editor editor;
    private float alpha;
    private JComponent editorComponent;

    public EditorBackgroundListener(java.util.List<Image> imageList, float alpha, int spacing, int margin) {
        this.imageList = imageList;
        this.alpha = alpha;
        this.spacing = spacing;
        this.margin = margin;
    }

    @Override
    public void editorCreated(EditorFactoryEvent event) {
        editor = event.getEditor();
        editorComponent = editor.getContentComponent();
        refreshBackground();
    }

    private Border getBackground() throws IOException {
        Collections.shuffle(imageList);
        return new BackgroundImageBorder(new ArrayList<Image>(imageList), alpha, spacing, BackgroundImageBorder.Align.RIGHT, margin);
    }

    private void refreshBackground() {
        if (editorComponent != null) {
            try {
                editorComponent.setBorder(getBackground());
            } catch (Exception e) {
                Notifications.Bus.notify(new Notification(
                        "BackgroundChibiChara",
                        e.toString(), "error loading background image",
                        NotificationType.ERROR
                ));
            }
        }
    }

    public void setOpacity(float alpha) {
        this.alpha = alpha;
        refreshBackground();
    }

    private void debug(String content) {
        Notifications.Bus.notify(new Notification("youjo", "youjo", content, NotificationType.ERROR));
    }

    @Override
    public void editorReleased(EditorFactoryEvent event) {
        event.getEditor().getContentComponent().setBorder(null);
    }
}
