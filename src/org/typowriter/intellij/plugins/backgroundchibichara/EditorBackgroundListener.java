package org.typowriter.intellij.plugins.backgroundchibichara;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.event.EditorFactoryEvent;
import com.intellij.openapi.editor.event.EditorFactoryListener;

import javax.imageio.ImageIO;
import javax.swing.border.Border;
import java.io.File;
import java.io.IOException;

public class EditorBackgroundListener implements EditorFactoryListener {
    private final String filepath;

    public EditorBackgroundListener(String filepath) {
        this.filepath = filepath;
    }

    @Override
    public void editorCreated(EditorFactoryEvent event) {
        Editor editor = event.getEditor();
        try{
            editor.getContentComponent().setBorder(getBackground());
        }catch (Exception e){
            Notifications.Bus.notify(new Notification(
                    "BackgroundChibiChara",
                    e.toString(), "error loading background image",
                    NotificationType.ERROR
            ));
        }
    }

    private Border getBackground() throws IOException {
        return new BackgroundBorder(0.3f, ImageIO.read(new File(filepath)));
    }

    @Override
    public void editorReleased(EditorFactoryEvent event) {
        event.getEditor().getContentComponent().setBorder(null);
    }
}
