package com.immortalidiot.wishes.ui;

import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {
    CallbackFragment fragment;

    public void setCallbackFragment(CallbackFragment fragment) { this.fragment = fragment; }
}
