package org.typowriter.intellij.plugins.backgroundchibichara;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.StoragePathMacros;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

@State(name = "BackgroundChibiCharaApplicationBackgroundChibiCharaSettings", storages = @Storage(file = StoragePathMacros.APP_CONFIG + "/backgroundChibiChara.xml"))
public class BackgroundChibiCharaApplicationSettings implements PersistentStateComponent<BackgroundChibiCharaSettings> {
    private static List<SettingChangeListener> listenerList = new ArrayList<SettingChangeListener>();
    private BackgroundChibiCharaSettings mySettings = new BackgroundChibiCharaSettings();

    public static void addSettingChangeListener(SettingChangeListener listener) {
        listenerList.add(listener);
    }

    @Nullable
    @Override
    public BackgroundChibiCharaSettings getState() {
        return mySettings;
    }

    @Override
    public void loadState(BackgroundChibiCharaSettings settings) {
        XmlSerializerUtil.copyBean(settings, mySettings);
    }

    @NotNull
    public BackgroundChibiCharaSettings getBackgroundChibiCharaSettings() {
        return mySettings;
    }

    public BackgroundChibiCharaSettings getSettings() {
        return mySettings;
    }

    public void updateSettings(BackgroundChibiCharaSettings settings) {
        this.mySettings = settings;
        for (SettingChangeListener listener : listenerList) {
            listener.onChange(settings);
        }
    }

    public interface SettingChangeListener {
        void onChange(BackgroundChibiCharaSettings settings);
    }
}
