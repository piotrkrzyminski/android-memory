package android.math.uni.lodz.pl.memory;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Activity for Memory menu.
 *
 * @author Piotr Krzyminski
 */
public class MenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    /**
     * Proceed to new game activity. Before new game starts show dialog that allows to choose
     * number of images in game.
     *
     * @param view new game button.
     */
    public void showGameActivity(View view) {
        Intent intent = new Intent(this, TakePictureActivity.class);
        startActivity(intent);
    }

    /**
     * Shows instructions activity screen.
     *
     * @param view instructions button.
     */
    public void showInstructionActivity(View view) {
        Intent intent = new Intent(this, InstructionsActivity.class);
        startActivity(intent);
    }

    /**
     * Exits application.
     *
     * @param view exit button.
     */
    public void exitApp(View view) {
        finishAndRemoveTask();
    }

}
