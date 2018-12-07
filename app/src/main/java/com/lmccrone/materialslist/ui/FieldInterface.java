package com.lmccrone.materialslist.ui;

import android.view.View;
import android.widget.ArrayAdapter;

public interface FieldInterface<T> {

    public void fireSelectionUpdate(boolean fieldSelected);

    public void notifyListChanged();

    public ArrayAdapter<T> getListAdapter();

    public int getCurrentSelectionPos();

    public View getCurrentSelectionView();
}
