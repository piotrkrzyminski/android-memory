package android.math.uni.lodz.pl.memory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Winner activity.
 *
 * @author Piotr Krzyminski
 */
public class WinnerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);
    }

    /**
     * Start menu menu activity.
     */
    public void backToMenuAction(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}
