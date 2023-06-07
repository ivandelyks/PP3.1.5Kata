// $(async function () {
//     await getUsers();
// })

const currentUser = "http://localhost:8080/api/user/currentUser"
//шапка
$(document).ready(function() {
    $.ajax({
        url: currentUser,
        type: 'GET',
        success: function(user) {
            $('#username').text(user.username);
            let roles = user.roles.map(function (role) {
                return role.name === 'ROLE_ADMIN' ? 'ADMIN' : 'USER';
            });
            $('#role').text(roles.join(' '));
        },
        error: function() {
            console.log('Error getting current user');
        }
    });
});
//таблица
$(document).ready(function() {
    $.ajax({
        url: currentUser,
        type: 'GET',
        success: function(user) {
            $('.id').text(user.id);
            $('.name').text(user.name);
            $('.surname').text(user.lastName);
            $('.username').text(user.username);
            let roles = user.roles.map(function(role) {
                return role.name === 'ROLE_ADMIN' ? 'ADMIN' : 'USER';
            });
            $('.roles').text(roles.join(', '));
        },
        error: function() {
            console.log('Error getting current user');
        }
    });
});

//пункт меню admin скрыть от обычного юзера
$(document).ready(function() {
    $.ajax({
        url: currentUser,
        type: 'GET',
        success: function(user) {
            let isAdmin = user.roles.some(function(role) {
                return role.name === 'ROLE_ADMIN';
            });
            if (isAdmin) {
                $('#adminMenuItem').show();
            } else {
                $('#adminMenuItem').hide();
            }
        },
        error: function() {
            console.log('Error getting current user');
        }
    });
});



// async function getUsers() {
//     let data = $('#userData');
//     data.empty();
//     fetch("http://localhost:8080/api/user")
//         .then(response => response.json())
//         .then(user => {
//             console.log(user);
//
//             document.getElementById("span322").innerHTML = user.principal.userName;
//
//             let roleNames = "";
//
//             user.principal.roles.forEach((role, index) => {
//                 if (index > 0) {
//                     roleNames += ", ";
//                 }
//                 roleNames += role.name.replace("ROLE_", "");
//             });
//
//             document.getElementById("span228").innerHTML = roleNames;
//         })
//     fetch("http://localhost:8080/api/admin")
//         .then(response => response.json())
//         .then(userData => {
//             console.log(userData);
//             if (userData.length > 0) {
//                 userData.forEach((u) => {
//                     let tableFilling = `$(
//                         <tr>
//                             <td>${u.id}</td>
//                             <td>${u.name}</td>
//                             <td>${u.lastName}</td>
//                             <td>${u.userName}</td>
//                             <td>${u.roles.map(r => r.name.replace("ROLE_", ""))}</td>
//                         </tr>
//                     )`;
//                     data.append(tableFilling);
//                 })
//             }
//         })
// }


