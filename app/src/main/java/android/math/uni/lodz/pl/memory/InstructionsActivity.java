package android.math.uni.lodz.pl.memory;

import android.app.Activity;
import android.os.Bundle;

/**
 * Game instructions activity.
 *
 * @author Piotr Krzyminski.
 */
public class InstructionsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);
    }
}
