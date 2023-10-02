# Connecting with Android

## Preface

In fact, everything is simple here, since I was able to make an adequate server switch (through
reading
the file). And in general, the guide can end with the fact that you just need to install the APK.

For the curious, there will be a guide on how to collect everything yourself.

## Navigation

1. [Getting APK for patch](#getting-the-apk-for-the-patch)
2. [Tools for patching](#patching-tools)
3. [Creating a Patch](#creating-a-patch)
    1. [Self-creating a patch](#instructions-for-creating-a-patch-yourself)
        1. [Creating an APK](#creating-an-apk)
        2. [Using debugging](#using-debugging)
4. [Patching](#patching)
5. [Launch](#launch)

### Getting the APK for the patch

We need to get the source apk from this launcher. There are two ways:

1. From this repository, if it is possible to download it
2. Download the Genshin Impact 4.0 client (I don't know where to look)
3. Download [YuukiPS launcher for Android](https://ps.yuuki.me/game/genshin-impact ). Downloaded apk
   we unpack it as a zip archive and there will be a source apk along the assets/lspatch/modules/
   path

### Patching tools

Found it? Great. Now we need to make our own patch. To do this, we
download [Spatch](https://github.com/LSPosed/LSPatch )
and [Shizuku](https://github.com/RikkaApps/Shizuku ). You can download them in the Releases section.
Downloaded, installed on a smartphone (or assembled themselves), cool.

### Creating a patch

There are two ways to create a patch:

1. You are using a ready-made patch
2. Create the patch yourself

The difference between these options is that in the first option you just
download [patch] (/docs/materials/books/GIModule.apk) and go
to [Patching] (#patching), and in the second you collect everything yourself.

#### Instructions for creating a patch yourself

You copy the current repository to your computer, you can use it as an archive, or you can pull
it up through GIT (for experienced users). Downloaded, unpacked and what should we do next? There
are two options

1. We are trying to assemble everything without Android Studio and use third-party guides (not the
   best option)
2. Just download Android Studio (https://developer.android.com/studio ), install and open
   the unpacked archive in it

Further instructions for assembly via **Android Studio!**. There are two build options:

1. Creating an APK
2. Using USB debugging

##### Creating an APK

Open the Build menu, then Build Bundle(s) / APK(s), then Build APK(s)

![APK Build](/docs/materials/images/as_apk_build.png)

After the assembly, such a notification will appear on the bottom right. Click on locate and the
folder with
our APK opens. We transfer it to the phone and install it

![APK Locate](/docs/materials/images/as_apk_locate.png)

##### Using debugging

The second option implies that you have enabled developer mode, enabled USB debugging and
connected the phone to the PC. To install, it will be enough to select your phone and press Start (
green
triangle)

![ADB Build](/docs/materials/images/as_adb_build.png)

### Patching

We need to install the prepared APK,
and [activate Shizuku](https://doc.yuuki.me/docs/tutorial-patch/android ).
Activated, patch LSPatch as Local, not Integrated (with Local you can quickly change the patch).
Patched, reinstalled. Next, activate our module for the patched game.

### Launch

So, we patched and installed everything. But we won't be able to play that easily. There is
a file for selecting a server server.txt , which is located on the Android/obb path/**Package name,
for example com.yuuki.gi40cn
or com.miHoYo.GenshinImpact**.

When we just installed the patched APK, there will be no file, it will be created the first time you
enter
the game (but you can create it yourself).
Why is this done? So that you can download the game resources from YuukiPS for 4.0 (not yet released
Grasscutter for 4.1), and then log in to your server.

We downloaded the resources, then find the file and enter a single line
of the format into it http://127.0.0.1:2222 (if you use IP) or https://domain.com
http or https - it doesn't matter (it depends on your server settings), the main thing is that there
is one in the file a line with your server, otherwise you will go to YuukiPS.

After these manipulations, you will be able to log in to your server.

**Congratulations, you can play on a local network!**

If necessary, I can expand the guide with a detailed description of the installation of Android
Studio, LSPatch,
Shizuku, enabling debugging and other things.