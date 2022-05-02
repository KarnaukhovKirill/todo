# Приложение todo

## О проекте

Web-приложение для составления задач. 

![HTML5](https://img.shields.io/badge/html5-%23E34F26.svg?style=for-the-badge&logo=html5&logoColor=white)
![JavaScript](https://img.shields.io/badge/javascript-%23323330.svg?style=for-the-badge&logo=javascript&logoColor=%23F7DF1E)
![CSS3](https://img.shields.io/badge/css3-%231572B6.svg?style=for-the-badge&logo=css3&logoColor=white)
![jQuery](https://img.shields.io/badge/jquery-%230769AD.svg?style=for-the-badge&logo=jquery&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Hibernate](https://a11ybadges.com/badge?logo=hibernate)
![TravisCI](https://img.shields.io/badge/travis%20ci-%232B2F33.svg?style=for-the-badge&logo=travis&logoColor=white)

## Запуск приложения
1. Скачайте исходные файлы 
2. Создайте в Postgres базу данных todo
3. В файле src/main/resources/hibernate.cfg.xml измените username, password базы данных. 
4. Из корневой папки вызовите команду
````
mvn install
````
5. Файл targe/job4j_todo.war поместите в контейнер сервлетов (Apache Tomcat, например)

## Как пользоваться

Если пользователь не зарегистирован в приложении, он увидит окно входа
![alt text](https://github.com/KarnaukhovKirill/todo/blob/main/img/0_Auth.png)

По кнопке "Регистрация" можно перейти на форму регистрации нового пользователя
![alt text](https://github.com/KarnaukhovKirill/todo/blob/main/img/1_Reg.png)

Начальная страница. 
![alt text](https://github.com/KarnaukhovKirill/todo/blob/main/img/2_Main.png)

Можем добавить задачи, вбив их названия в строку ввода, выбрав одну или несколько категорий. Жмём "сохранить"
![alt text](https://github.com/KarnaukhovKirill/todo/blob/main/img/3_AddTasks.png)

Те задания, которые мы выполнили, можно скрыть, нажав чекбокс "выполнено"
![alt text](https://github.com/KarnaukhovKirill/todo/blob/main/img/4_DoneTasks.png)

Чтобы просмотреть весь список задач, нужно поставить галку "показать все задания"
![alt text](https://github.com/KarnaukhovKirill/todo/blob/main/img/5_ShowAll.png)

## TODO
- [ ] добавить логирование
- [ ] tests
- [ ] изменение формы категорий в UI

## Контакты
Есть вопросы по проекту? Свяжитесь со мной в [![alt text](https://badges.aleen42.com/src/telegram.svg)](https://t.me/Tur_Boyama)

Или пишите на почту: karnaukhov.kirill23@gmail.com

[![Build Status](https://app.travis-ci.com/KarnaukhovKirill/todo.svg?branch=main)](https://app.travis-ci.com/KarnaukhovKirill/todo)

