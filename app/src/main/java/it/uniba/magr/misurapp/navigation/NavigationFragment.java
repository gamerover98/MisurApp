package it.uniba.magr.misurapp.navigation;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

import lombok.Getter;

public class NavigationFragment extends Fragment {

    /**
     * Gets the interface that manage the fragment view
     * and its behaviour.
     */
    @Getter @NotNull
    private final Navigable navigable;

    public NavigationFragment(@NotNull Navigable navigable) {
        this.navigable = navigable;
    }

    @Override
    public void onAttach(@NotNull Context context) {

        super.onAttach(context);
        navigable.onAttach(context);

    }

    @Override
    public void onCreate(@Nullable Bundle bundle) {

        super.onCreate(bundle);
        navigable.onCreate(bundle);

    }

    @Nullable
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater,
                             @Nullable ViewGroup parent,
                             @Nullable Bundle bundle) {

        if (parent == null) {
            throw new IllegalArgumentException("The parent argument cannot be null");
        }

        Context context = parent.getContext();
        navigable.onCreateView(context, bundle);

        int layoutID = navigable.getLayoutId();
        return inflater.inflate(layoutID, parent, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle bundle) {

        super.onActivityCreated(bundle);

        Activity activity = getActivity();
        assert activity != null;

        navigable.onActivityCreated(activity, bundle);

    }

    @Override
    public void onStart() {

        super.onStart();
        navigable.onStart();

    }

    @Override
    public void onResume() {

        super.onResume();
        navigable.onResume();

    }

    @Override
    public void onPause() {

        super.onPause();
        navigable.onPause();

    }

    @Override
    public void onStop() {

        super.onStop();
        navigable.onStop();

    }

    @Override
    public void onDestroyView() {

        super.onDestroyView();
        navigable.onDestroyView();

    }

    @Override
    public void onDestroy() {

        super.onDestroy();
        navigable.onDestroy();

    }

    @Override
    public void onDetach() {

        super.onDetach();
        navigable.onDetach();

    }

}