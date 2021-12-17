----------Подготовка окружения и запуск тестов----------
1. На машине должна быть установлена java 1.8
2. На машину необходимо установить maven и allure 
3. Открываем папку с тестами и вызываем cmd.
4. Выполняем команду mvn clean test -Dtest=testAuthUser (стандартно browser Chrome, headless = true - без поднятия браузера)
  4.1 Запуск с параметрами mvn clean test -Dbrowser=firefox -Dheadless=true -Dtest=testAuthUser
  4.2 Пока отсутствует возможность запуска тестов по тегам(позже будет)
  4.3 Пока отсутствует возможность паралельного запуска тестов(позже будет)
5. По завершению тестов можно сформировать отчет командой  mvn allure:serve
 - Allure сам поднимет веб сервер и откроет отчет в браузере
 - После завершения просмотра отчета, необходимо завершить соединение(убить сервер) в cmd
