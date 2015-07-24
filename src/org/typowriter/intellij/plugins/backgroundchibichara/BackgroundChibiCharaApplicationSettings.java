package org.typowriter.intellij.plugins.backgroundchibichara;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.StoragePathMacros;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.typowriter.intellij.plugins.backgroundchibichara.settings.BackgroundChibiCharaSettings;

import java.util.ArrayList;
import java.util.List;

@State(name = "BackgroundChibiCharaApplicationSettings", storages = @Storage(file = StoragePathMacros.APP_CONFIG + "/backgroundChibiChara.xml"))
public class BackgroundChibiCharaApplicationSettings implements PersistentStateComponent<BackgroundChibiCharaApplicationSettings.State>,
        BackgroundChibiCharaSettings.Holder {
    private static List<SettingChangeListener> listenerList = new ArrayList<SettingChangeListener>();
    private State myState = new State();

    public static void addSettingChangeListener(SettingChangeListener listener) {
        listenerList.add(listener);
    }

    @Nullable
    @Override
    public State getState() {
        return myState;
    }

    @Override
    public void loadState(State state) {
        XmlSerializerUtil.copyBean(state, myState);
    }

    @NotNull
    @Override
    public BackgroundChibiCharaSettings getSettings() {
        return myState.mySettings;
    }

    @Override
    public void setSettings(@NotNull BackgroundChibiCharaSettings settings) {
        myState.mySettings = settings;
        for (SettingChangeListener listener : listenerList) {
            listener.onChange(settings);
        }
    }

    public interface SettingChangeListener {
        void onChange(BackgroundChibiCharaSettings settings);
    }

    public static class State {
        public BackgroundChibiCharaSettings mySettings = BackgroundChibiCharaSettings.DEFAULT;
    }
}
