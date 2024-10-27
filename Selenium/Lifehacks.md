## Инструкция по запуску тестового стенда
Для работы стенда требуются:
* установленый Git
* установленная IntelliJ IDEa
* установленная JVM
### При первичном запуске:
1. Открыть любую папку на компьютере в Bash терминале
1. Выполнить `git clone https://github.com/Lazarenkov/Selenium`
1. Открыть скачанную папку проекта в IntelliJ IDEa
1. Открыть терминал PowerShell в IntelliJ IDEa
1. Выполнить в терминале команду: `java -jar ./artifacts/app-order.jar -port=9999`

### При последующих пусках:
1. Открыть скачанную папку проекта в IntelliJ IDEa
1. Открыть терминал PowerShell в IntelliJ IDEa
1. Выполнить в терминале команду: `java -jar ./artifacts/app-order.jar -port=9999`

---
Для запуска непосредственно тестов выполнить в еще одном инстансе терминала `./gradlew clean test` или нажать  `Ctrl+Ctrl` и в открывшемся шелле выполнить `gradle clean test`