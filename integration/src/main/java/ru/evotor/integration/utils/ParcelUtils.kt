package ru.evotor.integration.utils

import android.os.Parcel
import android.os.Parcelable
import java.math.BigDecimal

interface KParcelable : Parcelable {
    override fun describeContents(): Int = 0
}

inline fun <reified T> parcelableCreator(crossinline create: (Parcel) -> T) =
    object : Parcelable.Creator<T> {
        override fun createFromParcel(parcel: Parcel) = create(parcel)
        override fun newArray(size: Int) = arrayOfNulls<T>(size)
    }

fun Parcel.readBool(): Boolean =
    readByte() != 0.toByte()

fun Parcel.writeBool(value: Boolean) {
    writeByte(if (value) 1 else 0)
}

inline fun <reified T : Enum<T>> Parcel.readEnum(): T? =
    readString()?.let { enumValueOf<T>(it) }

fun <T : Enum<T>> Parcel.writeEnum(value: T?) {
    writeString(value?.name)
}

fun Parcel.readBigDecimal(): BigDecimal? =
    readSerializable() as? BigDecimal

fun Parcel.writeBigDecimal(value: BigDecimal?) {
    writeSerializable(value)
}

fun Parcel.readStringMap(): Map<String, String>? {
    val size = readInt()
    val map = HashMap<String, String>()

    for (i in 1..size) {
        val key = readString().toString()
        val value = readString().toString()
        map[key] = value
    }
    return map.ifEmpty { null }
}

fun Parcel.writeStringMap(map: Map<String, String>?) {
    writeInt(map?.size ?: 0)
    map?.let {
        for ((key, value) in it) {
            writeString(key)
            writeString(value)
        }
    }
}

inline fun Parcel.readVersioningData(version: Int, reader: (Int) -> Unit) {
    val dataVersion = readInt()
    val dataSize = readInt()
    if (dataSize == 0) {
        return
    }
    val startDataPosition = dataPosition()
    reader(dataVersion)
    if (dataVersion > version) {
        setDataPosition(startDataPosition + dataSize)
    }
}

inline fun Parcel.writeVersioningData(version: Int, writer: () -> Unit) {
    writeInt(version)
    val dataSizePosition = dataPosition()
    writeInt(0)
    val startDataPosition = dataPosition()
    writer()
    val endDataPosition = dataPosition()
    val dataSize = endDataPosition - startDataPosition
    setDataPosition(dataSizePosition)
    writeInt(dataSize)
    setDataPosition(endDataPosition)
}