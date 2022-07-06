package com.greencom.android.podcasts2.utils

import com.google.common.truth.Truth.assertThat
import com.greencom.android.podcasts2.utils.Size.Companion.bits
import com.greencom.android.podcasts2.utils.Size.Companion.bytes
import com.greencom.android.podcasts2.utils.Size.Companion.gigabits
import com.greencom.android.podcasts2.utils.Size.Companion.gigabytes
import com.greencom.android.podcasts2.utils.Size.Companion.kilobits
import com.greencom.android.podcasts2.utils.Size.Companion.kilobytes
import com.greencom.android.podcasts2.utils.Size.Companion.megabits
import com.greencom.android.podcasts2.utils.Size.Companion.megabytes
import org.junit.Test
import java.text.DecimalFormat

class SizeTest {

    @Test
    fun init_notNegativeValue_returnsSize() {
        val values = listOf(0, 1, 100, 1_000_000)

        val sizes = values.map { Size.of(it, SizeUnit.BYTES) }

        assertThat(sizes.size).isEqualTo(values.size)
    }

    @Test(expected = IllegalStateException::class)
    fun init_negativeValue_throwsException() {
        val negativeValue = -10
        Size.of(negativeValue, SizeUnit.BYTES)
    }

    @Test
    fun of_bits_returnsCorrectSize() {
        val bits = 16

        val sizeBits = Size.of(bits, SizeUnit.BITS)

        val sizeBitsInBits = sizeBits.inUnit(SizeUnit.BITS)
        assertThat(sizeBitsInBits).isEqualTo(bits.toFloat())
    }

    @Test
    fun of_kilobits_returnsCorrectSize() {
        val kilobits = 1
        val bits = kilobits * 1000

        val size = Size.of(kilobits, SizeUnit.KILOBITS)

        val sizeInKilobits = size.inUnit(SizeUnit.KILOBITS)
        val sizeInBits = size.inUnit(SizeUnit.BITS)
        assertThat(sizeInKilobits).isEqualTo(kilobits.toFloat())
        assertThat(sizeInBits).isEqualTo(bits.toFloat())
    }

    @Test
    fun of_megabits_returnsCorrectSize() {
        val megabits = 1
        val bits = megabits * 1000 * 1000

        val size = Size.of(megabits, SizeUnit.MEGABITS)

        val sizeInMegabits = size.inUnit(SizeUnit.MEGABITS)
        val sizeInBits = size.inUnit(SizeUnit.BITS)
        assertThat(sizeInMegabits).isEqualTo(megabits.toFloat())
        assertThat(sizeInBits).isEqualTo(bits.toFloat())
    }

    @Test
    fun of_gigabits_returnsCorrectSize() {
        val gigabits = 1L
        val bits = gigabits * 1000 * 1000 * 1000

        val size = Size.of(gigabits, SizeUnit.GIGABITS)

        val sizeInGigabits = size.inUnit(SizeUnit.GIGABITS)
        val sizeInBits = size.inUnit(SizeUnit.BITS)
        assertThat(sizeInGigabits).isEqualTo(gigabits.toFloat())
        assertThat(sizeInBits).isEqualTo(bits.toFloat())
    }

    @Test
    fun of_terabits_returnsCorrectSize() {
        val terabits = 1L
        val bits = terabits * 1000 * 1000 * 1000 * 1000

        val size = Size.of(terabits, SizeUnit.TERABITS)

        val sizeInTerabits = size.inUnit(SizeUnit.TERABITS)
        val sizeInBits = size.inUnit(SizeUnit.BITS)
        assertThat(sizeInTerabits).isEqualTo(terabits.toFloat())
        assertThat(sizeInBits).isEqualTo(bits.toFloat())
    }

    @Test
    fun of_float_returnsCorrectSize() {
        val kilobits = 1.5f
        val bits = kilobits * 1000

        val size = Size.of(kilobits, SizeUnit.KILOBITS)

        val sizeInBits = size.inUnit(SizeUnit.BITS)
        assertThat(sizeInBits).isEqualTo(bits)
    }

    @Test
    fun of_double_returnsCorrectSize() {
        val megabits = 1.5
        val bits = megabits * 1000 * 1000

        val size = Size.of(megabits, SizeUnit.MEGABITS)

        val sizeInBits = size.inUnit(SizeUnit.BITS)
        assertThat(sizeInBits.toDouble()).isEqualTo(bits)
    }

    @Test
    fun bits_returnsCorrectSize() {
        val value = 10

        val fromInt = value.bits
        val fromLong = value.toLong().bits
        val fromFloat = value.toFloat().bits
        val fromDouble = value.toDouble().bits

        assertThat(fromInt.inUnit(SizeUnit.BITS)).isEqualTo(value.toFloat())
        assertThat(fromLong.inUnit(SizeUnit.BITS)).isEqualTo(value.toFloat())
        assertThat(fromFloat.inUnit(SizeUnit.BITS)).isEqualTo(value.toFloat())
        assertThat(fromDouble.inUnit(SizeUnit.BITS)).isEqualTo(value.toFloat())
    }

    @Test
    fun kilobits_returnsCorrectSize() {
        val value = 10

        val fromInt = value.kilobits
        val fromLong = value.toLong().kilobits
        val fromFloat = value.toFloat().kilobits
        val fromDouble = value.toDouble().kilobits

        assertThat(fromInt.inUnit(SizeUnit.KILOBITS)).isEqualTo(value.toFloat())
        assertThat(fromLong.inUnit(SizeUnit.KILOBITS)).isEqualTo(value.toFloat())
        assertThat(fromFloat.inUnit(SizeUnit.KILOBITS)).isEqualTo(value.toFloat())
        assertThat(fromDouble.inUnit(SizeUnit.KILOBITS)).isEqualTo(value.toFloat())
    }

    @Test
    fun megabits_returnsCorrectSize() {
        val value = 10

        val fromInt = value.megabits
        val fromLong = value.toLong().megabits
        val fromFloat = value.toFloat().megabits
        val fromDouble = value.toDouble().megabits

        assertThat(fromInt.inUnit(SizeUnit.MEGABITS)).isEqualTo(value.toFloat())
        assertThat(fromLong.inUnit(SizeUnit.MEGABITS)).isEqualTo(value.toFloat())
        assertThat(fromFloat.inUnit(SizeUnit.MEGABITS)).isEqualTo(value.toFloat())
        assertThat(fromDouble.inUnit(SizeUnit.MEGABITS)).isEqualTo(value.toFloat())
    }

    @Test
    fun gigabits_returnsCorrectSize() {
        val value = 10

        val fromInt = value.gigabits
        val fromLong = value.toLong().gigabits
        val fromFloat = value.toFloat().gigabits
        val fromDouble = value.toDouble().gigabits

        assertThat(fromInt.inUnit(SizeUnit.GIGABITS)).isEqualTo(value.toFloat())
        assertThat(fromLong.inUnit(SizeUnit.GIGABITS)).isEqualTo(value.toFloat())
        assertThat(fromFloat.inUnit(SizeUnit.GIGABITS)).isEqualTo(value.toFloat())
        assertThat(fromDouble.inUnit(SizeUnit.GIGABITS)).isEqualTo(value.toFloat())
    }

    @Test
    fun bytes_returnsCorrectSize() {
        val value = 10

        val fromInt = value.bytes
        val fromLong = value.toLong().bytes
        val fromFloat = value.toFloat().bytes
        val fromDouble = value.toDouble().bytes

        assertThat(fromInt.inUnit(SizeUnit.BYTES)).isEqualTo(value.toFloat())
        assertThat(fromLong.inUnit(SizeUnit.BYTES)).isEqualTo(value.toFloat())
        assertThat(fromFloat.inUnit(SizeUnit.BYTES)).isEqualTo(value.toFloat())
        assertThat(fromDouble.inUnit(SizeUnit.BYTES)).isEqualTo(value.toFloat())
    }

    @Test
    fun kilobytes_returnsCorrectSize() {
        val value = 10

        val fromInt = value.kilobytes
        val fromLong = value.toLong().kilobytes
        val fromFloat = value.toFloat().kilobytes
        val fromDouble = value.toDouble().kilobytes

        assertThat(fromInt.inUnit(SizeUnit.KILOBYTES)).isEqualTo(value.toFloat())
        assertThat(fromLong.inUnit(SizeUnit.KILOBYTES)).isEqualTo(value.toFloat())
        assertThat(fromFloat.inUnit(SizeUnit.KILOBYTES)).isEqualTo(value.toFloat())
        assertThat(fromDouble.inUnit(SizeUnit.KILOBYTES)).isEqualTo(value.toFloat())
    }

    @Test
    fun megabytes_returnsCorrectSize() {
        val value = 10

        val fromInt = value.megabytes
        val fromLong = value.toLong().megabytes
        val fromFloat = value.toFloat().megabytes
        val fromDouble = value.toDouble().megabytes

        assertThat(fromInt.inUnit(SizeUnit.MEGABYTES)).isEqualTo(value.toFloat())
        assertThat(fromLong.inUnit(SizeUnit.MEGABYTES)).isEqualTo(value.toFloat())
        assertThat(fromFloat.inUnit(SizeUnit.MEGABYTES)).isEqualTo(value.toFloat())
        assertThat(fromDouble.inUnit(SizeUnit.MEGABYTES)).isEqualTo(value.toFloat())
    }

    @Test
    fun gigabytes_returnsCorrectSize() {
        val value = 10

        val fromInt = value.gigabytes
        val fromLong = value.toLong().gigabytes
        val fromFloat = value.toFloat().gigabytes
        val fromDouble = value.toDouble().gigabytes

        assertThat(fromInt.inUnit(SizeUnit.GIGABYTES)).isEqualTo(value.toFloat())
        assertThat(fromLong.inUnit(SizeUnit.GIGABYTES)).isEqualTo(value.toFloat())
        assertThat(fromFloat.inUnit(SizeUnit.GIGABYTES)).isEqualTo(value.toFloat())
        assertThat(fromDouble.inUnit(SizeUnit.GIGABYTES)).isEqualTo(value.toFloat())
    }

    @Test
    fun compareTo() {
        val kilobit = 1.kilobits
        val megabit = 1.megabits

        assertThat(kilobit < megabit).isTrue()
        assertThat(megabit < kilobit).isFalse()
    }

    @Test
    fun equals_same_returnsTrue() {
        val kilobit1 = 1.kilobits
        val kilobit2 = 1.kilobits
        val megabit = 1.megabits

        assertThat(kilobit1 == kilobit2).isTrue()
        assertThat(kilobit1 == megabit).isFalse()
    }

    @Test
    fun plus() {
        val megabit = 1.megabits
        val kilobits = 250.kilobits
        val megabitInBits = megabit.inUnit(SizeUnit.BITS)
        val kilobitsInBits = kilobits.inUnit(SizeUnit.BITS)

        val sum = megabit + kilobits

        val sumInBits = sum.inUnit(SizeUnit.BITS)
        assertThat(sumInBits).isEqualTo(megabitInBits + kilobitsInBits)
    }

    @Test
    fun minus() {
        val megabit = 1.megabits
        val kilobits = 250.kilobits
        val megabitInBits = megabit.inUnit(SizeUnit.BITS)
        val kilobitsInBits = kilobits.inUnit(SizeUnit.BITS)

        val diff = megabit - kilobits

        val sumInBits = diff.inUnit(SizeUnit.BITS)
        assertThat(sumInBits).isEqualTo(megabitInBits - kilobitsInBits)
    }

    @Test
    fun times() {
        val megabit = 1.megabits
        val times = 10
        val inBits = megabit.inUnit(SizeUnit.BITS) * times

        val product = megabit * times

        val productInBits = product.inUnit(SizeUnit.BITS)
        assertThat(productInBits).isEqualTo(inBits)
    }

    @Test
    fun times_zero_returnSizeOfZero() {
        val megabit = 1.megabits
        val times = 0
        val inBits = megabit.inUnit(SizeUnit.BITS) * times

        val product = megabit * times

        val productInBits = product.inUnit(SizeUnit.BITS)
        assertThat(productInBits).isEqualTo(inBits)
    }

    @Test
    fun div() {
        val megabit = 1.megabits
        val times = 10
        val inBits = megabit.inUnit(SizeUnit.BITS) / times

        val result = megabit / times

        val resultInBits = result.inUnit(SizeUnit.BITS)
        assertThat(resultInBits).isEqualTo(inBits)
    }

    @Test(expected = ArithmeticException::class)
    fun div_byZero_throwsException() {
        val megabit = 1.megabits
        val times = 0

        megabit / times
    }

    @Test
    fun inUnit_bitsInBits() {
        val bits = 10
        val size = bits.bits

        val sizeInBits = size.inUnit(SizeUnit.BITS)

        assertThat(sizeInBits).isEqualTo(bits.toFloat())
    }

    @Test
    fun inUnit_megabitsInBits() {
        val megabits = 10
        val bits = megabits * 1000 * 1000
        val size = megabits.megabits

        val sizeInBits = size.inUnit(SizeUnit.BITS)

        assertThat(sizeInBits).isEqualTo(bits.toFloat())
    }

    @Test
    fun inUnit_bitsInKilobits() {
        val bits = 1000
        val kilobits = bits.toFloat() / 1000
        val size = bits.bits

        val sizeInKilobits = size.inUnit(SizeUnit.KILOBITS)

        assertThat(sizeInKilobits).isEqualTo(kilobits)
    }

    @Test
    fun inUnit_bitsInMegabits() {
        val bits = 1000
        val megabits = bits.toFloat() / 1000 / 1000
        val size = bits.bits

        val sizeInMegabits = size.inUnit(SizeUnit.MEGABITS)

        assertThat(sizeInMegabits).isEqualTo(megabits)
    }

    @Test
    fun inUnit_bitsInGigabits() {
        val bits = 1000
        val gigabits = bits.toFloat() / 1000 / 1000 / 1000
        val size = bits.bits

        val sizeInGigabits = size.inUnit(SizeUnit.GIGABITS)

        assertThat(sizeInGigabits).isEqualTo(gigabits)
    }

    @Test
    fun inUnit_bitsInTerabits() {
        val bits = 1_000_000_000
        val terabits = bits.toFloat() / 1000 / 1000 / 1000 / 1000
        val size = bits.bits

        val sizeInTerabits = size.inUnit(SizeUnit.TERABITS)

        assertThat(sizeInTerabits).isEqualTo(terabits)
    }

    @Test
    fun inUnit_bytesInBytes() {
        val bytes = 1000
        val size = bytes.bytes

        val sizeInBytes = size.inUnit(SizeUnit.BYTES)

        assertThat(sizeInBytes).isEqualTo(bytes.toFloat())
    }

    @Test
    fun inUnit_bytesInKilobytes() {
        val bytes = 1000
        val kilobytes = bytes.toFloat() / 1000
        val size = bytes.bytes

        val sizeInKilobytes = size.inUnit(SizeUnit.KILOBYTES)

        assertThat(sizeInKilobytes).isEqualTo(kilobytes)
    }

    @Test
    fun inUnit_bytesInMegabytes() {
        val bytes = 1000
        val megabytes = bytes.toFloat() / 1000 / 1000
        val size = bytes.bytes

        val sizeInMegabytes = size.inUnit(SizeUnit.MEGABYTES)

        assertThat(sizeInMegabytes).isEqualTo(megabytes)
    }

    @Test
    fun inUnit_bytesInGigabytes() {
        val bytes = 1_000_000
        val gigabytes = bytes.toFloat() / 1000 / 1000 / 1000
        val size = bytes.bytes

        val sizeInGigabytes = size.inUnit(SizeUnit.GIGABYTES)

        assertThat(sizeInGigabytes).isEqualTo(gigabytes)
    }

    @Test
    fun inUnit_bytesInTerabytes() {
        val bytes = 5_123_456_789_012
        val gigabytes = bytes.toFloat() / 1000 / 1000 / 1000 / 1000
        val size = bytes.bytes

        val sizeInTerabytes = size.inUnit(SizeUnit.TERABYTES)

        assertThat(sizeInTerabytes).isEqualTo(gigabytes)
    }

    @Test
    fun inUnit_megabytesInBits() {
        val megabytes = 123
        val bits = megabytes * 1000 * 1000 * 8
        val size = megabytes.megabytes

        val sizeInBits = size.inUnit(SizeUnit.BITS)

        assertThat(sizeInBits).isEqualTo(bits.toFloat())
    }

    @Test
    fun inUnit_megabitsInBytes() {
        val megabits = 123
        val bytes = megabits * 1000 * 1000 / 8
        val size = megabits.megabits

        val sizeInBytes = size.inUnit(SizeUnit.BYTES)

        assertThat(sizeInBytes).isEqualTo(bytes.toFloat())
    }

    @Test
    fun format_withDefaultValueFormat() {
        val decimalFormat = DecimalFormat("#.##")
        val bits = 1_234
        val kilobits = bits.toFloat() / 1000
        val size = bits.bits

        val formatted = size.format(SizeUnit.KILOBITS)

        assertThat(formatted).isEqualTo(decimalFormat.format(kilobits))
    }

    @Test
    fun format_withCustomValueFormat() {
        val decimalFormat = DecimalFormat("#.###")
        val bits = 1_234_456
        val megabits = bits.toFloat() / 1000 / 1000
        val size = bits.bits

        val formatted = size.format(SizeUnit.MEGABITS, decimalFormat)

        assertThat(formatted).isEqualTo(decimalFormat.format(megabits))
    }

    @Test
    fun formatWithUnitSymbol_withDefaultUnitSymbolFormat() {
        val decimalFormat = DecimalFormat("#.##")
        val bits = 1_234_456
        val megabits = decimalFormat.format(bits.toFloat() / 1000 / 1000)
        val megabitsWithSymbol = "$megabits ${SizeUnit.MEGABITS.symbol}"
        val size = bits.bits

        val formatted = size.formatWithUnitSymbol(SizeUnit.MEGABITS)

        assertThat(formatted).isEqualTo(megabitsWithSymbol)
    }

    @Test
    fun formatWithUnitSymbol_withCustomUnitSymbolFormat() {
        val decimalFormat = DecimalFormat("#.##")
        val unitSymbolFormat = "%2\$s %1\$s"
        val bits = 1_234_456
        val megabits = decimalFormat.format(bits.toFloat() / 1000 / 1000)
        val megabitsWithSymbol = unitSymbolFormat.format(megabits, SizeUnit.MEGABITS.symbol)
        val size = bits.bits

        val formatted =
            size.formatWithUnitSymbol(SizeUnit.MEGABITS, unitSymbolFormat = unitSymbolFormat).also { println(it) }

        assertThat(formatted).isEqualTo(megabitsWithSymbol)
    }

}
