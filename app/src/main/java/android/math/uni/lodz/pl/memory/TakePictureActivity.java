package android.math.uni.lodz.pl.memory;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity that launch camera app to make bitmap images used in game.
 *
 * @author Piotr Krzyminski.
 */
public class TakePictureActivity extends Activity {

    private static final Logger LOG = LoggerFactory.getLogger(TakePictureActivity.class);

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static List<Bitmap> bitmaps = new ArrayList<>(MemoryConstants.IMAGES_NUMBER);
    private int imageIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_picture);

        for(int i = 0; i < MemoryConstants.IMAGES_NUMBER; i++) {
            dispatchTakePictureIntent();
        }

        LOG.info("All picture was captured");
    }

    /**
     * Process bitmap from camera app and assign in to ImageView.
     *
     * @param requestCode request code.
     * @param resultCode result code.
     * @param data data taken by camera.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        final ImageView imageView = findViewById(MemoryConstants.TAKE_PICTURE_VIEWS_IDS.get(imageIndex));
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            LOG.info("Picture taken successfully");
            final Bundle extras = data.getExtras();
            final Bitmap imageBitmap = (Bitmap) extras.get("data");
            bitmaps.add(imageBitmap);
            imageView.setImageBitmap(imageBitmap);
            imageIndex++;
        }
    }

    /**
     * Start game activity.
     */
    public void startGame(View view) {
        LOG.info("Starting game activity");
        final Intent intent = new Intent(this, GameActivity.class);
        intent.putParcelableArrayListExtra("IMAGES", new ArrayList<>(bitmaps));
        startActivity(intent);
    }

    /**
     * Starts camera activity.
     */
    private void dispatchTakePictureIntent() {
        final Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
}