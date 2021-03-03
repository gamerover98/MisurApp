package it.uniba.magr.misurapp.navigation.settings;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import org.jetbrains.annotations.NotNull;

import java.util.Locale;

import it.uniba.magr.misurapp.HomeActivity;
import it.uniba.magr.misurapp.R;
import it.uniba.magr.misurapp.util.LocaleUtil;

/**
 * The application settings fragment.
 */
public class SettingsFragment extends PreferenceFragmentCompat {

    /**
     * the name of the list_select_language into the application_settings.xml file
     */
    private static final String LIST_SELECT_LANGUAGE = "list_select_language";

    @Override
    public void onCreatePreferences(Bundle bundle, String rootKey) {
        setPreferencesFromResource(R.xml.application_settings, rootKey);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle bundle) {

        ListPreference listSelectLanguage = findPreference(LIST_SELECT_LANGUAGE);
        assert listSelectLanguage != null;

        listSelectLanguage.setOnPreferenceChangeListener(this :: changeLanguageClick);

        assert getContext() != null;

        Context context = getContext();

        if (context instanceof HomeActivity) {

            HomeActivity homeActivity = (HomeActivity) context;
            homeActivity.updateSettingsFragment();

        }

        return super.onCreateView(inflater, container, bundle);

    }

    @Override
    public void onAttach(@NonNull @NotNull Context context) {

        if (context instanceof HomeActivity) {

            HomeActivity homeActivity = (HomeActivity) context;
            homeActivity.updateSettingsFragment();

        }

        super.onAttach(context);

    }

    private boolean changeLanguageClick(Preference preference, Object newValue) {

        Context context = getContext();
        assert context != null;

        Activity activity = (Activity) context;

        String selectedLocaleName = (String) newValue;

        if (selectedLocaleName.equalsIgnoreCase(LocaleUtil.SYSTEM_LANGUAGE)) {
            LocaleUtil.setSystemLocale(context);
        } else {

            Locale locale = LocaleUtil.findLocale(selectedLocaleName);

            if (locale == null) {
                throw new IllegalStateException("Cannot find locale " + selectedLocaleName);
            }

            LocaleUtil.setLocale(activity, locale);

        }

        activity.recreate();
        return true;

    }

}
