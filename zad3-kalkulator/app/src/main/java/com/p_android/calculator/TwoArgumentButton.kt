package com.p_android.calculator

enum class TwoArgumentButton(val buttonId: Int, val text: String,) {
    PLUS(R.id.plus, "+"),
    MINUS(R.id.minus, "-"),
    MULTIPLICATION(R.id.multiplication, "*"),
    DIVISION(R.id.division, "/"),
    POWER(R.id.power, "^"),
    PERCENT(R.id.percent, "%"),
}