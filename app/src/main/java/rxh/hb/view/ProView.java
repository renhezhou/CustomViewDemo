package rxh.hb.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import rxh.hb.activity.R;

/**
 * Created by Administrator on 2016-05-23.
 */
public class ProView extends View {

    private int startx, starty, endx, endy, lineendx;
    private int pro_width, pro_height, pro_color;

    //程序内实例化时采用，传入Context即可
    public ProView(Context context) {
        super(context);
    }

    //用于layout文件实例化，会把XML内的参数通过AttributeSet带入到View内
    public ProView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    //主题的style信息，也会从XML里带入
    public ProView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray t = context.obtainStyledAttributes(attrs,
                R.styleable.pro, 0, 0);
        pro_width = t.getDimensionPixelSize(R.styleable.pro_pro_width, 100);
        pro_height = t.getDimensionPixelOffset(R.styleable.pro_pro_height, 16);
        pro_color = t.getColor(R.styleable.pro_pro_color, Color.WHITE);
        t.recycle();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec),
                getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec));
    }

    public static int getDefaultSize(int size, int measureSpec) {
        int result = size;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:
                result = size;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.EXACTLY:
                result = specSize;
                break;
        }
        return result;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        startx = left;
        starty = top;
        endx = right;
        endy = bottom;
        lineendx = getLeft();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(pro_color);
        paint.setStrokeWidth(pro_height);

//        canvas.drawLine(startx, (starty + endy) / 2, endx / 7, (starty + endy) / 2, paint);
//        canvas.drawLine(endx/7*2, (starty + endy) / 2, endx / 7*3, (starty + endy) / 2, paint);
//        canvas.drawLine(endx/7*4, (starty + endy) / 2, endx/7*5, (starty + endy) / 2, paint);
//        canvas.drawLine(endx/7*6, (starty + endy) / 2, endx, (starty + endy) / 2, paint);
        if (lineendx - pro_width < getRight()) {
            canvas.drawLine(lineendx - pro_width, (starty + endy) / 2, lineendx, (starty + endy) / 2, paint);
            lineendx++;
        } else {
            lineendx = getLeft();
        }
        invalidate();

    }
}
