
package com.example.wearapp;

import android.os.Bundle;
import android.app.Activity;
import android.support.constraint.solver.widgets.Helper;
import android.support.wearable.view.DelayedConfirmationView;
import android.view.View;



public class delete_activity extends Activity implements DelayedConfirmationView.DelayedConfirmationListener {
    DelayedConfirmationView delayedConfirmationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_activity);
        delayedConfirmationView=(DelayedConfirmationView)findViewById(R.id.delete_confirm);
        delayedConfirmationView.setListener(this);
        delayedConfirmationView.setTotalTimeMs(3000);
        delayedConfirmationView.start();
    }

    @Override
    public void onTimerFinished(View v) {
        Helper.displayConfirmation("Deleted",this);
        String id=getIntent().getStringExtra("id");
        Helper.removeNote(id,this);
        finish();

    }

    @Override
    public void onTimerSelected(View v) {
        Helper.displayConfirmation("Cancelled",this);
        displayConfirmationView.reset();
        finish();

    }
}
