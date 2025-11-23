package org.openrs2.compress.lzma

import org.openrs2.util.io.readAndCheckByte
import org.openrs2.util.io.readInt
import org.openrs2.util.io.writeInt
import org.tukaani.xz.LZMA2Options
import org.tukaani.xz.LZMAInputStream
import org.tukaani.xz.LZMAOutputStream
import java.io.InputStream
import java.io.OutputStream

public object Lzma {
    public val BEST_SPEED: LZMA2Options = LZMA2Options(LZMA2Options.PRESET_MIN)
    public val DEFAULT_COMPRESSION: LZMA2Options = LZMA2Options(LZMA2Options.PRESET_DEFAULT)
    public val BEST_COMPRESSION: LZMA2Options = LZMA2Options(LZMA2Options.PRESET_MAX)

    public fun createHeaderlessInputStream(input: InputStream, length: Long): InputStream {
        val properties = input.readAndCheckByte()
        val dictionarySize = input.readInt()

        return LZMAInputStream(input, length, properties, dictionarySize)
    }

    public fun createHeaderlessOutputStream(output: OutputStream, options: LZMA2Options): OutputStream {
        output.write((options.pb * 5 + options.lp) * 9 + options.lc)
        output.writeInt(options.dictSize)

        return LZMAOutputStream(output, options, false)
    }
}
