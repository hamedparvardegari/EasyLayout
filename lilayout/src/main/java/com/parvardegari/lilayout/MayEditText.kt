package com.parvardegari.lilayout

import android.content.Context
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.appcompat.content.res.AppCompatResources
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class MayEditText(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {

    private lateinit var texInputLayout: TextInputLayout
    private lateinit var textInput: TextInputEditText

    private var prefix = ""
    private var startIcon = -1
    private var counterEnable = false
    private var maxCounter = -1
    private var hint: String = ""
    private var inputType = 1


    init {
        initView(context)
        setAttrs(attrs, context)

    }

    fun getText():String{
        return textInput.text.toString()
    }
    private fun setAttrs(attrs: AttributeSet?, context: Context) {
        context.theme.obtainStyledAttributes(attrs, R.styleable.MyEditText, 0, 0).apply {
            try {
                prefix = getString(R.styleable.MyEditText_prefixText).toString()
                startIcon = getResourceId(R.styleable.MyEditText_startIcon, -1)
                counterEnable = getBoolean(R.styleable.MyEditText_counterEnabled, false)
                maxCounter = getInt(R.styleable.MyEditText_maxCounter, -1)
                hint = getString(R.styleable.MyEditText_hint).toString()
                inputType = getInt(R.styleable.MyEditText_inputType,1)
                texInputLayout.prefixText = prefix
                texInputLayout.isCounterEnabled = counterEnable
                if (startIcon != -1) {
                    texInputLayout.startIconDrawable =
                        AppCompatResources.getDrawable(context, startIcon)
                }
                if (maxCounter > 0) {
                    texInputLayout.counterMaxLength = maxCounter
                    textInput.filters = arrayOf<InputFilter>(LengthFilter(maxCounter))

                }
                textInput.inputType = inputType
                if (hint == "null")
                    hint = ""
                texInputLayout.hint = hint
            } finally {
                recycle()
            }
        }
    }

    private fun initView(context: Context) {
        inflate(context, R.layout.custom_edit_text, this)

        texInputLayout = findViewById(R.id.edtLayout)
        textInput = findViewById(R.id.edtText)

    }


}