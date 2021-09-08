package com.zires.switchsegmentedcontrol

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.content.ContextCompat
import androidx.core.view.setPadding


/**
 * Created by ClassicZires on 7/30/2019.
 */
open class ZiresSwitchSegmentedControl : LinearLayout {
    private val motionLayout: MotionLayout
    private val motionLayoutContainer: RelativeLayout
    private var mSwitchChangeListener: OnSwitchChangeListener? = null
    private lateinit var switchFirstItem: TextView
    private lateinit var switchSecondItem: TextView
    private var transitionStart = false
    private var switchBackgroundColor = 0
    private var activeBgColor = 0
    private var activeTextColor = 0
    private var inactiveTextColor = 0
    private var textSize = 0f
    private var switchFontFamily = ""
    private var cornerRadius: Float = 0f
    private var strokeWidth: Int = 0
    private var borderColor = 0
    private lateinit var selected: View

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        motionLayoutContainer =
            LayoutInflater.from(context).inflate(R.layout.switch_layout, null) as RelativeLayout
        motionLayout = motionLayoutContainer.findViewById(R.id.motion_layout)
        addView(motionLayoutContainer)
        init(context, attrs)
        val layoutParams =
            LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        setLayoutParams(layoutParams)
        motionLayoutContainer.layoutParams = layoutParams
        requestLayout()
        initOnClick()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        motionLayoutContainer =
            LayoutInflater.from(context).inflate(R.layout.switch_layout, null) as RelativeLayout
        motionLayout = motionLayoutContainer.findViewById(R.id.motion_layout)
        addView(motionLayoutContainer)
        init(context, attrs)
        initOnClick()
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        if (attrs != null) {
            val attributes =
                context.obtainStyledAttributes(attrs, R.styleable.ZiresSwitchSegmentedControl, 0, 0)
            try {
                switchFirstItem = motionLayout.findViewById(R.id.switch_first_item)
                switchSecondItem = motionLayout.findViewById(R.id.switch_second_item)
                selected = motionLayout.findViewById(R.id.switch_selected)
                val leftToggleText =
                    attributes.getString(R.styleable.ZiresSwitchSegmentedControl_textToggleLeft)
                val rightToggleText =
                    attributes.getString(R.styleable.ZiresSwitchSegmentedControl_textToggleRight)
                activeBgColor = attributes.getColor(
                    R.styleable.ZiresSwitchSegmentedControl_activeBgColor,
                    ContextCompat.getColor(context, Default.ACTIVE_BG_COLOR)
                )
                activeTextColor = attributes.getColor(
                    R.styleable.ZiresSwitchSegmentedControl_activeTextColor,
                    ContextCompat.getColor(context, Default.ACTIVE_TEXT_COLOR)
                )
                inactiveTextColor = attributes.getColor(
                    R.styleable.ZiresSwitchSegmentedControl_inactiveTextColor,
                    ContextCompat.getColor(context, Default.INACTIVE_TEXT_COLOR)
                )
                borderColor = attributes.getColor(
                    R.styleable.ZiresSwitchSegmentedControl_borderColor,
                    ContextCompat.getColor(context, Default.BORDER_COLOR)
                )
                textSize = attributes.getDimension(
                    R.styleable.ZiresSwitchSegmentedControl_textSize,
                    Default.TEXT_SIZE
                )
                switchFontFamily = attributes.getString(
                    R.styleable.ZiresSwitchSegmentedControl_switchFontFamily,
                ) ?: Default.FONT_FAMILY
                cornerRadius = attributes.getDimension(
                    R.styleable.ZiresSwitchSegmentedControl_cornerRadius,
                    Default.CORNER_RADIUS_DP
                )
                strokeWidth = attributes.getDimension(
                    R.styleable.ZiresSwitchSegmentedControl_strokeWidth,
                    Default.STROKE_WIDTH
                ).toInt()
                switchBackgroundColor = attributes.getColor(
                    R.styleable.ZiresSwitchSegmentedControl_backgroundColor,
                    ContextCompat.getColor(context, Default.BACKGROUND_COLOR)
                )
                val isChecked = attributes.getBoolean(
                    R.styleable.ZiresSwitchSegmentedControl_checked,
                    Default.CHECKED
                )
                val typeface = Typeface.create(switchFontFamily, Typeface.BOLD)
                switchFirstItem.text = rightToggleText
                switchSecondItem.text = leftToggleText
                switchFirstItem.typeface = typeface
                switchSecondItem.typeface = typeface
                switchFirstItem.textSize = textSize
                switchSecondItem.textSize = textSize

                val selectedGd = GradientDrawable()
                selectedGd.setColor(activeBgColor)
                selectedGd.cornerRadius = cornerRadius - (strokeWidth)
                selected.background = selectedGd

                val motionLayoutGd = GradientDrawable()
                motionLayoutGd.setColor(switchBackgroundColor)
                motionLayoutGd.cornerRadius = cornerRadius
                motionLayoutGd.setStroke(strokeWidth, borderColor)
                motionLayoutContainer.background = motionLayoutGd
                motionLayoutContainer.setPadding(strokeWidth)

                motionLayout.getConstraintSet(R.id.starting_set)?.let { startConstraintSet ->
                    startConstraintSet.getConstraint(R.id.switch_first_item).let {
                        it.mCustomConstraints["TextColor"]?.setColorValue(activeTextColor)
                    }

                    startConstraintSet.getConstraint(R.id.switch_second_item).let {
                        it.mCustomConstraints["TextColor"]?.setColorValue(inactiveTextColor)
                    }
                }
                motionLayout.getConstraintSet(R.id.ending_set)?.let { startConstraintSet ->
                    startConstraintSet.getConstraint(R.id.switch_first_item).let {
                        it.mCustomConstraints["TextColor"]?.setColorValue(inactiveTextColor)
                    }

                    startConstraintSet.getConstraint(R.id.switch_second_item).let {
                        it.mCustomConstraints["TextColor"]?.setColorValue(activeTextColor)
                    }
                }

                setChecked(isChecked)
            } finally {
                attributes.recycle()
            }
        }
    }

    fun setChecked(checked: Boolean) {
        if (checked) {
            transitionStart = false
            motionLayout.transitionToStart()
        } else {
            transitionStart = true
            motionLayout.transitionToEnd()
        }
    }

    fun getIsChecked(): Boolean {
        return transitionStart
    }

    private fun initOnClick() {
        transitionStart = true
        motionLayoutContainer.setOnClickListener {
            mSwitchChangeListener?.onToggleSwitchChangeListener(transitionStart)
            setChecked(transitionStart)
        }
    }

    fun setOnToggleSwitchChangeListener(listener: OnSwitchChangeListener) {
        mSwitchChangeListener = listener
    }

    interface OnSwitchChangeListener {
        fun onToggleSwitchChangeListener(isChecked: Boolean)
    }

    protected object Default {
        const val CHECKED: Boolean = false
        val ACTIVE_BG_COLOR = R.color.green
        const val ACTIVE_TEXT_COLOR = android.R.color.white
        val INACTIVE_TEXT_COLOR = R.color.gray
        val BORDER_COLOR = R.color.green
        const val BACKGROUND_COLOR = android.R.color.transparent
        const val CORNER_RADIUS_DP = 1000f
        const val TEXT_SIZE = 12f
        const val FONT_FAMILY = "sans-serif"
        const val STROKE_WIDTH = 2f
    }
}