# Развертывание сервера

## Навигация

1. [Шаг 1. Проверка портов](#шаг-1-проверка-портов)
2. [Шаг 2. Включение статического IP](#шаг-2-включение-внутреннего-статического-ip-для-пк-хоста)
3. [Шаг 3. Включение DMZ](#шаг-3-включение-dmz)
4. [Шаг 2. Настройка сервера](#шаг-4-настройка-сервера)

### Шаг 1. Проверка портов

Важно, если вы хотите играть в локальной сети или открыть сервер для всех желающих - необходимо
открыть порты и настроить роутинг на вашу локальную машину (если вы это делаете на локальной
машине).
Если вы развертываете сервер не на локальной машине - гайд для вас не подойдёт. Буду рад дополнить
его, когда появится нужная информация.

Итак, для проверки портов вам необходимо зайти в настройки роутера. Сделать можно это по ссылкам:

1. http://192.168.1.1
2. http://192.168.0.1
3. http://router.asus.com (**Для владельцев новых роутеров от ASUS**)
4. Введя в поисковую строку браузера 192.168.0.1/192.168.1.1

Я точно не помню, что именно надо вводить, однако вы можете сами найти инструкцию, написав в
Google "Как мне зайти в настройки роутера [Название вашего роутера]".

**Внимание!** Дальнейший гайд будет относится к роутерам ASUS с оффициальной прошивкой, чтобы найти
гайд для вашего роутера идём в Google с запросом "Как открыть порты на
роутере [Название вашего роутера]".

### Шаг 2. Включение внутреннего статического IP для ПК-хоста

Зашли в настройки, ищем меню "Дополнительные настройки".

![Дополнительные настройки](/docs/materials/images/router_global.png)

Нажимаем на "Локальная сеть" и находим в менюшке "DHCP-сервер".

![DHCP](/docs/materials/images/router_dhcp.png)

Листаем вниз и находим "Список присвоенных вручную IP-адресов". Там указываем ПК, на котором будет
сервер и пишем ему IP-адрес.

![DHCP включение статического IP](/docs/materials/images/router_dhcp_ip.png)

### Шаг 3. Включение DMZ

Опять ищем меню "Дополнительные настройки".

![Дополнительные настройки](/docs/materials/images/router_global.png)

Нажимаем на "Интернет" и находим в менюшке "DMZ".

![DMZ](/docs/materials/images/router_dmz.png)

Зашли, включили, выбрали IP нашего ПК. Сохранили настройки.

![DMZ включение](/docs/materials/images/router_dmz_ip.png)

### Шаг 4. Настройка сервера

Разблокировали порты? Круто. Теперь нам надо настройить сервер.
Тут всё просто, скачиваем [Grasscutter](https://github.com/Grasscutters/Grasscutter), следуем гайду
для запуска сервера (достаточно просто запустить и не получить ошибки). Дальше начинается самое
интересное.
Заходим в папку с сервером, находим файл config.json. Открываем его любым редактором и ищем
параметр "server".

![Настройки сервера](/docs/materials/images/gc_server_settings.jpg)

Далее в bindPort, accessPort мы вводим текущий порт (например, 443), а в accessAddress вводим
локальный IP-ПК (который мы настраивали в роутере). ВАЖНО, ip-адрес должен быть в кавычках """ так
как эту строку потом считает сервер.
Если вы оставите без кавычек или напишите что-то непонятное, то сервер просто не запустится. Также
мы выключаем useEncryption и useInRouting. Для использования HTTPS нужно создать локальный
сертификат, но это уже для тех, кто хочет заморочиться.
Дальше ищем параметр "game".

![Настройки игры](/docs/materials/images/gc_game_settings.jpg)

Делаем тоже самое. **ВАЖНО**, bindAddress не трогаем, оставляем как есть (я не знаю для чего эта
настройка :D)

После чего внутри "game" (ниже по списку) ищем "server".

![Настройки сервера в игре](/docs/materials/images/gc_game_server_settings.jpg)

Тут уже проще - в address наш IP, в port 443.

Итак, сервер настроен и можно запускать. Далее мы пойдём настраивать ПК.