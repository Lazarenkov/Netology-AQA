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
* `docker-compose up`  
  *Далее открыть соседнюю вкладку терминала, т.к. текущая будет занята*
*  Для запуска SUT с **MySQL** выполнить `java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" "-Dspring.datasource.username=app" "-Dspring.datasource.password=pass" -jar ./artifacts/aqa-shop.jar -port=8080"`  
   или
*  Для запуска SUT с **Postgres** выполнить `java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" "-Dspring.datasource.username=app" "-Dspring.datasource.password=pass" -jar ./artifacts/aqa-shop.jar -port=8080`
### При последующих пусках:
1. Открыть папку проекта в IntelliJ IDEa
1. Последовательно выполнить в терминале команды:
* `docker-compose up`  
  *Далее открыть соседнюю вкладку терминала, т.к. текущая будет занята*
*  Для запуска SUT с **MySQL** выполнить `java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" "-Dspring.datasource.username=app" "-Dspring.datasource.password=pass" -jar ./artifacts/aqa-shop.jar -port=8080"`  
   или
*  Для запуска SUT с **Postgres** выполнить `java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" "-Dspring.datasource.username=app" "-Dspring.datasource.password=pass" -jar ./artifacts/aqa-shop.jar -port=8080`

---
Для запуска непосредственно тестов выполнить в еще одном инстансе терминала
* `./gradlew clean test` - если SUT запущен на MySQL  
  или
* `./gradlew clean test -Ddatabase=Postgre` - если SUT запущен на PostgreSQL  