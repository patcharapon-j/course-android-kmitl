package kmitl.lab03.Patcharapon58070096.model;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by patcharaponjoksamut on 11/9/2017 AD.
 */

public class DotColor {
    private ArrayList<Integer> colorList = new ArrayList<>();

    public DotColor() {
        colorList.add(Color.RED);
        colorList.add(Color.BLUE);
        colorList.add(Color.GREEN);
        colorList.add(Color.BLACK);
        colorList.add(Color.MAGENTA);
        colorList.add(Color.YELLOW);
        colorList.add(Color.GRAY);
    }

    public int getRandomColor() {
        return colorList.get(new Random().nextInt(colorList.size()));
    }
}
