## Инструкция по запуску тестового стенда
Для работы стенда требуются:
* установленый Git
* установленная IntelliJ IDEa
* установленный и запущенный Docker Desktop
### При первичном запуске:
1. Открыть любую папку на компьютере в Bash терминале
1. Выполнить `git clone https://github.com/Lazarenkov/Diploma`
1. Открыть скачанную папку проекта в IntelliJ IDEa
1. Открыть терминал IntelliJ IDEa
1. Последовательно выполнить в терминале команды:
* `cd ./gate-simalutor`
* `docker build . -t gate-simulator/node-web-app`
* `cd ..`
* `docker run -p 9999:9999 -d gate-simulator/node-web-app`
* `docker-compose up`
* *Открыть соседнюю вкладку терминала, т.к. текущая будет занята*
* `java -jar ./artifacts/aqa-shop.jar -port=8080`
### При последующих пусках:
1. Открыть папку проекта в IntelliJ IDEa
1. Последовательно выполнить в консоли команды:
* `docker run -p 9999:9999 -d gate-simulator/node-web-app`
* `docker-compose up`
* *В соседней вкладке терминала* `java -jar ./artifacts/aqa-shop.jar -port=8080`

---
Для запуска непосредственно тестов выполнить в еще одном инстансе терминала `./gradlew clean test` или нажать  `Ctrl+Ctrl` и в открывшемся шелле выполнить `gradle clean test`