package com.aritana.unittestwillian.calculator

class CalculatorSut(
    private val dependencyOne: DependencyOne,
    private val dependencyTwo: DependencyTwo
) {

    fun calculate() = dependencyOne.value + dependencyTwo.valueTwo.toInt()
}