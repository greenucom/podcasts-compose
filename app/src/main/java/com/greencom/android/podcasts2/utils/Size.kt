package com.greencom.android.podcasts2.utils

import java.math.BigInteger

@JvmInline
value class Size private constructor(private val rawValueBits: BigInteger) : Comparable<Size> {

    val inBitsLong: Long
        get() {
            val maxValue = BigInteger.valueOf(Long.MAX_VALUE)
            if (rawValueBits <= maxValue) {
                return rawValueBits.toLong()
            } else {
                throw ArithmeticException("Value does not fit in a Long, use inBitsExact instead")
            }
        }

    val inBits: BigInteger
        get() = rawValueBits

    val inWholeBytesLong: Long
        get() {
            val value = rawValueBits / BigInteger.valueOf(bitsInByte)
            val maxValue = BigInteger.valueOf(Long.MAX_VALUE)
            if (value <= maxValue) {
                return value.toLong()
            } else {
                throw ArithmeticException("Value does not fit in a Long, use inWholeBytesExact instead")
            }
        }

    val inWholeBytes: BigInteger
        get() = rawValueBits / BigInteger.valueOf(bitsInByte)

    val inWholeKilobytesLong: Long
        get() {
            val value = rawValueBits / BigInteger.valueOf(bitsInKilobyte)
            val maxValue = BigInteger.valueOf(Long.MAX_VALUE)
            if (value <= maxValue) {
                return value.toLong()
            } else {
                throw ArithmeticException("Value does not fit in a Long, use inWholeKilobytesExact instead")
            }
        }

    val inWholeKilobytes: BigInteger
        get() = rawValueBits / BigInteger.valueOf(bitsInKilobyte)

    val inWholeMegabytes: Long
        get() = (rawValueBits / BigInteger.valueOf(bitsInMegabyte)).toLong()

    val inWholeGigabytes: Long
        get() = (rawValueBits / BigInteger.valueOf(bitsInGigabyte)).toLong()

    operator fun plus(size: Size): Size = Size(rawValueBits + size.rawValueBits)

    override fun compareTo(other: Size): Int {
        return this.rawValueBits.compareTo(other.rawValueBits)
    }

    companion object {

        val Int.bits: Size get() = this.toSize(SizeUnit.BITS)

        val Long.bits: Size get() = this.toSize(SizeUnit.BITS)

        val Int.bytes: Size get() = this.toSize(SizeUnit.BYTES)

        val Long.bytes: Size get() = this.toSize(SizeUnit.BYTES)

        val Int.kilobytes: Size get() = this.toSize(SizeUnit.KILOBYTES)

        val Long.kilobytes: Size get() = this.toSize(SizeUnit.KILOBYTES)

        val Int.megabytes get() = this.toSize(SizeUnit.MEGABYTES)

        val Long.megabytes get() = this.toSize(SizeUnit.MEGABYTES)

        val Int.gigabytes get() = this.toSize(SizeUnit.GIGABYTES)

        val Long.gigabytes get() = this.toSize(SizeUnit.GIGABYTES)

        private fun Int.toSize(unit: SizeUnit) = this.toLong().toSize(unit)

        private fun Long.toSize(unit: SizeUnit): Size {
            val bitsInUnit = bitsInUnit(unit)
            val bits = this * bitsInUnit
            val value = BigInteger.valueOf(bits)
            return Size(value)
        }

        private fun bitsInUnit(unit: SizeUnit): Long = when (unit) {
            SizeUnit.BITS -> ONE_BIT
            SizeUnit.BYTES -> bitsInByte
            SizeUnit.KILOBYTES -> bitsInKilobyte
            SizeUnit.MEGABYTES -> bitsInMegabyte
            SizeUnit.GIGABYTES -> bitsInGigabyte
        }

        private val bitsInByte get() = BITS_IN_BYTE
        private val bitsInKilobyte get() = BYTES_IN_KILOBYTE * bitsInByte
        private val bitsInMegabyte get() = KILOBYTES_IN_MEGABYTE * bitsInKilobyte
        private val bitsInGigabyte get() = MEGABYTES_IN_GIGABYTE * bitsInMegabyte

        private const val ONE_BIT = 1L
        private const val BITS_IN_BYTE = 8L
        private const val BYTES_IN_KILOBYTE = 1_000L
        private const val KILOBYTES_IN_MEGABYTE = 1_000L
        private const val MEGABYTES_IN_GIGABYTE = 1_000L

    }

}

private enum class SizeUnit {
    BITS,
    BYTES,
    KILOBYTES,
    MEGABYTES,
    GIGABYTES,
}
