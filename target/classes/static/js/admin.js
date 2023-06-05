$(async function () {
    await getUsers();
    await newUser();
    await updateUser();
})

async function getUsers() {
    let data = $('#userData');
    data.empty();
    fetch("http://localhost:8080/api/roles")
        .then(response => response.json())
        .then(roles => {
            console.log(roles);
            let roleNames = "";

            roles.forEach((role, index) => {
                if (index > 0) {
                    roleNames += ", ";
                }
                roleNames += role.name.replace("ROLE_", "");
            });

            document.getElementById("span228").innerHTML = roleNames;
        })
    fetch("http://localhost:8080/api/user")
        .then(response => response.json())
        .then(user => {
            console.log(user);

            document.getElementById("span322").innerHTML = user.principal.userName;
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
                            <td>
                                <button type="button" data-userid="${u.id}" data-action="update" class="btn btn-info" 
                                data-toggle="modal" data-target="#updateModal">Update</button>
                            </td>
                            <td>
                                <button type="button" data-userid="${u.id}" data-action="delete" class="btn btn-danger" onclick="deleteUser(${u.id})">Delete</button>
                            </td>
                        </tr>
                    )`;
                    data.append(tableFilling);
                })
            }
        })
}

async function newUser() {
    const form = document.forms["userForm"];

    form.addEventListener('submit', (e) => {
        e.preventDefault();
        let addRoles = [];
        for (let i = 0; i < form.roles.options.length; i++) {
            if (form.roles.options[i].selected) addRoles.push({
                id: form.roles.options[i].value,
                name: form.roles.options[i].text
            })
        }
        fetch("http://localhost:8080/api/admin", {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                name: form.firstName.value,
                lastName: form.lastName.value,
                userName: form.userName.value,
                age: form.age.value,
                password: form.password.value,
                roles: addRoles
            })
        }).then(() => {
            form.reset();
            getUsers();
            $('#usersTable').click();
        })
    })
}

$('#updateModal').on('show.bs.modal', ev => {
    let button = $(ev.relatedTarget);
    let id = button.attr('data-userid');
    updateModal(id);
})

async function updateModal(id) {
    let form = document.forms["modalUpdate"];
    let user = await fetch(`http://localhost:8080/api/admin/${id}`)
        .then(response => response.json());

    $('#roleUpdate').empty();
    fetch("http://localhost:8080/api/roles")
        .then(response => response.json())
        .then(roles => {
            console.log(roles);
            roles.forEach(role => {
                let select = false;
                for (let i = 0; i < user.roles.length; i++) {
                    if (user.roles[i].name === role.name) {
                        select = true;
                        break;
                    }
                }
                let el = document.createElement("option");
                el.value = role.id;
                el.text = role.name;
                if (select) {
                    el.selected = true;
                }
                $('#roleUpdate')[0].appendChild(el);
            })
        });
    form.id.value = user.id;
    form.firstNameUpdate.value = user.name;
    form.lastNameUpdate.value = user.lastName;
    form.userNameUpdate.value = user.userName;
    form.ageUpdate.value = user.age;
    form.passwordUpdate.value = user.password;
}

async function updateUser() {
    const form = document.forms["modalUpdate"];
    form.addEventListener("submit", async (e) => {
        e.preventDefault();
        let updateRoles = [];
        for (let i = 0; i < form.roles.options.length; i++) {
            if (form.roles.options[i].selected) updateRoles.push({
                id: form.roles.options[i].value,
                name: form.roles.options[i].text
            })
        }
        fetch("http://localhost:8080/api/admin", {
            method: 'PUT',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                id: form.id.value,
                name: form.firstNameUpdate.value,
                lastName: form.lastNameUpdate.value,
                userName: form.userNameUpdate.value,
                age: form.ageUpdate.value,
                password: form.passwordUpdate.value,
                roles: updateRoles
            })
        }).then(() => {
            getUsers();
            $('#closeUpdateButton').click();
        })
    })
}

async function deleteUser(id) {
    fetch(`http://localhost:8080/api/admin/${id}`, {
        method: 'DELETE',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    }).then(() => {
        getUsers();
        $('#closeDeleteButton').click();
    })
}


