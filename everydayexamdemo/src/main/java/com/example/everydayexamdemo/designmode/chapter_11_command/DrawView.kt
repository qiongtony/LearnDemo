package com.example.everydayexamdemo.designmode.chapter_11_command

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.view.View
import java.util.*

class DrawView
@JvmOverloads
constructor( context: Context,
             attrs: AttributeSet? = null,
             defStyleAttr: Int = 0): View(context, attrs, defStyleAttr){

    // 绘制命令请求对象
    private val drawInvoker : DrawInvoker = DrawInvoker()
    var isDrawing : Boolean = false

    init {
        val valueAnimator = ValueAnimator.ofInt(0, 100)
        valueAnimator.repeatCount = ValueAnimator.INFINITE
        valueAnimator.duration = 10 * 1000L
        valueAnimator.addUpdateListener(object : ValueAnimator.AnimatorUpdateListener{
            override fun onAnimationUpdate(animation: ValueAnimator?) {
                if (isDrawing) {
                    invalidate()
                }
            }
        })
        valueAnimator.start()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(context.resources.displayMetrics.widthPixels, context.resources.displayMetrics.heightPixels)
    }

    fun add(drawPath: DrawPath){
        drawInvoker.add(drawPath)
    }

    // 重做上一步撤销的绘制
    fun redo(){
        isDrawing = true
        drawInvoker.redo()
    }

    fun undo(){
        isDrawing = true
        drawInvoker.undo()
    }

    fun canUndo() = drawInvoker.canUndo()

    fun canRedo() = drawInvoker.canRedo()



    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?:return
        canvas.save()
        drawInvoker.execute(canvas)
        canvas.restore()
    }
}

/**
 * 笔触
 */
interface IBrush{
    fun down(path: Path, x : Float, y : Float)

    fun move(path : Path, x : Float, y : Float)

    fun up(path: Path, x : Float, y: Float)
}

/**
 * 直线笔触
 */
class NormalBrush : IBrush{
    override fun down(path: Path, x: Float, y: Float) {
        Log.e("WWS", "down x = $x y = $y")
        path.moveTo(x, y)
    }

    override fun move(path: Path, x: Float, y: Float) {
        Log.e("WWS", "move x = $x y = $y")
        path.lineTo(x, y)
    }

    override fun up(path: Path, x: Float, y: Float) {

    }
}

/**
 * 圆形笔触
 */
class CircleBrush : IBrush{
    override fun down(path: Path, x: Float, y: Float) {

    }

    override fun move(path: Path, x: Float, y: Float) {
        path.addCircle(x, y, 10f, Path.Direction.CW)
    }

    override fun up(path: Path, x: Float, y: Float) {

    }
}

/**
 * 绘制命令：绘制、撤销
 */
interface IDraw {
    fun draw(canvas: Canvas)

    fun undo()
}

/**
 * 接收者（具体命令的实际执行者）
 */
class DrawPath(val path: Path, val paint: Paint) : IDraw{
    override fun draw(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }

    override fun undo() {

    }
}

class DrawInvoker{
    // 绘制列表
    private val drawList = Collections.synchronizedList(arrayListOf<DrawPath>())
    // 重做列表
    private val redoList = Collections.synchronizedList(arrayListOf<DrawPath>())

    /**
     * 添加一个命令
     */
    fun add(command : DrawPath){
        redoList.clear()
        drawList.add(command)
    }

    /**
     * 撤销上一步的命令
     */
    fun undo(){
        if (drawList.isNotEmpty()){
            val last = drawList.last()
            drawList.removeAt(drawList.size - 1)
            last.undo()
            redoList.add(last)
        }
    }

    /**
     * 重做上一步撤销的命令
     */
    fun redo(){
        if (redoList.isNotEmpty()){
            val last = redoList.last()
            redoList.removeAt(redoList.size - 1)
            drawList.add(last)
        }
    }

    fun execute(canvas: Canvas){
        if (drawList.isNotEmpty()){
            for (drawPath in drawList){
                drawPath.draw(canvas)
            }
        }
    }

    fun canRedo() = redoList.isNotEmpty()

    fun canUndo() = drawList.isNotEmpty()
}
