package org.typowriter.intellij.plugins.backgroundchibichara;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.editor.EditorFactory;
import org.jetbrains.annotations.NotNull;
import org.typowriter.intellij.plugins.backgroundchibichara.settings.BackgroundChibiCharaSettings;

public class BackgroundChibiCharaPlugin implements ApplicationComponent {
    public static final String COMPONENT_NAME = "BackgroundChibiCharaPlugin";
    private EditorBackgroundListener backgroundListener;
    private BackgroundChibiCharaApplicationSettings applicationSettings;

    public BackgroundChibiCharaPlugin() {
        super();
    }

    @Override
    public void initComponent() {
        applicationSettings = ServiceManager.getService(BackgroundChibiCharaApplicationSettings.class);
        BackgroundChibiCharaSettings settings = applicationSettings.getSettings();
        backgroundListener = new EditorBackgroundListener(settings);
        applicationSettings.addSettingChangeListener(backgroundListener);

        EditorFactory.getInstance().addEditorFactoryListener(backgroundListener, new Disposable() {
            @Override
            public void dispose() {
            }
        });
    }

    @Override
    public void disposeComponent() {
    }

    @NotNull
    @Override
    public String getComponentName() {
        return COMPONENT_NAME;
    }

}
