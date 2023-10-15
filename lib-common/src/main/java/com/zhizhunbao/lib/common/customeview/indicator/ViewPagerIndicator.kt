package com.zhizhunbao.lib.common.customeview.indicator

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.LinearLayout
import androidx.viewpager2.widget.ViewPager2
import com.zhizhunbao.lib.common.R
import com.zhizhunbao.lib.common.ext.safe
import com.zhizhunbao.lib.common.tool.color
import com.zhizhunbao.lib.common.tool.sp

/**
 * 自定义指示器
 */
class ViewPagerIndicator : LinearLayout {
    private var mViewPager: ViewPager2? = null

    private var parentWidth = 0
    private var parentHeight = 0
    private var visibleItemCount = 3
    private var itemCount = 3
    private var maxCount = "99+"

    //绘制框框
    private var paint: Paint? = null
    private var unCheckPaint: Paint? = null
    private var checkPaint: Paint? = null
    private var badgePaint = Paint()
    private var badgeBackPaint = Paint()
    private var mWidth = 0f
    private var mHeight = 0f
    private var mLeft = 0f
    private var mTop = 0f
    private var radiusX = 100f
    private var radiusY = 100f
    private var mPadding = 8

    private var mDatas: List<String>? = null
    private var mBadgeNumList = HashMap<Int, Int>()
    private var isSetData = false
    private var currentPosition = 0
    private var isAutoSelect = false //判断是否进行切换

    private var rebounceOffset = 0f

    private var mContext: Context? = null

    //点击的区域
    private var clickIndex = -1

    //按下时间
    private var mDownTime = 0L

    /** 所有子item **/
    private var mRectPath = mutableListOf<MutableList<Int>>()

    constructor(context: Context) : super(context) {
        mContext = context
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        mContext = context
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        mContext = context
    }

    init {
        setBackgroundResource(R.drawable.indicator_round_bg)
        paint = Paint()
        paint?.style = Paint.Style.FILL
        paint?.color = R.color.app_white.color
        paint?.isAntiAlias = true
        unCheckPaint = Paint()
        unCheckPaint?.textSize = 18.sp
        unCheckPaint?.color = R.color.app_white.color
        unCheckPaint?.isAntiAlias = true
        unCheckPaint?.strokeWidth = 8f
        unCheckPaint?.textAlign = Paint.Align.CENTER
        checkPaint = Paint()
        checkPaint?.color = R.color.app_colorPrimary.color
        checkPaint?.isAntiAlias = true
        checkPaint?.textSize = 18.sp
        checkPaint?.strokeWidth = 8f
        checkPaint?.textAlign = Paint.Align.CENTER
        badgeBackPaint.color = R.color.red.color
        badgeBackPaint.isAntiAlias = true
        badgePaint.color = R.color.app_white.color
        badgePaint.isAntiAlias = true
        badgePaint.textSize = 8.sp
        badgePaint.strokeWidth = 4f
        badgePaint.textAlign = Paint.Align.CENTER
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        parentWidth = measuredWidth
        parentHeight = measuredHeight
        mWidth = (parentWidth / visibleItemCount).toFloat()
        mHeight = parentHeight.toFloat()

        if (mDatas.isNullOrEmpty() && isSetData)
            return
        isSetData = false

        mRectPath.clear()
        for (i in mDatas!!.indices) {
            val rect =
                mutableListOf(i * mWidth.toInt(), 0, (i + 1) * mWidth.toInt(), mHeight.toInt())
            mRectPath.add(rect)
        }
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawRoundRect(
            mLeft + mPadding,
            mTop + mPadding,
            mLeft + mWidth - mPadding,
            mTop + mHeight - mPadding,
            radiusX,
            radiusY,
            paint!!
        )
        for (i in mDatas!!.indices) {
            val startX = i * mWidth + (mWidth / 2)
            val fontMetrics: Paint.FontMetrics = checkPaint!!.fontMetrics
            val distance = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom
            val baseline: Float = mHeight / 2 + distance

            val badgeFontMetrics: Paint.FontMetrics = badgePaint.fontMetrics
            val badgeDistance =
                (badgeFontMetrics.bottom - badgeFontMetrics.top) / 2 - badgeFontMetrics.bottom


            if (i == currentPosition) {
                canvas.drawText(mDatas!![i], startX, baseline, checkPaint!!)
                if (mBadgeNumList.containsKey(i) && mBadgeNumList[i].safe() > 0) {
                    val countText =
                        if (mBadgeNumList[i].safe() > 99) maxCount else mBadgeNumList[i].toString()
                    val badgeBaseline = mHeight / 2 + badgeDistance
                    val badgeWidth = badgePaint.measureText(countText)
                    val badgeStart = startX + checkPaint!!.measureText(mDatas!![i]) / 2 + badgeWidth
                    canvas.drawCircle(badgeStart + 12, mHeight / 2 + 2, badgeWidth / 2 + 12, badgeBackPaint)
                    canvas.drawText(
                        countText,
                        badgeStart + 12,
                        badgeBaseline,
                        badgePaint
                    )
                }
            } else {
                canvas.drawText(mDatas!![i], startX, baseline, unCheckPaint!!)
                if (mBadgeNumList.containsKey(i) && mBadgeNumList[i].safe() > 0) {
                    val countText =
                        if (mBadgeNumList[i].safe() > 99) maxCount else mBadgeNumList[i].toString()
                    val badgeBaseline = mHeight / 2 + badgeDistance
                    val badgeWidth = badgePaint.measureText(countText)
                    val badgeStart =
                        startX + unCheckPaint!!.measureText(mDatas!![i]) / 2 + badgeWidth
                    canvas.drawCircle(badgeStart + 12, mHeight / 2 + 2, badgeWidth / 2 + 12, badgeBackPaint)
                    canvas.drawText(
                        countText,
                        badgeStart + 12,
                        badgeBaseline,
                        badgePaint
                    )
                }
            }
        }
    }

    /**
     * 设置未读红点
     */
    fun setBadgeData(num: Int, index: Int) {
        mBadgeNumList[index] = num
        invalidate()
    }

    fun setViewPager(viewpager: ViewPager2, position: Int) {
        mViewPager = viewpager
        currentPosition = position
        viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                //当移动的是最左边item
                if (isAutoSelect && currentPosition == 0) {
                    //滑动手松开时，让最左边（即第一个）item滑动到左边缘位置
                    mLeft = if (positionOffset > rebounceOffset / 2) {
                        (position + (positionOffset - rebounceOffset / 2) * 2) * mWidth
                    } else if (positionOffset > rebounceOffset / 3 && positionOffset < rebounceOffset / 2) {
                        //让最左边（即第一个）item 向右回弹一部分距离
                        (position + (rebounceOffset / 2) - positionOffset) * mWidth * 6 / 12
                    } else {
                        //让最左边（即最后一个）item 向左回弹到边缘位置
                        (position + positionOffset) * mWidth * 6 / 12
                    }
                    invalidate()
                } else if (isAutoSelect && currentPosition == itemCount - 1) {
                    //当移动的是最右边（即最后一个）item

                    //滑动手松开时，让最右边（即最后一个）item滑动到右边缘位置
                    if (positionOffset >= rebounceOffset && positionOffset < (1 - (1 - rebounceOffset) / 2)) {
                        //
                        mLeft =
                            (position + positionOffset / (1 - (1 - rebounceOffset) / 2)) * mWidth
                        //当item数大于visibleItem可见数，本控件(本质LinearLayout)才滚动
                        if (visibleItemCount < itemCount) {
                            val scrollX =
                                (mWidth * positionOffset / (1 - (1 - rebounceOffset) / 2) + (position - visibleItemCount + 1) * mWidth).toInt()
                            scrollTo(scrollX, 0)
                        }
                        if ((mLeft + mWidth) > (childCount * mWidth)) {
                            //当(mLeft + mWidth)大于最边缘的宽度时，设置
                            mLeft = (itemCount - 1) * mWidth
                        }
                    } else if (positionOffset > (1 - (1 - rebounceOffset) / 2) && positionOffset < (1 - (1 - rebounceOffset) / 4)) {
                        //让最右边（即最后一个）item 向左回弹一部分距离

                        //当item数大于visibleItem可见数，且本控件未滚动到指定位置，则设置控件滚动到指定位置
                        if (visibleItemCount < itemCount && scrollX.toFloat() != (itemCount - visibleItemCount) * mWidth) {
                            val scrollX = ((itemCount - visibleItemCount) * mWidth).toInt()
                            scrollTo(scrollX, 0)
                        }
                        mLeft =
                            (position + 1) * mWidth - (positionOffset - (1 - (1 - rebounceOffset) / 2)) * mWidth * 7 / 12
                    } else {
                        //让最右边（即最后一个）item 向右回弹到边缘位置

                        //因为onPageScrolled 最后positionOffset会变成0，所以这里需要判断一下
                        //当positionOffset = 0 时，设置mLeft位置
                        if (positionOffset != 0f) {
                            mLeft =
                                (position + 1) * mWidth - (1.0f - positionOffset) * mWidth * 7 / 12
                            if (mLeft > (itemCount - 1) * mWidth) {
                                mLeft = (itemCount - 1) * mWidth
                            }
                        } else {
                            mLeft = (itemCount - 1) * mWidth
                        }

                    }
                    invalidate()
                } else {
                    //当移动的是中间item
                    scrollTo(position, positionOffset)
                    rebounceOffset = positionOffset
                }
                setTitleColor()
            }

            override fun onPageSelected(position: Int) {
                currentPosition = position
            }

            override fun onPageScrollStateChanged(state: Int) {
                if (state == 2) {
                    //当state = 2时，表示手松开，viewpager开启自动滑动
                    isAutoSelect = true
                }
                if (state == 0) {
                    //当state = 0时，表示viewpager滑动停止
                    isAutoSelect = false
                }
            }
        })
    }

    fun setViewPager(viewpager: ViewPager2) {
        setViewPager(viewpager, 0)
    }

    /**
     * 正常滑动
     * @param position
     * @param positionOffset
     */
    private fun scrollTo(position: Int, positionOffset: Float) {
        //item数量大于可见item，linearlayout才滑动
        if (visibleItemCount < itemCount) {
            if (positionOffset > 0 && position > visibleItemCount - 2) {
                this.scrollTo(
                    (mWidth * positionOffset + (position - visibleItemCount + 1) * mWidth).toInt(),
                    0
                )
            }
        }
        mLeft = (position + positionOffset) * mWidth
        invalidate()
    }

    /**
     * 设置字体颜色
     */
    private fun setTitleColor() {
        invalidate()
    }

    /**
     * 设置内容数据
     *
     * @param mDatas
     */
    fun setDatas(mDatas: List<String>?) {
        if (mDatas.isNullOrEmpty())
            return

        isSetData = true
        this.mDatas = mDatas
        itemCount = mDatas.size.safe()
        if (itemCount < visibleItemCount) {
            visibleItemCount = itemCount
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                val eventX = event.x
                val eventY = event.y
                mDownTime = event.downTime
                TT@ for (index in mRectPath.indices) {
                    val rect = mRectPath[index]
                    if (eventX > rect[0] && eventX < (rect[0] + rect[2]) && eventY > rect[1] && eventY < (rect[1] + rect[3])) {
                        clickIndex = index
                        break@TT
                    }
                }
            }
            MotionEvent.ACTION_UP -> {
                if (clickIndex > -1 && event.eventTime - mDownTime < 500) {
                    mViewPager?.currentItem = clickIndex
                }
                clickIndex = -1
            }
            MotionEvent.ACTION_CANCEL -> {
                clickIndex = -1
            }
        }

        return true
    }
}