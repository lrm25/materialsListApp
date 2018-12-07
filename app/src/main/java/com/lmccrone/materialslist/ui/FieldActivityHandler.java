package com.lmccrone.materialslist.ui;

import android.view.View;

import com.lmccrone.materialslist.R;
import com.lmccrone.materialslist.app.FieldManager;
import com.lmccrone.materialslist.bus.Item;

public class FieldActivityHandler<T> {

    private FieldManager<T> fieldManager;
    private FieldSelectHandler<T> fieldSelectHandler;
    private FieldInterface fieldInterface;

    private class FieldSelectHandler<T> {

        private T selectedField = null;
        private FieldInterface fieldInterface = null;

        public FieldSelectHandler() {
            this.fieldInterface = fieldInterface;
        }

        public T getSelectedField() {
            return selectedField;
        }

        public void setSelectedField(T t) {
            selectedField = t;
            boolean fieldSelected = (null == t) ? false : true;
            fieldInterface.fireSelectionUpdate(fieldSelected);
        }
    }

    /*private void deleteField(T t) {

        fieldManager.delete(t);
        fieldInterface.notifyListChanged();
        if (!fieldInterface.getListAdapter().isEmpty()) {
            setSelectedItem(0 < currentSelectionPos ? --currentSelectionPos : 0);

        } else {
            fieldSelectHandler.setSelectedItem(null);
            currentSelectionView = null;
        }
    }

    private void setSelectedItem(final View v, final int position) {

        View currentSelectionView = fieldInterface.getCurrentSelectionView();
        if (null != currentSelectionView) {
            currentSelectionView.setSelected(false);
            currentSelectionView.setBackgroundColor(getResources().getColor(R.color.white));
        }
        fieldSelectHandler.setSelectedField(fieldInterface.getListAdapter().getItem(position));
        currentSelectionView = v;
        currentSelectionView.setSelected(true);
        currentSelectionView.setBackgroundColor(getResources().getColor(
                R.color.lightBlue));
        currentSelectionPos = position;
    }*/
}
