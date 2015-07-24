package org.typowriter.intellij.plugins.backgroundchibichara;

import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.editor.EditorFactory;
import org.jetbrains.annotations.NotNull;
import org.typowriter.intellij.plugins.backgroundchibichara.settings.BackgroundChibiCharaConfigurable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BackgroundChibiCharaPlugin implements ApplicationComponent {
    public static final String COMPONENT_NAME = "BackgroundChibiCharaPlugin";
    BackgroundChibiCharaConfigurable theConfiguration;
    private EditorBackgroundListener backgroundListener;

    public BackgroundChibiCharaPlugin() {
        super();
    }

    @Override
    public void initComponent() {
        Application application = ApplicationManager.getApplication();
        theConfiguration = application.getComponent(BackgroundChibiCharaConfigurable.class);

        List<Image> images = getImages();
        backgroundListener = new EditorBackgroundListener(
                images,
                0.3,
                30,
                30
        );

        EditorFactory.getInstance().addEditorFactoryListener(backgroundListener); // fixme: duplicated method
    }

    @Override
    public void disposeComponent() {
    }

    public List<Image> getImages() {
        ArrayList<Image> images = new ArrayList<Image>();
        try {
            images.add(ImageIO.read(new File("/home/cocu/Dropbox/etc/icons/chibit_stern/chibit_stern.png")));
            images.add(ImageIO.read(new File("/home/cocu/picture/wallpaper/wo-class2-.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return images;
    }


    @NotNull
    @Override
    public String getComponentName() {
        return COMPONENT_NAME;
    }
}
