package org.typowriter.intellij.plugins.backgroundchibichara;

import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.editor.EditorFactory;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BackgroundChibiCharaPlugin implements ApplicationComponent {
    private BackgroundChibiCharaConfiguration theConfiguration;
    private BackgroundChibiCharaConfigurationPanel userInterface;
    private EditorBackgroundListener backgroundListener;

    public BackgroundChibiCharaPlugin() {
        super();
    }

    @Override
    public void initComponent() {
        Application application = ApplicationManager.getApplication();
//        theConfiguration =(BackgroundChibiCharaConfiguration) application.getComponent(BackgroundChibiCharaConfiguration.class);
        List<Image> images = null;
        try {
            images = getImages("");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (images != null) {
            backgroundListener = new EditorBackgroundListener(
                    images,
                    0.3f,
                    30,
                    30
            );
        }

//        if(theConfiguration.enabled){
        EditorFactory.getInstance().addEditorFactoryListener(backgroundListener); // fixme: duplicated method
//        }
    }

    @Override
    public void disposeComponent() {
    }

    public List<Image> getImages(String direcotryPath) throws IOException {
        ArrayList<Image> images = new ArrayList<Image>();
        images.add(ImageIO.read(new File("/home/cocu/Dropbox/etc/icons/chibit_stern/chibit_stern.png")));
        images.add(ImageIO.read(new File("/home/cocu/picture/wallpaper/wo-class2-.png")));
        return images;
    }


    @NotNull
    @Override
    public String getComponentName() {
        return "BackgroundChibiCharaPlugin";
    }
}
