package org.typowriter.intellij.plugins.backgroundchibichara;

import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.editor.EditorFactory;
import org.jetbrains.annotations.NotNull;

public class BackgroundChibiCharaPlugin implements ApplicationComponent {
    private BackgroundChibiCharaConfiguration theConfiguration;
    private BackgroundChibiCharaConfigurationPanel userInterface;
    private EditorBackgroundListener backgroundListener;

    public BackgroundChibiCharaPlugin(){
        super();
    }

    @Override
    public void initComponent() {
        Application application = ApplicationManager.getApplication();
//        theConfiguration =(BackgroundChibiCharaConfiguration) application.getComponent(BackgroundChibiCharaConfiguration.class);

        backgroundListener = new EditorBackgroundListener(
                "/home/cocu/picture/wallpaper/wo-class2-.png"
        );

//        if(theConfiguration.enabled){
            EditorFactory.getInstance().addEditorFactoryListener(backgroundListener); // fixme: duplicated method
//        }
    }

    @Override
    public void disposeComponent() {
    }


    @NotNull
    @Override
    public String getComponentName() {
        return "BackgroundChibiCharaPlugin";
    }
}
