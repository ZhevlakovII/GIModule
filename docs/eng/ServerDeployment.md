# Развертывание сервера

## Навигация

1. [Step 1. Checking ports](#step-1-checking-ports)
2. [Step 2. Enabling static IP](#step-2-enabling-the-internal-static-ip-for-the-pc-host)
3. [Step 3. Enabling DMZ](#step-3-enabling-dmz)
4. [Step 2. Server Setup](#step-4-server-setup)

### Step 1. Checking ports

It is important if you want to play on a local network or open a server for everyone - it is necessary
open ports and configure routing to your local machine (if you do it on a local
machine).
If you are deploying a server not on a local machine, the guide will not work for you. I will be glad to supplement
it when the necessary information becomes available.

And so, to check the ports, you need to go to the router settings. You can do this by following the links:

1. http://192.168.1.1
2. http://192.168.0.1
3. http://router.asus.com (**For owners of new routers from ASUS**)
4. By entering 192.168.0.1/192.168.1.1 into the browser search bar

I don't remember exactly what to enter, but you can find the instructions yourself by writing in
Google "How do I go to the router settings [Name of your router]".

**Attention!** The further guide will refer to ASUS routers with official firmware, to find
a guide for your router, go to Google with the query "How to open ports on
the router [Your router's name]".

### Step 2. Enabling the internal static IP for the PC host

We went to the settings, we are looking for the "Advanced Settings" menu.

![Advanced Settings](/docs/materials/images/router_global.png)

Click on "Local network" and find "DHCP server" in the menu.

![DHCP](/docs/materials/images/router_dhcp.png)

Scroll down and find the "List of manually assigned IP addresses". There we specify the PC on which
the server will be and write to it the IP address.

![DHCP enabling static IP](/docs/materials/images/router_dhcp_ip.png)

### Step 3. Enabling DMZ

Again, we are looking for the "Advanced Settings" menu.

![Advanced Settings](/docs/materials/images/router_global.png)

Click on "Internet" and find "DMZ" in the menu.

![DMZ](/docs/materials/images/router_dmz.png)

We went in, turned it on, and chose the IP of our PC. Saved the settings.

![DMZ enable](/docs/materials/images/router_dmz_ip.png)

### Step 4. Server Setup

Have the ports been unblocked? Cool. Now we need to configure the server.
Everything is simple here, download [Grasscutter](https://github.com/Grasscutter/Grasscutter ), follow the guide
to start the server (it's enough to just start and not get an error). Then the most
interesting thing begins.
Go to the folder with the server, find the config.json file. Open it with any editor and look for the parameter
"server".

![Настройки сервера](/docs/materials/images/gc_server_settings.jpg)

Next, in bindPort, AccessPort, we enter the current port (for example, 443), and in access Address we enter
the local IP (which we configured in the router). IT is IMPORTANT that the ip address should be in quotes ""
since the server then counts this string. If you leave without quotes or write something incomprehensible, then
the server simply won't start. We also turn off use Encryption and useInRouting. To use
HTTPS you need to create a local certificate, but this is for those who want to mess around.
Next, we are looking for the "game" parameter.

![Настройки игры](/docs/materials/images/gc_game_settings.jpg)

We do the same thing. ***IMPORTANT**, we do not touch bind Address, we leave it as it is (I do not know what this
setting is for :D)

After that, inside the "game" (lower in the list), we look for "server".

![Настройки сервера в игре](/docs/materials/images/gc_game_server_settings.jpg)

It's easier here - our IP is in the address, in port 443.

So, the server is configured and can be started. Next, we will go to configure the PC.