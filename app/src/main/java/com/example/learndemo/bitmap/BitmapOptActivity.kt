package com.example.learndemo.bitmap

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.learndemo.*
import com.example.learndemo.databinding.ActivityBitmapOptBinding
import kotlin.concurrent.thread

/**
 * 位图像素格式Config：
 * ARGB_8888：RGBA各占8位，一个像素4字节，默认值
 * ARGB_4444：同上各占4位，一个像素4字节
 * RGB_565：RGB分别占565位，一个像素2字节
 * ALPHA_8：只含透明度占8位，1字节
 */
class BitmapOptActivity : BaseActivity<ActivityBitmapOptBinding>() {
    val resIds = arrayOf(R.drawable.girl, R.drawable.beauty)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnShowPhoto.setOnClickListener {
            showPhoto()
        }

        binding.btnShowAssetsPhoto.setOnClickListener {
            showAssetsPhoto()
        }

        binding.btnConfig.setOnClickListener {
            showConfigPhoto()
        }

        binding.btnScale.setOnClickListener {
            showInsampleSizePhoto()
        }

        binding.btnUnMutable.setOnClickListener {
            showUnMutablePhoto()
        }

        binding.btnMutable.setOnClickListener {
            showMutablePhoto()
        }

        binding.btnLargePhoto.setOnClickListener {
            startActivity(Intent(this, LargeBitmapActivity::class.java))
        }

        /**
         * 报错：
         * android.os.TransactionTooLargeException: data parcel size 35070348 bytes
         * 报错位置：BinderProxy.transactNative
         */
        binding.btnSendBitmap.setOnClickListener {
            val intent = Intent(this, ReceiveBitmapActivity::class.java)
            intent.putExtra("bitmap", BitmapFactory.decodeResource(this@BitmapOptActivity.resources, R.drawable.girl))
            startActivity(intent)
        }

        binding.btnSendBitmapByPutBinder.setOnClickListener {
            val intent = Intent(this, ReceiveBitmapActivity::class.java)
            intent.putExtras(Bundle()
                    .apply {

            putBinder("binder", object : IRemoteGetBitmap.Stub() {
                override fun getBitmap(): Bitmap {
                    return BitmapFactory.decodeResource(this@BitmapOptActivity.resources, R.drawable.girl)
                }
            })})
            startActivity(intent)
        }
    }

    fun showPhoto(){
        thread {
            val bitmap = BitmapFactory.decodeResource(resources, R.drawable.girl)
            /**
             *
             * width = 2835
             * height = 2368
             * allocationByteCount = 26853120
             * byteCount = 26853120
             * rowBytes = 11340
             * density = 420
             * mutable = false
             * width = originalWidth * 系统的density / 所在目录的density 即为 1080 * 420 / 160
             * allocationByteCount = width * height * 位图像素格式（默认为ARGB_8888：一个像素占4字节）
             * 结论：图片应放到对应文件夹下，放到低分辨率的文件夹会造成图片的内存占用升高，且放到越高的文件夹，适配性是越高的
             *
             */
            Log.i("WWS",
                    " width = ${bitmap.width}\n" +
                    "height = ${bitmap.height}\n" +
                    " allocationByteCount = ${bitmap.allocationByteCount}\n"
                    + " byteCount = ${bitmap.byteCount}\n" +
                    " rowBytes = ${bitmap.rowBytes}\n" +
                    " density = ${bitmap.density}\n" +
                    " mutable = ${bitmap.isMutable}\n"
            )
            Handler(Looper.getMainLooper()).post {
                binding.ivGirl.setImageBitmap(bitmap)
            }
        }.start()
    }

    fun showAssetsPhoto(){
//        thread {
            val inputStream = assets.open("girl.jpg")
            val bitmap = BitmapFactory.decodeStream(inputStream)
            /**
             *
             * width = 1080
             * height = 902
             * allocationByteCount = 3896640
             * byteCount = 3896640
             * rowBytes = 4320
             * density = 420
             * mutable = false
             * 结论：Assets目录下的图片不会进行缩放，是原始的宽高
             *
             */
            Log.i("WWS",
                    " width = ${bitmap.width}\n" +
                            "height = ${bitmap.height}\n" +
                            " allocationByteCount = ${bitmap.allocationByteCount}\n"
                            + " byteCount = ${bitmap.byteCount}\n" +
                            " rowBytes = ${bitmap.rowBytes}\n" +
                            " density = ${bitmap.density}\n" +
                            " mutable = ${bitmap.isMutable}\n"
            )
            Handler(Looper.getMainLooper()).post {
                binding.ivGirl.setImageBitmap(bitmap)
            }
//        }.start()
    }

    fun showConfigPhoto(){
//        thread {
            val options = BitmapFactory.Options()
            options.inPreferredConfig = Bitmap.Config.RGB_565
            val bitmap = BitmapFactory.decodeResource(resources, R.drawable.girl, options)
            /**
             *
             * width = 2835
             * height = 2368
             * allocationByteCount = 26853120变为13426560
             * byteCount = 26853120变为：13426560
             * rowBytes = 11340变为：5670
             * density = 420
             * mutable = false
             * width = originalWidth * 系统的density / 所在目录的density 即为 1080 * 420 / 160
             * 结论：修改图片的像素位图格式可以降低内存
             *
             */
            Log.i("WWS",
                    " width = ${bitmap.width}\n" +
                            "height = ${bitmap.height}\n" +
                            " allocationByteCount = ${bitmap.allocationByteCount}\n"
                            + " byteCount = ${bitmap.byteCount}\n" +
                            " rowBytes = ${bitmap.rowBytes}\n" +
                            " density = ${bitmap.density}\n" +
                            " mutable = ${bitmap.isMutable}\n"
            )
            Handler(Looper.getMainLooper()).post {
                binding.ivGirl.setImageBitmap(bitmap)
            }
//        }.start()
    }

    fun showInsampleSizePhoto(){
//        thread {
        val options = BitmapFactory.Options()
        options.inSampleSize = 2
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.girl, options)
        /**
         *
         * width = 2835 缩小为1418
         * height = 2368 缩小为1184
         * allocationByteCount = 26853120变为6715648
         * byteCount = 26853120变为：6715648
         * rowBytes = 11340变为：5672
         * density = 420
         * mutable = false
         * 结论：修改Options.inSampleSize可以达到优化图片占用内存的目的，效果为inSmapleSize的平方
         *
         */
        Log.i("WWS",
                " width = ${bitmap.width}\n" +
                        "height = ${bitmap.height}\n" +
                        " allocationByteCount = ${bitmap.allocationByteCount}\n"
                        + " byteCount = ${bitmap.byteCount}\n" +
                        " rowBytes = ${bitmap.rowBytes}\n" +
                        " density = ${bitmap.density}\n" +
                        " mutable = ${bitmap.isMutable}\n"
        )
        Handler(Looper.getMainLooper()).post {
            binding.ivGirl.setImageBitmap(bitmap)
        }
//        }.start()
    }

    var number = 0;

    fun showUnMutablePhoto(){
        number++;
        val options = BitmapFactory.Options()
        val bitmap = BitmapFactory.decodeResource(resources, resIds[number % 2], options)
        /**
         *
         * width = 2835 缩小为1418
         * height = 2368 缩小为1184
         * allocationByteCount = 26853120变为6715648
         * byteCount = 26853120变为：6715648
         * rowBytes = 11340变为：5672
         * density = 420
         * mutable = false
         * 结论：修改Options.inSampleSize可以达到优化图片占用内存的目的，效果为inSmapleSize的平方
         *
         */
        Log.i("WWS",
                " width = ${bitmap.width}\n" +
                        "height = ${bitmap.height}\n" +
                        " allocationByteCount = ${bitmap.allocationByteCount}\n"
                        + " byteCount = ${bitmap.byteCount}\n" +
                        " rowBytes = ${bitmap.rowBytes}\n" +
                        " density = ${bitmap.density}\n" +
                        " mutable = ${bitmap.isMutable}\n"
        )
        Handler(Looper.getMainLooper()).post {
            binding.ivGirl.setImageBitmap(bitmap)
        }
    }
    var bitmap : Bitmap? = null

    fun showMutablePhoto(){
        val options = BitmapFactory.Options()
        number++
        // 第二张图要比第一张图大，也就是说先加载出来复用的图要比后面加载的图大,很好理解:复用的内存肯定是取大者
        options.inMutable = true
        if (bitmap != null){
            // 设为不加载内存是想用复用加载的bitmap的配置,与复用的bitmap进行比较能不能复用
            options.inJustDecodeBounds = true
            BitmapFactory.decodeResource(resources, resIds[number % 2], options)
            if (canUseForBitmap(bitmap!!, options)) {
                options.inBitmap = bitmap
            }
            options.inJustDecodeBounds = false
        }
        bitmap = BitmapFactory.decodeResource(resources, resIds[number % 2], options)
        /**
         *
         * allocationByteCount = 55036800(这个取的是当前bitmap占的内存,因为复用,所以用的是大的)
         * byteCount = 55036800或为26853120
         * density = 420
         * mutable = false
         * 结论：修改Options.inSampleSize可以达到优化图片占用内存的目的，效果为inSmapleSize的平方
         *
         */
        bitmap?.apply {
            Log.i("WWS",
                    " width = ${width}\n" +
                            "height = ${height}\n" +
                            " allocationByteCount = ${allocationByteCount}\n"
                            + " byteCount = ${byteCount}\n" +
                            " rowBytes = ${rowBytes}\n" +
                            " density = ${density}\n" +
                            " mutable = ${isMutable}\n"
            )
        }
        Handler(Looper.getMainLooper()).post {
            binding.ivGirl.setImageBitmap(bitmap)
        }
    }

    // 是否可以复用:判断标准,复用的bitmap占的内存>=要加载的bitmap所占的内存
    fun canUseForBitmap(reuseBitmap : Bitmap, options : BitmapFactory.Options) : Boolean{
        val targetWidth = options.outWidth / Math.max(options.inSampleSize, 1)
        val targetHeight = options.outHeight / Math.max(options.inSampleSize, 1)
        // 4代表的是ALPHA_8888,一个像素占四个字节
        return targetWidth * targetHeight * 4 <= reuseBitmap.allocationByteCount
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_bitmap_opt
    }
}