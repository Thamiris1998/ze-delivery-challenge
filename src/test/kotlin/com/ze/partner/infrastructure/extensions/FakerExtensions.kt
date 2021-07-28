package com.ze.partner.infrastructure.extensions

import com.github.javafaker.Faker
import java.util.*

fun Faker.validCPF() : String {
    val cpfNumber = StringBuilder()
    for (i in 0..8) {
        cpfNumber.append(Random().nextInt(9))
    }

    return generateCPF(cpfNumber.toString())
}

fun Faker.validCNPJ() : String {
    return generateCNPJ()
}
private fun generateCNPJ() : String{
    val n = 9
    val n1: Int = randomiza(n)
    val n2: Int = randomiza(n)
    val n3: Int = randomiza(n)
    val n4: Int = randomiza(n)
    val n5: Int = randomiza(n)
    val n6: Int = randomiza(n)
    val n7: Int = randomiza(n)
    val n8: Int = randomiza(n)
    val n9 = 0

    val n10 = 0

    val n11 = 0

    val n12 = 1

    var d1 =
        n12 * 2 + n11 * 3 + n10 * 4 + n9 * 5 + n8 * 6 + n7 * 7 + n6 * 8 + n5 * 9 + n4 * 2 + n3 * 3 + n2 * 4 + n1 * 5

    d1 = 11 - mod(d1, 11)

    if (d1 >= 10) d1 = 0

    var d2 =
        d1 * 2 + n12 * 3 + n11 * 4 + n10 * 5 + n9 * 6 + n8 * 7 + n7 * 8 + n6 * 9 + n5 * 2 + n4 * 3 + n3 * 4 + n2 * 5 + n1 * 6

    d2 = 11 - mod(d2, 11)

    if (d2 >= 10) d2 = 0


    return  "" + n1 + n2 + n3 + n4 + n5 + n6 + n7 + n8 + n9 + n10 + n11 + n12 + d1 + d2
}
private fun generateCPF(digitsBase: String): String {
    val sbCpfNumber = StringBuilder(digitsBase)
    var total = 0
    var multiple = digitsBase.length + 1
    for (digit in digitsBase.toCharArray()) {
        val parcial = (digit.toString().toInt() * multiple--).toLong()
        total += parcial.toInt()
    }
    var resto = Math.abs(total % 11).toString().toInt()
    resto = if (resto < 2) {
        0
    } else {
        11 - resto
    }
    sbCpfNumber.append(resto)
    return if (sbCpfNumber.length < 11) {
        generateCPF(sbCpfNumber.toString())
    } else sbCpfNumber.toString()
}

private fun randomiza(n: Int): Int {
    return (Math.random() * n).toInt()
}

private fun mod(dividendo: Int, divisor: Int): Int {
    return Math.round(dividendo - Math.floor((dividendo / divisor).toDouble()) * divisor).toInt()
}