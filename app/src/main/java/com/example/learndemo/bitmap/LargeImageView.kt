package com.example.learndemo.bitmap

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import com.example.learndemo.R
import java.io.InputStream
import java.lang.Exception

/**
 * BitmapRegionDecoder来加载大图：
 * 每次只加载设置的矩形框为止的图形
 *
 * 处理步骤：
 * 1、创建BitmapRegionDecoder实例（通过Byte流，或是InputStream将图片传进来）
 * 2、通过BitmapFactory。Options.InJustDecodeBounds获取图片的尺寸；
 * 3、进来一些操作后更新要展示图片的rect范围（注意对超过范围的情况进行调整）
 * 3、调用bitmapRegionDecoder.decodeRegion(rect, options)来获取部分图片的bitmap；
 * 4、将bitmap展示出来
 */
class LargeImageView : View, GestureDetector.OnGestureListener{
    private var bitmapRegionDecoder : BitmapRegionDecoder? = null
    @Volatile
    private var rect : Rect = Rect()

    private val gestureDetector : GestureDetector = GestureDetector(context, this)

    private var imageWidth : Int = 0
    private var imageHeight : Int = 0
    private val options = BitmapFactory.Options()

    public var inputStream : InputStream? = null
    set(value) {
        try {

            val options = BitmapFactory.Options()
            options.inJustDecodeBounds = true
            BitmapFactory.decodeStream(value, null, options)
            imageWidth = options.outWidth
            imageHeight = options.outHeight
            options.inJustDecodeBounds = true
            bitmapRegionDecoder = BitmapRegionDecoder.newInstance(value, false)
            requestLayout()
            invalidate()
        }catch (e : Exception){
            Log.e("WWS", "e = ${e.toString()}")
        }
    }

    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init(attrs, defStyle)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        // Load attributes
        val a = context.obtainStyledAttributes(
                attrs, R.styleable.LargeImageView, defStyle, 0)

        a.recycle()


    }

    private fun checkHeight(){
        val imageHeight = this.imageHeight
        if (rect.bottom > imageHeight){
            rect.bottom = imageHeight
            rect.top = rect.bottom - height
        }

        if (rect.top < 0){
            rect.top = 0
            rect.bottom = height
        }
    }

    private fun checkWidth(){
        val rect = this.rect
        val imageWidth = this.imageWidth


        if (rect.right > imageWidth){
            rect.right = imageWidth
            rect.left = imageWidth - width
        }

        if (rect.left < 0){
            rect.left = 0
            rect.right = width
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        rect.left = imageWidth / 2 - measuredWidth / 2
        rect.top = imageHeight / 2 - measuredHeight  / 2
        rect.right = rect.left + measuredWidth
        rect.bottom =  rect.top + measuredHeight
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return gestureDetector.onTouchEvent(event)
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (bitmapRegionDecoder != null) {
            val bitmap = bitmapRegionDecoder!!.decodeRegion(rect, options)
            canvas.drawBitmap(bitmap, 0f, 0f, null)
        }
    }

    override fun onShowPress(e: MotionEvent?) {

    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        return false
    }

    override fun onDown(e: MotionEvent?): Boolean {
        return true
    }

    override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
        return false
    }

    override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
        if (imageWidth > width || imageHeight > height){
            if (imageWidth > width) {
                rect.offset(distanceX.toInt(), 0)
                checkWidth()
            }
            if (imageHeight > height) {
                rect.offset(0, distanceY.toInt())
                checkHeight();
            }
            invalidate()
        }

        return true
    }

    override fun onLongPress(e: MotionEvent?) {

    }
}
