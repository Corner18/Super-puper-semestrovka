function validform() {
    var a = document.forms["my-form"]["first_name"].value;
    var b = document.forms["my-form"]["second_name"].value;
    var c = document.forms["my-form"]["patronymic"].value;
    var d = document.forms["my-form"]["email-address"].value;
    var e = document.forms["my-form"]["city"].value;
    var f = document.forms["my-form"]["activityKind"].value;

    if (a == null || a === "") {
        alert("Пожалуйста напишите ваше имя");
        return false;
    } else if (b == null || b === "") {
        alert("Пожалуйста напишите вашу фамилию");
        return false;
    } else if (c == null || c === "") {
        alert("Пожалуйста напишите ваше отчество");
        return false;
    } else if (d == null || d === "") {
        alert("Пожалуйста напишите ваш e-mail");
        return false;
    } else if (e == null || e === "") {
        alert("Пожалуйста укажите ваш город");
        return false;
    } else if (f == null || f === "") {
        alert("Пожалуйста напишите ваш род детяльности");
        return false;
    }
}