package android.math.uni.lodz.pl.memory;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.math.uni.lodz.pl.memory.services.MemoryGameService;
import android.math.uni.lodz.pl.memory.services.impl.DefaultMemoryGameService;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Memory game activity.
 *
 * @author Piotr Krzyminski.
 */
public class GameActivity extends Activity {

    private static final Logger LOG = LoggerFactory.getLogger(GameActivity.class);

    private ImageView firstImageView = null;
    private ImageView secondImageView = null;

    private MemoryGameService memoryGameService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);
        final Bitmap standardBitmap = getStandardBitmap();
        memoryGameService = new DefaultMemoryGameService(getBitmapFromIntent(), standardBitmap);
        LOG.info("Starting game");
    }

    /**
     * Action launched on ImageView click. Checks if selected ImageView is first of second.
     * If two images are uncovered then checks if bitmaps assigned to these components are equals.
     * If all images are uncovered then loads winner activity.
     *
     * @param view clicked ImageView component.
     */
    public void clickImageView(View view) {
        final ImageView imageView = (ImageView) view;
        if (firstImageView == null) {
            firstImageView = imageView;
            memoryGameService.addBitmapToImageView(firstImageView);
        } else if (secondImageView == null) {
            secondImageView = imageView;
            memoryGameService.addBitmapToImageView(secondImageView);

            memoryGameService.checkImages(firstImageView, secondImageView);
            if (memoryGameService.isGameFinished()) {
                LOG.info("Game finished");
                loadWinnerActivity();
            }

            firstImageView = null;
            secondImageView = null;
        }
    }

    private List<Bitmap> getBitmapFromIntent() {
        final Intent intent = getIntent();
        return intent.getParcelableArrayListExtra("IMAGES");
    }

    /**
     * Loads end game screen.
     */
    private void loadWinnerActivity() {
        LOG.info("Loading end game activity");
        final Intent intent = new Intent(this, WinnerActivity.class);
        startActivity(intent);
    }

    private Bitmap getStandardBitmap() {
        final Drawable drawable = getResources().getDrawable(R.drawable.ic_launcher_background);
        if(drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable)drawable).getBitmap();
        }

        final Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }
}
