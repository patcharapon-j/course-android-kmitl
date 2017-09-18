package kmitl.lab03.Patcharapon58070096.model;

import java.util.ArrayList;

/**
 * Created by patcharaponjoksamut on 11/9/2017 AD.
 */

public class Dots {

    public interface OnDotChangeListener {
        void onDotsChanged(Dots dots);
    }

    private OnDotChangeListener listener;

    private ArrayList<Dot> allDots = new ArrayList<>();

    public void setListener(OnDotChangeListener listener) {
        this.listener = listener;
    }

    public ArrayList<Dot> getAllDots() {
        return allDots;
    }

    public void addDot(Dot dot) {
        this.allDots.add(dot);
        this.listener.onDotsChanged(this);
    }

    public void removeDot(Dot dot) {
        this.allDots.remove(dot);
        this.listener.onDotsChanged(this);
    }

    public void undoDot() {
        this.allDots.remove(this.allDots.size() - 1);
        this.listener.onDotsChanged(this);
    }

    public void clearDot() {
        this.allDots.clear();
        this.listener.onDotsChanged(this);
    }


}
