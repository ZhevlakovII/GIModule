package ru.izhxx.editor.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
@SerialName("server")
internal class ServerDto(
    @SerialName("server_name") val serverName: String?,
    @SerialName("server_url") val serverUrl: String?
) : Parcelable