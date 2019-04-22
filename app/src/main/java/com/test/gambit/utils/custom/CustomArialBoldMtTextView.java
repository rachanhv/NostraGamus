package com.test.gambit.utils.custom;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.test.gambit.utils.Constants;

public class CustomArialBoldMtTextView extends AppCompatTextView {
    public CustomArialBoldMtTextView(Context context) {
        super(context);
        init();
    }

    public CustomArialBoldMtTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomArialBoldMtTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), Constants.FontFamilies.ARIAL_BOLDMT);
            setTypeface(tf);
        }
    }
}
