package ru.izhxx.gimodule

import java.util.regex.Pattern

//Clear hook class
internal const val DEFAULT_SERVER: String = "https://ps.yuuki.me"

internal val urlRegex = Regex("https?://[a-zA-Z./?@=+&\\d#]{0,256}$")
internal val ipRegex = Regex("https?://([a-zA-Z\\d.]{2,256}):(\\d{1,5})$")

internal val domains = Pattern.compile("http(s|)://.*?\\.(hoyoverse|mihoyo|yuanshen|mob|starrails)\\.com")
internal val domainsList = arrayListOf(
    // More Domain & log
    "globaldp-prod-os01.starrails.com",
    "prod-official-eur-dp01.starrails.com",
    "prod-official-usa-dp01.starrails.com",
    "prod-official-asia-dp01.starrails.com",
    "minor-api-os.hoyoverse.com",
    "sdk-os-static.hoyoverse.com",
    "hkrpg-sdk-os-static.hoyoverse.com",
    "sdk-os-static.hoyoverse.com",
    "webstatic.hoyoverse.com",
    "sdk-os-static.hoyoverse.com",
    "sg-hkrpg-api.hoyoverse.com",
    "log-upload-os.hoyoverse.com",
    "sg-public-data-api.hoyoverse.com",
    "sdk-common-static.hoyoverse.com",
    "overseauspider.yuanshen.com:8888",
    // CN
    "dispatchcnglobal.yuanshen.com",
    "gameapi-account.mihoyo.com",
    "hk4e-sdk-s.mihoyo.com",
    "log-upload.mihoyo.com",
    "minor-api.mihoyo.com",
    "public-data-api.mihoyo.com",
    "sdk-static.mihoyo.com",
    "webstatic.mihoyo.com",
    "user.mihoyo.com",
    // Global
    "dispatchosglobal.yuanshen.com",
    "api-account-os.hoyoverse.com",
    "hk4e-sdk-os-s.hoyoverse.com",
    "hk4e-sdk-os-static.hoyoverse.com",
    "hk4e-sdk-os.hoyoverse.com",
    "log-upload-os.hoyoverse.com",
    "minor-api-os.hoyoverse.com",
    "sdk-os-static.hoyoverse.com",
    "sg-public-data-api.hoyoverse.com",
    "webstatic.hoyoverse.com",
    // List Server
    "osasiadispatch.yuanshen.com",
    "oseurodispatch.yuanshen.com",
    "osusadispatch.yuanshen.com"
)