package com.greencom.android.podcasts2.utils

import java.math.BigInteger
import java.text.DecimalFormat

@JvmInline
value class Size(private val bits: Long) : Comparable<Size> {

    init {
        check(bits >= 0) { "Size can not be negative: $bits bits" }
    }

    fun inUnit(unit: SizeUnit): Float {
        return bits / getBitsInUnit(unit).toFloat()
    }

    fun formatToUnit(
        unit: SizeUnit,
        decimalFormat: DecimalFormat? = null,
        appendUnitSymbol: Boolean = false,
    ): String {
        val format = decimalFormat ?: defaultDecimalFormat
        val value = inUnit(unit)
        val formatted = format.format(value)

        return if (appendUnitSymbol) {
            "$formatted ${unit.symbol}"
        } else {
            formatted
        }
    }

    override fun compareTo(other: Size): Int {
        return this.bits.compareTo(other.bits)
    }

    operator fun plus(size: Size): Size {
        val bits = bits + size.bits
        return Size(bits)
    }

    operator fun minus(size: Size): Size {
        val bits = bits - size.bits
        return Size(bits)
    }

    operator fun times(other: Int): Size {
        val bits = bits * other
        return Size(bits)
    }

    operator fun div(other: Int): Size {
        val bits = bits / other
        return Size(bits)
    }

    private val defaultDecimalFormat: DecimalFormat
        get() = DecimalFormat(DEFAULT_DECIMAL_FORMAT_PATTERN)

    companion object {

        fun of(value: Int, unit: SizeUnit): Size = value.toSize(unit)
        fun of(value: Long, unit: SizeUnit): Size = value.toSize(unit)
        fun of(value: Float, unit: SizeUnit): Size = value.toSize(unit)
        fun of(value: Double, unit: SizeUnit): Size = value.toSize(unit)

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

        private fun Int.toSize(unit: SizeUnit): Size {
            val bitsInUnit = getBitsInUnit(unit)
            val bits = this * bitsInUnit
            return Size(bits)
        }

        private fun Long.toSize(unit: SizeUnit): Size {
            val bitsInUnit = getBitsInUnit(unit)
            val bits = this * bitsInUnit
            return Size(bits)
        }

        private fun Float.toSize(unit: SizeUnit): Size {
            val bitsInUnit = getBitsInUnit(unit)
            val bits = (this * bitsInUnit).toLong()
            return Size(bits)
        }

        private fun Double.toSize(unit: SizeUnit): Size {
            val bitsInUnit = getBitsInUnit(unit)
            val bits = (this * bitsInUnit).toLong()
            return Size(bits)
        }

        private fun getBitsInUnit(unit: SizeUnit): Long {
            val unitPowerBase = getUnitPowerBase(unit)
            val unitPower = getUnitPower(unit)
            val unitConversionFactor = getUnitConversionFactor(unit)

            return BigInteger.valueOf(unitPowerBase)
                .pow(unitPower)
                .multiply(BigInteger.valueOf(unitConversionFactor.toLong()))
                .toLong()
        }

        private fun getUnitPowerBase(unit: SizeUnit): Long = when (unit) {
            SizeUnit.KIBIBITS, SizeUnit.KIBIBYTES, SizeUnit.MEBIBITS, SizeUnit.MEBIBYTES,
            SizeUnit.GIBIBITS, SizeUnit.GIBIBYTES, SizeUnit.TEBIBITS, SizeUnit.TEBIBYTES ->
                UNIT_POWER_BASE_BINARY

            else -> UNIT_POWER_BASE_DECIMAL
        }

        private fun getUnitPower(unit: SizeUnit): Int = when (unit) {
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
            SizeUnit.TERABITS, SizeUnit.TEBIBITS -> UNIT_CONVERSION_FACTOR_BITS_IN_ONE_BIT

            else -> UNIT_CONVERSION_FACTOR_BITS_IN_ONE_BYTE
        }

        private const val UNIT_POWER_BASE_DECIMAL = 1_000L
        private const val UNIT_POWER_BASE_BINARY = 1_024L

        private const val UNIT_POWER_NONE = 0
        private const val UNIT_POWER_KILO = 1
        private const val UNIT_POWER_MEGA = 2
        private const val UNIT_POWER_GIGA = 3
        private const val UNIT_POWER_TERA = 4

        private const val UNIT_CONVERSION_FACTOR_BITS_IN_ONE_BIT = 1
        private const val UNIT_CONVERSION_FACTOR_BITS_IN_ONE_BYTE = 8

        private const val DEFAULT_DECIMAL_FORMAT_PATTERN = "#.##"

    }

}

enum class SizeUnit(val symbol: String) {
    BITS("b"),
    KILOBITS("kbit"),
    MEGABITS("Mbit"),
    GIGABITS("Gbit"),
    TERABITS("Tbit"),

    KIBIBITS("Kibit"),
    MEBIBITS("Mibit"),
    GIBIBITS("Gibit"),
    TEBIBITS("Tibit"),

    BYTES("B"),
    KILOBYTES("kB"),
    MEGABYTES("MB"),
    GIGABYTES("GB"),
    TERABYTES("TB"),

    KIBIBYTES("KiB"),
    MEBIBYTES("MiB"),
    GIBIBYTES("GiB"),
    TEBIBYTES("TiB"),
}
