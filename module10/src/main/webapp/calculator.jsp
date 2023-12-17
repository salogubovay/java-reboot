<!doctype html>
<html>

<head>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <title>Calculator</title>
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
</head>

<body>
<h1>Калькулятор доходности вклада</h1>
<form method="POST" action="">
    <span width=400px>Сумма на момент открытия</span><input name="sum"><br>
    <span>Процентная ставка</span><input name="percentage"><br>
    <span>Количество лет</span><input name="years"><br>
    <input type="submit" name="submit" value="Посчитать">
</form>
</body>
</html>