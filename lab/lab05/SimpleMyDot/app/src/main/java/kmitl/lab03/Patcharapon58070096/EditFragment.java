package kmitl.lab03.Patcharapon58070096;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class EditFragment extends DialogFragment implements SeekBar.OnSeekBarChangeListener{

    private int dotX;
    private int doty;
    private Color dotColor;
    private int dotRadius;
    private int maxX;
    private int maxY;
    private SeekBar seekBar_x;
    private SeekBar seekBar_y;
    private SeekBar seekBar_radius;
    private TextView label_x;
    private TextView label_y;
    private TextView label_radius;
    private OnEditDotComfirmedListener onEditDotComfirmedListener;

    interface OnEditDotComfirmedListener {
        void onDotEditConfirmed(int x, int y, int radius);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.edit_layout, null);

        seekBar_x = view.findViewById(R.id.edit_x_seekbar);
        seekBar_y = view.findViewById(R.id.ediy_y_seekbar);
        seekBar_radius = view.findViewById(R.id.edit_radius_seekbar);
        label_x = view.findViewById(R.id.edit_x_label);
        label_y = view.findViewById(R.id.edit_y_label);
        label_radius = view.findViewById(R.id.edit_radius_label);

        seekBar_x.setMax(maxX);
        seekBar_x.setProgress(dotX);
        seekBar_y.setMax(maxY);
        seekBar_y.setProgress(doty);
        seekBar_radius.setMax(90);
        seekBar_radius.setProgress(dotRadius-10);
        label_x.setText(String.valueOf(dotX));
        label_y.setText(String.valueOf(doty));
        label_radius.setText(String.valueOf(dotRadius));

        seekBar_x.setOnSeekBarChangeListener(this);
        seekBar_y.setOnSeekBarChangeListener(this);
        seekBar_radius.setOnSeekBarChangeListener(this);

        seekBar_x.refreshDrawableState();
        seekBar_y.refreshDrawableState();
        seekBar_radius.refreshDrawableState();

        return new AlertDialog.Builder(getActivity())
                .setTitle("Edit Dot")
                .setView(view)
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        onEditDotComfirmedListener.onDotEditConfirmed(dotX, doty, dotRadius);
                    }
                })

                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create();
    }

    public int getDotX() {
        return dotX;
    }

    public void setDotX(int dotX) {
        this.dotX = dotX;
    }

    public int getDoty() {
        return doty;
    }

    public void setDoty(int doty) {
        this.doty = doty;

    }

    public Color getDotColor() {
        return dotColor;
    }

    public void setDotColor(Color dotColor) {
        this.dotColor = dotColor;
    }

    public int getDotRadius() {
        return dotRadius;
    }

    public void setDotRadius(int dotRadius) {
        this.dotRadius = dotRadius;
    }

    public int getMaxX() {
        return maxX;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        switch (seekBar.getId()) {
            case R.id.edit_radius_seekbar:
                dotRadius = seekBar.getProgress() + 10;
                label_radius.setText(String.valueOf(dotRadius));
                break;
            case R.id.edit_x_seekbar:
                dotX = seekBar.getProgress();
                label_x.setText(String.valueOf(dotX));
                break;
            case R.id.ediy_y_seekbar:
                doty = seekBar.getProgress();
                label_y.setText(String.valueOf(doty));
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    public OnEditDotComfirmedListener getOnEditDotComfirmedListener() {
        return onEditDotComfirmedListener;
    }

    public void setOnEditDotComfirmedListener(OnEditDotComfirmedListener onEditDotComfirmedListener) {
        this.onEditDotComfirmedListener = onEditDotComfirmedListener;
    }
}
