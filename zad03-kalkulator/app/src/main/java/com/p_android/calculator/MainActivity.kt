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
    private var hasDot = false
    private var hasDotEquals = false
    private var firstDot = false

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
        setUpNegativeSign()
//        setUpSmiley()
    }

    private fun setUpNumberButton(buttonSetup: NumberButton) {
        val button: Button = findViewById(buttonSetup.buttonId)

        button.setOnClickListener {
            val newText = "${calculatorScreen.text}${buttonSetup.text}"
            state = when (state) {
                State.EMPTY, State.NUMBER, -> {
                    State.NUMBER
                }
                State.DOT -> {
                    if (firstDot) State.NUMBER else State.EQUALS
                }
                State.SINGLE_OPERATOR, State.DOUBLE_OPERATOR,
                State.EQUALS -> {
                    State.EQUALS
                }
            }
            calculatorScreen.text = newText
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
                    hasDot = false
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
                hasDotEquals = false
                if (calculatorScreen.text.contains(".")) {
                    hasDot = true
                }
                state = State.NUMBER
            }
        }
    }

    private fun setUpClear() {
        val button: Button = findViewById(R.id.clear)
        button.setOnClickListener {
            calculatorScreen.text = ""
            hasDot = false
            hasDotEquals = false
            firstDot = false
            state = State.EMPTY
        }
    }

    private fun setUpDelete() {
        val button: Button = findViewById(R.id.delete)
        button.setOnClickListener {
            val length = calculatorScreen.text.length
            val newText = calculatorScreen.text.subSequence(0, length - 1).toString()
            val deletedCharacter = calculatorScreen.text[length - 1]
            when (state) {
                State.EQUALS -> {
                    val del = deletedCharacter.toString()
                    if (del in TwoArgumentButton.values().map { it.text }) {
                        state = State.NUMBER
                    } else if (newText[length - 2] == '.') {
                        state = State.DOT
                    }
                }
                State.DOT -> {
                    if (firstDot) {
                        hasDot = false
                        state = State.NUMBER
                    } else {
                        hasDotEquals = false
                        state = State.EQUALS
                    }
                }
                State.DOUBLE_OPERATOR -> {
                    state = State.NUMBER
                }
                State.NUMBER -> {
                    state = if (newText[length - 2] == '.') {
                        State.DOT
                    } else State.NUMBER
                }
                State.SINGLE_OPERATOR -> state = State.EMPTY
                State.EMPTY -> {}
            }

            calculatorScreen.text = newText
        }
    }

    private fun setUpDot() {
        val dotSymbol = "."
        val button: Button = findViewById(R.id.dot)
        button.setOnClickListener {
            if (state == State.NUMBER && !hasDot) {
                hasDot = true
                val newText = "${calculatorScreen.text}$dotSymbol"
                calculatorScreen.text = newText
                state = State.DOT
                firstDot = true
            } else if (state == State.EQUALS && !hasDot) {
                hasDotEquals = true
                val newText = "${calculatorScreen.text}$dotSymbol"
                calculatorScreen.text = newText
                state = State.DOT
                firstDot = false
            }
        }
    }

    private fun setUpNegativeSign() {
        val button: Button = findViewById(R.id.negativeSign)
        button.setOnClickListener {
            if (state == State.NUMBER || state == State.EQUALS) {
                val newText = "-${calculatorScreen.text}"
                calculatorScreen.text = newText
            }
        }
    }

    private fun setUpSmiley() {
        val button: Button = findViewById(R.id.smiley)
        button.setOnClickListener {
            println(calculatorScreen.text)
            println(state)
            println("hasDot = $hasDot")
            println("hasDotEquals = $hasDotEquals")
            println("firstDot = $firstDot")
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