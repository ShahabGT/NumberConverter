fun main() {

    print(1234567.toLong().toEnglishFormat())

}


fun Long.toEnglishFormat(): String {
    var number = this.toString()

    number = when (number.length % 3) {
        1 -> "00$number"
        2 -> "0$number"
        else -> number
    }

    val digits = getDigits()
    val result = StringBuilder()
    var res: StringBuilder
    var len = number.length
    var count = 0
    var sum: Int
    while (len > 0) {
        sum = 0
        res = StringBuilder()
        val three = number.substring(len - 3, len)

        for (i in 0..2) {
            val num = three[i].toInt() - '0'.toInt()
            sum += num
            if (num == 0) continue
            if (i == 1) {
                val nextNum = three[i + 1].toInt() - '0'.toInt()
                if (num == 1 && nextNum != 0) {
                    sum += nextNum
                    res.append(digits[nextNum - 1].fourth)
                    break
                }
            }
            res.append(digits[num - 1].get(i))
        }
        if (sum != 0) {
            when (count) {
                1 -> res.append("thousand and ")
                2 -> res.append("million and ")
                3 -> res.append("billion and ")
                4 -> res.append("trillion and ")
                5 -> res.append("quadrillion and ")
                6 -> res.append("quintillion and ")
                7 -> res.append("sextillion and ")
            }
        }
        result.insert(0, res.toString())
        count++
        len -= 3


    }
    return if (result.toString().endsWith(" and ")) result.toString().substring(0, result.toString().length - 3) else result.toString()

}

data class Types(val first: String, val second: String, val third: String, val fourth: String) {
    fun get(i: Int) = when (i) {
        0 -> third
        1 -> second
        2 -> first
        else -> "zero"
    }
}

fun getDigits(): List<Types> {
    val digits = mutableListOf<Types>()
    digits.add(Types("one ", "ten ", "one hundred ", "eleven "))
    digits.add(Types("two ", "twenty ", "two hundred ", "twelve "))
    digits.add(Types("three ", "thirty ", "three hundred ", "thirteen "))
    digits.add(Types("four ", "forty ", "four hundred ", "fourteen "))
    digits.add(Types("five ", "fifty ", "five hundred ", "fifteen "))
    digits.add(Types("six ", "sixty ", "six hundred ", "sixteen "))
    digits.add(Types("seven ", "seventy ", "seven hundred ", "seventeen "))
    digits.add(Types("eight ", "eighty ", "eight hundred ", "eighteen "))
    digits.add(Types("nine ", "ninety ", "nine hundred ", "nineteen "))
    return digits


}
