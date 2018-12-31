package android.math.uni.lodz.pl.memory.services.impl;

import android.graphics.Bitmap;
import android.math.uni.lodz.pl.memory.MemoryConstants;
import android.math.uni.lodz.pl.memory.services.MemoryGameService;
import android.os.Handler;
import android.widget.ImageView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

/**
 * Service for memory game. It checks if two selected image views has the same images.
 *
 * @author Piotr Krzyminski
 */
public class DefaultMemoryGameService implements MemoryGameService {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultMemoryGameService.class);

    /**
     * List of bitmaps. Each image is stored two times.
     */
    private List<Bitmap> bitmaps;

    /**
     * Standard bitmap displayed when image is covered.
     */
    private Bitmap standardBitmap;

    /**
     * Stores number of uncovered bitmaps pairs.
     */
    private int uncoveredPairs;

    /**
     * Sets data required for game.
     *
     * @param bitmaps        bitmaps captured by camera.
     * @param standardBitmap standard bitmap for covered images.
     */
    public DefaultMemoryGameService(List<Bitmap> bitmaps, Bitmap standardBitmap) {
        this.standardBitmap = standardBitmap;
        uncoveredPairs = 0;

        prepareBitmaps(bitmaps);
    }

    @Override
    public void checkImages(final ImageView firstImageView, final ImageView secondImageView) {
        if (isImageEquals(firstImageView, secondImageView)) {
            LOG.info("Selected images are the same");
            uncoveredPairs++;
        } else {
            LOG.info("Selected images are different");
            HideImages hideImages = new HideImages(firstImageView, secondImageView, standardBitmap);
            new Handler().postDelayed(hideImages, 1000);
        }
    }

    @Override
    public boolean isGameFinished() {
        return (uncoveredPairs == MemoryConstants.IMAGES_NUMBER);
    }

    /**
     * Assign and show bitmap assigned to ImageView.
     *
     * @param imageView ImageView component.
     */
    @Override
    public void addBitmapToImageView(final ImageView imageView) {
        imageView.setImageBitmap(getBitmapForImageView(imageView));
    }

    private static class HideImages implements Runnable {
        private final ImageView firstImageView;
        private final ImageView secondImageView;
        private final Bitmap standardBitmap;

        public HideImages(ImageView firstImageView, ImageView secondImageView, Bitmap standardBitmap) {
            this.firstImageView = firstImageView;
            this.secondImageView = secondImageView;
            this.standardBitmap = standardBitmap;
        }

        @Override
        public void run() {
            firstImageView.setImageBitmap(standardBitmap);
            secondImageView.setImageBitmap(standardBitmap);
        }
    }

    /**
     * Get captured images and add two of each types to list. Shuffle it for random indexes.
     *
     * @param bitmaps bitmaps captured by camera.
     */
    private void prepareBitmaps(List<Bitmap> bitmaps) {
        bitmaps.addAll(bitmaps);
        Collections.shuffle(bitmaps);

        this.bitmaps = bitmaps;
    }

    /**
     * Checks if bitmaps assigned to selected ImageView are equal
     *
     * @return true if images is the same otherwise false.
     */
    private boolean isImageEquals(final ImageView firstImageView, final ImageView secondImageView) {
        final Bitmap firstBitmap = getBitmapForImageView(firstImageView);
        final Bitmap secondBitmap = getBitmapForImageView(secondImageView);

        return firstBitmap.equals(secondBitmap);
    }

    /**
     * Get id of ImageView component and return bitmap assigned to this index from array.
     *
     * @param imageView ImageView component.
     * @return bitmap assigned to ImageView.
     */
    private Bitmap getBitmapForImageView(final ImageView imageView) {
        final int imageViewIndex = MemoryConstants.GAME_IMAGE_VIEW_IDS.indexOf(imageView.getId());
        return bitmaps.get(imageViewIndex);
    }
}
