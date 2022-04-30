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

    private val decimalFormatter: DecimalFormat
        get() = DecimalFormat("#.##")

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

    override fun compareTo(other: Size): Int {
        return this.bits.compareTo(other.bits)
    }

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
