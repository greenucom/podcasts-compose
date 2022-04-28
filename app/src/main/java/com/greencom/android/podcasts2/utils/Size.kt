package com.greencom.android.podcasts2.utils

import java.text.DecimalFormat

/**
 * Value class that based on **64bit value of size in bytes**, hence the max value that can be
 * correctly represented by this class is ~9200 petabytes.
 */
@JvmInline
value class Size private constructor(private val bytes: Long) : Comparable<Size> {

    val inBytes: Long
        get() = bytes

    val inKilobytes: Float
        get() = bytes / BYTES_IN_KILOBYTE.toFloat()

    val inMegabytes: Float
        get() = bytes / BYTES_IN_MEGABYTE.toFloat()

    val inGigabytes: Float
        get() = bytes / BYTES_IN_GIGABYTE.toFloat()

    /** [inKilobytes] value in #.## format */
    val inKilobytesFormatted: String
        get() = decimalFormatter.format(inKilobytes)

    /** [inMegabytes] value in #.## format */
    val inMegabytesFormatted: String
        get() = decimalFormatter.format(inMegabytes)

    /** [inGigabytes] value in #.## format */
    val inGigabytesFormatted: String
        get() = decimalFormatter.format(inGigabytes)

    val inWholeKilobytes: Long
        get() = bytes / BYTES_IN_KILOBYTE

    val inWholeMegabytes: Long
        get() = bytes / BYTES_IN_MEGABYTE

    val inWholeGigabytes: Long
        get() = bytes / BYTES_IN_GIGABYTE

    val isAtLeastKilobyte: Boolean
        get() = inWholeKilobytes > 0

    val isAtLeastMegabyte: Boolean
        get() = inWholeMegabytes > 0

    val isAtLeastGigabyte: Boolean
        get() = inWholeGigabytes > 0

    operator fun plus(size: Size): Size {
        val newBytes = bytes + size.bytes
        return Size(newBytes)
    }

    operator fun minus(size: Size): Size {
        val newBytes = bytes - size.bytes
        check(newBytes >= 0) { "Size can not be negative" }
        return Size(newBytes)
    }

    operator fun times(times: Int): Size {
        val newBytes = bytes * times
        return Size(newBytes)
    }

    fun toComponents(
        action: (megabytes: Long, kilobytes: Int, bytes: Int) -> Unit,
    ) {
        var remainingBytes = bytes

        val megabytes = remainingBytes / BYTES_IN_MEGABYTE
        remainingBytes -= megabytes * BYTES_IN_MEGABYTE

        val kilobytes = remainingBytes / BYTES_IN_KILOBYTE
        remainingBytes -= kilobytes * BYTES_IN_KILOBYTE

        action(megabytes, kilobytes.toInt(), remainingBytes.toInt())
    }

    fun toComponents(
        action: (gigabytes: Long, megabytes: Int, kilobytes: Int, bytes: Int) -> Unit,
    ) {
        var remainingBytes = bytes

        val gigabytes = remainingBytes / BYTES_IN_GIGABYTE
        remainingBytes -= gigabytes * BYTES_IN_GIGABYTE

        val megabytes = remainingBytes / BYTES_IN_MEGABYTE
        remainingBytes -= megabytes * BYTES_IN_MEGABYTE

        val kilobytes = remainingBytes / BYTES_IN_KILOBYTE
        remainingBytes -= kilobytes * BYTES_IN_KILOBYTE

        action(gigabytes, megabytes.toInt(), kilobytes.toInt(), remainingBytes.toInt())
    }

    override fun compareTo(other: Size): Int {
        return bytes.compareTo(other.bytes)
    }

    override fun toString(): String = when {
        isAtLeastGigabyte -> formattedGigabytesWithUnit
        isAtLeastMegabyte -> formattedMegabytesWithUnit
        isAtLeastKilobyte -> formattedKilobytesWithUnit
        else -> formattedBytesWithUnit
    }

    private val formattedGigabytesWithUnit: String
        get() = format(inGigabytes, SizeUnit.GIGABYTES)

    private val formattedMegabytesWithUnit: String
        get() = format(inMegabytes, SizeUnit.MEGABYTES)

    private val formattedKilobytesWithUnit: String
        get() = format(inKilobytes, SizeUnit.KILOBYTES)

    private val formattedBytesWithUnit: String
        get() = format(inBytes.toFloat(), SizeUnit.BYTES)

    private fun format(value: Float, unit: SizeUnit): String {
        val valueString = decimalFormatter.format(value)
        val unitString = when (unit) {
            SizeUnit.BYTES -> STRING_BYTE
            SizeUnit.KILOBYTES -> STRING_KILOBYTE
            SizeUnit.MEGABYTES -> STRING_MEGABYTE
            SizeUnit.GIGABYTES -> STRING_GIGABYTE
        }
        return STRING_FORMAT.format(valueString, unitString)
    }

    private val decimalFormatter: DecimalFormat
        get() = DecimalFormat("#.##")

    companion object {

        val Int.bytes: Size get() = toSize(SizeUnit.BYTES)

        val Long.bytes: Size get() = toSize(SizeUnit.BYTES)

        val Int.kilobytes: Size get() = toSize(SizeUnit.KILOBYTES)

        val Long.kilobytes: Size get() = toSize(SizeUnit.KILOBYTES)

        val Int.megabytes: Size get() = toSize(SizeUnit.MEGABYTES)

        val Long.megabytes: Size get() = toSize(SizeUnit.MEGABYTES)

        val Int.gigabytes: Size get() = toSize(SizeUnit.GIGABYTES)

        val Long.gigabytes: Size get() = toSize(SizeUnit.GIGABYTES)

        private fun Int.toSize(unit: SizeUnit): Size = (this.toLong()).toSize(unit)

        private fun Long.toSize(unit: SizeUnit): Size {
            val bytesInUnit = bytesInUnit(unit)
            val bytes = this * bytesInUnit
            return Size(bytes)
        }

        private fun bytesInUnit(unit: SizeUnit): Long = when (unit) {
            SizeUnit.BYTES -> ONE_BYTE
            SizeUnit.KILOBYTES -> BYTES_IN_KILOBYTE
            SizeUnit.MEGABYTES -> BYTES_IN_MEGABYTE
            SizeUnit.GIGABYTES -> BYTES_IN_GIGABYTE
        }

        private const val ONE_BYTE = 1L
        private const val BYTES_IN_KILOBYTE = 1_000L
        private const val BYTES_IN_MEGABYTE = 1_000_000L
        private const val BYTES_IN_GIGABYTE = 1_000_000_000L

        private const val STRING_BYTE = "B"
        private const val STRING_KILOBYTE = "kB"
        private const val STRING_MEGABYTE = "MB"
        private const val STRING_GIGABYTE = "GB"
        private const val STRING_FORMAT = "%1\$s %2\$s"

    }

}

private enum class SizeUnit {
    BYTES,
    KILOBYTES,
    MEGABYTES,
    GIGABYTES,
}
