package com.greencom.android.podcasts2.utils

import com.greencom.android.podcasts2.utils.Size.Companion.bytes
import com.greencom.android.podcasts2.utils.Size.Companion.gigabytes
import com.greencom.android.podcasts2.utils.Size.Companion.kilobytes
import com.greencom.android.podcasts2.utils.Size.Companion.megabytes
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.text.DecimalFormat

class SizeTest {

    lateinit var formatter: DecimalFormat

    @Before
    fun before() {
        formatter = DecimalFormat("#.##")
    }

    @Test
    fun bytesInt() {
        val bytesExpected = 100
        val size = bytesExpected.bytes

        val bytesActual = size.inBytes

        Assert.assertEquals(bytesExpected.toLong(), bytesActual)
    }

    @Test
    fun bytesLong() {
        val bytesExpected = 100L
        val size = bytesExpected.bytes

        val bytesActual = size.inBytes

        Assert.assertEquals(bytesExpected, bytesActual)
    }

    @Test
    fun kilobytesInt() {
        val kilobytesExpected = 100
        val size = kilobytesExpected.kilobytes

        val kilobytesActual = size.inWholeKilobytes

        Assert.assertEquals(kilobytesExpected.toLong(), kilobytesActual)
    }

    @Test
    fun kilobytesLong() {
        val kilobytesExpected = 100L
        val size = kilobytesExpected.kilobytes

        val kilobytesActual = size.inWholeKilobytes

        Assert.assertEquals(kilobytesExpected, kilobytesActual)
    }

    @Test
    fun megabytesInt() {
        val megabytesExpected = 100
        val size = megabytesExpected.megabytes

        val megabytesActual = size.inWholeMegabytes

        Assert.assertEquals(megabytesExpected.toLong(), megabytesActual)
    }

    @Test
    fun megabytesLong() {
        val megabytesExpected = 100L
        val size = megabytesExpected.megabytes

        val megabytesActual = size.inWholeMegabytes

        Assert.assertEquals(megabytesExpected, megabytesActual)
    }

    @Test
    fun gigabytesInt() {
        val gigabytesExpected = 100
        val size = gigabytesExpected.gigabytes

        val gigabytesActual = size.inWholeGigabytes

        Assert.assertEquals(gigabytesExpected.toLong(), gigabytesActual)
    }

    @Test
    fun gigabytesLong() {
        val gigabytesExpected = 100L
        val size = gigabytesExpected.gigabytes

        val gigabytesActual = size.inWholeGigabytes

        Assert.assertEquals(gigabytesExpected, gigabytesActual)
    }

    @Test
    fun inBytes() {
        val bytesExpected = 1280
        val size = bytesExpected.bytes

        val bytesActual = size.inBytes

        Assert.assertEquals(bytesExpected.toLong(), bytesActual)
    }

    @Test
    fun inKilobytes() {
        val bytes = 1500
        val kilobytesExpected = bytes / 1_000F
        val size = bytes.bytes

        val kilobytesActual = size.inKilobytes

        Assert.assertEquals(kilobytesExpected, kilobytesActual)
    }

    @Test
    fun inMegabytes() {
        val kilobytes = 1500
        val megabytesExpected = kilobytes / 1_000F
        val size = kilobytes.kilobytes

        val megabytesActual = size.inMegabytes

        Assert.assertEquals(megabytesExpected, megabytesActual)
    }

    @Test
    fun inGigabytes() {
        val megabytes = 1500
        val gigabytesExpected = megabytes / 1_000F
        val size = megabytes.megabytes

        val gigabytesActual = size.inGigabytes

        Assert.assertEquals(gigabytesExpected, gigabytesActual)
    }

    @Test
    fun inWholeKilobytes() {
        val bytes = 1500
        val kilobytesExpected = bytes / 1_000
        val size = bytes.bytes

        val kilobytesActual = size.inWholeKilobytes

        Assert.assertEquals(kilobytesExpected.toLong(), kilobytesActual)
    }

    @Test
    fun inWholeMegabytes() {
        val kilobytes = 1500
        val megabytesExpected = kilobytes / 1_000
        val size = kilobytes.kilobytes

        val megabytesActual = size.inWholeMegabytes

        Assert.assertEquals(megabytesExpected.toLong(), megabytesActual)
    }

    @Test
    fun inWholeGigabytes() {
        val megabytes = 1500
        val gigabytesExpected = megabytes / 1_000
        val size = megabytes.megabytes

        val gigabytesActual = size.inWholeGigabytes

        Assert.assertEquals(gigabytesExpected.toLong(), gigabytesActual)
    }

    @Test
    fun plus() {
        val megabytes = 5
        val kilobytes = 2_000
        val sumExpected = megabytes + kilobytes / 1_000
        val size = megabytes.megabytes + kilobytes.kilobytes

        val sumActual = size.inWholeMegabytes

        Assert.assertEquals(sumExpected.toLong(), sumActual)
    }

    @Test
    fun minus() {
        val megabytes = 5
        val kilobytes = 2_000
        val sumExpected = megabytes - kilobytes / 1_000
        val size = megabytes.megabytes - kilobytes.kilobytes

        val sumActual = size.inWholeMegabytes

        Assert.assertEquals(sumExpected.toLong(), sumActual)
    }

    @Test
    fun times() {
        val megabytes = 10
        val times = 3
        val productExpected = megabytes * times
        val size = megabytes.megabytes * times

        val productActual = size.inWholeMegabytes

        Assert.assertEquals(productExpected.toLong(), productActual)
    }

    @Test
    fun compareTo1() {
        val kilobytes1 = 1
        val kilobytes2 = 4
        val size1 = kilobytes1.kilobytes
        val size2 = kilobytes2.kilobytes

        Assert.assertEquals(kilobytes1 < kilobytes2, size1 < size2)
    }

    @Test
    fun compareTo2() {
        val bytes = 1_000
        val kilobyte = 1
        val bytesSize = bytes.bytes
        val kilobyteSize = kilobyte.kilobytes

        Assert.assertEquals(kilobyte * 1_000 == bytes, bytesSize == kilobyteSize)
    }

    @Test
    fun toComponents1() {
        val bytesExpected = 100
        val kilobytesExpected = 200
        val megabytesExpected = 300
        val size = megabytesExpected.megabytes +
                kilobytesExpected.kilobytes + bytesExpected.bytes

        var bytesActual = 0
        var kilobytesActual = 0
        var megabytesActual = 0L
        size.toComponents { megabytes, kilobytes, bytes ->
            megabytesActual = megabytes
            kilobytesActual = kilobytes
            bytesActual = bytes
        }

        Assert.assertEquals(megabytesExpected.toLong(), megabytesActual)
        Assert.assertEquals(kilobytesExpected, kilobytesActual)
        Assert.assertEquals(bytesExpected, bytesActual)
    }

    @Test
    fun toComponents2() {
        val bytesExpected = 100
        val kilobytesInit = 2100
        val megabytesInit = 300

        val kilobytesExpected = kilobytesInit - kilobytesInit / 1_000 * 1_000
        val megabytesExpected = megabytesInit + kilobytesInit / 1_000
        val size = megabytesInit.megabytes + kilobytesInit.kilobytes + bytesExpected.bytes

        var bytesActual = 0
        var kilobytesActual = 0
        var megabytesActual = 0L
        size.toComponents { megabytes, kilobytes, bytes ->
            megabytesActual = megabytes
            kilobytesActual = kilobytes
            bytesActual = bytes
        }

        Assert.assertEquals(megabytesExpected.toLong(), megabytesActual)
        Assert.assertEquals(kilobytesExpected, kilobytesActual)
        Assert.assertEquals(bytesExpected, bytesActual)
    }

    @Test
    fun toComponents3() {
        val bytesExpected = 100
        val kilobytesInit = 2100
        val megabytesInit = 300
        val gigabytesExpected = 1

        val kilobytesExpected = kilobytesInit - kilobytesInit / 1_000 * 1_000
        val megabytesExpected = megabytesInit + kilobytesInit / 1_000
        val size = megabytesInit.megabytes + kilobytesInit.kilobytes +
                bytesExpected.bytes + gigabytesExpected.gigabytes

        var bytesActual = 0
        var kilobytesActual = 0
        var megabytesActual = 0
        var gigabytesActual = 0L
        size.toComponents { gigabytes, megabytes, kilobytes, bytes ->
            gigabytesActual = gigabytes
            megabytesActual = megabytes
            kilobytesActual = kilobytes
            bytesActual = bytes
        }

        Assert.assertEquals(gigabytesExpected.toLong(), gigabytesActual)
        Assert.assertEquals(megabytesExpected, megabytesActual)
        Assert.assertEquals(kilobytesExpected, kilobytesActual)
        Assert.assertEquals(bytesExpected, bytesActual)
    }

    @Test
    fun toString1() {
        val gigabytes = 3
        val megabytes = 275
        val size = gigabytes.gigabytes + megabytes.megabytes

        val total = gigabytes + megabytes / 1_000F
        val expected = formatter.format(total) + " GB"

        Assert.assertEquals(expected, size.toString())
    }

    @Test
    fun toString2() {
        val megabytes = 3
        val kilobytes = 275
        val size = megabytes.megabytes + kilobytes.kilobytes

        val total = megabytes + kilobytes / 1_000F
        val expected = formatter.format(total) + " MB"

        Assert.assertEquals(expected, size.toString())
    }

    @Test
    fun toString3() {
        val kilobytes = 3
        val bytes = 275
        val size = kilobytes.kilobytes + bytes.bytes

        val total = kilobytes + bytes / 1_000F
        val expected = formatter.format(total) + " kB"

        Assert.assertEquals(expected, size.toString())
    }

    @Test
    fun toString4() {
        val bytes = 275
        val size = bytes.bytes

        val total = bytes
        val expected = formatter.format(total) + " B"

        Assert.assertEquals(expected, size.toString())
    }

    @Test
    fun toString5() {
        val bytes = 1275
        val size = bytes.bytes

        val total = bytes / 1_000F
        val expected = formatter.format(total) + " kB"

        Assert.assertEquals(expected, size.toString())
    }

}
