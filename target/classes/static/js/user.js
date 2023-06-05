$(async function () {
    await getUsers();
    await newUser();
    await updateUser();
})

async function getUsers() {
    let data = $('#userData');
    data.empty();
    fetch("http://localhost:8080/api/user")
        .then(response => response.json())
        .then(user => {
            console.log(user);

            document.getElementById("span322").innerHTML = user.principal.userName;

            let roleNames = "";

            user.principal.roles.forEach((role, index) => {
                if (index > 0) {
                    roleNames += ", ";
                }
                roleNames += role.name.replace("ROLE_", "");
            });

            document.getElementById("span228").innerHTML = roleNames;
        })
    fetch("http://localhost:8080/api/admin")
        .then(response => response.json())
        .then(userData => {
            console.log(userData);
            if (userData.length > 0) {
                userData.forEach((u) => {
                    let tableFilling = `$(
                        <tr>
                            <td>${u.id}</td>
                            <td>${u.name}</td>
                            <td>${u.lastName}</td>
                            <td>${u.userName}</td>
                            <td>${u.roles.map(r => r.name.replace("ROLE_", ""))}</td>
                        </tr>
                    )`;
                    data.append(tableFilling);
                })
            }
        })
}