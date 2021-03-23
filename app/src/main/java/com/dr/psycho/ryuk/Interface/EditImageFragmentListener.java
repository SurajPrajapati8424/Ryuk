package com.dr.psycho.ryuk.Interface;

public interface EditImageFragmentListener {

    void  onBrightnessChanges(int brightness);
    void onContrastChanged(float contrast);
    void onSaturationChanged(float saturation);
    void onEditStarted();
    void onEditCompleted();

}