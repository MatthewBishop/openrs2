package org.openrs2.util.io

import java.io.DataInputStream
import java.io.EOFException
import java.io.IOException
import java.io.InputStream
import java.util.Arrays

public fun InputStream.contentEquals(other: InputStream): Boolean {
    val buf1 = ByteArray(4096)
    val buf2 = ByteArray(4096)

    while (true) {
        val n1 = read(buf1, 0, buf1.size)
        if (n1 == -1) {
            return other.read() == -1
        }

        var off = 0
        var remaining = n1
        while (remaining > 0) {
            val n2 = other.read(buf2, off, remaining)
            if (n2 == -1) {
                return false
            }

            off += n2
            remaining -= n2
        }

        if (!Arrays.equals(buf1, 0, n1, buf2, 0, n1)) {
            return false
        }
    }
}

/**
 * Reads a byte from the input stream checking that the end of file (EOF) has not been
 * encountered.
 *
 * @return byte read from input
 * @throws IOException if an error is encountered while reading
 * @throws EOFException if the end of file (EOF) is encountered.
 */
@Throws(IOException::class, EOFException::class)
public fun InputStream.readAndCheckByte(): Byte {
    val b1: Int = read()

    if (-1 == b1) {
        throw EOFException()
    }

    return b1.toByte()
}

/**
 * Reads an integer as specified by [DataInputStream.readInt], except using little-endian
 * byte order.
 *
 * @return the next four bytes of the input stream, interpreted as an `int` in little-endian
 * byte order
 * @throws IOException if an I/O error occurs
 */
@Throws(IOException::class)
public fun InputStream.readInt(): Int {
    val b1: Byte = readAndCheckByte()
    val b2: Byte = readAndCheckByte()
    val b3: Byte = readAndCheckByte()
    val b4: Byte = readAndCheckByte()

    return b4.toInt() shl 24 or
        ((b3.toInt() and 0xFF) shl 16) or
        ((b2.toInt() and 0xFF) shl 8) or
        (b1.toInt() and 0xFF)
}
