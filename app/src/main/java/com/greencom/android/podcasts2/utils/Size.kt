package com.greencom.android.podcasts2.utils

import com.greencom.android.podcasts2.utils.Size.Companion.bytes
import com.greencom.android.podcasts2.utils.Size.Companion.megabytes
import com.greencom.android.podcasts2.utils.Size.Companion.of
import java.math.BigInteger
import java.text.DecimalFormat

// TODO: Implement toString()

/**
 * Value class that represents the size of the information. Note that the underlying value is
 * represented by the number of bits in [Long], so the maximum size that can be correctly
 * represented by this class is ~1100 petabytes. Measurement units are represented by
 * [SizeUnit].
 *
 * Use [of] to create an instance of the class. Alternatively, you can use extension properties
 * such as [bits], [bytes], [megabytes] etc.
 */
@JvmInline
value class Size(private val bits: Long) : Comparable<Size> {

    init {
        check(bits >= 0) { "Size can not be negative: $bits bits" }
    }

    fun inUnit(unit: SizeUnit): Float {
        return bits / getBitsInUnit(unit).toFloat()
    }

    fun format(
        unit: SizeUnit,
        appendUnitSymbol: Boolean = false,
        valueFormat: DecimalFormat = defaultDecimalFormat,
    ): String {
        val value = inUnit(unit)
        val formatted = valueFormat.format(value)
        return if (appendUnitSymbol) {
            FORMAT_WITH_UNIT_SYMBOL.format(formatted, unit.symbol)
        } else {
            formatted
        }
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

    override fun compareTo(other: Size): Int {
        return this.bits.compareTo(other.bits)
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
            val unitPowerBase = UNIT_POWER_BASE_DECIMAL
            val unitPower = getUnitPower(unit)
            val unitConversionFactor = getUnitConversionFactor(unit)

            return BigInteger.valueOf(unitPowerBase)
                .pow(unitPower)
                .multiply(BigInteger.valueOf(unitConversionFactor.toLong()))
                .toLong()
        }

        private fun getUnitPower(unit: SizeUnit): Int = when (unit) {
            SizeUnit.BITS, SizeUnit.BYTES -> UNIT_POWER_NONE
            SizeUnit.KILOBITS, SizeUnit.KILOBYTES -> UNIT_POWER_KILO
            SizeUnit.MEGABITS, SizeUnit.MEGABYTES -> UNIT_POWER_MEGA
            SizeUnit.GIGABITS, SizeUnit.GIGABYTES -> UNIT_POWER_GIGA
            SizeUnit.TERABITS, SizeUnit.TERABYTES -> UNIT_POWER_TERA
        }

        private fun getUnitConversionFactor(unit: SizeUnit): Int = when (unit) {
            SizeUnit.BITS,
            SizeUnit.KILOBITS,
            SizeUnit.MEGABITS,
            SizeUnit.GIGABITS,
            SizeUnit.TERABITS -> UNIT_CONVERSION_FACTOR_BITS_IN_ONE_BIT

            else -> UNIT_CONVERSION_FACTOR_BITS_IN_ONE_BYTE
        }

        private const val UNIT_POWER_BASE_DECIMAL = 1_000L

        private const val UNIT_POWER_NONE = 0
        private const val UNIT_POWER_KILO = 1
        private const val UNIT_POWER_MEGA = 2
        private const val UNIT_POWER_GIGA = 3
        private const val UNIT_POWER_TERA = 4

        private const val UNIT_CONVERSION_FACTOR_BITS_IN_ONE_BIT = 1
        private const val UNIT_CONVERSION_FACTOR_BITS_IN_ONE_BYTE = 8

        private const val DEFAULT_DECIMAL_FORMAT_PATTERN = "#.##"
        private const val FORMAT_WITH_UNIT_SYMBOL = "%1\$s %2\$s"

    }

}

enum class SizeUnit(val symbol: String) {
    BITS("b"),
    KILOBITS("kbit"),
    MEGABITS("Mbit"),
    GIGABITS("Gbit"),
    TERABITS("Tbit"),

    BYTES("B"),
    KILOBYTES("kB"),
    MEGABYTES("MB"),
    GIGABYTES("GB"),
    TERABYTES("TB"),
}
