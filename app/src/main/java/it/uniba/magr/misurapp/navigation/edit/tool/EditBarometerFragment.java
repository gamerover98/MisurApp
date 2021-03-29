package it.uniba.magr.misurapp.navigation.edit.tool;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.google.android.material.textfield.TextInputEditText;

import org.jetbrains.annotations.NotNull;

import it.uniba.magr.misurapp.R;
import it.uniba.magr.misurapp.navigation.edit.EditMeasureFragment;
import it.uniba.magr.misurapp.tool.ruler.RulerNavigation;

public class EditBarometerFragment extends EditMeasureFragment {

    public EditBarometerFragment() {
        super(() -> R.layout.fragment_save_ruler);
    }

    @Override
    protected void handleParametersCreation(@NotNull FragmentActivity activity, @NotNull Bundle bundle) {

        TextInputEditText lengthEditText = activity.findViewById(R.id.save_ruler_input_text_length);
        float length = bundle.getFloat(RulerNavigation.BUNDLE_LENGTH_KEY);

        String lengthText = length + " cm";
        lengthEditText.setText(lengthText);

    }

}