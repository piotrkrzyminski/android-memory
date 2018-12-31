package android.math.uni.lodz.pl.memory.services;

import android.widget.ImageView;

/**
 * Interface for game service.
 *
 * @author Piotr Krzyminski
 */
public interface MemoryGameService {

    /**
     * Checks if images assigned to two ImageViews from parameter are equals.
     *
     * @param firstImageView  first selected ImageView.
     * @param secondImageView second selected ImageView.
     */
    void checkImages(final ImageView firstImageView, final ImageView secondImageView);

    /**
     * Checks if user uncovered all bitmaps pairs.
     *
     * @return true if user uncovered all pairs or false if not.
     */
    boolean isGameFinished();

    /**
     * Resolves bitmap assigned to ImageView from parameter and displays it.
     *
     * @param imageView ImageView whose bitmap should be displayed.
     */
    void addBitmapToImageView(final ImageView imageView);
}
