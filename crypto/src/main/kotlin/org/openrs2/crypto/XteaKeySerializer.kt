package org.openrs2.crypto

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer

public object XteaKeySerializer : StdSerializer<XteaKey>(XteaKey::class.java) {
    override fun serialize(value: XteaKey, gen: JsonGenerator, provider: SerializerProvider) {
        gen.writeStartArray()
        gen.writeNumber(value.k0)
        gen.writeNumber(value.k1)
        gen.writeNumber(value.k2)
        gen.writeNumber(value.k3)
        gen.writeEndArray()
    }
}
