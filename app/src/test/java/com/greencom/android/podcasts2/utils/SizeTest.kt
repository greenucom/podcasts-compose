package com.greencom.android.podcasts2.utils

import com.greencom.android.podcasts2.utils.Size.Companion.bits
import com.greencom.android.podcasts2.utils.Size.Companion.bytes
import com.greencom.android.podcasts2.utils.Size.Companion.gigabytes
import com.greencom.android.podcasts2.utils.Size.Companion.kilobytes
import com.greencom.android.podcasts2.utils.Size.Companion.megabytes
import org.junit.Assert
import org.junit.Test
import java.math.BigInteger

class SizeTest {

    @Test
    fun bits() {
        val bitsInitial = 1024
        val size = bitsInitial.bits

        val bitsFinal = size.inBitsLong

        Assert.assertEquals(bitsInitial.toLong(), bitsFinal)
    }

    @Test
    fun bytes() {
        val bytesInitial = 1024
        val size = bytesInitial.bytes

        val bytesFinal = size.inWholeBytesLong

        Assert.assertEquals(bytesInitial.toLong(), bytesFinal)
    }

    @Test
    fun kilobytes() {
        val kilobytesInitial = 1024
        val size = kilobytesInitial.kilobytes

        val kilobytesFinal = size.inWholeKilobytesLong

        Assert.assertEquals(kilobytesInitial.toLong(), kilobytesFinal)
    }

    @Test
    fun megabytes() {
        val megabytesInitial = 1024
        val size = megabytesInitial.megabytes

        val megabytesFinal = size.inWholeMegabytes

        Assert.assertEquals(megabytesInitial.toLong(), megabytesFinal)
    }

    @Test
    fun gigabytes() {
        val gigabytesInitial = 1024
        val size = gigabytesInitial.gigabytes

        val gigabytesFinal = size.inWholeGigabytes

        Assert.assertEquals(gigabytesInitial.toLong(), gigabytesFinal)
    }

    @Test
    fun bitsLong() {
        val bitsInitial = 1024L
        val size = bitsInitial.bits

        val bitsFinal = size.inBitsLong

        Assert.assertEquals(bitsInitial, bitsFinal)
    }

    @Test
    fun bytesLong() {
        val bytesInitial = 1024L
        val size = bytesInitial.bytes

        val bytesFinal = size.inWholeBytesLong

        Assert.assertEquals(bytesInitial, bytesFinal)
    }

    @Test
    fun kilobytesLong() {
        val kilobytesInitial = 1024L
        val size = kilobytesInitial.kilobytes

        val kilobytesFinal = size.inWholeKilobytesLong

        Assert.assertEquals(kilobytesInitial, kilobytesFinal)
    }

    @Test
    fun megabytesLong() {
        val megabytesInitial = 1024L
        val size = megabytesInitial.megabytes

        val megabytesFinal = size.inWholeMegabytes

        Assert.assertEquals(megabytesInitial, megabytesFinal)
    }

    @Test
    fun gigabytesLong() {
        val gigabytesInitial = 1024L
        val size = gigabytesInitial.gigabytes

        val gigabytesFinal = size.inWholeGigabytes

        Assert.assertEquals(gigabytesInitial, gigabytesFinal)
    }

    @Test
    fun inBitsLong() {
        val bitsInitial = 128
        val size = bitsInitial.bits

        val bitsFinal = size.inBitsLong

        Assert.assertEquals(bitsInitial.toLong(), bitsFinal)
    }

    @Test
    fun inBits() {
        val bitsInitial = 128L
        val size = bitsInitial.bits

        val bitsFinal = size.inBits

        Assert.assertEquals(BigInteger.valueOf(bitsInitial), bitsFinal)
    }

    @Test
    fun inWholeBytesLong() {
        val bytesInitial = 128
        val size = bytesInitial.bytes

        val bytesFinal = size.inWholeBytesLong

        Assert.assertEquals(bytesInitial.toLong(), bytesFinal)
    }

    @Test
    fun inWholeBytes() {
        val bytesInitial = 128L
        val size = bytesInitial.bytes

        val bytesFinal = size.inWholeBytes

        Assert.assertEquals(BigInteger.valueOf(bytesInitial), bytesFinal)
    }

    @Test
    fun inWholeKilobytesLong() {
        val kilobytesInitial = 128
        val size = kilobytesInitial.kilobytes

        val kilobytesFinal = size.inWholeKilobytesLong

        Assert.assertEquals(kilobytesInitial.toLong(), kilobytesFinal)
    }

    @Test
    fun inWholeKilobytes() {
        val kilobytesInitial = 128L
        val size = kilobytesInitial.kilobytes

        val kilobytesFinal = size.inWholeKilobytes

        Assert.assertEquals(BigInteger.valueOf(kilobytesInitial), kilobytesFinal)
    }

    @Test
    fun inWholeMegabytes() {
        val megabytesInitial = 128L
        val size = megabytesInitial.megabytes

        val megabytesFinal = size.inWholeMegabytes

        Assert.assertEquals(megabytesInitial, megabytesFinal)
    }

    @Test
    fun inWholeGigabytes() {
        val gigabytesInitial = 128L
        val size = gigabytesInitial.gigabytes

        val gigabytesFinal = size.inWholeGigabytes

        Assert.assertEquals(gigabytesInitial, gigabytesFinal)
    }

    @Test
    fun plusBitsBits() {
        val bits1 = 16L
        val bits2 = 32L
        val sum = bits1 + bits2
        val size = bits1.bits + bits2.bits

        val bitsFinal = size.inBitsLong

        Assert.assertEquals(sum, bitsFinal)
    }

    @Test
    fun plusBytesBits() {
        val bytes = 1L
        val bits = 8L
        val sum = (bytes * 8) + bits
        val size = bytes.bytes + bits.bits

        val bitsFinal = size.inBitsLong

        Assert.assertEquals(sum, bitsFinal)
    }

    @Test
    fun plusKilobytesBytes() {
        val kilobytes = 100
        val bytes = 2_000L
        val sum = kilobytes + bytes / 1_000
        val size = kilobytes.kilobytes + bytes.bytes

        val kilobytesFinal = size.inWholeKilobytesLong

        Assert.assertEquals(sum, kilobytesFinal)
    }

    @Test
    fun plusMegabytesKilobytes() {
        val megabytes = 100
        val kilobytes = 2_000L
        val sum = megabytes + kilobytes / 1_000
        val size = megabytes.megabytes + kilobytes.kilobytes

        val megabytesFinal = size.inWholeMegabytes

        Assert.assertEquals(sum, megabytesFinal)
    }

    @Test
    fun plusGigabytesMegabytes() {
        val gigabytes = 100
        val megabytes = 2_000L
        val sum = gigabytes + megabytes / 1_000
        val size = gigabytes.gigabytes + megabytes.megabytes

        val gigabytesFinal = size.inWholeGigabytes

        Assert.assertEquals(sum, gigabytesFinal)
    }

    @Test
    fun compareToEqual() {
        val kilobytes1 = 16
        val kilobytes2 = 16
        val size1 = kilobytes1.kilobytes
        val size2 = kilobytes2.kilobytes

        Assert.assertEquals(kilobytes1 == kilobytes2, size1 == size2)
    }

    @Test
    fun compareToMore() {
        val kilobytes1 = 100
        val kilobytes2 = 50
        val size1 = kilobytes1.kilobytes
        val size2 = kilobytes2.kilobytes

        Assert.assertEquals(kilobytes1 > kilobytes2, size1 > size2)
    }

}
