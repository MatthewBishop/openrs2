package org.openrs2.util.io

import java.io.OutputStream

public fun OutputStream.writeInt(v: Int) {
    write(0xFF and v)
    write(0xFF and (v shr 8))
    write(0xFF and (v shr 16))
    write(0xFF and (v shr 24))
}
