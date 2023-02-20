## Инструкция по запуску тестового стенда
Для работы стенда требуются:
* установленый Git
* установленная IntelliJ IDEa
* установленный и запущенный Docker Desktop
### При первичном запуске:
1. Открыть любую папку на компьютере в Bash терминале
1. Выполнить `git clone https://github.com/Lazarenkov/API-SQL`
1. Открыть скачанную папку проекта в IntelliJ IDEa
1. Открыть терминал IntelliJ IDEa
1. Последовательно выполнить в терминале команды:
* `docker-compose up`  
  *Далее открыть соседнюю вкладку терминала, т.к. текущая будет занята*
*  Для запуска SUT с выполнить `java -jar ./artifacts/app-deadline.jar -P:jdbc.url=jdbc:mysql://localhost:3306/db -P:jdbc.user=user -P:jdbc.password=qwerty123`  
   
### При последующих пусках:
1. Открыть папку проекта в IntelliJ IDEa
1. Последовательно выполнить в терминале команды:
* `docker-compose up`  
  *Далее открыть соседнюю вкладку терминала, т.к. текущая будет занята*
*  Для запуска SUT с **MySQL** выполнить `java -jar ./artifacts/app-deadline.jar -P:jdbc.url=jdbc:mysql://localhost:3306/db -P:jdbc.user=user -P:jdbc.password=qwerty123`
---
Для запуска непосредственно тестов выполнить в еще одном инстансе терминала
* `./gradlew clean test`  
* bkb
* Ввести на клавиатуре CTRL+CTRL и в открывшенмся шелле ввести команду `gradle clean test` и затем ENTER
  