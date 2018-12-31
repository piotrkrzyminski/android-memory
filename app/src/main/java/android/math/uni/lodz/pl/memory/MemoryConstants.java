package android.math.uni.lodz.pl.memory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Game constants.
 *
 * @author Piotr Krzyminski
 */
public class MemoryConstants {

    public static final int IMAGES_NUMBER = 4;

    /**
     * Ids of ImageViews in GameActivity.
     */
    public static final List<Integer> GAME_IMAGE_VIEW_IDS = new ArrayList<>(Arrays.asList(
            R.id.game_bitmap_01,
            R.id.game_bitmap_02,
            R.id.game_bitmap_03,
            R.id.game_bitmap_04,
            R.id.game_bitmap_05,
            R.id.game_bitmap_06,
            R.id.game_bitmap_07,
            R.id.game_bitmap_08
    ));

    /**
     * Ids of ImageViews in TakePictureActivity.
     */
    public static final List<Integer> TAKE_PICTURE_VIEWS_IDS = new ArrayList<>(Arrays.asList(
            R.id.bitmap_01,
            R.id.bitmap_02,
            R.id.bitmap_03,
            R.id.bitmap_04
    ));

    private MemoryConstants() {}
}
