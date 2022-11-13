package com.p_android.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import net.objecthunter.exp4j.ExpressionBuilder
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.round

class MainActivity : AppCompatActivity() {
    private lateinit var calculatorScreen: TextView
    private var state = State.EMPTY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        calculatorScreen = findViewById(R.id.calculator_screen)
        prepareButtons()
    }

    private fun prepareButtons() {
        for (button in NumberButton.values()) {
            setUpNumberButton(button)
        }
        for (button in OneArgumentButton.values()) {
            setUpSingleArgumentButton(button)
        }
        for (button in TwoArgumentButton.values()) {
            setUpTwoArgumentButton(button)
        }
        setUpEquals()
        setUpClear()
        setUpDot()
        setUpDelete()
    }

    private fun setUpNumberButton(buttonSetup: NumberButton) {
        val button: Button = findViewById(buttonSetup.buttonId)

        button.setOnClickListener {
            val newText = "${calculatorScreen.text}${buttonSetup.text}"
            state = when (state) {
                State.EMPTY, State.NUMBER,
                    State.EQUALS, State.DOT-> {
                    calculatorScreen.text = newText
                    State.NUMBER
                }
                State.SINGLE_OPERATOR, State.DOUBLE_OPERATOR -> {
                    calculatorScreen.text = newText
                    State.EQUALS
                }
            }
        }
    }

    private fun setUpSingleArgumentButton(buttonSetup: OneArgumentButton) {
        val button: Button = findViewById(buttonSetup.buttonId)
        button.setOnClickListener {
            val newText = "${calculatorScreen.text}${buttonSetup.text}"
            when(state) {
                State.EMPTY -> {
                    calculatorScreen.text = newText
                    state = State.SINGLE_OPERATOR
                }
                else -> {}
            }
        }
    }

    private fun setUpTwoArgumentButton(buttonSetup: TwoArgumentButton) {
        val button: Button = findViewById(buttonSetup.buttonId)
        button.setOnClickListener {
            val newText = "${calculatorScreen.text}${buttonSetup.text}"
            when(state) {
                State.NUMBER -> {
                    calculatorScreen.text = newText
                    state = State.DOUBLE_OPERATOR
                }
                else -> {}
            }
        }
    }

    private fun setUpEquals() {
        val button: Button = findViewById(R.id.equals)
        button.setOnClickListener {
            if (state == State.EQUALS) {
                val output = ExpressionBuilder(calculatorScreen.text.toString().replace("√", "sqrt")).build().evaluate()
                val remainder = output - output.toInt()
                if (remainder != 0.0) {
                    calculatorScreen.text = BigDecimal(output).setScale(6, RoundingMode.HALF_UP).toString()
                } else {
                    calculatorScreen.text = output.toInt().toString()
                }
                state = State.NUMBER
            }
        }
    }

    private fun setUpClear() {
        val button: Button = findViewById(R.id.clear)
        button.setOnClickListener {
            calculatorScreen.text = ""
            state = State.EMPTY
        }
    }

    private fun setUpDelete() {
        val button: Button = findViewById(R.id.delete)
        button.setOnClickListener {
            val length = calculatorScreen.text.length
            val newText = calculatorScreen.text.subSequence(0, length - 1).toString()
            calculatorScreen.text = newText
        }
    }

    private fun setUpDot() {
        val dotSymbol = "."
        val button: Button = findViewById(R.id.dot)
        button.setOnClickListener {
            if (state == State.NUMBER) {
                val newText = "${calculatorScreen.text}$dotSymbol"
                calculatorScreen.text = newText
                state = State.DOT
            }
        }
    }


//    private fun setUpPercent() {
//        TODO jesli nagle zachce mi sie robic procent jako *1/100 anizeli modulo
//        val percentSign = "%"
//        val button: Button = findViewById(R.id.percent)
//        button.setOnClickListener {
//            if (state == State.NUMBER) {
//                val newText = "${calculatorScreen.text}$percentSign"
//                calculatorScreen.text = newText
//                state = State.EQUALS
//            }
//        }
//    }

}