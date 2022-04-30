package com.greencom.android.podcasts2.utils

import java.math.BigInteger
import java.text.DecimalFormat

@JvmInline
value class Size private constructor(private val bits: Long) : Comparable<Size> {

    val inBits: Long
        get() = bits

    val inKilobits: Float
        get() = bits / getBitsInUnit(SizeUnit.KILOBITS).toFloat()

    val inMegabits: Float
        get() = bits / getBitsInUnit(SizeUnit.MEGABITS).toFloat()

    val inGigabits: Float
        get() = bits / getBitsInUnit(SizeUnit.GIGABITS).toFloat()

    val inTerabits: Float
        get() = bits / getBitsInUnit(SizeUnit.TERABITS).toFloat()

    val inKibibits: Float
        get() = bits / getBitsInUnit(SizeUnit.KIBIBITS).toFloat()

    val inMebibits: Float
        get() = bits / getBitsInUnit(SizeUnit.MEBIBITS).toFloat()

    val inGibibits: Float
        get() = bits / getBitsInUnit(SizeUnit.GIBIBITS).toFloat()

    val inTebibits: Float
        get() = bits / getBitsInUnit(SizeUnit.TEBIBITS).toFloat()

    val inBytes: Float
        get() = bits / getBitsInUnit(SizeUnit.BYTES).toFloat()

    val inKilobytes: Float
        get() = bits / getBitsInUnit(SizeUnit.KILOBYTES).toFloat()

    val inMegabytes: Float
        get() = bits / getBitsInUnit(SizeUnit.MEGABYTES).toFloat()

    val inGigabytes: Float
        get() = bits / getBitsInUnit(SizeUnit.GIGABYTES).toFloat()

    val inTerabytes: Float
        get() = bits / getBitsInUnit(SizeUnit.TERABYTES).toFloat()

    val inKibibytes: Float
        get() = bits / getBitsInUnit(SizeUnit.KIBIBYTES).toFloat()

    val inMebibytes: Float
        get() = bits / getBitsInUnit(SizeUnit.MEBIBYTES).toFloat()

    val inGibibytes: Float
        get() = bits / getBitsInUnit(SizeUnit.GIBIBYTES).toFloat()

    val inTebibytes: Float
        get() = bits / getBitsInUnit(SizeUnit.TEBIBYTES).toFloat()

    val inKilobitsFormatted: String
        get() = decimalFormatter.format(inKilobits)

    val inMegabitsFormatted: String
        get() = decimalFormatter.format(inMegabits)

    val inGigabitsFormatted: String
        get() = decimalFormatter.format(inGigabits)

    val inTerabitsFormatted: String
        get() = decimalFormatter.format(inTerabits)

    val inKibibitsFormatted: String
        get() = decimalFormatter.format(inKibibits)

    val inMebibitsFormatted: String
        get() = decimalFormatter.format(inMebibits)

    val inGibibitsFormatted: String
        get() = decimalFormatter.format(inGibibits)

    val inTebibitsFormatted: String
        get() = decimalFormatter.format(inTebibits)

    val inBytesFormatted: String
        get() = decimalFormatter.format(inBytes)

    val inKilobytesFormatted: String
        get() = decimalFormatter.format(inKilobytes)

    val inMegabytesFormatted: String
        get() = decimalFormatter.format(inMegabytes)

    val inGigabytesFormatted: String
        get() = decimalFormatter.format(inGigabytes)

    val inTerabytesFormatted: String
        get() = decimalFormatter.format(inTerabytes)

    val inKibibytesFormatted: String
        get() = decimalFormatter.format(inKibibytes)

    val inMebibytesFormatted: String
        get() = decimalFormatter.format(inMebibytes)

    val inGibibytesFormatted: String
        get() = decimalFormatter.format(inGibibytes)

    val inTebibytesFormatted: String
        get() = decimalFormatter.format(inTebibytes)

    val inBitsFormattedWithUnit: String
        get() = formatToStringWithUnitSymbol(inBits.toFloat(), SizeUnit.BITS)

    val inKilobitsFormattedWithUnit: String
        get() = formatToStringWithUnitSymbol(inKilobits, SizeUnit.KILOBITS)

    val inMegabitsFormattedWithUnit: String
        get() = formatToStringWithUnitSymbol(inMegabits, SizeUnit.MEGABITS)

    val inGigabitsFormattedWithUnit: String
        get() = formatToStringWithUnitSymbol(inGigabits, SizeUnit.GIGABITS)

    val inTerabitsFormattedWithUnit: String
        get() = formatToStringWithUnitSymbol(inTerabits, SizeUnit.TERABITS)

    val inKibibitsFormattedWithUnit: String
        get() = formatToStringWithUnitSymbol(inKibibits, SizeUnit.KIBIBITS)

    val inMebibitsFormattedWithUnit: String
        get() = formatToStringWithUnitSymbol(inMebibits, SizeUnit.MEBIBITS)

    val inGibibitsFormattedWithUnit: String
        get() = formatToStringWithUnitSymbol(inGibibits, SizeUnit.GIBIBITS)

    val inTebibitsFormattedWithUnit: String
        get() = formatToStringWithUnitSymbol(inTebibits, SizeUnit.TEBIBITS)

    val inBytesFormattedWithUnit: String
        get() = formatToStringWithUnitSymbol(inBytes, SizeUnit.BYTES)

    val inKilobytesFormattedWithUnit: String
        get() = formatToStringWithUnitSymbol(inKilobytes, SizeUnit.KILOBYTES)

    val inMegabytesFormattedWithUnit: String
        get() = formatToStringWithUnitSymbol(inMegabytes, SizeUnit.MEGABYTES)

    val inGigabytesFormattedWithUnit: String
        get() = formatToStringWithUnitSymbol(inGigabytes, SizeUnit.GIGABYTES)

    val inTerabytesFormattedWithUnit: String
        get() = formatToStringWithUnitSymbol(inTerabytes, SizeUnit.TERABYTES)

    val inKibibytesFormattedWithUnit: String
        get() = formatToStringWithUnitSymbol(inKibibytes, SizeUnit.KIBIBYTES)

    val inMebibytesFormattedWithUnit: String
        get() = formatToStringWithUnitSymbol(inMebibytes, SizeUnit.MEBIBYTES)

    val inGibibytesFormattedWithUnit: String
        get() = formatToStringWithUnitSymbol(inGibibytes, SizeUnit.GIBIBYTES)

    val inTebibytesFormattedWithUnit: String
        get() = formatToStringWithUnitSymbol(inTebibytes, SizeUnit.TEBIBYTES)

    val inWholeKilobits: Long
        get() = bits / getBitsInUnit(SizeUnit.KILOBITS)

    val inWholeMegabits: Long
        get() = bits / getBitsInUnit(SizeUnit.MEGABITS)

    val inWholeGigabits: Long
        get() = bits / getBitsInUnit(SizeUnit.GIGABITS)

    val inWholeTerabits: Long
        get() = bits / getBitsInUnit(SizeUnit.TERABITS)

    val inWholeKibibits: Long
        get() = bits / getBitsInUnit(SizeUnit.KIBIBITS)

    val inWholeMebibits: Long
        get() = bits / getBitsInUnit(SizeUnit.MEBIBITS)

    val inWholeGibibits: Long
        get() = bits / getBitsInUnit(SizeUnit.GIBIBITS)

    val inWholeTebibits: Long
        get() = bits / getBitsInUnit(SizeUnit.TEBIBITS)

    val inWholeBytes: Long
        get() = bits / getBitsInUnit(SizeUnit.BYTES)

    val inWholeKilobytes: Long
        get() = bits / getBitsInUnit(SizeUnit.KILOBYTES)

    val inWholeMegabytes: Long
        get() = bits / getBitsInUnit(SizeUnit.MEGABYTES)

    val inWholeGigabytes: Long
        get() = bits / getBitsInUnit(SizeUnit.GIGABYTES)

    val inWholeTerabytes: Long
        get() = bits / getBitsInUnit(SizeUnit.TERABYTES)

    val inWholeKibibytes: Long
        get() = bits / getBitsInUnit(SizeUnit.KIBIBYTES)

    val inWholeMebibytes: Long
        get() = bits / getBitsInUnit(SizeUnit.MEBIBYTES)

    val inWholeGibibytes: Long
        get() = bits / getBitsInUnit(SizeUnit.GIBIBYTES)

    val inWholeTebibytes: Long
        get() = bits / getBitsInUnit(SizeUnit.TEBIBYTES)

    val isAtLeastKilobit: Boolean
        get() = inWholeKilobits > 0

    val isAtLeastMegabit: Boolean
        get() = inWholeMegabits > 0

    val isAtLeastGigabit: Boolean
        get() = inWholeGigabits > 0

    val isAtLeastTerabit: Boolean
        get() = inWholeTerabits > 0

    val isAtLeastByte: Boolean
        get() = inWholeBytes > 0

    val isAtLeastKilobyte: Boolean
        get() = inWholeKilobytes > 0

    val isAtLeastMegabyte: Boolean
        get() = inWholeMegabytes > 0

    val isAtLeastGigabyte: Boolean
        get() = inWholeGigabytes > 0

    val isAtLeastTerabyte: Boolean
        get() = inWholeTerabytes > 0

    private val decimalFormatter: DecimalFormat
        get() = DecimalFormat("#.##")

    fun toBitBasedString(): String = when {
        isAtLeastTerabit -> inTerabitsFormattedWithUnit
        isAtLeastGigabit -> inGigabitsFormattedWithUnit
        isAtLeastMegabit -> inMegabitsFormattedWithUnit
        isAtLeastKilobit -> inKilobitsFormattedWithUnit
        else -> inBitsFormattedWithUnit
    }

    fun toByteBasedString(): String = when {
        isAtLeastTerabyte -> inTerabytesFormattedWithUnit
        isAtLeastGigabyte -> inGigabytesFormattedWithUnit
        isAtLeastMegabyte -> inMegabytesFormattedWithUnit
        isAtLeastKilobyte -> inKilobytesFormattedWithUnit
        else -> inBytesFormattedWithUnit
    }

    operator fun plus(size: Size): Size {
        val bits = bits + size.inBits
        return Size(bits)
    }

    operator fun minus(size: Size): Size {
        val bits = bits - size.inBits
        check(bits >= 0) { "Size can not be negative: $bits bits" }
        return Size(bits)
    }

    operator fun times(times: Int): Size {
        val bits = bits * times
        return Size(bits)
    }

    private fun formatToStringWithUnitSymbol(value: Float, unit: SizeUnit): String {
        val valueString = decimalFormatter.format(value)
        val unitSymbol = getUnitSymbol(unit)
        return FORMAT_WITH_UNIT_SYMBOL.format(valueString, unitSymbol)
    }

    private fun getUnitSymbol(unit: SizeUnit): String = when (unit) {
        SizeUnit.BITS -> UNIT_SYMBOL_BIT
        SizeUnit.KILOBITS -> UNIT_SYMBOL_KILOBIT
        SizeUnit.MEGABITS -> UNIT_SYMBOL_MEGABIT
        SizeUnit.GIGABITS -> UNIT_SYMBOL_GIGABIT
        SizeUnit.TERABITS -> UNIT_SYMBOL_TERABIT
        SizeUnit.KIBIBITS -> UNIT_SYMBOL_KIBIBIT
        SizeUnit.MEBIBITS -> UNIT_SYMBOL_MEBIBIT
        SizeUnit.GIBIBITS -> UNIT_SYMBOL_GIBIBIT
        SizeUnit.TEBIBITS -> UNIT_SYMBOL_TEBIBIT
        SizeUnit.BYTES -> UNIT_SYMBOL_BYTE
        SizeUnit.KILOBYTES -> UNIT_SYMBOL_KILOBYTE
        SizeUnit.MEGABYTES -> UNIT_SYMBOL_MEGABYTE
        SizeUnit.GIGABYTES -> UNIT_SYMBOL_GIGABYTE
        SizeUnit.TERABYTES -> UNIT_SYMBOL_TERABYTE
        SizeUnit.KIBIBYTES -> UNIT_SYMBOL_KIBIBYTE
        SizeUnit.MEBIBYTES -> UNIT_SYMBOL_MEBIBYTE
        SizeUnit.GIBIBYTES -> UNIT_SYMBOL_GIBIBYTE
        SizeUnit.TEBIBYTES -> UNIT_SYMBOL_TEBIBYTE
    }

    override fun compareTo(other: Size): Int {
        return this.bits.compareTo(other.bits)
    }

    override fun toString(): String = toBitBasedString()

    companion object {

        val Int.bits: Size get() = toSize(SizeUnit.BITS)
        val Long.bits: Size get() = toSize(SizeUnit.BITS)
        val Float.bits: Size get() = toSize(SizeUnit.BITS)
        val Double.bits: Size get() = toSize(SizeUnit.BITS)

        val Int.kilobits: Size get() = toSize(SizeUnit.KILOBITS)
        val Long.kilobits: Size get() = toSize(SizeUnit.KILOBITS)
        val Float.kilobits: Size get() = toSize(SizeUnit.KILOBITS)
        val Double.kilobits: Size get() = toSize(SizeUnit.KILOBITS)

        val Int.megabits: Size get() = toSize(SizeUnit.MEGABITS)
        val Long.megabits: Size get() = toSize(SizeUnit.MEGABITS)
        val Float.megabits: Size get() = toSize(SizeUnit.MEGABITS)
        val Double.megabits: Size get() = toSize(SizeUnit.MEGABITS)

        val Int.gigabits: Size get() = toSize(SizeUnit.GIGABITS)
        val Long.gigabits: Size get() = toSize(SizeUnit.GIGABITS)
        val Float.gigabits: Size get() = toSize(SizeUnit.GIGABITS)
        val Double.gigabits: Size get() = toSize(SizeUnit.GIGABITS)

        val Int.terabits: Size get() = toSize(SizeUnit.TERABITS)
        val Long.terabits: Size get() = toSize(SizeUnit.TERABITS)
        val Float.terabits: Size get() = toSize(SizeUnit.TERABITS)
        val Double.terabits: Size get() = toSize(SizeUnit.TERABITS)

        val Int.kibibits: Size get() = toSize(SizeUnit.KIBIBITS)
        val Long.kibibits: Size get() = toSize(SizeUnit.KIBIBITS)
        val Float.kibibits: Size get() = toSize(SizeUnit.KIBIBITS)
        val Double.kibibits: Size get() = toSize(SizeUnit.KIBIBITS)

        val Int.mebibits: Size get() = toSize(SizeUnit.MEBIBITS)
        val Long.mebibits: Size get() = toSize(SizeUnit.MEBIBITS)
        val Float.mebibits: Size get() = toSize(SizeUnit.MEBIBITS)
        val Double.mebibits: Size get() = toSize(SizeUnit.MEBIBITS)

        val Int.gibibits: Size get() = toSize(SizeUnit.GIBIBITS)
        val Long.gibibits: Size get() = toSize(SizeUnit.GIBIBITS)
        val Float.gibibits: Size get() = toSize(SizeUnit.GIBIBITS)
        val Double.gibibits: Size get() = toSize(SizeUnit.GIBIBITS)

        val Int.tebibits: Size get() = toSize(SizeUnit.TEBIBITS)
        val Long.tebibits: Size get() = toSize(SizeUnit.TEBIBITS)
        val Float.tebibits: Size get() = toSize(SizeUnit.TEBIBITS)
        val Double.tebibits: Size get() = toSize(SizeUnit.TEBIBITS)

        val Int.bytes: Size get() = toSize(SizeUnit.BYTES)
        val Long.bytes: Size get() = toSize(SizeUnit.BYTES)
        val Float.bytes: Size get() = toSize(SizeUnit.BYTES)
        val Double.bytes: Size get() = toSize(SizeUnit.BYTES)

        val Int.kilobytes: Size get() = toSize(SizeUnit.KILOBYTES)
        val Long.kilobytes: Size get() = toSize(SizeUnit.KILOBYTES)
        val Float.kilobytes: Size get() = toSize(SizeUnit.KILOBYTES)
        val Double.kilobytes: Size get() = toSize(SizeUnit.KILOBYTES)

        val Int.megabytes: Size get() = toSize(SizeUnit.MEGABYTES)
        val Long.megabytes: Size get() = toSize(SizeUnit.MEGABYTES)
        val Float.megabytes: Size get() = toSize(SizeUnit.MEGABYTES)
        val Double.megabytes: Size get() = toSize(SizeUnit.MEGABYTES)

        val Int.gigabytes: Size get() = toSize(SizeUnit.GIGABYTES)
        val Long.gigabytes: Size get() = toSize(SizeUnit.GIGABYTES)
        val Float.gigabytes: Size get() = toSize(SizeUnit.GIGABYTES)
        val Double.gigabytes: Size get() = toSize(SizeUnit.GIGABYTES)

        val Int.terabytes: Size get() = toSize(SizeUnit.TERABYTES)
        val Long.terabytes: Size get() = toSize(SizeUnit.TERABYTES)
        val Float.terabytes: Size get() = toSize(SizeUnit.TERABYTES)
        val Double.terabytes: Size get() = toSize(SizeUnit.TERABYTES)

        val Int.kibibytes: Size get() = toSize(SizeUnit.KIBIBYTES)
        val Long.kibibytes: Size get() = toSize(SizeUnit.KIBIBYTES)
        val Float.kibibytes: Size get() = toSize(SizeUnit.KIBIBYTES)
        val Double.kibibytes: Size get() = toSize(SizeUnit.KIBIBYTES)

        val Int.mebibytes: Size get() = toSize(SizeUnit.MEBIBYTES)
        val Long.mebibytes: Size get() = toSize(SizeUnit.MEBIBYTES)
        val Float.mebibytes: Size get() = toSize(SizeUnit.MEBIBYTES)
        val Double.mebibytes: Size get() = toSize(SizeUnit.MEBIBYTES)

        val Int.gibibytes: Size get() = toSize(SizeUnit.GIBIBYTES)
        val Long.gibibytes: Size get() = toSize(SizeUnit.GIBIBYTES)
        val Float.gibibytes: Size get() = toSize(SizeUnit.GIBIBYTES)
        val Double.gibibytes: Size get() = toSize(SizeUnit.GIBIBYTES)

        val Int.tebibytes: Size get() = toSize(SizeUnit.TEBIBYTES)
        val Long.tebibytes: Size get() = toSize(SizeUnit.TEBIBYTES)
        val Float.tebibytes: Size get() = toSize(SizeUnit.TEBIBYTES)
        val Double.tebibytes: Size get() = toSize(SizeUnit.TEBIBYTES)

        private fun Int.toSize(unit: SizeUnit): Size = toLong().toSize(unit)

        private fun Long.toSize(unit: SizeUnit): Size {
            val bitsInUnit = getBitsInUnit(unit)
            val bits = this * bitsInUnit
            return Size(bits)
        }

        private fun Float.toSize(unit: SizeUnit): Size = toDouble().toSize(unit)

        private fun Double.toSize(unit: SizeUnit): Size {
            val bitsInUnit = getBitsInUnit(unit)
            val bits = (this * bitsInUnit).toLong()
            return Size(bits)
        }

        private fun getBitsInUnit(unit: SizeUnit): Long {
            val unitPowerBase = getUnitPowerBase(unit)
            val unitPrefixPower = getUnitPrefixPower(unit)
            val unitConversionFactor = getUnitConversionFactor(unit)

            return BigInteger.valueOf(unitPowerBase).pow(unitPrefixPower).toLong() *
                    unitConversionFactor
        }

        private fun getUnitPowerBase(unit: SizeUnit): Long = when (unit) {
            SizeUnit.KIBIBITS, SizeUnit.KIBIBYTES, SizeUnit.MEBIBITS, SizeUnit.MEBIBYTES,
            SizeUnit.GIBIBITS, SizeUnit.GIBIBYTES, SizeUnit.TEBIBITS, SizeUnit.TEBIBYTES ->
                UNIT_POWER_BASE_BINARY

            else -> UNIT_POWER_BASE_DECIMAL
        }

        private fun getUnitPrefixPower(unit: SizeUnit): Int = when (unit) {
            SizeUnit.BITS, SizeUnit.BYTES -> UNIT_POWER_NONE
            SizeUnit.KILOBITS, SizeUnit.KIBIBITS, SizeUnit.KILOBYTES, SizeUnit.KIBIBYTES -> UNIT_POWER_KILO
            SizeUnit.MEGABITS, SizeUnit.MEBIBITS, SizeUnit.MEGABYTES, SizeUnit.MEBIBYTES -> UNIT_POWER_MEGA
            SizeUnit.GIGABITS, SizeUnit.GIBIBITS, SizeUnit.GIGABYTES, SizeUnit.GIBIBYTES -> UNIT_POWER_GIGA
            SizeUnit.TERABITS, SizeUnit.TEBIBITS, SizeUnit.TERABYTES, SizeUnit.TEBIBYTES -> UNIT_POWER_TERA
        }

        private fun getUnitConversionFactor(unit: SizeUnit): Int = when (unit) {
            SizeUnit.BITS,
            SizeUnit.KILOBITS, SizeUnit.KIBIBITS,
            SizeUnit.MEGABITS, SizeUnit.MEBIBITS,
            SizeUnit.GIGABITS, SizeUnit.GIBIBITS,
            SizeUnit.TERABITS, SizeUnit.TEBIBITS -> BIT

            else -> BITS_IN_BYTE
        }

        private const val UNIT_SYMBOL_BIT = "b"
        private const val UNIT_SYMBOL_KILOBIT = "kbit"
        private const val UNIT_SYMBOL_MEGABIT = "Mbit"
        private const val UNIT_SYMBOL_GIGABIT = "Gbit"
        private const val UNIT_SYMBOL_TERABIT = "Tbit"

        private const val UNIT_SYMBOL_KIBIBIT = "Kibit"
        private const val UNIT_SYMBOL_MEBIBIT = "Mibit"
        private const val UNIT_SYMBOL_GIBIBIT = "Gibit"
        private const val UNIT_SYMBOL_TEBIBIT = "Tibit"

        private const val UNIT_SYMBOL_BYTE = "B"
        private const val UNIT_SYMBOL_KILOBYTE = "kB"
        private const val UNIT_SYMBOL_MEGABYTE = "MB"
        private const val UNIT_SYMBOL_GIGABYTE = "GB"
        private const val UNIT_SYMBOL_TERABYTE = "TB"

        private const val UNIT_SYMBOL_KIBIBYTE = "KiB"
        private const val UNIT_SYMBOL_MEBIBYTE = "MiB"
        private const val UNIT_SYMBOL_GIBIBYTE = "GiB"
        private const val UNIT_SYMBOL_TEBIBYTE = "TiB"

        private const val UNIT_POWER_BASE_DECIMAL = 1_000L
        private const val UNIT_POWER_BASE_BINARY = 1_024L

        private const val UNIT_POWER_NONE = 0
        private const val UNIT_POWER_KILO = 1
        private const val UNIT_POWER_MEGA = 2
        private const val UNIT_POWER_GIGA = 3
        private const val UNIT_POWER_TERA = 4

        private const val BIT = 1
        private const val BITS_IN_BYTE = 8

        private const val FORMAT_WITH_UNIT_SYMBOL = "%1\$s %2\$s"

    }

}

enum class SizeUnit {
    BITS,
    KILOBITS,
    MEGABITS,
    GIGABITS,
    TERABITS,

    KIBIBITS,
    MEBIBITS,
    GIBIBITS,
    TEBIBITS,

    BYTES,
    KILOBYTES,
    MEGABYTES,
    GIGABYTES,
    TERABYTES,

    KIBIBYTES,
    MEBIBYTES,
    GIBIBYTES,
    TEBIBYTES,
}
